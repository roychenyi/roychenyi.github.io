package com.cx.wxs.action.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cx.wxs.dto.UFriendDto;
import com.cx.wxs.dto.UUserDto;
import com.cx.wxs.service.UFriendService;
import com.cx.wxs.service.UUserService;

/**
 * 处理用户好友
 * @author 陈义
 * @date   2016-5-14 下午3:06:29
 */
@Controller
@RequestMapping("/friend")
public class FriendAction {

	@Resource
	private UUserService userService;
	@Resource
	private UFriendService friendService;
	/**
	 * 添加好友
	 * @param request
	 * @param response
	 * @param friendDto
	 * @return
	 * @author 陈义
	 * @date   2016-5-26下午9:46:50
	 */
	@RequestMapping(value="add")
	public UFriendDto addFriend(HttpServletRequest request,HttpServletResponse response,UFriendDto friendDto){
		UUserDto userDto=(UUserDto) request.getSession().getAttribute("user");
		
		return friendDto;
	}
}
