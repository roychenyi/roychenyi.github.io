package com.cx.wxs.util;
/** 
 * 
 * @author sx 
 * @date 20140802 09：17 	 */  
import java.security.MessageDigest;

/** 
 * 字符串Md5加密类
 * @author 陈义
 * @date 2015-11-24 15:43
 * @说明：以下加密的方法，命名生成方式如需改变不要直接修改方法
 * 重新写一个方法，改写要实现的功能，并写清楚注释
 */  
public class MD5Tool {
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
		"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

// 十六进制下数字到字符的映射数组

/** 把inputString加密 */
public static String createPassword(String inputString) {
	return encodeByMD5(inputString);
}

/**
 * 验证输入的密码是否正确
 * 
 * @param password
 *            真正的密码（加密后的真密码）
 * @param inputString
 *            输入的字符串
 * @return 验证结果，boolean类型
 */
public static boolean authenticatePassword(String password,
		String inputString) {
	if (password.equals(encodeByMD5(inputString))) {
		return true;
	} else {
		return false;
	}
}

/** 
 * 对字符串进行MD5编码 
 * */
private static String encodeByMD5(String originString) {
	if (originString != null) {
		try {
			// 创建具有指定算法名称的信息摘要
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
			byte[] results = md.digest(originString.getBytes());
			// 将得到的字节数组变成字符串返回
			String resultString = byteArrayToHexString(results);
			return resultString.toUpperCase();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	return null;
}

/**
 * 轮换字节数组为十六进制字符串
 * 
 * @param b
 *            字节数组
 * @return 十六进制字符串
 */
private static String byteArrayToHexString(byte[] b) {
	StringBuffer resultSb = new StringBuffer();
	for (int i = 0; i < b.length; i++) {
		resultSb.append(byteToHexString(b[i]));
	}
	return resultSb.toString();
}

/**
 * 将一个字节转化成十六进制形式的字符串
 */
private static String byteToHexString(byte b) {
	int n = b;
	if (n < 0)
		n = 256 + n;
	int d1 = n / 16;
	int d2 = n % 16;
	return hexDigits[d1] + hexDigits[d2];
}

public static void main(String[] args) {
	String password = MD5Tool.createPassword("123456");
	System.out.println("对123456用MD5摘要后的字符串：" + password);
	String inputString = "51cto51cto";
	System.out.println("51cto51cto与密码匹配？"
			+ MD5Tool.authenticatePassword(password, inputString));
	inputString = "123456";
	System.out.println("51cto与密码匹配？"
			+ MD5Tool.authenticatePassword(password, inputString));
}

}
