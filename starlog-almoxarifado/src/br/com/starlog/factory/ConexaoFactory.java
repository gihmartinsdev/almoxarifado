package br.com.starlog.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public final class ConexaoFactory {

    private static final String URL = "jdbc:sqlite:almoxarifado.db";

    private ConexaoFactory() {
        throw new UnsupportedOperationException("ConexaoFactory nao deve ser instanciada!");
    }

    public static Connection obterConexao() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    /** Cria a tabela "produtos" se ela ainda nao existir no arquivo almoxarifado.db. */
    public static void inicializarBanco() {
        String sql = """
            CREATE TABLE IF NOT EXISTS produtos (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                preco REAL NOT NULL,
                estoque INTEGER NOT NULL
            );
        """;
        try (Connection conn = obterConexao();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inicializar o banco: " + e.getMessage(), e);
        }
    }
}
