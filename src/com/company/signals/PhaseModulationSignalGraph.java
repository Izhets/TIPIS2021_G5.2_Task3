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

    private double[] Fam;

    public ChartPanel drawGraph(double signalFrequency, double fmFrequency) {
        XYSeries series = new XYSeries("sign(sin(2*Pi*frequency*t)) + 1");

        double Fs = 10000;  //Частота дискретизации

        double Am = 1;  //Амплитуда несущего колебания в отсутствии модуляции

        double f0 = 10; //Начальная фаза высокочастотного заполнения
        double w0 = 2 * Math.PI * f0 * signalFrequency; //Круговая частота

        double F0 = 1;
        double W = 2 * Math.PI * F0 * fmFrequency;


        double[] t = new double[20000]; //Массив с точками

        for (double i = 0; i < t.length; i++) {
            t[(int) i] = (i / Fs);
        }

        double deltaPSI = W * (2);

        double s = 0;
        double fm = 0;
        double f = 0;

        series.add(0, 1.5);
        series.add(0, -1.5);

        for (float i = 0; i < t.length; i++) {
            s = Math.cos(w0 * t[(int) i] + f0); // модулирующий сигнал
            fm = Am * Math.signum(Math.cos(W * t[(int) i] + F0 + deltaPSI)); // фазо-модулированный
            f = s * fm;
            series.add(t[(int) i], f);
        }

        XYDataset xyDataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory
                .createXYLineChart("График амплитудной модуляции гармонического сигнала" + "\n Частота: " + signalFrequency + " Гц", "t", "A (U)",
                        xyDataset,
                        PlotOrientation.VERTICAL,
                        true, true, true);
        ChartPanel frame =
                new ChartPanel(chart);
        frame.setPreferredSize(new Dimension(850, 500));


        return frame;
    }

    public double[] getFam(double signalFrequency, double fmFrequency) {
        double Fs = 10000;  //Частота дискретизации

        double Am = 1;  //Амплитуда несущего колебания в отсутствии модуляции

        double f0 = 10; //Начальная фаза высокочастотного заполнения
        double w0 = 2 * Math.PI * f0 * signalFrequency; //Круговая частота

        double F0 = 1;
        double W = 2 * Math.PI * F0 * fmFrequency;


        double[] t = new double[20000]; //Массив с точками

        for (double i = 0; i < t.length; i++) {
            t[(int) i] = (i / Fs);
        }

        double deltaPSI = W * (2);

        double s = 0;
        double fm = 0;
        double f = 0;

        float[] array4dft = new float[20000]; //Массив для dft

        for (float i = 0; i < t.length; i++) {
            s = Math.cos(w0 * t[(int) i] + f0); // модулирующий сигнал
            fm = Am * Math.signum(Math.cos(W * t[(int) i] + F0 + deltaPSI)); // фазо-модулированный
            f = s * fm;
            array4dft[(int) i] = (float) f;
            System.out.println(i + ": " + f);
        }

        Fam = new DFT().dft(array4dft, 20000);


        return Fam;
    }

}

