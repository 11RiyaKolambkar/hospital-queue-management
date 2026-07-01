# Hospital Queue Management System

## Overview

Hospital Queue Management System is a RESTful backend application built using Spring Boot. It helps hospitals manage patient queues efficiently by allowing staff to add, update, view, and remove patient records through REST APIs.

The application follows a layered architecture using Controller, Service, Repository, and Entity layers to ensure clean, maintainable, and scalable code.

## Features

- Add a new patient to the queue
- View all patients
- View patient details by ID
- Update patient information
- Delete a patient from the queue
- Input validation
- Global exception handling
- MySQL database integration
- RESTful API architecture

## Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Postman

## Project Structure

```
src
├── controller
├── service
├── repository
├── entity
├── exception
├── config
└── HospitalQueueManagementApplication.java
```

## API Endpoints

| HTTP Method | Endpoint | Description |
|-------------|----------|-------------|
| POST | /patients | Add a new patient |
| GET | /patients | Retrieve all patients |
| GET | /patients/{id} | Retrieve a patient by ID |
| PUT | /patients/{id} | Update patient details |
| DELETE | /patients/{id} | Remove a patient |

## Database Configuration

Configure the following properties in `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/hospital_queue
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## Running the Application

1. Clone the repository.

```bash
git clone https://github.com/your-username/hospital-queue-management.git
```

2. Navigate to the project directory.

```bash
cd hospital-queue-management
```

3. Configure the MySQL database in `application.properties`.

4. Run the application.

```bash
mvn spring-boot:run
```

5. Test the REST APIs using Postman.

## Concepts Implemented

- REST API Development
- CRUD Operations
- Spring Boot
- Spring Data JPA
- Hibernate ORM
- Layered Architecture
- Dependency Injection
- Exception Handling
- Validation

## Future Enhancements

- JWT Authentication
- Role-Based Authorization
- Appointment Scheduling
- Patient Search and Filtering
- Pagination and Sorting
- Swagger/OpenAPI Documentation




