# Goslinuly Student API (Goslinuly, ID gosling)

Student management REST API with documentation by Goslinuly.

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
| PUT | `/api/students/{id}` | Update student (404 if missing) |
| DELETE | `/api/students/{id}` | Delete student |

## Changes by Goslinuly
- **feature/goslinuly-crud**: implemented the *Update* operation
  (`PUT /api/students/{id}`) with validation, 404 handling and two tests.
