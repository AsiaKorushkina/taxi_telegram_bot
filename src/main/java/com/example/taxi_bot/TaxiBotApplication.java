package com.example.taxi_bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;


@SpringBootApplication
public class TaxiBotApplication {

	public static void main(String[] args) {
		ApiContextInitializer.init();

		SpringApplication.run(TaxiBotApplication.class, args);
	}

}
