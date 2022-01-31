package com.gusto.todo.view;

import com.gusto.todo.controller.Controller;
import com.gusto.todo.exception.ControllerException;
import com.gusto.todo.model.Tarefa;
import com.gusto.todo.model.TarefaBuilder;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TarefaView extends JFrame {

    private static final long serialVersionUID = 1L;

    private final transient Controller<Tarefa, Integer> controller;

    public TarefaView(Controller<Tarefa, Integer> controller) {
        this.controller = controller;
    }

    public void iniciar() {
        renderizarTela();
        setTitle("Tarefas");
        setSize(600, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void renderizarTela() {
        JPanel contentPane = new JPanel();

        JPanel panelNovatarefa = new JPanel();
        JLabel lblNovaTarefa = new JLabel("Nova tarefa: ");
        JTextField textFieldNovaTarefa = new JTextField();
        JButton btnAdicionar = new JButton("Adicionar");

        JPanel panelTodasTarefas = new JPanel();
        JScrollPane scrollPane = new JScrollPane();
        JTable tableTarefas = new JTable();
        TarefaTableModel tableModel = new TarefaTableModel(controller.listar());

        JPanel panelOpcoes = new JPanel();
        JButton btnAlterarTarefa = new JButton("Concluir Tarefa");
        JButton btnRenomearTarefa = new JButton("Renomear Tarefa");
        JButton btnExcluirTarefa = new JButton("Excluir Tarefa");

        EmptyBorder emptyBorder = new EmptyBorder(5, 5, 5, 5);
        BorderLayout borderLayoutContentPane = new BorderLayout(0, 0);
        BorderLayout borderLayoutPanelNovatarefa = new BorderLayout(0, 0);
        BorderLayout borderLayoutTodasTarefas = new BorderLayout(0, 0);
        Dimension dimension = new Dimension(500, 300);
        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 5, 5);

        contentPane.setBorder(emptyBorder);
        contentPane.setLayout(borderLayoutContentPane);
        setContentPane(contentPane);

        contentPane.add(panelNovatarefa, BorderLayout.NORTH);
        panelNovatarefa.setLayout(borderLayoutPanelNovatarefa);
        panelNovatarefa.add(lblNovaTarefa, BorderLayout.WEST);
        textFieldNovaTarefa.addKeyListener(getKeyListener(textFieldNovaTarefa, tableModel));
        panelNovatarefa.add(textFieldNovaTarefa, BorderLayout.CENTER);
        textFieldNovaTarefa.setColumns(10);
        btnAdicionar.addActionListener(e -> adicionarTarefa(textFieldNovaTarefa, tableModel));
        panelNovatarefa.add(btnAdicionar, BorderLayout.EAST);

        contentPane.add(panelTodasTarefas, BorderLayout.CENTER);
        panelTodasTarefas.setLayout(borderLayoutTodasTarefas);
        panelTodasTarefas.add(scrollPane, BorderLayout.CENTER);
        tableTarefas.setFillsViewportHeight(true);
        tableTarefas.addMouseListener(getMouseClickedEvent(tableTarefas, btnRenomearTarefa, btnAlterarTarefa, btnExcluirTarefa));
        tableTarefas.setModel(tableModel);
        tableTarefas.setPreferredScrollableViewportSize(dimension);
        tableTarefas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(tableTarefas);

        contentPane.add(panelOpcoes, BorderLayout.SOUTH);
        panelOpcoes.setLayout(flowLayout);
        btnAlterarTarefa.setEnabled(false);
        btnAlterarTarefa.addActionListener(e -> getActionListenerAlterarTarefa(tableTarefas));
        btnRenomearTarefa.addActionListener(e -> actionListenerRenomearTarefa(tableTarefas));
        btnRenomearTarefa.setEnabled(false);
        panelOpcoes.add(btnRenomearTarefa);
        panelOpcoes.add(btnAlterarTarefa);
        btnExcluirTarefa.setEnabled(false);
        btnExcluirTarefa.addActionListener(e -> actionListenerRemoverTarefa(tableTarefas));
        panelOpcoes.add(btnExcluirTarefa);
    }

    private void actionListenerRemoverTarefa(JTable tableTarefas) {
        TarefaTableModel tableModel = (TarefaTableModel) tableTarefas.getModel();
        int linha = tableTarefas.getSelectedRow();
        Tarefa tarefaExcluida = tableModel.getValue(linha);
        removerTarefa(tarefaExcluida, tableModel);
    }

    private void actionListenerRenomearTarefa(JTable tableTarefas) {
        TarefaTableModel tableModel = (TarefaTableModel) tableTarefas.getModel();
        int linha = tableTarefas.getSelectedRow();
        Tarefa tarefaRenomeada = tableModel.getValue(linha);
        String tituloTarefa = JOptionPane.showInputDialog("Digite o nome da nova tarefa");
        renomearTarefa(tarefaRenomeada, tituloTarefa, tableModel);
    }

    private KeyAdapter getKeyListener(JTextField textFieldNovaTarefa, TarefaTableModel tableModel) {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                adicionarTarefaKeyPressed(e, textFieldNovaTarefa, tableModel);
            }
        };
    }

    private void adicionarTarefaKeyPressed(KeyEvent e, JTextField textFieldNovaTarefa, TarefaTableModel tableModel) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            adicionarTarefa(textFieldNovaTarefa, tableModel);
        }
    }

    private MouseAdapter getMouseClickedEvent(JTable tableTarefas, JButton btnRenomearTarefa, JButton btnAlterarTarefa, JButton btnExcluirTarefa) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ativarBotoes(tableTarefas, btnRenomearTarefa, btnAlterarTarefa, btnExcluirTarefa);
            }
        };
    }

    private void ativarBotoes(JTable tableTarefas, JButton btnRenomearTarefa, JButton btnAlterarTarefa, JButton btnExcluirTarefa) {
        if (tableTarefas.getSelectedRow() >= 0) {
            btnRenomearTarefa.setEnabled(true);
            btnAlterarTarefa.setEnabled(true);
            btnExcluirTarefa.setEnabled(true);
            alteraTextoBtn(tableTarefas, btnAlterarTarefa);
        }
    }

    private void adicionarTarefa(JTextField textFieldNovaTarefa, TarefaTableModel tableModel) {
        try {
            Tarefa novaTarefa = TarefaBuilder.builder()
                    .titulo(textFieldNovaTarefa.getText().trim())
                    .concluido(false)
                    .build();
            this.controller.inserir(novaTarefa);
            tableModel.onAdd(novaTarefa);
            textFieldNovaTarefa.setText("");
        } catch (ControllerException e) {
            e.printStackTrace();
        }
    }

    private void removerTarefa(Tarefa tarefa, TarefaTableModel tableModel) {
        try {
            this.controller.remover(tarefa.getId());
            int linha = tableModel.indexOf(tarefa);
            tableModel.onRemove(linha);
        } catch (ControllerException e) {
            e.printStackTrace();
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

    private void concluirTarefa(Tarefa tarefa, TarefaTableModel tableModel) {
        try {
            tarefa.setConcluido(true);
            this.controller.editar(tarefa.getId(), tarefa);
            tableModel.onUpdate(tarefa);
        } catch (ControllerException e) {
            e.printStackTrace();
        }
    }

    private void refazerTarefa(Tarefa tarefa, TarefaTableModel tableModel) {
        try {
            tarefa.setConcluido(false);
            this.controller.editar(tarefa.getId(), tarefa);
            tableModel.onUpdate(tarefa);
        } catch (ControllerException e) {
            e.printStackTrace();
        }
    }

    private void renomearTarefa(Tarefa tarefa, String novoTitulo, TarefaTableModel tableModel) {
        try {
            tarefa.setTitulo(novoTitulo);
            this.controller.editar(tarefa.getId(), tarefa);
            tableModel.onUpdate(tarefa);
        } catch (ControllerException e) {
            e.printStackTrace();
        }
    }

    private void getActionListenerAlterarTarefa(JTable tableTarefas) {
        TarefaTableModel tableModel = (TarefaTableModel) tableTarefas.getModel();
        Tarefa tarefaEditada = tableModel.getValue(tableTarefas.getSelectedRow());
        Boolean valorDaLinha = (Boolean) tableModel.getValueAt(tableTarefas.getSelectedRow(), 1);

        if (Boolean.TRUE == valorDaLinha) refazerTarefa(tarefaEditada, tableModel);
        else concluirTarefa(tarefaEditada, tableModel);
    }

}
