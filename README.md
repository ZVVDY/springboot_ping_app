#Ping Web Application
This is a simple web application for a ping utility based on Spring Boot 2+.

#Requirements
Java 17
Spring Boot 3+
Hibernate
Flyway
Thymeleaf (or any other templating engine and framework)
Tests
Getting Started
Prerequisites
Docker
Docker Compose

#Build and Run with Docker Compose
Clone the repository:

bash
Copy code
git clone https://github.com/ZVVDY/springboot_ping_app.git
cd ping-web-app

#Build the application with Maven:

bash
Copy code
./mvnw clean package

#Build and start the Docker containers using Docker Compose:

bash
Copy code
docker-compose up --build
This command will build the Docker images and start the containers for Tomcat9 and PostgreSQL.

#Access the application in your web browser:

arduino
Copy code
http://localhost:8081
Usage
Navigate to the "Ping Results" page to view a list of ping results with pagination (5 results per page).
Use the search functionality to filter results by domain or IP, date range, and test status.
Click on a result to view detailed information about the ping test.
Development
Running Locally
Ensure you have PostgreSQL running locally on port 5432.

Update the application.properties file in the src/main/resources directory with your local PostgreSQL configuration.

#Run the application using Maven:

bash
Copy code
./mvnw spring-boot:run
Access the application in your web browser:

arduino
Copy code
http://localhost:8081
Database Migrations
Database migrations are managed using Flyway. To apply migrations manually, run the following Maven command:

bash
Copy code
./mvnw flyway:migrate
Testing
Run the tests using Maven:

bash
Copy code
./mvnw test
Contributing
Fork the repository.
Create a new branch for your feature or bugfix: git checkout -b feature-name.
Commit your changes: git commit -m "Description of changes".
Push to the branch: git push origin feature-name.
Create a new pull request.
