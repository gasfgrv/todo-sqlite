package com.gusto.todo.model;

public class TarefaBuilder {

    private final Tarefa tarefa;

    private TarefaBuilder() {
        this.tarefa = new Tarefa();
    }

    public static TarefaBuilder builder() {
        return new TarefaBuilder();
    }

    public TarefaBuilder id(int id) {
        this.tarefa.setId(id);
        return this;
    }

    public TarefaBuilder titulo(String titulo) {
        this.tarefa.setTitulo(titulo);
        return this;
    }

    public TarefaBuilder concluido(boolean concluido) {
        this.tarefa.setConcluido(concluido);
        return this;
    }

    public Tarefa build() {
        return this.tarefa;
    }
}
