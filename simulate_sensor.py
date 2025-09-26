import requests
import time
import random
import datetime

CONTAINER_URL = "http://localhost:8080/containers"
SENSOR_URL = "http://localhost:8080/sensors/data"

containerID = str(random.randint(1, 10))

def create_container(containerID):
    container_data = {
        "containerID": containerID,
        "description": f"Demo container {containerID}",
        "location": "Warehouse"
    }
    try:
        response = requests.post(CONTAINER_URL, json=container_data)
        print(f"Container POST: {container_data} | Status: {response.status_code}")
    except Exception as e:
        print(f"Error creating container: {e}")

def generate_sensor_data():
    return {
        "containerID": containerID,
        "timestamp": int(datetime.datetime.now().timestamp() * 1000),
        "temperature": round(random.uniform(15.0, 30.0), 2),
        "humidity": round(random.uniform(30.0, 80.0), 2),
        "lat": round(random.uniform(-90.0, 90.0), 6),
        "lon": round(random.uniform(-180.0, 180.0), 6)
    }

if __name__ == "__main__":
    create_container(containerID)
    while True:
        data = generate_sensor_data()
        try:
            response = requests.post(SENSOR_URL, json=data)
            print(f"Sent: {data} | Status: {response.status_code}")
        except Exception as e:
            print(f"Error: {e}")
        time.sleep(5)
