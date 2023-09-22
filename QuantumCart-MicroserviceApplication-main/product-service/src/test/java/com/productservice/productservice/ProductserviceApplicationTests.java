package com.productservice.productservice;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootTest
class ProductserviceApplicationTests {
	@Test
	private String getProductRequestObj() {
		return "hello";
	}

}
