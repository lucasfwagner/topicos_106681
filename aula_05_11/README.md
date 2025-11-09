# Sistema de Biblioteca Digital

Projeto desenvolvido em **Jakarta EE com Quarkus**, demonstrando:  
- Arquitetura em camadas (Entity, Repository, Service, Controller)  
- Relacionamentos JPA (One-to-Many, Many-to-One)  
- Interface web com **Jakarta Faces (JSF/Facelets)**  
- Banco de dados **PostgreSQL** com dados de exemplo

---

## Estrutura do Projeto

src/main/java/com/biblioteca/
├─ entity/ -> Entidades JPA (Autor, Livro, Emprestimo)
├─ repository/ -> Repositórios JPA
├─ service/ -> Lógica de negócio e métodos transacionais
├─ controller/ -> Managed Beans JSF

src/main/resources/
├─ META-INF/resources/ -> Arquivos XHTML (interface)
└─ import.sql -> Dados de exemplo para inicialização

application.properties -> Configuração do Quarkus e do banco
pom.xml -> Dependências Maven

---

## Pré-requisitos

- Java 21+  
- Maven  
- PostgreSQL rodando na porta 5432  
- Banco de dados: `biblioteca_digital`  
- Usuário: `biblioteca` / Senha: `biblioteca123`  

---

## Como rodar

1. Clonar o projeto:  
```bash
git clone <repositório>
cd biblioteca-digital

2. Configurar o banco de dados no application.properties se necessário.

3. Rodar em modo desenvolvimento:
mvn quarkus:dev

4. Acessar no navegador:
http://localhost:8080

----
Testar dados de exemplo

O projeto já contém import.sql com:

 - 3 Autores

 - 3 Livros

 - 2 Empréstimos ativos


 ---
 Observações

Para adicionar novos registros via interface ou serviço, métodos transacionais são obrigatórios (@Transactional).

As tabelas são recriadas em cada execução no modo dev (drop-and-create).
