package net.cooliang.dubbo.api.result;

import java.io.Serializable;
import java.util.List;

public class UserResult implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private String nickName;
	private RoleResult role;
	private List<MenuResult> menus;

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

	public RoleResult getRole() {
		return role;
	}

	public void setRole(RoleResult role) {
		this.role = role;
	}

	public List<MenuResult> getMenus() {
		return menus;
	}

	public void setMenus(List<MenuResult> menus) {
		this.menus = menus;
	}

}
