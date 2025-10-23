# 🛒 Sistema de Cadastro de Produtos (Spring Boot + Thymeleaf)  

Este é um projeto de aplicação web para cadastro de produtos, desenvolvido com Spring Boot.
A aplicação permite realizar operações CRUD completas (Criar, Ler, Atualizar e Excluir), além de funcionalidades extras como filtro por categoria e alertas dinâmicos.  

## ✨ Funcionalidades Principais  

📝 Cadastro de Produtos: Formulário com validação de dados.  
📋 Listagem de Produtos: Exibe todos os produtos cadastrados em tabela paginada.  
✏️ Edição: Permite alterar as informações de um produto existente.  
❌ Exclusão: Remove um produto do sistema com confirmação.  
🔍 Filtro por Categoria: Filtra os produtos por categoria específica.  
⚡ Alertas Dinâmicos: Mensagens de sucesso exibidas diretamente nas páginas.  
📱 Design Responsivo: Interface construída com Bootstrap 5.  

## 🚀 Tecnologias Utilizadas  
### 🧩 Backend  
☕ Java 17  
⚙️ Spring Boot 3  
🌐 Spring Web — Controladores e endpoints REST  
🗄️ Spring Data JPA — Persistência de dados  
✅ Spring Validation — Validação dos formulários  

### 🎨 Frontend
🧱 Thymeleaf — Motor de templates HTML  
💅 Bootstrap 5 — Estilização e responsividade  

### 🗃️ Banco de Dados  
🐬 MySQL — Banco de dados relacional  
🔄 Hibernate (JPA) — Mapeamento objeto-relacional (ORM)  
🔧 Build
🧰 Maven — Gerenciamento de dependências e build

## 📋 Pré-requisitos
- Antes de iniciar, certifique-se de ter instalado:
- Java Development Kit (JDK) 17 ou superior
- Maven (ou utilize o Maven Wrapper incluído: mvnw)
- Servidor MySQL em execução localmente

## ⚙️ Como Executar o Projeto  
1️.  Clone o repositório  
````
git clone https://github.com/seu-usuario/nome-do-repositorio.git
cd nome-do-repositorio
````
2️.  Configure o banco de dados  

Certifique-se de que o servidor MySQL está ativo e crie o banco:  
``
CREATE DATABASE produtosdb;
``

Em seguida, configure o arquivo src/main/resources/application.properties:

Configuração do Banco de Dados MySQL:  
````
spring.datasource.url=jdbc:mysql://localhost:3306/produtosdb

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.datasource.username=      # Atualize com seu usuário

spring.datasource.password=       # Atualize com sua senha           
````

Configuração do Hibernate:  
````
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect  
spring.jpa.hibernate.ddl-auto=create  # Use "update" em produção  
````
3️.  Execute a aplicação:  

### 🖥️ Usando o Maven Wrapper:  
````
# Linux / macOS
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
````
### 💡 Ou pela IDE (IntelliJ, Eclipse, VSCode):  
Execute a classe principal:  
``ExemplosistemaApplication.java``

4️. Acesse a aplicação

Abra o navegador e acesse:  

🧾 Página de Cadastro: http://localhost:8080/cadastro  

📃 Página de Listagem: http://localhost:8080/listar  

## 📂 Estrutura de Pastas  
exemplosistema/  
├── .mvn/  
│   └── wrapper/  
│       └── ...  
├── src/  
│   ├── main/  
│   │   ├── java/com/exemplo/sistema/crud/exemplosistema/  
│   │   │   ├── Categoria.java              # Enum com as categorias  
│   │   │   ├── Produto.java                # Entidade JPA  
│   │   │   ├── ProdutoController.java      # Controlador web  
│   │   │   ├── ProdutoDTO.java             # DTO com validações  
│   │   │   ├── ProdutoMapper.java          # Conversor entre Entidade e DTO  
│   │   │   ├── ProdutoRepository.java      # Interface JPA  
│   │   │   └── ExemplosistemaApplication.java  # Classe principal  
│   │   └── resources/  
│   │       ├── templates/                  # Páginas HTML (Thymeleaf)  
│   │       │   ├── editar.html  
│   │       │   ├── index.html  
│   │       │   └── listar.html  
│   │       └── application.properties      # Configurações da aplicação  
│   └── test/java/...                       # Testes unitários  
├── pom.xml                                 # Dependências e build Maven  
├── mvnw / mvnw.cmd                         # Maven Wrapper  
├── .gitignore  
└── README.md  

## 🛣️ Endpoints (Rotas)  
Método	Endpoint	Descrição  
GET	/cadastro	Exibe o formulário de cadastro  
POST	/cadastro	Salva um novo produto  
GET	/listar	Exibe a lista de produtos (pode filtrar com ?categoria=)  
GET	/editar/{id}	Exibe o formulário de edição  
POST	/editar/{id}	Atualiza os dados do produto  
GET	/excluir/{id}	Exclui um produto do banco de dados  

## 📸 Demonstração 

<img width="1080" height="720" alt="teladecadastro" src="https://github.com/user-attachments/assets/b6975175-dc16-4ffc-8e90-05b80a3daeab" />
<img width="1080" height="720" alt="teladelistagem" src="https://github.com/user-attachments/assets/52fa898c-4cc2-403b-b071-79688aa369ac" />



### 🧑‍💻 Autor

Arthur Felipe   
📧 arthurfelipedasilvamatosdev@gmail.com  
🌐 https://github.com/ArthurFelipe27  

## 📄 Licença

Este projeto está licenciado sob a Licença MIT  

💡 Projeto desenvolvido como exemplo de sistema CRUD completo com Spring Boot e Thymeleaf.  
