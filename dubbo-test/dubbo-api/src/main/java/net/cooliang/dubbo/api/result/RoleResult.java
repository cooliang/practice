package net.cooliang.dubbo.api.result;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;

public class RoleResult implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String roleName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}
