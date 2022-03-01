package com.educationalplatform.core;

import com.educationalplatform.core.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
@RestController
public class CoreApplication {

	private final AtomicLong counter = new AtomicLong();

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}

	@GetMapping("/hello")
	@CrossOrigin(origins = "http://localhost:4200")
	public User hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new User(name, "Ritz");
	}

}