package net.cooliang.dubbo.provider.facade.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.cooliang.dubbo.api.facade.UserFacade;
import net.cooliang.dubbo.api.param.UserParam;
import net.cooliang.dubbo.api.result.UserResult;
import net.cooliang.dubbo.api.rpc.Result;
import net.cooliang.dubbo.provider.dto.UserDto;
import net.cooliang.dubbo.provider.service.UserService;
import net.cooliang.dubbo.provider.vo.UserVo;

@Component("userFacade")
public class UserFacadeImpl implements UserFacade {

	@Autowired
	private UserService userService;

	@Override
	public Result<UserResult> login(UserParam userParam) {
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userParam, userDto);
		if (userService.login(userDto)) {
			UserVo userVo = userService.get(userDto);
			UserResult userRes = new UserResult();
			BeanUtils.copyProperties(userVo, userRes); // 存在类型擦除导致序列化异常
			return Result.createResult(userRes);
		}
		return Result.createFail("login_fail", "登录验证失败");
	}

}
