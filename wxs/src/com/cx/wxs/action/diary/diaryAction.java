package com.cx.wxs.action.diary;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
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
import com.cx.wxs.dto.DUpvoteDto;
import com.cx.wxs.dto.SysTypeDto;
import com.cx.wxs.dto.UUserDto;
import com.cx.wxs.po.DAccess;
import com.cx.wxs.service.BConfigService;
import com.cx.wxs.service.BSiteService;
import com.cx.wxs.service.DAccessService;
import com.cx.wxs.service.DCatalogService;
import com.cx.wxs.service.DDiaryService;
import com.cx.wxs.service.DFavoriteService;
import com.cx.wxs.service.DUpvoteService;
import com.cx.wxs.service.SysTypeService;
import com.cx.wxs.service.UUserService;
import com.cx.wxs.utils.clientInfo;

/**
 * @author 陈义
 * @date   2016-4-7 下午4:03:13
 */
@Controller
@RequestMapping("/{vip}/article")
public class diaryAction extends BaseDiaryAction{
	@Resource
	private UUserService userService;
	@Resource
	private DDiaryService diaryService;
	@Resource
	private BSiteService siteService;
	@Resource
	private BConfigService configService;
	@Resource
	private DCatalogService catalogService;
	@Resource
	private SysTypeService sysTypeService;
	@Resource
	private DAccessService accessService;
	@Resource
	private DUpvoteService upvoteService;
	@Resource
	private DFavoriteService favoriteService;
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
			if(diaryService.updateDDiary(diaryDto)>0){
				diaryDto=diaryService.getDDiaryByID(diaryDto);
				diaryDto.setStatusFlag("1");
			}else{
				diaryDto.setStatusFlag("-1");
			}
		}else{
			short contribute =new Short(request.getParameter("contribute"));
			diaryDto.setContribute(contribute);
			diaryDto.setBSiteDto(userDto.getBSiteDto());
			diaryDto.setUUserDto(userDto);
			diaryDto.setWriteTime(new Timestamp(date.getTime()));
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
		if(userDto.getNickname().equals(vip)){
			diaryDto.setDiaryId(diaryId);
			diaryDto.setUUserDto(userDto);
			diaryDto=diaryService.getDDiaryByID(diaryDto);
			if(diaryDto==null){
				mv.setViewName("diary/d_no_details");
			}else{
			getDiarySetting(userDto, mv);
			}
			mv.addObject("author", userDto);
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
		Date date=new Date();
		if(userDto==null){
			diaryDto.setStatusFlag("-1");
		}else if(diaryId1==null){
			short contribute =new Short(request.getParameter("contribute"));
			diaryDto.setContribute(contribute);
			diaryDto.setBSiteDto(userDto.getBSiteDto());
			diaryDto.setUUserDto(userDto);

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
	@RequestMapping(value="/article_list")
	public ModelAndView articleList(@PathVariable("vip") String vip,
			HttpServletRequest request,HttpServletResponse reqResponse){
		ModelAndView mv=new ModelAndView("diary/d_list");
		UUserDto userDto=getUserDtoByNickname(vip);
		if(userDto==null){
			mv.setViewName("404");
		}else{
			//获取用户信息
			DDiaryDto diaryDto=new DDiaryDto();
			diaryDto.setUUserDto(userDto);
			diaryDto.setPage(1);
			diaryDto.setRows(10);
			diaryDto.setRole((short)1);
			List<DDiaryDto> diaryDtos=diaryService.getDDiaryList(diaryDto);
			//获取用户的列表
			DCatalogDto catalogDto=new DCatalogDto();
			catalogDto.setUUserDto(userDto);
			this.getDcatolog(catalogDto, mv);
			mv.addObject("author", userDto);
			mv.addObject("diarys",diaryDtos);
		}
		return mv;
	}
	@RequestMapping(value="/details/{diaryId}")
	public ModelAndView articlePreview(@PathVariable("vip") String vip,@PathVariable("diaryId") Integer diaryId,
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
		if(diaryDto.getDiaryId()==null){
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
				if(!userDto.getUserId().equals(author.getUserId())){
					//设置访问记录过期
					
					//用户已经登录，且登录用户与文章作者不是同一人，则写入访问列表
					DAccessDto accessDto=new DAccessDto();
					accessDto.setUUserDto(userDto);
					accessDto.setDDiaryDto(diaryDto);
					String ip=clientInfo.getIpAddr(request);
					String clientAgent=clientInfo.getAgent(request);
					boolean isMoblie=clientInfo.isMoblie(request);
					accessDto.setClientIp(ip);
					accessDto.setClientAgent(clientAgent);
					accessDto.setClientType((short)(isMoblie?1:0)); 
					accessDto.setTime(new Timestamp(date.getTime()));
					accessDto.setStatus((short)1);  //1：未过期，0：过期
					accessService.addDAccess(accessDto);
					author.setIsUsers(false);	
				}else{
					author.setIsUsers(true);
				}}else{
					author.setIsUsers(false);		
				}		
			mv.addObject("diary",diaryDto);
		}
		mv.addObject("author", author);		
		return mv;
	}
	/***
	 * 删除日志（移动到回收站）
	 * @param vip
	 * @param diaryId
	 * @param request
	 * @param reqResponse
	 * @param diaryDto
	 * @return
	 * @author 陈义
	 * @date   2016-4-16下午4:17:28
	 */
	@RequestMapping(value="/article_delete/{diaryId}")
	@ResponseBody
	public DDiaryDto  articleDelete(@PathVariable("vip") String vip,@PathVariable("diaryId") Integer diaryId,
			HttpServletRequest request,HttpServletResponse reqResponse,DDiaryDto diaryDto ){
		diaryDto.setDiaryId(diaryId);
		Date date=new Date();
		diaryDto=diaryService.getDDiaryByID(diaryDto);
		diaryDto.setRole((short)-1);
		diaryDto.setModifyTime(new Timestamp(date.getTime()));
		if(diaryService.updateDDiary(diaryDto)>0){
			diaryDto.setStatusFlag("1");
		}else{
			diaryDto.setStatusFlag("-1");
		}
		return diaryDto;
	}
	/**
	 * 文章点赞
	 * @param vip
	 * @param diaryId
	 * @param request
	 * @param reqResponse
	 * @param diaryDto
	 * @return
	 * @author 陈义
	 * @date   2016-4-16下午4:18:21
	 */
	@RequestMapping(value="/article_upvote/{diaryId}")
	public DUpvoteDto articleUpvote(@PathVariable("vip") String vip,@PathVariable("diaryId") Integer diaryId,
			HttpServletRequest request,HttpServletResponse reqResponse,DDiaryDto diaryDto){
		DUpvoteDto upvoteDto=new DUpvoteDto();
		UUserDto userDto1=(UUserDto) request.getSession().getAttribute("user");
		if(userDto1==null){
			upvoteDto.setStatusFlag("-2");   //未登录
		}else{
			//作者信息
			UUserDto userDto2=getUserDtoByNickname(vip);
			Date date=new Date();
			upvoteDto.setUUserDto(userDto1);
			diaryDto.setDiaryId(diaryId);		
			diaryDto=diaryService.getDDiaryByID(diaryDto);
			upvoteDto.setDDiaryDto(diaryDto);			
			DUpvoteDto upvoteDto2=upvoteService.getDUpvote(upvoteDto);
			if(upvoteDto2!=null){
				//取消赞
				diaryDto.setUpvoteCount(diaryDto.getUpvoteCount()-1);  //点赞数量减一
				diaryService.updateDDiary(diaryDto);
				upvoteDto2.setStatus((short)0);
				upvoteDto2.setUpdateTime(new Timestamp(date.getTime()));
				if(upvoteService.updateDUpvote(upvoteDto2)>0){
				upvoteDto=upvoteDto2;
				upvoteDto.setStatusFlag("0");
				}else{
					upvoteDto.setStatusFlag("-1");
				}
			}else{
				//点赞
				if(diaryDto.getUpvoteCount()==null){
					diaryDto.setUpvoteCount(1);
				}else{
					diaryDto.setUpvoteCount(diaryDto.getUpvoteCount()+1);
				}
				diaryService.updateDDiary(diaryDto);
				upvoteDto.setTime(new Timestamp(date.getTime())); //添加时间
				int upvoteId=upvoteService.addDUpvote(upvoteDto);
				if(upvoteId>0){
					diaryDto.setStatusFlag("1");
				}else{
					diaryDto.setStatusFlag("-1");
				}
			}
		}
		return upvoteDto;
	}
	/**
	 * 文章收藏
	 * @param vip
	 * @param diaryId
	 * @param request
	 * @param reqResponse
	 * @param diaryDto
	 * @return
	 * @author 陈义
	 * @date   2016-4-16下午4:18:42
	 */
	@RequestMapping(value="/article_favorite/{diaryId}")
	@ResponseBody
	public DFavoriteDto articleFavorite(@PathVariable("vip") String vip,@PathVariable("diaryId") Integer diaryId,
			HttpServletRequest request,HttpServletResponse reqResponse,DDiaryDto diaryDto){
		DFavoriteDto favoriteDto=new DFavoriteDto();
		UUserDto userDto1=(UUserDto) request.getSession().getAttribute("user");
		if(userDto1==null){
			favoriteDto.setStatusFlag("-2");			
		}else{
			Date date=new Date();
			diaryDto.setDiaryId(diaryId);
			diaryDto=diaryService.getDDiaryByID(diaryDto);
			favoriteDto.setDDiaryDto(diaryDto);
			favoriteDto.setUUserDto(userDto1);
			DFavoriteDto favoriteDto2=favoriteService.getDFADFavorite(favoriteDto);
			if(favoriteDto2!=null){
				//取消收藏
				diaryDto.setFavoriteCount(diaryDto.getFavoriteCount()-1);
				diaryService.updateDDiary(diaryDto);
				favoriteDto2.setStatus((short)0);
				favoriteDto2.setUpdateTime(new Timestamp(date.getTime()));
				if(favoriteService.updateDFavorite(favoriteDto2)>0){
					favoriteDto=favoriteDto2;
					favoriteDto.setStatusFlag("0");
				}else{
					favoriteDto.setStatusFlag("-1");
				}
			}else{
				//确认收藏
				if(diaryDto.getFavoriteCount()==null){
					diaryDto.setFavoriteCount(1);
				}else{
					diaryDto.setFavoriteCount(diaryDto.getFavoriteCount()+1);
				}
				diaryService.updateDDiary(diaryDto);
				favoriteDto.setTime(new Timestamp(date.getTime()));
				int favoriteId= favoriteService.addDFavorite(favoriteDto);
				if(favoriteId>0){
					favoriteDto.setStatusFlag("1");
					favoriteDto.setDfavoriteId(favoriteId);
				}else{
					favoriteDto.setStatusFlag("-1");
				}
			}
		}
		return favoriteDto;
	}

}
