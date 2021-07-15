package com.gusto.todo.dao;

import com.gusto.todo.connection.Conexao;
import com.gusto.todo.exception.DaoException;
import com.gusto.todo.model.Tarefa;
import com.gusto.todo.model.TarrefaBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TarefaDao implements DAO<Tarefa> {

    @Override
    public void inserir(Tarefa t) throws DaoException {
        String sql = "INSERT INTO tarefa (titulo, concluido) VALUES(?,?)";
        try (Connection conn = Conexao.getConexao().conectar();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, t.getTitulo());
            preparedStatement.setBoolean(2, false);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }
    }

    @Override
    public List<Tarefa> listar() throws DaoException {
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT id, titulo, concluido FROM tarefa";
        try (Connection conn = Conexao.getConexao().conectar();
             PreparedStatement preparedStatement = conn.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                tarefas.add(TarrefaBuilder.builder()
                        .id(resultSet.getInt("id"))
                        .titulo(resultSet.getString("titulo"))
                        .concluido(resultSet.getBoolean("concluido"))
                        .build());
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return tarefas;
    }

    @Override
    public void editar(int id, Tarefa t) throws DaoException {
        String sql = "UPDATE tarefa SET titulo = ?, concluido = ? WHERE id = ?";
        try (Connection conn = Conexao.getConexao().conectar();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, t.getTitulo());
            preparedStatement.setBoolean(2, t.isConcluido());
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }
    }

    @Override
    public void remover(int id) throws DaoException {
        String sql = "DELETE FROM tarefa WHERE id = ?";
        try (Connection conn = Conexao.getConexao().conectar();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }
    }

}
