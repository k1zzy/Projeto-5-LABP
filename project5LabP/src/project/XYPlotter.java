package project;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class XYPlotter {

    /**
     * Displays an XY plot with multiple series in a Swing window.
     *
     * @param title       The title of the plot window.
     * @param xAxisLabel  The label for the x-axis.
     * @param yAxisLabel  The label for the y-axis.
     * @param datasets    An array of XYDataset objects representing the series to plot.
     */
    public static void showXYPlot(String title, String xAxisLabel, String yAxisLabel, XYDataset[] datasets) {
        EventQueue.invokeLater(() -> {
            // Create a new JFreeChart XY plot
            JFreeChart chart = ChartFactory.createXYLineChart(
                    title, xAxisLabel, yAxisLabel, null, PlotOrientation.VERTICAL, true, true, false);

            // Get the plot's XYPlot (main chart area)
            org.jfree.chart.plot.XYPlot plot = chart.getXYPlot();

            // Clear any existing series in the plot
            plot.clearRangeMarkers();

            // Add each dataset as a series to the plot
            for (int i = 0; i < datasets.length; i++) {
                plot.setDataset(i, datasets[i]);
                plot.setRenderer(i, new org.jfree.chart.renderer.xy.XYLineAndShapeRenderer(true, false));
            }

            // Create a Swing JFrame to display the chart
            JFrame frame = new JFrame(title);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Create a ChartPanel with the chart and add it to the JFrame
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(800, 600));
            frame.getContentPane().add(chartPanel, BorderLayout.CENTER);

            // Pack the JFrame and set visible
            frame.pack();
            frame.setLocationRelativeTo(null); // Center the window on the screen
            frame.setVisible(true);
        });
    }

    /**
     * Creates an XYDataset from given data points.
     *
     * @param seriesName  The name of the series.
     * @param xData       The array of x values.
     * @param yData       The array of y values.
     * @return An XYDataset containing the series data.
     */
    public static XYDataset createDataset(String seriesName, double[] xData, double[] yData) {
        XYSeries series = new XYSeries(seriesName);
        for (int i = 0; i < xData.length; i++) {
            series.add(xData[i], yData[i]);
        }
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        return dataset;
    }
    
    /**
     * Creates an XYDataset from given data points.
     *
     * The xData is automatically generated as integer values starting from 1.
     * @param seriesName  The name of the series.
     * @param yData       The array of y values.
     * @return An XYDataset containing the series data.
     */
    public static XYDataset createDataset(String seriesName, double[] yData) {
        XYSeries series = new XYSeries(seriesName);
        for (int i = 0; i < yData.length; i++) {
            series.add(i +1, yData[i]);
        }
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        return dataset;
    }

}
