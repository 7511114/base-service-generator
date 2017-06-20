/**
 * 上海尤恩信息技术有限公司
 * Copyright 2017-2025 uenpay
 * project : base-service
 * package ：com.uenpay.generator.util
 * file : FileUtils.java
 * date ：2017年6月14日
 */
package com.uenpay.generator.util;

import java.io.File;
import java.util.StringTokenizer;

/**
 * @author chenbing
 * Specification : 文档说明
 */
public final class FileUtils {

	public final static File getDirectory(String targetProject, String targetPackage)
			throws Exception {

		File project = new File(targetProject);
		if (!project.isDirectory()) {
			throw new Exception(targetProject);
		}

		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(targetPackage, "."); 
		while (st.hasMoreTokens()) {
			sb.append(st.nextToken());
			sb.append(File.separatorChar);
		}

		File directory = new File(project, sb.toString());
		if (!directory.isDirectory()) {
			boolean rc = directory.mkdirs();
			if (!rc) {
				throw new Exception(directory.getAbsolutePath());
			}
		}

		return directory;
	}

}
