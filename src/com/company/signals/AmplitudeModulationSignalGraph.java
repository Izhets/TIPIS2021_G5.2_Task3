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

public class AmplitudeModulationSignalGraph {

    private double[] Fam;

    public ChartPanel drawGraph(double carrierFrequency, double signalFrequency) {
        XYSeries series = new XYSeries("sign(sin(2*Pi*frequency*t)) + 1");

        double Fs = 10000;  //Частота дискретизации

        double m0 = 1; // Коэффициент модуляции

        double Am = 1;  //Амплитуда несущего колебания в отсутствии модуляции

        double Amax = Am * (1 + m0);
        double Amin = Am * (1 - m0);

        //double m = Amax - Amin / Amax + Amin;

        double As = Amax - Am;

        double m = As / Am;

        double f0 = 1; //Начальная фаза высокочастотного заполнения
        double w0 = 2 * Math.PI * f0 * carrierFrequency; //Круговая частота

        double F0 = 1;
        double W = 2 * Math.PI * F0 * signalFrequency;

        double[] t = new double[20000]; //Массив с точками

        for (double i = 0; i < t.length; i++) {
            t[(int) i] = (i / Fs);
        }

        double f = 0;

        series.add(0, 3);
        series.add(0, -3);

        for (float i = 0; i < t.length / 2; i++) {
            f = Am * (1 + m * Math.signum(Math.cos(W * t[(int) i] * carrierFrequency + F0)))
                    * Math.sin(w0 * t[(int) i] * carrierFrequency + f0);
            series.add(t[(int) i] * carrierFrequency, f);
        }

        XYDataset xyDataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory
                .createXYLineChart("График амплитудной модуляции гармонического сигнала" + "\n Частота: " + carrierFrequency + " Гц", "t", "A (U)",
                        xyDataset,
                        PlotOrientation.VERTICAL,
                        true, true, true);
        ChartPanel frame =
                new ChartPanel(chart);
        frame.setPreferredSize(new Dimension(850, 500));


        return frame;
    }

    public double[] getFam(double carrierFrequency, double signalFrequency) {
        double Fs = 10000;  //Частота дискретизации

        double m0 = 1; // Коэффициент модуляции

        double Am = 1;  //Амплитуда несущего колебания в отсутствии модуляции

        double Amax = Am * (1 + m0);
        double Amin = Am * (1 - m0);

        //double m = Amax - Amin / Amax + Amin;

        double As = Amax - Am;

        double m = As / Am;

        double f0 = 20; //Начальная фаза высокочастотного заполнения
        double w0 = 2 * Math.PI * f0 * carrierFrequency; //Круговая частота

        double F0 = 1;
        double W = 2 * Math.PI * F0 * signalFrequency;

        double[] t = new double[20000]; //Массив с точками

        for (double i = 0; i < t.length; i++) {
            t[(int) i] = (i / Fs);
        }

        double f = 0;

        float[] array4dft = new float[20000]; //Массив для dft

        for (float i = 0; i < t.length; i++) {
//            f = Am * (1 + m * Math.signum(Math.cos(W * t[(int) i] * carrierFrequency + F0)))
//                    * Math.sin(w0 * t[(int) i] * carrierFrequency + f0);
            f = Am * (m * Math.signum(Math.cos(W * t[(int) i] * carrierFrequency + F0)))
                    * Math.sin(w0 * t[(int) i] * carrierFrequency + f0);
            array4dft[(int) i] = (float) f;
        }

        Fam = new DFT().dft(array4dft, 20000);

        return Fam;
    }

}
