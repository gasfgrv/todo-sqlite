package com.gusto.todo.view.listerner;

import com.gusto.todo.controller.Controller;
import com.gusto.todo.model.Tarefa;
import com.gusto.todo.model.TarefaBuilder;
import com.gusto.todo.view.TarefaTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

public class NovaTarefaActionListener implements ActionListener {

    private final TarefaTableModel tableModel;
    private final JTextField tarefa;
    private final Controller<Tarefa, Integer> controller;

    public NovaTarefaActionListener(TarefaTableModel tableModel, JTextField tarefa, Controller<Tarefa, Integer> controller) {
        this.tableModel = tableModel;
        this.tarefa = tarefa;
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Tarefa novaTarefa = TarefaBuilder.builder()
                .titulo(tarefa.getText().trim())
                .concluido(false)
                .build();

        controller.inserir(novaTarefa);

        tableModel.add(novaTarefa);

        tarefa.setText("");
    }

}
