package EECS_1021.Main;

import EECS_1021.Clock.PotTask;
import EECS_1021.Clock.Timer_Task;
import EECS_1021.Graph.PlotGraph;
import EECS_1021.Hardware.EMERGENCY_Button;
import EECS_1021.Hardware.OLED;
import org.firmata4j.*;
import org.firmata4j.firmata.FirmataDevice;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Timer;


public class Main{


    private static Timer myTimer;

    public static void main(String[] args) throws IOException {
        IODevice myArduino = new FirmataDevice("COM3");
        long potValue;

        try {

            myArduino.start();
            myArduino.ensureInitializationIsDone();

            OLED myOLED = new OLED(myArduino);

            myTimer = new Timer();
            PotTask task = new PotTask(myArduino);

            myTimer.schedule(task, 0, 100);

            //Keeping the main program up while the timer program runs
            Thread.sleep(10000);

            myTimer.cancel();

            potValue = PotTask.getPotValue();
            System.out.println("Your threshold is: " + potValue );
            myOLED.clearOLED();

            Timer_Task executeTask = new Timer_Task(myArduino, potValue);
            myTimer = new Timer();
            myTimer.scheduleAtFixedRate(executeTask, 0, 1000);

            EMERGENCY_Button myButton = new EMERGENCY_Button(myArduino);

            //While loop to keep listening to the button
            while (!myButton.getButtonVal()) {
                //Small delay for quick response in case of Emergency
                Thread.sleep(500);
            }

            myTimer.cancel();
            myOLED.clearOLED();
            myArduino.stop();
            System.out.println("The program has been manually terminated");

            ArrayList<Long> finalMoistureData = executeTask.getMoistureData();
            PlotGraph.drawGraph(finalMoistureData);


        } catch (Exception IO) {
            System.out.println("Error");
        }
    }
}
