package com.company.signals;

import com.company.utils.DFT;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;

public class PhaseModulationSignalGraph {

    private final double signalFrequency; // Частота модулирующего сигнала
    private final double modulationFrequency; // Частота частотно-модулированного сигнала

    private double Fs = 100;  //Частота дискретизации
    private double Am = 1;  //Амплитуда несущего колебания в отсутствии модуляции

    private double F0 = 1; //Начальная фаза заполнения модулирующего сигнала
    private double W; //Круговая частота модулирующего сигнала

    private double f0 = 1; //Начальная фаза заполнения частотно-модулированного сигнала
    private double w0; //Круговая частота частотно-модулированного сигнала

    private double deltaPSI;

    private double[] t = new double[200]; //Массив с точками

    private double s; //Модулирующий сигнал s(t) - гармоническое (однотональное) колебание
    private double pm; // Фазово-модулированный сигнал

    private float[] array4dft = new float[1000]; //Массив для dft

    public PhaseModulationSignalGraph(double signalFrequency, double modulationFrequency) {
        this.signalFrequency = signalFrequency;
        this.modulationFrequency = modulationFrequency;

        for (double i = 0; i < t.length; i++) {
            t[(int) i] = (i / Fs);
        }

        W = 2 * Math.PI * F0 * signalFrequency;
        w0 = 2 * Math.PI * f0 * modulationFrequency;

        deltaPSI = 1;
    }

    public ChartPanel drawGraph() {
        XYSeries series = new XYSeries("sign(sin(2*Pi*frequency*t)) + 1");

        series.add(0, 1.5);
        series.add(0, -1.5);

        for (int i = 0; i < t.length; i++) {
            pm = Am * Math.cos(w0 * t[i] + f0 + deltaPSI * Math.signum(Math.cos(W * t[i] + F0)));

            array4dft[i] = (float) pm; // Добавление полученных точек в массив
            series.add(t[i] * modulationFrequency, pm);

        }

        XYDataset xyDataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory
                .createXYLineChart("График амплитудной модуляции гармонического сигнала" + "\n Частота: " + signalFrequency + " Гц", "t", "A (U)",
                        xyDataset, PlotOrientation.VERTICAL, true, true, true);
        ChartPanel frame = new ChartPanel(chart);
        frame.setPreferredSize(new Dimension(850, 500));


        return frame;
    }

    public double[] getOutDftArray() {
        drawGraph();
        DFT dft = new DFT();
        return dft.dft(array4dft, 200);
    }

}

