package com.cx.wxs.utils;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;



/**
 * @author 陈义
 * @date   2016-8-6 下午5:24:10
 */
public class PropertiesUtils {
	
	private static Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);

	public final static String UPLOAD_PATH="upload.path";
	
	private static Properties properties = null;
	
	public static String getString(String key) {
		if (properties == null) {
			properties = PropertiesUtils
					.loadPropertiesFileFromClassPath("application.properties");
		}
		return properties.getProperty(key);
	}
	
	public static Properties loadPropertiesFileFromClassPath(String path) {
		Properties config = new Properties();
		try {
			config.load(new ClassPathResource(path).getInputStream());
		} catch (IOException e) {
			logger.error("load properties file error: {}", e.getMessage());
		}
		return config;
	}
}
