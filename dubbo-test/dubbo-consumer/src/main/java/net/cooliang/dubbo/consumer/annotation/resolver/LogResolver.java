package net.cooliang.dubbo.consumer.annotation.resolver;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.rpc.RpcContext;

@Component
@Aspect
public class LogResolver {

	private static final Logger logger = LoggerFactory.getLogger(RetryResolver.class);

	@Pointcut("@annotation(net.cooliang.dubbo.consumer.annotation.Loggable)")
	public void pointCut() {
	}

	@Before("pointCut()")
	public void before() {
		MDC.put("traceId", "12345679");
		logger.info("before log..");
		RpcContext.getContext().setAttachment("traceId", MDC.get("traceId"));
	}

}
