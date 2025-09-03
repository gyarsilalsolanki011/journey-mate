# 🌍 JourneyMate – Trip Management Application
**JourneyMate** is a Spring Boot–based Trip Management System that allows users to create, search, and manage trips.
It provides REST APIs for trip booking, searching by destination, filtering, and managing trip statuses with proper exception handling.

## ✨ Features
- ✅ CRUD Operations for trips (Create, Read, Update, Delete)
- ✅ Pagination & Sorting for listing trips
- ✅ Search Trips by Destination (partial & full match)
- ✅ Enum-based Trip Status (e.g., PLANNED, COMPLETED, CANCELLED)
- ✅ Global Exception Handling with @ControllerAdvice
- ✅ DTO–Entity Mapping with Mapper layer
- ✅ Repository Layer with Spring Data JPA
- ✅ Service Layer with Business Logic and try–catch handling
- ✅ RESTful APIs with ResponseEntity responses

## 📂 Project Structure
```graphql
journeymate/
├── controller/           # REST controllers (TripController)
├── dto/                  # Data Transfer Objects (TripDto, ErrorResponseDto)
├── entity/               # JPA Entities (Trip.java)
├── enum/                 # Enums (TripStatus.java)
├── exception/            # Custom exceptions + GlobalExceptionHandler
├── mapper/               # DTO ↔ Entity mappers
├── repository/           # Spring Data JPA Repositories
├── service/              # Service interfaces & implementations
├── util/                 # Utility classes
└── JourneyMateApplication.java  # Main Spring Boot app
```

## ⚙️ Tech Stack
- **Backend:** Java 17, Spring Boot
- **Database:** MySQL (with JPA/Hibernate)
- **Build Tool:** Maven
- **Testing:** JUnit & Mockito
- **API Testing:** Postman / Swagger UI

## 🚀 API Endpoints
**🔹 Trip Management**

| Method   | Endpoint                                                       | Description                                        |
|----------|----------------------------------------------------------------|----------------------------------------------------|
| `POST`   | `/api/trips`                                                   | Create a new trip                                  |
| `GET`    | `/api/trips`                                                   | Get all trips (with pagination & sorting)          |
| `GET`    | `/api/trips/{id}`                                              | Get trip by ID                                     |
| `PUT`    | `/api/trips/{id}`                                              | Update a trip                                      |
| `DELETE` | `/api/trips/{id}`                                              | Delete a trip                                      |
| `GET`    | `/api/trips/search?destination=Paris`                          | Search trips by destination                        |
| `GET`    | `/api/trips?status=PLANNED`                                    | Filter trip by status                              |
| `GET`    | `/api/trips/daterange?startDate=2025-09-01&endDate=2025-09-30` | Filter trips by date range                         |
| `GET`    | `/api/trips/summary`                                           | Get trip summary (total trips, minPrice, maxPrice) |

</br>**🔹 Example Requests**

create Trip
```http
POST /api/trips
Content-Type: application/json

{
  "title": "Paris Vacation",
  "destination": "Paris",
  "startDate": "2025-09-10",
  "endDate": "2025-09-20",
  "price": 50000,
  "status": "PLANNED"
}
```

Get Trips (with Pagination & Sorting)
```http
GET /api/trips?page=0&size=5&sort=startDate,asc
```

Search by Destination
```http
GET /api/trips/search?destination=Paris
```

## 🛡️ Exception Handling

Handled globally using `@ControllerAdvice:`
- `TripNotFoundException` → 404 NOT FOUND
- `InvalidTripStatusException` → 400 BAD REQUEST
- `TripServiceException` → 500 INTERNAL SERVER ERROR
- `Generic Exception` → 500 INTERNAL SERVER ERROR

Response Example:
```json
{
  "timestamp": "2024-10-01T12:00:00.000+00:00",
  "status": 404,
  "message": "Trip not found with ID: 100",
  "path": "/api/trips/100"
}
```

## ▶️ How to Run

1. Clone the repo
```bash
https://github.com/gyarsilalsolanki011/DS-SEP-2025-178.git
cd DS-SEP-2025-1725-178
```

2. Configure MySQL DB in `application.properties`
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/journeymate
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```
3. Run the app
```bash
mvn spring-boot:run
```

4. Access API at:
```bash
http://localhost:8080/api/trips
```

## 👨‍💻 Developer

`Gyarsilal Solanki`
- [![LinkedIn](https://img.shields.io/badge/LinkedIn-%230A66C2.svg?logo=LinkedIn&logoColor=white)](https://www.linkedin.com/in/gyarsilal-solanki) [![GitHub](https://img.shields.io/badge/GitHub-%23121011.svg?logo=github&logoColor=white)](https://github.com/gyarsilalsolanki011) [![Pinterest](https://img.shields.io/badge/Pinterest-%23BD081C.svg?logo=Pinterest&logoColor=white)](https://in.pinterest.com/gyarsilalsolanki011)
- [![Instagram](https://img.shields.io/badge/Instagram-%23E4405F.svg?logo=Instagram&logoColor=white)](https://instagram.com/itz_gsl_tiger) [![Twitter](https://img.shields.io/badge/Twitter-%231DA1F2.svg?logo=Twitter&logoColor=white)](https://x.com/Itz_gsl_tiger) [![Snapchat](https://img.shields.io/badge/Snapchat-%23FFFC00.svg?logo=Snapchat&logoColor=black)](https://www.snapchat.com/add/itz_gsltiger?share_id=7OCVgTGQWSg&locale=en-GB)
- [![WhatsApp](https://img.shields.io/badge/WhatsApp-%2325D366.svg?logo=whatsapp&logoColor=white)](https://api.whatsapp.com/send/?phone=919111852267) [![Gmail](https://img.shields.io/badge/Email-D14836?logo=gmail&logoColor=white)](mailto:gyarsilalsolanki011@gmail.com)


Join us to discuss ideas, share feedback, and coordinate contributions:  
[![Join Discord](https://img.shields.io/discord/1405808666179014697?color=4CBB17&label=Join%20Us%20on%20Discord&logo=discord&logoColor=blue)](https://discord.gg/Zrc9x3ts)