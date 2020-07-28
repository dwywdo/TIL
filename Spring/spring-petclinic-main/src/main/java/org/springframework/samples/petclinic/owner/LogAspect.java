package org.springframework.samples.petclinic.owner;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component // Bean으로 등록
@Aspect
public class LogAspect {

	// 로거 생성
	Logger logger = LoggerFactory.getLogger(LogAspect.class);

	// Advice Code 작성
	// 이 Annotation 주변에다가 아래의 코드를 적용하겠다. joinPoint는 그 기준점으로 사용될 수 있다
	@Around("@annotation(LogExecutionTime)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		// joinPoint? Target, 여기서는 이 Annotation이 붙어있는 메소드를 의미한다
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		Object proceed = joinPoint.proceed();
		stopWatch.stop();
		logger.info(stopWatch.prettyPrint());

		return proceed;

	}

}
