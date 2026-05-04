package EECS_1021.Graph; // Adjust package name as needed

import edu.princeton.cs.introcs.StdDraw;

import java.util.ArrayList;

public class PlotGraph {

    /*
     Plots the moisture sensor data.
     X-axis: Time (Minutes)
     Y-axis: Voltage (0 - 5V)
    */
    public static void drawGraph(ArrayList<Long> moistureData) {
        //Check if we actually have data
        if (moistureData == null || moistureData.size() < 2) {
            System.out.println("Not enough data points to draw a graph.");
            return;
        }

        //Calculate the maximum time in minutes
        // (Size of array = total seconds, divide by 60 for minutes)
        int totalSeconds = moistureData.size();

        double maxMinutes = totalSeconds/ 60.0;

        // Setup the Canvas size
        StdDraw.setCanvasSize(800, 600);

        // Set the scales (with a little bit of padding so lines don't hug the absolute edge)
        StdDraw.setXscale(-0.1 * maxMinutes, maxMinutes * 1.1);
        StdDraw.setYscale(-0.5, 5.5);

        //Draw the X and Y axes
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.005);
        StdDraw.line(0, 0, maxMinutes, 0); // X-axis line
        StdDraw.line(0, 0, 0, 5);          // Y-axis line

        //Add Titles and Labels
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(maxMinutes / 2, -0.3, "Time (Minutes)"); // X-axis label
        StdDraw.text(-0.08 * maxMinutes, 2.5, "Voltage (V)"); // Y-axis label
        StdDraw.text(maxMinutes / 2, 5.2, "Soil Moisture Sensor Voltage vs. Time"); // Main Title

        for(int v = 1; v <= 5; v++) {
            StdDraw.text(-0.02 * maxMinutes, v, v + "V");
            StdDraw.line(-0.01 * maxMinutes, v, 0.01 * maxMinutes, v);
        }

        //Plot the Data!
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.setPenRadius(0.003);

        for (int i = 0; i < totalSeconds - 1; i++) {
            //Convert current point: Index to Minutes, 0-1023 to 0-5V
            double currentX = i / 60.0;
            double currentY = (moistureData.get(i) / 1023.0) * 5.0;

            //Convert next point: Index to Minutes, 0-1023 to 0-5V
            double nextX = (i + 1) / 60.0;
            double nextY = (moistureData.get(i + 1) / 1023.0) * 5.0;

            //Draw a line connecting the two points
            StdDraw.line(currentX, currentY, nextX, nextY);
        }

        System.out.println("Graph has been successfully drawn!");
    }
}