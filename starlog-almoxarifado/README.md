# StarLog Almoxarifado • CRUD com DAO, Controller e SQLite

Projeto Java (console) com persistência JDBC em SQLite.

## Estrutura
- `model/Produto.java` — entidade
- `factory/ConexaoFactory.java` — conexão com `almoxarifado.db` e criação da tabela
- `dao/ProdutoDAO.java` — contrato da interface CRUD
- `dao/ProdutoDAOSqlite.java` — implementação JDBC (PreparedStatement)
- `controller/ProdutoController.java` — orquestra o DAO
- `main/MainApp.java` — menu de console

## Como executar (VS Code)
1. Crie a pasta `lib` na raiz e coloque dentro `sqlite-jdbc-3.45.1.0.jar`,
   `slf4j-api-1.7.36.jar` e `slf4j-simple-1.7.36.jar` (os mesmos do projeto Aula26Exemplo).
2. Abra a pasta no VS Code e rode `src/br/com/starlog/main/MainApp.java` (F5).
