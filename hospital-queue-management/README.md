# Hospital Queue Management System

A full-stack hospital queue and appointment management system. Staff and doctors
manage patients, doctors, and appointments; patients get an auto-generated
token number per doctor and can check their queue status.

**Backend:** Spring Boot (Java 17), Spring Data JPA, MySQL
**Frontend:** Static HTML/CSS/JS, served directly by Spring Boot

## Features

- Add and view patients and doctors
- Book appointments with an auto-generated **token number**, scoped per doctor
  (each doctor's queue numbers independently)
- Mark appointments as `Waiting` → `Consulted`
- Printable appointment slip with token number
- Role-based views: staff/admin dashboard, doctor dashboard, and a public
  token-status lookup page for patients
- Live dashboard counts (total patients, doctors, appointments, waiting)

## Project Structure

```
src/main/java/com/hospital/queue_management
├── controller     # REST endpoints (Patient, Doctor, Appointment)
├── service        # Business logic, e.g. token number assignment
├── repository     # Spring Data JPA repositories
└── entity         # Patient, Doctor, Appointment

src/main/resources/static
├── login.html            # Role selection + login
├── index.html            # Staff/admin dashboard
├── doctor-dashboard.html # Doctor's queue view
└── patient-status.html   # Public token/status lookup
```

## API Endpoints

| Method | Endpoint                  | Description                          |
|--------|----------------------------|--------------------------------------|
| POST   | /patients/add              | Add a patient                        |
| GET    | /patients/all               | List all patients                    |
| POST   | /doctors/add                | Add a doctor                         |
| GET    | /doctors/all                | List all doctors                     |
| POST   | /appointments/book          | Book an appointment (assigns token)  |
| GET    | /appointments/all           | List all appointments                |
| PUT    | /appointments/update/{id}   | Mark appointment as Consulted        |
| DELETE | /appointments/delete/{id}   | Delete an appointment                |

## Running Locally

1. Clone the repo and install MySQL locally, or point it at a remote instance.
2. Create a database: `CREATE DATABASE hospital_queue;`
3. Either edit `src/main/resources/application.properties` directly, or set
   environment variables (recommended, see below).
4. Run:
   ```
   ./mvnw spring-boot:run
   ```
5. Open `http://localhost:8080/login.html`

   **Demo logins** (hardcoded client-side for demo purposes — see Known
   Limitations below):
   - Staff: `admin` / `admin123` or `reception` / `rec123`
   - Doctor: `doctor` / `doc123`

## Environment Variables (for local override or deployment)

| Variable      | Default                                        |
|---------------|-------------------------------------------------|
| `DB_URL`      | `jdbc:mysql://localhost:3306/hospital_queue`     |
| `DB_USERNAME` | `root`                                          |
| `DB_PASSWORD` | `root123`                                       |
| `PORT`        | `8080`                                          |

## Deployment

The frontend is served as static files from the same Spring Boot app (no
separate frontend deploy needed), so you only need to deploy one service.

1. **Database**: create a free MySQL instance (Railway, Aiven, or
   PlanetScale-compatible provider work well).
2. **Backend + frontend**: deploy this repo to Render or Railway as a Java/Maven
   web service. Set `DB_URL`, `DB_USERNAME`, `DB_PASSWORD` as environment
   variables in the platform's dashboard — do **not** commit real credentials.
3. Once deployed, your live URL serves everything: `https://your-app.onrender.com/login.html`

## Known Limitations (by design, for a learning/portfolio project)

- **Login is not real authentication.** Credentials are checked in
  client-side JavaScript and stored in `localStorage`. There's no backend
  session, JWT, or password hashing. Anyone can view source or navigate
  directly to `index.html` to bypass it. This is intentionally out of scope
  for now — see Future Enhancements.
- No input validation or centralized exception handling yet on the backend.
- `Appointment` stores `patientName` / `doctorName` as plain strings rather
  than foreign keys to `Patient` / `Doctor` — fine for this scale, but not
  normalized.

## Future Enhancements

- Real backend authentication (Spring Security + JWT) and role-based
  authorization
- Foreign-key relationships between Appointment ↔ Patient/Doctor
- Input validation (`@Valid`) and a global exception handler
- Pagination and search on patient/appointment lists
- Swagger/OpenAPI docs

## Technologies Used

- Java 17, Spring Boot, Spring Data JPA, Hibernate, MySQL, Maven
