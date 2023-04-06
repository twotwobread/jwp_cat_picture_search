package com.prgrms.catpicture.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.prgrms.catpicture.config.HeaderConfiguration;
import com.prgrms.catpicture.dto.external.ExternalCatPictureApiResponseDto;

@FeignClient(name = "catPictureClient", url = "${external.cat-service.host}", configuration = HeaderConfiguration.class)
public interface CatPictureClient {

	@GetMapping("/images/search")
	List<ExternalCatPictureApiResponseDto> getCatPictures50(
		@RequestParam int limit,
		@RequestParam int has_breeds);

}
