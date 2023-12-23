# Ping Web Application

This is a simple web application for a ping utility based on Spring Boot 3+.

## Requirements
- Java 17+
- Spring Boot 3+
- Hibernate
- Flyway
- Thymeleaf (or any other templating engine and framework)
- Tests

## Getting Started

### Prerequisites
- Docker
- Docker Compose

### Build and Run with Docker Compose

1. **Clone the repository:**
    ```bash
    git clone https://github.com/ZVVDY/springboot_ping_app.git
    cd ping-web-app
    ```

2. **Build the application with Maven:**
    ```bash
    ./mvnw clean package
    ```

3. **Build and start the Docker containers using Docker Compose:**
    ```bash
    docker-compose up --build
    ```

    This command will build the Docker images and start the containers for Tomcat9 and PostgreSQL.

4. **Access the application in your web browser:**
    ```
    http://localhost:8081
    ```

### Usage

- Navigate to the "Ping Results" page to view a list of ping results with pagination (5 results per page).
- Use the search functionality to filter results by domain or IP, date range, and test status.
- Click on a result to view detailed information about the ping test.

## Development

### Running Locally

1. Ensure you have PostgreSQL running locally on port 5432.

2. Update the `application.properties` file in the `src/main/resources` directory with your local PostgreSQL configuration.

3. Run the application using Maven:
    ```bash
    ./mvnw spring-boot:run
    ```

4. Access the application in your web browser:
    ```
    http://localhost:8081
    ```

### Database Migrations

Database migrations are managed using Flyway. To apply migrations manually, run the following Maven command:

```bash
./mvnw flyway:migrate
