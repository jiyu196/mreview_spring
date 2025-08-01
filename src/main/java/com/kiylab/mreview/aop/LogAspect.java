package com.kiylab.mreview.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Log4j2
@Component
public class LogAspect {
  // 파라미터는 joinpoint여야함. 그 안에 호출하는 시그니쳐랑 파라미터들이 있음
  @Before("execution(* *..*.ReviewController.*(..))")
  public void beforeLog(JoinPoint joinPoint) {
    log.info("-------------------------" + joinPoint.getSignature().getName());
    Arrays.stream(joinPoint.getArgs()).forEach(log::info);
  }
}
