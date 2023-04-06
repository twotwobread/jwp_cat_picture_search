package com.prgrms.catpicture.common;

public record ApiResponse(Object data) {

	public static ApiResponse success(Object data) {
		return new ApiResponse(data);
	}

	public static ApiResponse fail(String message) {
		return new ApiResponse(message);
	}
}
