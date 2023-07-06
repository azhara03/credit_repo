package com.Credit.credit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class CreditApplication {
	public static void main(String[] args) {
		SpringApplication.run(CreditApplication.class, args);
	}

	public Docket apis(){
		return  new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.Credit")).build();
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
