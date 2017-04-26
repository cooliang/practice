package net.cooliang.dubbo.consumer.client;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import net.cooliang.dubbo.api.facade.UserFacade;
import net.cooliang.dubbo.api.param.UserParam;
import net.cooliang.dubbo.api.result.UserResult;
import net.cooliang.dubbo.api.rpc.Result;
import net.cooliang.dubbo.consumer.annotation.Loggable;
import net.cooliang.dubbo.consumer.annotation.Retryable;

@Component("userClient")
public class UserClient {

	@Resource(name = "userFacade")
	private UserFacade userFacade;

	@Loggable
	@Retryable
	public UserResult login(String username, String password) {
		UserParam param = UserParam.initByLogin(username, password);
		Result<UserResult> res = userFacade.login(param);
		if (Result.checkSuccess(res)) {
			return res.getData();
		}
		return null;
	}

}
