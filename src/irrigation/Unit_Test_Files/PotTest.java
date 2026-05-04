package irrigation.Unit_Test_Files;

import irrigation.Hardware.Potentiometer;
import org.firmata4j.firmata.FirmataDevice;

import java.io.IOException;

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
