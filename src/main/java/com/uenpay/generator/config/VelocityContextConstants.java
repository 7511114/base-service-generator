/**
 * 上海尤恩信息技术有限公司
 * Copyright 2017-2025 uenpay
 * project : base-service
 * package ：com.uenpay.generator.config
 * file : VelocityContextConstants.java
 * date ：2017年6月14日
 */
package com.uenpay.generator.config;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chenbing
 * Specification : 文档说明
 */
public class VelocityContextConstants {

	public static final String PACKAGE = "package";

	public static final String DATE_KEY = "date";
	
	public static final String CLASS_NAME = "className";

	public static final String IMPORT_CLASS = "importClass";
	
	public static final String PRIVATE_KEY = "privateKey";
	
	public static final String PRIVATE_KEY_UP = "privateKeyUp";
	
	public static final String PRIVATE_KEY_TYPE = "privateKeyType";

	public static final String PAGE_FILE = "pageFile";
	
	public static final String PAGE_PARAM = "param";
	
	public static final String THEME_TITLE = "title";
	
	public static final String PACKAGE_SERVICE = "packageService";
	
	public static final String AUTHOR = "author";
	
	public static final String PROJECT = "project";
	
	public static final String CLASS_NAME_LOW = "classNameLow";
	
	public static final String simpleDateFormat(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
}
