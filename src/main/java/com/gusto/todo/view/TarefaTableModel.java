package com.gusto.todo.view;

import com.gusto.todo.model.Tarefa;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TarefaTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private final String[] colunas = {"Tarefa", "Concluído"};

    private final transient List<Tarefa> dados;

    public TarefaTableModel(List<Tarefa> dados) {
        this.dados = dados;
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        return coluna == 0
                ? dados.get(linha).getTitulo()
                : dados.get(linha).isConcluido();
    }

    @Override
    public Class<?> getColumnClass(int coluna) {
        return coluna == 0
                ? String.class
                : Boolean.class;
    }

    @Override
    public String getColumnName(int coluna) {
        return colunas[coluna];
    }

    @Override
    public boolean isCellEditable(int linha, int coluna) {
        return false;
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        Tarefa tarefa = dados.get(linha);

        if (coluna == 0) {
            String titulo = (String) valor;
            tarefa.setTitulo(titulo);
        } else {
            boolean concluido = (boolean) valor;
            tarefa.setConcluido(concluido);
        }

        fireTableCellUpdated(linha, coluna);
    }

    public Tarefa getValueRow(int linha) {
        return dados.get(linha);
    }

    public int getLineOf(Tarefa tarefa) {
        return dados.indexOf(tarefa);
    }

    public void add(Tarefa tarefa) {
        dados.add(tarefa);
        fireTableRowsInserted(getLineOf(tarefa), getLineOf(tarefa));
    }

    public void remove(int linha) {
        dados.remove(linha);
        fireTableRowsDeleted(linha, linha);
    }

    public void update(Tarefa tarefa) {
        dados.add(tarefa.getId(), tarefa);
        fireTableRowsUpdated(getLineOf(tarefa), getLineOf(tarefa));
    }

}
