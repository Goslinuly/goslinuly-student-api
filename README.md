# Goslinuly Student API (Goslinuly, ID gosling)

Spring Boot CRUD application for managing students, built for the DevOps midterm.

| | |
|---|---|
| Student | Goslinuly |
| Group | Goslinuly |
| Student ID | gosling |
| Image | `goslinuly-student-api:v1.0-gosling` |
| URL | http://localhost:8037 |

## Stack
Java 17, Spring Boot 3, Gradle wrapper, PostgreSQL 16, Docker Compose, Jenkins.

## Run
```bash
cp .env.example .env    # set your own password
docker compose up -d --build
curl http://localhost:8037/
```

## API
| Method | Path | Description |
|---|---|---|
| GET | `/` | Student/application info |
| GET | `/api/students` | List students |
| GET | `/api/students/{id}` | Get one student (404 if missing) |
| POST | `/api/students` | Create student |
| DELETE | `/api/students/{id}` | Delete student |
