package com.fiap.lanchonete.application.cucumber;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.fiap.lanchonete.domain.entity.Categoria;
import com.fiap.lanchonete.domain.entity.Produto;

import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;

public class StepDefinition {
	private final String ENDPOINT_API = "http://localhost:8080/pedido/api/v1/produto";

	private Response response;

	@Quando("efeturar uma requisicao para Buscar todos os produtos")
	public void efeturar_uma_requisicao_para_buscar_todos_os_produtos() {
		response = given().when().get(ENDPOINT_API);
	}

	@Entao("deve retornar uma lista com os produtos cadastrados")
	public void deve_retornar_uma_lista_com_os_produtos_cadastrados() {
		response.then().body(containsString("\"nome\" : \"x_salada\""));
	}

	@Quando("efetuar uma requisicao para Buscar todos os produtos por categoria")
	public void efetuar_uma_requisicao_para_buscar_todos_os_produtos_por_categoria() {
		response = given().when().get(ENDPOINT_API + "/{categoria}", Categoria.Lanche);
	}

	@Entao("deve retornar uma lista com os produtos cadastrados da categoria")
	public void deve_retornar_uma_lista_com_os_produtos_cadastrados_da_categoria() {
		response.then().statusCode(200).extract().as(List.class);
	}

	@Quando("efetuar requisicao de criacao de um novo produto")
	public void efetuar_requisicao_de_criacao_de_um_novo_produto() {
		Produto produto = new Produto(Categoria.Lanche, "Hambúrguer2", "Delicioso hambúrguer de carne",
				BigDecimal.valueOf(10.50));

		response = given().contentType(MediaType.APPLICATION_JSON_VALUE).body(produto).when().post(ENDPOINT_API);

	}

	@Entao("O Produto é criado com sucesso")
	public void o_produto_é_criado_com_sucesso() {
		response.then().statusCode(HttpStatus.CREATED.value()).body(containsString("Produto cadastrado com sucesso"));
	}

	@Entao("retorna conflito com a string Produto já cadastrado")
	public void retorna_conflito_com_a_string_produto_já_cadastrado() {
		response.then().statusCode(HttpStatus.CONFLICT.value()).body(containsString("Produto já cadastrado"));

	}

	@Quando("efetuar requisicao de atualizar um produto")
	public void efetuar_requisicao_de_atualizar_um_produto() {
		Produto produto = new Produto(Categoria.Lanche, "Hambúrguer2", "Delicioso hambúrguer de carne",
				BigDecimal.valueOf(11.50));

		response = given().contentType(MediaType.APPLICATION_JSON_VALUE).body(produto).when().put(ENDPOINT_API);

	}

	@Entao("retorna a string Produto atualizado com sucesso")
	public void retorna_a_string_produto_atualizado_com_sucesso_com_status() {
		response.then().statusCode(HttpStatus.OK.value()).body(containsString("Produto atualizado com sucesso"));

	}

	@Entao("retorna a string Produto não cadastrado")
	public void retorna_a_string_produto_não_cadastrado_com_status() {
		response.then().statusCode(HttpStatus.BAD_REQUEST.value()).body(containsString("Produto não cadastrado"));
	}

	@Quando("efetuar requisicao de deletar um produto")
	public void efetuar_requisicao_de_deletar_um_produto() {
		Produto produto = new Produto(Categoria.Lanche, "Hambúrguer2", "Delicioso hambúrguer de carne",
				BigDecimal.valueOf(10.50));

		response = given().contentType(MediaType.APPLICATION_JSON_VALUE).body(produto).when().delete(ENDPOINT_API);
	}

	@Entao("retorna com STATUS ok")
	public void retorna_com_status_ok() {
		response.then().statusCode(HttpStatus.OK.value()).body(containsString("Produto deletado com sucesso"));

	}
}
