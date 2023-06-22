package com.Credit.credit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CreditApplication {
	public static void main(String[] args) {
		SpringApplication.run(CreditApplication.class, args);
	}
}
/*
[
    {
        "id": 1,
        "amount": 50000,
        "typeid": 1,
        "currencyid": 1,
        "term": 2,
        "creditTerm1": {
            "id": 2,
            "term": 12
        },
        "currencyE": {
            "id": 1,
            "currencyname": "KGS"
        },
        "type": {
            "id": 1,
            "creditname": "Потребительский",
            "percent": 22
        }
    }
]
 */
