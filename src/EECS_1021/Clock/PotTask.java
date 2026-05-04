package EECS_1021.Clock;

import EECS_1021.Hardware.OLED;
import EECS_1021.Hardware.Potentiometer;
import org.firmata4j.IODevice;
import org.firmata4j.Pin;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class PotTask extends TimerTask {
    private static long potValue;
    private static long roundedVal;
    private final OLED myOLED;
    private final Potentiometer pot;
    private final IODevice myArduino;

    public PotTask(IODevice myArduino) throws IOException {
        this.myArduino = myArduino;
        this.pot = new Potentiometer(myArduino);
        this.myOLED = new OLED(myArduino);
    }

    @Override
    public void run() {
        //Get analog value from potentiometer
        potValue = pot.getPotVal();

        roundedVal = Math.round(potValue / 10.0) * 10;

        String potString = String.valueOf(roundedVal);

        //Print to the Java console
        System.out.println("Potentiometer: " + roundedVal);


        myOLED.clearCanvas();

        //Write the Potentiometer value to the screen
        myOLED.setTopLeftDisplay("Value: " + roundedVal);


        //ERASE the previous line by drawing a full-width line in "DARK" (Turns pixels OFF)
        //Draw it all the way across the screen (0 to 127)
        myOLED.drawLine(roundedVal);
        myOLED.displayOLED();



    }

    public static long getPotValue() {
        return roundedVal;
    }
}
