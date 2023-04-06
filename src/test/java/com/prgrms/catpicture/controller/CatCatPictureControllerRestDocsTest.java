package com.prgrms.catpicture.controller;

import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.prgrms.catpicture.TestUtils;
import com.prgrms.catpicture.dto.CatPictureDetailResponseDto;
import com.prgrms.catpicture.dto.CatPictureResponseVo;
import com.prgrms.catpicture.model.CatPicture;
import com.prgrms.catpicture.service.CatPictureService;
import com.prgrms.catpicture.service.ExternalCatPictureService;

@WebMvcTest
@AutoConfigureRestDocs
@ExtendWith(MockitoExtension.class)
@Import({CatPictureService.class, ExternalCatPictureService.class})
class CatCatPictureControllerRestDocsTest {

	private static final String API_NAME = "Cat_Pictures";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ExternalCatPictureService externalCatPictureService;

	@MockBean
	private CatPictureService catPictureService;

	@Test
	void ID를_이용하여_특정_고양이_사진을_조회할_수_있다() throws Exception {
		Long id = 1L;
		CatPictureDetailResponseDto responseDto = CatPictureDetailResponseDto.builder()
			.name("name")
			.id("id")
			.url("url")
			.width(100)
			.height(100)
			.temperament("Active")
			.origin("Seoul")
			.build();

		when(catPictureService.getCatPicture(any()))
			.thenReturn(responseDto);
		ResultActions perform = mockMvc.perform(get("/cats/{id}", id)
			.contentType(MediaType.APPLICATION_JSON)
		);

		perform
			.andExpect(status().isOk())
			.andDo(document("조회 성공 - ID를 이용하여 특정 고양이 사진 조회 성공",
				pathParameters(
					parameterWithName("id").description("조회할 이미지 id")
				),
				responseFields(
					fieldWithPath("data").type(OBJECT).description("id로 검색된 고양이 사진 입니다."),
					fieldWithPath("data.name").type(STRING).description("검색된 고양이의 품종입니다."),
					fieldWithPath("data.id").type(STRING).description("검색된 고양이 사진 id입니다."),
					fieldWithPath("data.url").type(STRING).description("검색된 고양이 사진 url입니다."),
					fieldWithPath("data.width").type(NUMBER).description("검색된 고양이 사진의 width입니다."),
					fieldWithPath("data.height").type(NUMBER).description("검색된 고양이 사진의 height입니다."),
					fieldWithPath("data.temperament").type(STRING).description("검색된 고양이 사진의 느낌입니다."),
					fieldWithPath("data.origin").type(STRING).description("검색된 고양이 사진의 기원입니다.")
				)
			));
	}

	@Test
	void 특정_품종의_고양이_사진들을_조회할_수_있다() throws Exception {
		List<CatPictureResponseVo> responseDtos = List.of(new CatPictureResponseVo("id", "url", "name"));

		when(catPictureService.getCatPicturesByName(any()))
			.thenReturn(responseDtos);
		ResultActions perform = mockMvc.perform(get("/cats/search")
			.contentType(MediaType.APPLICATION_JSON)
			.param("q", "name")
		);

		perform
			.andExpect(status().isOk())
			.andDo(document("조회 성공 - 특정 품종의 고양이 사진 조회 성공",
				queryParameters(
					parameterWithName("q").description("조회할 고양이 품종입니다. (필수로 삽입해야합니다.)")
				),
				responseFields(
					fieldWithPath("data").type(ARRAY).description("id로 검색된 고양이 사진 입니다."),
					fieldWithPath("data[].name").type(STRING).description("검색된 고양이의 품종입니다."),
					fieldWithPath("data[].id").type(STRING).description("검색된 고양이 사진 id입니다."),
					fieldWithPath("data[].url").type(STRING).description("검색된 고양이 사진 url입니다.")
				)
			));
	}

	@Test
	void 랜덤한_고양이_사진_50개를_조회할_수_있다() throws Exception {
		int limit = 50;
		List<CatPicture> responseDtos = List.of(TestUtils.createCatPicture());

		when(externalCatPictureService.loadCatPictures(limit))
			.thenReturn(responseDtos);
		ResultActions perform = mockMvc.perform(get("/cats/random50")
			.contentType(MediaType.APPLICATION_JSON)
		);

		perform
			.andExpect(status().isOk())
			.andDo(document("조회 성공 - 랜덤한 50개 고양이 사진 조회 성공",
				responseFields(
					fieldWithPath("data").type(ARRAY).description("id로 검색된 고양이 사진 입니다."),
					fieldWithPath("data[].name").type(STRING).description("검색된 고양이의 품종입니다."),
					fieldWithPath("data[].id").type(STRING).description("검색된 고양이 사진 id입니다."),
					fieldWithPath("data[].url").type(STRING).description("검색된 고양이 사진 url입니다.")
				)
			));
	}
}