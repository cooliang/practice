package net.cooliang.dubbo.provider.vo;

import java.util.List;

import net.cooliang.dubbo.provider.entity.Menu;
import net.cooliang.dubbo.provider.entity.Role;

public class UserVo {

	private String username;
	private String password;

	private String nickName;
	private List<Role> roles;
	private List<Menu> menus;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

}
