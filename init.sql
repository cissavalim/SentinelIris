CREATE TABLE Container (
    ContainerID VARCHAR(50) PRIMARY KEY,
    Description VARCHAR(255),
    Location VARCHAR(100)
)

CREATE TABLE SensorReading (
    ReadingID SERIAL PRIMARY KEY,
    ContainerID VARCHAR(50),
    Timestamp TIMESTAMP,
    Temperature DECIMAL(5,2),
    Humidity DECIMAL(5,2),
    Lat DECIMAL(9,6),
    Lon DECIMAL(9,6),
    AnomalyFlag INT,
    FOREIGN KEY (ContainerID) REFERENCES Container(ContainerID)
)
