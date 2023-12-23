# springboot_ping_app
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ping Web Application</title>
</head>
<body>

    <h1>Ping Web Application</h1>

    <p>This is a simple web application for a ping utility based on Spring Boot 2+.</p>

    <h2>Requirements</h2>
    <ul>
        <li>Java 11+</li>
        <li>Spring Boot 2+</li>
        <li>Hibernate</li>
        <li>Flyway</li>
        <li>Thymeleaf (or any other templating engine and framework)</li>
        <li>Tests</li>
    </ul>

    <h2>Getting Started</h2>

    <h3>Prerequisites</h3>
    <ul>
        <li>Docker</li>
        <li>Docker Compose</li>
    </ul>

    <h3>Build and Run with Docker Compose</h3>

    <ol>
        <li>Clone the repository:</li>
    </ol>
    <pre><code>git clone https://github.com/yourusername/ping-web-app.git
    cd ping-web-app</code></pre>

    <ol start="2">
        <li>Build the application with Maven:</li>
    </ol>
    <pre><code>./mvnw clean package</code></pre>

    <ol start="3">
        <li>Build and start the Docker containers using Docker Compose:</li>
    </ol>
    <pre><code>docker-compose up --build</code></pre>

    <p>This command will build the Docker images and start the containers for Tomcat9 and PostgreSQL.</p>

    <ol start="4">
        <li>Access the application in your web browser:</li>
    </ol>
    <p><a href="http://localhost:8081" target="_blank">http://localhost:8081</a></p>

    <h2>Usage</h2>

    <ul>
        <li>Navigate to the "Ping Results" page to view a list of ping results with pagination (5 results per page).</li>
        <li>Use the search functionality to filter results by domain or IP, date range, and test status.</li>
        <li>Click on a result to view detailed information about the ping test.</li>
    </ul>

    <h2>Development</h2>

    <h3>Running Locally</h3>

    <ol>
        <li>Ensure you have PostgreSQL running locally on port 5432.</li>
        <li>Update the <code>application.properties</code> file in the <code>src/main/resources</code> directory with your local PostgreSQL configuration.</li>
        <li>Run the application using Maven:</li>
    </ol>
    <pre><code>./mvnw spring-boot:run</code></pre>

    <ol start="4">
        <li>Access the application in your web browser:</li>
    </ol>
    <p><a href="http://localhost:8080" target="_blank">http://localhost:8080</a></p>

    <h3>Database Migrations</h3>

    <p>Database migrations are managed using Flyway. To apply migrations manually, run the following Maven command:</p>
    <pre><code>./mvnw flyway:migrate</code></pre>

    <h2>Testing</h2>

    <p>Run the tests using Maven:</p>
    <pre><code>./mvnw test</code></pre>

    <h2>Contributing</h2>

    <ol>
        <li>Fork the repository.</li>
        <li>Create a new branch for your feature or bugfix: <code>git checkout -b feature-name</code>.</li>
        <li>Commit your changes: <code>git commit -m "Description of changes"</code>.</li>
        <li>Push to the branch: <code>git push origin feature-name</code>.</li>
        <li>Create a new pull request.</li>
    </ol>

    <h2>License</h2>

    <p>This project is licensed under the MIT License - see the <a href="LICENSE" target="_blank">LICENSE</a> file for details.</p>

</body>
</html>
