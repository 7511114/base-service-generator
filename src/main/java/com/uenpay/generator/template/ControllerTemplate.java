/**
 * 上海尤恩信息技术有限公司
 * Copyright 2017-2025 uenpay
 * project : base-service
 * package ：com.uenpay.generator.template
 * file : ControllerTemplate.java
 * date ：2017年6月14日
 */
package com.uenpay.generator.template;

import java.io.File;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import com.uenpay.generator.config.Configuration;
import com.uenpay.generator.config.VelocityContextConstants;
import com.uenpay.generator.config.XmlConstants;
import com.uenpay.generator.util.FileUtils;
import com.uenpay.generator.util.StringUtils;
import com.uenpay.generator.util.TemplateUtils;

/**
 * @author chenbing
 * Specification : 文档说明
 */
public class ControllerTemplate {
	
	public static void init(Configuration config, Template template) throws Exception {
		Map<String, String> map = config.map;
		String flag = map.get("javaControllerGenerator");
		if(!"true".equals(flag)){
			System.out.println("-----------generatorConfig.xml没有配置javaControllerGenerator节点，生成Controller失败------------");
			return;
		}
		String beanClass = map.get(XmlConstants.BEAN_CLASS);
		String beanPrivateKey = map.get(XmlConstants.BEAN_PRIVATE_KEY);
		beanPrivateKey = (null == beanPrivateKey || "".equals(beanPrivateKey)) ? "id" : beanPrivateKey;
		String beanPrivateKeyType = map.get(XmlConstants.BEAN_PRIVATE_KEY_TYPE);
		beanPrivateKeyType = (null == beanPrivateKeyType || "".equals(beanPrivateKeyType)) ? "id" : beanPrivateKeyType;
		String targetPackage = map.get(XmlConstants.JAVACONTROLLERGENERATOR_TARGETPACKAGE);
		String targetProject = map.get(XmlConstants.JAVACONTROLLERGENERATOR_TARGETPROJECT);
		String pageFile = map.get(XmlConstants.JAVACONTROLLERGENERATOR_PAGEFILE);
		String packageService = map.get(XmlConstants.JAVASERVICEGENERATOR_TARGETPACKAGE);
		String beanName = beanClass.substring(beanClass.lastIndexOf(".")+1);
		VelocityContext ctx = new VelocityContext();
		ctx.put(VelocityContextConstants.PACKAGE, targetPackage);
		ctx.put(VelocityContextConstants.DATE_KEY, VelocityContextConstants.simpleDateFormat());
		ctx.put(VelocityContextConstants.CLASS_NAME, beanName);
		ctx.put(VelocityContextConstants.IMPORT_CLASS, beanClass);
		ctx.put(VelocityContextConstants.PRIVATE_KEY, beanPrivateKey);
		ctx.put(VelocityContextConstants.PRIVATE_KEY_UP, beanPrivateKey.substring(0, 1).toUpperCase() + beanPrivateKey.substring(1));
		ctx.put(VelocityContextConstants.PRIVATE_KEY_TYPE, beanPrivateKeyType);
		ctx.put(VelocityContextConstants.PAGE_FILE, pageFile);
		ctx.put(VelocityContextConstants.PACKAGE_SERVICE, packageService);
		ctx.put(VelocityContextConstants.AUTHOR, System.getProperty(XmlConstants.USER_NAME));
		String subStr = targetProject.substring(targetProject.indexOf("/")+1);
		ctx.put(VelocityContextConstants.PROJECT, subStr.substring(0,subStr.indexOf("/")));
		ctx.put(VelocityContextConstants.CLASS_NAME_LOW, StringUtils.toLowerCaseFirstOne(beanName));
		File file = FileUtils.getDirectory(targetProject, targetPackage);
		TemplateUtils.merge(template, ctx, new File(file, beanName+"Controller.java"));
		System.out.println("-----------"+beanName+"Controller.java"+"生成Controller成功------------");
	}
}
