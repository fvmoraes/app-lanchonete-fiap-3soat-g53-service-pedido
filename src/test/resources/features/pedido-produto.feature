# language: pt
Funcionalidade: Produto

  Cenario: Busca Produtos
    Quando efeturar uma requisicao para Buscar todos os produtos
    Entao deve retornar uma lista com os produtos cadastrados

  Cenario: Buscar Produtos por Categoria Lanche
    Quando efetuar uma requisicao para Buscar todos os produtos por categoria
    Entao deve retornar uma lista com os produtos cadastrados da categoria

  Cenario: Criar Produto sem ser repetido
    Quando efetuar requisicao de criacao de um novo produto
    Entao O Produto é criado com sucesso

  Cenario: Criar Produto repetido
    Quando efetuar requisicao de criacao de um novo produto
    Entao retorna conflito com a string Produto já cadastrado

  Cenario: Atualizar Produto
    Quando efetuar requisicao de atualizar um produto
    Entao retorna a string Produto atualizado com sucesso

  Cenario: Deletar Produto existente
    Quando efetuar requisicao de deletar um produto
    Entao retorna com STATUS ok
    
  Cenario: Atualizar Produto não existente
    Quando efetuar requisicao de atualizar um produto
    Entao retorna a string Produto não cadastrado
