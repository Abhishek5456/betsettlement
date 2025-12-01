package com.sprotygroup.betsettlement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class BetSettlementApplication {

	public static void main(String[] args) {
		SpringApplication.run(BetSettlementApplication.class, args);
	}

}
