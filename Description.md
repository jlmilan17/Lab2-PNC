# PNC - Spring Boot Police Notes Application

## Tech Stack
- **Framework:** Spring Boot 4.0.6
- **Language:** Java 21
- **Build:** Maven (use `./mvnw`)
- **Database:** PostgreSQL 16 (via Docker)
- **ORM:** Spring Data JPA + Hibernate

## Development Commands

```bash
# Run locally (requires PostgreSQL running)
./mvnw spring-boot:run

# Run tests
./mvnw test

# Build JAR (skip tests)
./mvnw clean package -DskipTests

# Run with Docker (db + app)
docker-compose up --build
```

## Required Environment
Create `.env` with:
```
DB_NAME=pnc_database
DB_USER=postgres
DB_PASSWORD=<your-password>
```
Then `DB_URL=jdbc:postgresql://localhost:5432/${DB_NAME}` (or `db:5432` in Docker)

## Docker Notes
- **app:** port 8080
- **db:** port 5433 (not default 5432)
- Use `Dockerfile.dev` for development with hot reload

## Project Structure
- `src/main/java/com/lab2/pnc/` - main source
  - `Controller/` - REST endpoints
  - `Service/ServiceImp/` - business logic
  - `Repository/` - JPA repositories
  - `Model/` - entities, DTOs, enums
  - `HandlerException/` - custom exceptions + global handler

## Key Config
- `application.yaml` uses env vars: `DB_URL`, `DB_USER`, `DB_PASSWORD`
- `data.sql` auto-runs on startup (ddl-auto: update)
- Lombok used for @Data, @Builder on all models

## Test
Minimal - only `PncApplicationTests.contextLoads()`. Add more tests in `src/test/java/`.