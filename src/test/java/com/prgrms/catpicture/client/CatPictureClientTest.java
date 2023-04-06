package com.prgrms.catpicture.client;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.prgrms.catpicture.dto.external.ExternalCatPictureApiResponseDto;

@SpringBootTest
class CatPictureClientTest {

	@Autowired
	CatPictureClient catPictureClient;

	@Test
	void 외부_API로부터_고양이_사진을_50개_가져올_수_있다() {
		// given
		int limit = 50;
		// when
		List<ExternalCatPictureApiResponseDto> catPicturesHavingBreeds = catPictureClient.getCatPictures50(
			limit, 1);
		// then
		assertThat(catPicturesHavingBreeds.size()).isEqualTo(limit);
		catPicturesHavingBreeds
			.forEach(i -> assertThat(i.breeds().size()).isNotEqualTo(0));
	}
}