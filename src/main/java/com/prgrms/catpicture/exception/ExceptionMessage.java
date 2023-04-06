package com.prgrms.catpicture.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {
	// JpaObjectRetrievalFailureException
	NOT_FOUND_PICTURE("해당 ID를 가진 사진이 존재하지 않습니다."),
	// NullPointException
	CAN_NOT_NULL("해당 요청 시 null 값이 들어올 수 없습니다.");

	private final String message;
}
