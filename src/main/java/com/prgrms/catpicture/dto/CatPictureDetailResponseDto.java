package com.prgrms.catpicture.dto;

import com.prgrms.catpicture.model.CatPicture;

import lombok.Builder;

@Builder
public record CatPictureDetailResponseDto(
	String name,
	String id,
	String url,
	int width,
	int height,
	String temperament,
	String origin
) {
	public static CatPictureDetailResponseDto of(CatPicture catPicture) {
		return CatPictureDetailResponseDto.builder()
			.name(catPicture.getName())
			.id(catPicture.getImageId())
			.url(catPicture.getUrl())
			.width(catPicture.getWidth())
			.height(catPicture.getHeight())
			.temperament(catPicture.getTemperament())
			.origin(catPicture.getOrigin())
			.build();
	}
}
