package com.cx.wxs.action.diary;


import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cx.wxs.action.BaseDiaryAction;
import com.cx.wxs.dto.DDiaryDto;
import com.cx.wxs.dto.DReply1Dto;
import com.cx.wxs.dto.DReply2Dto;
import com.cx.wxs.dto.UUserDto;
import com.cx.wxs.service.DDiaryService;
import com.cx.wxs.service.DReply1Service;
import com.cx.wxs.service.DReply2Service;
import com.cx.wxs.service.SysIllegalService;
import com.cx.wxs.service.UUserService;

/**
 * @author 陈义
 * @date   2016-4-27 上午10:39:11
 */
@Controller
@RequestMapping("/{vip}/article")
public class CommentAction extends BaseDiaryAction{
	@Resource
	private DDiaryService diaryService;
	@Resource
	private DReply1Service reply1Service;
	@Resource
	private DReply2Service reply2Service;
	@Resource
	private UUserService userService;
	@Resource
	private SysIllegalService sysIllegalService;
	/***
	 * 获取评论信息
	 * @param vip
	 * @param diaryId
	 * @param request
	 * @param response
	 * @param dReply1Dto
	 * @return
	 * @author 陈义
	 * @date   2016-4-27下午2:50:13
	 */
	@RequestMapping(value="/{diaryId}/reply_list")
	@ResponseBody
	public List<DReply1Dto> getDreplys(@PathVariable("vip") String vip,@PathVariable("diaryId") Integer diaryId,Integer page,
			HttpServletRequest request,HttpServletResponse response,DReply1Dto dReply1Dto){
		//获取日志评论
		DDiaryDto diaryDto=new DDiaryDto();
		diaryDto.setDiaryId(diaryId);
		dReply1Dto.setDDiaryDto(diaryDto);
		dReply1Dto.setPage(page);
		dReply1Dto.setRows(10);
		List<DReply1Dto> dReply1s=reply1Service.getDReply1List(dReply1Dto);
		return dReply1s;
	}
	
	public List<DReply1Dto> getDreplys(Integer row,DReply1Dto dReply1Dto){
		return null;
	}
	/***
	 * 添加新评论
	 * @param vip
	 * @param request
	 * @param response
	 * @param dReply1Dto  评论父表
	 * @param dReply2Dto  评论子表
	 * @return
	 * @author 陈义
	 * @date   2016-4-27上午10:51:27
	 */
	@RequestMapping(value="/{diaryId}/reply_add")
	@ResponseBody
	public DReply1Dto addDReply(@PathVariable("vip") String vip,@PathVariable("diaryId") Integer diaryId,
			HttpServletRequest request,HttpServletResponse response,DReply1Dto dReply1Dto,DReply2Dto dReply2Dto){
		DReply1Dto dReply1Dto1=new DReply1Dto();
		UUserDto userDto1=(UUserDto) request.getSession().getAttribute("user");
		try{
			dReply2Dto.setContent(URLDecoder.decode(dReply2Dto.getContent(),"UTF-8"));
			}catch (Exception e) {
				// TODO: handle exception
			}
		Date date=new Date();
		if(userDto1==null){
			dReply1Dto.setStatusFlag("-2");
		}else{
			
			//被评论的文章
			DDiaryDto diaryDto=new DDiaryDto();
			diaryDto.setDiaryId(diaryId);
			//diaryDto=diaryService.getDDiaryByID(diaryDto);
			//设置评论父表
			dReply1Dto.setUUserDto(userDto1);
			dReply1Dto.setDDiaryDto(diaryDto);
			dReply1Dto.setWriteTime(new Timestamp(date.getTime()));
			dReply1Dto.setStatus((short)1);
			int  reply1Id=reply1Service.addDReply1(dReply1Dto);
			if(reply1Id<1){
				return null;
			}else{
				dReply1Dto.setDreplyId(reply1Id);
			}
			//写入子表
			UUserDto userDto=this.getUserDtoByNickname(vip);
			dReply2Dto.setUUserByUserIdDto(userDto1);
			dReply2Dto.setUUserByCommentatorDto(userDto);
			dReply2Dto.setSortOrder((short)1);
			dReply2Dto.setReplyTime(new Timestamp(date.getTime()));
			dReply2Dto.setDReply1Dto(dReply1Dto);
			dReply2Dto.setContent(sysIllegalService.IllegalReplace(dReply2Dto.getContent()));
			int reply2Id=reply2Service.addDReply2(dReply2Dto);
			if(reply2Id>0){
				dReply2Dto.setDreply1Id(reply2Id);
			//	DReply2Dto reply2Dto=reply2Service.getDReply2ByID(dReply2Dto);
				//dReply1Dto=reply2Dto.getDReply1Dto();
//				dReply1Dto.setDreplyId(reply1Id);
//				dReply1Dto=reply1Service.getDReply1ByID(dReply1Dto);
//				if(dReply1Dto.getDReply2Dtos().size()==0||dReply1Dto.getDReply2Dtos()==null){
//				}
				dReply1Dto=reply1Service.getDReply1ByID(dReply1Dto);
				List<DReply2Dto> dReply2Dtos=new ArrayList<DReply2Dto>();
				dReply2Dtos.add(dReply2Dto);
				dReply1Dto.setDReply2Dtos(dReply2Dtos);
				//评论数更新
				if(diaryDto.getReplyCount()==null||diaryDto.getReplyCount()==0){
					diaryDto.setReplyCount(1);
				}else{
					if((diaryDto.getReplyCount()+1)<dReply1Dto.getRow()){
						diaryDto.setReplyCount(dReply1Dto.getRow());
					}else{
						diaryDto.setReplyCount(diaryDto.getReplyCount()+1);
					}
				}
				diaryService.updateDDiary(diaryDto);
				this.addDCommentCount(diaryDto);
				dReply1Dto.setStatusFlag("1");
			}else{
				//发布评论失败，写入子表数据错误，将父表状态变成删除状态
				dReply1Dto.setStatus((short)0);
				reply1Service.updateDReply1(dReply1Dto);
				dReply1Dto.setStatusFlag("-1");
				dReply1Dto=null;
			}
		}
		return dReply1Dto;
	}
	/**
	 * 添加回复（子评论）
	 * @param vip
	 * @param request
	 * @param response
	 * @param dReply2Dto
	 * @return
	 * @author 陈义
	 * @date   2016-4-27上午10:58:53
	 */
	@RequestMapping(value="/{diaryId}/reply2_add")
	@ResponseBody
	public DReply2Dto addDReply2(@PathVariable("vip") String vip,
			HttpServletRequest request,HttpServletResponse response,DReply2Dto dReply2Dto){
		UUserDto userDto1=(UUserDto) request.getSession().getAttribute("user");
	    if(userDto1==null){
	    	dReply2Dto.setStatusFlag("-2");
	    }else{
	    	try{
	    		dReply2Dto.setContent(URLDecoder.decode(dReply2Dto.getContent(),"UTF-8"));
	    	}catch (Exception e) {
				// TODO: handle exception
			}
	    	Date date=new Date();
	    	UUserDto userDto=this.getUserDtoByNickname(vip);
			dReply2Dto.setUUserByUserIdDto(userDto1);
			dReply2Dto.setUUserByCommentatorDto(userDto);
			dReply2Dto.setReplyTime(new Timestamp(date.getTime()));
			dReply2Dto.setContent(sysIllegalService.IllegalReplace(dReply2Dto.getContent()));
			int dReply2=reply2Service.addDReply2(dReply2Dto);
			if(dReply2>0){
				dReply2Dto.setDreply1Id(dReply2);
				dReply2Dto.setStatusFlag("1");
			}else{
				dReply2Dto.setStatusFlag("-1");
			}
	    }
		return dReply2Dto;
	}
	/***
	 * 删除评论
	 * @param vip
	 * @param request
	 * @param response
	 * @param dReply1Dto
	 * @param dReply2Dto
	 * @return
	 * @author 陈义
	 * @date   2016-4-27上午10:53:12
	 */
	@RequestMapping(value="/{diaryId}/reply_delete")
	@ResponseBody
	public List<DReply1Dto> deleteDReply(@PathVariable("vip") String vip,@PathVariable("diaryId") Integer diaryId,Integer page,
			HttpServletRequest request,HttpServletResponse response,DReply1Dto dReply1Dto,DReply2Dto dReply2Dto){
		List<DReply1Dto> reply1Dtos=new ArrayList<DReply1Dto>();
		UUserDto userDto=(UUserDto)request.getSession().getAttribute("user");
		//检查用户是否登陆，同时检查用户是否和作者是同一个人
		if(userDto==null){
			dReply1Dto.setStatusFlag("-2");
			reply1Dtos.add(dReply1Dto);
			return reply1Dtos;
		}else if(!userDto.getNickname().equals(vip)){
			dReply1Dto.setStatusFlag("-3");
			reply1Dtos.add(dReply1Dto);
			return reply1Dtos;
		}
		//更新评论的状态值，0表示删除
		DReply1Dto reply1Dto=new DReply1Dto();
		reply1Dto.setDreplyId(dReply1Dto.getDreplyId());
		reply1Dto.setStatus((short)0);
		Integer result= reply1Service.updateDReply1(reply1Dto);
		if(result>0){
			//评论-1
			DDiaryDto diaryDto=new DDiaryDto();
			diaryDto.setDiaryId(diaryId);
			diaryDto=diaryService.getDDiaryByID(diaryDto);
			diaryDto.setReplyCount(diaryDto.getReplyCount()-1);
			diaryService.updateDDiary(diaryDto);
			//查找新的评论列表
			dReply1Dto.setPage(page);
			dReply1Dto.setRows(10);
			dReply1Dto.setDDiaryDto(diaryDto);
			reply1Dtos=reply1Service.getDReply1List(dReply1Dto);
			if(reply1Dtos!=null&&reply1Dtos.size()>0){
			reply1Dtos.get(0).setStatusFlag("1");
			}else{
				//当删除评论后评论为null的情况
				DReply1Dto dto=new DReply1Dto();
				dto.setStatusFlag("1");
				dto.setRow(0);
				reply1Dtos=new ArrayList<DReply1Dto>();
				reply1Dtos.add(dto);
			}
		}else{
			dReply1Dto.setStatusFlag("-1");
			reply1Dtos.add(dReply1Dto);
		}
		return reply1Dtos;
	}
	
	@RequestMapping(value="/{diaryId}/reply2_delete")
	@ResponseBody
	public DReply2Dto  deleteDReply2(@PathVariable("vip") String vip,
			HttpServletRequest request,HttpServletResponse response,DReply1Dto dReply1Dto,DReply2Dto dReply2Dto){
		
		return dReply2Dto;
	}
}
