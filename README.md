# Expense Tracker

A REST API for managing user expenses. The project uses Spring Boot, Spring Data JPA, Spring Security, JWT, and MySQL.

## Technologies

- Java 25
- Spring Boot 4.1
- Spring Web MVC
- Spring Data JPA
- Spring Security
- JWT
- MySQL
- Maven

## Requirements

- Java 25
- Maven lub Maven Wrapper
- XAMPP/MySQL

## Database setup

Start MySQL and create the database:

```sql
CREATE DATABASE expense_tracker;
```

The default configuration is located in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/expense_tracker
spring.datasource.username=root
spring.datasource.password=
```

If your database uses different credentials, update this file.

## Running the application

On Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

The application will be available at:

```text
http://localhost:8080
```

## Endpoints

### Registration

`POST /api/v1/auth/register`

```json
{
  "email": "user@example.com",
  "password": "password123"
}
```

### Login

`POST /api/v1/auth/login`

```json
{
  "email": "user@example.com",
  "password": "password123"
}
```

After logging in, send the JWT token in the following header:

```text
Authorization: Bearer <token>
```

If the login endpoint does not return a token yet, finish the `AuthServiceImpl` implementation first.

### Create an expense

`POST /api/v1/expenses`

```json
{
  "title": "Grocery shopping",
  "amount": 125.50,
  "category": "FOOD",
  "date": "2026-09-04"
}
```

### Get expenses

`GET /api/v1/expenses?period=PAST_WEEK`

Available `period` values:

- `PAST_WEEK`
- `PAST_MONTH`
- `LAST_3_MONTHS`
- `PAST_YEAR`
- `CUSTOM`

## Expense categories

```text
FOOD
TRANSPORTATION
ENTERTAINMENT
UTILITIES
HEALTHCARE
EDUCATION
PERSONAL_CARE
TRAVEL
OTHER
```

## Testing with Postman

1. Start the application and MySQL.
2. Send a login request.
3. Copy the JWT token from the response.
4. For requests to `/api/v1/expenses`, set:
   - `Authorization`: Bearer Token
   - value: the copied token
   - `Content-Type`: `application/json`
5. Send a request to create or retrieve expenses.
