package com.cx.wxs.action;

import java.text.MessageFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.cx.wxs.dto.UUserDto;
import com.cx.wxs.enums.EmailType;
import com.cx.wxs.service.EmailService;
import com.cx.wxs.service.UUserService;
import com.cx.wxs.utils.TemplateUtils;

/**
 * @author 陈义
 * @date   2016-5-19 下午9:51:41
 */
public class BaseUserAction extends BaseAction{

	@Resource
	private UUserService userService;
	@Resource
	private EmailService emailService;
	
	public boolean sendRegisterCheckMail(HttpServletRequest request,UUserDto userDto){
	    
		StringBuffer url = request.getRequestURL();  
		String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getServletContext().getContextPath()).append("/").toString(); 
        tempContextUrl+="register/check";
		try {
			tempContextUrl+="?username="+userDto.getUsername()+"&prev_url="+userDto.getUrl()+"&popedom="+userDto.getPopedom()+"&nocahe="+new Date().getTime();
			// 邮件内容
			String template=TemplateUtils.getRegisterCheckTemplate();
			String notify_content = MessageFormat.format(template,userDto.getNickname(),tempContextUrl,tempContextUrl);
//			String email_content = "<h1>欢迎验证</h1><p>请点击下面链接进行验证，若点击不成功，请充值url地址到浏览器跳转，谢谢合作。<br>地址：<a href=\""+tempContextUrl+"\" target=\"_blank\">"+tempContextUrl+"</a></p>";
//			Parser html = new Parser();
//			html.setEncoding("UTF-8");
//			html.setInputHTML(email_content);
//			Node[] nodes = html.extractAllNodesThatMatch(
//					HtmlNodeFilters.titleFilter).toNodeArray();
			String title = "文学社平台注册激活";
			String[] receivers=new String[]{userDto.getUsername()};
			emailService.sendEmail(title, receivers, notify_content, EmailType.VALIDATE);
			System.out.println("验证链接已发送！");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
