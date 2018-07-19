package com.oak.pbn.service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FreemarkerService {

	@Autowired
	private Configuration freemarkerConfiguration;

	public String process(String name, Map<String, Object> params) {
		Template template;
		try {
			template = freemarkerConfiguration.getTemplate(name);
		} catch (Exception ex) {
			log.error("Can not find template [{}].", name, ex);
			throw new RuntimeException("Can not find template " + name, ex);
		}
		try (StringWriter sw = new StringWriter()) {
			try {
				template.process(params, sw);
				return sw.toString();
			} catch (TemplateException ex) {
				log.error("Error while processing template.", ex);
				throw new RuntimeException("Error while processing template.");
			}
		} catch (IOException ex) {
			log.error("IO error while processing template.", ex);
			throw new RuntimeException("IO error while processing template.");
		}
	}

}
