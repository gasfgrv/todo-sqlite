package com.gusto.todo.view;

import com.gusto.todo.controller.Controller;
import com.gusto.todo.model.Tarefa;
import com.gusto.todo.view.adapter.NovaTarefaKeyAdapter;
import com.gusto.todo.view.adapter.TodasTarefasMouseAdapter;
import com.gusto.todo.view.listerner.AlterarTarefaActionListener;
import com.gusto.todo.view.listerner.ExcluirTarefaActionListener;
import com.gusto.todo.view.listerner.NovaTarefaActionListener;
import com.gusto.todo.view.listerner.RenomearTarefaActionListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

public class TarefaView extends JFrame {

    private static final long serialVersionUID = 1L;
    private final TarefaTableModel tableModel;

    private final transient Controller<Tarefa, Integer> controller;

    public TarefaView(TarefaTableModel tableModel, Controller<Tarefa, Integer> controller) {
        this.tableModel = tableModel;
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

        EmptyBorder emptyBorder = new EmptyBorder(5, 5, 5, 5);
        contentPane.setBorder(emptyBorder);

        BorderLayout borderLayoutContentPane = new BorderLayout(0, 0);
        contentPane.setLayout(borderLayoutContentPane);

        JPanel novatarefa = montarPanelNovatarefa();
        JPanel todasTarefas = montarPanelTodasTarefas();
        JPanel panelOpcoes = montarPanelOpcoes();

        adicionarListenersEAdapters(novatarefa, todasTarefas, panelOpcoes);

        contentPane.add(novatarefa, BorderLayout.NORTH);
        contentPane.add(todasTarefas, BorderLayout.CENTER);
        contentPane.add(panelOpcoes, BorderLayout.SOUTH);

        setContentPane(contentPane);
    }

    private void adicionarListenersEAdapters(JPanel novatarefa, JPanel todasTarefas, JPanel panelOpcoes) {
        JTextField textFieldNovaTarefa = (JTextField) novatarefa.getComponent(1);
        textFieldNovaTarefa.addKeyListener(new NovaTarefaKeyAdapter(tableModel, textFieldNovaTarefa, controller));

        JButton btnAdicionar = (JButton) novatarefa.getComponent(2);
        btnAdicionar.addActionListener(new NovaTarefaActionListener(tableModel, textFieldNovaTarefa, controller));

        JScrollPane scrollPane = (JScrollPane) todasTarefas.getComponent(0);
        JViewport viewport = (JViewport) scrollPane.getComponent(0);
        JTable tableTarefas = (JTable) viewport.getComponent(0);
        tableTarefas.addMouseListener(new TodasTarefasMouseAdapter(panelOpcoes, tableTarefas));

        JButton btnAlterarTarefa = (JButton) panelOpcoes.getComponent(0);
        btnAlterarTarefa.addActionListener(new AlterarTarefaActionListener(tableModel, tableTarefas, controller));

        JButton btnRenomearTarefa = (JButton) panelOpcoes.getComponent(1);
        btnRenomearTarefa.addActionListener(new RenomearTarefaActionListener(tableModel, tableTarefas, controller));

        JButton btnExcluirTarefa = (JButton) panelOpcoes.getComponent(2);
        btnExcluirTarefa.addActionListener(new ExcluirTarefaActionListener(tableModel, tableTarefas, controller));
    }

    private JPanel montarPanelOpcoes() {
        JPanel panelOpcoes = new JPanel();

        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 5, 5);
        panelOpcoes.setLayout(flowLayout);

        JButton btnAlterarTarefa = new JButton("Concluir Tarefa");
        btnAlterarTarefa.setEnabled(false);
        panelOpcoes.add(btnAlterarTarefa);

        JButton btnRenomearTarefa = new JButton("Renomear Tarefa");
        btnRenomearTarefa.setEnabled(false);
        panelOpcoes.add(btnRenomearTarefa);

        JButton btnExcluirTarefa = new JButton("Excluir Tarefa");
        btnExcluirTarefa.setEnabled(false);
        panelOpcoes.add(btnExcluirTarefa);

        return panelOpcoes;
    }


    private JPanel montarPanelTodasTarefas() {
        JPanel panelTodasTarefas = new JPanel();

        BorderLayout borderLayoutTodasTarefas = new BorderLayout(0, 0);
        panelTodasTarefas.setLayout(borderLayoutTodasTarefas);

        JTable tableTarefas = new JTable(tableModel);
        tableTarefas.setFillsViewportHeight(true);
        tableTarefas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        Dimension dimension = new Dimension(500, 300);
        tableTarefas.setPreferredScrollableViewportSize(dimension);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(tableTarefas);
        panelTodasTarefas.add(scrollPane, BorderLayout.CENTER);

        return panelTodasTarefas;
    }

    private JPanel montarPanelNovatarefa() {
        JPanel panelNovatarefa = new JPanel();

        BorderLayout borderLayoutPanelNovatarefa = new BorderLayout(0, 0);
        panelNovatarefa.setLayout(borderLayoutPanelNovatarefa);

        JLabel lblNovaTarefa = new JLabel("Nova tarefa: ");
        panelNovatarefa.add(lblNovaTarefa, BorderLayout.WEST);

        JTextField textFieldNovaTarefa = new JTextField();
        textFieldNovaTarefa.setColumns(10);
        panelNovatarefa.add(textFieldNovaTarefa, BorderLayout.CENTER);

        JButton btnAdicionar = new JButton("Adicionar");
        panelNovatarefa.add(btnAdicionar, BorderLayout.EAST);

        return panelNovatarefa;
    }

}
