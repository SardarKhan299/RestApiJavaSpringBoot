package com.stella.restApiApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sound.sampled.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootApplication
@EnableTransactionManagement
public class RestApiAppApplication {
	private static TargetDataLine mic;

	public static void main(String[] args) {
		SpringApplication.run(RestApiAppApplication.class, args);
	}




	@Bean
	public PlatformTransactionManager getPlatformManager(MongoDatabaseFactory dbFactory){
		return new MongoTransactionManager(dbFactory);
	}



}
