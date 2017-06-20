/**
 * 上海尤恩信息技术有限公司
 * Copyright 2017-2025 uenpay
 * project : base-service
 * package ：com.uenpay.generator.util
 * file : StringUtils.java
 * date ：2017年6月14日
 */
package com.uenpay.generator.util;

/**
 * @author chenbing
 * Specification : 文档说明
 */
public class StringUtils {

	
	//首字母转小写
	public static String toLowerCaseFirstOne(String s){
	  if(Character.isLowerCase(s.charAt(0)))
	    return s;
	  else
	    return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
	}
}
