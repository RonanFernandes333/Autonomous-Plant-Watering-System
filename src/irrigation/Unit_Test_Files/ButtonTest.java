package irrigation.Unit_Test_Files;

import irrigation.Pins.Pins;
import org.firmata4j.IODevice;
import org.firmata4j.IODeviceEventListener;
import org.firmata4j.IOEvent;
import org.firmata4j.Pin;
import java.io.IOException;

import org.firmata4j.firmata.FirmataDevice;

class ButtonTest {
    static void main(String[] args) {
        IODevice myArduino = new FirmataDevice("COM3");

        try {
            myArduino.start();
            myArduino.ensureInitializationIsDone();
            Pin buttonPin = myArduino.getPin(Pins.D6);
            buttonPin.setMode(Pin.Mode.INPUT);
            System.out.println("Board initialised");

            myArduino.addEventListener(new IODeviceEventListener() {
                @Override
                public void onStart(IOEvent event) {
                    // Triggered when board starts
                }

                @Override
                public void onStop(IOEvent event) {
                    // Triggered when board stops
                }

                @Override
                public void onPinChange(IOEvent event) {
                    // This is the one you wanted!
                    // It triggers for ANY pin change on the board.
                    Pin pin = event.getPin();
                    if (pin.getIndex() == buttonPin.getIndex()) {
                        System.out.println("Button Pin " + buttonPin + " value: " + pin.getValue());
                    }

                }

                @Override
                public void onMessageReceive(IOEvent event, String message) {
                    // Triggered when a custom SysEx message is received
                }

            });
            while (true) {
                  // Do nothing, just stay alive
                Thread.sleep(1000);
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
