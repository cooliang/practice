package net.cooliang.dubbo.provider.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import net.cooliang.dubbo.provider.dto.UserDto;
import net.cooliang.dubbo.provider.entity.Role;
import net.cooliang.dubbo.provider.service.UserService;
import net.cooliang.dubbo.provider.vo.UserVo;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Override
	public boolean login(UserDto userDto) {
		return "cooliang".equals(userDto.getUsername()) && "test123".equals(userDto.getPassword());
	}

	@Override
	public UserVo get(UserDto userDto) {
		UserVo vo = new UserVo();
		vo.setUsername(userDto.getUsername());
		vo.setPassword(userDto.getPassword());
		vo.setNickName("cooliang");
		List<Role> roles = new ArrayList<>();
		Role role = new Role();
		role.setRoleId(1);
		role.setRoleName("super_admin");
		roles.add(role);
		vo.setRoles(roles);
		return vo;
	}

}
