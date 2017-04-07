package net.cooliang.dubbo.api.result;

import java.io.Serializable;

public class Result<T> implements Serializable {

	private static final long serialVersionUID = 2039083426825713916L;
	
	public static final int CODE_OK = 0;

	private int code;
	private String errMsg;
	private T data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public static <T> Result<T> createError(int errCode, String errMsg) {
		Result<T> result = new Result<T>();
		result.setCode(errCode);
		result.setErrMsg(errMsg);
		return result;
	}

	public static <T> Result<T> createResult(T data) {
		Result<T> result = new Result<T>();
		result.setCode(CODE_OK);
		result.setData(data);
		return result;
	}

}
