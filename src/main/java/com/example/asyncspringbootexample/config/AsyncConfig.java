package com.example.asyncspringbootexample.config;

import java.util.concurrent.Executor;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

  private final AsyncExceptionHandler asyncExceptionHandler;

  public AsyncConfig(AsyncExceptionHandler asyncExceptionHandler) {
    this.asyncExceptionHandler = asyncExceptionHandler;
  }


  //Application level overridden method
  @Override
  public Executor getAsyncExecutor() {
    ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
    taskExecutor.setMaxPoolSize(50);
    taskExecutor.setCorePoolSize(5);
    taskExecutor.setThreadNamePrefix("thread-Async-");
    taskExecutor.setQueueCapacity(500);
    taskExecutor.initialize();
    return taskExecutor;
  }


  //Method level
  @Bean(name = "asyncExecutor")
  public Executor asyncExecutor() {
    ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
    taskExecutor.setMaxPoolSize(50);
    taskExecutor.setCorePoolSize(5);
    taskExecutor.setThreadNamePrefix("thread 2-Async-");
    taskExecutor.setQueueCapacity(500);
    taskExecutor.initialize();
    return taskExecutor;
  }

  @Override
  public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
    return asyncExceptionHandler;
  }
}
