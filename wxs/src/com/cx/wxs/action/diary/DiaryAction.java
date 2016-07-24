package com.cx.wxs.action.diary;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cx.wxs.action.BaseAction;
import com.cx.wxs.action.BaseDiaryAction;
import com.cx.wxs.dto.BConfigDto;
import com.cx.wxs.dto.DAccessDto;
import com.cx.wxs.dto.DCatalogDto;
import com.cx.wxs.dto.DDiaryDto;
import com.cx.wxs.dto.DFavoriteDto;
import com.cx.wxs.dto.DReply1Dto;
import com.cx.wxs.dto.DUpvoteDto;
import com.cx.wxs.dto.SysTypeDto;
import com.cx.wxs.dto.UUserDto;
import com.cx.wxs.enums.DiaryRole;
import com.cx.wxs.po.DAccess;
import com.cx.wxs.service.BConfigService;
import com.cx.wxs.service.BSiteService;
import com.cx.wxs.service.DAccessService;
import com.cx.wxs.service.DCatalogService;
import com.cx.wxs.service.DDiaryService;
import com.cx.wxs.service.DFavoriteService;
import com.cx.wxs.service.DReply1Service;
import com.cx.wxs.service.DUpvoteService;
import com.cx.wxs.service.SysIllegalService;
import com.cx.wxs.service.SysTypeService;
import com.cx.wxs.service.UUserService;
import com.cx.wxs.utils.RequestUtils;
import com.cx.wxs.utils.ClientInfo;

/**
 * @author 陈义
 * @date   2016-4-7 下午4:03:13
 */
@Controller("DdiaryAction")
@RequestMapping("/{vip}/article")
public class DiaryAction extends BaseDiaryAction{

	@Resource
	private DDiaryService diaryService;
	@Resource
	private DReply1Service reply1Service;
	@Resource
	private SysIllegalService sysIllegalService;
	/***
	 * 跳转到更新日志的界面
	 * @param vip
	 * @param request
	 * @param reqResponse
	 * @return
	 * @author 陈义
	 * @date   2016-4-7下午4:20:48
	 */
	@RequestMapping(value="/article_add")
	public ModelAndView toArticleAdd(@PathVariable("vip") String vip,
			HttpServletRequest request,HttpServletResponse reqResponse ){
		ModelAndView mv=new ModelAndView("diary/d_edite");
		UUserDto userDto=(UUserDto)request.getSession().getAttribute("user");
		if(userDto.getNickname().equals(vip)){
			mv.addObject("author",userDto);
			getDiarySetting(userDto, mv);
		}else{
			mv.setViewName("404");
		}
		return mv;
	}
	//保存日志
	@RequestMapping(value="/article_save")
	@ResponseBody
	public DDiaryDto articleAdd(@PathVariable("vip") String vip, HttpServletRequest request,HttpServletResponse reqResponse,Integer diaryId1 ,DDiaryDto diaryDto){

		UUserDto userDto=(UUserDto) request.getSession().getAttribute("user");
        //设置是否投稿
		if(diaryDto.getContribute()==null){
			diaryDto.setContribute((short)0);
		}
		try{
		diaryDto.setContent(URLDecoder.decode(diaryDto.getContent(),"UTF-8"));
		}catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println(diaryDto.getContent());
		String coverFlag=request.getParameter("coverFlag");
		System.out.println("测试："+coverFlag);
		Date date=new Date();
		if(userDto==null){
			diaryDto.setStatusFlag("-1");
		}else if(diaryDto.getDiaryId()!=null){
			if(diaryId1!=null){
			if(coverFlag!=null&&coverFlag.equals("1")){				
				diaryDto.setRole((short)-2);
				diaryService.updateDDiary(diaryDto);
				diaryDto.setDiaryId(diaryId1);
			}else{
			/*	DDiaryDto diaryDto3=new DDiaryDto();
				diaryDto3.setDiaryId(diaryId1);
				diaryDto3.setRole((short)-2);
				diaryService.updateDDiary(diaryDto3);*/
			}
			}
			diaryDto.setModifyTime(new Timestamp(date.getTime()));
			DDiaryDto diaryDto2=diaryService.getDDiaryByID(diaryDto);
			if(diaryDto2.getExt1()==null){
				diaryDto.setExt1(0);
			}else{
				diaryDto.setExt1(diaryDto2.getExt1()+1);
			}
			diaryDto.setExt2(0);
			diaryDto.setRole((short)1);
			diaryDto.setContent(sysIllegalService.IllegalReplace(diaryDto.getContent()));
			if(diaryService.updateDDiary(diaryDto)>0){
				diaryDto=diaryService.getDDiaryByID(diaryDto);
				diaryDto.setStatusFlag("1");
			}else{
				diaryDto.setStatusFlag("-1");
			}
		}else{
		//	short contribute =new Short(request.getParameter("contribute"));
			
		//	diaryDto.setContribute(contribute);
			diaryDto.setBSiteDto(userDto.getBSiteDto());
			diaryDto.setUUserDto(userDto);
			diaryDto.setWriteTime(new Timestamp(date.getTime()));
			diaryDto.setContent(sysIllegalService.IllegalReplace(diaryDto.getContent()));
			int diaryId= diaryService.addDDiary(diaryDto);
			if(diaryId>0){
				diaryDto.setDiaryId(diaryId);
				diaryDto.setStatusFlag("1");
			}else{
				diaryDto.setStatusFlag("-1");
			}
		}
		return diaryDto;
	}
	@RequestMapping(value="/article_edite/{diaryId}")
	public ModelAndView toArticleEdite(@PathVariable("vip") String vip,@PathVariable("diaryId") Integer diaryId,
			HttpServletRequest request,HttpServletResponse reqResponse,DDiaryDto diaryDto ){
		ModelAndView mv=new ModelAndView("diary/d_edite");
		UUserDto userDto=(UUserDto) request.getSession().getAttribute("user");
		UUserDto userDto1=this.getUserDtoByNickname(vip);
		try{
			diaryDto.setContent(URLDecoder.decode(diaryDto.getContent(),"UTF-8"));
			}catch (Exception e) {
				// TODO: handle exception
			}
		if(userDto.getNickname().equals(vip)){
			diaryDto.setDiaryId(diaryId);
			diaryDto.setUUserDto(userDto);
			diaryDto=diaryService.getDDiaryByID(diaryDto);
			if(diaryDto==null){
				mv.setViewName("diary/d_no_details");
			}else{
			getDiarySetting(userDto1, mv);
			}			
			mv.addObject("author", userDto1);
			mv.addObject("diary",diaryDto);
		}else{
			mv.setViewName("404");
		}

		return mv;
	}
	//进入草稿箱
	@RequestMapping(value="/article_draft_save")
	@ResponseBody
	public DDiaryDto ArticleSaveAsDraft(@PathVariable("vip") String vip,Integer diaryId1,
			HttpServletRequest request,HttpServletResponse reqResponse,DDiaryDto diaryDto ){
		ModelAndView mv=new ModelAndView();
		UUserDto userDto=(UUserDto) request.getSession().getAttribute("user");
		try{
		diaryDto.setContent(URLDecoder.decode(diaryDto.getContent(),"UTF-8"));
		}catch (Exception e) {
			// TODO: handle exception
		}
		Date date=new Date();
		if(userDto==null){
			diaryDto.setStatusFlag("-1");
		}else if(diaryId1==null){
			short contribute =new Short(request.getParameter("contribute"));
			diaryDto.setContribute(contribute);
			diaryDto.setBSiteDto(userDto.getBSiteDto());
			diaryDto.setUUserDto(userDto);
			diaryDto.setRole((short)2);
			diaryDto.setWriteTime(new Timestamp(date.getTime()));
			if(diaryDto.getDiaryId()!=null){
				diaryDto.setExt2(diaryDto.getDiaryId());
			}
			int diaryId= diaryService.addDDiary(diaryDto);
			if(diaryId>0){
				diaryDto.setDiaryId(diaryId);
				diaryDto.setStatusFlag("1");
			}else{
				diaryDto.setStatusFlag("-1");
			}
		}else{
			//如果原日志的id存在，则写入扩展EXT2
			if(diaryDto.getDiaryId()!=null){
				diaryDto.setExt2(diaryDto.getDiaryId());
			}
			diaryDto.setDiaryId(diaryId1);
			diaryDto.setModifyTime(new Timestamp(date.getTime()));
			DDiaryDto diaryDto2=diaryService.getDDiaryByID(diaryDto);
			if(diaryDto2.getExt1()==null){
				diaryDto.setExt1(0);
			}else{
				diaryDto.setExt1(diaryDto2.getExt1()+1);
			}
			if(diaryService.updateDDiary(diaryDto)>0){
				diaryDto=diaryService.getDDiaryByID(diaryDto);
				diaryDto.setStatusFlag("1");
			}else{
				diaryDto.setStatusFlag("-1");

			}
		}
		return  diaryDto;
	}
	@RequestMapping(value="")
	public ModelAndView articlePage(@PathVariable("vip") String vip,Integer page,Integer role,
			HttpServletRequest request,HttpServletResponse response,DDiaryDto diaryDto){
		ModelAndView mv=new ModelAndView("diary/d_list");
		UUserDto userDto=getUserDtoByNickname(vip);
		if(userDto==null){
			mv.setViewName("404");
		}else{			
		//	DDiaryDto diaryDto=new DDiaryDto();
			//获取文章访问权限
			role=this.getDiaryRole(role, userDto, request);
			diaryDto.setRole(Short.parseShort(role.toString()));
			//获取日志列表
			diaryDto.setUUserDto(userDto);
			if(page!=null){
				int count=diaryService.getDiaryCount(diaryDto);
				//考虑用户删除了日志，日志总数少于客户端显示数量
				if(page>count){   
			        diaryDto.setPage(count);
				}else{
					diaryDto.setPage(page);	
				}
			}else{
				diaryDto.setPage(1);
			}
			diaryDto.setRows(10);
			/*if(diaryDto.getRole()!=null){
			diaryDto.setRole((short)1);
			}*/
			List<DDiaryDto> diaryDtos=diaryService.getDDiaryList(diaryDto);
			//分页信息
			diaryDto=diaryService.getPageInfo(diaryDto);
		//	this.setCookie_page(diaryDto, request, response);
			//获取文章分类
			DCatalogDto catalogDto=new DCatalogDto();
			catalogDto.setUUserDto(userDto);
		//	this.getDcatolog(catalogDto, mv);
		    if(role!=null){
		    	mv.addObject("role",role);
		    }
			mv.addObject("author", userDto);
			mv.addObject("diarys",diaryDtos);

		}
		return mv;
	}
	 
	@RequestMapping(value="/article_list") 
	@ResponseBody
	public List<DDiaryDto> articleList(@PathVariable("vip") String vip,Integer page,Integer role,
			HttpServletRequest request,HttpServletResponse response,DDiaryDto diaryDto){
		List<DDiaryDto> dDiaryDtos=new ArrayList<DDiaryDto>();
		UUserDto author=this.getUserDtoByNickname(vip);
		diaryDto.setUUserDto(author);
		diaryDto.setRows(10);
		int count=diaryService.getDiaryCount(diaryDto);
		//考虑用户删除了日志，日志总数少于客户端显示数量
		if(page==null||page<1){
			page=1;
		}else if(page>count){   
	        diaryDto.setPage(count);
		}else{
			diaryDto.setPage(page);	
		}
		//获取文章访问权限
		role=this.getDiaryRole(role, author, request);
		diaryDto.setRole(Short.parseShort(role.toString()));
		dDiaryDtos=diaryService.getDDiaryList(diaryDto);
		if(dDiaryDtos==null){
			return new ArrayList<DDiaryDto>();
		}
		return dDiaryDtos;
	}
	/***
	 * 文章浏览
	 * @param vip
	 * @param diaryId
	 * @param request
	 * @param reqResponse
	 * @param diaryDto
	 * @return
	 * @author 陈义
	 * @date   2016-5-11上午10:45:54
	 */
	@RequestMapping(value="/details/{diaryId}")
	public ModelAndView articleDetails(@PathVariable("vip") String vip,@PathVariable("diaryId") Integer diaryId,
			HttpServletRequest request,HttpServletResponse reqResponse,DDiaryDto diaryDto){
		ModelAndView mv=new ModelAndView("diary/d_details");
	
		UUserDto userDto=(UUserDto) request.getSession().getAttribute("user");
		UUserDto author=getUserDtoByNickname(vip);
		diaryDto.setDiaryId(diaryId);
		diaryDto.setUUserDto(author);
		List<DDiaryDto> list=diaryService.getCurrentDiarys(diaryDto);
		if(list==null){
			mv.setViewName("404");
			return mv;
		}
		mv.addObject("prev_diary",list.get(2));
		mv.addObject("next_diary",list.get(1));

		diaryDto=list.get(0);
	//	System.out.println("content-length:"+diaryDto.getContent().length());
		if(diaryDto==null){
			mv.setViewName("diary/d_no_details");
		}else{
			
			//如果访问日志存在，访问一次，访问量+1			
			if(diaryDto.getViewCount()==null){
				diaryDto.setViewCount(1);
			}else{
				diaryDto.setViewCount(diaryDto.getViewCount()+1);
			}
			Date date=new Date();
			diaryDto.setLastReadTime(new Timestamp(date.getTime()));			
			diaryService.updateDDiary(diaryDto);			
			if(userDto!=null){
	//		if(!userDto.getUserId().equals(author.getUserId())){
	            this.addDiaryAccess(request, author, diaryDto);
				//该用户是否点赞
				DUpvoteDto upvoteDto=this.getDUpvoteDto(diaryDto, userDto);
				if(upvoteDto!=null){
					mv.addObject("upvote", upvoteDto);
				}
				//是否收藏
				DFavoriteDto favoriteDto=this.getDFavoriteDto(diaryDto, userDto);
				if(favoriteDto!=null){
					mv.addObject("favorite", favoriteDto);
				}	
			}
			if(diaryDto.getRole()!=1){
				if(userDto==null){
					mv.setViewName("diary/d_no_details");
				}else if(!userDto.getNickname().equals(vip)){
					mv.setViewName("diary/d_no_details");
				}
			}
			mv.addObject("diary",diaryDto);
			//获取日志评论
			DReply1Dto  dReply1Dto=new DReply1Dto();
			dReply1Dto.setDDiaryDto(diaryDto);
			List<DReply1Dto> dReply1s=reply1Service.getDReply1List(dReply1Dto);
			mv.addObject("dReply1s", dReply1s);

		}
		mv.addObject("author", author);		
		return mv;
	}
	/***
	 * 预览文章
	 * @param vip
	 * @param diaryId
	 * @param request
	 * @param reqResponse
	 * @param diaryDto
	 * @return
	 * @author 陈义
	 * @date   2016-5-14下午2:33:41
	 */
	@RequestMapping(value="/preview/{diaryId}")
	public DDiaryDto articlePreview(@PathVariable("vip") String vip,@PathVariable("diaryId") Integer diaryId,
			HttpServletRequest request,HttpServletResponse reqResponse,DDiaryDto diaryDto){
		return null;
	}
	/***
	 * 通过用户自定义分类获取文章列表
	 * @param vip
	 * @param catalogId
	 * @param request
	 * @param reqResponse
	 * @param diaryDto
	 * @return
	 * @author 陈义
	 * @date   2016-5-13上午10:16:52
	 */
	@RequestMapping("/catalog/{catalogid}")
	@ResponseBody
	public List<DDiaryDto> getDiarysByCatalog(@PathVariable("vip") String vip,@PathVariable("catalogid") Integer catalogId,
			HttpServletRequest request,HttpServletResponse reqResponse,DDiaryDto diaryDto){
		UUserDto author =this.getUserDtoByNickname(vip);
	    DCatalogDto catalogDto=new DCatalogDto(catalogId);
	    diaryDto.setUUserDto(author);
	    diaryDto.setDCatalogDto(catalogDto);
	    return diaryService.getDDiaryList(diaryDto);
	}
	/**
	 * 用户收藏 
	 * @param vip
	 * @param page
	 * @param role
	 * @param request
	 * @param response
	 * @param diaryDto
	 * @return
	 * @author 陈义
	 * @date   2016-5-12下午11:30:03
	 */
	@RequestMapping(value="/favorite") 
	public ModelAndView toFavoritePage(@PathVariable("vip") String vip,Integer page,Integer role,
			HttpServletRequest request,HttpServletResponse response,DDiaryDto diaryDto){
	    ModelAndView mv=new ModelAndView("diary/d_list");
	   /* UUserDto userDto=(UUserDto) request.getSession().getAttribute("user");
	    if(userDto==null||!userDto.equals("vip")){
	    	mv.setViewName("404");
	    	return mv;
	    }*/
	    UUserDto userDto =this.getUserDtoByNickname(vip);
	    mv.addObject("author", userDto);
		List<DDiaryDto> list=this.getDiarysByFavorite(userDto, page);
		mv.addObject("diarys", list);
		mv.addObject("role",3);
		return mv;
	}
	/***
	 * 获取文章收藏列表
	 * @param vip
	 * @param page
	 * @param role
	 * @param request
	 * @param response
	 * @param diaryDto
	 * @return
	 * @author 陈义
	 * @date   2016-5-14下午2:34:03
	 */
	@RequestMapping(value="/favorite/list")
	@ResponseBody
	public List<DDiaryDto> getFavoriteDiarys(@PathVariable("vip") String vip,Integer page,Integer role,
			HttpServletRequest request,HttpServletResponse response,DDiaryDto diaryDto){
		UUserDto userDto =this.getUserDtoByNickname(vip);
		return this.getDiarysByFavorite(userDto, page);
	}
 
	@RequestMapping(value="/catalog/diarys")
	@ResponseBody
	public List<DDiaryDto> getDiarysByCatalog(@PathVariable("vip") String vip,Integer page,Integer role,
			HttpServletRequest request,HttpServletResponse response,DDiaryDto diaryDto){
		return null;
	}
}
