package com.cx.wxs.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;






/**
 * @author 陈义
 * @date   2016-1-8 上午11:24:13
 */
public class RequestUtils {

	//定义log
	final static Log log = LogFactory.getLog(RequestUtils.class);
	
	private static Properties header_map;
	private static String default_mobile; 
	static{
		InputStream in = RequestUtils.class.getResourceAsStream("mobile_match.properties");
		header_map = new Properties();
		try{
			header_map.load(in);
			default_mobile = header_map.getProperty("empty");
		}catch(IOException e){
			log.error("加载手机号码匹配策略文件/mobile_match.conf失败",e);
		}
	}

	public static boolean isMultipart(HttpServletRequest req) {
		return ((req.getContentType() != null) && (req.getContentType()
				.toLowerCase().startsWith("multipart")));
	}
	
	/**
	 * 获取COOKIE
	 * 
	 * @param name
	 */
	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if(cookies == null)
			return null;
		for (int i = 0; i < cookies.length; i++) {
			if (name.equals(cookies[i].getName())) {
				return cookies[i];
			}
		}
		return null;
	}

	/**
	 * 设置COOKIE
	 * 
	 * @param name
	 * @param value
	 * @param maxAge
	 */
	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name,
			String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(maxAge);
		String serverName = request.getServerName();
		String domain = getDomainOfServerName(serverName);
		if(domain!=null && domain.indexOf('.')!=-1){
			cookie.setDomain('.' + domain);
		}
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	
	/**
	 * 获取用户访问URL中的根域名
	 * 例如: www.dlog.cn -> dlog.cn
	 * @param req
	 * @return
	 */
	public static String getDomainOfServerName(String host){
		if(StringUtils.isIPAddr(host))
			return null;
		String[] names = StringUtils.split(host, '.');
		int len = names.length;
		if(len>=2){
			if(domains.contains(names[len-2]))
				return names[len-3] + '.' + names[len-2]+'.'+names[len-1];
			else
				return names[len-2]+'.'+names[len-1];
		}
		return host;
	}
	
	private final static List<String> domains = new ArrayList<String>() {
		{
			add("net");
			add("com");
			add("gov");
			add("edu");
			add("org");
		}
	};
	
	public static void main(String[] args){
		String host = "127.0.0.1";
		System.out.println("DOMAIN: " + getDomainOfServerName(host));		
		host = "dlog.cn";
		System.out.println("DOMAIN: " + getDomainOfServerName(host));		
		host = "abc.mail.dlog.cn";
		System.out.println("DOMAIN: " + getDomainOfServerName(host));		
		host = "sky.net.my";
		System.out.println("DOMAIN: " + getDomainOfServerName(host));
		host="www.baidu.com/12345?a=b";
		
	}

	/**
	 * 从URL地址中解析出URL前缀，例如
	 * http://wap.mo168.com:8081/index.jsp -> http://wap.mo168.com:8081
	 * @param req
	 * @return
	 */
	public static String getUrlPrefix(HttpServletRequest req){
		StringBuffer url = new StringBuffer(req.getScheme());
		url.append("://");
		url.append(req.getServerName());
		int port = req.getServerPort();
		if(port!=80){
			url.append(":");
			url.append(port);
		}
		return url.toString();
	}
	
	/**
	 * 获取访问的URL全路径
	 * @param req
	 * @return
	 */
	public static String getRequestURL(HttpServletRequest req){
		StringBuffer url = new StringBuffer(req.getRequestURI());
		String param = req.getQueryString();
		if(param!=null){
			url.append('?');
			url.append(param);
		}
		String path = url.toString();
		return path.substring(req.getContextPath().length());
	}

	
	/**
	 * 打印所有的头信息
	 * @param out
	 * @param req
	 */
	public static void dumpHeaders(PrintStream out, HttpServletRequest req){
		Enumeration names = req.getHeaderNames();
		while(names.hasMoreElements()){
			String name = (String)names.nextElement();
			out.println(name+"="+req.getHeader(name));
		}
	}
	/**
	 * 从请求中解析手机号码
	 * @param req
	 * @return
	 */
	public static String getRequestMobile(HttpServletRequest req){
		String mobile = default_mobile;
		Iterator keys = header_map.keySet().iterator();
		while(keys.hasNext()){
			String header = (String)keys.next();
			String value = getHeader(req,header);
			if(value!=null){
				String pattern = (String)header_map.get(header);
				MessageFormat mf = new MessageFormat(pattern);
				try{
					Object[] vs = mf.parse(value);
					mobile = (String)vs[0];
					if(mobile.startsWith("86"))
						mobile = mobile.substring(2);
					break;
				}catch(Exception e){
					log.warn("解析header失败",e);
					dumpHeaders(req, System.err);
					continue;
				}
			}
		}
		return mobile;
	}
	
	/**
	 * 获取header信息，名字大小写无关
	 * @param req
	 * @param name
	 * @return
	 */
	public static String getHeader(HttpServletRequest req, String name){
		String value = req.getHeader(name);
		if(value!=null)
			return value;
		Enumeration names = req.getHeaderNames();
		while(names.hasMoreElements()){
			String n = (String)names.nextElement();
			if(n.equalsIgnoreCase(name)){
				return req.getHeader(n);
			}
		}
		return null;
	}
	
	/**
	 * 打印所有头信息
	 * @param req
	 * @param out
	 */
	public static void dumpHeaders(HttpServletRequest req, PrintStream out){
		Enumeration hds = req.getHeaderNames();
		out.println("=============== HEADERS ===============");
		while(hds.hasMoreElements()){
			String name = (String)hds.nextElement();
			out.println(name+"="+req.getHeader(name));
		}
	}

	/**
	 * 判断手机是否支持某种类型的格式
	 * @param req
	 * @param contentType
	 * @return
	 */
	public static boolean support(HttpServletRequest req, String contentType){
		String accept = getHeader(req, "accept");
		if(accept!=null){
			accept = accept.toLowerCase();
			return accept.indexOf(contentType.toLowerCase())!=-1;
		}
		return false;
	}
	
	/**
	 * 判断浏览器是否与Mozilla兼容
	 * @param req
	 * @return
	 */
	public static boolean isMozillaCompatible(HttpServletRequest req){
		String user_agent = req.getHeader("user-agent");
		return user_agent==null || user_agent.indexOf("Mozilla")!=-1;
	}

	/**
	 * 获取浏览器提交的整形参数
	 * @param param
	 * @param defaultValue
	 * @return
	 */
	public static int getParam(ServletRequest req, String param, int defaultValue){
		try{
			String value = req.getParameter(param);
			int idx = value.indexOf('#');
			if(idx!=-1)
				value = value.substring(0,idx);
			return Integer.parseInt(value);
		}catch(Exception e){}
		return defaultValue;
	}
	/**
	 * 获取浏览器提交的字符串参数
	 * @param param
	 * @param defaultValue
	 * @return
	 */
	public static String getParam(ServletRequest req, String param, String defaultValue){
		String value = req.getParameter(param);
		return (StringUtils.isEmpty(value))?defaultValue:value;
	}

	/**
	 * 获取浏览器提交的整形参数
	 * @param param
	 * @param defaultValue
	 * @return
	 */
	public static int getParam(HttpServletRequest req, String param, int defaultValue){
		try{
			String value = req.getParameter(param);
			int idx = value.indexOf('#');
			if(idx!=-1)
				value = value.substring(0,idx);
			return Integer.parseInt(value);
		}catch(Exception e){}
		return defaultValue;
	}
	/**
	 * 获取浏览器提交的字符串参数
	 * @param param
	 * @param defaultValue
	 * @return
	 */
	public static String getParam(HttpServletRequest req, String param, String defaultValue){
		String value = req.getParameter(param);
		return (StringUtils.isEmpty(value))?defaultValue:value;
	}

	/***
	 * 获取用户的user-angent
	 * @param req
	 * @return
	 * @author 陈义
	 * @date   2016-1-8上午11:36:12
	 */
	public static String getUA(HttpServletRequest req){
		return req.getHeader("User-Agent");
	}
	
	/***
	 * 获取用户的ip
	 * @param request
	 * @return
	 * @author 陈义
	 * @date   2016-1-8上午11:38:12
	 */
	public static String getIp(HttpServletRequest req){
		String ip = req.getHeader("x-forwarded-for");
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = req.getHeader("Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = req.getHeader("WL-Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = req.getRemoteAddr();
	    }
	    return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
	}
	
	
}
