package com.company.spectrums;

import com.company.signals.FrequencyModulationSignalGraph;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;

//Спектр частотной модуляции
public class FrequencyModulationSpectrum {

    public ChartPanel drawSignalSpectrum(double carrierFrequency, double signalFrequency) {
        XYSeries series = new XYSeries("Am cos(w0t + f0 + Beta sin(Wt + F0))");

        double a = 0;

        double[] outDftArray = new FrequencyModulationSignalGraph(carrierFrequency,signalFrequency).getOutDftArray();

        for (int i = 0; i < outDftArray.length; i++) {
            series.add(a, 0);
            series.add(a, Math.abs(outDftArray[i]));
            series.add(a, 0);

            a += (2 * Math.PI) / 25;
        }

        XYDataset xyDataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory
                .createXYLineChart("График спектра частотной модуляции" + "\n Несущая частота: " + carrierFrequency + " Гц" + " " + "Частота высокочастотного заполнения: " + signalFrequency + " Гц", "Гц", "А",
                        xyDataset, PlotOrientation.VERTICAL, true, true, true);

        ChartPanel frame = new ChartPanel(chart);
        frame.setPreferredSize(new Dimension(850, 500));

        return frame;
    }

}
