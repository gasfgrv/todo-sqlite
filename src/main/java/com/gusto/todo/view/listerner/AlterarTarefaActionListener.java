package com.gusto.todo.view.listerner;

import com.gusto.todo.controller.Controller;
import com.gusto.todo.model.Tarefa;
import com.gusto.todo.view.TarefaTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;

public class AlterarTarefaActionListener implements ActionListener {

    private final TarefaTableModel tableModel;
    private final JTable tableTarefas;
    private final Controller<Tarefa, Integer> controller;

    public AlterarTarefaActionListener(TarefaTableModel tableModel, JTable tableTarefas, Controller<Tarefa, Integer> controller) {
        this.tableModel = tableModel;
        this.tableTarefas = tableTarefas;
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Tarefa tarefaEditada = tableModel.getValueRow(tableTarefas.getSelectedRow());
        Boolean valorDaLinha = (Boolean) tableModel.getValueAt(tableTarefas.getSelectedRow(), 1);

        tarefaEditada.setConcluido(Boolean.TRUE != valorDaLinha);

        controller.editar(tarefaEditada.getId(), tarefaEditada);

        tableModel.update(tarefaEditada);
    }

}
