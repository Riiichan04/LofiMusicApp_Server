package com.project.server;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication


public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
//		Dotenv dotenv = Dotenv.load();
//
//		System.setProperty("AWS_S3_ACCESS_KEY", dotenv.get("AWS_S3_ACCESS_KEY"));
//		System.setProperty("AWS_S3_SECRET_KEY", dotenv.get("AWS_S3_SECRET_KEY"));
//		System.setProperty("AWS_S3_REGION", dotenv.get("AWS_S3_REGION"));
//		System.setProperty("AWS_S3_BUCKET_NAME", dotenv.get("AWS_S3_BUCKET_NAME"));
//		System.setProperty("DB_URI", dotenv.get("DB_URI"));
//		System.setProperty("DB_NAME", dotenv.get("DB_NAME"));
//
//		new SpringApplicationBuilder(ServerApplication.class)
//				.properties("spring.config.name=application") // optional
//				.build()
//				.run(args);
	}

}
