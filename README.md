# 🌦️ Lugia Weather API

Uma API REST completa para gerenciamento de usuários com autenticação JWT, integração com ViaCEP e documentação Swagger.

## 📋 Sobre o Projeto

Esta API foi desenvolvida seguindo as melhores práticas da arquitetura REST, oferecendo um sistema robusto de cadastro e autenticação de usuários com funcionalidades avançadas de validação e integração externa.

## 🚀 Funcionalidades

- ✅ **CRUD completo de usuários**
- 🔐 **Autenticação JWT**
- 🏠 **Integração com API ViaCEP**
- 📱 **Gerenciamento de telefones**
- 🛡️ **Criptografia de senhas com BCrypt**
- ✔️ **Validações com Bean Validation**
- 📚 **Documentação automática com Swagger**
- 🔄 **Relacionamentos JPA**

## 🛠️ Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Security**
- **Spring Data JPA**
- **JWT (JSON Web Token)**
- **BCrypt**
- **Swagger/OpenAPI 3**
- **Bean Validation**
- **RestTemplate**
- **H2/MySQL Database**

## 📦 Estrutura do Projeto

src/main/java/com/lugiaweather/api/
├── controller/ # Controladores REST
├── dto/ # Data Transfer Objects
├── model/ # Entidades JPA
├── repository/ # Repositórios Spring Data
├── security/ # Configurações de segurança
└── service/ # Serviços de negócio

text

## 🔧 Configuração e Instalação

### Pré-requisitos
- Java 17 ou superior
- Maven 3.6+
- MySQL (opcional - H2 configurado por padrão)

### Passos para execução

1. **Clone o repositório**
git clone https://github.com/seu-usuario/lugia-weather-api.git
cd lugia-weather-api

text

2. **Configure o banco de dados** (opcional)
application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/lugiaweather
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

text

3. **Execute o projeto**
mvn spring-boot:run

text

4. **Acesse a documentação**
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- API Docs: `http://localhost:8080/v3/api-docs`

## 📡 Endpoints Principais

### Autenticação
POST /auth/login
Content-Type: application/json

{
"email": "usuario@email.com",
"senha": "suaSenha123@"
}

text

### Usuários
Criar usuário
POST /users/inserir

Listar usuários
GET /users

Buscar por ID
GET /users/{id}

Atualizar usuário
PUT /users/atualizar/{id}

Deletar usuário
DELETE /users/{id}

text

## 📝 Exemplo de Uso

### 1. Criar um novo usuário
POST /users/inserir
{
  "nome": "Lulio",
  "email": "lulio@email.com",
  "senha": "12345678@Ss",
  "telefone": {
    "ddd": "11",
    "numero": "966910838"
  },
  "endereco": {
    "cep": "01001-000"
  }
}


text

### 2. Fazer login
POST /auth/login
{
"email": "lulio@email.com",
"senha": "12345678@Ss"
}

text

### 3. Usar token nas requisições protegidas
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2FvQGVtYWlsLmNvbSI...

text

## 🏗️ Arquitetura

### Entidades Principais
- **User**: Usuário do sistema
- **Endereco**: Endereço completo via ViaCEP
- **Telefone**: Telefone com DDD

### Relacionamentos
- User ↔ Endereco (OneToOne)
- User ↔ Telefone (OneToOne)

### Segurança
- Senhas criptografadas com BCrypt
- Autenticação stateless com JWT
- Endpoints protegidos por Spring Security

## 🔍 Validações Implementadas

- **Email**: Formato válido
- **Senha**: Obrigatória e não vazia
- **Nome**: Entre 3 e 50 caracteres
- **CEP**: Formato brasileiro (8 dígitos)
- **Telefone**: DDD e número obrigatórios

## 📊 Status do Projeto

- ✅ API REST com boas práticas
- ✅ Persistência com Spring Data JPA
- ✅ Mapeamento de relacionamentos
- ✅ Validação com Bean Validation
- ✅ Documentação com Swagger
- ✅ Autenticação JWT
- ⏳ Deploy em nuvem (em desenvolvimento)

## 🤝 Contribuição

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 👨‍💻 Autor

**Jubs** - [GitHub](https://github.com/seu-usuario)
---
**Erik** - [GitHub](https://github.com/ozerikoz)
---
**Nathan** - [GitHub](https://github.com/NathanMagno)
---

⭐ Se este projeto te ajudou, deixe uma estrela!
