package com.solstice.cloud.nominationservice;

import com.solstice.cloud.nominationservice.entities.Nomination;
import com.solstice.cloud.nominationservice.repositories.NominationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
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
		Nomination nomination1 = new Nomination(10001, 10000, new Date(1018, 7, 14), "principles", "he was good");
		Nomination nomination2 = new Nomination(10001, 10000, new Date(1018, 7, 15), "principles", "he was good");
		Nomination nomination3 = new Nomination(10001, 10000, new Date(1018, 7, 20), "principles", "he was good");
		Nomination nomination4 = new Nomination(10001, 10000, new Date(1018, 5, 14), "principles", "he was good");

		this.repository.addNomination(nomination1);
		this.repository.addNomination(nomination2);
		this.repository.addNomination(nomination3);
		this.repository.addNomination(nomination4);

		repository.nominationsByWeek(new Date());

		List<Nomination> nominations = repository.nominationsForEmployee(10001);
		logger.info("Nominations for Employee Number 10001 -> {}", nominations);

		List<Nomination> nominationsForAugust = repository.nominationsForRange(new Date(1018, 7, 1), new Date(1018, 7, 31));
		logger.info("Nominations for August -> {}", nominationsForAugust);

		List<Nomination> nominationsForWeek = repository.nominationsByWeek(new Date(1018, 7, 16));
		logger.info("Nominations for week -> {}", nominationsForWeek);
	}
}
