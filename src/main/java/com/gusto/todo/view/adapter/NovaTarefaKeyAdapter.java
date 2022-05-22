package com.gusto.todo.view.adapter;

import com.gusto.todo.controller.Controller;
import com.gusto.todo.model.Tarefa;
import com.gusto.todo.model.TarefaBuilder;
import com.gusto.todo.view.TarefaTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;

public class NovaTarefaKeyAdapter extends KeyAdapter {

    private final TarefaTableModel tableModel;
    private final JTextField tarefa;
    private final Controller<Tarefa, Integer> controller;

    public NovaTarefaKeyAdapter(TarefaTableModel tableModel, JTextField tarefa, Controller<Tarefa, Integer> controller) {
        this.tableModel = tableModel;
        this.tarefa = tarefa;
        this.controller = controller;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            Tarefa novaTarefa = TarefaBuilder.builder()
                    .titulo(tarefa.getText().trim())
                    .concluido(false)
                    .build();

            controller.inserir(novaTarefa);
            tableModel.add(novaTarefa);

            tarefa.setText("");
        }
    }

}
