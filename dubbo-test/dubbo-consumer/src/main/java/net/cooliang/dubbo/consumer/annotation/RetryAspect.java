package net.cooliang.dubbo.consumer.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RetryAspect {

	private static final Logger logger = LoggerFactory.getLogger(RetryAspect.class);

	@Value(value = "${retry.times}")
	private int defaultRetryTimes;

	@Around("@annotation(retry)")
	public Object around(ProceedingJoinPoint point, Retryable retry) throws Throwable {
		int retryTimes = defaultRetryTimes;
		if (retry.retryTimes() > 0) {
			retryTimes = retry.retryTimes();
		}
		for (int i = 0; i < retryTimes; i++) {
			try {
				return point.proceed();
			} catch (Exception e) {
				logger.error("执行异常，第{}次执行retry", i + 1);
			}
		}
		return null;
	}

}
