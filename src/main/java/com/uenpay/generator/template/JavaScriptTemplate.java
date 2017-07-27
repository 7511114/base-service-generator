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
public class JavaScriptTemplate {
	
	public static void init(Configuration config, Template template) throws Exception {
		Map<String, String> map = config.map;
		String flag = map.get("pageGenerator");
		if(!"true".equals(flag)){
			System.out.println("-----------generatorConfig.xml没有配置pageGenerator节点，生成javascript失败------------");
			return;
		}
		String beanClass = map.get(XmlConstants.BEAN_CLASS);
		String beanPrivateKey = map.get(XmlConstants.BEAN_PRIVATE_KEY);
		beanPrivateKey = (null == beanPrivateKey || "".equals(beanPrivateKey)) ? "id" : beanPrivateKey;
		
		String title = map.get(XmlConstants.PAGEGENERATOR_TITLE);
		
		//js
		String targetJavaScriptFile = map.get(XmlConstants.PAGEGENERATOR_TARGETJAVASCRIPTFILE);
		//分类文件夹
		String pageFile = map.
				get(XmlConstants.JAVACONTROLLERGENERATOR_PAGEFILE);
		//目标目录
		String targetProject = map.get(XmlConstants.PAGEGENERATOR_TARGETPROJECT);
		String beanName = beanClass.substring(beanClass.lastIndexOf(".")+1);
		VelocityContext ctx = new VelocityContext();
		ctx.put(VelocityContextConstants.PAGE_FILE, pageFile);
		ctx.put(VelocityContextConstants.PAGE_PARAM, beanPrivateKey);
		ctx.put(VelocityContextConstants.PAGE_TITLE, title);
		String beanNameLow = StringUtils.toLowerCaseFirstOne(beanName);
		ctx.put(VelocityContextConstants.CLASS_NAME_LOW, beanNameLow);
		File file = FileUtils.getDirectory(targetProject, targetJavaScriptFile+"/"+pageFile);
		TemplateUtils.merge(template, ctx, new File(file, beanNameLow+".js"));
		System.out.println("-----------"+beanNameLow+".js"+"生成javascript成功------------");
	}
}
