/**
 * 上海尤恩信息技术有限公司
 * Copyright 2017-2025 uenpay
 * project : base-service
 * package ：com.uenpay.generator.config.xml
 * file : ConfigurationParser.java
 * date ：2017年6月13日
 */
package com.uenpay.generator.config.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.management.modelmbean.XMLParseException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.uenpay.generator.config.Configuration;

/**
 * @author chenbing
 * Specification : 文档说明
 */
public class ConfigurationParser {

	public Configuration parseConfiguration(InputStream inputStream) throws XMLParseException, IOException{
		InputSource is = new InputSource(inputStream);
		return parseConfiguration(is);
	}
	
	private Configuration parseConfiguration(InputSource inputSource) throws XMLParseException, IOException{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = null;
			try {
				document = builder.parse(inputSource);
			} catch (SAXException e) {
				throw new XMLParseException("解析xml文件出现问题！");
			} 
			Configuration config = new Configuration();
			Element rootNode = document.getDocumentElement();
			parseConfiguration(config,rootNode);
			return config;
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void parseConfiguration(Configuration config,Element rootNode){
		NodeList nodeList = rootNode.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node childNode = nodeList.item(i);
			if (childNode.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
			String nodeName = childNode.getNodeName();
			if("bean".equals(nodeName)){
				parseAttributes(config,childNode);
				config.map.put(nodeName, "true");
			}
			if("javaServiceGenerator".equals(nodeName)){
				parseAttributes(config,childNode);
				config.map.put(nodeName, "true");
			}
			if("javaControllerGenerator".equals(nodeName)){
				parseAttributes(config,childNode);
				config.map.put(nodeName, "true");
			}
			
			if("pageGenerator".equals(nodeName)){
				parseAttributes(config,childNode);
				config.map.put(nodeName, "true");
			}
		}
	}
	
	private void parseAttributes(Configuration config,Node node){
		NamedNodeMap nnm = node.getAttributes();
		for(int i=0; i<nnm.getLength(); i++){
			Node attribute = nnm.item(i);
			config.map.put(node.getNodeName()+"-"+attribute.getNodeName(), attribute.getNodeValue());
		}
	}
}
