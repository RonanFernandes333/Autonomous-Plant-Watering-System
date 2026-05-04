package EECS_1021.Unit_Test_Files;

import EECS_1021.Hardware.Potentiometer;
import EECS_1021.Pins.Pins;
import org.firmata4j.I2CDevice;
import org.firmata4j.Pin;
import org.firmata4j.firmata.FirmataDevice;
import org.firmata4j.ssd1306.SSD1306;

import java.io.IOException;
import java.util.Timer;

public class PotTest {
    public static void main(String[] args) throws InterruptedException, IOException {
        var device = new FirmataDevice("COM3"); // Make sure this is COM3!
        device.start();
        device.ensureInitializationIsDone();

        //Set up the Potentiometer (Pin 14) to read Analog signals
        //Pin potPin = device.getPin(Pins.A0);
        //potPin.setMode(Pin.Mode.ANALOG);
        Potentiometer potPin = new Potentiometer(device);

        while(true) {
            Thread.sleep(1000); // Time to wake up
            //System.out.println(potPin.getValue());
            System.out.println(potPin.getPotVal());
        }

    }
}
