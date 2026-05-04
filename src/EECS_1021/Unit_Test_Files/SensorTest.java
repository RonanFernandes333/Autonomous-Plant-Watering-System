package EECS_1021.Unit_Test_Files;

import org.firmata4j.IODevice;
import org.firmata4j.Pin;
import org.firmata4j.firmata.FirmataDevice;
import java.io.IOException;

public class SensorTest {

    public static void main(String[] args) {
        String myPort = "COM3";
        IODevice device = new FirmataDevice(myPort);

        try {
            //Start the connection
            System.out.println("Connecting to Arduino on " + myPort + "...");
            device.start();
            device.ensureInitializationIsDone();
            System.out.println("Connected!");

            //Setup Pin A1 (Pin 15 on Uno)
            Pin sensorPin = device.getPin(15);
            sensorPin.setMode(Pin.Mode.ANALOG);

            //Manual "Test" loop - reads the sensor 10 times
            System.out.println("Starting Sensor Readings...");
            int i = 0;
            while  (true) {

                i++;
                long value = sensorPin.getValue();
                System.out.println("Reading #" + i + ": " + value);

                //Delay between readings
                Thread.sleep(500);
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("Error: Could not connect to the Arduino. Check your port!");
        } finally {
            //Stop the device
            try {
                device.stop();
                System.out.println("Connection closed.");
            } catch (IOException e) {
                System.out.println("IO Error");
            }
        }
    }
}