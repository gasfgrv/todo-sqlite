package com.gusto.todo.model;

public class TarrefaBuilder {

    private final Tarefa tarefa;

    public TarrefaBuilder() {
        this.tarefa = new Tarefa();
    }

    public static TarrefaBuilder builder() {
        return new TarrefaBuilder();
    }

    public TarrefaBuilder id(int id) {
        this.tarefa.setId(id);
        return this;
    }

    public TarrefaBuilder titulo(String titulo) {
        this.tarefa.setTitulo(titulo);
        return this;
    }

    public TarrefaBuilder concluido(boolean concluido) {
        this.tarefa.setConcluido(concluido);
        return this;
    }

    public Tarefa build() {
        return this.tarefa;
    }
}
