package net.cooliang.dubbo.api.param;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;

public class UserParam implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;

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

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

	public static UserParam initByLogin(String username, String password) {
		UserParam param = new UserParam();
		param.username = username;
		param.password = password;
		return param;
	}

}
