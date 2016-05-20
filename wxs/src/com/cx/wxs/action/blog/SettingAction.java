package com.cx.wxs.action.blog;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cx.wxs.action.BaseAction;
import com.cx.wxs.dto.BConfigDto;
import com.cx.wxs.dto.BSiteDto;
import com.cx.wxs.dto.DCatalogDto;
import com.cx.wxs.dto.DDiaryDto;
import com.cx.wxs.dto.USignDto;
import com.cx.wxs.dto.UUserDto;
import com.cx.wxs.service.BConfigService;
import com.cx.wxs.service.BSiteService;
import com.cx.wxs.service.DCatalogService;
import com.cx.wxs.service.DDiaryService;
import com.cx.wxs.service.USignService;
import com.cx.wxs.service.UUserService;
import com.cx.wxs.utils.RequestUtils;

/**
 * @author 陈义
 * @date   2016-4-2 下午3:08:20
 */
@Controller
@RequestMapping("/{vip}/set")
public class SettingAction extends BaseAction{
	@Resource
	private BSiteService bSiteService;
	@Resource
	private UUserService uUserService;
	@Resource
	private USignService uSignService;
	@Resource
	private BConfigService bConfigService;
	@Resource 
	private DCatalogService dCatalogService;
	@Resource
	private DDiaryService diaryService;
	

	
	@RequestMapping(value="/index")
	public ModelAndView toSettingIndex(@PathVariable("vip") String vip,
			HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv=new ModelAndView("blog/setting/index");
		UUserDto userDto=(UUserDto) request.getSession().getAttribute("user");
		if(userDto==null){
			mv.setViewName("404");
		}
		return mv;
	}
	@RequestMapping(value="/site")
	public ModelAndView toSettingSite(@PathVariable("vip") String vip,
			HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv=new ModelAndView("blog/setting/site");
		System.out.println(vip);
		UUserDto userDto=(UUserDto) request.getSession().getAttribute("user");
		if(userDto==null){
			mv.setViewName("404");
			return mv;
		}
		//允许投稿
		BConfigDto allow_pullConfig=new BConfigDto();
		allow_pullConfig.setBSiteDto(userDto.getBSiteDto());
		allow_pullConfig.setConfigKey("allow_pull");
		allow_pullConfig=bConfigService.getBConfig(allow_pullConfig);
		mv.addObject("allow_pullConfig", allow_pullConfig);
		//文章目录
		DCatalogDto catalogDto=new DCatalogDto();
		catalogDto.setUUserDto(userDto);
		List<DCatalogDto> cataLogList=dCatalogService.getDCatalogList(catalogDto);
		mv.addObject("catalogList", cataLogList);
		return mv;
	}
	
	@RequestMapping(value="/privacy")
	public ModelAndView toSettingPrivacy(@PathVariable("vip") String vip,
			HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv=new ModelAndView("blog/setting/privacy");
		UUserDto userDto=(UUserDto) request.getSession().getAttribute("user");
		if(userDto==null){
			mv.setViewName("404");
		}
		return mv;
	}
	
	@RequestMapping(value="/avatar")
	public ModelAndView toSettingAvatar(@PathVariable("vip") String vip,
			HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv=new ModelAndView("blog/setting/avatar");
		UUserDto userDto=(UUserDto) request.getSession().getAttribute("user");
		if(userDto==null){
			mv.setViewName("404");
		}
		return mv;
	}
	/**
	 * 更新用户信息
	 * @param vip
	 * @param request
	 * @param response
	 * @param userDto
	 * @return
	 * @author 陈义
	 * @date   2016-4-9上午10:51:09
	 */
	@RequestMapping(value="/setUserInfo")
	@ResponseBody
	public UUserDto setUserInfo(@PathVariable("vip") String vip,HttpServletRequest request,HttpServletResponse response,UUserDto userDto){
		userDto.setNickname(vip);
		String signstr=request.getParameter("sign");
		USignDto uSignDto=new USignDto();
		Date date=new Date();
		if(signstr!=null&&signstr!=""){
			uSignDto.setUUserDto(userDto);
			uSignDto.setContent(signstr);
			uSignDto.setStatus((short)1);
			int signId= uSignService.setUserSign(uSignDto);
			if(signId>0){
				uSignDto.setSignId(signId);
				userDto.setUSignDto(uSignDto);
			}
		}
		userDto.setLastTime(new Timestamp(date.getTime()));
		if(uUserService.updateUuser(userDto)>0){
			userDto=uUserService.getUuser(userDto);
			userDto.setStatusFlag("1");
			RequestUtils.setUserInfo(request, response, userDto);
		}else{
			userDto.setStatusFlag("-1");
		}
		return userDto;
	}
	/***
	 * 更新站点信息
	 * @param vip
	 * @param request
	 * @param response
	 * @param siteDto
	 * @return
	 * @author 陈义
	 * @date   2016-4-9上午10:51:59
	 */
	@RequestMapping(value="/updateSiteInfo")
	@ResponseBody
	public BSiteDto updateSiteInfo(@PathVariable("vip") String vip,HttpServletRequest request,HttpServletResponse response,BSiteDto siteDto){
		if(bSiteService.updateBSite(siteDto)>0){
			siteDto.setStatusFlag("1");
			UUserDto userDto=getUserDtoByNickname(vip); 
			RequestUtils.setUserInfo(request, response, userDto);
		}else{
			siteDto.setStatusFlag("-1");
		}
		return siteDto;
	}
	/***
	 * 更新或添加是否允许投稿设置
	 * @return
	 * @author 陈义
	 * @date   2016-4-9上午10:52:17
	 */
	@RequestMapping(value="/pullConfig")
	@ResponseBody
	public BConfigDto allowPull(@PathVariable("vip") String vip,HttpServletRequest request,HttpServletResponse response,Integer configId){
		BConfigDto configDto=new BConfigDto();
		configDto.setConfigKey(request.getParameter("configKey"));
		configDto.setValue(request.getParameter("pull_status"));
		UUserDto userDto=getUserDtoByNickname(vip);
		configDto.setBSiteDto(userDto.getBSiteDto());
		Date date=new Date();
		if(configId==null){
			configDto.setCreateTime(new Timestamp(date.getTime()));
		    configId=bConfigService.addBConfig(configDto);
		    if(configId>0){
		    configDto.setBconfigId(configId);
		    configDto.setStatusFlag("1");	    
		    }else{
		    	configDto.setStatusFlag("-1");
		    }
		}else{
			configDto.setBconfigId(configId);
			configDto.setLastTime(new Timestamp(date.getTime()));
			if(bConfigService.updateBConfig(configDto)>0){
				configDto.setStatusFlag("1");
			}else{
				configDto.setStatusFlag("-1");
			}
		}
		return configDto;
	}
	/***
	 * 更新或者添加新的分章分类
	 * @param vip
	 * @param request
	 * @param response
	 * @param catalogDto
	 * @return
	 * @author 陈义
	 * @date   2016-4-9下午2:25:32
	 */
	@RequestMapping(value="/setCatalog")
	@ResponseBody
	public DCatalogDto setCatalog(@PathVariable("vip") String vip,HttpServletRequest request,HttpServletResponse response,DCatalogDto catalogDto){
		String status=request.getParameter("status");
		if(status!=null&&status.equals("delete")){
			//更新日志表，把该日志表的里的日志移至个人日记中
			DDiaryDto diaryDto=new DDiaryDto();
			diaryDto.setUUserDto(catalogDto.getUUserDto());
			diaryDto.setDCatalogDto(catalogDto);
			DCatalogDto catalogDto2=new DCatalogDto();
			catalogDto2.setUUserDto(catalogDto.getUUserDto());
			catalogDto2.setCatalogName("个人日记");
			catalogDto2=dCatalogService.getDCatalog(catalogDto2);
			int moveNum= diaryService.diaryMove(diaryDto, catalogDto2);
			
			if(dCatalogService.deleteDCatalog(catalogDto)>0){
				catalogDto.setStatusFlag("1");
			}else{
				catalogDto.setStatusFlag("-1");
			}
			return catalogDto;
		}
		Date date=new Date();
		UUserDto userDto=getUserDtoByNickname(vip);
		catalogDto.setUUserDto(userDto);
		if(catalogDto.getCatalogId()==null){
			catalogDto.setCreateTime(new Timestamp(date.getTime()));
			catalogDto.setArticleCount(0);  //设置文章数为0
			int catalogId= dCatalogService.addDCatalog(catalogDto);
			if(catalogId>0){
				catalogDto.setCatalogId(catalogId);
				catalogDto.setStatusFlag("1");
			}else{
				catalogDto.setStatusFlag("-1");
			}
		}else{
			if(dCatalogService.updateDCatalog(catalogDto)>0){
				catalogDto.setStatusFlag("1");
			}else{
				catalogDto.setStatusFlag("-1");
			}
		}
		return catalogDto;
	}
	@RequestMapping(value="/checkCatalog")
	@ResponseBody
	public DCatalogDto checkCatalogName(@PathVariable("vip") String vip,HttpServletRequest request,HttpServletResponse response,DCatalogDto catalogDto){
		System.out.println("ip="+request.getParameter("uid"));
		catalogDto.setCatalogId(null);
		catalogDto=dCatalogService.getDCatalog(catalogDto);
		if(catalogDto!=null){
			catalogDto.setStatusFlag("1");
		}else{
			catalogDto=new DCatalogDto();
			catalogDto.setStatusFlag("-1");
		}
		return catalogDto;
	}
	

}
