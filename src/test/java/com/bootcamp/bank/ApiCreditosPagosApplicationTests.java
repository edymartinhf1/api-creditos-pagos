package com.bootcamp.bank;

import com.bootcamp.bank.creditos.service.CreditosPagosService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ApiCreditosPagosApplicationTests {
	@Autowired
	private CreditosPagosService creditosPagosService;
	@Test
	void contextLoads() {
		assertThat(creditosPagosService).isNotNull();

	}

}
