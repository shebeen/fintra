# Fintra Stock Trading API Documentation

## Base URL

`http://localhost:8080`

## Endpoints

### Get All Trades

-   **Endpoint:** `/trades`
-   **Method:** `GET`
-   **Response:**
    -   **200 OK:**
        ```json
        [
            {
                "id": 1,
                "stockSymbol": "AAPL",
                "buyPrice": 150.00,
                "buyDate": "2025-01-15",
                "quantity": 10,
                "totalBuyValue": 1500.00,
                "sellDate": null,
                "sellPrice": null,
                "totalSellValue": null
            }
        ]
        ```

### Get a Trade by ID

-   **Endpoint:** `/trades/{id}`
-   **Method:** `GET`
-   **Response:**
    -   **200 OK:**
        ```json
        {
            "id": 1,
            "stockSymbol": "AAPL",
            "buyPrice": 150.00,
            "buyDate": "2025-01-15",
            "quantity": 10,
            "totalBuyValue": 1500.00,
            "sellDate": null,
            "sellPrice": null,
            "totalSellValue": null
        }
        ```
    -   **404 Not Found:** If the trade with the specified ID is not found.

### Add a New Trade

-   **Endpoint:** `/trades`
-   **Method:** `POST`
-   **Request Body:**
    ```json
    {
        "stockSymbol": "GOOGL",
        "buyPrice": 2800.00,
        "buyDate": "2025-02-10",
        "quantity": 5
    }
    ```
-   **Response:**
    -   **200 OK:**
        ```json
        {
            "id": 2,
            "stockSymbol": "GOOGL",
            "buyPrice": 2800.00,
            "buyDate": "2025-02-10",
            "quantity": 5,
            "totalBuyValue": 14000.00,
            "sellDate": null,
            "sellPrice": null,
            "totalSellValue": null
        }
        ```

### Update a Trade

-   **Endpoint:** `/trades/{id}`
-   **Method:** `PUT`
-   **Request Body:**
    ```json
    {
        "stockSymbol": "GOOGL",
        "buyPrice": 2800.00,
        "buyDate": "2025-02-10",
        "quantity": 5,
        "sellPrice": 3000.00,
        "sellDate": "2025-03-20"
    }
    ```
-   **Response:**
    -   **200 OK:**
        ```json
        {
            "id": 2,
            "stockSymbol": "GOOGL",
            "buyPrice": 2800.00,
            "buyDate": "2025-02-10",
            "quantity": 5,
            "totalBuyValue": 14000.00,
            "sellPrice": 3000.00,
            "sellDate": "2025-03-20",
            "totalSellValue": 15000.00
        }
        ```
    -   **404 Not Found:** If the trade with the specified ID is not found.

### Delete a Trade

-   **Endpoint:** `/trades/{id}`
-   **Method:** `DELETE`
-   **Response:**
    -   **200 OK:** No content.
    -   **404 Not Found:** If the trade with the specified ID is not found.
