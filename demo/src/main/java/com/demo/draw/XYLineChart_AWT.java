package com.demo.draw;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class XYLineChart_AWT extends ApplicationFrame {

    public XYLineChart_AWT(String applicationTitle, String chartTitle, List<String> lists, double max_value, double min_value) {
        super(applicationTitle);
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        for (int i = 0; i < lists.size(); i++) {
            String string = lists.get(i);
            String[] strings = string.split(",");
            XYSeries series = new XYSeries(i);
            series.add(Double.parseDouble(strings[1]), Double.parseDouble(strings[2]));
            double rgb = 256 / (max_value - min_value) * (Double.parseDouble(strings[3]) - min_value);
            renderer.setSeriesPaint(i, new Color((int) rgb));
            dataset.addSeries(series);
        }

        JFreeChart xylineChart = ChartFactory.createXYLineChart(
            chartTitle,
            "Category",
            "Score",
            dataset,
            PlotOrientation.VERTICAL,
            true, true, false);
        xylineChart.setBackgroundPaint(Color.WHITE);
        ChartPanel chartPanel = new ChartPanel(xylineChart);

        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        final XYPlot plot = xylineChart.getXYPlot();

        plot.setRenderer(renderer);
        setContentPane(chartPanel);
    }

//    private XYDataset createDataset() {
//        final XYSeries firefox = new XYSeries("Firefox");
//        firefox.add(1.0, 1.0);
//        firefox.add(2.0, 4.0);
//        firefox.add(3.0, 3.0);
//        final XYSeries chrome = new XYSeries("Chrome");
//        chrome.add(1.0, 4.0);
//        chrome.add(2.0, 5.0);
//        chrome.add(3.0, 6.0);
//        final XYSeries iexplorer = new XYSeries("InternetExplorer");
//        iexplorer.add(3.0, 4.0);
//        iexplorer.add(4.0, 5.0);
//        iexplorer.add(5.0, 4.0);
//        final XYSeriesCollection dataset = new XYSeriesCollection();
//        dataset.addSeries(firefox);
//        dataset.addSeries(chrome);
//        dataset.addSeries(iexplorer);
//        return dataset;
//    }

    public static void main(String[] args) throws Exception {
        String filePath =
            Thread.currentThread().getContextClassLoader().getResource(".").getPath() + "/file";
        String fileName = filePath + "/test.txt";
        Reader reader = new FileReader(fileName);
        BufferedReader inputStream = new BufferedReader(reader);
        String line;
        List<String> list = new ArrayList<>();
        while ((line = inputStream.readLine()) != null) {
            list.add(line);
        }

        List<String> sezhi = list.stream().map(x -> x.split(",")[3]).collect(Collectors.toList());
        List<Double> doubles = sezhi.stream().map(Double::parseDouble).collect(Collectors.toList());
        double max_value = doubles.stream().max((o1, o2) -> (int) (o1 - o2) * 100000).get();
        double min_value = doubles.stream().min((o1, o2) -> (int) (o1 - o2) * 100000).get();
        XYLineChart_AWT chart = new XYLineChart_AWT(null,
            null, list, max_value, min_value);
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }
}

