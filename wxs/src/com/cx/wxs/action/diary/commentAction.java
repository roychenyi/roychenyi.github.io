package com.cx.wxs.action.diary;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cx.wxs.action.BaseDiaryAction;
import com.cx.wxs.dto.DReply1Dto;
import com.cx.wxs.dto.DReply2Dto;
import com.cx.wxs.dto.UUserDto;
import com.cx.wxs.service.DDiaryService;
import com.cx.wxs.service.DReply1Service;
import com.cx.wxs.service.DReply2Service;
import com.cx.wxs.service.UUserService;

/**
 * @author 陈义
 * @date   2016-4-27 上午10:39:11
 */
@Controller
@RequestMapping("/{vip}/article")
public class commentAction extends BaseDiaryAction{
	@Resource
	private DDiaryService diaryService;
	@Resource
	private DReply1Service reply1Service;
	@Resource
	private DReply2Service reply2Service;
	@Resource
	private UUserService userService;
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
		if(userDto1==null){
			dReply1Dto.setStatusFlag("-2");
		}else{
			
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
	public DReply1Dto deleteDReply(@PathVariable("vip") String vip,
			HttpServletRequest request,HttpServletResponse response,DReply1Dto dReply1Dto,DReply2Dto dReply2Dto){
		return dReply1Dto;
	}
}
