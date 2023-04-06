package com.prgrms.catpicture.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.prgrms.catpicture.model.CatPicture;

public interface CatPictureRepository extends JpaRepository<CatPicture, Long> {
	Optional<CatPicture> findByImageId(String imageId);

	boolean existsByImageId(String imageId);

	List<CatPicture> findAllByName(String name);

	@Query(value = "select id, height, image_id, name, origin, temperament, url, width "
		+ "from cat_picture as c order by RAND() limit 50",
		nativeQuery = true)
	List<CatPicture> findRandom50();
}
