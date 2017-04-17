package net.cooliang.dubbo.provider.service;

import net.cooliang.dubbo.provider.dto.UserDto;
import net.cooliang.dubbo.provider.vo.UserVo;

public interface UserService {

	boolean login(UserDto userDto);

	UserVo get(UserDto userDto);

}
