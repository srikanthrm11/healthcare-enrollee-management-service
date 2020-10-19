package com.healthcare.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration;

@SpringBootApplication(exclude = { SolrAutoConfiguration.class })
public class HealthcareEnrollApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthcareEnrollApplication.class, args);
	}

}
