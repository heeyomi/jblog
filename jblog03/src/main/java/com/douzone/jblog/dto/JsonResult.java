package com.douzone.jblog.dto;

public class JsonResult {
	private String result;
	private Object data;
	private String message;

	public JsonResult() {
	}

	public JsonResult(Object data) {
		result = "success";
		this.data = data;
		System.out.println(data);
	}

	public JsonResult(String message) {
		result = "fail";
		this.message = message;
	}

	public String getResult() {
		return result;
	}

	public Object getData() {
		return data;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "JsonReult [result=" + result + ", data=" + data + ", message=" + message + "]";
	}
	
	public static JsonResult success(Object data) {
		return new JsonResult(data);
	}

	public static JsonResult fail(String message) {
		return new JsonResult(message);
	}
}
