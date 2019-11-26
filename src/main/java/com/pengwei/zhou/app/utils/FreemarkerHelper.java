package com.pengwei.zhou.app.utils;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FreemarkerHelper {

  private static final String DEFAULT_CHARSET = "UTF-8";
  private static final Configuration DEFAULT_FREEMARKER_CONFIGURATION = configuration();

  private static Configuration configuration() {
    Configuration configuration = new Configuration(
        Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
    configuration.setDefaultEncoding(DEFAULT_CHARSET);
    ClassTemplateLoader templateLoader = new ClassTemplateLoader(
        Thread.currentThread().getContextClassLoader(), "template");
    configuration.setTemplateLoader(templateLoader);
    return configuration;
  }

  public static String process(String name, Map params) {
    StringWriter stringWriter = new StringWriter();
    BufferedWriter writer = new BufferedWriter(stringWriter);
    process(name, params, writer);
    return stringWriter.toString();
  }

  public static void process(String name, Map params, Writer writer) {
    try {
      Template template = DEFAULT_FREEMARKER_CONFIGURATION.getTemplate(name, DEFAULT_CHARSET);
      template.process(params, writer);
    } catch (IOException | TemplateException e) {
      log.error(e.getMessage(), e);
      throw new IllegalStateException(e.getMessage(), e);
    }
  }
}
