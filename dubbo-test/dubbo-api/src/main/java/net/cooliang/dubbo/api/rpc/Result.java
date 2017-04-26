package net.cooliang.dubbo.api.rpc;

import java.io.Serializable;

import net.cooliang.dubbo.api.rpc.enums.ResultStatus;

public class Result<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private int code;
	private String msg;
	private String subCode;
	private String subMsg;
	private T data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getSubCode() {
		return subCode;
	}

	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}

	public String getSubMsg() {
		return subMsg;
	}

	public void setSubMsg(String subMsg) {
		this.subMsg = subMsg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public static <T> Result<T> createFail(String errCode, String errMsg) {
		Result<T> result = new Result<T>();
		result.setCode(ResultStatus.FAIL.getCode());
		result.setMsg(ResultStatus.FAIL.getMsg());
		result.setSubCode(errCode);
		result.setSubMsg(errMsg);
		return result;
	}

	public static <T> Result<T> createError(String errCode, String errMsg) {
		Result<T> result = new Result<T>();
		result.setCode(ResultStatus.ERROR.getCode());
		result.setMsg(ResultStatus.ERROR.getMsg());
		result.setSubCode(errCode);
		result.setSubMsg(errMsg);
		return result;
	}

	public static <T> Result<T> createResult(T data) {
		Result<T> result = new Result<T>();
		result.setCode(ResultStatus.SUCCESS.getCode());
		result.setMsg(ResultStatus.SUCCESS.getMsg());
		result.setData(data);
		return result;
	}

	public static boolean checkSuccess(Result<?> res) {
		return res != null && res.getCode() == ResultStatus.SUCCESS.getCode();
	}

}
