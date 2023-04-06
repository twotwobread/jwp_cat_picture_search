package com.prgrms.catpicture.controller;

import static org.assertj.core.api.Assertions.*;

import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prgrms.catpicture.TestUtils;
import com.prgrms.catpicture.common.ApiResponse;
import com.prgrms.catpicture.dto.CatPictureDetailResponseDto;
import com.prgrms.catpicture.model.CatPicture;
import com.prgrms.catpicture.repository.CatPictureRepository;

@SpringBootTest
public class GetCatCatPictureTest {

	private final ObjectMapper mapper = new ObjectMapper();
	@MockBean
	CommandLineRunner mockCommandLineRunner;
	@Autowired
	CatPictureController catPictureController;
	@Autowired
	CatPictureRepository catPictureRepository;

	@Test
	@Transactional
	void ID를_이용하여_고양이_사진을_조회할_수_있다() throws JsonProcessingException {
		// given
		CatPicture catPicture = TestUtils.createCatPicture();
		CatPictureDetailResponseDto expected = CatPictureDetailResponseDto.of(catPicture);
		catPictureRepository.save(catPicture);
		String id = TestUtils.ID;

		// when
		ResponseEntity<ApiResponse> response = catPictureController.getCatPicture(id);
		String actualJson = mapper.writeValueAsString(Objects.requireNonNull(response.getBody()).data());
		CatPictureDetailResponseDto apiResponse = mapper.readValue(actualJson, CatPictureDetailResponseDto.class);

		// then
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(apiResponse).usingRecursiveComparison().isEqualTo(expected);
	}
}
