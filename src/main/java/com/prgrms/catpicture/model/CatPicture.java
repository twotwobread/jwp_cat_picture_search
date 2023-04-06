package com.prgrms.catpicture.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CatPicture {

	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true)
	private String imageId;

	private String name;

	private String url;

	private int width;

	private int height;

	private String temperament;

	private String origin;
}
