# Automated Plant Watering System 
<img width="925" height="697" alt="image" src="https://github.com/user-attachments/assets/0f23b364-6726-425e-81f8-9cb3b578fc76" />

## About the Project
This project is an autonomous electromechanical system designed to continuously monitor soil moisture levels and water plants automatically without human intervention. 

By combining physical sensors, software logic, and actuators, this system ensures plants are watered accurately based on real-time data. It eliminates human error (such as over-watering or under-watering) and provides a practical, hands-free solution for plant care. Built with system-integration and computational thinking in mind, this project serves as a foundational application for building larger autonomous systems.

## Key Features
* **Autonomous Watering:** Detects when soil moisture crosses a user-defined threshold and activates a water pump.
* **Custom Calibration:** Features a 10-second initialization window where the user can set a custom moisture threshold (0-1023, in increments of 10) using a potentiometer.
* **Real-Time OLED Dashboard:** Displays live moisture readings, the current threshold, and pump status (`ON`, `OFF`, `COOLDOWN`, or `READY`).
* **Smart Pumping Logic:** The pump activates for 5 seconds, followed by a 1-minute cooldown period. This allows the water to seep through the soil and reach the sensor, preventing over-watering.
* **Data Logging & Visualization:** Continuously logs sensor voltage readings into an array list every second, allowing for the generation of a time-series plot of moisture trends.
* **Long-Term Endurance:** Designed to run continuously for over a week, managing multiple wet/dry soil cycles and capable of delivering over 2 liters of water.
* **Emergency Override:** The program can be manually terminated at any time via a physical push button.

## Hardware Components
* **Arduino Microcontroller (Grove Kit Beginner Board):** The central hub that communicates with and controls all physical components.
* **Moisture Sensor:** Reads soil moisture levels on a scale from 0 to 1023.
* **Water Pump & 9V Battery:** Pumps water from the reservoir to the plant, powered by an external 9V battery.
* **MOSFET:** Acts as an electronic switch to safely supply the necessary power from the battery to the water pump.
* **OLED Screen:** Provides a real-time visual interface for system data.
* **Potentiometer:** A rotary dial used to set the moisture threshold during startup.
* **Push Button:** Acts as an emergency kill-switch to terminate the program safely.

## Software & Technologies
* **Language:** Java
* **Protocol:** Firmata4j (enables communication between the Java program and the Arduino microcontroller)
* **Architecture:** Modular Object-Oriented Programming (OOP)

## System Workflow & Procedure
1. **Planning & Architecture:** The system logic was first mapped out using flowcharts. Basic functions for each hardware component were written independently before being integrated into the main Java program, ensuring clean, modular, and maintainable code.
2. **Initialization:** Upon starting, the program opens a 10-second calibration window. The user adjusts the potentiometer to set the desired moisture threshold, guided by the OLED screen.
3. **Monitoring Loop:** The moisture sensor continuously reads the soil state. Data is logged every second.
4. **Action:** If the moisture reading exceeds the threshold value, the pump activates for 5 seconds.
5. **Cooldown:** The system enters a 1-minute idle state to let the soil absorb the water, avoiding a continuous pumping loop. 
6. **Reset:** The cycle repeats continuously until the emergency button is pressed.
