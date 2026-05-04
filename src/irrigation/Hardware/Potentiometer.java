package irrigation.Hardware;

import irrigation.Pins.Pins;
import org.firmata4j.IODevice;
import org.firmata4j.Pin;

import java.io.IOException;

// Allows user to adjust threshold voltage for personalisation
public class Potentiometer {

    private IODevice myArduino;
    private Pin myPotPin;

    public Potentiometer(IODevice myArduino) throws IOException {
        this.myArduino = myArduino;
        this.myPotPin = myArduino.getPin(Pins.A0);
        myPotPin.setMode(Pin.Mode.ANALOG);
    }

    public long getPotVal(){
        long potVal = myPotPin.getValue();
        return potVal;
    }
}
