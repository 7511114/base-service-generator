/**
 * 上海尤恩信息技术有限公司
 * Copyright 2017-2025 uenpay
 * project : base-service
 * package ：com.uenpay.generator.util
 * file : TemplateUtils.java
 * date ：2017年6月14日
 */
package com.uenpay.generator.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

/**
 * @author chenbing
 * Specification : 文档说明
 */
public class TemplateUtils {

	public final static void merge(Template template, VelocityContext ctx, File file) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(file);
			template.merge(ctx, writer);
			writer.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			writer.close();
		}
	}
}
