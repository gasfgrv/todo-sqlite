package com.gusto.todo.dao;

import java.util.List;

import com.gusto.todo.exception.DaoException;

public interface DAO<T> {

	void inserir(T t) throws DaoException;

	List<T> listar() throws DaoException;

	void editar(int id, T t) throws DaoException;

	void remover(int id) throws DaoException;

}
