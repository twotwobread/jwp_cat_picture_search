package com.prgrms.catpicture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CatPictureApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatPictureApplication.class, args);
	}

}
