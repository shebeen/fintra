# Fintra Stock Trading API

Fintra is a Spring Boot application that provides a RESTful API for managing stock trades. It allows you to create, retrieve, update, and delete trade records, with data persisted in a MySQL database.

## Features

-   **CRUD Operations:** Full support for creating, reading, updating, and deleting stock trades.
-   **Data Persistence:** Uses Spring Data JPA to store trade data in a MySQL database.
-   **Input Validation:** Ensures that all incoming data is valid and meets the required constraints.
-   **In-Memory Testing:** The test environment is configured to use an in-memory H2 database for fast and reliable testing.
-   **API Documentation:** The API is documented using the OpenAPI 3.0 specification in the `openapi.yaml` file.

## Getting Started

### Prerequisites

-   Java 17 or later
-   Maven
-   MySQL

### Building and Running the Application

1.  **Clone the repository:**
    ```sh
    git clone https://github.com/your-username/fintra.git
    cd fintra
    ```
2.  **Configure the environment:**
    -   **Database:** Set the `SPRING_DATASOURCE_PASSWORD` environment variable to your MySQL root password.
        ```sh
        export SPRING_DATASOURCE_PASSWORD=your_password
        ```
    -   **JWT Secret:** Set the `JWT_SECRET` environment variable to a long, random, and secure string. This is required for signing authentication tokens.
        ```sh
        export JWT_SECRET=your_super_secret_key_that_is_long_and_secure
        ```
3.  **Run the application:**
    ```sh
    ./mvnw spring-boot:run
    ```

The application will start on port 8080.

## API Documentation

The API is documented in the `openapi.yaml` file, which follows the OpenAPI 3.0 specification. You can use tools like [Swagger UI](https://swagger.io/tools/swagger-ui/) to view the documentation in an interactive way.
