# Portfolio API

(Replace with your logo)

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Architecture](#architecture)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)
  - [Authentication](#authentication)
  - [Endpoints](#endpoints)
    - [User Controller](#user-controller)
    - [Portfolio Controller](#portfolio-controller)
    - [Asset Controller](#asset-controller)
    - [Transaction Controller](#transaction-controller)
    - [Home Controller](#home-controller)
- [Database](#database)
  - [H2 Database Console](#h2-database-console)
  - [Data Seeding](#data-seeding)
    - [Admin User](#admin-user)
    - [Random Users](#random-users)
- [Security](#security)
- [Testing](#testing)
  - [Unit Testing](#unit-testing)
  - [Integration Testing](#integration-testing)
  - [Manual Testing](#manual-testing)
- [Streamlit Integration](#streamlit-integration)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Overview
The Portfolio API is a robust backend service built with Spring Boot that enables users to manage their investment portfolios efficiently. It offers functionalities for user registration, authentication, and comprehensive management of portfolios, assets, and transactions. The API leverages JWT (JSON Web Token) for secure authentication and integrates seamlessly with a Streamlit frontend for an interactive user experience.

## Features
- User Management
  - User Registration and Authentication
  - Secure Password Storage with BCrypt
  - Admin User Creation
- Portfolio Management
  - Create, Read, Update, Delete (CRUD) Portfolios
  - Associate Portfolios with Users
- Asset Management
  - CRUD Operations for Assets within Portfolios
  - Track Asset Details like Symbol, Type, Quantity, Prices
- Transaction Management
  - Record Buy/Sell Transactions for Assets
  - Retrieve Transaction Histories
- Advanced Functionalities
  - Health Check Endpoint for Monitoring
  - User Count Retrieval
  - Advanced SQL Query Execution for Data Analysis
- API Documentation
  - Comprehensive Swagger UI Integration
- Educational Resources
  - Object-Oriented Programming (OOP) Concepts Explanation

## Technologies Used
### Backend
- Java 17
- Spring Boot 3.x
- Spring Data JPA
- Spring Security
- Hibernate
- H2 In-Memory Database
- Faker (for data seeding)

### Frontend
- Streamlit (for user interface)

### Others
- Maven (Build Tool)
- Swagger UI (API Documentation)

## Architecture
(Replace with your architecture diagram)

The Portfolio API follows a RESTful architectural style, organizing resources into distinct entities: Users, Portfolios, Assets, and Transactions. Each entity is managed via its respective controller, repository, and service layer, ensuring a clean separation of concerns and scalability.

## Getting Started
### Prerequisites
Ensure you have the following installed on your machine:
- Java Development Kit (JDK) 17 or higher
  - Download JDK
- Maven 3.6.x or higher
  - Download Maven
- Git
  - Download Git
- (Optional) IDE
  - IntelliJ IDEA, Eclipse, or any preferred Java IDE.

### Installation
1. Clone the Repository
   ```bash
   git clone https://github.com/yourusername/portfolio-api.git
   cd portfolio-api
   ```
2. Build the Project
   ```bash
   mvn clean install
   ```
   This command compiles the code, runs tests, and packages the application.

### Running the Application
1. Run with Maven
   ```bash
   mvn spring-boot:run
   ```
2. Access the Application
   - API Endpoints: `http://localhost:8080/api/`
   - Swagger UI (API Documentation): `http://localhost:8080/swagger-ui/index.html`
   - H2 Database Console: `http://localhost:8080/h2-console` (Use JDBC URL from `application.properties`)

## API Documentation
### Authentication
#### Login
- Endpoint: `POST /api/auth/login`
- Description: Authenticates a user and returns a JWT token.
- Request Body:
  ```json
  {
    "email": "user@example.com",
    "password": "password123"
  }
  ```
- Response:
  ```json
  {
    "token": "jwt-token-string"
  }
```
- Usage: Include the returned token in the `Authorization` header for subsequent requests:
  ```
  Authorization: Bearer <jwt-token-string>
  ```

### Endpoints
#### User Controller
- **Create User**: `POST /api/users`
- **Get User by ID**: `GET /api/users/{id}`
- **Update User**: `PUT /api/users/{id}`
- **Delete User**: `DELETE /api/users/{id}`
- **Get All Users**: `GET /api/users`
- **Get User by Email**: `GET /api/users/email/{email}`
- **Get User Count**: `GET /api/users/count`
- **Get User by Account Number**: `GET /api/users/account/{accountNumber}`

#### Portfolio Controller
- **Create Portfolio**: `POST /api/portfolios`
- **Get Portfolio by ID**: `GET /api/portfolios/{id}`
- **Update Portfolio**: `PUT /api/portfolios/{id}`
- **Delete Portfolio**: `DELETE /api/portfolios/{id}`
- **Get Portfolios by User ID**: `GET /api/portfolios/user/{userId}`

#### Asset Controller
- **Create Asset**: `POST /api/assets`
- **Get Asset by ID**: `GET /api/assets/{id}`
- **Update Asset**: `PUT /api/assets/{id}`
- **Delete Asset**: `DELETE /api/assets/{id}`
- **Get Assets by Portfolio ID**: `GET /api/assets/portfolio/{portfolioId}`

#### Transaction Controller
- **Create Transaction**: `POST /api/transactions`
- **Get Transaction by ID**: `GET /api/transactions/{id}`
- **Update Transaction**: `PUT /api/transactions/{id}`
- **Delete Transaction**: `DELETE /api/transactions/{id}`
- **Get Transactions by Asset ID**: `GET /api/transactions/asset/{assetId}`

#### Home Controller
- **Home Endpoint**: `GET /api/`
- **Health Check**: `GET /api/health`

## Database
The Portfolio API uses an H2 in-memory database for development and testing purposes. This allows for rapid development without the need for external database configurations. For production environments, it's recommended to switch to a more robust database system like PostgreSQL, MySQL, or Oracle.

### H2 Database Console
- Access URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb` (Ensure this matches your `application.properties` configuration)
- Username: `sa`
- Password: (Leave blank or as configured)

Note: The H2 console is enabled for development and should be disabled or secured in production environments.

### Data Seeding
Upon application startup, the `PortfolioApiApplication` seeds the database with initial data to facilitate testing and development.

#### Admin User
- Purpose: Provides an initial user with administrative privileges.
- Credentials:
  - Email: `admin@test.com`
  - Password: `admin123`
- Details:
  - An admin user is created with associated portfolio, asset, and transaction.
  - Ensures that there's at least one user to interact with the API.

#### Random Users
- Purpose: Populates the database with 100 random users, each with their own portfolios, assets, and transactions.
- Details:
  - Utilizes the Faker library to generate realistic fake data.
  - Each user is assigned:
    - A unique account number.
    - One portfolio with a random name and type.
    - One asset within the portfolio with random stock symbols, types, quantities, purchase prices, and current prices.
    - One transaction associated with the asset.
- Benefits:
  - Provides a substantial dataset for testing functionalities like fetching users, portfolios, assets, and transactions.
  - Helps in performance testing and ensuring the API scales with data volume.

## Security
### JWT (JSON Web Token) Authentication
The Portfolio API employs JWT for securing its endpoints. This ensures that only authenticated users can access and manipulate their data.

Authentication Process:
1. Login: Users send their email and password to the `/api/auth/login` endpoint.
2. Token Issuance: Upon successful authentication, the API issues a JWT token.
3. Authorization: Users include this token in the `Authorization` header (`Bearer <token>`) for subsequent API requests.
4. Token Validation: The API validates the token to ensure the request is from an authenticated user.

Benefits:
- Stateless: No need to maintain session information on the server.
- Scalable: Suitable for distributed systems.
- Secure: Tokens are signed and can be validated for integrity.

### Password Security
- BCrypt Password Encoding:
  - All user passwords are hashed using BCrypt before storage.
  - Enhances security by ensuring that plain-text passwords are never stored in the database.
- Implementation:
  ```java
  @Bean
  public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }
  ```

### CORS Configuration
- Purpose: Allows or restricts resources to be requested from different domains.
- Configuration:
  ```java
  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
      CorsConfiguration configuration = new CorsConfiguration();
      configuration.addAllowedOrigin("http://localhost:8501"); // Streamlit app origin
      configuration.addAllowedMethod("*");
      configuration.addAllowedHeader("*");
      configuration.setAllowCredentials(true);
      configuration.setMaxAge(3600L);

      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", configuration);

      return source;
  }
  ```
  Note: Ensure that the origin matches where your frontend (Streamlit app) is running.

## Testing
### Unit Testing
Purpose: Validate individual components (services, controllers, repositories) to ensure they function as intended.
Frameworks:
- JUnit 5: For writing and running tests.
- Mockito: For mocking dependencies.

Example Test Case:
```java
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    public void testCreateUser() throws Exception {
        UserDto userDto = new UserDto("John Doe", "john.doe@example.com", "1234567890", "1990-01-01", "555-1234", "123 Main St", "password123");
        User user = new User();
        user.setId(1L);
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        // ... set other fields

        Mockito.when(userService.registerUser(Mockito.any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }
}
```

### Integration Testing
Purpose: Test the interaction between different components and ensure the entire system works cohesively.
Approach: Use Spring Boot Test with Testcontainers to spin up an actual database and test API endpoints.

Example Integration Test:
```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class UserIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:13")
            .withDatabaseName("testdb")
            .withUsername("user")
            .withPassword("password");

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testUserCreation() {
        UserDto userDto = new UserDto("Jane Doe", "jane.doe@example.com", "0987654321", "1992-02-02", "555-5678", "456 Elm St", "password123");
        ResponseEntity<User> response = restTemplate.postForEntity("/api/users", userDto, User.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody().getId());
        assertEquals("jane.doe@example.com", response.getBody().getEmail());
    }
}
```

### Manual Testing
- **Swagger UI**: Utilize the integrated Swagger UI to manually test API endpoints.
- **Postman**: Use Postman for more advanced testing scenarios, including authentication flows.
- **cURL**: Execute API requests via the command line for quick checks.

## Streamlit Integration
The Portfolio API integrates with a Streamlit frontend to provide a user-friendly interface for interacting with the API.

### Features
- Authentication: Users can log in using their email and password to access protected features.
- Data Visualization: Display users, portfolios, assets, and transactions in an organized manner.
- Advanced Queries: Execute custom SQL queries for in-depth data analysis.
- Educational Resources: Access explanations of Object-Oriented Programming (OOP) concepts.

### Running the Streamlit App
1. Navigate to the Frontend Directory
   ```bash
   cd portfolio-frontend
   ```
2. Install Dependencies
   ```bash
   pip install -r requirements.txt
   ```
3. Run the Streamlit App
   ```bash
   streamlit run app.py
   ```
4. Access the Application
   - Open your browser and navigate to `http://localhost:8501`

### Streamlit Features
- Login/Logout Functionality: Secure access to user-specific data.
- Tabs for Different Data Entities: Organized navigation for Users, Portfolios, Assets, Transactions, etc.
- Advanced SQL Query Interface: Allows users to perform complex queries on their data.
- OOP Education: Provides learning materials on Object-Oriented Programming principles.
- API Documentation Access: Direct access to Swagger UI for testing and exploration.

## Contributing
Contributions are welcome! Please follow these steps to contribute to the Portfolio API:

1. Fork the Repository
2. Clone the Forked Repository
   ```bash
   git clone https://github.com/yourusername/portfolio-api.git
   cd portfolio-api
   ```
3. Create a New Branch
   ```bash
   git checkout -b feature/YourFeatureName
   ```
4. Make Your Changes
5. Commit Your Changes
   ```bash
   git commit -m "Add feature: YourFeatureName"
   ```
6. Push to the Branch
   ```bash
   git push origin feature/YourFeatureName
   ```
7. Create a Pull Request
   - Navigate to the original repository and click on "Compare & pull request."
   - Describe Your Changes
   - Submit the Pull Request

Note: Ensure that your code adheres to the project's coding standards and includes appropriate tests.

## License
Distributed under the MIT License.
