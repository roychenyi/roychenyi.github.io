package com.cx.wxs.action.user;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.cx.wxs.action.BaseUserAction;
import com.cx.wxs.dto.UFriendGroupDto;
import com.cx.wxs.service.UFriendGroupService;
import com.cx.wxs.service.UFriendService;

/**
 * @author 陈义
 * @date   2016-6-4 下午3:28:40
 */
@Controller
@RequestMapping("/{vip}/friend/group")
public class FriendGroupAction extends BaseUserAction{

	@Resource
	private UFriendService friendService;
	@Resource
	private UFriendGroupService friendGroupService;
	/**
	 * 增加好友分类
	 * @param vip
	 * @param request
	 * @param response
	 * @param friendGroupDto
	 * @return
	 * @author 陈义
	 * @date   2016-6-4下午3:34:56
	 */
	@ResourceMapping(value="add")
	@ResponseBody
	public UFriendGroupDto addFriendGroup(@PathVariable("vip") String vip,
			HttpServletRequest request,HttpServletResponse response,UFriendGroupDto friendGroupDto){
		return friendGroupDto;
	}
	/**
	 * 删除好友分类
	 * @param vip
	 * @param request
	 * @param response
	 * @param friendGroupDto
	 * @return
	 * @author 陈义
	 * @date   2016-6-4下午3:35:22
	 */
	@ResourceMapping(value="delete")
	@ResponseBody
	public UFriendGroupDto deleteFriendGroup(@PathVariable("vip") String vip,
			HttpServletRequest request,HttpServletResponse response,UFriendGroupDto friendGroupDto){
		return friendGroupDto;
	}
	/**
	 * 获取好友分类列表
	 * @param vip
	 * @param request
	 * @param response
	 * @param friendGroupDto
	 * @return
	 * @author 陈义
	 * @date   2016-6-4下午3:35:31
	 */
	@ResourceMapping(value="list")
	@ResponseBody
	public List<UFriendGroupDto> getFriendGroupList(@PathVariable("vip") String vip,
			HttpServletRequest request,HttpServletResponse response,UFriendGroupDto friendGroupDto){
		List<UFriendGroupDto> list=new ArrayList<UFriendGroupDto>();
		return list;
	}
	
	
}
