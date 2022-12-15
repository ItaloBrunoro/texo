# Desafio TEXO IT

### Instruções para Deploy

Então para rodar a aplicação só é preciso executar o comando mvn spring-boot:run e para rodar e os testes de integração é preciso executar o comando mvn test é necessário ter o maven instalado, link para download https://maven.apache.org/download.cgi.

#### Implementações e melhorias

Utilização do Swagger para utilização da API, visto que ele cria uma documentação bem completa para que mesmo quem não conhece a API e o negócio possa utiliza-la.
Url padrão para utilizar: http://localhost:8080/swagger-ui.html#/film-api

Código com logs para facilitar a visibilidade do processamento e persistência dos dados, poderiam ser mais detalhados juntamente com as informações de entrada e saida de cada execução.

Poderia ser realizados mais testes em questão de: testes de banco de dados, testes especificos em cada camada e teste de performance.