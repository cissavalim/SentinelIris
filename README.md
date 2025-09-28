# SentinelIRIS

This project is a Spring Boot application that connects to an InterSystems IRIS database. 
It demonstrates the following scenario:
- Tracking containers, cargo, or vaccines (cold chain).
- Use sensors that collect data (temperature, humidity, location) and send it to a Java backend. 
- IRIS stores the data, maintains a history, allows for quick queries, and issues alerts if something goes outside acceptable limits. 
- Java can perform real-time or near-real-time processing, trend analysis, and anomaly detection.

---

## Prerequisites
- Docker (for running IRIS and the application)

---

## Running IRIS and the Java application

### Using Docker
1. Go to the root folder and run the following command in your terminal:
   ```cmd
    docker-compose up -d
   ```
2. You need to open IRIS interface:
    - Open your web browser and go to `http://localhost:52773/csp/sys/UtilHome.csp`.
    - Log in with:
      - Username: `_SYSTEM`
      - Password: `SYS1`
3. Then you'll need to create the tables (see below).

---

## Creating the tables

1. Go to init.sql file and copy its content.
2. In the IRIS web interface, go to "System Explorer" > "SQL".
3. Use the namespace `USER` and paste the SQL commands and execute each one to create the necessary tables.

---

## Simulating Sensor Data

1. You can simulate sensor data by running the simulate_sensor.py script.
2. Ensure you have Python installed.
3. Run the script:
    ```sh
    python simulate_sensor.py
    ```
---

## API Documentation

1. You can access the API Swagger through this URL after you run the application: `http://localhost:8080/swagger-ui.html`

---

## Troubleshooting
- Ensure Java and Gradle are installed and available in your PATH.
- For Docker Compose, ensure Docker Desktop is running.

---

For more details, refer to the source code and configuration files in the project.

