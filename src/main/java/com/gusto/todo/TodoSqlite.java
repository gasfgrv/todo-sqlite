package com.gusto.todo;

import com.gusto.todo.controller.Controller;
import com.gusto.todo.controller.TarefaController;
import com.gusto.todo.dao.DAO;
import com.gusto.todo.dao.TarefaDao;
import com.gusto.todo.model.Tarefa;
import com.gusto.todo.view.TarefaView;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.EventQueue;

public class TodoSqlite {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                JOptionPane.showMessageDialog(null,
                        "Não foi possível usar a biblioteca padrão do seu SO.",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE);

            } finally {
                DAO<Tarefa> dao = new TarefaDao();
                Controller<Tarefa, Integer> controller = new TarefaController(dao);
                TarefaView frame = new TarefaView(controller);
                frame.iniciar();
            }
        });
    }
}
