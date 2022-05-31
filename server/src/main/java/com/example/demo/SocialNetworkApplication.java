package com.example.demo;


import com.example.demo.service.StorageService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class SocialNetworkApplication {



	public static void main(String[] args) {
		ConfigurableApplicationContext application = SpringApplication.run(SocialNetworkApplication.class, args);


		StorageService storageService = application.getBean(StorageService.class);

		System.out.println(storageService.getSongFileNames());
	}



}
