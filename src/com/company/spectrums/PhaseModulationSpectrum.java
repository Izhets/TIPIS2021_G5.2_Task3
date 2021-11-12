package com.company.spectrums;

import com.company.signals.FrequencyModulationSignalGraph;
import com.company.signals.PhaseModulationSignalGraph;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;

public class PhaseModulationSpectrum {

    public ChartPanel drawSignalSpectrum(double carrierFrequency, double signalFrequency) {
        XYSeries series = new XYSeries("T * (|Math.sin(i) * T / 2)| / i * (T / 2)");

        PhaseModulationSignalGraph pmsg = new PhaseModulationSignalGraph();

        double a = 0;
        double[] Fam = pmsg.getFam(carrierFrequency, signalFrequency);

        for (float i = 0; i < 1500 * carrierFrequency && a <= 10  * carrierFrequency; i++) {
            series.add(a, 0);
            series.add(a, Math.abs(Fam[(int) i]));
            series.add(a, 0);

            a += (2 * Math.PI) / 100;
        }

        System.out.println(a);

        XYDataset xyDataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory
                .createXYLineChart("График спектра амплитудной модуляции" + "\n Частота: " + carrierFrequency + " Гц", "Гц", "А",
                        xyDataset,
                        PlotOrientation.VERTICAL,
                        true, true, true);

        ChartPanel frame =
                new ChartPanel(chart);
        frame.setPreferredSize(new

                Dimension(850, 500));

        return frame;
    }

}
