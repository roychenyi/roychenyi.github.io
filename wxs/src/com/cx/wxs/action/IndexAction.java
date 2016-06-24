package com.cx.wxs.action;

import java.awt.Color;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.cx.wxs.bean.EmailInfo;
import com.cx.wxs.dto.DDiaryDto;
import com.cx.wxs.dto.UUserDto;
import com.cx.wxs.dto.WWxsDto;
import com.cx.wxs.enums.EmailType;
import com.cx.wxs.service.DDiaryService;
import com.cx.wxs.service.EmailService;
import com.cx.wxs.service.UUserService;
import com.cx.wxs.service.WWxsService;
import com.cx.wxs.servlet.MailTransportQueue;
import com.cx.wxs.utils.Globals;
import com.cx.wxs.utils.HtmlNodeFilters;
import com.cx.wxs.utils.StringUtils;
import com.cx.wxs.utils.ValidateCode;
import com.cx.wxs.utils.ClientInfo;


/**
 * @author 陈义
 * @date   2016-1-7 下午4:21:18
 */ 
@Controller
public class IndexAction {

	@Resource
	private UUserService userService;
	@Resource
	private EmailService emailService;
	@Resource
	private WWxsService wxsService;
	@Resource
	private DDiaryService dDiaryService;
	


	/***
	 * 首页
	 * @param request
	 * @param response
	 * @return
	 * @author 陈义
	 * @date   2016-3-23下午9:21:46
	 */
	@RequestMapping(value="/")
	public  ModelAndView index(HttpServletRequest request,
			HttpServletResponse response){
		ModelAndView mv=new ModelAndView("main");
		return mv;
	}

	
	/***
	 * 进入专属页面
	 * @param vip  专属页面后缀
	 * @return
	 * @author 陈义
	 * @throws UnsupportedEncodingException 
	 * @date   2016-1-8下午9:20:30
	 */
	@RequestMapping(value="/{vip}")
	public ModelAndView home(@PathVariable(value = "vip") String name) throws UnsupportedEncodingException{
		ModelAndView mv=new ModelAndView();
		
		System.out.println(name);
		
		if(name.length()>=3&&name.substring(name.length()-3, name.length()).equals("文学社")){
			System.out.println("进入文学社判断");
			WWxsDto wxsDto=new WWxsDto();
			wxsDto.setName(name);
			wxsDto= wxsService.getWwxs(wxsDto);
			if(wxsDto==null){
				mv.setViewName("404");
			}else{
				mv.setViewName("wxs/main");
			}
		}else{ 
			UUserDto userDto=new UUserDto();
			userDto.setNickname(name);
			userDto=userService.getUuser(userDto);
			if(userDto==null){
				mv.setViewName("404");
			}else{
				mv.setViewName("user/main");
				//获取用户的文章
				DDiaryDto dDiaryDto = new DDiaryDto();
				dDiaryDto.setUUserDto(userDto);
			//	dDiaryDto.setRows(10);//默认一页显示10行
			//	mv.addObject("diaryList", dDiaryService.getDDiaryList(dDiaryDto));
				mv.addObject("author",userDto);
			}
		}
		mv.addObject("name", name);
		return mv;
	}
	
	@RequestMapping(value="/404")
	public ModelAndView to404(){
		ModelAndView mv=new ModelAndView("404");
		return mv;
	}
	@RequestMapping(value="/500")
	public ModelAndView to500(){
		ModelAndView mv=new ModelAndView("500");
		return mv;
	}
     
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
}
