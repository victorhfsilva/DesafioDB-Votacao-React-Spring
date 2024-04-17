package com.db.dbpautasbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DbpautasBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbpautasBackEndApplication.class, args);
	}

}
