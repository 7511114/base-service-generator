/**
 * 上海尤恩信息技术有限公司
 * Copyright 2017-2025 uenpay
 * project : base-service
 * package ：com.uenpay.generator
 * file : VelocityGenerator.java
 * date ：2017年6月13日
 */
package com.uenpay.generator;

import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import com.uenpay.generator.config.Configuration;
import com.uenpay.generator.template.ControllerTemplate;
import com.uenpay.generator.template.JavaScriptTemplate;
import com.uenpay.generator.template.PageTemplate;
import com.uenpay.generator.template.ServiceTemplate;

/**
 * @author chenbing Specification : 文档说明
 */
public class VelocityGenerator {

	private VelocityEngine velocityEngine;

	private String encoding = "UTF-8";
	
	public void init(Configuration config) throws Exception {
		initService(config);
		initController(config);
		initPageData(config);
		initJavaScript(config);
	}
	
	public void initService(Configuration config) throws Exception{
		beanValidate(config);
		Template template = getTemplate("service-velocity.vm");
		ServiceTemplate.initTemplate(config, template);
		Template templateImpl = getTemplate("service-impl-velocity.vm");
		ServiceTemplate.initTemplateImpl(config, templateImpl);
	}
	
	public void initController(Configuration config) throws Exception{
		beanValidate(config);
		Template templateController = getTemplate("controller-velocity.vm");
		ControllerTemplate.init(config, templateController);
	}
	
	public void initPageData(Configuration config) throws Exception{
		beanValidate(config);
		Template templatePage = getTemplate("pages-velocity.vm");
		PageTemplate.initPageData(config, templatePage);
		Template templatePageEdit = getTemplate("pages-edit-velocity.vm");
		PageTemplate.initPageEdit(config, templatePageEdit);
	}
	
	public void initJavaScript(Configuration config) throws Exception{
		beanValidate(config);
		Template templatePage = getTemplate("pages-javascript-velocity.vm");
		JavaScriptTemplate.init(config, templatePage);
	}

	private synchronized void initEngine() {
		if(null != velocityEngine){
			return;
		}
		velocityEngine = new VelocityEngine();
		velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER,
				"classpath");
		velocityEngine.setProperty("classpath.resource.loader.class",
				ClasspathResourceLoader.class.getName());
		velocityEngine.init();
	}

	private Template getTemplate(String name) {
		initEngine();
		Template template = velocityEngine.getTemplate(name, encoding);
		return template;
	}
	
	private void beanValidate(Configuration config) throws Exception{
		Map<String, String> map = config.map;
		String beanClass = map.get("bean");
		if(null == beanClass || "".equals(beanClass)){
			throw new Exception("-----------generatorConfig.xml没有配置bean节点,请先配置-----------");
		}
	}

}
