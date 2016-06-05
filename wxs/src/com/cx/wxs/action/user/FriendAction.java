package com.cx.wxs.action.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cx.wxs.action.BaseAction;
import com.cx.wxs.dto.UFriendDto;
import com.cx.wxs.dto.UUserDto;
import com.cx.wxs.service.UFriendGroupService;
import com.cx.wxs.service.UFriendService;
import com.cx.wxs.service.UUserService;

/**
 * 用户好友的处理、删除、添加、列表
 * @author 陈义
 * @date   2016-5-14 下午3:06:29
 */
@Controller
@RequestMapping("/friend")
public class FriendAction extends BaseAction{

	@Resource
	private UUserService userService;
	@Resource
	private UFriendService friendService;
	@Resource
	private UFriendGroupService friendGroupService;
	
	@RequestMapping(value="/status")
	@ResponseBody
	public UFriendDto getFriendStatus(
			HttpServletRequest request,HttpServletResponse response,UFriendDto friendDto){
		
		return friendDto;
	}
	/**
	 * 添加好友
	 * @param request
	 * @param response
	 * @param friendDto
	 * @return
	 * @author 陈义
	 * @date   2016-5-26下午9:46:50
	 */
	@RequestMapping(value="/add")
	@ResponseBody
	public UFriendDto addFriend(
			HttpServletRequest request,HttpServletResponse response,UFriendDto friendDto){
		UUserDto userDto=(UUserDto) request.getSession().getAttribute("user");
		
		return friendDto;
	}
	/**
	 * 删除好友
	 * @param request
	 * @param response
	 * @param friendDto
	 * @return
	 * @author 陈义
	 * @date   2016-6-4下午3:13:35
	 */
	@ResourceMapping(value="/delete")
	@ResponseBody
	public UFriendDto deleteFriend(
			HttpServletRequest request,HttpServletResponse response,UFriendDto friendDto){
		
		return friendDto;
	}
	/**
	 * 好友分类
	 * @param vip
	 * @param request
	 * @param response
	 * @param friendDto
	 * @return
	 * @author 陈义
	 * @date   2016-6-4下午3:25:12
	 */
	@ResourceMapping(value="/classify")
	@ResponseBody
	public UFriendDto FriendClassify(
			HttpServletRequest request,HttpServletResponse response,UFriendDto friendDto){
		return friendDto;
	}
	/**
	 * 异步获取用户列表
	 * @param vip
	 * @param request
	 * @param response
	 * @param friendDto
	 * @return
	 * @author 陈义
	 * @date   2016-6-4下午3:21:01
	 */
	@ResourceMapping(value="/list1")
	@ResponseBody
	public ModelAndView getFriendList1(
			HttpServletRequest request,HttpServletResponse response,UFriendDto friendDto){
		ModelAndView mv=new ModelAndView();
		return mv;
	}
    /**
     * 好友列表
     * @param request
     * @param response
     * @param friendDto
     * @return
     * @author 陈义
     * @date   2016-6-4下午3:13:48
     */
	@ResourceMapping(value="/list")
	public List<UFriendDto> getFriendList(
			HttpServletRequest request,HttpServletResponse response,UFriendDto friendDto){
		List<UFriendDto> list =new ArrayList<UFriendDto>();
		return list;
	}
	
}
