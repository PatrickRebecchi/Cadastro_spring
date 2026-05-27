# Cadastro Spring Boot API

API REST desenvolvida com Java + Spring Boot para cadastro e autenticação de usuários.

## 🚀 Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- BCryptPasswordEncoder
- Maven
- MySQL
- Postman

---

# 📌 Funcionalidades

✅ Cadastro de usuários  
✅ Login com autenticação  
✅ Criptografia de senha com BCrypt  
✅ Validação de email e senha  
✅ Respostas HTTP personalizadas  
✅ Organização em camadas:
- Controller
- Service
- Repository
- DTO

---

# 🔐 Segurança

As senhas são armazenadas de forma criptografada utilizando:

```java
BCryptPasswordEncoder
```

A autenticação é realizada comparando a senha digitada com a senha criptografada salva no banco.

---

# 📂 Estrutura do projeto

```bash
src
 ├── controller
 ├── service
 ├── repository
 ├── entity
 ├── dto
 ├── config
```

---

# ⚙️ Como executar o projeto

## 1. Clone o repositório

```bash
git clone https://github.com/PatrickRebecchi/Cadastro_spring.git
```

---

## 2. Entre na pasta

```bash
cd Cadastro_spring
```

---

## 3. Configure o banco de dados

No arquivo:

```properties
application.properties
```

configure:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/seu_banco
spring.datasource.username=root
spring.datasource.password=sua_senha
```

---

## 4. Execute o projeto

Pelo IntelliJ ou terminal:

```bash
mvn spring-boot:run
```

---

# 📮 Endpoints

## ➕ Cadastrar usuário

### POST

```http
/usuarios
```

### Body

```json
{
  "nome": "Patrick",
  "email": "patrick@email.com",
  "cpf": "52998224725",
  "senha": "123456"
}
```

---

## 🔑 Login

### POST

```http
/loginComSenha
```

### Body

```json
{
  "email": "patrick@email.com",
  "senha": "123456"
}
```

---

# ✅ Resposta de sucesso

```json
{
  "id": 1,
  "nome": "Patrick",
  "role": "USER",
  "mensagem": "Login bem-sucedido"
}
```

---

# ❌ Resposta de erro

```json
{
  "mensagem": "Email ou senha inválidos"
}
```

---

# 🎯 Objetivo do projeto

Este projeto foi desenvolvido com foco em aprendizado prático de backend utilizando Spring Boot, principalmente:
- autenticação
- segurança
- APIs REST
- criptografia de senha
- arquitetura em camadas

---

# 👨‍💻 Autor

Patrick Rebecchi

GitHub:
https://github.com/PatrickRebecchi
