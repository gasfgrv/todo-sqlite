package com.gusto.todo.connection;

import java.sql.Connection;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

public class Conexao {

    private static Conexao conexao;

    private Connection connection;

    private Conexao() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Conexao getConexao() {
        return conexao == null ? new Conexao() : conexao;
    }

    public Connection conectar() {
        try {
            setConnetion();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            showMessageDialog(null,
                    "Não foi possível conectar ao banco de dados",
                    "Erro",
                    ERROR_MESSAGE);
        }
        return connection;
    }

    private void setConnetion() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = getConnection("jdbc:sqlite::resource:database/tarefas.db");
        }
    }

}
