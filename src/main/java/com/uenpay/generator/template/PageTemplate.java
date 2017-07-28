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
public class PageTemplate {
	
	public static void initPageData(Configuration config, Template template) throws Exception {
		Map<String, String> map = config.map;
		String flag = map.get("pageGenerator");
		if(!"true".equals(flag)){
			System.out.println("-----------generatorConfig.xml没有配置pageGenerator节点，生成pageData失败------------");
			return;
		}
		String beanClass = map.get(XmlConstants.BEAN_CLASS);
		//页面
		String targetPageFile = map.get(XmlConstants.PAGEGENERATOR_TARGETPAGEFILE);
		//分类文件夹
		String pageFile = map.get(XmlConstants.JAVACONTROLLERGENERATOR_PAGEFILE);
		String beanTitle = map.get(XmlConstants.BEAN_TITLE);
		beanTitle = null == beanTitle ? "" : beanTitle;
		//目标目录
		String targetProject = map.get(XmlConstants.PAGEGENERATOR_TARGETPROJECT);
		String beanName = beanClass.substring(beanClass.lastIndexOf(".")+1);
		VelocityContext ctx = new VelocityContext();
		ctx.put(VelocityContextConstants.PAGE_FILE, pageFile);
		String beanNameLow = StringUtils.toLowerCaseFirstOne(beanName);
		ctx.put(VelocityContextConstants.CLASS_NAME_LOW, beanNameLow);
		ctx.put(VelocityContextConstants.THEME_TITLE, beanTitle);
		File file = FileUtils.getDirectory(targetProject, targetPageFile+"/"+pageFile);
		TemplateUtils.merge(template, ctx, new File(file, beanNameLow+"Page.vm"));
		System.out.println("-----------"+beanNameLow+"Page.vm"+"生成pageData成功------------");
	}
	
	public static void initPageEdit(Configuration config, Template template) throws Exception {
		Map<String, String> map = config.map;
		String flag = map.get("pageGenerator");
		if(!"true".equals(flag)){
			System.out.println("-----------generatorConfig.xml没有配置pageGenerator节点，生成pageEdit失败------------");
			return;
		}
		String beanClass = map.get(XmlConstants.BEAN_CLASS);
		//页面
		String targetPageFile = map.get(XmlConstants.PAGEGENERATOR_TARGETPAGEFILE);
		String beanTitle = map.get(XmlConstants.BEAN_TITLE);
		beanTitle = null == beanTitle ? "" : beanTitle;
		//分类文件夹
		String pageFile = map.get(XmlConstants.JAVACONTROLLERGENERATOR_PAGEFILE);
		//目标目录
		String targetProject = map.get(XmlConstants.PAGEGENERATOR_TARGETPROJECT);
		String beanName = beanClass.substring(beanClass.lastIndexOf(".")+1);
		VelocityContext ctx = new VelocityContext();
		ctx.put(VelocityContextConstants.PAGE_FILE, pageFile);
		ctx.put(VelocityContextConstants.THEME_TITLE, beanTitle);
		String beanNameLow = StringUtils.toLowerCaseFirstOne(beanName);
		ctx.put(VelocityContextConstants.CLASS_NAME_LOW, beanNameLow);
		File file = FileUtils.getDirectory(targetProject, targetPageFile+"/"+pageFile);
		TemplateUtils.merge(template, ctx, new File(file, beanNameLow+"Edit.vm"));
		System.out.println("-----------"+beanNameLow+"Edit.vm"+"生成pageEdit成功------------");
	}
	
	/*private List<String> initMap(String beanClass){
		List<String> list = new ArrayList<String>();
		try {
			Class clazz = Class.forName(beanClass);
			Field[] field = clazz.getDeclaredFields(); // 获取实体类的所有属性，
			for (int j = 0; j < field.length; j++) { // 遍历所有属性
                String name = field[j].getName(); // 获取属性的名字
                list.add(name);
			}
			return list;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}*/
}
