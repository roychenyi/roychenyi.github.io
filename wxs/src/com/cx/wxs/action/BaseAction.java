package com.cx.wxs.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.cx.wxs.dto.UUserDto;
import com.cx.wxs.service.UUserService;
import com.cx.wxs.utils.ClientInfo;

/**
 * @author 陈义
 * @param <T>
 * @date   2016-4-7 下午4:53:07
 */
@Controller 
public class BaseAction {
	@Resource
	private UUserService userService;

	/**
	 * @return the userService
	 */
	public UUserService getUserService() {
		return userService;
	}

	/**
	 * @author Administrator
	 * @param userService the userService to set
	 */
	public void setUserService(UUserService userService) {
		System.out.println("neirb");
		this.userService = userService;
	}
	/***
	 * 设置站点的作者信息
	 * @param mv
	 * @param name
	 * @return
	 * @author 陈义
	 * @date   2016-4-9上午10:51:30
	 */
	public boolean setAuthor(ModelAndView mv,String name){
		UUserDto userDto=new UUserDto();
		userDto.setNickname(name);
		userDto=userService.getUuser(userDto);
		if(userDto==null){
			return false;
		}else{
			
			mv.addObject("author",userDto);
			return true;
		}
	}
	//通过昵称获取用户信息
	public UUserDto getUserDtoByNickname(String name){
		UUserDto userDto=new UUserDto();
		userDto.setNickname(name);
		userDto=userService.getUuser(userDto);
		return userDto;
	}
	/**
	 * 通过用户名获取用户信息
	 * @param name
	 * @return
	 * @author 陈义
	 * @date   2016-5-19下午10:17:32
	 */
	public UUserDto getUserDtoByUsername(String name){
		UUserDto userDto=new UUserDto();
		userDto.setUsername(name);
		userDto=userService.getUuser(userDto);
		return userDto;
	}


}
