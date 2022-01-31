package com.gusto.todo.controller;

import com.gusto.todo.dao.DAO;
import com.gusto.todo.exception.ControllerException;
import com.gusto.todo.exception.DaoException;
import com.gusto.todo.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class TarefaController implements Controller<Tarefa, Integer> {

    private final DAO<Tarefa> dao;

    public TarefaController(DAO<Tarefa> dao) {
        this.dao = dao;
    }

    @Override
    public void inserir(Tarefa tarefa) throws ControllerException {
        try {
            dao.inserir(tarefa);
        } catch (DaoException e) {
            throw new ControllerException(e);
        }
    }

    @Override
    public List<Tarefa> listar() throws ControllerException {
        List<Tarefa> tarefas = new ArrayList<>();

        try {
            tarefas = dao.listar();
        } catch (DaoException e) {
            throw new ControllerException(e);
        }

        return tarefas;
    }

    @Override
    public void editar(Integer id, Tarefa tarefa) throws ControllerException {
        try {
            dao.editar(id, tarefa);
        } catch (DaoException e) {
            throw new ControllerException(e);
        }
    }

    @Override
    public void remover(Integer id) throws ControllerException {
        try {
            dao.remover(id);
        } catch (DaoException e) {
            throw new ControllerException(e);
        }
    }

}
