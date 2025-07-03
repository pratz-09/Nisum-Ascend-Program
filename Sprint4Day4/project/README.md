# Spring Boot Testing Assignments

This project contains four comprehensive testing assignments demonstrating various testing strategies and frameworks in Spring Boot applications.

## Project Structure

```
src/
├── main/java/com/example/
│   ├── TestingAssignmentsApplication.java
│   ├── assignment1/          # JUnit 5 + Mockito Service Layer Tests
│   ├── assignment2/          # JUnit 4 + Legacy Spy Tests
│   ├── assignment3/          # MockMvc + Spring REST Docs Controller Tests
│   └── assignment4/          # Integration Tests with Context Isolation
└── test/java/com/example/
    ├── assignment1/          # UserServiceTest
    ├── assignment2/          # PriceFacadeTest
    ├── assignment3/          # BookControllerTest
    └── assignment4/          # OrderControllerIntegrationTest + SlicedTest
```

## Assignment Overview

### Assignment 1: JUnit 5 Service Layer Testing
- **Location**: `src/test/java/com/example/assignment1/UserServiceTest.java`
- **Features**:
  - Uses `@ExtendWith(MockitoExtension.class)`
  - Mocks `UserRepository` and `EmailSender` with `@Mock`
  - Injects dependencies with `@InjectMocks`
  - Demonstrates stubbing with `thenReturn(null, user)` for sequential calls
  - Tests exception handling and fallback method invocation
  - Uses `ArgumentCaptor` to verify email subject lines

### Assignment 2: JUnit 4 Legacy Spy Testing
- **Location**: `src/test/java/com/example/assignment2/PriceFacadeTest.java`
- **Features**:
  - Uses `@RunWith(MockitoJUnitRunner.class)`
  - Demonstrates `@Spy` annotation for partial mocking
  - Tests exception handling with `doThrow()` and `doReturn()`
  - Shows spy reset functionality
  - Compares mocked vs real method invocations

### Assignment 3: MockMvc Controller Testing
- **Location**: `src/test/java/com/example/assignment3/BookControllerTest.java`
- **Features**:
  - Uses `@WebMvcTest(controllers = BookController.class)`
  - Mocks service layer with `@MockBean`
  - Tests REST API endpoints (GET, POST)
  - Handles 200 OK and 404 Not Found responses
  - Integrates Spring REST Docs for API documentation
  - Validates JSON responses and request payloads

### Assignment 4: Integration Testing with Context Isolation
- **Location**: `src/test/java/com/example/assignment4/OrderControllerIntegrationTest.java`
- **Features**:
  - Uses `@SpringBootTest` with full application context
  - Selectively mocks only `PaymentGatewayClient` with `@MockBean`
  - Tests real HTTP calls with `TestRestTemplate`
  - Verifies integration between real beans (OrderService, OrderRepository)
  - Demonstrates context isolation principles

## Key Technologies Used

- **Spring Boot 3.2.0**
- **JUnit 5** (Jupiter) for modern testing
- **JUnit 4** for legacy compatibility
- **Mockito** for mocking and spying
- **Spring Test** for integration testing
- **Spring REST Docs** for API documentation
- **H2 Database** for in-memory testing
- **TestRestTemplate** for HTTP client testing

## Running the Tests

### Prerequisites
- Java 17+
- Maven 3.6+

### Run All Tests
```bash
mvn test
```

### Run Specific Assignment Tests
```bash
# Assignment 1 - JUnit 5 Service Tests
mvn test -Dtest=UserServiceTest

# Assignment 2 - JUnit 4 Legacy Spy Tests
mvn test -Dtest=PriceFacadeTest

# Assignment 3 - MockMvc Controller Tests
mvn test -Dtest=BookControllerTest

# Assignment 4 - Integration Tests
mvn test -Dtest=OrderControllerIntegrationTest
```

### Run the Application
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Endpoints

### Books API
- `GET /api/books` - Get all books
- `GET /api/books/{id}` - Get book by ID
- `POST /api/books` - Create new book
- `PUT /api/books/{id}` - Update book
- `DELETE /api/books/{id}` - Delete book
- `GET /api/books/author/{author}` - Get books by author

### Orders API
- `GET /orders` - Get all orders
- `GET /orders/{id}` - Get order by ID
- `POST /orders` - Create new order
- `GET /orders/customer/{customerName}` - Get orders by customer

## Testing Strategy Comparison

### Startup Time Comparison

**Full Integration Test** (`@SpringBootTest`):
- Loads complete Spring application context
- Initializes web server, database, and all beans
- Slower startup (~3-5 seconds)
- Suitable for end-to-end testing

**Sliced Test** (`@DataJpaTest`):
- Loads only JPA-related components
- Uses in-memory database only
- Faster startup (~1-2 seconds)
- Suitable for repository layer testing

### When to Use Each Approach

**Use `@SpringBootTest` when**:
- Testing complete user workflows
- Verifying integration between multiple layers
- Testing HTTP endpoints with real network calls
- Validating configuration and bean wiring

**Use `@WebMvcTest` when**:
- Testing controller layer in isolation
- Validating request/response handling
- Testing REST API contracts
- Generating API documentation

**Use `@DataJpaTest` when**:
- Testing JPA repositories
- Validating database queries
- Testing entity relationships
- Verifying database constraints

## Test Coverage

The project demonstrates comprehensive testing coverage including:

1. **Unit Tests**: Testing individual components in isolation
2. **Integration Tests**: Testing component interactions
3. **Slice Tests**: Testing specific application layers
4. **Mock vs Real**: Comparing mocked and real implementations
5. **Error Handling**: Testing exception scenarios
6. **Argument Verification**: Validating method calls and parameters

## Learning Objectives

By studying this project, you will learn:

1. How to write effective unit tests with JUnit 5 and Mockito
2. Legacy testing approaches with JUnit 4
3. Spring Boot testing strategies and annotations
4. Integration testing with selective mocking
5. API testing with MockMvc and REST Docs
6. Test slice selection for performance optimization
7. Argument capturing and verification techniques
8. Exception handling in test scenarios

## Additional Resources

- [Spring Boot Testing Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html)
- [Mockito Documentation](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html)
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
- [Spring REST Docs](https://docs.spring.io/spring-restdocs/docs/current/reference/html5/)

## Notes for IntelliJ IDEA

This project is configured for IntelliJ IDEA with:
- Maven integration
- JUnit test runner configuration
- Spring Boot run configurations
- H2 database console access at `/h2-console`

Import the project as a Maven project and run tests using the built-in test runner.