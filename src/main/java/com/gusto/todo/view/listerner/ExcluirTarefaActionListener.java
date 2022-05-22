package com.gusto.todo.view.listerner;

import com.gusto.todo.controller.Controller;
import com.gusto.todo.model.Tarefa;
import com.gusto.todo.view.TarefaTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;

public class ExcluirTarefaActionListener implements ActionListener {
    private final TarefaTableModel tableModel;
    private final JTable tableTarefas;
    private final Controller<Tarefa, Integer> controller;

    public ExcluirTarefaActionListener(TarefaTableModel tableModel, JTable tableTarefas, Controller<Tarefa, Integer> controller) {
        this.tableModel = tableModel;
        this.tableTarefas = tableTarefas;
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        int linha = tableTarefas.getSelectedRow();

        Tarefa tarefa = tableModel.getValueRow(linha);
        controller.remover(tarefa.getId());

        tableModel.remove(linha);
    }
}
