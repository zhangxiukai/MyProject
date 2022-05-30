package com.demo.draw;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;


public class DrawTest {

    static BufferedImage image;

    static void createImage(String fileLocation) {
        try {
            FileOutputStream fos = new FileOutputStream(fileLocation);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
            encoder.encode(image);
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void graphicsGeneration(List<String[]> sezhi, double max_value,
        double min_value) {

        int imageWidth = 5000;//图片的宽度
        int imageHeight = 2117;//图片的高度

        // ChartGraphics chartGraphics = new ChartGraphics();
        image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, imageWidth, imageHeight);

        sezhi.forEach(point -> {
            double color =
                256 / (max_value - min_value) * (Double.parseDouble(point[3]) - min_value);
            graphics.setColor(new Color((int) color));
            graphics.drawOval((int) Double.parseDouble(point[1]) * 1000,
                (int) Double.parseDouble(point[2]), 1, 1);
        });
        createImage("/home/kyle/Pictures/test.jpg");
    }

    public static void main(String[] args) throws Exception {
        String filePath =
            Thread.currentThread().getContextClassLoader().getResource(".").getPath() + "/file";
        String fileName = filePath + "/test.txt";
        Reader reader = new FileReader(fileName);
        BufferedReader inputStream = new BufferedReader(reader);
        String line;
        List<String[]> list = new ArrayList<>();
        while ((line = inputStream.readLine()) != null) {
            list.add(line.split(","));
        }
        List<String> sezhi = list.stream().map(x -> x[3]).collect(Collectors.toList());
        List<Double> doubles = sezhi.stream().map(Double::parseDouble).collect(Collectors.toList());
        double max_value = doubles.stream().max((o1, o2) -> (int) (o1 - o2) * 100000).get();
        double min_value = doubles.stream().min((o1, o2) -> (int) (o1 - o2) * 100000).get();
        System.out.println(max_value);
        System.out.println(min_value);
        graphicsGeneration(list, max_value, min_value);
    }

    public void showAttr(LinkedList ac_cnlList, ArrayList all) {

        DefaultXYDataset xydataset = new DefaultXYDataset();

        //创建主题样式
        StandardChartTheme mChartTheme = new StandardChartTheme("CN");
        //设置标题字体
        mChartTheme.setExtraLargeFont(new Font("黑体", Font.BOLD, 20));
        //设置轴向字体
        mChartTheme.setLargeFont(new Font("宋体", Font.CENTER_BASELINE, 15));
        //设置图例字体
        mChartTheme.setRegularFont(new Font("宋体", Font.CENTER_BASELINE, 15));
        //应用主题样式
        ChartFactory.setChartTheme(mChartTheme);

        //根绝实际需求加载数据集到xydatasets中
        for (int l = 0; l < all.size(); l++) {

            int size = ((Set) all.get(l)).size();
            double[][] datas = new double[2][size];
            int m = 0;
            for (Iterator it = ((Set) all.get(l)).iterator(); it.hasNext(); m++) {
                HashMap line = ((HashMap) ac_cnlList.get((Integer) it.next()));
                double AC = (Double) line.get("AC");
                double CNL = (Double) line.get("CNL");
                datas[0][m] = AC;    //x轴
                datas[1][m] = CNL;   //y轴

            }
            xydataset.addSeries(l, datas);  //l为类别标签

        }

        JFreeChart chart = ChartFactory
            .createScatterPlot("k2 =10,k3=20,kernel=2", "GR", "CNL", xydataset,
                PlotOrientation.VERTICAL, true, false, false);
        ChartFrame frame = new ChartFrame("散点图", chart, true);
        chart.setBackgroundPaint(Color.white);
        chart.setBorderPaint(Color.GREEN);
        chart.setBorderStroke(new BasicStroke(1.5f));
        XYPlot xyplot = (XYPlot) chart.getPlot();

        xyplot.setBackgroundPaint(new Color(255, 253, 246));
        ValueAxis vaaxis = xyplot.getDomainAxis();
        vaaxis.setAxisLineStroke(new BasicStroke(1.5f));

        ValueAxis va = xyplot.getDomainAxis(0);
        va.setAxisLineStroke(new BasicStroke(1.5f));

        va.setAxisLineStroke(new BasicStroke(1.5f));        // 坐标轴粗细
        va.setAxisLinePaint(new Color(215, 215, 215));    // 坐标轴颜色
        xyplot.setOutlineStroke(new BasicStroke(1.5f));   // 边框粗细
        va.setLabelPaint(new Color(10, 10, 10));          // 坐标轴标题颜色
        va.setTickLabelPaint(new Color(102, 102, 102));   // 坐标轴标尺值颜色
        ValueAxis axis = xyplot.getRangeAxis();
        axis.setAxisLineStroke(new BasicStroke(1.5f));

        XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer) xyplot
            .getRenderer();
        xylineandshaperenderer.setSeriesOutlinePaint(0, Color.WHITE);
        xylineandshaperenderer.setUseOutlinePaint(true);
        NumberAxis numberaxis = (NumberAxis) xyplot.getDomainAxis();
        numberaxis.setAutoRangeIncludesZero(false);
        numberaxis.setTickMarkInsideLength(2.0F);
        numberaxis.setTickMarkOutsideLength(0.0F);
        numberaxis.setAxisLineStroke(new BasicStroke(1.5f));

        frame.pack();
        frame.setVisible(true);
    }

}
