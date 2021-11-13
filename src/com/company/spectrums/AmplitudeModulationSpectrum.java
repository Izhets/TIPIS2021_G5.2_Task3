package com.company.spectrums;

import com.company.signals.AmplitudeModulationSignalGraph;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;

//Спектр амплитудной модуляции
public class AmplitudeModulationSpectrum {

    public ChartPanel drawSignalSpectrum(double signalFrequency, double modulationFrequency) {
        XYSeries series = new XYSeries("T * (|Math.sin(i) * T / 2)| / i * (T / 2)");

        double a = 0;
        double[] outDftArray = new AmplitudeModulationSignalGraph(signalFrequency, modulationFrequency).getOutDftArray();

        for (int i = 0; i < outDftArray.length; i++) {
            series.add(a, 0);
            series.add(a, Math.abs(outDftArray[i] * 10 / 6.5));
            series.add(a, 0);

            a += (2 * Math.PI) / 25;
        }

        XYDataset xyDataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory
                .createXYLineChart("График спектра амплитудной модуляции" + "\n Частота: " + signalFrequency + " Гц", "Гц", "А",
                        xyDataset, PlotOrientation.VERTICAL, true, true, true);

        ChartPanel frame = new ChartPanel(chart);
        frame.setPreferredSize(new Dimension(850, 500));

        return frame;
    }

}