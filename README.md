# ‍ Gerenciador de Funcionários e Departamentos

Sistema web desenvolvido com **Spring Boot** e **Thymeleaf** para **cadastro, edição e exclusão** de funcionários e departamentos.

---
##  Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot**
- **Spring Data JPA**
- **Thymeleaf**
- **H2** / **MySQL** (configurável)
- **Maven**

---

##  Como Executar a Aplicação

### 1. Clone o repositório

```bash
git clone https://github.com/seu-usuario/gerenciador.git
cd gerenciador
```

### 2. Configure o banco de dados

Edite o arquivo `src/main/resources/application.properties` conforme seu ambiente:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/gerenciador
spring.datasource.username=seu-usuario
spring.datasource.password=sua-senha
spring.jpa.hibernate.ddl-auto=update
```

>  Caso prefira, você pode utilizar o banco em memória **H2** para testes.

### 3. Execute o projeto

```bash
./mvnw spring-boot:run
```

### 4. Acesse no navegador

-  [http://localhost:8080/web/funcionarios](http://localhost:8080/web/funcionarios)  
-  [http://localhost:8080/web/departamentos](http://localhost:8080/web/departamentos)

---

##  Exemplos de Requisições (API REST)

###  Criar Departamento (POST)

```bash
curl -X POST http://localhost:8080/api/departamentos -H "Content-Type: application/json" -d '{"nome":"Financeiro","localizacao":"Andar 3"}'
```

###  Listar Funcionários (GET)

```bash
curl http://localhost:8080/api/funcionarios
```

###  Excluir Funcionário (DELETE)

```bash
curl -X DELETE http://localhost:8080/api/funcionarios/1
```

---

##  Estrutura do Projeto

```
gerenciador/
├── src/
│   ├── main/
│   │   ├── java/com/exemplo/gerenciador/
│   │   ├── resources/
│   │   │   ├── templates/      # Páginas Thymeleaf
│   │   │   └── application.properties
│   └── test/
├── pom.xml
└── README.md
```

---

##  Autor

**Diego Jardim de Oliveira**  
email: diego.morfeu@gmail.com
Github: https://github.com/DiegoMorpheus

---

 *Desenvolvido com Spring Boot e dedicação!*
