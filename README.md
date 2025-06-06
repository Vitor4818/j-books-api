# 🚀 API RESTful com Spring Boot + Oracle

Este projeto é uma **API RESTful** desenvolvida em **Java** com **Spring Boot**, conectada ao banco de dados **Oracle**, com controle de versões usando **Flyway** e segurança implementada com **Spring Security**. A aplicação permite que usuários organizem seus livros em categorias como:

- 📖 Lendo  
- 📕 Lidos  
- 📘 A ler  

## 🚀 Funcionalidades

- 📌 Cadastro de usuários com autenticação segura
- 📚 Adicionar, atualizar e remover livros
- 🔄 Mover livros entre categorias: `lendo`, `lidos`, `a ler`
- 🔒 Autenticação e autorização com Spring Security
- 🧾 Histórico de livros por usuário
- 💾 Integração com banco de dados Oracle usando JPA
- 📂 Migrations com Flyway para versionamento do banco de dados




---

## 🛠️ Tecnologias e Ferramentas Utilizadas

- **Java 17+**
- **Spring Boot**
  - Spring Web
  - Spring Data JPA
  - Spring Security
- **Banco de Dados Oracle**
- **Flyway** (para versionamento e controle de migrations)
- **Maven** (gerenciador de dependências)
- **Postman** (para testes da API)
---

## 📦 Funcionalidades

- CRUD completo de entidades (Ex: usuários, livros, etc.)
- Integração com banco de dados Oracle usando JPA/Hibernate
- Migrations controladas com Flyway
- Autenticação e autorização com Spring Security
- Hash de senhas com BCrypt
- Validações com Bean Validation
- Estrutura limpa e desacoplada seguindo boas práticas (camadas: Controller, Service, Repository, DTOs)

---

## 🔐 Segurança
- Registro e login com autenticação
- Proteção de rotas com Spring Security
- Criptografia de senha com BCrypt
- Implementação de **autenticação JWT**
---

## 📂 Estrutura do Projeto
```
src
├── main
│ ├── java
│ │ └── com.jbooks
| | ├── config
│ │ ├── controller
│ │ ├── dto
│ │ ├── model
│ │ ├── repository
│ │ ├── security
│ │ └── service
│ └── resources
│ ├── application.properties
│ └── db/migration (migrations Flyway)
```
## ⚙️ Configuração do Banco (Oracle)

Certifique-se de que o banco de dados Oracle está rodando e acessível.  
Configure as credenciais no arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:XE
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
```

## 🧪 Como Executar o Projeto
Clone o repositório:

```
git clone https://github.com/Vitor4818/j-books-api.git
```
- Configure o banco Oracle localmente ou em nuvem
- Execute a aplicação na sua IDE (IntelliJ, Eclipse ou via terminal)
- Acesse http://localhost:8080

### ✅ Requisitos
- Java 21
- Oracle Database
- Maven
- Postman 

# 📌 Boas Práticas Adotadas

- Organização em camadas (Controller, Service, Repository)
- Uso de DTOs para segurança e desacoplamento
- Versionamento de banco com Flyway
- Senhas criptografadas
- Tratamento de erros personalizado
- Validação de entrada com Bean Validation

# 👨‍💻 Autor
Desenvolvido por Vitor Gomes Martins

