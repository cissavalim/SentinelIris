# Demo Project

This project is a Spring Boot application that connects to an InterSystems IRIS database. 
It demonstrates the following scenario:
- Tracking containers, cargo, or vaccines (cold chain).
- Use sensors that collect data (temperature, humidity, location) and send it to a Java backend. 
- IRIS stores the data, maintains a history, allows for quick queries, and issues alerts if something goes outside acceptable limits. 
- Java can perform real-time or near-real-time processing, trend analysis, and anomaly detection.
Web interface for viewing maps/situations and reports.

---

## Prerequisites
- Java 17 or newer
- Docker (for running IRIS)

---

## Running IRIS

### Using Docker
1. Run the following command in your terminal:
   ```cmd
    docker run --name my-iris -d --publish 9091:1972 --publish 9092:52773 intersystems/iris-community:latest-cd
   ```
2. This will start IRIS.
3. You need to open its interface:
    - Open your web browser and go to `http://localhost:9091/csp/sys/UtilHome.csp`.
    - Log in with:
      - Username: `_SYSTEM`
      - Password: `SYS`
    - You'll be requested to change your password.
    - Then update this password in Repository.java file.

---

## Creating the tables

1. Go to init.sql file and copy its content.
2. In the IRIS web interface, go to "System Explorer" > "SQL".
3. Paste the SQL commands and execute them to create the necessary tables.

## Running the Project

### Using Gradle Wrapper
1. Open a terminal in the project root directory.
2. Run the following command:
   - On Windows:
     ```cmd
     gradlew.bat bootRun
     ```
   - On macOS/Linux:
     ```sh
     ./gradlew bootRun
     ```
3. The application will start on the default port (usually 8080).

---

## Troubleshooting
- Ensure Java and Gradle are installed and available in your PATH.
- For Docker Compose, ensure Docker Desktop is running.

---

For more details, refer to the source code and configuration files in the project.

