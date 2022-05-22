package com.gusto.todo.controller;

import com.gusto.todo.exception.ControllerException;
import com.gusto.todo.model.Tarefa;
import java.util.List;

public interface Controller<T, I> {
    void inserir(T t) throws ControllerException;

    List<Tarefa> listar() throws ControllerException;

    void editar(I id, T t) throws ControllerException;

    void remover(I id) throws ControllerException;
}
