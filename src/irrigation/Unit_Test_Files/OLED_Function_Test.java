package irrigation.Unit_Test_Files;


import irrigation.Hardware.OLED;
import org.firmata4j.IODevice;
import org.firmata4j.firmata.FirmataDevice;

import java.io.IOException;

public class OLED_Function_Test {

    static void main(String[] args) throws IOException, InterruptedException {
        IODevice myArduino = new FirmataDevice("COM3");

        myArduino.start();
        myArduino.ensureInitializationIsDone();

        OLED myOLED = new OLED(myArduino);

        myOLED.clearOLED();
        myOLED.setMyOLED("Top left: Text","Mid Lef: Text","Bottom Left: Text");
        myOLED.displayOLED();

        Thread.sleep(5000);
        myOLED.clearOLED();
        myArduino.stop();

    }
}
