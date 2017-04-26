package net.cooliang.dubbo.api.facade;

import net.cooliang.dubbo.api.param.UserParam;
import net.cooliang.dubbo.api.result.UserResult;
import net.cooliang.dubbo.api.rpc.Result;

public interface UserFacade {

	Result<UserResult> login(UserParam userParam);

}
