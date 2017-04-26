package net.cooliang.dubbo.api.rpc.enums;

public enum ResultStatus {
	SUCCESS(0, "success"), FAIL(1, "fail"), ERROR(-1, "error");

	private int code;
	private String msg;

	private ResultStatus(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

}
