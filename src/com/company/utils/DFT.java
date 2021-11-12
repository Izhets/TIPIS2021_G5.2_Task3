package com.company.utils;

public class DFT {

    private double cos(int index, int frequency, int sampleRate) {
        return Math.cos((2 * Math.PI * frequency * index) / sampleRate);
    }
    private double sin(int index, int frequency, int sampleRate) {
        return Math.sin((2 * Math.PI * frequency * index) / sampleRate);
    }

    public double[] dft(float[] frame, int sampleRate) {
        final int resultSize = sampleRate / 2;
        double[] result = new double[resultSize * 2];
        for (int i = 0; i < result.length / 2; i++) {
            int frequency = i;
            for (int j = 0; j < frame.length; j++) {
                result[2*i] +=frame[j] * cos(j, frequency, sampleRate);
                result[2*i + 1] +=frame[j] * sin(j, frequency, sampleRate);
            }
            result[2*i] =result[2*i] / resultSize;
            result[2*i + 1] = result[2*i + 1] / resultSize;
        }

        return result;
    }

}
