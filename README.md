# Hospital Queue Management System

A production-ready, full-stack Java healthcare application designed for hospital Outpatient Departments (OPD). The platform streamlines patient intake, automated queue token allocation, medical staff scheduling, and live waiting-room status displays.

---

## Technical Stack

- **Backend:** Java 17+, Spring Boot 3.5.x
- **Persistence & ORM:** Spring Data JPA, Hibernate, HikariCP Connection Pooling
- **Database:** MySQL 8.0 (Production) and H2 Database (Embedded Profile for Zero-Dependency Development/Testing)
- **API Documentation:** OpenAPI 3.0 / Swagger UI (`springdoc-openapi-starter-webmvc-ui` 2.8.x)
- **Testing:** JUnit 5, Spring Boot Test, MockMvc
- **Frontend:** HTML5, CSS3, Vanilla JavaScript (Decoupled, zero external framework dependencies)
- **Containerization & DevOps:** Docker, Docker Compose (Multi-stage build)

---

## System Architecture

```
+---------------------------------------------------------------------------------+
|                                 CLIENT LAYER                                    |
|   +-------------------+  +-------------------+  +----------------------------+  |
|   | Staff / Admin     |  | Doctor OPD Desk   |  | Public Waiting Display     |  |
|   | (index.html)      |  | (doctor-dash.html)|  | (live-queue.html)          |  |
|   +---------+---------+  +---------+---------+  +-------------+--------------+  |
+-------------|----------------------|--------------------------|-----------------+
              |                      |                          |
              +----------------------+--------------------------+
                                     | JSON REST APIs (CORS Enabled)
                                     v
+---------------------------------------------------------------------------------+
|                         SPRING BOOT 3 APPLICATION LAYER                         |
|                                                                                 |
|   [ Controllers ]                                                               |
|     - AppointmentController (/appointments)                                     |
|     - DoctorController (/doctors)                                               |
|     - PatientController (/patients)                                             |
|     - DashboardController (/dashboard)                                          |
|                                                                                 |
|   [ Middleware & Cross-Cutting ]                                                |
|     - GlobalExceptionHandler (@RestControllerAdvice)                            |
|     - CorsConfig (Global WebMvcConfigurer)                                      |
|     - OpenApiConfig (OpenAPI 3 / Swagger Documentation)                         |
|     - DataInitializer (Automatic Schema & Seed Data Initialization)             |
|                                                                                 |
|   [ Service Layer ]                                                             |
|     - AppointmentService (Token logic, Queue status transitions, Stats engine)  |
|     - DoctorService (Specialization & Availability management)                  |
|     - PatientService (Intake & Next-token resolution)                           |
|                                                                                 |
|   [ Repositories (Spring Data JPA) ]                                            |
|     - AppointmentRepository, DoctorRepository, PatientRepository               |
+------------------------------------|--------------------------------------------+
                                     |
                                     v
+---------------------------------------------------------------------------------+
|                                 DATA LAYER                                      |
|   - MySQL 8.0 (Default / Containerized)                                         |
|   - H2 In-Memory Database (Optional dev profile via -Dspring.profiles.active=h2)|
+---------------------------------------------------------------------------------+
```

---

## Core Features

1. **Role-Based Authentication & Access Control**
   - **Staff & Receptionist:** Register patients, schedule appointments, search patient records, register doctors, and manage queues.
   - **Doctor Consultation Desk:** Filter assigned queue, call waiting tokens inside, and record completed consultations.
   - **Patient Status Portal:** Search by patient name to look up assigned token number and live queue progress.
   - **Public OPD Display Screen:** High-contrast Waiting Room TV monitor with real-time token calling and auto-sync.

2. **Automated Queue & Token Engine**
   - Generates incremental, sequential token numbers per doctor and department.
   - Handles queue lifecycle transitions: `Waiting` -> `In Progress` -> `Consulted`.
   - Prevents queue collisions and race conditions using relational indexing.

3. **Real-Time Analytics & Aggregates**
   - Single-endpoint dashboard metrics (`/dashboard/stats`) delivering patient counts, active doctors, waiting tokens, and completed consultations.

4. **Self-Documenting OpenAPI 3 / Swagger UI**
   - Interactive API testbed at `/swagger-ui.html` for inspecting schemas, parameters, and testing requests.

---

## REST API Reference

| Method | Endpoint | Description | Status Code |
| :--- | :--- | :--- | :--- |
| **POST** | `/appointments/book` | Book a new appointment & generate queue token | `201 Created` |
| **GET** | `/appointments/all` | Fetch all appointments | `200 OK` |
| **GET** | `/appointments/{id}` | Get appointment details by ID | `200 OK` |
| **GET** | `/appointments/doctor/{name}` | Get appointments assigned to a specific doctor | `200 OK` |
| **GET** | `/appointments/search?patientName={name}` | Search appointments by patient name | `200 OK` |
| **PUT** | `/appointments/{id}/status?status={status}` | Update status (`Waiting`, `In Progress`, `Consulted`) | `200 OK` |
| **DELETE**| `/appointments/delete/{id}` | Cancel / remove an appointment | `200 OK` |
| **POST** | `/doctors/add` | Register a new doctor | `201 Created` |
| **GET** | `/doctors/all` | Retrieve directory of all doctors | `200 OK` |
| **PUT** | `/doctors/{id}/toggle-availability` | Toggle doctor availability (`true`/`false`) | `200 OK` |
| **POST** | `/patients/add` | Register a new patient for today's OPD intake | `201 Created` |
| **GET** | `/patients/all` | Retrieve directory of all patients | `200 OK` |
| **GET** | `/patients/search?name={name}` | Search patients by name | `200 OK` |
| **POST** | `/patients/queue/next` | Call next waiting patient in queue | `200 OK` |
| **GET** | `/dashboard/stats` | Real-time aggregate statistics | `200 OK` |

---

## Prerequisites

- **Java Development Kit (JDK):** Version 17 or higher
- **Maven:** Version 3.8+ (or bundled wrapper)
- **Database:** MySQL 8.0 (or run with H2 profile with zero installation)
- **Docker & Docker Compose:** (Optional for containerized deployment)

---

## Local Setup & Execution

### Method 1: Running with MySQL (Default)

1. Ensure MySQL is running on port 3306.
2. The database `hospital_queue` is automatically created on first boot if it does not exist.
3. Configure your credentials via environment variables or use the standard defaults:
   - `DB_URL`: `jdbc:mysql://localhost:3306/hospital_queue`
   - `DB_USERNAME`: `root`
   - `DB_PASSWORD`: `root123`
4. Build and start the application:
   ```bash
   mvn clean package -DskipTests
   mvn spring-boot:run
   ```
5. Access the application:
   - Portal Login: `http://localhost:8080/login.html`
   - Administration Portal: `http://localhost:8080/index.html`
   - Doctor Consultation Desk: `http://localhost:8080/doctor-dashboard.html`
   - Patient Status: `http://localhost:8080/patient-status.html`
   - Public Waiting Display: `http://localhost:8080/live-queue.html`
   - Swagger UI: `http://localhost:8080/swagger-ui.html`

### Method 2: Running with Embedded H2 (Zero Installation Required)

To run the application with zero external prerequisites (e.g. for evaluation or code review):
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=h2
```
- H2 Web Console: `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:hospital_queue`, Username: `sa`, Password: empty)

---

## Containerized Deployment (Docker Compose)

The repository includes a production multi-stage `Dockerfile` and a `docker-compose.yml` orchestrating both the application and a healthy MySQL container:

```bash
# Build and run containers in detached mode
docker-compose up -d --build

# Inspect running containers
docker-compose ps

# View container logs
docker-compose logs -f app

# Stop containers and preserve database volume
docker-compose down
```

---

## Cloud Deployment Guide

### Deploying to Render / Railway / AWS

1. **Repository Setup:** Push the repository to GitHub.
2. **Environment Variables:** Set the following variables in your hosting provider's dashboard:
   - `PORT`: `8080` (or host-provided port)
   - `DB_URL`: `jdbc:mysql://<cloud-db-host>:<port>/<dbname>?useSSL=true`
   - `DB_USERNAME`: `<cloud-db-username>`
   - `DB_PASSWORD`: `<cloud-db-password>`
3. **Build Command:**
   ```bash
   mvn clean package -DskipTests
   ```
4. **Start Command:**
   ```bash
   java -jar target/queue-management-0.0.1-SNAPSHOT.jar
   ```

---

## Testing

Run unit and integration test suites:
```bash
mvn test
```
The test suite utilizes an isolated, in-memory H2 database (`src/test/resources/application.properties`) to guarantee tests run reliably in any CI/CD environment without mutating persistent data.

---

## Key Resume & Interview Discussion Points

- **Layered Architecture:** Strict separation of concerns across Controller, Service, and Repository layers.
- **RESTful Best Practices:** Consistent HTTP response status codes (`201 Created`, `200 OK`, `404 Not Found`, `400 Bad Request`), idempotent operations, and global exception handling.
- **Resilient Database Configuration:** Dual-profile design enabling effortless local review via H2 and enterprise persistence via MySQL with connection pooling.
- **API Documentation:** Interactive documentation powered by SpringDoc OpenAPI 3.0.
- **Production Containerization:** Optimized multi-stage Docker builds separating Maven compilation from the slim JRE runtime.
