package com.gusto.todo.view;

import com.gusto.todo.model.Tarefa;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TarefaTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private static final int TAREFA = 0;
	private static final int CONCLUIDO = 1;
	private static final String ERRO_COLUNA_INVALIDA = "Coluna Inválida!!!";

	private final String[] colunas = {"Tarefa", "Concluído" };

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
		switch (coluna) {
		case TAREFA:
			return dados.get(linha).getTitulo();
		case CONCLUIDO:
			return dados.get(linha).isConcluido();
		default:
			throw new IndexOutOfBoundsException(ERRO_COLUNA_INVALIDA);
		}
	}

	@Override
	public Class<?> getColumnClass(int coluna) {
		switch (coluna) {
		case TAREFA:
			return String.class;
		case CONCLUIDO:
			return Boolean.class;
		default:
			throw new IndexOutOfBoundsException(ERRO_COLUNA_INVALIDA);
		}
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

		switch (coluna) {
		case TAREFA:
			String titulo = (String) valor;
			tarefa.setTitulo(titulo);
			break;
		case CONCLUIDO:
			boolean concluido = (boolean) valor;
			tarefa.setConcluido(concluido);
			break;
		default:
			throw new IndexOutOfBoundsException(ERRO_COLUNA_INVALIDA);
		}

		fireTableCellUpdated(linha, coluna);
	}

	public Tarefa getValue(int linha) {
		return dados.get(linha);
	}

	public int indexOf(Tarefa tarefa) {
		return dados.indexOf(tarefa);
	}

	public void onAdd(Tarefa tarefa) {
		dados.add(tarefa);
		fireTableRowsInserted(indexOf(tarefa), indexOf(tarefa));
	}

	public void onRemove(int linha) {
		dados.remove(linha);
		fireTableRowsDeleted(linha, linha);
	}

	public void onUpdate(Tarefa tarefa) {
		dados.add(tarefa);
		fireTableRowsUpdated(indexOf(tarefa), indexOf(tarefa));
	}

}
