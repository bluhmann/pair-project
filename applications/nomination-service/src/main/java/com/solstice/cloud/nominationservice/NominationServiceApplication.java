package com.solstice.cloud.nominationservice;

import com.solstice.cloud.nominationservice.repositories.NominationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NominationServiceApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private NominationRepository repository;

	public NominationServiceApplication(NominationRepository repository) {
		this.repository = repository;
	}

	public static void main(String[] args) {
		SpringApplication.run(NominationServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
	}
}
