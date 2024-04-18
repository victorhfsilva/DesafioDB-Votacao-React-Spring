# Desafio Votação - Start DB

Este desafio foi parte do treinamento oferecido pela DB no Start DB 2023. 

## Sobre

O projeto consistia em desenvolver um MVP que solucionasse este [desafio](DESAFIO.md).

## Execução

Para executar a aplicação basta executar o comando a seguir na pasta raiz deste diretório.

```bash
docker-compose up
```

Para fazer o primeiro acesso a aplicação e registrar novos usuários pode-se utilizar a conta a seguir:

- **Cpf:** admin
- **Senha:** admin

## Tecnologias Utilizadas

### Back-End

- Java
- Spring Boot
- Spring Data JPA
- Spring Web
- Spring Security
- Spring Cloud Open Feign
- Lombok
- Validation
- Passay
- PostgreSQL
- H2
- Flyway
- Swagger
- Docker

### Front-End

- Typescript
- React
- Chakra UI
- React Router Dom
- Axios
- Zustand
- Jest
- React Testing Library
- Vite

## Configurações

Para reproduzir as configurações locais do back-end pode-se editar o arquivo .env dentro da pasta `dbpautas-back-end` com as seguintes variáveis de ambiente:

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

Lembre-se de criar o banco de dados de desenvolvimento previamente e substituir as configurações entre chaves pelas específicas ao seu ambiente de desenvolvimento.

## Débitos Técnicos

- Configurar a responsividade das páginas.

## Bugs

- Quando o front-end é executado sobre uma imagem do Nginx definida no Dockerfile do front-end, as pautas não são carregadas automaticamente ao navegar para suas respectivas páginas. É necessário antes selecionar uma categoria e só então as pautas serão carregadas. Executando localmente o erro não ocorre. 
