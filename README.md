Gerenciador de Funcionários e Departamentos

Sistema web desenvolvido com Spring Boot e Thymeleaf para cadastro, edição e exclusão de funcionários e departamentos.

Tecnologias utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- Thymeleaf
- H2 / MySQL (configurável)
- Maven

Como executar a aplicação

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/gerenciador.git
   cd gerenciador

Configure o banco de dados em src/main/resources/application.properties:
spring.datasource.url=jdbc:mysql://localhost:3306/gerenciador
spring.datasource.username=seu-usuario
spring.datasource.password=sua-senha
spring.jpa.hibernate.ddl-auto=update

Execute o projeto:
./mvnw spring-boot:run

Acesse no navegador:
http://localhost:8080/web/funcionarios
http://localhost:8080/web/departamentos

Exemplos de requisições
Criar departamento (POST)
curl -X POST http://localhost:8080/api/departamentos \
-H "Content-Type: application/json" \
-d '{"nome":"Financeiro","localizacao":"Andar 3"}'

Listar funcionários (GET)
curl http://localhost:8080/api/funcionarios

Excluir funcionário (DELETE)
curl -X DELETE http://localhost:8080/api/funcionarios/1


   
