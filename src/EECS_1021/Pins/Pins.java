package EECS_1021.Pins;

import org.firmata4j.I2CDevice;
import org.firmata4j.Pin;
import org.firmata4j.ssd1306.SSD1306;

public class Pins {
    public static final int A0 = 14; //Potentiometer
    public static final int A1 = 15; //Moisture sensor
    public static final int A2 = 16; //Sound
    public static final int D6 =  6; //Button
    public static final int D3 = 3; //Water Pump
    public static final int D4 = 4; //red LED
    public static final int D13 = 13; //default LED

    public static final byte I2C0 = 0x3C; // OLED Display
    private Pins(){
    }
}
