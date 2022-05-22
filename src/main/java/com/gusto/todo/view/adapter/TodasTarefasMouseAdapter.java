package com.gusto.todo.view.adapter;

import com.gusto.todo.view.TarefaTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

public class TodasTarefasMouseAdapter extends MouseAdapter {

    private final JPanel panelOpcoes;
    private final JTable tableTarefas;

    public TodasTarefasMouseAdapter(JPanel panelOpcoes, JTable tableTarefas) {
        this.panelOpcoes = panelOpcoes;
        this.tableTarefas = tableTarefas;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JButton btnAlterarTarefa = (JButton) panelOpcoes.getComponent(0);
        JButton btnRenomearTarefa = (JButton) panelOpcoes.getComponent(1);
        JButton btnExcluirTarefa = (JButton) panelOpcoes.getComponent(2);

        if (tableTarefas.getSelectedRow() >= 0) {
            btnRenomearTarefa.setEnabled(true);
            btnAlterarTarefa.setEnabled(true);
            btnExcluirTarefa.setEnabled(true);
            alteraTextoBtn(tableTarefas, btnAlterarTarefa);
        }
    }

    private void alteraTextoBtn(JTable tableTarefas, JButton btnAlterarTarefa) {
        TarefaTableModel tableModel = (TarefaTableModel) tableTarefas.getModel();
        Boolean valorDaLinha = (Boolean) tableModel.getValueAt(tableTarefas.getSelectedRow(), 1);

        String titulo = Boolean.TRUE.equals(valorDaLinha)
                ? "Refazer Tarefa"
                : "Concluir Tarefa";

        btnAlterarTarefa.setText(titulo);
    }

}
