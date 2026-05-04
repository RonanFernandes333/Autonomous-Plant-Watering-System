package irrigation.Hardware;

import irrigation.Pins.Pins;
import org.firmata4j.IODevice;
import org.firmata4j.Pin;

import java.io.IOException;

//Allows main code to activate or deactivates the pump
public class WaterPump {

    private final Pin pumpPin;
    private final IODevice myArduino;


    public WaterPump(IODevice myArduino) throws IOException {
        this.myArduino = myArduino;
        this.pumpPin = myArduino.getPin(Pins.D3);
        this.pumpPin.setMode(Pin.Mode.OUTPUT);
    }

    public void startPump() throws IOException {
        pumpPin.setValue(1);
    }

    public void stopPump() throws IOException {
        pumpPin.setValue(0);
    }

}
