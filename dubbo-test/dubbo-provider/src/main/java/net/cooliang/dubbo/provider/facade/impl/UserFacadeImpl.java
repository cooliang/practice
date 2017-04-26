package net.cooliang.dubbo.provider.facade.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

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
			UserResult userRes = JSON.parseObject(JSON.toJSONString(userVo), UserResult.class);
			return Result.createResult(userRes);
		}
		return Result.createFail("login_fail", "登录验证失败");
	}

}
