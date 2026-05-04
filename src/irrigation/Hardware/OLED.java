package irrigation.Hardware;

import irrigation.Pins.Pins;
import org.firmata4j.I2CDevice;
import org.firmata4j.IODevice;
import org.firmata4j.ssd1306.SSD1306;
import org.firmata4j.ssd1306.MonochromeCanvas;

import java.io.IOException;

//Outputs realtime data of soil moisture percentage and pump status
public class OLED {

    private final I2CDevice i2cObject;
    private final SSD1306 myOLED;
    private MonochromeCanvas canvas;
    public OLED(IODevice myArduino) throws IOException {

        this.i2cObject = myArduino.getI2CDevice(Pins.I2C0);
        this.myOLED = new SSD1306(i2cObject,SSD1306.Size.SSD1306_128_64);
        this.canvas = new MonochromeCanvas(128, 64);
        myOLED.init();
        canvas = myOLED.getCanvas();

    }
    public void setTopLeftDisplay(String text){
        canvas.drawString(0,0, text);

    }

    public void setMidLeftDisplay(String text){
        canvas.drawString(0,24, text);

    }

    public void setBottomLeftDisplay(String text){
        canvas.drawString(0,48, text);
    }

    public void setMyOLED(String txt1, String txt2, String txt3){
        canvas.drawString(0,0, txt1);
        canvas.drawString(0,24, txt2);
        canvas.drawString(0,48, txt3);
    }

    public void drawLine(long potValue){
        int lineWidth = (int) ((potValue / 1023.0) * 127);
        canvas.drawLine(0, 20, lineWidth, 20, MonochromeCanvas.Color.BRIGHT);
        canvas.drawLine(0, 21, lineWidth, 21, MonochromeCanvas.Color.BRIGHT);
        canvas.drawLine(0, 22, lineWidth, 22, MonochromeCanvas.Color.BRIGHT);
    }


    public void displayOLED(){
        myOLED.display();
    }

    public void clearOLED(){
        myOLED.clear();
        myOLED.display();
    }

    public void clearCanvas(){
        canvas.clear();
    }

}
