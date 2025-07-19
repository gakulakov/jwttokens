# JWT Tokens - Spring Boot Application

A Spring Boot web application with JWT token support for user authentication and authorization.

## Description

This application demonstrates the implementation of JWT (JSON Web Token) authentication in a Spring Boot application written in Kotlin. The project includes user and article management with protected endpoints.

**This is a learning project** designed to help developers understand how to implement JWT authentication and authorization in Spring Boot applications. It serves as a practical example for studying JWT token-based security mechanisms.

## Technologies

- **Kotlin** - primary programming language
- **Spring Boot 3.5.3** - framework for creating web applications
- **Spring Security** - security and authentication
- **JWT (JSON Web Tokens)** - authentication tokens
- **Gradle** - build system
- **Java 17** - Java version

## Running the Project

### Prerequisites

- Java 17 or higher
- Gradle

### Installation and Setup

1. Clone the repository:
```bash
git clone https://github.com/gakulakov/jwttokens
cd jwttokens
```

2. Run the application:
```bash
./gradlew bootRun
```

Or build and run the JAR file:
```bash
./gradlew build
java -jar build/libs/jwttokens-0.0.1-SNAPSHOT.jar
```

The application will be available at: `http://localhost:8080`

## Configuration

Main application settings are located in `src/main/resources/application.properties`:

```properties
spring.application.name=jwttokens
```

## Dependencies

Main project dependencies:

- `spring-boot-starter-security` - Spring Security
- `spring-boot-starter-web` - Spring Web
- `jjwt-api`, `jjwt-jackson`, `jjwt-impl` - JWT libraries
- `jackson-module-kotlin` - Kotlin support in Jackson
- `kotlin-reflect` - Kotlin reflection

## Testing

To run tests use:

```bash
./gradlew test
```

## License

This project is created for educational purposes.

## Author

The project is developed to demonstrate JWT token work in Spring Boot applications. 