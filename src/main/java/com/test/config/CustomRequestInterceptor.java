package com.test.config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;

@Component
public class CustomRequestInterceptor extends HandlerInterceptorAdapter {

  private static final Logger logger = LoggerFactory.getLogger(CustomRequestInterceptor.class);

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    long startTime = Instant.now().toEpochMilli();
    request.setAttribute("startTime", startTime);
    return true;
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    long startTime = (Long) request.getAttribute("startTime");
    File file = new File("log_request.txt");
    try (Writer writer = new BufferedWriter(new FileWriter(file,true))) {
      String contents = "Request URL::" + request.getRequestURL().toString() +
          ":: Time Taken=" + (Instant.now().toEpochMilli() - startTime)+"ms\n";
      writer.write(contents);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
