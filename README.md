# Event Registration System (Backend)

A robust, secure, and fully functional backend API for an Event Registration System. This project was built as part of the CodeAlpha internship program, demonstrating modern backend development practices including JWT-based authentication, Role-Based Access Control (RBAC), and robust data validation.

##  Features

* **Secure Authentication:** JWT (JSON Web Tokens) for stateless, secure user sessions.
* **Role-Based Access Control (RBAC):** Three distinct user roles (`ADMIN`, `REGISTRAR`, `USER`) with specific API access rules.
* **Event Management:** Organizers (Registrars/Admins) can create and manage events with set capacities.
* **Smart Registration Logic:** * Prevents users from registering for the same event twice.
    * Enforces maximum event capacity limits.
    * Allows users to cancel registrations, dynamically freeing up event slots.
* **Global Exception Handling:** Clean, unified JSON error responses for business logic failures (e.g., 400 Bad Request for "Event Full") and validation errors.
* **Interactive API Documentation:** Integrated Swagger UI / OpenAPI for seamless endpoint testing.

##  Tech Stack

* **Language:** Java 21
* **Framework:** Spring Boot 3.5.x
* **Database:** MongoDB (Spring Data MongoDB)
* **Security:** Spring Security & JJWT
* **Documentation:** SpringDoc OpenAPI (Swagger)
* **Tools:** Maven, Lombok, Spring Boot Validation

##  Project Structure

```text
src/main/java/com/event_management/
├── config/         # Security, JWT filters, and Application configs
├── controller/     # REST API Endpoints
├── dto/            # Data Transfer Objects for secure request/response payloads
├── entity/         # MongoDB Documents (User, Event, Registration)
├── enums/          # Constants (Role, RegistrationStatus)
├── exception/      # GlobalExceptionHandler and custom business exceptions
├── repository/     # Spring Data MongoRepository interfaces
└── service/        # Core business logic and transaction management
```

## Getting Started

#### Prerequisites
* Java 17 or 21 installed.
* Maven installed.
* MongoDB running locally (default port 27017) or a MongoDB Atlas URI.

#### Installation

1. **Clone the repository:** ``` git clone [https://github.com/vDhinithya/CodeAlpha_Event_Registration.git](https://github.com/vDhinithya/CodeAlpha_Event_Registration.git)
cd event-management-system```
2.  **YAML** ```spring: 
                  data: 
                    mongodb: 
                      host:localhost 
                      port: 27017 
                      database: event_registration_db```

3.  **Run the application** `mvn spring-boot:run` The server will start on `http://localhost:8080`.


API Documentation & Testing (Swagger)
----------------------------------------

This project uses Swagger UI for interactive API documentation.

Once the application is running, navigate to: ``http://localhost:8080/swagger-ui/index.html``

### Testing the Authentication Flow

To fully test the API, follow this flow within the Swagger UI:

1.  **Create an Organizer:**

    *   Go to POST /auth/register.

    *   Provide a JSON body with "role": "REGISTRAR".

    *   Copy the returned JWT token.

2.  **Authenticate:**

    *   Scroll to the top of Swagger and click the green **Authorize** button.

    *   Paste your token and click Authorize.

3.  **Create an Event:**

    *   Go to POST /events and create a new event. Copy the generated Event id.

4.  **Register a User:**

    *   Go to POST /auth/register and create a standard attendee with "role": "USER".

    *   Authorize Swagger with this new user's token.

    *   Go to POST /events/registrations and register for the event using the Event id.


🤝 Acknowledgements
-------------------

Developed as an internship project for **CodeAlpha**..