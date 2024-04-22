# Desafio Votação - Start DB

Este desafio foi parte do treinamento oferecido pela DB no Start DB 2023. 

## Sobre

O projeto consistia em desenvolver um MVP que solucionasse este [desafio](DESAFIO.md).

## Execução

Para executar a aplicação basta executar o comando a seguir na pasta raiz deste diretório. Para que esta execução ocorra sem erros é necessário copiar o conteúdo do `./dbpautas-back-end/.env.docker` para o arquivo `./dbpautas-back-end/.env`.

```bash
docker-compose up
```

Para fazer o primeiro acesso a aplicação e registrar novos usuários pode-se utilizar a conta a seguir:

- **Cpf:** admin
- **Senha:** admin

## Tecnologias Utilizadas

### Back-End

- [Java 17](https://www.oracle.com/br/java/technologies/downloads/)
- [Spring Boot 3.2.4](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Spring Web MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
- [Spring Security](https://docs.spring.io/spring-security/reference/index.html)
- [Spring Cloud Open Feign](https://spring.io/projects/spring-cloud-openfeign)
- [Lombok](https://projectlombok.org/)
- [Validation](https://docs.spring.io/spring-framework/reference/core/validation/beanvalidation.html)
- [Passay](https://www.passay.org/)
- [PostgreSQL](https://www.postgresql.org/)
- [H2](https://www.h2database.com/html/main.html)
- [Flyway](https://flywaydb.org/)
- [Springdoc](https://springdoc.org/#google_vignette)
- [Docker](https://www.docker.com/)

### Front-End

- [Typescript](https://www.typescriptlang.org/)
- [React.js](https://react.dev/)
- [Chakra UI](https://v2.chakra-ui.com/)
- [React Router Dom](https://www.npmjs.com/package/react-router-dom)
- [Axios](https://axios-http.com/)
- [Zustand](https://docs.pmnd.rs/zustand/getting-started/introduction)
- [Jest](https://jestjs.io/pt-BR/)
- [React Testing Library](https://testing-library.com/docs/react-testing-library/intro/)
- [Vite](https://vitejs.dev/)

## Configurações

Para reproduzir as configurações locais do back-end basta editar o arquivo `./dbpautas-back-end/.env` dentro da pasta  com as seguintes variáveis de ambiente:

```
DEV_DB_URL=jdbc:postgresql://localhost:5432/{banco de dados de desenvolvimento}
POSTGRES_USER={usuário do banco de dados}
POSTGRES_PWD={senha do banco de dados}
JWT_SECRET={senha utilizada para gerar os tokens jwt}
JWT_ISSUER={issuer dos tokens}
VALIDATION_CPF_ACTIVE={true | false}
```

A variável `VALIDATION_CPF_ACTIVE` habilita ou desabilita a verificação da regularidade dos CPFs na api [CPF.CNPJ](https://www.cpfcnpj.com.br/).

O token utilizado nesta API é um token de teste que sempre retorna cpf irregular. Para utilizá-lo em produção favor substituir o token da api por um válido em `CpfCnpjClient`.

Um exemplo desta configuração é o arquivo `./dbpautas-back-end/.env.local`

Lembre-se de criar o banco de dados de desenvolvimento previamente e substituir as configurações entre chaves pelas específicas ao seu ambiente de desenvolvimento.

## Débitos Técnicos

- Configurar a responsividade das páginas.

## Bugs

- Quando o front-end é executado sobre uma imagem do Nginx definida no Dockerfile do front-end, as pautas não são carregadas automaticamente ao navegar para suas respectivas páginas. É necessário antes selecionar uma categoria e só então as pautas serão carregadas. Executando localmente o erro não ocorre. 
