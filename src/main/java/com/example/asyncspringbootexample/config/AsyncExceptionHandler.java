package com.example.asyncspringbootexample.config;

import java.lang.reflect.Method;
import java.util.Arrays;
import lombok.extern.log4j.Log4j2;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class AsyncExceptionHandler implements
    AsyncUncaughtExceptionHandler {

  @Override
  public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
    System.out.println("Method name:"+method.getName()+"()--"+Arrays.toString(objects)+" Exception mgs:"+throwable.getMessage());
  }
}
