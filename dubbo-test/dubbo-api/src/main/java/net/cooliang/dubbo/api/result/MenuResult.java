package net.cooliang.dubbo.api.result;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;

public class MenuResult implements Serializable {

	private static final long serialVersionUID = 1L;

	private int menuId;
	private String menuName;
	private String menuUrl;

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}
