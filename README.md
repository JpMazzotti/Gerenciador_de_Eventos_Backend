# Gerenciador de Eventos - Backend

Backend (API REST) de um gerenciador de eventos. Ele permite **cadastrar e fazer login de administradores** (com autenticação por token JWT) e **criar, listar, atualizar e excluir eventos**.

Feito como parte de um desafio de estudo.

## Tecnologias usadas

| Tecnologia | Para que serve |
|---|---|
| Java 17 | 
| Spring Boot | 
| Spring Data JPA + Hibernate |
| H2 | Banco de dados em memória (os dados não são salvos em disco) |
| Spring Security + JWT | Segurança: login e controle de acesso com token |
| Swagger (springdoc) | 
| Maven |

## Como rodar o projeto

1. Abra o terminal/prompt na pasta do projeto.
2. Execute o comando abaixo para subir o servidor:

**Windows:**
mvnw.cmd spring-boot:run

**Linux/Mac:**
./mvnw spring-boot:run

. O servidor estará rodando em: `http://localhost:8080`

> Para parar o servidor, pressione `Ctrl + C` no terminal.

## Dados de acesso já criados

Ao iniciar o projeto, um **administrador de exemplo** já é criado automaticamente, junto com **3 eventos de teste**. Use essas credenciais para fazer login:

| Campo | Valor |
|---|---|
| Email | `admin@neki.com` |
| Senha | `1234567` |

## Endpoints (rotas) da API

| Método | URL | Precisa de token? | O que faz |
|---|---|---|---|
| POST | `/api/auth/cadastro` | Não | Cadastra um novo administrador |
| POST | `/api/auth/login` | Não | Faz login e devolve o token JWT |
| GET | `/api/eventos` | Sim | Lista os eventos do administrador logado |
| POST | `/api/eventos` | Sim | Cria um novo evento |
| PUT | `/api/eventos/{eventoId}` | Sim | Atualiza data e localização de um evento |
| DELETE | `/api/eventos/{eventoId}` | Sim | Exclui um evento |

> `{eventoId}` é o número do ID do evento (ex.: `1`).

## Exemplos de requisições

### 1. Cadastro de administrador
**POST** `http://localhost:8080/api/auth/cadastro`

Corpo da requisição (JSON):
```json
{
  "nome": "João Silva",
  "email": "joao@neki.com",
  "senha": "senha123"
}
Resposta esperada: 201 Created (sem corpo).
2. Login
POST http://localhost:8080/api/auth/login
Corpo da requisição (JSON):
{
  "email": "admin@neki.com",
  "senha": "1234567"
}
Resposta esperada (JSON):
{
  "token": "eyJhbGciOiJIUzI1NiJ9....",
  "email": "admin@neki.com",
  "nome": "Administrador Demo"
}
Guarde o token! 
3. Listar eventos
GET http://localhost:8080/api/eventos
Header obrigatório:
Authorization: Bearer SEU_TOKEN_AQUI
Resposta esperada (JSON — lista de eventos):
[
  {
    "id": 1,
    "nome": "Hackathon Neki",
    "data": "2026-10-15",
    "localizacao": "São Paulo - SP",
    "imagem": "https://exemplo.com/imagem.png"
  }
]
4. Criar evento
POST http://localhost:8080/api/eventos
Header obrigatório:
Authorization: Bearer SEU_TOKEN_AQUI
Corpo da requisição (JSON):
{
  "nome": "Meetup de Tecnologia",
  "data": "2026-12-10",
  "localizacao": "Curitiba - PR",
  "imagem": "https://exemplo.com/banner.png"
}
Resposta esperada: 201 Created com o evento criado no corpo.
5. Atualizar evento
PUT http://localhost:8080/api/eventos/1
Header obrigatório:
Authorization: Bearer SEU_TOKEN_AQUI
Corpo da requisição (JSON):
{
  "data": "2027-01-15",
  "localizacao": "Florianópolis - SC"
}
Resposta esperada: 200 OK com o evento atualizado no corpo.
6. Excluir evento
DELETE http://localhost:8080/api/eventos/1
Header obrigatório:
Authorization: Bearer SEU_TOKEN_AQUI
Resposta esperada: 204 No Content (sem corpo).
Swagger (documentação interativa)
O Swagger é uma tela pronta para testar a API sem precisar do Postman.
1. Suba o projeto e acesse: http://localhost:8080/swagger-ui.html
2. Lá você encontra todas as rotas, com botões para testar cada uma.
3. Nas rotas de evento (que precisam de token), clique no botão Authorize (no topo da página) e cole o token no formato:
Bearer SEU_TOKEN_AQUI
(ou cole apenas o token, dependendo da versão). Depois é só clicar em Authorize.
4. Feche a janela e teste as rotas à vontade.
Banco de dados H2 (em memória)
O projeto usa o H2, um banco de dados em memória. Isso significa que:
- Ele é criado do zero toda vez que o projeto inicia.
- Todos os dados são apagados quando o servidor é desligado.
- As tabelas são criadas automaticamente (não precisa criar nada no banco).
Para ver o console do banco:
1. Com o servidor rodando, acesse: http://localhost:8080/h2-console
2. Use estas configurações:
- JDBC URL: jdbc:h2:mem:eventosdb
- User Name: sa
- Password: (deixe em branco)
