package com.cx.wxs.utils;



import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.springframework.util.DigestUtils;



/**
 * 字符串工具集合
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

	/**
	 * 如果系统中存在旧版本的数据，则此值不能修改，否则在进行密码解析的时候出错
	 */
    private static final String PASSWORD_CRYPT_KEY = "wxs2016_";
	private final static String DES = "DES";
    private final static String ISO8859_1 = "8859_1";
	
    /**
	 * [a-z][A-Z][0-9]单字符集合
	 */
	public static char[] chars = new char[] {
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
		'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
		'0', '1','2', '3', '4', '5', '6', '7', '8', '9' 
	};
    
    /*** 成功-success **/
	public static final String SUCCESS = "success";
	/*** 错误-error **/
	public static final String ERROR = "error";
	/*** 失败-failure **/
	public static final String FAILURE = "failure";

	/*** 成功状态码-00000 **/
	public static final String SUCCESS_CODE = "00000";
	/*** 错误状态码-44444 **/
	public static final String ERROR_CODE = "44444";
	/*** 失败状态码-99999 **/
	public static final String FAILURE_CODE = "99999";

	/*** 成功单个数字状态码-1 **/
	public static final Integer SUCCESS_NUMBER_CODE = 1;
	/*** 错误单个数字状态码-7 **/
	public static final Integer ERROR_NUMBER_CODE = 7;
	/*** 失败单个数字状态码-0 **/
	public static final Integer FAILURE_NUMBER_CODE = 0;

	/*** 返回结果集参数名称 **/
	public static final String RETURN_RESULT = "result";
	/**返回消息参数名称**/
	public static final String RETURN_MSG="msg";
	/*** 返回状态码参数名称 **/
	public static final String RETURN_CODE = "code";
	/*** 返回调试信息参数名称 **/
	public static final String RETURN_DEBUG = "debug";

	/*** 失败 **/
	public static final String TIP = "tip";
	/**请选择**/
	public static final String CHOOSE="--请选择--";
	/**
	 * 在线信息
	 */
	public static final String ONLINEINFO="OnlineInfoList";
	/**
	 * 更新的set Hql语句的key值
	 */
	public static final String SET_HQL = "setHql";
	/**
	 * 返回的参数列表key值
	 */
	public static final String PARAMS = "params";
	/**
	 * UTF-8编码
	 */
	public static final String UTF8 = "UTF-8";
	/**
	 * retrive the extend name of the given filename
	 * @param fn
	 * @return
	 */
	public static String getFileExtend(String fn){
		if(isEmpty(fn))
			return null;
		int idx = fn.lastIndexOf('.')+1;
		if(idx==0 || idx >= fn.length())
			return null;
		return fn.substring(idx);
	}

	/**
	 * MD5 摘要计算(byte[]).
	 * 
	 * @param src
	 *                   byte[]
	 * @throws Exception
	 * @return byte[] 16 bit digest
	 */
	public static byte[] md5(byte[] src) throws Exception {
		MessageDigest alg = MessageDigest.getInstance("MD5");
		return alg.digest(src);
	}

	/**
	 * MD5 摘要计算(String).
	 * 
	 * @param src
	 *                   String
	 * @throws Exception
	 * @return String
	 */
	public static String md5(String src) {
		try{
		return byte2hex(md5(src.getBytes()));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) throws Exception{
		System.out.println(md5("123456"));
		System.out.println(getPinyin("成长"));
		System.out.println(isAsciiOrDigit("a23AC123"));
		String name="123";
		  String regex="(.*)([a-zA-Z]+)(\\d+)(.*)";
		  String regex1="(.*)([a-zA-Z]+)(.*)";
	        Pattern pattern = Pattern.compile(regex);
	        Matcher match=pattern.matcher(name);
	        boolean b=match.matches();
	        pattern =Pattern.compile(regex1);
	        match=pattern.matcher(name);
	        boolean a=match.matches();
	        System.out.println(b);
	        System.out.println(a);
	        System.out.println(a&b);
	        System.out.println(compressLength("12::：3陈义agbc",8)); 
	}

	/**
	 * 将字符串用ch分割并放入队列
	 * @param tags
	 * @param ch
	 * @return
	 */
	public static List stringToList(String tags, String ch){
		if(tags==null)
			return null;
		ArrayList<String> tagList = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(tags, ch);
		while(st.hasMoreElements()){
			tagList.add(st.nextToken());
		}
		return tagList;
	}

	/**
	 * 将字符串用空格分割并放入队列
	 * @param tags
	 * @return
	 */
	public static List stringToList(String tags){
		if(tags==null)
			return null;
		ArrayList<String> tagList = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(tags);
		while(st.hasMoreElements()){
			tagList.add(st.nextToken());
		}
		return tagList;
	}

	/**
	 * BASE64编码
	 * @param s
	 * @return String
	 */
	public static byte[] enBASE64(byte[] bytes){
		return Base64Code.encode(bytes);
	}
	/**
	 * BASE64反编码
	 * @param bytes
	 * @return byte[]
	 */
	public static byte[] deBASE64(byte[] bytes){
		return Base64Code.decode(bytes);
	}
	/**
	 * BASE64编码
	 * @param s
	 * @return String
	 */
	public static String enBASE64(String s) {
		if (s != null) {
			byte abyte0[] = s.getBytes();
			abyte0 = Base64Code.encode(abyte0);
			s = new String(abyte0);
			return s;
		} 
		return null;
	}
	/**
	 * BASE64反编码
	 * @param s
	 * @return String
	 */
	public static String deBASE64(String s) {
		if (s != null) {
			byte abyte0[] = s.getBytes();
			abyte0 = Base64Code.decode(abyte0);
			s = new String(abyte0);
			abyte0 = null;
			return s;
		} 
		return null;
	}
	/**
	 * HTML输出内容格式转换
	 * @param content
	 * @return
	 */
	public static String formatContent(String content) {
        if(content==null)
            return "";        
		String randomStr = String.valueOf(System.currentTimeMillis());
		String html = StringUtils.replace(content,"&nbsp;",randomStr);		
		html = StringUtils.replace(html, "&", "&amp;");
		html = StringUtils.replace(html, "'", "&apos;");
		html = StringUtils.replace(html, "\"", "&quot;");
		html = StringUtils.replace(html, "\t", "&nbsp;&nbsp;");// 替换跳格
		html = StringUtils.replace(html, " ", "&nbsp;");// 替换空格
		html = StringUtils.replace(html, "<", "&lt;");
		html = StringUtils.replace(html, ">", "&gt;");
		return StringUtils.replace(html,randomStr,"&nbsp;").trim();
	}

	/**
	 * 用于提取HTML中的纯文本信息，过滤掉所有的HTML标签
	 */
    private final static NodeFilter nfilter = new NodeFilter() {
		public boolean accept(Node node) {
			//IMPORTANT: 只显示TextNode的内容
			return (node instanceof TextNode);
		}
    };
    
    /**
	  * 抽取纯文本信息
	  * @param inputHtml
	  * @return
	  */
	public static String extractText(String html) throws Exception {
		if(html==null) return null;
		StringBuffer text = new StringBuffer();
		Parser parser = new Parser();
		parser.setInputHTML(html);
		parser.setEncoding(ISO8859_1);
		//遍历所有的节点
		NodeList nodes;
		try {
			nodes = parser.extractAllNodesThatMatch(nfilter);
		} catch (ParserException e) {
			return html;
		}
		for(int i=0;i<nodes.size();i++){
			TextNode node = (TextNode)nodes.elementAt(i);
			text.append(node.getText());
		}
		return StringUtils.remove(text.toString(),"&nbsp;");
	}

	/**
	 * 判断是不是一个合法的电子邮件地址
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email){
		if(email==null)
			return false;
		email = email.trim();
		if(email.indexOf(' ')!=-1)
			return false;
		
		int idx = email.indexOf('@');
		if(idx==-1 || idx==0 || (idx+1)==email.length())
			return false;
		if(email.indexOf('@', idx+1)!=-1)
			return false;
        if(email.indexOf('.')==-1)
            return false;
		return true;
		/*
		Pattern emailer;
		if(emailer==null){
			String check = "^([a-z0-9A-Z]+[-|\\._]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			emailer = Pattern.compile(check);
		}
	    Matcher matcher = emailer.matcher(email);
	    return matcher.matches();
		*/
	}
	
	/***
	 * 如果字符串不为空，return false
	 * @param str
	 * @return
	 * @author 陈义
	 * @date   2015-12-4下午3:54:11
	 */
	public static boolean isNotEmpty(String str){
		return !isEmpty(str);
	}
	
	/***
	 * 判断字符串是否为空，如果为空，return ture;
	 * @param str
	 * @return
	 * @author 陈义
	 * @date   2015-12-4下午3:51:27
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0||str.isEmpty();
	}
	
	/**
	 * 判断字符串是否是一个IP地址
	 * @param addr
	 * @return
	 */
	public static boolean isIPAddr(String addr){
		if(isEmpty(addr))
			return false;
		String[] ips = split(addr, '.');
		if(ips.length != 4)
			return false;
		try{
			int ipa = Integer.parseInt(ips[0]);
			int ipb = Integer.parseInt(ips[1]);
			int ipc = Integer.parseInt(ips[2]);
			int ipd = Integer.parseInt(ips[3]);
			return ipa >= 0 && ipa <= 255 && ipb >= 0 && ipb <=255 && ipc >= 0
					&& ipc <= 255 && ipd >= 0 && ipd <=255;
		}catch(Exception e){}
		return false;
	}
	/**
	 * 加密
	 * @param src 数据源
	 * @param key 密钥，长度必须是8的倍数
	 * @return	  返回加密后的数据
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] src, byte[] key)
		throws Exception {
		//		DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		// 创建一个密匙工厂，然后用它把DESKeySpec转换成
		// 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance(DES);
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
		// 现在，获取数据并加密
		// 正式执行加密操作
		return cipher.doFinal(src);
	}
	
	/**
	 * 解密
	 * @param src	数据源
	 * @param key	密钥，长度必须是8的倍数
	 * @return		返回解密后的原始数据
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] src, byte[] key)
		throws Exception {
		//		DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建一个DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
		// 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance(DES);
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
		// 现在，获取数据并解密
		// 正式执行解密操作
		return cipher.doFinal(src);
	}
    /**
     * 数据解密
     * @param data
     * @param key 密钥
     * @return
     * @throws Exception
     */
    public final static String decrypt(String data, String key){
    	if(data!=null)
        try {
            return new String(decrypt(hex2byte(data.getBytes()),key.getBytes()));
        }catch(Exception e) {
        	e.printStackTrace();
        }
        return null;
    }
    /**
     * 数据加密
     * @param data
     * @param key 密钥
     * @return
     * @throws Exception
     */
    public final static String encrypt(String data, String key){
    	if(data!=null)
        try {
            return byte2hex(encrypt(data.getBytes(),key.getBytes()));
        }catch(Exception e) {
        	e.printStackTrace();
        }
        return null;
    }
    /**
     * 密码解密
     * @param data
     * @return
     * @throws Exception
     */
    public final static String decryptPassword(String data){
    	if(data!=null)
        try {
            return new String(decrypt(hex2byte(data.getBytes()),PASSWORD_CRYPT_KEY.getBytes()));
        }catch(Exception e) {
        	e.printStackTrace();
        }
        return null;
    }
    /**
     * 密码加密
     * @param password
     * @return
     * @throws Exception
     */
    public final static String encryptPassword(String password){
    	if(password!=null)
        try {
            return byte2hex(encrypt(password.getBytes(),PASSWORD_CRYPT_KEY.getBytes()));
        }catch(Exception e) {
        	e.printStackTrace();
        }
        return null;
    }
	/**
	 * 二行制转字符串
	 * @param b
	 * @return
	 */
    public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; b!=null && n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();
	}
    
    public static byte[] hex2byte(byte[] b) {
        if((b.length%2)!=0)
            throw new IllegalArgumentException("长度不是偶数");
		byte[] b2 = new byte[b.length/2];
		for (int n = 0; n < b.length; n+=2) {
		    String item = new String(b,n,2);
		    b2[n/2] = (byte)Integer.parseInt(item,16);
		}
        return b2;
    }
    
    /**
     * 大小写无关的字符串替换策略
     * @param str
     * @param src
     * @param obj
     * @return
     */
    public static String replaceIgnoreCase(String str, String src, String obj){
    	String l_str = str.toLowerCase();
    	String l_src = src.toLowerCase();
    	int fromIdx = 0;
    	StringBuffer result = new StringBuffer();
    	do{
    		int idx = l_str.indexOf(l_src, fromIdx);
    		if(idx==-1)
    			break;
    		result.append(str.substring(fromIdx, idx));
    		result.append(obj);
    		fromIdx = idx + src.length();
    	}while(true);
    	result.append(str.substring(fromIdx));
    	return result.toString();
    }

	/**
	 * 根据汉字字符获得笔画数,拼音和非法字符默认为0
	 * @param charcator
	 * @return int
	 */
	public static int getStrokeCount(char charcator) {
		byte[] bytes = (String.valueOf(charcator)).getBytes();
		if (bytes == null || bytes.length > 2 || bytes.length <= 0) {
			// 错误引用,非合法字符
			return 0;
		}
		if (bytes.length == 1) {
			// 英文字符
			return 0;
		}
		if (bytes.length == 2) {
			// 中文字符
			int highByte = 256 + bytes[0];
			int lowByte = 256 + bytes[1];
			return getStrokeCount(highByte, lowByte);
		}

		// 未知错误
		return 0;
	}

	 /** 
	  * @param highByte 高位字节
	  * @param lowByte 低位字节
	  * @return int
	  */
	private static int getStrokeCount(int highByte, int lowByte) {
		if (highByte < 0xB0 || highByte > 0xF7 || lowByte < 0xA1
				|| lowByte > 0xFE) {
			// 非GB2312合法字符
			return -1;
		}
		int offset = (highByte - 0xB0) * (0xFE - 0xA0) + (lowByte - 0xA1);
		return Constants.gb2312StrokeCount[offset];
	}

	/**
	 * 该方法返回一个字符串的拼音，对于要做敏感字
	 * 检查时应该一个字一个字来获取其拼音以免无法
	 * 得知每个字对应的拼音。
	 * @param word
	 * @return String
	 */
	public static String getPinyin(String word) {
		String pinyin = "";
		for (int i = 0; i < word.length(); i++)
			pinyin += getPinyin2(getCode(word.charAt(i)));
		return pinyin;
	}
	/**
	 * 该方法返回一个字符的DBCS编码值
	 * @param cc
	 * @return int
	 * @throws UnsupportedEncodingException 
	 */
	protected static int getCode(char cc) {
		byte[] bs;
		int code=0;
		try {
			bs= String.valueOf(cc).getBytes("GB2312");
			code = (bs[0] << 8) | (bs[1] & 0x00FF);
			if(bs.length < 2)
				code = (int)cc;
			bs = null;
			return code;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return code;
		}
	}
	/**
	 * 该方法通过DBCS的编码值到哈希表中查询得到对应的拼音串
	 * @param hz
	 * @return String
	 */
	protected static String getPinyin2(int hz) {
		String py = "";
		if (hz > 0 && hz < 160)
			py += hz;
		//else if (hz < -20319 || hz > -10247);
		else if (hz <= -10247 && hz >= -20319){
			PinyinCode pc = null;
			int i = Constants.pinyin.size() - 1;
			for (; i >= 0; i--) {
				pc = (PinyinCode) Constants.pinyin.get(i);
				if (pc.code <= hz)
					break;
			}
			if (i >= 0)
				py = pc.pinyin;
		}
		return py;
	}
	
    /**
     * 用户名必须是数字或者字母的结合
     * @param username
     * @return
     */
    public static boolean isLegalUsername(String username) {
        for(int i=0;i<username.length();i++){
            char ch = username.charAt(i);
            if(!isAscii(ch)&& 
                ch != '.' &&
                ch != '_' &&
                ch != '-' &&
                ch != '+' &&
                ch != '(' &&
                ch != ')' &&
                ch != '*' &&
                ch != '^' &&
                ch != '@' &&
                ch != '%' &&
                ch != '$' &&
                ch != '#' &&
                ch != '~' &&
                ch != '-')
                return false;
        }
        return true;
    }
    
    /**
     * 判断是否是字母和数字的结合
     * @param name
     * @return
     */
    public static boolean isAsciiOrDigit(String name){
      /*  for(int i=0;i<name.length();i++){
            char ch = name.charAt(i);
            if(!isAscii(ch))
            	return false;
        }*/
        String regex="(.*)([a-zA-Z]+)(\\d+)(.*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher match=pattern.matcher(name);
        boolean b=match.matches();
       
        return b;
    }
    
    public static boolean isAscii(char ch){
    	return (ch >='a' && ch <='z') || (ch >='A' && ch <='Z') || (ch >='0' && ch <='9');
    }
    
   
    
    /**
	 * 方法说明：判空为空
	 * @param obj
	 * @return 为null或空字符串返回true
	 * Ou
	 * 2012-12-5
	 */
	public static boolean isNull(Object obj) {
		if (obj == null || "".equals(obj))
			return true;
		else
			return false;
	}

	/***
	 * 
	 * @param obj
	 * @return
	 * @author 陈义
	 * @date   2015-12-1下午5:03:22
	 */
	public static boolean isNotNull(Object obj){
		if(obj == null || "".equals(obj))
			return false;
		else
			return true;
	}
	/***
	 * 
	 * @param str
	 * @return
	 * @author 陈义
	 * @date   2015-12-1下午5:03:33
	 */
	public static String isStrNull(String str){
		if(str.equals("null")){
			str="";
		} 
		return str;
	}
    
    /**
     * 返回姓名的拼音首字母
     * @param username
     * @return
     */
    public static String getTxlUserPinyin(String username) {
        if(username.getBytes().length == (2 * username.length())){
            //纯中文
            StringBuffer pinyin = new StringBuffer();
            for(int i=0;i<username.length();i++){
                String py = StringUtils.getPinyin(String.valueOf(username.charAt(i)));
                if(py!=null &&  py.length()>0)
                    pinyin.append(py.charAt(0));
                else
                    pinyin.append('V');
            }
            return pinyin.toString().toUpperCase();
        }
        else
        if(username.getBytes().length == username.length()){
            int len = (username.length()>3)?3:username.length();
            return username.substring(0,len).toUpperCase();
        }
        else{
            StringBuffer pinyin = new StringBuffer();
            for(int i=0;i<username.length();i++){
                char ch = username.charAt(i);
                try{
                    String py = StringUtils.getPinyin(String.valueOf(ch));
                    if(py!=null &&  py.length()>0)
                        pinyin.append(py.charAt(0));
                    else
                        pinyin.append(ch);
                }catch(ArrayIndexOutOfBoundsException e){}
                if(pinyin.length()>=3)
                    break;
            }
            return pinyin.toString().toUpperCase();
        }
        
    }
    
	
	
	/***************
	 * @Description: 返回一个map[100=--请选择--]
	 * @param stateNo
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map chooseMap(String stateNo){
		Map m=new HashMap();
		String text="";
		if(!StringUtils.isNullStr(stateNo)){
			if("100".equals(stateNo)){
				text=StringUtils.CHOOSE;
			}else{
				return null;
			}
		}else{
			return null;
		}
		m.put("id", "");
		m.put("text", text);
		return m;
	}
	/**********
	 * 
	 * @Description: (封装返回map)
	 * @param state
	 *            "success"|"error"|"failure"|"tip"
	 * @param msg
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map resultMap(String state, String msg) {
		Map map = new HashMap();
		map.put("state", state);
		map.put("msg", msg);
		return map;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void removeDuplicateWithOrder(ArrayList arlList) {
		Set set = new HashSet();
		List newList = new ArrayList();
		for (Iterator iter = arlList.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (set.add(element))
				newList.add(element);
		}
		arlList.clear();
		arlList.addAll(newList);
	}

	/***********
	 * 
	 * @Description: (验证输入的字符串是否为空)
	 * @param str
	 * @return true=为空|false=不为空
	 */
	public static boolean isNullStr(String str) {
		if (null != str && !"".equals(str.trim())) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 36进制的加法, 添加1
	 * @param value
	 * @return
	 * @author Ou
	 */
	public static String add36System(String value) {
		String KEY_SET = "0123456789abcdefghijklmnopqrstuvwxyz";
		return addStrSystem(value,KEY_SET);
	}
	/**
	 * 方法说明：x进制转10进制
	 * @param input 进制字符串
	 * @param radix 进制基数x
	 * @return
	 * @author Ou
	 * 2013-11-26
	 */
	public static int to10System(String x,int radix){
		int num = 0;
		try {
			if(StringUtils.isNotBlank(x))
				num = Integer.parseInt(x.trim(),radix);
		} catch (Exception e) {
			return 0;
		}
		return num;
	}
	/**
	 * 方法说明：将26进制转为10进制
	 * @param str
	 * @return
	 * @author Ou
	 * 2014-6-4
	 */
	public static int get26to10(String str){
		String standard = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int l = str.length();
		int scale = 1;
		int index = 0;
		str = str.toUpperCase();
		for(int j=l-1;j>=0;j--){
			char s = str.charAt(j);
			int n = standard.indexOf(s)+1;
			index = index + n * scale;
			scale = scale * 26;
		}
		return index;
	}
	/**
	 * 10进制加法，添加1
	 * @param value
	 * @return 返回value+1
	 * 2013-3-14
	 */
	public static String add10System(String value) {
		String KEY_SET = "0123456789";
		return addStrSystem(value,KEY_SET);
	}
	/**
	 * 10进制加法，添加1
	 * @param value
	 * @return 返回value+1
	 * 2013-3-14
	 */
	public static String addStrSystem(String value,final String KEY_SET){

		String[] bytes = value.split("");

		// 合法性校验
		for (int k = 0; k < bytes.length - 1; k++) {
			if (!"".equals(bytes[k]) && KEY_SET.indexOf(bytes[k]) == -1) {
				throw new IllegalArgumentException("错误的进制值。");
			}
		}

		boolean carryFlag = false;
		for (int k = bytes.length - 1; k >= 0; k--) {
			if (carryFlag) {
				int index = KEY_SET.indexOf(bytes[k]);
				if (index == KEY_SET.length() - 1) {// 已经是最大值，需要进位
					bytes[k] = "0";
					carryFlag = true;
					continue;
				} else {// 取下一位的值
					bytes[k] = KEY_SET.substring(index + 1, index + 2);
					carryFlag = false;
					break;
				}
			}

			// 不需要进位
			int index = KEY_SET.indexOf(bytes[k]);
			if (index == KEY_SET.length() - 1) {// 准备进制
				bytes[k] = "0";
				carryFlag = true;
			} else {
				bytes[k] = KEY_SET.substring(index + 1, index + 2);
				break;
			}
		}
		String result = "";
		for (int k = 0; k < bytes.length; k++) {
			result += bytes[k];
		}
		return result;
	}

	/**
	 * 返回min-max之间的随机数
	 * @param min 最小
	 * @param max 最大
	 * @return
	 * @author Ou
	 * 2013-5-6
	 */
	public static long randomNum(int min,int max){
		return Math.round(Math.random()*(max-min)+min);  //公式
	}
	/**
	 * 获取n位[0-9]string类型的随机数
	 * @param num
	 * @return
	 * @author Ou
	 * 2013-5-6
	 */
	public static String randomStr(int n){
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < n ; i ++){
			long num = Math.round(Math.random()*(9-0)+0);
			String[] str = {"0","1","2","3","4","5","6","7","8","9"};
			sb.append(str[(int) num]);
		}
		return sb.toString();
	}

	

	

	/**
	 * 生成36位的uuid
	 * @return 格式：b935ac46-b4d8-4985-bc30-d27852ef2f19
	 * @author Ou
	 * 2013-4-6
	 */
	public static String getUuid(){
		return UUID.randomUUID().toString();
	}
	/**
	 * 生成32位的uuid
	 * @return 格式：b935ac46b4d84985bc30d27852ef2f19
	 * @author Ou
	 * 2013-4-6
	 */
	public static String getUuid32(){
		return UUID.randomUUID().toString().replace("-","");
	}
	/** 
	 * 以年月日时分秒毫秒+4位随机数的格式来创建一个文件名，不带扩展名 
	 * @return 文件名 
	 */  
	public static String createFileName() {  
		StringBuffer sb = new StringBuffer();  
		Date date = new Date();  
		//获取年月日时分秒  
		sb.append(new SimpleDateFormat("yyyyMMddHHmmss").format(date));  
		//毫秒  
		String milli = String.valueOf(date.getTime() % 1000);  
		while (milli.length() < 3) {  
			milli = "0" + milli;  
		}  
		sb.append(milli);  
		//四位随机数  
		String rondom = String.valueOf(new Random().nextInt(10000));  
		while (rondom.length() < 4) {  
			rondom = "0" + rondom;  
		}  
		sb.append(rondom);  
		return sb.toString();  
	}

	public static String getNewFilePath(String currentPath,String fileName){
		//临时目录,用于生成临时文件  
		String tempPath = "" ; 
		File f = new File(currentPath);  
		//不存在则创建它  
		if (!f.exists())   
			f.mkdirs(); 
		tempPath = currentPath + "/" + fileName;  
		return tempPath;
	}
	/**
	 * 替换从word等文件中复制带有tab格式的字符，在字符中表现为"\t"
	 * @param str
	 * @return
	 * Ou
	 * 2013-7-26
	 */
	public static String replaceTab(String str){
		return str.replaceAll("\t", "");
	}




	/**
	 * 判断字符串是否为一个空格或者多个空格字符串
	 * <p>
	 * 例如:isBlank("")则返回true,isBlank(" ")则返回true
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		return str == null || str.trim().length() == 0;
	}

	/**
	 * 判断字符串不为一个空格或者多个空格字符串
	 * <p>
	 * 查看isBlank(String str)方法
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	/**
	 * 比较两个字符值是否相等
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean eqauls(String str1,
			String str2) {
		return (str1 == str2 || (str1 != null && str1.equals(str2)));
	}

	/**
	 * toString
	 * 
	 * @param i
	 * @return
	 */
	public static String toString(int i) {
		return i + "";// new Integer(i).toString();
	}

	/**
	 * toString
	 * 
	 * @param obj
	 * @return
	 */
	public static String toString(Object obj) {
		return obj == null ? null : obj.toString();
	}

	/**
	 * 去除字符串两边的空格
	 * <p>
	 * 
	 * @param str
	 * @return
	 */
	public static String trim(String str) {
		if (str != null) {
			str = str.trim();
		}
		return str;
	}

	/**
	 * 若该斯字符串为空白字符，则返回空。
	 * 
	 * @param str
	 * @return
	 */
	public static String returnNullIfBlank(String str) {
		if (isBlank(str)) {
			return null;
		}
		return str;
	}

	/**
	 * 若该对象为NULL，则返回Empty。即""的字符串。
	 * 
	 * @param object
	 * @return
	 */
	public static String returnEmptyIfNull(Object object) {
		if (object == null) {
			return "";
		}
		return object.toString();
	}

	/**
	 * 若该斯字符串为空字符，则返回空。
	 * 
	 * @param str
	 * @return
	 */
	public static String returnNullIfEmpty(String str) {
		if (isEmpty(str)) {
			return null;
		}
		return str;
	}
	/**
	 * 把字符数组连接成字符串，以"，"连接
	 * 
	 * @param stringArray
	 * @return
	 */
	public static String arrayToString(String[] stringArray) {
		return arrayToString(stringArray, ",");
	}

	/**
	 * 把字符数组连接成字符串
	 * 
	 * @param stringArray
	 * @param joinchar
	 *            连接字符串
	 * @return
	 */
	public static String arrayToString(String[] stringArray,
			String joinchar) {
		if (stringArray == null || stringArray.length == 0) {
			return "";
		}

		if (joinchar == null) {
			joinchar = ",";
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < stringArray.length; i++) {
			sb.append(stringArray[i]).append(joinchar);
		}

		sb.delete(sb.length() - joinchar.length(), sb.length());

		return sb.toString();
	}
	/**
	 * 将数组转化成元素带单引号的字符串
	 * @return
	 */
	public static String arraytoStringaddqt(String[] stringArray){
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < stringArray.length; i++) {
			if (i == stringArray.length-1) {
				str.append("'"+stringArray[i]+"'");
			}else {
				str.append("'"+stringArray[i]+"',");
			}
		}
		return str.toString();
	}



	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String[] strToArrTo(String src,String delim){
		StringTokenizer s=new StringTokenizer(src,delim);
		List list=new ArrayList();
		while(s.hasMoreElements()){
			list.add((String)s.nextElement());
		}
		Object obj[]=list.toArray();
		String[] arr=new String[obj.length];
		for(int i=0;i<obj.length;i++){
			arr[i]=(String)obj[i];
		}
		return arr;
	}

	/**********************
	 * @Description: 根据给定的分隔符把字符串转为List<String>
	 * @param src
	 * @param delim
	 * @return
	 */
	public static List<String> strToList(String src,String delim){
		StringTokenizer s=new StringTokenizer(src,delim);
		List<String> list=new ArrayList<String>();
		while(s.hasMoreElements()){
			list.add((String)s.nextElement());
		}
		return list;
	}

	/**************
	 * list集合转为字符串根据给定的分隔符
	 * 描述  
	 * @param list
	 * @param delim 分隔符
	 * @return
	 */
	public static String listToStr(List<String> list,String delim){
		StringBuffer buf=new StringBuffer();
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				buf.append(list.get(i)+delim);
			}
			buf.delete(buf.length()-1, buf.length());
		}
		return buf.toString();
	}

	/*********************
	 * 移除相同的项
	 * 描述  
	 * @param list
	 * @return
	 */
	public static List<String> checkRepeat(List<String> list) {
		List<String> pks = new ArrayList<String>();
		Set<String> set = new HashSet<String>();
		for (String str : list) {
			set.add(str);
		}
		for (Iterator<String> pk = set.iterator(); pk.hasNext();) {
			pks.add(pk.next());
		}
		return pks;
	}

	/********************
	 * 
	 * 描述  ：得到指定长度的编号如（001，000002）
	 * @param tar 目标字符串
	 * @param len 创建指定长度
	 * @return
	 */
	public static String getLengthStr(String tar,int len){
		int l=tar.length();
		StringBuffer sb=new StringBuffer();
		if(l<len){
			for(int i=0;i<len-l;i++){
				sb.append("0");
			}
			sb.append(tar);
		}
		return sb.toString();
	}
	/**
	 * 方法说明：替换html，防止恶意注入js
	 * @param html
	 * @return
	 */
	public static String replaceHtmlText(String html){

		html = html.replaceAll("&", "&amp;");

		html = html.replaceAll("'", "&qpos;");

		html = html.replaceAll("<", "&lt;");

		html = html.replaceAll(">", "&gt;");

		html = html.replaceAll("\"", "&quot;");

		html = html.replaceAll(" ", "&nbsp;");

		html = html.replaceAll("javascript", "&nbsp;");

		html = html.replaceAll("(?i)script", "&nbsp;");

		return html;
	}

	/************
	 * 比较两个对象是否相同(属性值比较)
	 * 描述  
	 * @param pojo1
	 * @param pojo2
	 * @return true=相同|false=不相同
	 * @throws IntrospectionException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 */
	@SuppressWarnings("rawtypes")
	public static boolean compareObj(Object pojo1, Object pojo2) throws IntrospectionException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, InstantiationException {
		boolean flg = true;
		Class clazz = pojo1.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
			Method getMethod = pd.getReadMethod();
			Object o1 = getMethod.invoke(pojo1);
			Object o2 = getMethod.invoke(pojo2);
			o1 = o1 == null ? "" : o1;
			o2 = o2 == null ? "" : o2;
			if(o1 instanceof String){
				if (!o1.toString().trim().equals(o2.toString().trim())) {
					flg = false;
					break;
				}
			}
			if(o1 instanceof Double){
				int m=Double.compare(((Double) o1).doubleValue(), ((Double) o2).doubleValue());
				if(m!=0){
					flg=false;
					break;
				}
			}

		}
		return flg;
	}

	/***************************
	 * 
	 * @Description: (比较两个集合查询出要操作的类型（添加|删除|修改）)
	 * @param pro_fk_ids
	 * @param itss
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map operResult(List<String> pro_fk_ids,List<String> itss){
		List ll=new ArrayList();//记录要添加的担当外键
		List temp=new ArrayList();
		List al=new ArrayList();//记录要删除的担当外键
		List up=new ArrayList();//记录要修改的担当外键
		for(int i=0;i<pro_fk_ids.size();i++){
			boolean flg=false;
			for(int k=0;k<itss.size();k++){
				if(pro_fk_ids.get(i).equals(itss.get(k))){
					flg=true;
				}
			}
			if(!flg){
				ll.add(pro_fk_ids.get(i));
			}
			if(flg){
				temp.add(pro_fk_ids.get(i));

			}
		}
		for(int k=0;k<itss.size();k++){
			boolean f=false;
			for(int l=0;l<temp.size();l++){
				if(itss.get(k).equals(temp.get(l))){
					f=true;
				}
			}
			if(!f){
				al.add(itss.get(k));
			}else{
				up.add(itss.get(k));
			}
		}
		Map m=new HashMap();
		m.put("add", ll);
		m.put("del", al);
		m.put("update", up);
		return m;
	}
	/**
	 * MD5加密
	 * @param str需要MD5加密的字符串(不能为空或null，否则原样返回）
	 * @param times 加密的次数，默认为1次加密
	 * @return 加密后的字符串
	
	 * @date 2015-1-27 下午4:43:34
	 */
	public static String MD5(String str,Integer times){
		if(isNotEmpty(str)){
			if(times<1){
				times = 1;
			}
			String md5String = "";
			for (int i = 0; i < times; i++) {
				md5String = isNotNull(md5String)? DigestUtils.md5DigestAsHex(md5String.getBytes()):DigestUtils.md5DigestAsHex(str.getBytes());
			}
			return md5String;
		} 
		return str;
	}
	/**
	 * unicode 转换成 中文
	 * @param theString
	 * @return
	
	 * @date 2015-2-2 上午11:13:30
	 */
	public static String decodeUnicode(String theString) {  
		char aChar;  
		int len = theString.length();  
		StringBuffer outBuffer = new StringBuffer(len);  
		for (int x = 0; x < len;) { 
			aChar = theString.charAt(x++);   
			if (aChar == '\\') {   
				aChar = theString.charAt(x++);    
				if (aChar == 'u') {  
					int value = 0;   
					for (int i = 0; i < 4; i++) { 
						aChar = theString.charAt(x++);   
						switch (aChar) {    
						case '0': break;
						case '1': break;
						case '2': break;
						case '3': break;
						case '4': break;
						case '5': break;
						case '6': break;
						case '7': break;
						case '8': break;
						case '9': 
							value = (value << 4) + aChar - '0'; 
							break;
						case 'a': break;
						case 'b': break;
						case 'c': break;
						case 'd': break;
						case 'e': break;
						case 'f': 
							value = (value << 4) + 10 + aChar - 'a';   
							break; 
						case 'A': break;
						case 'B': break;
						case 'C': break;
						case 'D': break;
						case 'E': break;
						case 'F': 
							value = (value << 4) + 10 + aChar - 'A';    
							break;
						default:  
							throw new IllegalArgumentException("Malformed  encoding.");    
						}   
					}   
					outBuffer.append((char) value);  
				} else {   
					if (aChar == 't') { 
						aChar = '\t';   
					} else if (aChar == 'r') {  
						aChar = '\r';   
					} else if (aChar == 'n') { 
						aChar = '\n';     
					} else if (aChar == 'f') {   
						aChar = '\f';  
					}     
					outBuffer.append(aChar);  
				}  
			} else { 
				outBuffer.append(aChar); 
			} 
		}
		return outBuffer.toString();
	}
	/**
	 * 根据货币原名称，返回其实际的名称意思
	 * @param coin 支持大小写
	 * @return
	
	 * @date 2015-2-3 下午3:39:06
	 */
	public static String getCoinName(String coin){
		String coinStr = coin.toUpperCase();
		if(coinStr.equals("FCV")){
			return "冻结余额";
		}

		if(coinStr.equals("CSGW")){
			return "GW可用消费";
		}
		return coin;
	}
	/**
	 * 去掉String数组重复
	 * @param strArray
	 * @return
	
	 * @date 2015-2-6 上午10:43:47
	 */
	public static String[] uniqStringArray(String[] strArray){
		if(strArray!=null&&strArray.length>0){
			TreeSet<String> ts = new TreeSet<String>();
			for (String obj : strArray) {
				ts.add(obj);
			}
			return ts.toArray(new String[]{});//这里必须为新的数组对象，不能将参数中的数组对象传入
		}
		return null;
	}
	/**
	 * 去掉最后的指定长度的字符串
	 * @param str 字符串
	 * @param length 从字符串最后处开始，截断多少长度的字符【注意：最大长度不能超过原字符串长度，否则被设置为0】
	 * @return
	 * @date 2015-3-17 上午10:15:14
	
	 */
	public static String removeLastStr(String str, int length) {
		if(isNotBlank(str)){
			length = str.length()>=length?length:0;
			return str.substring(0, str.length()-length);
		}
		return str;
	}
	/**
	 * 去掉数组中重复的对象
	 * @param objects
	 * @return
	
	 * @date 2015-3-11 下午9:48:06
	 */
	public static Object[] uniqObjectArray(Object[] objects){
		if(objects!=null){
			Object[] objs = (Object[])objects;
			TreeSet<Object> ts = new TreeSet<Object>();
			for (Object obj : objs) {
				ts.add(obj);
			}
			return ts.toArray(new Object[]{});//这里必须为新的数组对象，不能将参数中的数组对象传入
		}
		return null;
	}
	
	/**
	 * 对字符串进行编码
	 * @param s 需要编码的字符串
	 * @param enc 需要编码的编码格式
	 * @return 若s为空或者转换失败，则原样返回s
	 * @date 2015-4-20 上午8:27:05
	
	 */
	@SuppressWarnings("deprecation")
	public static String encode(String s,String enc) {
		try {
			if(isNotEmpty(enc)){
			return URLEncoder.encode(s, enc);
			}
			return URLEncoder.encode(s);
		} catch (UnsupportedEncodingException e) {
			
		}
		return s;
	}
	/**
	 * 对字符串进行解码
	 * @param s 需要解码的字符串
	 * @param enc 解码的格式
	 * @return 若s为空，则原样返回
	 * @date 2015-4-20 上午8:29:52
	
	 */
	@SuppressWarnings("deprecation")
	public static String decode(String s,String enc){
		try {
			if(isNotEmpty(enc)){
			return URLDecoder.decode(s, enc);
			}
			return URLDecoder.decode(s);
		} catch (UnsupportedEncodingException e) {
			
		}
		return s;
	}

	
	/**
	 * 处理平台内商品原图片路径格式
	 * PS:商品原图片路径格式：
	 * http://xxx.com/uploads/goods/images/[uname]/[uploadDate]/[filename];http://xxx.com/uploads/goods/images/[uname]/[uploadDate]/[filename]
	 * @param img
	 * @param regex
	 * @return
	 * @date 2015-3-13 上午10:51:18
	
	 */
	public static String[] toImagesList(String img,String regex){
		if(isNotEmpty(img)){
			regex = isNotEmpty(regex)?regex:";";
			return img.split("["+regex+"]");
		}
		return null;
	}
	/**
	 * 根据字符串数组，返回指定索引的值
	 * @param images 字符串数组
	 * @param index 数组中的索引，小于当前索引的长度，否则默认为0
	 * @return
	 * @date 2015-3-13 上午11:41:09
	
	 */
	public static String getImagesPath(String[] images,Integer index){
		if(images!=null&&images.length>0){
			if(index>=images.length||index<0)index = 0;
			return images[index];
		}
		return "";
	}
	/**
	 * 批量参数的值，转换为整数数组形式
	 * @return
	
	 * @date 2015-2-13 上午11:20:24
	 */
	public static List<Integer> toBatchIntArray(String batch){
		List<Integer> list= null;
		if(batch!=null){
			String[] batchs = batch.split(",");
			list = new ArrayList<Integer>();
			for (String string : batchs) {
				list.add(Integer.valueOf(string));
			}
		}
		return list;
	}
	/**
	 * 生成随机字符，包括a-z，A-Z，0-9字符组成
	 * @param length  要生成随机字符的长度
	 * @return
	 */
	public static String random(int length) {
		int charsLen = chars.length;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append(chars[(int) (Math.random() * charsLen)]);
		}
		return sb.toString();
	}
	/**
	 * GBK转UTF-8
	 * @param chenese
	 * @return
	 * @author 陈义
	 * @date   2016-4-29下午8:11:50
	 */
	public static byte[] gbk2utf8(String chenese){
		  char c[] = chenese.toCharArray();
		        byte [] fullByte =new byte[3*c.length];
		        for(int i=0; i<c.length; i++){
		         int m = (int)c[i];
		         String word = Integer.toBinaryString(m);
//		         System.out.println(word);
		        
		         StringBuffer sb = new StringBuffer();
		         int len = 16 - word.length();
		         //补零
		         for(int j=0; j<len; j++){
		          sb.append("0");
		         }
		         sb.append(word);
		         sb.insert(0, "1110");
		         sb.insert(8, "10");
		         sb.insert(16, "10");
		         
//		         System.out.println(sb.toString());
		         
		         String s1 = sb.substring(0, 8);          
		         String s2 = sb.substring(8, 16);          
		         String s3 = sb.substring(16);
		         
		         byte b0 = Integer.valueOf(s1, 2).byteValue();
		         byte b1 = Integer.valueOf(s2, 2).byteValue();
		         byte b2 = Integer.valueOf(s3, 2).byteValue();
		         byte[] bf = new byte[3];
		         bf[0] = b0;
		         fullByte[i*3] = bf[0];
		         bf[1] = b1;
		         fullByte[i*3+1] = bf[1];
		         bf[2] = b2;
		         fullByte[i*3+2] = bf[2];
		         
		        }
		        return fullByte;
		 }

	/***
	 * 字符串的首字母变大写
	 * @param fildeName
	 * @return
	 * @author 陈义
	 * @date   2015-12-9下午3:26:10
	 */
	public static String firstUpperCase(String str){
		return str.substring(0,1).toUpperCase()+str.substring(1);
		 /*byte[] items = fildeName.getBytes();
		  System.out.println(items[0]);
		  if(items[0]>=((byte)'A')&&items[0]<=((byte)'Z')){
			  return new String(items);
		  }else{
		  items[0] = (byte) ((char) items[0] - 'a' + 'A');
		  return new String(items);
		  }*/
	}

	public static String firstLowerCase(String str){
		return str.substring(0,1).toLowerCase()+str.substring(1);
	}
	/***
	 * 截取字符串
	 * @param str
	 * @param subSLength
	 * @return
	 * @author 陈义
	 * @date   2016-6-19上午11:15:00
	 */
	public static String compressLength(String str,int subSLength){
		if (str == null)    
            return "";    
        else{   
            int tempSubLength = subSLength;//截取字节数  
            String subStr = str.substring(0, str.length()<subSLength ? str.length() : subSLength);//截取的子串    
         try{   int subStrByetsL = subStr.getBytes("GBK").length;//截取子串的字节长度   
            //int subStrByetsL = subStr.getBytes().length;//截取子串的字节长度   
            // 说明截取的字符串中包含有汉字    
            while (subStrByetsL > tempSubLength){    
                int subSLengthTemp = --subSLength;  
                subStr = str.substring(0, subSLengthTemp>str.length() ? str.length() : subSLengthTemp);    
                subStrByetsL = subStr.getBytes("GBK").length;  
                //subStrByetsL = subStr.getBytes().length;  
            }    
            return subStr; 
         }catch (Exception e) {
			// TODO: handle exception
        	 e.printStackTrace();
		}
		return str+"...";
        }
	}
	 
}

/***
 * base64实现类型
 * @author 陈义
 * @date   2015-11-27 上午9:30:20
 */
class Base64Code {


	protected static byte[] _encode_map = 
		{
		(byte)'A', (byte)'B', (byte)'C', (byte)'D', (byte)'E', (byte)'F', (byte)'G',
		(byte)'H', (byte)'I', (byte)'J', (byte)'K', (byte)'L', (byte)'M', (byte)'N',
		(byte)'O', (byte)'P', (byte)'Q', (byte)'R', (byte)'S', (byte)'T', (byte)'U',
		(byte)'V', (byte)'W', (byte)'X', (byte)'Y', (byte)'Z',

		(byte)'a', (byte)'b', (byte)'c', (byte)'d', (byte)'e', (byte)'f', (byte)'g',
		(byte)'h', (byte)'i', (byte)'j', (byte)'k', (byte)'l', (byte)'m', (byte)'n',
		(byte)'o', (byte)'p', (byte)'q', (byte)'r', (byte)'s', (byte)'t', (byte)'u',
		(byte)'v', (byte)'w', (byte)'x', (byte)'y', (byte)'z',

		(byte)'0', (byte)'1', (byte)'2', (byte)'3', (byte)'4', 
		(byte)'5', (byte)'6', (byte)'7', (byte)'8', (byte)'9',

		(byte)'+', (byte)'/' };
	
	protected static byte _decode_map[] = new byte[128];
	static {
		/*
		 * Fill in the decode map
		 */
		for (int i = 0; i < _encode_map.length; i++) {
			_decode_map[_encode_map[i]] = (byte)i;
		}
	}


	/**
	 * This class isn't meant to be instantiated.
	 */
	private Base64Code () 
	{

	}



	/**
	 * This method encodes the given byte[] using the Base64 encoding
	 * 
	 *
	 * @param  data the data to encode.
	 * @return the Base64 encoded <var>data</var>
	 */
	public final static byte[] encode (byte[] data) {

		if (data == null) {
			return (null);
		}

		/*
		 * Craete a buffer to hold the results
		 */
		byte dest[] = new byte[((data.length + 2) / 3) * 4];


		/*
		 * 3-byte to 4-byte conversion and 
		 * 0-63 to ascii printable conversion
		 */
		int i, j;
		int data_len = data.length - 2;
		for (i = 0, j = 0; i < data_len; i += 3) {

			dest[j++] = _encode_map[(data[i] >>> 2) & 077];
	    		dest[j++] = _encode_map[(data[i + 1] >>> 4) & 017 |
				(data[i] << 4) & 077];
	    		dest[j++] = _encode_map[(data[i + 2] >>> 6) & 003 |
				(data[i + 1] << 2) & 077];
	    		dest[j++] = _encode_map[data[i + 2] & 077];
		}
	
		if (i < data.length) {
			dest[j++] = _encode_map[(data[i] >>> 2) & 077];

			if (i < data.length-1) {
			    dest[j++] = _encode_map[(data[i + 1] >>> 4) & 017 |
				    (data[i] << 4) & 077];
			    dest[j++] = _encode_map[(data[i + 1] << 2) & 077];
	    		} else {
			    dest[j++] = _encode_map[(data[i] << 4) & 077];
			}
		}


		/*
		 * Pad with "=" characters
		 */
		for ( ; j < dest.length; j++) {
			dest[j] = (byte)'=';
		}

		return (dest);
	}


	/**
	 * This method decodes the given byte[] using the Base64 encoding
	 *
	 *
	 * @param  data the Base64 encoded data to decode.
	 * @return the decoded <var>data</var>.
	 */
	public final static byte[] decode (byte[] data) {

		if (data == null)
			return (null);

		/*
		 * Remove the padding on the end
		 */
		int ending = data.length;
		if (ending < 1) {
			return (null);
		}
		while (data[ending - 1] == '=')
			ending--;

		/*
		 * Create a buffer to hold the results
		 */
		byte dest[] = new byte[ending - data.length / 4];


		/*
		 * ASCII printable to 0-63 conversion
		 */
		for (int i = 0; i < data.length; i++) {
			data[i] = _decode_map[data[i]];
		}

	
		/*
		 * 4-byte to 3-byte conversion
		 */
		int i, j;
		int dest_len = dest.length - 2;
		for (i = 0, j = 0; j < dest_len; i += 4, j += 3) {
			dest[j] = (byte) (((data[i] << 2) & 255) |
			 	((data[i + 1] >>> 4) & 003));
			dest[j + 1] = (byte) (((data[i + 1] << 4) & 255) |
				((data[i + 2] >>> 2) & 017));
	    		dest[j + 2] = (byte) (((data[i + 2] << 6) & 255) |
			    (data[i + 3] & 077));
		}

		if (j < dest.length) {
			dest[j] = (byte) (((data[i] << 2) & 255) |
				((data[i + 1] >>> 4) & 003));
		}

		j++;
		if (j < dest.length) {
			dest[j] = (byte) (((data[i + 1] << 4) & 255) |
				((data[i + 2] >>> 2) & 017));
		}

		return (dest);
	}
    
}
