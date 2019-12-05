package com.pengwei.zhou.app.utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class CorsFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(
      ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletResponse httpResponse = (HttpServletResponse) response;
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    String origin = "null";
    if (!StringUtils.isEmpty(origin)) {
      String[] origins = origin.split(",");
      List<String> allowedOrigins = Arrays.asList(origins);
      if (allowedOrigins.contains(httpRequest.getHeader("Origin"))) {
        httpResponse.setHeader("Access-Control-Allow-Origin", httpRequest.getHeader("Origin"));
      }
    }
    httpResponse.setHeader("Access-Control-Allow-Methods", httpRequest.getMethod());
    httpResponse.setHeader("Access-Control-Max-Age", "3600");
    httpResponse.setHeader("Access-Control-Allow-Headers",
        httpRequest.getHeader("Access-Control-Request-Headers"));
    httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {

  }
}
