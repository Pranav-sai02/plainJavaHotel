package com.neoteric.plainJavaHotel.async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class PlainJavaHotelApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlainJavaHotelApplication.class, args);
	}

}
