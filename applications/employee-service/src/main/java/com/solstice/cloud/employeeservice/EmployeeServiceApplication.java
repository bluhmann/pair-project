package com.solstice.cloud.employeeservice;

import com.solstice.cloud.employeeservice.entities.Employee;
import com.solstice.cloud.employeeservice.repositories.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class EmployeeServiceApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private EmployeeRepository repository;

	// No need to add explicit @Autowired annotation if we have only one constructor
	public EmployeeServiceApplication(EmployeeRepository repository) {
		this.repository = repository;
	}

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		repository.addEmployee(new Employee("Bill", "Luhmann", "Chicago", "Technical Consultant", "bluhmann@solstice.com", "www.google.com", 10001));
		repository.addEmployee(new Employee("Sue", "Jones", "Chicago", "Technical Consultant", "sjones@solstice.com", "www.google.com", 10000));
		repository.addEmployee(new Employee("Hannah", "Montana", "Chicago", "product Consultant", "sjones@solstice.com", "www.google.com", 10002));

		logger.info("Employee Number 10001 -> " + repository.getEmployeeForID(10001));
	}
}
