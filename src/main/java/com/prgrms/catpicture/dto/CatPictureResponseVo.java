package com.prgrms.catpicture.dto;

import com.prgrms.catpicture.model.CatPicture;

public record CatPictureResponseVo(
	String id,
	String url,
	String name
) {
	public static CatPictureResponseVo of(CatPicture catPicture) {
		return new CatPictureResponseVo(
			catPicture.getImageId(),
			catPicture.getUrl(),
			catPicture.getName()
		);
	}
}
