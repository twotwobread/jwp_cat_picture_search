package com.prgrms.catpicture.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prgrms.catpicture.common.ApiResponse;
import com.prgrms.catpicture.dto.CatPictureDetailResponseDto;
import com.prgrms.catpicture.dto.CatPictureResponseVo;
import com.prgrms.catpicture.service.CatPictureService;
import com.prgrms.catpicture.service.ExternalCatPictureService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cats")
public class CatPictureController {

	private static final int LIMIT = 50;
	private final CatPictureService catPictureService;
	private final ExternalCatPictureService externalCatPictureService;

	@GetMapping("/random50")
	public ResponseEntity<ApiResponse> getCatPicturesRandom50() {
		try {
			List<CatPictureResponseVo> response = externalCatPictureService.loadCatPictures(LIMIT).stream()
				.map(CatPictureResponseVo::of)
				.toList();
			return ResponseEntity.ok(
				ApiResponse.success(response)
			);
		} catch (RuntimeException e) {
			List<CatPictureResponseVo> response = catPictureService.getCatPictures50();
			return ResponseEntity.ok(
				ApiResponse.success(response)
			);
		}
	}

	@GetMapping("/search")
	public ResponseEntity<ApiResponse> getCatPicturesByCatKind(@RequestParam String q) {
		List<CatPictureResponseVo> response = catPictureService.getCatPicturesByName(q);
		return ResponseEntity.ok(
			ApiResponse.success(response)
		);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getCatPicture(@PathVariable String id) {
		CatPictureDetailResponseDto response = catPictureService.getCatPicture(id);
		return ResponseEntity.ok(
			ApiResponse.success(response)
		);
	}
}
