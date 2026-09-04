# Expense Tracker

Aplikacja REST API do zarządzania wydatkami użytkownika. Projekt wykorzystuje Spring Boot, Spring Data JPA, Spring Security, JWT oraz bazę MySQL.

## Technologie

- Java 25
- Spring Boot 4.1
- Spring Web MVC
- Spring Data JPA
- Spring Security
- JWT
- MySQL
- Maven

## Wymagania

- Java 25
- Maven lub Maven Wrapper
- XAMPP/MySQL

## Konfiguracja bazy danych

Uruchom MySQL i utwórz bazę:

```sql
CREATE DATABASE expense_tracker;
```

Domyślna konfiguracja znajduje się w pliku `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/expense_tracker
spring.datasource.username=root
spring.datasource.password=
```

Jeśli baza ma inne dane logowania, zmień je w tym pliku.

## Uruchomienie

Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

Aplikacja uruchomi się pod adresem:

```text
http://localhost:8080
```

## Endpointy

### Rejestracja

`POST /api/v1/auth/register`

```json
{
  "email": "user@example.com",
  "password": "password123"
}
```

### Logowanie

`POST /api/v1/auth/login`

```json
{
  "email": "user@example.com",
  "password": "password123"
}
```

Po zalogowaniu token JWT należy przekazywać w nagłówku:

```text
Authorization: Bearer <token>
```

Jeżeli endpoint logowania nie zwraca jeszcze tokena, należy najpierw dokończyć implementację `AuthServiceImpl`.

### Dodanie wydatku

`POST /api/v1/expenses`

```json
{
  "title": "Zakupy spożywcze",
  "amount": 125.50,
  "category": "FOOD",
  "date": "2026-09-04"
}
```

### Pobranie wydatków

`GET /api/v1/expenses?period=PAST_WEEK`

Dostępne wartości `period`:

- `PAST_WEEK`
- `PAST_MONTH`
- `LAST_3_MONTHS`
- `PAST_YEAR`
- `CUSTOM`

## Kategorie wydatków

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

## Testowanie w Postmanie

1. Uruchom aplikację i MySQL.
2. Wyślij żądanie logowania.
3. Skopiuj token JWT z odpowiedzi.
4. W żądaniach do `/api/v1/expenses` ustaw:
   - `Authorization`: Bearer Token
   - wartość: skopiowany token
   - `Content-Type`: `application/json`
5. Wyślij żądanie utworzenia lub pobrania wydatków.
