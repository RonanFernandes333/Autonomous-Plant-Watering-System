package irrigation.Hardware;

import irrigation.Pins.Pins;
import org.firmata4j.IODevice;
import org.firmata4j.IODeviceEventListener;
import org.firmata4j.IOEvent;
import org.firmata4j.Pin;

import java.io.IOException;

//Immediately terminates program when the button is pressed
public class EMERGENCY_Button {
    private final IODevice myArduino;
    private final Pin buttonPin;
    static boolean buttonPressed;

    public EMERGENCY_Button(IODevice myArduino) throws IOException {
        this.myArduino = myArduino;
        this.buttonPin = myArduino.getPin(Pins.D6);
        buttonPin.setMode(Pin.Mode.INPUT);
    }
    public boolean getButtonVal() throws IOException {
        try {
            myArduino.addEventListener(new IODeviceEventListener() {

                @Override
                public void onPinChange(IOEvent event) {
                    // This is the one you wanted!
                    // It triggers for ANY pin change on the board.
                    Pin pin = event.getPin();
                    if (pin.getIndex() == buttonPin.getIndex()) {

                        //If the button is pressed then the program will return true
                        if (pin.getValue() == 1){
                            buttonPressed = true;
                        }
                    }
                }

                @Override
                public void onStart(IOEvent event) {
                    //Empty
                }
                @Override
                public void onStop(IOEvent event) {
                    //Empty
                }
                @Override
                public void onMessageReceive(IOEvent event, String message) {
                    //Empty
                }

            });

        } catch (Exception e) {
            System.out.println("Button failed");
        }
        return buttonPressed;

    }
}
