package irrigation.Clock;
import irrigation.Hardware.MoistureSensor;
import irrigation.Hardware.OLED;
import irrigation.Hardware.WaterPump;
import org.firmata4j.IODevice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TimerTask;

public class Timer_Task extends TimerTask {
    private final MoistureSensor sensor;
    private final WaterPump pump;
    private final OLED myOLED;

    private boolean isPumpRunning = false;
    private boolean isCooldown = false;
    private long previousTime = 0; //Keeps track of time

    private final long pumptTime = 5000;   // 5 seconds
    private final long cooldownTime = 5000;  // 1 minute
    private final long thresholdVal;

    private final ArrayList<Long> moistureData;

    public Timer_Task(IODevice myArduino, long thresholdVal) throws IOException {
        this.thresholdVal = thresholdVal;
        this.sensor = new MoistureSensor(myArduino);
        this.pump = new WaterPump(myArduino);
        this.myOLED = new OLED(myArduino);
        this.moistureData = new ArrayList<>();

    }



    @Override
    public void run() {
        //Get moisture sensor data
        long sensorVal = sensor.getMoistureVal();

        //Get current time in milliseconds
        long currentTime = System.currentTimeMillis();


        //Check if the pump is on
        if (isPumpRunning) {
            //Check if it's time to turn the pump off
            if (currentTime - previousTime >= pumptTime) {
                try {
                    pump.stopPump();

                    isPumpRunning = false;
                    isCooldown = true;     //Set mode to cool down
                    previousTime = currentTime;  //Reset the timer for the cool-down


                    System.out.println("Pump stopped. 1 minute cooldown started.");


                } catch (IOException e) {
                    System.out.println("Failed to stop pump");
                }
            }
        }
        else if (isCooldown) {
            //Check if the cool down is over?
            if (currentTime - previousTime >= cooldownTime) {
                isCooldown = false; // Cool-down finished, back to normal!
                System.out.println("Cooldown finished. Ready to water if needed.");


            }
        }
        else {
            //IDLE STATE: Pump is off, while NOT being in a cool down state.
            //Check the moisture threshold
            if (sensorVal > thresholdVal) {
                try {
                    myOLED.clearCanvas();

                    pump.startPump();
                    isPumpRunning = true;
                    previousTime = currentTime;
                    System.out.println("Moisture low! Pump started.");


                } catch (IOException e) {
                    System.out.println("Failed to start pump");
                }
            }

        }
        if(isPumpRunning){
            myOLED.setMyOLED("Moisture: " + sensorVal,"Pump: ON","Watering...");
        }
        else if(isCooldown){
            myOLED.setMyOLED("Moisture: " + sensorVal,"Pump: OFF","Cool Down");
        }
        else{
            myOLED.setMyOLED("Moisture: " + sensorVal,"Pump: OFF","Ready to Water");
        }
        //myOLED.setMyOLED("Moisture: " + sensorVal,"","");
        myOLED.displayOLED();

    }
    public ArrayList<Long> getMoistureData() {
        return this.moistureData;
    }
}