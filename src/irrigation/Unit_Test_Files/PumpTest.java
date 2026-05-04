package irrigation.Unit_Test_Files;

import irrigation.Pins.Pins;
import org.firmata4j.IODevice;
import org.firmata4j.Pin;
import org.firmata4j.firmata.FirmataDevice;

import java.io.IOException;

public class PumpTest {
    static void main(String[] args) throws IOException, InterruptedException {
        String myPort = "COM3";
        IODevice device = new FirmataDevice(myPort);

        System.out.println("Connecting to Arduino on " + myPort + "...");
        device.start();
        device.ensureInitializationIsDone();
        System.out.println("Connected!");

        Pin pumpPin = device.getPin(Pins.D3);
        pumpPin.setMode(Pin.Mode.OUTPUT);

        //Start pump
        pumpPin.setValue(1);

        //Wait 5 seconds
        Thread.sleep(5000);

        //Stop pump
        pumpPin.setValue(0);
        System.out.println("done");
        device.stop();

    }
}
