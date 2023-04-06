package com.prgrms.catpicture.dto.external;

import java.util.List;

import com.prgrms.catpicture.model.CatPicture;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public record ExternalCatPictureApiResponseDto(
	String id,
	String url,
	int width,
	int height,
	List<CatBreedVo> breeds
) {
	public CatPicture toEntity() {
		String name = breeds.get(0).getName();
		String temperament = breeds.get(0).getTemperament();
		String origin = breeds.get(0).getOrigin();
		return CatPicture.builder()
			.imageId(id)
			.url(url)
			.width(width)
			.height(height)
			.name(name)
			.temperament(temperament)
			.origin(origin)
			.build();
	}

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class CatBreedVo {
		private String name;
		private String temperament;
		private String origin;
	}
}
