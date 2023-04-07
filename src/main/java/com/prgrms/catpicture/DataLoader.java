package com.prgrms.catpicture;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.prgrms.catpicture.service.ExternalCatPictureService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

	private static final int MAX_LIMIT = 100;
	private final ExternalCatPictureService externalCatPictureService;

	@Override
	public void run(String... args) {
		try {
			externalCatPictureService.loadCatPictures(MAX_LIMIT);
		} catch (RuntimeException e) {
			log.info(e.getMessage());
		}
	}
}
