# Courier Logistics System

## Overview

The Courier Logistics System is a comprehensive solution for managing courier logistics operations. It includes features such as shipment tracking, courier assignment, and status updates. The system uses Spring Boot for the backend, JWT for authentication, Docker for containerization, and Kubernetes for orchestration.

## Features

- **REST API** for creating, updating, and tracking shipments.
- **JWT Authentication** for secure API access.
- **Real-Time Tracking** using Redis for caching and asynchronous messaging (RabbitMQ/Kafka).
- **Swagger Documentation** for interactive API docs.
- **Docker and Kubernetes** support for containerization and deployment.
- **CI/CD Pipeline** for continuous integration and deployment using GitHub Actions.

## Prerequisites

- Java 17
- Maven
- Docker
- Kubernetes (kubectl)
- PostgreSQL database

## Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/your-repository/courier-logistics-system.git
cd courier-logistics-system
```

### 2. Build the Project

Ensure that you have Java 17 and Maven installed, then run:
```bash
mvn clean install
```

### 3. Run the Application Locally

```bash
mvn spring-boot:run
```

### 4. Dockerize the Application

Build the Docker image:
```bash
docker build -t cls:latest .
```

Run the Docker container:
```bash
docker run -p 8080:8080 courier-logistics-system:latest
```
### 5. Kubernetes Deployment
Ensure you have a Kubernetes cluster running and kubectl configured. Apply the deployment and service YAML files:
```bash
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml
```

### 6. Swagger Documentation
Access the Swagger UI at http://localhost:8080/swagger-ui.html to interact with the API endpoints. 

## Authentication
### Login
To obtain a JWT token, make a POST request to /api/auth/login with the following parameters:

- `username` (string): The username of the user.
- `password` (string): The password of the user.

#### Example Request:
```bash
curl -X POST "http://localhost:8080/api/auth/login" -d "username=user&password=pass"
```

#### Example Response:
```json
{
  "Authorization": "Bearer <jwt-token>"
}
```
Use this token in the `Authorization` header for subsequent API requests.

## API Endpoints

### Shipments

- `POST /api/shipments`: Create a new shipment.
- `PUT /api/shipments/{id}`: Update an existing shipment.
- `GET /api/shipments/{id}`: Track a shipment by ID.

### Couriers

- `POST /api/couriers`: Assign a shipment to a courier.
- `GET /api/couriers/{id}/shipments`: List shipments assigned to a courier.

### Authentication

- `POST /api/auth/login`: Authenticate and obtain a JWT token.


## Testing

### Unit Tests

Run unit tests using Maven:
```bash
mvn test
```

### Integration Tests

Run integration tests using Maven:
```bash
mvn verify
```

## CI/CD Pipeline

This project uses GitHub Actions for CI/CD. The pipeline includes:

- Building the project.
- Building the Docker image.
- Deploying to Kubernetes.

## Troubleshooting

- Ensure PostgreSQL is running and accessible.
- Verify Docker and Kubernetes configurations.
- Check logs for errors if the application fails to start.