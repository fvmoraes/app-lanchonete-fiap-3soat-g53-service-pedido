package com.fiap.lanchonete.infraestructure.framework;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fiap.lanchonete.application.gateways.LGPDGateway;
import com.fiap.lanchonete.application.usecases.LGPDUseCases;
import com.fiap.lanchonete.application.usecases.LGPDUseCasesImp;
import com.fiap.lanchonete.infrastructure.gateway.LGPDRepositoryGateway;
import com.fiap.lanchonete.infrastructure.gateway.mapper.LgpdEntityMapper;
import com.fiap.lanchonete.infrastructure.persistence.LgpdRepository;

@Configuration
public class LgpdConfig {

	@Bean
	LGPDUseCases lgpdUseCasesBean(LGPDGateway lgpdGateway) {
		return new LGPDUseCasesImp(lgpdGateway);
	}

	@Bean
	LGPDGateway gatewa(LgpdRepository repository, LgpdEntityMapper mapper) {
		return new LGPDRepositoryGateway( repository,  mapper);
	}
	
	@Bean
	LgpdEntityMapper mapper() {
		return new LgpdEntityMapper();
	}
}
