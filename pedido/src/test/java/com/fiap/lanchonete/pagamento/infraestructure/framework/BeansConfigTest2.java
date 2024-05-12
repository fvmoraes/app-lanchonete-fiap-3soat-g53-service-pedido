package com.fiap.lanchonete.pagamento.infraestructure.framework;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.fiap.lanchonete.ApplicationPedido;

@SpringBootTest(classes = ApplicationPedido.class)
@Testcontainers
@ContextConfiguration(initializers = {BeansConfigTest2.Initializer.class})
class BeansConfigTest2 {

    @Autowired
    private ApplicationContext context;

    @Container
    private static final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("pagamento")
            .withUsername("test")
            .withPassword("test");

    
    @Container
    RabbitMQContainer rabbit = new RabbitMQContainer(DockerImageName.parse("rabbitmq:3.13.1-management"));

 

    @Test
    void testBeansExistence() {
        assertThat(context.getBean("pedidoInteractorBean")).isNotNull();
        assertThat(context.getBean("pedidoGateway")).isNotNull();
        assertThat(context.getBean("pedidoMapper")).isNotNull();
        assertThat(context.getBean("pedidoRequestMapper")).isNotNull();
        assertThat(context.getBean("produtoInteractorBean")).isNotNull();
        assertThat(context.getBean("produtoGateway")).isNotNull();
        assertThat(context.getBean("produtoMapper")).isNotNull();
        assertThat(context.getBean("produtoRequestMapper")).isNotNull();
        assertThat(context.getBean("jsonMessageConverter")).isNotNull();
        assertThat(context.getBean("rabbitTemplate")).isNotNull();
        assertThat(context.getBean("pedidoExchange")).isNotNull();

        assertThat(context.getBean("pedidoQueue")).isNotNull();
        assertThat(context.getBean("pagamentoQueue")).isNotNull();
        assertThat(context.getBean("pedidoBinding")).isNotNull();
        assertThat(context.getBean("producaoQueue")).isNotNull();
        assertThat(context.getBean("pedido2Binding")).isNotNull();

    }
   
    static class Initializer
    implements ApplicationContextInitializer<ConfigurableApplicationContext> {
      public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
          TestPropertyValues.of(
            "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
            "spring.datasource.username=" + postgreSQLContainer.getUsername(),
            "spring.datasource.password=" + postgreSQLContainer.getPassword()
          ).applyTo(configurableApplicationContext.getEnvironment());
      }
  }
}
