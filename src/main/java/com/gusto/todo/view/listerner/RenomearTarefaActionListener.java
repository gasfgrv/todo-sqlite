package com.gusto.todo.view.listerner;

import com.gusto.todo.controller.Controller;
import com.gusto.todo.model.Tarefa;
import com.gusto.todo.view.TarefaTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class RenomearTarefaActionListener implements ActionListener {

    private final TarefaTableModel tableModel;
    private final JTable tableTarefas;
    private final Controller<Tarefa, Integer> controller;

    public RenomearTarefaActionListener(TarefaTableModel tableModel, JTable tableTarefas, Controller<Tarefa, Integer> controller) {
        this.tableModel = tableModel;
        this.tableTarefas = tableTarefas;
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        int linha = tableTarefas.getSelectedRow();

        Tarefa tarefaRenomeada = tableModel.getValueRow(linha);
        String tituloTarefa = JOptionPane.showInputDialog("Digite o nome da nova tarefa");
        tarefaRenomeada.setTitulo(tituloTarefa);

        controller.editar(tarefaRenomeada.getId(), tarefaRenomeada);

        tableModel.update(tarefaRenomeada);
    }

}
