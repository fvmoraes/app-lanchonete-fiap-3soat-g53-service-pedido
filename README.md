# Sistema de Pedidos para Lanchonete - FIAP 3SOAT (Grupo 53)
---
---
## Estrutura e tecnologias utilizadas
### Qual a proposta?
>Este projeto é um microserviço para a aplicação de uma lanchonete, desenvolvida como parte do curso de pós graduação em Software Architeture da FIAP. É encarregado da parte de realização de pedidos.

Os microserviços fazem a comunicação entre si utilizando o RabbitMQ

### Tecnologias utilizadas:
- [Java](https://dev.java/learn/)
- [Docker](https://docs.docker.com/get-started/)
- [Cassandra](https://cassandra.apache.org/_/index.html)
- [PostgreSQL](https://www.postgresql.org/about/)
- [PGAdmin](https://www.pgadmin.org/docs/)
- [Swagger](https://swagger.io/solutions/api-documentation/)

### Informações Básicas:

Para interação básica, você pode usar o Swagger (via navegador) ou o Postman (importando a collection, o arquivo FIAP.postman_collection.json que contém a configuração essencial para iniciar o uso).

O banco de dados Cassandra e o banco de dados Postgress já estão configuradosdentro do docker-compose prontos para uso  dos três microserviços

O projeto possui boas praticas de programação com base em DDD , CleanCode e padrão SAGA.


### Relatório RIPD
Foi desenvolvido um Relatório de Impacto à Proteção de Dados Pessoais (RIPD).
[link relatório RIPD](https://docs.google.com/document/d/1aOfazLaWvX0epL91mDLoZKoSHe7SImiJN_0f8_cPWwQ/edit)

### Relatório processamento OWASP ZAP

Foi gerado o relatório utilizando a ferramenta ZAP na versão 2.15.0.
[link versão sem correção](old/2024-08-03-ZAP-Report-.html)
Após foram realizados as correções e rodados a ferramenta novamente, segue o relatório final:

[link versão corrigida](new/2024-08-03-ZAP-Report-.html)


### Padrão Saga

Foi escolhido o padrão de coreografia para gerenciar a interação entre as aplicações de pedido, pagamento e produção, essa escolha foi justificada por alguns motivos:

1. Desacoplamento dos Serviços: Cada serviço é independente e não precisa conhecer os detalhes internos dos outros serviços. Eles apenas precisam saber como reagir às mensagens que recebem. Isso permite que cada serviço evolua de forma independente.
2. Resiliência: Se um serviço falha, os outros serviços podem continuar operando de forma independente, e o sistema pode se recuperar de falhas parciais mais facilmente.
3. Escalabilidade Horizontal: Como cada serviço é independente, eles podem ser escalados horizontalmente de forma independente. Isso é útil para lidar com aumentos no volume de pedidos, pagamentos ou operações de cozinha sem precisar escalonar todo o sistema.
4. Simplificação da Lógica: A lógica de negócios é distribuída e encapsulada dentro de cada serviço, reduzindo a complexidade de um orquestrador central. Cada serviço realiza suas operações e notifica os outros serviços quando uma ação é concluída.
5. Tolerância a Falhas: A coreografia permite uma maior tolerância a falhas, pois não há um ponto único de falha, como seria o caso com um orquestrador centralizado.
6. Adaptabilidade: Novos serviços podem ser adicionados ao sistema sem a necessidade de modificar um orquestrador central. Eles apenas precisam ser configurados para escutar e reagir às mensagens apropriadas.

O fluxo pode ser visto na imagem seguindo o link abaixo:
[Fluxo SAGA](https://miro.com/app/board/uXjVKsgCDYA=/?share_link_id=701888483667)


#### Explicação do Funcionamento

1.  **Aplicação de Pedido**:
    
    -   Gera uma mensagem para a aplicação de Pagamento informando sobre um novo pedido.
    
2.  **Aplicação de Pagamento**:
    
    -   Escuta a mensagem de novo pedido.
    -   Cria o pedido no banco de dados com status "PENDENTE".
    
3.  **Aplicação de Pagamento** (continuação):
    
    -   Recebe uma mensagem de atualização do status do pagamento.
        -   Se o status for "Pago":
            -   Atualiza o status do pedido para "Pago" no banco de dados.
            -   Gera uma mensagem para a aplicação Pedido com status de pagamento "PAGO".
        -   Se o status não for "PAGO":
            -   Atualiza o status do pedido para "CANCELADO" no banco de dados.
            -   Gera uma mensagem para a aplicação Pedido com status de pagamento "CANCELADO".
            
4.  **Aplicação de Pedido** (após atualização do pagamento):
    
    -   Recebe a mensagem de atualização do pagamento.
        -   Se o status for "Pago":
            -   Atualiza o status do pagamento para "Pago" e o status do Pedido "PREPARACAO".
            -   Gera uma mensagem para a aplicação de Cozinha para iniciar a preparação do pedido.
        -   Se o status for "Cancelado":
            -   Atualiza o status do pagamento e o status do pedido para "Cancelado".
5.  **Aplicação de Cozinha**:
    
    -   Recebe a mensagem para começar a preparar o pedido.
    -   Inicia a preparação do pedido.

6.  **Aplicação de Cozinha**(continuação):
    -   Recebe a mensagem de atualização do status do pedido.
    -   Gera uma mensagem para a aplicação Pedido com a atualização do status do pedido.

7.  **Aplicação de Pedido** (após atualização do pagamento):
    
    -   Recebe a mensagem de atualização do pedido.
    -   Atualiza o status do Pedido para o cliente(UI) poder consultar.

### Banco de Dados:

Esse microserviço utiliza o Postgress que utiliza as tabelas a seguir:

  +--------------------+
  
  |------Pedido--------|
  
  +--------------------+
  
  |-id-(PK)------------|
  
  |-statusPedido-------|
  
  |-listaProdutosPedido|
  
  |-statusPagamento----|
  
  |-valorTotal---------|
  
  +--------------------+

  +-----------------+
  
  |-----Produto-----|
  
  +-----------------+
  
  |-nome-(PK)-------|
  
  |-categoria-------|
  
  |-descricão-------|
  
  |-valor-----------|
  
  +-----------------+
  
Essa estrutura de banco de dados possui duas tabelas principais: Pedido e Produto, representando as entidades PedidoEntity e ProdutoEntity, respectivamente.

    A tabela Pedido armazena informações sobre cada pedido, como seu identificador (id),uma lista de produtos relacionado ao pedido (listaProdutosPedido),  status do pedido (statusPedido), status do pagamento (statusPagamento), e o valor total (valorTotal).
    A tabela Produto armazena informações sobre cada produto disponível, como seu identificador nome (nome), categoria (categoria), , descrição (descricao), e valor (valor).
    
A chave primária (PK) de cada tabela está indicada.

### Inspeçao de código:
> Efetuamos a inspeçao de código com o SonarCloud, mantendo a cobertura minima de testes em 80%:

![](/img/sonarcloud.png)

### Message Broker
> Utilizamos o rabbitMQ, via CloudAMQP para troca de mensagens entre os microsserviços:

![](/img/rabbitmq.png)

## Como Usar
### O que preciso ter instalado no meu computador?
> Qualquer sistema operacional Linux ou subsistema Linux, Windows ou MacOs

> Docker, Docker Desktop e Docker Compose instalados na sua máquina

- [Docker](https://docs.docker.com/get-started/)
- [Docker Compose](https://docs.docker.com/compose/install/)

### Como executar o projeto no meu computador?
> Não é necessario criar databases, ou rodar o projeto de forma local, e sim apenas utilizar um docker-compose que existe neste repositorio. Esse Docker-compose já está configurado para rodar os três microserviços.

> Dentro do repositorio da aplicação, o comando inicial pode ser o seguinte para uso com Docker Compose:
```sh
docker-compose up
``````
> Após é só rodar localmente o projeto!

---
## Mais informações sobre a API
> Lista de endpoints
- GET http://localhost:8080/api/v1/produto
- POST http://localhost:8080/api/v1/produto
- DELETE http://localhost:8080/api/v1/produto
- GET http://localhost:8080/api/v1/produto/{categoria}
- POST http://localhost:8080/api/v1/pedido
- GET http://localhost:8080/api/v1/pedido
- GET http://localhost:8080/api/v1/pedido/{id}
- GET http://localhost:8080/api/v1/pedido/status/{status}
- GET http://localhost:8080/api/v1/pedido/pagamento/{id}

> Swagger
- GET http://localhost:8080/swagger-ui/index.html
---
---
## Referências
### Este projeto foi criado usando os seguintes pacotes Java
- [spring boot starter data jpa](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa)
- [spring boot starter web](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web)
- [springdoc openapi starter webmvc ui](https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-starter-webmvc-ui)
- [postgresql](https://mvnrepository.com/artifact/org.postgresql/postgresql)
- [hibernate validator](https://mvnrepository.com/artifact/org.hibernate.validator/hibernate-validator)
- [spring boot](https://spring.io/projects/spring-boot/)

---
---
_fim do README.md_
