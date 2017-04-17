package net.cooliang.dubbo.api.facade;

import net.cooliang.dubbo.api.rpc.Result;

public interface HelloFacade {

	Result<String> sayHello(String name);

}
