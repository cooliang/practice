package net.cooliang.dubbo.api.result;

import java.io.Serializable;

public class MenuResult implements Serializable {

	private static final long serialVersionUID = 4390204990228143408L;

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

}
