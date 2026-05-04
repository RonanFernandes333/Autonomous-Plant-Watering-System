package irrigation.Unit_Test_Files;

import org.firmata4j.firmata.FirmataDevice;
import org.firmata4j.IODevice;
import org.firmata4j.ssd1306.SSD1306;
import org.firmata4j.I2CDevice;
import org.firmata4j.ssd1306.MonochromeCanvas;
import java.io.IOException;

public class OLEDTest {
    static void main(String[] args) throws IOException, InterruptedException {
        String myPort = "COM3";
        IODevice theArduinoObject = new FirmataDevice(myPort);

        System.out.println("Connecting...");
        theArduinoObject.start();
        theArduinoObject.ensureInitializationIsDone();
        Thread.sleep(2000); //Let the upgraded Firmata wake up

        System.out.println("Initializing OLED");
        I2CDevice i2cObject = theArduinoObject.getI2CDevice((byte) 0x3C);
        SSD1306 theOledObject = new SSD1306(i2cObject, SSD1306.Size.SSD1306_128_64);

        theOledObject.init();
        Thread.sleep(500);

        //Clear any previous data on the OLED
        System.out.println("Clearing screen");
        MonochromeCanvas canvas = theOledObject.getCanvas();
        canvas.clear();
        theOledObject.display();
        Thread.sleep(500);

        System.out.println("Writing text");

        //Display in the centre of OLED
        canvas.setCursor(64, 32);
        canvas.write("The OLED is working!");
        theOledObject.display();

        //Display for 15 seconds
        System.out.println("Waiting for 15 seconds");
        Thread.sleep(15000);

        //Clear screen again
        System.out.println("Clearing screen");
        canvas.clear();
        theOledObject.display();

        theArduinoObject.stop();
    }
}
