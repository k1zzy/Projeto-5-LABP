package project;

import org.jfree.data.xy.XYDataset;

public class XYPlotterExampleRun {

    public static void main(String[] args) {
        // Generate sample data for two series
        double[] xData1 = {1, 2, 3, 4, 5};
        double[] yData1 = {10, 20, 15, 25, 30};

        double[] xData2 = {1, 2, 3, 4, 5};
        double[] yData2 = {5, 15, 10, 20, 25};

        // Create XYDatasets for each series
        XYDataset dataset1 = XYPlotter.createDataset("Series 1", xData1, yData1);
        XYDataset dataset2 = XYPlotter.createDataset("Series 2", xData2, yData2);

        // Display the XY plot with the datasets
        XYPlotter.showXYPlot("XY Plot Example", "X Axis", "Y Axis", new XYDataset[]{dataset1, dataset2});
        
        // Generate sample data for two more series
        double[] yData3 = {10, 20, 15, 25, 30};
        
        double[] yData4 = {5, 15, 10, 20, 25};
        
        // Create XYDatasets for each series
        XYDataset dataset3 = XYPlotter.createDataset("Series 3", yData3);
        XYDataset dataset4 = XYPlotter.createDataset("Series 4", yData4);

        // Display the XY plot with the datasets
        XYPlotter.showXYPlot("XY Plot Example", "X Axis", "Y Axis", new XYDataset[]{dataset3, dataset4});
        
        // Generate sample data for three more series
        double[] yData5 = {10, 20, 15, 25, 30, 28, 13, 35, 27, 10, 20, 15, 25,
                30, 28, 13, 35, 27, 10, 20, 15, 25, 30, 28};
        
        double[] yData6 = {5, 15, 10, 20, 25, 38, 23, 25, 17, 5, 15, 10, 20,
                25, 38, 23, 25, 17, 5, 15, 10, 20, 25, 38};
        
        double[] yData7 = {55, 65, 70, 88, 95, 103, 70, 45, 27, 15, 25, 50, 29,
                67, 78, 85, 95, 107, 105, 105, 101, 94, 56, 48};
        
        // Create XYDatasets for each series
        XYDataset dataset5 = XYPlotter.createDataset("Series 5", yData5);
        XYDataset dataset6 = XYPlotter.createDataset("Series 6", yData6);
        XYDataset dataset7 = XYPlotter.createDataset("Series 7", yData7);

        // Display the XY plot with the datasets
        XYPlotter.showXYPlot("XY Plot Example", "X Axis", "Y Axis", new XYDataset[]{dataset5, dataset6, dataset7});
    }
}
