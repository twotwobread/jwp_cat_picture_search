package com.prgrms.catpicture.controller;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
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
import com.prgrms.catpicture.dto.CatPictureResponseVo;
import com.prgrms.catpicture.model.CatPicture;
import com.prgrms.catpicture.repository.CatPictureRepository;

@SpringBootTest
public class GetCatPicturesByNameTest {

	private final ObjectMapper mapper = new ObjectMapper();
	@MockBean
	CommandLineRunner mockCommandLineRunner;
	@Autowired
	CatPictureController catPictureController;
	@Autowired
	CatPictureRepository catPictureRepository;

	@Test
	@Transactional
	void 특정_품종의_고양이_사진을_조회할_수_있다() throws JsonProcessingException {
		// given
		int size = 100;
		String name = TestUtils.NAME;
		for (int i = 1; i <= 100; i++) {
			CatPicture catPicture = TestUtils.createCatPicture(String.valueOf(i));
			catPictureRepository.save(catPicture);
		}

		// when
		ResponseEntity<ApiResponse> response = catPictureController.getCatPicturesByCatKind(name);
		String actualJson = mapper.writeValueAsString(Objects.requireNonNull(response.getBody()).data());
		List<CatPictureResponseVo> apiResponse = Arrays.asList(
			mapper.readValue(actualJson, CatPictureResponseVo[].class));

		// then
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(apiResponse.size()).isEqualTo(size);
		apiResponse.forEach(i -> assertThat(i.name()).isEqualTo(name));
	}
}
