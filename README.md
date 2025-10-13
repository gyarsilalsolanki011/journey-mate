<a name="journeymate"></a>
# 🌍 JourneyMate – Trip Management Application
**JourneyMate** is a *Spring Boot + Hibernate (JPA)* based Trip Management System built as part of the **BT-JAVA-SEP-2025 Assignment**.
It provides ***REST APIs*** to *create, search, filter, update, delete,* and *summarize* trips with proper validation and exception handling.

<br>

<a name="features"></a>
## ✨ Features
- ✅ CRUD Operations for trips (Create, Read, Update, Delete)
- ✅ Pagination & Sorting for listing trips
- ✅ Search trips by destination (partial & full match)
- ✅ Filter trips by status (PLANNED, ONGOING, COMPLETED)
- ✅ Filter trips between start & end dates
- ✅ Trip summary (total, min, max, average price)
- ✅ DTO ↔ Entity mapping via Mapper layer
- ✅ Custom Validations to validate Dates 
- ✅ Global Exception Handling with @ControllerAdvice
- ✅ Swagger (OpenAPI) Documentation
- ✅ Unit Tests (JUnit + Mockito)
- ✅ Postman Collection for API Testing

<br>

<a name="project-structure"></a>
## 📂 Project Structure
```graphql
journeymate/
├── controller/      # REST Controllers (TripController, etc.)
├── exception/       # GlobalExceptionHandler, custom exceptions
├── mapper/          # DTO ↔ Entity mappers (TripMapper, etc.)
├── model/           # Data models(enums, entities, DTOs)
│   ├── dto/         # Data Transfer Objects (TripDTO, etc.)
│   ├── entity/      # JPA Entities (Trip, User, etc.)
│   └── enums/       # Enum definitions (TripStatus, etc.)
├── repository/      # Spring Data JPA repositories
├── seeder/          # DataLoader / Database seeding classes
├── service/         # Business logic layer (TripService, etc.)
├── util/            # Utility classes (DateUtils, etc.)
├── validation/      # Custom validators / annotations
└── JourneyMateApplication.java  # Spring Boot main class
```

<br>

<a name="tech-stack"></a>
## ⚙️ Tech Stack
- **Backend:** Java 17, Spring Boot
- **Database:** MySQL (with JPA/Hibernate)
- **Build Tool:** Maven
- **Testing:** JUnit & Mockito
- **API Testing:** Postman / Swagger UI

<br>

<a name="api-endpoints"></a>
## 🚀 API Endpoints
**🔹 Trip Management**

| ***Method*** | ***Endpoint***                                         | ***Description***                             |
|--------------|--------------------------------------------------------|-----------------------------------------------|
| **POST**     | `/api/trips`                                           | Create a new trip                             |
| **GET**      | `/api/trips`                                           | Get all trips (with pagination & sorting)     |
| **GET**      | `/api/trips/{id}`                                      | Get trip by ID                                |
| **PUT**      | `/api/trips/{id}`                                      | Update a trip                                 |
| **DELETE**   | `/api/trips/{id}`                                      | Delete a trip                                 |
| **GET**      | `/api/trips/search?destination=Paris`                  | Search trips by destination                   |
| **GET**      | `/api/trips/filter?status=PLANNED`                     | Filter trips by status                        |
| **GET**      | `/api/trips/daterange?start=2025-09-01&end=2025-09-30` | Filter trips by date range                    |
| **GET**      | `/api/trips/summary`                                   | Get trip summary (total, min, max, avg price) |


</br>**🔹 Example Requests**

create Trip
```http
POST /api/trips
Content-Type: application/json

{
  "destination": "Paris",
  "startDate": "2025-09-10",
  "endDate": "2025-09-20",
  "price": 1500.00,
  "status": "PLANNED"
}
```

Get Trips (Pagination & Sorting)
```http
GET /api/trips?page=0&size=5&sort=startDate,asc
```

Search by Destination
```http
GET /api/trips/search?destination=Paris
```

Filter by Status
```http
GET /api/trips/filter?status=ONGOING
```

Trips Between Dates
```http
GET /api/trips/daterange?start=2025-09-01&end=2025-09-30
```

Trip Summary
```http
GET /api/trips/summary
```

<br>

<a name="exception-handling"></a>
## 🛡️ Exception Handling

Global exceptions handled via `@ControllerAdvice:`
- `TripNotFoundException` → 404 NOT FOUND (for missing trips)
- `TripServiceException` → 400 BAD REQUEST (for business logic errors)
- `MethodArgumentNotValidException` → 400 BAD REQUEST (for validation errors)
- `ConstraintViolationException` → 400 BAD REQUEST (for custom validation errors)
- `Generic Exception` → 500 INTERNAL SERVER ERROR (for unhandled exceptions)

Error Response Examples:
```json
{
    "path": "uri=/api/trips/9",
    "error": "validation error",
    "details": {
        "endDate": "End date can not before start date",
        "price": "must be greater than 0",
        "destination": "must not be blank",
        "tripStatus": "Invalid trip status: Not Planned"
    }
}
```

<br>

<a name="how-to-run"></a>
## ▶️ How to Run

#### 1. Clone Repository
```bash
git clone https://github.com/gyarsilalsolanki011/BT-JAVA-SEP-2025-178.git
cd BT-JAVA-SEP-2025-178
```

#### 2. Set up MySQL Database
**Create MySQL database named `trips_db`**
```sql
CREATE DATABASE trips_db;
```
 
**setup using command line**
```bash
mysql -u root -p < src/main/resources/setup/trips_db.sql
```
> [!TIP]  
> Setup using dev profile via Seeder(optional)


#### 3. Configure MySQL DB in `application.properties`
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/trips_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```
#### 4. Run application
```bash
mvn spring-boot:run
```
OR
```bash
mvn package
java -jar target/spring-boot-journey-mate.jar
```

#### 5. Access APIs:
- Base URL → `http://localhost:8080/api/trips`
- Swagger → `http://localhost:8080/swagger-ui.html`

<br>

<a name="testing"></a>
## 🧪 Testing

**Run Unit Tests**
```bash    
mvn test
```
**Run Postman Collection:**
- Download postman collection → [`TripCollection.postman_collection.json`](src/main/resources/setup/TripCollection.postman_collection.json)
- Import this collection into Postman and execute each API's requests.

<br>

<a name="deliverables"></a>
## 📦 Deliverables (as per assignment)
- ✅ Complete Spring Boot Project on GitHub
- ✅ Public repository: BT-JAVA-SEP-2025-178
- ✅ README.md having project details:
  - [Steps to run](#how-to-run) the application
  - [API endpoints](#api-endpoints) with examples
- ✅ Postman Collection (resource path: [`/setup/TripCollection.postman_collection.json`](src/main/resources/setup/TripCollection.postman_collection.json) )
- ✅ Database Script (resource path: [`/setup/trips_db.sql`](src/main/resources/setup/trips_db.sql))

<br>

<a name="developer"></a>
## 👨‍💻 Developer

`Gyarsilal Solanki`
- [![LinkedIn](https://img.shields.io/badge/LinkedIn-%230A66C2.svg?logo=LinkedIn&logoColor=white)](https://www.linkedin.com/in/gyarsilal-solanki) [![GitHub](https://img.shields.io/badge/GitHub-%23121011.svg?logo=github&logoColor=white)](https://github.com/gyarsilalsolanki011)
- [![Gmail](https://img.shields.io/badge/Email-D14836?logo=gmail&logoColor=white)](mailto:gyarsilalsolanki011@gmail.com)


Join us to discuss ideas, share feedback, and coordinate contributions:  
[![Join Discord](https://img.shields.io/discord/1405808666179014697?color=4CBB17&label=Join%20Us%20on%20Discord&logo=discord&logoColor=blue)](https://discord.gg/Zrc9x3ts)

***If you find this project helpful, consider giving it a ⭐ to support!***

