package com.company;

import com.company.signals.AmplitudeModulationSignalGraph;
import com.company.signals.FrequencyModulationSignalGraph;
import com.company.signals.PhaseModulationSignalGraph;
import com.company.spectrums.AmplitudeModulationSpectrum;
import com.company.spectrums.FrequencyModulationSpectrum;
import com.company.spectrums.PhaseModulationSpectrum;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Window extends JFrame {
    private JPanel mainPanel;
    private JPanel jfreeChartPanel;
    private JButton createFunctionButton;
    private JButton createSpectrumButton;
    private JSpinner carrierFrequencySpinner;
    private JComboBox comboBox1;
    private JSpinner signalFrequencySpinner;
    private JMenuItem exit;
    private JMenuItem aboutProgramm;

    public Window() throws NullPointerException, IOException {
        carrierFrequencySpinner.setValue(1);
        signalFrequencySpinner.setValue(1);
        this.setTitle("ТИПИС2021 | Задача 2 | Хныкин Д.Е. | Группа 5.2");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);

        //Create menuBar
        JMenuBar nb = new JMenuBar();

        JMenu file = new JMenu("Файл");
        nb.add(file);
        exit = new JMenuItem("Выход");
        file.add(exit);
        aboutProgramm = new JMenuItem("О программе");
        nb.add(aboutProgramm);

        this.setJMenuBar(nb);
        //Create menuBar

        //Add first graph with run program
        jfreeChartPanel.removeAll();
        AmplitudeModulationSignalGraph harmonicSignalGraph = new AmplitudeModulationSignalGraph();
        jfreeChartPanel
                .add(harmonicSignalGraph.drawGraph(getCarrierFrequencySpinner(), getSignalFrequencySpinner()));
        jfreeChartPanel.updateUI();
        //Add first graph with run program

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });

        aboutProgramm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AboutProgramm aboutProgramm = new AboutProgramm();
                aboutProgramm.main();
            }
        });


        createFunctionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < comboBox1.getItemCount(); i++) {
                    if (comboBox1.getSelectedIndex() == 0) {
                        jfreeChartPanel.removeAll();
                        AmplitudeModulationSignalGraph harmonicSignalGraph = new AmplitudeModulationSignalGraph();
                        jfreeChartPanel
                                .add(harmonicSignalGraph.drawGraph(getCarrierFrequencySpinner(), getSignalFrequencySpinner()));
                        jfreeChartPanel.updateUI();
                    } else if (comboBox1.getSelectedIndex() == 1) {
                        jfreeChartPanel.removeAll();
                        FrequencyModulationSignalGraph fmsg = new FrequencyModulationSignalGraph();
                        jfreeChartPanel
                                .add(fmsg.drawGraph(getCarrierFrequencySpinner(), getSignalFrequencySpinner()));
                        jfreeChartPanel.updateUI();
                    } else if (comboBox1.getSelectedIndex() == 2) {
                        jfreeChartPanel.removeAll();
                        PhaseModulationSignalGraph pmsg = new PhaseModulationSignalGraph();
                        jfreeChartPanel
                                .add(pmsg.drawGraph(getCarrierFrequencySpinner(), getSignalFrequencySpinner()));
                        jfreeChartPanel.updateUI();
                    }
                }
            }
        });

        createSpectrumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < comboBox1.getItemCount(); i++) {
                    if (comboBox1.getSelectedIndex() == 0) {
                        jfreeChartPanel.removeAll();
                        AmplitudeModulationSpectrum amplitudeModulationSpectrum = new AmplitudeModulationSpectrum();
                        jfreeChartPanel
                                .add(amplitudeModulationSpectrum.drawSignalSpectrum(getCarrierFrequencySpinner(), getSignalFrequencySpinner()));
                        jfreeChartPanel.updateUI();
                    } else if (comboBox1.getSelectedIndex() == 1) {
                        jfreeChartPanel.removeAll();
                        FrequencyModulationSpectrum fms = new FrequencyModulationSpectrum();
                        jfreeChartPanel
                                .add(fms.drawSignalSpectrum(getCarrierFrequencySpinner(), getSignalFrequencySpinner()));
                        jfreeChartPanel.updateUI();
                    } else if (comboBox1.getSelectedIndex() == 2) {
                        jfreeChartPanel.removeAll();
                        PhaseModulationSpectrum pms = new PhaseModulationSpectrum();
                        jfreeChartPanel
                                .add(pms.drawSignalSpectrum(getCarrierFrequencySpinner(), getSignalFrequencySpinner()));
                        jfreeChartPanel.updateUI();
                    }
                }
            }
        });
    }

    public int getCarrierFrequencySpinner() {
        return (int) carrierFrequencySpinner.getValue();
    }

    public int getSignalFrequencySpinner() {
        return (int) signalFrequencySpinner.getValue();
    }

    private void createUIComponents() {
        jfreeChartPanel = new JPanel();
    }
}