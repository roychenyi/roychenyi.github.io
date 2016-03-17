package com.cx.wxs.velocity;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;


import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.tools.view.LinkTool;
import org.apache.velocity.tools.view.ViewToolContext;
import org.apache.velocity.tools.view.context.ViewContext;



import org.htmlparser.util.ParserException;

import com.cx.wxs.utils.DateUtils;
import com.cx.wxs.utils.LunarCalendar;
import com.cx.wxs.utils.RequestUtils;
import com.cx.wxs.utils.StringUtils;



/**
 * velocity基类
 * @author 陈义
 * @date   2016-1-8 上午11:20:31
 */
public abstract class VelocityTool extends LinkTool{
	
	public VelocityTool(){
		
	}

private static Log log = LogFactory.getLog(VelocityTool.class);
	
  protected VelocityContext velocity;

  protected Map params;
	/*protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected ServletContext context;
	protected VelocityContext velocity;

	protected Map params;
	
	
	 * Initialize toolbox
	 * @see org.apache.velocity.tools.view.tools.ViewTool#init(java.lang.Object)
	 
	public void init(Object arg0){
		//scope: request or session
		if(arg0 instanceof ViewContext){
			ViewContext viewContext = (ViewContext) arg0;
			request = viewContext.getRequest();
			response = viewContext.getResponse();
			context = viewContext.getServletContext();
			velocity = (VelocityContext)viewContext.getVelocityContext();
		}
		//scope: application
		else if(arg0 instanceof ServletContext){
			context = (ServletContext)arg0;
		}
	}*/
	
/*	public void init(Object arg0) {
		//scope: request or session
		if(arg0 instanceof ViewToolContext){
			ViewToolContext viewContext = (ViewToolContext) arg0;
			request = viewContext.getRequest();
			response = viewContext.getResponse();
			context = viewContext.getServletContext();
			velocity = (VelocityContext)viewContext.getVelocityContext();
		}
		//scope: application
		else if(arg0 instanceof ServletContext){
			context = (ServletContext)arg0;
		}
	}
	*/
	/**
	 * Call by VelocityViewServlet
	 */
	public void configure(Map arg0) {
		this.params = arg0;	
		try {
			BeanUtils.populate(this, arg0);
		} catch (Exception e) {
			log.error("Populate parameters to "+getClass().getName()+" failed.", e);
		}
	}
	
	public Locale locale(String language){
		return new Locale(language);
	}

	public Locale locale(String language, String country){
		return new Locale(language, country);
	}
	
	/**
	 * 设置Cookie
	 * @param key
	 * @param value
	 * @param maxAge
	 */
	public void set_cookie(String key, String value, int maxAge){
		RequestUtils.setCookie(request, response, key, value, maxAge);
	}

	/**
	 * 设置Cookie
	 * @param key
	 * @param value
	 * @param maxAge
	 */
	public void set_cookie(String key, int value, int maxAge){
		RequestUtils.setCookie(request, response, key, String.valueOf(value), maxAge);
	}
	
	public int get_cookie_as_int(String key){
		Cookie cookie = RequestUtils.getCookie(request, key);
		if(cookie == null)
			return -1;
		try{
			return Integer.parseInt(cookie.getValue());
		}catch(Exception e){}
		return -1;
	}
	
	/**
	 * 将异常信息转化成字符串
	 * @param t
	 * @return
	 * @throws IOException 
	 */
	public String exception(Throwable t) throws IOException{
		if(t == null)
			return null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try{
			t.printStackTrace(new PrintStream(baos));
		}finally{
			baos.close();
		}
		return baos.toString();
	}
	
	/**
	 * 将当前上下文发生的异常转为字符串
	 * @return
	 * @throws IOException
	 */
	public String exception() throws IOException{
		Throwable t = (Throwable)request.getAttribute(PageContext.EXCEPTION);
		if(t==null){
			//Tomcat的错误处理方式
			t = (Throwable)request.getAttribute("javax.servlet.error.exception");
		}
		return exception(t);
	}

	/**
	 * 返回当前时间戳
	 * @return
	 */
	public long current_timestamp(){
		return System.currentTimeMillis();
	}
	
	/**
	 * 返回耗时
	 * @param start_time
	 * @return
	 */
	public long time_consume(long start_time){
		return System.currentTimeMillis() - start_time;
	}
	
	/**
	 * 判断当前浏览器是否支持某种格式内容
	 * @param contentType
	 * @return
	 */
	public boolean support_content_type(String contentType){
		return RequestUtils.support(request, contentType);
	}

	/**
	 * 返回Toolbox类的初始化参数
	 * @param key
	 * @return
	 */
	protected String get_velocity_param(String key){
		return (String)params.get(key);
	}

    /**
     * 重定向到一个页面
     * @param url
     * @throws IOException
     */
    public void redirect(String url) throws IOException{
    	response.sendRedirect(url);
    }
    
    /**
     * 转换HTML成纯文本，用于显示最新评论或者是日记在首页的预览信息
     * @param html
     * @return
     * @throws UnsupportedEncodingException
     * @throws ParserException 
     * @deprecated
     */
    @Deprecated
	public String plain_text(String html) throws Exception{
		return VelocityTextTool.plain_text(html);
	}

	public String current_template(){
		return velocity.getCurrentTemplateName();
	}
	
	/**
	 * 取得某个页面模板的绝对路径
	 * @param page
	 * @return
	 */
	public String absolute_path(String page){
		if(page.startsWith("/"))
			return page;
		String cur_t = current_template();
		String path = cur_t.substring(0, cur_t.lastIndexOf("/"));
		return path + '/' + page;
	}
	
	/**
	 * 判断某个日期是否为今天
	 * @param year
	 * @param month
	 * @param date
	 * @return
	 */
	public boolean is_today(int year, int month, int date){
		Calendar today = Calendar.getInstance();
		today.set(Calendar.YEAR, year);
		today.set(Calendar.MONTH, month - 1);
		today.set(Calendar.DATE, date);
		return is_today(today.getTime());
	}
	
	/**
	 * 判断某个日期是否为今天
	 * @param date
	 * @return
	 */
	public boolean is_today(Date date){
		if(date==null)
			return false;
		return DateUtils.isSameDay(new Date(System.currentTimeMillis()), date);
	}
	
	/**
	 * 获取HTTP头
	 * @param name
	 * @return
	 */
	public String http_header(String name){
		return RequestUtils.getHeader(request, name);
	}
	
	/**
	 * 字符串智能截断
	 * @param str
	 * @param maxWidth
	 * @return
	 * @deprecated 此方法迁移到VelocityTextTool中
	 */
	@Deprecated
	public String abbreviate(String str, int maxWidth){
		if(str==null) return null;
		return StringUtils.abbreviate(str,maxWidth);
	}
	
	public void debug(Object obj) {
		System.out.println(obj);
	}

	/**
	 * 获取当前请求的资源名
	 * 
	 * @return
	 */
	public String get_request_file() {
		String url = request.getRequestURL().toString();
		int idx = url.lastIndexOf('/');
		return url.substring(idx + 1);
	}

	/**
	 * 获取当前请求的URL地址
	 * 
	 * @return
	 */
	public String get_request_url() {
		return RequestUtils.getRequestURL(request);
	}

	/**
	 * 判断是否是一个合法的邮件地址
	 * 
	 * @param email
	 * @return
	 * @deprecated 此方法迁移到VelocityTextTool中
	 */
	@Deprecated
	public boolean is_email(String email) {
		return StringUtils.isEmail(email);
	}
	
	public boolean is_post(){
		return "post".equalsIgnoreCase(request.getMethod());
	}
	
	public boolean is_get(){
		return "get".equalsIgnoreCase(request.getMethod());
	}

	/**
	 * 显示今天的日期
	 * @return
	 */
	public String today(){
		Locale locale = request.getLocale();
		String fmt = null;
		if(locale!=null && "zh".equalsIgnoreCase(locale.getLanguage()))
			fmt = "yyyy年M月d日 EEEEE";
		else
			fmt = "yyyy/MM/dd EEEEE";
		return new SimpleDateFormat(fmt, locale).format(Calendar.getInstance(locale).getTime());
	}
	/***
	 * 获取今天的时间+农历 
	 * @return  格式例如：2016年1月8日 星期五 农历乙未(羊)年冬月廿九
	 * @author 陈义
	 * @date   2016-1-8下午8:51:42
	 */
	public String today_lunar(){
		return LunarCalendar.today1();
	}

	/**
	 * 得到今天的节日(阳历在前，阴历在后)
	 * 如果同时有两个节日则：劳动节,端午节
	 * @return
	 */
	public String festival(){
		long[] ds = LunarCalendar.today();
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(0);
		nf.setMinimumIntegerDigits(2);
		String nongli = nf.format(ds[1]) + nf.format(ds[2]);
		Calendar cal = Calendar.getInstance();
		String yangli = nf.format(cal.get(Calendar.MONTH)+1)+nf.format(cal.get(Calendar.DATE));
		//System.out.printf("nongli=%s,yangli=%s\r\n",nongli,yangli);
		String f_nl = getMessage(request, "festival.properties", 'Y'+nongli);
		String f_yl = getMessage(request, "festival.properties", yangli);
		if(f_nl!=null && f_yl!=null)
			return f_yl + "," + f_nl;
		if(f_nl!=null)
			return f_nl;
		if(f_yl!=null)
			return f_yl;
		return null;
	}
	

	/**
	 * 读取资源中定义的信息
	 * @param req
	 * @param bundle
	 * @param key
	 * @return
	 */
    protected String getMessage(HttpServletRequest req, String bundle, String key) {
    	if(key==null)
    		return null;
    	Properties pps = new Properties();
    	try {
    		InputStream in = new FileInputStream(bundle);
    		//从输入流中读取属性列表（键和元素对） 
			pps.load(in);
			Object o=pps.get(key);
			if(o==null){
				return null;
			}else{
				return (String)o;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    	
    //	MessageResources res = StrutsUtils.getMessageResources(req, context, bundle);
    //	if(res==null)
    //		return null;
    //	return res.getMessage(key);
    }

	/**
	 * 格式化当前月第一天的日期
	 * 
	 * @param formatter
	 * @return
	 */
	public String format_now_month(String formatter) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatter, Locale.ENGLISH);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 1);
		return sdf.format(cal.getTime());
	}

	/**
	 * 获取web应用的上下文路径
	 * 
	 * @return
	 */
	public String root1() {
		VelocityTool v=new VelocityTool() {
		};
		v.root();
		return request.getContextPath();
	}

	/**
	 * 获取浏览器提交的整形参数
	 * 
	 * @param param
	 * @param defaultValue
	 * @return
	 */
	public int param(String param, int defaultValue) {
		return RequestUtils.getParam(request, param, defaultValue);
	}

	/**
	 * 获取浏览器提交的字符串参数
	 * 
	 * @param param
	 * @param defaultValue
	 * @return
	 */
	public String param(String param, String defaultValue) {
		return RequestUtils.getParam(request, param, defaultValue);
	}
	
	public String param(String param){
		return request.getParameter(param);
	}

	/**
	 * 获取浏览器提交的整形参数
	 * 
	 * @param param
	 * @param defaultValue
	 * @return
	 */
	public int get_attr(String param, int defaultValue) {
		try {
			return Integer.parseInt((String) request.getAttribute(param));
		} catch (Exception e) {
		}
		return defaultValue;
	}

	/**
	 * 获取浏览器提交的字符串参数
	 * 
	 * @param param
	 * @param defaultValue
	 * @return
	 */
	public Object get_attr(String param, Object defaultValue) {
		Object value = request.getAttribute(param);
		return (value == null) ? defaultValue : value;
	}

	/**
	 * @param str
	 * @return
	 * @deprecated 此方法迁移到VelocityTextTool中
	 */
	@Deprecated
	public boolean is_empty(String str) {
		return str == null || str.trim().length() == 0;
	}

	/**
	 * @param str
	 * @return
	 * @deprecated 此方法迁移到VelocityTextTool中
	 */
	@Deprecated
	public boolean not_empty(String str) {
		return !is_empty(str);
	}

	/**
	 * 从URL中解析出主机地址
	 * 
	 * @param url
	 * @return
	 */
	public String host(String url) {
		try {
			url = new java.net.URL(url).getHost();
		} catch (Exception e) {}
		return url;
	}

	/**
	 * 文本替换(login.vm)
	 * 
	 * @param src
	 * @param srcText
	 * @param desText
	 * @return
	 * @deprecated 此方法迁移到VelocityTextTool中
	 */
	@Deprecated
	public String replace(String src, String srcText, String desText) {
		return StringUtils.replace(src, srcText, desText);
	}

	public void dump() {
		RequestUtils.dumpHeaders(request, System.out);
	}

	/**
	 * 根据记录数和每页现实文章数确定页数
	 * @param recordCount
	 * @param perPage
	 * @return
	 */
	public int page_count(int recordCount, int perPage) {
		int pc = (int)Math.ceil(recordCount / (double)perPage);
		if(pc==0)
			pc = 1;
		return pc;
	}

	/**
	 * 获取当前正在访问的用户的手机号码
	 * 
	 * @return
	 */
	public String mobile() {
		return RequestUtils.getRequestMobile(request);
	}

	/**
	 * 返回数组的大小
	 * @param objs
	 * @return
	 */
	public int sizeof(Object[] objs){
		return (objs!=null)?objs.length:0;
	}
}
