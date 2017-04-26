package net.cooliang.dubbo.provider.facade.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.rpc.RpcContext;

import net.cooliang.dubbo.api.facade.HelloFacade;
import net.cooliang.dubbo.api.rpc.Result;
import net.cooliang.dubbo.provider.service.HelloService;

@Component("helloFacade")
public class HelloFacadeImpl implements HelloFacade {

	private static final Logger logger = LoggerFactory.getLogger(HelloFacadeImpl.class);

	@Autowired
	private HelloService helloService;

	@Override
	public Result<String> sayHello(String name) {
		MDC.put("traceId", RpcContext.getContext().getAttachment("traceId"));
		logger.info("begin sayHello...");
		return Result.createResult(helloService.sayHello(name));
	}

}
