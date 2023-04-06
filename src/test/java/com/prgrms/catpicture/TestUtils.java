package com.prgrms.catpicture;

import com.prgrms.catpicture.model.CatPicture;

public class TestUtils {

	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String URL = "url";
	public static final int WIDTH = 100;
	public static final int HEIGHT = 100;
	public static final String TEMPERAMENT = "url";
	public static final String ORIGIN = "url";

	public static CatPicture createCatPicture() {
		return CatPicture.builder()
			.imageId(ID)
			.name(NAME)
			.url(URL)
			.width(WIDTH)
			.height(HEIGHT)
			.temperament(TEMPERAMENT)
			.origin(ORIGIN)
			.build();
	}

	public static CatPicture createCatPicture(String id) {
		return CatPicture.builder()
			.imageId(id)
			.name(NAME)
			.url(URL)
			.width(WIDTH)
			.height(HEIGHT)
			.temperament(TEMPERAMENT)
			.origin(ORIGIN)
			.build();
	}
}
