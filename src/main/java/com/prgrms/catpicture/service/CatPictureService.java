package com.prgrms.catpicture.service;

import static com.prgrms.catpicture.exception.ExceptionMessage.*;

import java.util.List;

import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prgrms.catpicture.client.CatPictureClient;
import com.prgrms.catpicture.dto.CatPictureDetailResponseDto;
import com.prgrms.catpicture.dto.CatPictureResponseVo;
import com.prgrms.catpicture.model.CatPicture;
import com.prgrms.catpicture.repository.CatPictureRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CatPictureService {

	private static final int LIMIT = 50;
	private static final int HAS_BREEDS = 1;
	private final CatPictureRepository catPictureRepository;
	private final CatPictureClient catPictureClient;

	public CatPictureDetailResponseDto getCatPicture(String id) {
		CatPicture catPicture = catPictureRepository.findByImageId(id)
			.orElseThrow(() -> new JpaObjectRetrievalFailureException(
				new EntityNotFoundException(NOT_FOUND_PICTURE.getMessage())
			));
		return CatPictureDetailResponseDto.of(catPicture);
	}

	public List<CatPictureResponseVo> getCatPicturesByName(String name) {
		List<CatPicture> allByName = catPictureRepository.findAllByName(name);
		return allByName.stream()
			.map(CatPictureResponseVo::of)
			.toList();
	}

	public List<CatPictureResponseVo> getCatPictures50() {
		List<CatPicture> random50 = catPictureRepository.findRandom50();
		return random50.stream()
			.map(CatPictureResponseVo::of)
			.toList();
	}
}
