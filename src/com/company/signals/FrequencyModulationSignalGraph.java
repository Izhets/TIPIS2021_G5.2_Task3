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

public class FrequencyModulationSignalGraph {

    private final double signalFrequency; //
    private final double modulationFrequency; //

    private double Fs = 100;  //Частота дискретизации
    private double Am = 1;  //Амплитуда несущего колебания в отсутствии модуляции

    private double F0 = 1; //Начальная фаза заполнения модулирующего сигнала
    private double W; //Круговая частота модулирующего сигнала

    private double f0 = 1; //Начальная фаза заполнения частотно-модулированного сигнала
    private double w0; //Круговая частота частотно-модулированного сигнала

    private double deltaw = 10;
    private double Beta;

    private double[] t = new double[200]; //Массив с точками

    private double s; //Модулирующий сигнал s(t) - гармоническое (однотональное) колебание
    double fm; // Частотно-модулированный сигнал

    public float[] array4dft = new float[1000]; //Массив для dft

    public FrequencyModulationSignalGraph(double signalFrequency, double modulationFrequency) {
        this.signalFrequency = signalFrequency;
        this.modulationFrequency = modulationFrequency;

        for (double i = 0; i < t.length; i++) {
            t[(int) i] = (i / Fs);
        }

        W = 2 * Math.PI * F0 * signalFrequency;
        w0 = 2 * Math.PI * f0 * modulationFrequency;

        Beta = deltaw / W;
    }

    public ChartPanel drawGraph() {
        XYSeries series = new XYSeries("Am cos(w0t + f0 + Beta sin(Wt + F0))");

        // 2 холостые точки для того, чтобы уменьшить область просмотра
        series.add(0, 1.5);
        series.add(0, -1.5);

        for (int i = 0; i < t.length; i++) {
            fm = Math.cos(w0 * t[i] + f0 + Beta * Math.signum(Math.cos(W * t[i] + F0)));

            array4dft[i] = (float) fm; // Добавление полученных точек в массив

            series.add(t[i] * modulationFrequency, fm); // Печать на график
        }

        XYDataset xyDataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory
                .createXYLineChart("График сигнала частотной модуляции меандром" + "\n Несущая частота: " + signalFrequency + " Гц" + " " + "Частота высокочастотного заполнения: " + modulationFrequency + " Гц", "Гц", "А",
                        xyDataset, PlotOrientation.VERTICAL, true, true, true);
        ChartPanel frame = new ChartPanel(chart);
        frame.setPreferredSize(new Dimension(850, 500));

        return frame;
    }

    // Getter, который отдаст преобразованный массив точек (у) после преобразоания Фурье
    public double[] getOutDftArray() {
        drawGraph();
        DFT dft = new DFT();
        return dft.dft(array4dft, 200);
    }

}
