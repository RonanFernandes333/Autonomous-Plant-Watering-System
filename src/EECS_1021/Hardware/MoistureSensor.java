package EECS_1021.Hardware;

import EECS_1021.Pins.Pins;
import org.firmata4j.IODevice;
import org.firmata4j.Pin;
import java.io.IOException;

/* Sensor Callibration
   Air: 825
   Dry Soil: 750
   Ideal wet Soil: 600
   Saturated soil: 520
   Water: 510
*/
public class MoistureSensor {

    private final Pin sensorPin;

    public MoistureSensor(IODevice myArduino) {

        this.sensorPin = myArduino.getPin(Pins.A1);
        try {
            this.sensorPin.setMode(Pin.Mode.ANALOG);
        } catch (IOException e) {
            System.out.println("The Moisture Sensor is not connected to pin number"+" "+ this.sensorPin);
            throw new RuntimeException(e);
        }
    }

    //Returns the moisture value and outputs it to the terminal
    public long getMoistureVal(){

        //Get sensor value
        long sensorVal = sensorPin.getValue();

        //Output the sensor Value
        System.out.println("Moisture Reading:" + " " + sensorVal);

        return sensorVal;
    }
}

