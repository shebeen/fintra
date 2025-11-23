# Fintra Stock Trading API

Fintra is a Spring Boot application that provides a RESTful API for managing stock trades. It allows you to create, retrieve, update, and delete trade records, with data persisted in a MySQL database.

## Features

-   **CRUD Operations:** Full support for creating, reading, updating, and deleting stock trades.
-   **Data Persistence:** Uses Spring Data JPA to store trade data in a MySQL database.
-   **Input Validation:** Ensures that all incoming data is valid and meets the required constraints.
-   **In-Memory Testing:** The test environment is configured to use an in-memory H2 database for fast and reliable testing.

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
2.  **Configure the database:**
    -   Open `src/main/resources/application.properties`.
    -   Set the `spring.datasource.password` property to your MySQL root password. You can also set this as an environment variable:
        ```sh
        export SPRING_DATASOURCE_PASSWORD=your_password
        ```
3.  **Run the application:**
    ```sh
    ./mvnw spring-boot:run
    ```

The application will start on port 8080.
