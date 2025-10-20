Gym Management Application

This is a **Spring Boot project** I built to practice backend development and learn how real-world applications are structured.  
It’s a REST API for managing a gym — with users, trainers, managers, memberships, and gym classes.

## 💡 What It Does

- Create, read, update, and delete gym classes, users, trainers, managers, and memberships;  
- Uses DTOs and Mappers (MapStruct) to keep data clean between layers;
- Has services, repositories, and controllers for each entity;  
- Includes Swagger for documentation;  
- Tested with **JUnit 5** and **Mockito**

Tech-Stack

- Java 17  
- Spring Boot  
- Spring Data JPA  
- MapStruct  
- Maven  
- Lombok  
- JUnit 5 & Mockito  
- Swagger (OpenAPI 3)

Project Structure

src/
main/java/GymManagement/GymManagement/
Controllers/
   DTOs/
   Mappers/
   Model/
   Repositories/
   Services/
 test/java/GymManagement/GymManagement/
    Controllers/
    Services/


How to Run my Application

1. Clone the project  
   bash
   git clone https://github.com/yourusername/gym-management.git
   cd gym-management
   
2. Build it with Maven:

bash
mvn clean install
Run it
mvn spring-boot:run

3. Open your browser and go to:
http://localhost:8080/swagger-ui/index.html

 Testing my Application
 
All services and controllers have unit tests.
Tests use Mockito to mock dependencies and JUnit 5 to verify behavior.

Each test class has a setup method that runs before every test.

Example naming style:
methodName_ShouldDoSomething_WhenCondition()
Example API (Gym Class)

POST /api/gym_management/gymclasses

json
{
  "name": "8 Hour Arm Workout",
  "date": "30.05.2026 14:30"
}
Response:
json
{
  "id": 1,
  "name": "8 Hour Arm Workout",
  "date": "30.05.2026 14:30"
}
Author
Marc
The Most Pragmatic Aspiring Java Developer
Learning Spring Boot, clean architecture, and testing.

“A slack cable can carry nothing, but one under tension can haul mountains.”
