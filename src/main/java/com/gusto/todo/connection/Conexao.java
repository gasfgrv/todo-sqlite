package com.gusto.todo.connection;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class Conexao implements AutoCloseable{

    public static final String URL = "jdbc:sqlite::resource:database/tarefas.db";

    private static Connection connection;

    private static Connection novaConexao() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(URL);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Não foi possível conectar ao banco de dados",
                    "Erro",
                    ERROR_MESSAGE);
        }

        return connection;
    }

    public static Connection getConexao() throws SQLException {
        return connection == null || connection.isClosed()
                ? novaConexao()
                : connection;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
