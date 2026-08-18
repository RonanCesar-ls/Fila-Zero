# FilaZero API

Backend RESTful para gerenciamento de filas e pedidos, desenvolvido com Java e Spring Boot.

O projeto foi desenvolvido com foco em organização de código, persistência de dados, containerização e automação de build e testes por CI/CD.

## Stack

| Tecnologia      | Uso                                           |
| --------------- | --------------------------------------------- |
| Java 25         | Linguagem principal                           |
| Spring Boot     | Desenvolvimento da API REST                   |
| Spring Data JPA | Persistência e acesso aos dados               |
| Hibernate       | ORM                                           |
| PostgreSQL      | Banco de dados                                |
| Docker          | Ambiente de desenvolvimento e containerização |
| GitHub Actions  | CI/CD                                         |
| Qodana          | Análise estática de código                    |
| Render          | Deploy da aplicação                           |
| Neon            | PostgreSQL em produção                        |

## Arquitetura

A aplicação segue uma arquitetura organizada em camadas, separando responsabilidades entre domínio, persistência e exposição dos endpoints HTTP.

```text
src/main/java
└── Ronan.filazero
    ├── controller
    ├── domain
    ├── repository
    ├── service
    └── exception
```

O fluxo principal da aplicação é:

```text
HTTP Request
     |
     v
Controller
     |
     v
Service
     |
     v
Repository
     |
     v
PostgreSQL
```

## Principais decisões técnicas

### Configuração por variáveis de ambiente

As credenciais e informações de conexão com o banco não são armazenadas diretamente no código.

A aplicação utiliza variáveis de ambiente:

```text
DB_URL
DB_USER
DB_PASS
```

Isso permite utilizar configurações diferentes entre desenvolvimento e produção sem alterar o código da aplicação.

### Docker

O projeto possui um `Dockerfile` utilizando multi-stage build.

A primeira etapa é responsável pela compilação da aplicação. A imagem final contém apenas o necessário para executar o artefato gerado, reduzindo o tamanho da imagem e a quantidade de dependências presentes no ambiente de execução.

Para desenvolvimento local, o PostgreSQL pode ser iniciado utilizando Docker Compose:

```bash
docker compose up -d
```

Para verificar os containers em execução:

```bash
docker ps
```

### Tratamento global de exceções

As exceções relacionadas às regras de negócio são tratadas por um `@RestControllerAdvice`.

Dessa forma, erros esperados pela aplicação são convertidos em respostas HTTP apropriadas, evitando expor detalhes internos ou stack traces para o cliente.

## API

### Produção

```text
https://fila-zero-roq2.onrender.com
```

### Pedidos

#### Listar pedidos

```http
GET /api/pedidos
```

Retorna todos os pedidos cadastrados.

#### Criar pedido

```http
POST /api/pedidos
Content-Type: application/json
```

Exemplo:

```json
{
  "nomeCliente": "Tech Recruiter",
  "valorTotal": 85.90
}
```

## Modelo de pedido

Um pedido possui, entre outros atributos:

```text
Pedido
├── id
├── nomeCliente
├── valorTotal
├── dataCriacao
└── status
```

Os possíveis estados do pedido são:

```text
AGUARDANDO_PREPARO
EM_PREPARO
PRONTO
FINALIZADO
```

## Executando localmente

### Pré-requisitos

* Java 25
* Docker
* Docker Compose
* Git

Clone o repositório:

```bash
git clone <REPOSITORY_URL>
cd filazero
```

Inicie o PostgreSQL:

```bash
docker compose up -d
```

Execute a aplicação:

```bash
./gradlew bootRun
```

No Windows:

```powershell
.\gradlew.bat bootRun
```

A API estará disponível em:

```text
http://localhost:8080
```

## Build

Para gerar o artefato da aplicação:

```bash
./gradlew build
```

No Windows:

```powershell
.\gradlew.bat build
```

Para executar os testes:

```bash
./gradlew test
```

## CI/CD

O projeto utiliza GitHub Actions para automatizar o processo de integração.

O pipeline é responsável por executar etapas como:

```text
Push / Pull Request
        |
        v
Checkout
        |
        v
Configuração do Java
        |
        v
Build
        |
        v
Testes
        |
        v
Análise de código
```

A análise estática é realizada utilizando Qodana.

O deploy da aplicação é realizado no Render após a conclusão das etapas definidas no pipeline.

## Banco de dados

### Desenvolvimento

O ambiente local utiliza PostgreSQL executado através do Docker Compose.

### Produção

O banco de dados de produção utiliza PostgreSQL hospedado no Neon.

As informações de conexão são fornecidas através de variáveis de ambiente no ambiente de deploy.

## Status

Em desenvolvimento.

## Autor

**Ronan César Lourenço**

Desenvolvedor em formação, com foco em desenvolvimento backend utilizando Java, Spring Boot e tecnologias relacionadas ao ecossistema web.
