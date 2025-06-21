# Quiz App MicroService
 
# 🧠 Quiz Application - Monolithic to Microservices

A full-fledged Quiz Application developed using **Java Spring Boot**, starting as a **monolithic** application and later refactored into a **microservices** architecture. The application allows users to **create** quizzes and **fetch** them anytime with clean APIs and service separation.

---

## 📌 Features

- 📝 Create a new quiz with custom questions.
- 📚 Retrieve quiz by ID at any time.
- 🏗️ Initially built as a Monolithic architecture.
- ⚙️ Later modularized into Microservices using Spring Cloud stack.
- 🧪 Interactive and easily testable endpoints with Postman or Swagger.

---

## 🧰 Tech Stack

### ✅ Core Technologies

- **Java 21**
- **Spring Boot**
- **Spring Web**
- **Spring Data JPA (Hibernate)**
- **PostgreSQL**

### ✅ Microservice Components

- **OpenFeign Client** – Inter-service communication
- **Eureka Server** – Service registry for discovery
- **Spring Cloud API Gateway** – Routing and centralized entry point
- **Spring DevTools** – Hot reload during development
- **Lombok** – Boilerplate code reduction

---

## 🧱 Microservice Architecture
        +---------------------+
        |     API GATEWAY     |
        +---------------------+
                    |
    +-------------------------------+
    |            EUREKA             |
    +-------------------------------+
       /                        \
    +--------------+ +------------------+
    | Quiz Service | | Question Service | 
    +--------------+ +------------------+ 

- **Quiz Service**: Manages quiz creation and fetching.
- **Question Service**: Manages Question DataBase and gives questions to quiz service to create quiz.

---

## 📦 Installation

### Prerequisites

- Java 21+
- PostgreSQL
- **Preferred Intellij IDEA**

### Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/quiz-app-microservice.git
   cd quiz-app-microservice

2. **application.properties**
   - Set your PostgreSQL credentials for Quiz Service and Question Service in application.properties, in the feilds {username} and {passwd}
   - You can alter the server.port in application properties if you want to
         -- **or by default the Ports are**
         -- **Quiz Service** - 8095
         -- **Question Service** - 8080
         -- **API Gateway** - 8765
         -- **Service Registry** - 8761

3. **Run Each First Service Registry and remaning three**
   - you can send every request through port 8765 or individual ports



# 🧑‍💻 Author
# Vinu Kanth
  - **💼 Java Backend Developer**
  - **🌱 Exploring Spring Cloud and Microservice architecture**
