package com.unusseat.unusseat;

import org.springframework.boot.SpringApplication;

public class TestUnusseatApplication {

	public static void main(String[] args) {
		SpringApplication.from(UnusseatApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
