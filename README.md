# payday-trade

## Phase 1

In this phase, we will create User and Role entities. We'll define a Role enum for user roles. User registration and login will be implemented using email, along with DTOs and validation (password must be at least 6 characters long). We will also utilize MapStruct for mapping.

### Requirements
- Java version 8 or higher
- Spring Boot
- Maven or Gradle as your build tool

### Steps
1. Define User and Role entities.
2. Create DTOs for registration and user information.
3. Implement validation, ensuring that the password is at least 6 characters long.
4. Utilize MapStruct to map entities to DTOs and vice versa.
5. Implement User registration and login endpoints.
6. Implement constant messages for responses.
7. Implement a registration endpoint that requires user email, password, and other necessary information.
8. Validate user input, especially email format and password strength.

## Phase 2

In this phase, we'll create repositories, services, and controllers for the User entity. Additionally, we will implement an email service to handle email notifications.

### Requirements
- Completed Phase 1
- Spring Data JPA for repository management

### Steps
1. Create repositories for User and Role entities.
2. Implement UserService to handle user-related business logic.
3. Implement UserController to handle user endpoints.
4. Create an EmailService to send email notifications.
5. Integrate email notifications with user registration and other relevant processes.

## Phase 3

In this phase, we will implement the stock management system and place orders using a Feign client.

### Requirements
- Completed Phase 2
- Feign Client for external API communication

### The description main problem of phase 
### Place an order
User selects 2 symbols and chooses the corresponding quantity and target price. in this
The system tracks the latest market price.
A user creates a purchase order:
The user says he wants to buy 100 shares of TSLA stock and has a target price of $200.
The user creates a sales order:
A user states that he wants to sell 50 shares of AAPL stock and has a target price of $200.
The system constantly monitors the current market prices of TSLA and AAPL stocks.
When the TSLA stock price is $200 or less, the system automatically executes a buy order:
For example, if the TSLA stock price falls below $200 (eg $195), the system buys 100 TSLA shares at the user-specified price ($200).
After the purchase is made, the system reduces the user's account balance by the total value of the purchased shares (for example, $195 x 100 units).
When the AAPL stock price is $200 or higher, the system automatically executes a sell order:
For example, if the price of AAPL stock rises above $200 (say $205), the system sells 50 AAPL shares at the user-specified price ($200).
After the sale is made, the system increases the user's account balance by the total sale price of the sold shares (for example, $205 x 50 units).
The system updates the account balance after each purchase/sale transaction:
The cash balance decreases during the purchase order.
At the time of the sales order, the cash balance increases.
Email notification comes during purchase and sale

### Steps
1. Implement Feign clients to communicate with external stock data APIs.
2. Create endpoints to display stock information.
3. Implement the ability for users to place buy and sell orders.
4. Users can select a stock symbol, quantity, and target price.
5. The system monitors stock prices and executes orders when conditions are met.

## Phase 4

In this phase, we will implement the order execution logic and account balance management.

### Requirements
- Completed Phase 3

### Steps
1. Implement the order execution logic for buy and sell orders.
2. Update user account balance after successful buy and sell orders.
3. Update account balance during buy and sell order creation.
4. Implement email notifications for successful buy and sell orders.

## Phase 5

In this phase, we will focus on security, including JWT authentication and Liquibase for database management.

### Requirements
- Completed Phase 4
- Spring Security for JWT authentication
- Liquibase for database versioning and management

### Steps
1. Implement JWT authentication and authorization for user endpoints.
2. Secure sensitive endpoints using Spring Security.
3. Set up Liquibase for database version control and schema management.

## Phase 6

Testing and Finalization

### Requirements
- Completed Phase 5

### Steps
1. Write unit tests for different components of the application.
2. Perform integration testing to ensure the entire system works as expected.
3. Fine-tune the application and fix any bugs or issues.
4. Update the README with setup instructions and usage guidelines.
5. Push the code to a version control repository, such as GitHub.

Feel free to refer to the respective subdirectories for each phase's detailed implementation and code examples.

### Jwt and security imgage
![image](https://github.com/Elchin-Huseynli/payday-trade/assets/116680886/0d99b4f0-251e-4ad3-adb1-c6bf30ea30d5)
