package com.poc.byondmap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
public class ByondmapApplication {
	public static void main(String[] args) {
		SpringApplication.run(ByondmapApplication.class, args);
	}

}
