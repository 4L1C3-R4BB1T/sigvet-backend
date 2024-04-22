package br.com.sigvet.sigvetapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SigvetApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SigvetApiApplication.class, args);
	}

}
