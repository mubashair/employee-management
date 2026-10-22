# Employee Management System

A Spring Boot portfolio project for managing employee records using a REST API and MySQL.

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Git and GitHub

## Features

- Create employee records
- View all employees
- Find an employee by ID
- Update employee information
- Delete employee records
- Store employee data in MySQL

*Note: List only the features implemented in your project.*

## Prerequisites

- JDK compatible with the project
- MySQL Server
- Maven or Maven Wrapper
- Git

## Database Setup

Create the database in MySQL:

```sql
CREATE DATABASE portfolio_db;
```

Configure these environment variables on your computer:

- `DB_URL` — `jdbc:mysql://localhost:3306/portfolio_db`
- `DB_USERNAME` — `portfolio_user`
- `DB_PASSWORD` — your MySQL application-user password

Never commit database passwords or other secrets to GitHub.

## Run the Application

From the project root, run:

```bash
mvn spring-boot:run
```

If the project includes the Maven Wrapper, you can use:

```bash
mvnw.cmd spring-boot:run
```

## API Testing

Use Postman to test the employee CRUD endpoints implemented in the application.

## Future Improvements

- Request validation
- Global exception handling
- Spring Security and JWT authentication
- Unit and integration tests
- React frontend
- Docker deployment

Author
Mubashar Hussain Shah
