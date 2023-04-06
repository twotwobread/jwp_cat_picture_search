package com.prgrms.catpicture.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prgrms.catpicture.client.CatPictureClient;
import com.prgrms.catpicture.dto.external.ExternalCatPictureApiResponseDto;
import com.prgrms.catpicture.model.CatPicture;
import com.prgrms.catpicture.repository.CatPictureRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExternalCatPictureService {

	private static final int LIMIT = 50;
	private static final int HAS_BREEDS = 1;
	private final CatPictureRepository catPictureRepository;
	private final CatPictureClient catPictureClient;

	@Transactional
	public List<CatPicture> loadCatPictures(int limit) {
		List<ExternalCatPictureApiResponseDto> externalCatPictureApiResponseDtoList = catPictureClient.getCatPictures50(
			limit, HAS_BREEDS);
		List<CatPicture> catPictures = externalCatPictureApiResponseDtoList.stream()
			.map(ExternalCatPictureApiResponseDto::toEntity)
			.toList();
		saveLoadedData(catPictures);
		return catPictures;
	}

	private void saveLoadedData(List<CatPicture> catPictures) {
		catPictures.stream()
			.filter(catPicture -> !catPictureRepository.existsByImageId(catPicture.getImageId()))
			.forEach(catPictureRepository::save);
	}
}
