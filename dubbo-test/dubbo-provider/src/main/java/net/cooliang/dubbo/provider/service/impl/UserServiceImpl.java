package net.cooliang.dubbo.provider.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import net.cooliang.dubbo.provider.dto.UserDto;
import net.cooliang.dubbo.provider.entity.Menu;
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
		UserVo userVo = new UserVo();
		userVo.setUsername(userDto.getUsername());
		userVo.setPassword(userDto.getPassword());
		userVo.setNickName("cooliang");

		Role role = new Role();
		role.setRoleId(1);
		role.setRoleName("超级管理员");
		userVo.setRole(role);

		List<Menu> menus = new ArrayList<>();
		Menu menu1 = new Menu();
		menu1.setMenuId(1);
		menu1.setMenuName("baidu");
		menu1.setMenuUrl("https://www.baidu.com");
		menus.add(menu1);
		Menu menu2 = new Menu();
		menu2.setMenuId(1);
		menu2.setMenuName("sohu");
		menu2.setMenuUrl("http://v2.sohu.com/");
		menus.add(menu2);
		userVo.setMenus(menus);

		return userVo;
	}

}
