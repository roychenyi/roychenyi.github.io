package com.cx.wxs.action;

import java.awt.Color;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.enterprise.inject.Model;
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
import com.cx.wxs.dto.UUserDto;
import com.cx.wxs.enums.EmailType;
import com.cx.wxs.service.EmailService;
import com.cx.wxs.service.UUserService;
import com.cx.wxs.servlet.MailTransportQueue;
import com.cx.wxs.utils.Globals;
import com.cx.wxs.utils.HtmlNodeFilters;
import com.cx.wxs.utils.MailSender;
import com.cx.wxs.utils.StringUtils;
import com.cx.wxs.utils.ValidateCode;
import com.cx.wxs.utils.clientInfo;


/**
 * @author 陈义
 * @date   2016-1-7 下午4:21:18
 */
@Controller
public class indexAction {

	@Resource
	UUserService uuService;
	@Resource
	EmailService emailService;



	/**
	 * @return the uuService
	 */
	public UUserService getUuService() {
		return uuService;
	}

	/**
	 * @param uuService the uuService to set
	 */
	public void setUuService(UUserService uuService) {
		this.uuService = uuService;
	}
	
	

	/**
	 * @return the emailService
	 */
	public EmailService getEmailService() {
		return emailService;
	}

	/**
	 * @param emailService the emailService to set
	 */
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	@RequestMapping(value="/")
	public  ModelAndView index(HttpServletRequest request,
			HttpServletResponse response){
		ModelAndView mv=new ModelAndView("user/main");
		return mv;
	}

	
	/***
	 * 进入专属页面
	 * @param vip  专属页面后缀
	 * @return
	 * @author 陈义
	 * @date   2016-1-8下午9:20:30
	 */
	@RequestMapping(value="/{vip}")
	public ModelAndView home(@PathVariable(value = "vip") String nickname){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("user/main");
		mv.addObject("nickname", nickname);
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

}
