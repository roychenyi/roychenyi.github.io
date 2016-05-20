package com.cx.wxs.action.user;

import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Random;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.cx.wxs.action.BaseUserAction;
import com.cx.wxs.dto.BConfigDto;
import com.cx.wxs.dto.BSiteDto;
import com.cx.wxs.dto.DCatalogDto;
import com.cx.wxs.dto.SysStyleDto;
import com.cx.wxs.dto.UUserDto;
import com.cx.wxs.enums.EmailType;
import com.cx.wxs.service.BConfigService;
import com.cx.wxs.service.BSiteService;
import com.cx.wxs.service.DCatalogService;
import com.cx.wxs.service.EmailService;
import com.cx.wxs.service.SysIllegalService;
import com.cx.wxs.service.UUserService;
import com.cx.wxs.utils.HtmlNodeFilters;
import com.cx.wxs.utils.RequestUtils;
import com.cx.wxs.utils.StringUtils;
import com.cx.wxs.utils.TemplateUtils;
import com.cx.wxs.utils.ValidateCode;
import com.cx.wxs.utils.ClientInfo;

/**
 * 用户登陆、注册、用户验证
 * @author 陈义
 * @date   2016-3-21 下午9:03:08
 */
@Controller
public class UserAction extends BaseUserAction{
	
	@Resource
	private UUserService uuService;
	@Resource
	private EmailService emailService;
	@Resource
	private BSiteService bSiteService;
	@Resource
	private SysIllegalService sysIllegalService;
	@Resource
	private BConfigService bConfigService;
	@Resource
	private DCatalogService dCatalogService;

	

	/***
	 *跳转进入登陆页面 
	 * @param request
	 * @param response
	 * @return
	 * @author 陈义
	 * @date   2016-2-29下午9:29:54
	 */
	@RequestMapping(value="/login")
	public ModelAndView tologin(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv=new ModelAndView("user/login");
		String prev_url=request.getHeader("Referer");
		mv.addObject("prev_url", prev_url);
		return mv;

	}

	/***
	 * 前端登陆
	 * @return
	 * @author 陈义
	 * @date   2016-1-7下午4:28:12
	 */
	@RequestMapping(value="/login1")
	@ResponseBody
	public  JSONObject loginCheck(HttpServletRequest request,
			HttpServletResponse response){		
		String prev_url=request.getParameter("prev_url");
		if(prev_url==null||prev_url==""){
			prev_url=request.getHeader("Referer");
		}
	//	HttpSession session= request.getSession();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String verifyCode=request.getParameter("verifyCode");
		String ip=ClientInfo.getIpAddr(request);
		String codeSession=(String) request.getSession().getAttribute("verifycode");
		UUserDto uuser=new UUserDto();
		//		StatusFlag ="1";    //1:登陆成功；-1 ：登陆失败，密码或用户名错误；0：验证码错误
		System.out.println("code1:"+codeSession+" code:"+verifyCode);
   	if(verifyCode.equalsIgnoreCase(codeSession)){
		uuser.setUsername(username);
		uuser.setPassword(StringUtils.md5(password));
		uuser.setIp(ip);
		uuser= uuService.login(uuser);
		uuser.setUrl(prev_url);
		if(uuser==null){
			return null;
		}else if(uuser.getStatusFlag().equals("1")){
			if(uuser.getPopedom()!=1){
				uuser.setStatusFlag("-3");
			}else{
				RequestUtils.setUserInfo(request, response, uuser);
			}
		}
		}else{
			  return null;	
			}
		JSONObject json=(JSONObject) JSONObject.toJSON(uuser);
		return json;
	}
	
	@RequestMapping(value="/login2")
	public ModelAndView login2(HttpServletRequest request,
			HttpServletResponse response){
		ModelAndView mv=new ModelAndView("main");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String verifyCode=request.getParameter("verifyCode");
		System.out.println("username:"+username);
		System.out.println("password:"+password);
		return mv;
	}
/**
 * 用户退出登录
 * @param request
 * @param response
 * @return
 * @author 陈义
 * @date   2016-5-14下午8:32:30
 */
	@RequestMapping(value="/logout")
	public ModelAndView logout(HttpServletRequest request,
			HttpServletResponse response){
		String prev_url=request.getHeader("Referer");
		ModelAndView  mv=new ModelAndView("redirect:"+prev_url);
		//request.getSession().removeAttribute("user");
		HttpSession session= request.getSession();
		session.removeAttribute("user");
		request.getSession().invalidate();
		RequestUtils.removeCookie(request, response, "user");
		return mv;
	}

	/***
	 * 跳转到注册页面
	 * @param request
	 * @param response
	 * @return
	 * @author 陈义
	 * @date   2016-5-14下午8:32:59
	 */
	@RequestMapping(value="/register")
	public ModelAndView toRegister(HttpServletRequest request,
			HttpServletResponse response){
		ModelAndView mv=new ModelAndView("user/register");
		String prev_url=request.getHeader("Referer");
		mv.addObject("prev_url", prev_url);
		return mv;
	}

	/***
	 * 注册确认，发送验证邮件
	 * @param request
	 * @param response
	 * @param userDto
	 * @return
	 * @author 陈义
	 * @date   2016-5-14下午8:33:21
	 */
	@ResponseBody 
	@RequestMapping(value="/register1",method=RequestMethod.POST)
	public UUserDto register(HttpServletRequest request,
			HttpServletResponse response,UUserDto userDto){
		String prev_url=request.getParameter("prev_url");
		String password=StringUtils.md5(request.getParameter("password"));
		userDto.setEmail(userDto.getUsername());
		userDto.setPassword(password);
		int popedom=(int)((Math.random()*9+1)*100000);
		userDto.setPopedom(popedom);
		userDto.setRoleId(2);
		if(uuService.addUuser(userDto)>0){
			userDto.setUrl(prev_url);
			this.sendRegisterCheckMail(request, userDto);
			userDto.setStatusFlag("1");
		}else{
			userDto.setStatusFlag("-1");
		}		
		return userDto;
	}
	/***
	 * 验证邮箱验证
	 * @param request
	 * @param response
	 * @return
	 * @author 陈义
	 * @date   2016-5-14下午8:33:45
	 */
	@RequestMapping(value="register/check")
	public ModelAndView registerCheck(HttpServletRequest request,
			HttpServletResponse response){
		ModelAndView mv=new ModelAndView("user/regcheck");
	    String username=request.getParameter("username");
	    Integer popedom=Integer.parseInt(request.getParameter("popedom"));
	    String prev_url=request.getParameter("prev_url");
	    UUserDto userDto=new UUserDto();
	    userDto.setUsername(username);
	    userDto= uuService.getUuser(userDto);
	    if(userDto.getPopedom()==1){
	    	UUserDto user=(UUserDto) request.getSession().getAttribute("user");
	    	if(user!=null){
	    		mv.setViewName("redirect:"+prev_url);
	    		return mv;
	    	}else{
	    //	mv.addObject("user", userDto);
	    	return mv;
	    	}
	    }
	    if(userDto.getUserId()!=null&userDto.getPopedom().equals(popedom)){
	    	//验证成功
	    	userDto.setPopedom(1);
	    	userDto.setUid(userDto.getUserId());
	    	//开通空间
            BSiteDto siteDto=openBlog(userDto);
	    	if(siteDto!=null){
	    		userDto.setBSiteDto(siteDto);
	    	}
	    	if(uuService.updateUuser(userDto)>0){
	    		userDto.setStatusFlag("1");
	    		userDto.setUrl(prev_url);    		
	    	}else{
	    		userDto=null;
	    	}
	    	
	    }else{
	    	userDto=null;
	    }
	    mv.addObject("user", userDto); 
		return mv;
	}

	/***
	 * 跳转到页面的忘记页面
	 * @param request
	 * @param response
	 * @return
	 * @author 陈义
	 * @date   2016-5-14下午8:34:03
	 */
	@RequestMapping(value="/forgot")
	public ModelAndView toforget(HttpServletRequest request,
			HttpServletResponse response){
		ModelAndView mv=new ModelAndView("user/iforgot");
		String prev_url=request.getHeader("Referer");
		mv.addObject("prev_url", prev_url);
		return mv;
	}


	/***
	 * 忘记页面验证结果
	 * @param request
	 * @param response
	 * @return
	 * @author 陈义
	 * @date   2016-5-14下午8:34:21
	 */
	@RequestMapping(value="/forgot/result")
	@ResponseBody
	public UUserDto forget(HttpServletRequest request,HttpServletResponse response){
		UUserDto user=new UUserDto();
		String prev_url=request.getParameter("prev_url");
		if(prev_url==null||prev_url==""){
			prev_url=request.getHeader("Referer");
		}
		String username=request.getParameter("username");
		user.setUsername(username);

		HttpSession session=request.getSession();
		String verifyCode=request.getParameter("verifyCode");
		String codeSession=(String) request.getSession().getAttribute("verifycode");
		if(!verifyCode.equalsIgnoreCase(codeSession)){
			return null;
		}
		StringBuffer url = request.getRequestURL();  
		String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getServletContext().getContextPath()).append("/").toString(); 
        tempContextUrl+="forgot/changepassword";
		try {
			user=uuService.getUuser(user);
			if(user==null){
				return null;
			}
			user.setUrl(prev_url);
			ValidateCode code=new ValidateCode();
			String checkCode=code.getCkeckCode();
			session.setAttribute("newCheckCode", checkCode);
			tempContextUrl+="?uid="+user.getUserId()+"&username="+user.getUsername()+"&prev_url="+prev_url+"&newCheckCode="+checkCode+"&nocahe="+new Date().getTime();
			// 邮件内容
			String template=TemplateUtils.getPasswordTipTemplate();
			String notify_content = MessageFormat.format(template,
					user.getNickname(), tempContextUrl, tempContextUrl);
		//	String email_content = "<h1>欢迎验证</h1><p>请点击下面链接进行验证，<a href=\""+tempContextUrl+"\" target=\"_blank\">"+tempContextUrl+"</a></p>";
//			Parser html = new Parser();
//			html.setEncoding("UTF-8");
//			html.setInputHTML(email_content);
//			Node[] nodes = html.extractAllNodesThatMatch(
//					HtmlNodeFilters.titleFilter).toNodeArray();
			String title = "文学社平台重置密码邮箱验证";
			String[] receivers=new String[]{username};
			emailService.sendEmail(title, receivers, notify_content, EmailType.VALIDATE);
			System.out.println("验证链接已发送！");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}
	/***
	 * 修改密码界面
	 * @param request
	 * @param response
	 * @return
	 * @author 陈义
	 * @date   2016-5-14下午8:34:48
	 */
	@RequestMapping(value="/forgot/changepassword")
	public ModelAndView changePassword(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv=new ModelAndView();
		String newCheckCode=request.getParameter("newCheckCode");
		String checkCode=(String) request.getSession().getAttribute("newCheckCode");
		String prev_url=request.getParameter("prev_url");
		String username=request.getParameter("username");
		String uid=request.getParameter("uid");
		mv.addObject("uid", uid);
		mv.addObject("username",username);
		mv.addObject("prev_url",prev_url);
		if(newCheckCode.equals(checkCode)||newCheckCode==checkCode){
			mv.setViewName("user/changepassword");
		}else{
			mv.setViewName("404");
		}
		return mv;
	}
	/***
	 * 密码修改
	 * @param uid
	 * @param request
	 * @param response
	 * @return
	 * @author 陈义
	 * @date   2016-5-14下午8:35:17
	 */
	@ResponseBody
	@RequestMapping(value="/forgot/change" , method = { RequestMethod.GET, RequestMethod.POST }, produces="application/json")
	public UUserDto changePassword1(Integer uid, HttpServletRequest request,HttpServletResponse response){
		UUserDto uuserDto =new UUserDto();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String prev_rul=request.getParameter("prev_url");
		password=StringUtils.md5(password);
		uuserDto.setUrl(prev_rul);
		uuserDto.setUid(uid);
		uuserDto.setUserId(uid);
		uuserDto.setUsername(username);
		uuserDto=uuService.getUuser(uuserDto);
		uuserDto.setUid(uuserDto.getUserId());
		uuserDto.setPassword(password);
		if(uuService.updateUuser(uuserDto)>0){
			uuserDto.setStatusFlag("1");
			uuserDto.setUrl(prev_rul);
//			HttpSession session=request.getSession();
//			session.setAttribute("user", uuserDto);			
		}else{
			uuserDto.setStatusFlag("-1");
		}
		return uuserDto;
	}
	/***
	 * 修改密码界面
	 * @param request
	 * @return
	 * @author 陈义
	 * @date   2016-5-14下午8:35:40
	 */
	@RequestMapping(value="/change/newpassword")
	public ModelAndView newPassword(HttpServletRequest request){
		ModelAndView mv=new ModelAndView("user/newpassword");
		String prev_url=request.getHeader("Referer");
		mv.addObject("prev_url", prev_url);
		return mv;
	}
	/***
	 * 修改新密码结果
	 * @param request
	 * @param response
	 * @return
	 * @author 陈义
	 * @date   2016-5-14下午8:35:53
	 */
	@ResponseBody
	@RequestMapping(value="/change/new")
	public UUserDto newPassword1(HttpServletRequest request,HttpServletResponse response){
		HttpSession session=request.getSession();
		UUserDto uuserDto=(UUserDto) session.getAttribute("user");
		String oldPwd=StringUtils.md5(request.getParameter("oldPassword"));
		String newPwd=StringUtils.md5(request.getParameter("password"));
		String prev_url=request.getParameter("prev_url");
		if(uuserDto.getPassword().equals(oldPwd)){
		  	uuserDto.setPassword(newPwd);
			String ip=ClientInfo.getIpAddr(request);
			uuserDto.setIp(ip);
			Date date=new Date();
			uuserDto.setLastTime(new Timestamp(date.getTime()));
			uuserDto.setUid(uuserDto.getUserId());		
		  	if(uuService.updateUuser(uuserDto)>0){
		  		uuserDto.setStatusFlag("1");
		  		session.setAttribute("user", uuserDto);
		  	}else{
		  		uuserDto.setStatusFlag("0");
		  	}
		}else{
			uuserDto.setStatusFlag("-1");
		}
		uuserDto.setUrl(prev_url);
		return uuserDto;
	}
		
	/***
	 * 验证码验证
	 * @param VerifyCode
	 * @param request
	 * @return
	 * @author 陈义
	 * @date   2016-5-14下午8:36:19
	 */
	@ResponseBody
	@RequestMapping(value="/checkVerifyCode")
	public String checkVerifyCode(String VerifyCode,HttpServletRequest request){
	//	String verifyCode=(String) request.getAttribute("VerifyCode");
		String codeSession=(String) request.getSession().getAttribute("verifycode");
		String  flag="0";
		if(VerifyCode.equalsIgnoreCase(codeSession)){
			flag="1";
			return flag;
		}else{
			return flag;
		}
	}
	/***
	 * 验证用户名唯一性和可用性
	 * @param username
	 * @return
	 * @author 陈义
	 * @date   2016-3-22上午11:11:07
	 */
	@ResponseBody
	@RequestMapping(value="/user/checkusername")
	public String usernameCheck(String username){
		UUserDto user=new UUserDto();
		user.setUsername(username);
		user=uuService.getUuser(user);
		String flag="1";
		if(user==null){
			flag="1";
		}else{
			flag="0";
		}
		return flag;
	}
	/***
	 * 验证昵称的唯一性和可用性
	 * @param nickname
	 * @return
	 * @author 陈义
	 * @date   2016-3-22上午11:11:49
	 */
	@ResponseBody
	@RequestMapping(value="/user/checknickname")
	public String nicknameCheck(String nickname){
		//检查是否包含非法字符
		String flag="0";
		if(!sysIllegalService.IllegalCheck(nickname)){
		 flag="-1";
		return flag;
		}		
		//验证用户表中是否有昵称为这个名字的
		UUserDto user=new UUserDto();
		user.setNickname(nickname);
		user=uuService.getUuser(user);
		if(user==null){
			flag="1";
		}else{
			flag="0";
		}
		return flag;
	}
	/**
	 * 发送注册链接
	 * @param request
	 * @param response
	 * @param userDto
	 * @return
	 * @author 陈义
	 * @date   2016-5-19下午10:19:36
	 */
	@ResponseBody 
	@RequestMapping(value="/register/sendmail")
	public UUserDto sendRegisterMail(HttpServletRequest request,HttpServletResponse response,UUserDto userDto){
		String url=RequestUtils.getDomain(request);
		url+="/login1";
		userDto=this.getUserDtoByUsername(userDto.getUsername());
		userDto.setUrl(url);
		if(this.sendRegisterCheckMail(request, userDto)){
			userDto.setStatusFlag("1");
		}else{
			userDto.setStatusFlag("-1");
		}
		return userDto;
	}
	
	public String sendPasswordNotify(){
		return null;
	}
	/***
	 * 开通博客
	 * @param userDto
	 * @return
	 * @author 陈义
	 * @date   2016-3-24下午3:23:04
	 */
	public  BSiteDto openBlog(UUserDto userDto){
		Date date=new Date();
		BSiteDto bSiteDto=new BSiteDto();
    	bSiteDto.setUUserDto(userDto);
    	bSiteDto.setName(userDto.getNickname());
    	bSiteDto.setTitle(userDto.getNickname()+"的博客");
    	bSiteDto.setSiteUrl("/"+userDto.getNickname());
    	SysStyleDto styleDto=new SysStyleDto();
    	styleDto.setStyleId(1);	    	
    	bSiteDto.setSysStyleDto(styleDto);
    	bSiteDto.setMode((short)1);
    	bSiteDto.setCreateTime(new Timestamp(date.getTime()));
    	int id=bSiteService.addBSite(bSiteDto);
    	if(id>0){
    		bSiteDto.setSiteId(id);
    		//日志分类添加一个默认分类：个人日记
    		DCatalogDto catalogDto=new DCatalogDto();
    		catalogDto.setUUserDto(userDto);
    		catalogDto.setCatalogName("个人日记");
    		catalogDto.setCatalogType(1);
    		catalogDto.setArticleCount(0);
    		catalogDto.setCreateTime(new Timestamp(date.getTime()));
    		dCatalogService.addDCatalog(catalogDto);
    		//空间配置：添加允许投稿
    		BConfigDto configDto=new BConfigDto();
    		configDto.setBSiteDto(bSiteDto);
    		configDto.setConfigKey("allow_pull");
    		configDto.setValue("1");
    		configDto.setCreateTime(new Timestamp(date.getTime()));
    		bConfigService.addBConfig(configDto);
    		return bSiteDto;
    	}else{
    		return null;
    	}
	}
}
