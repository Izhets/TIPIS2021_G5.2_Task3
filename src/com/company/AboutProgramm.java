package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutProgramm extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextPane textPane1;

    public AboutProgramm() {
        this.setTitle("О программе / ТИПИС2021 | Задача 2");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        this.setLocationRelativeTo(null);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        textPane1.setText("Программа для демонстрации графиков импульсного сигнала (однополярного меандра) и их спектров." +
                " Для использования сначала нужно выбрать частоту сигнала, после чего нажать на кнопку " +
                " \"Построить функцию\" или \"Построить спектр\"");
    }

    private void onOK() {
        this.dispose();
    }

    private void onCancel() {
        this.dispose();
    }

    public static void main( ) {
        AboutProgramm dialog = new AboutProgramm();
        dialog.pack();
        dialog.setVisible(true);
    }
}
