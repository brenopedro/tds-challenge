# Solução do Desafio Backend Encurtador de URLs - TDS Company
[Desafio TDS Company](https://github.com/tdscompany/desafio-de-back-end-encurtador-de-url)

Aplicação backend que encurta e gerencia URLs. O objetivo é receber um link e transformá-lo em outro mais curto, mais fácil de lembrar e que também seja possível saber a quantidade de acessos.

## Sumário

- [Funcionalidades](#funcionalidades)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Pré-requisitos](#pré-requisitos-para-executar-a-aplicação)
- [Instalação](#instalação)
- [Como Usar](#como-usar)
- [Endpoints](#endpoints)
- [Contato](#contato)

## Funcionalidades

- Cadastrar URL e retornar uma encurtada;
- Ser redirecionado para a URL cadastrada ao acessar a URL encurtada;
- Exibir estatisticas de acesso da url encurtada(média de acessos por dia e total geral).


## Tecnologias Utilizadas

- Java
- Spring Boot
- Swagger

## Pré-requisitos para executar a aplicação

- JDK 21+
- Maven
- Banco de Dados MySQL

## Como Usar

Instruções para rodar o projeto:

1. Inicie a aplicação:
    ```sh
    mvn spring-boot:run
    ```
2. Acesse a aplicação no navegador:
    ```
    http://localhost:8080
    ```


## Endpoints

1. Acesse o Swagger por meio do navegador: http://localhost:8080/swagger-ui.html

Ou 

### Salva a URL Original e cria uma nova URL encurtada:
Método HTTP: `POST`
<br>
Endpoint: `/`

**Exemplo de corpo da requisição:**
```json
{
    "urlComplete": "https://google.com"
}

```
### Exibe as estatisticas de acesso a URL encurtada:
Método HTTP: `GET`
<br>
Endpoint: `/statistics`

**Exemplo de corpo da requisição:**
```json
{
    "urlComplete": "http://localhost:8080/sjs012"
}

```
### Direciona para a URL original:
Método HTTP: `GET`
<br>
Endpoint: `/{urlShort}` 

## Contato
- Nome: Breno Pinto
- Email: brenopedro4@gmail.com
- LinkedIn: [LinkedIn](https://www.linkedin.com/in/brenoassispinto/)
