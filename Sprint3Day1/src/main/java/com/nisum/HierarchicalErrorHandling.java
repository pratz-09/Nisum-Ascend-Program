package com.nisum;

// Custom exceptions for different layers
class DataLayerException extends RuntimeException {
    public DataLayerException(String message, Throwable cause) {
        super(message, cause);
    }
}

class ServiceLayerException extends RuntimeException {
    public ServiceLayerException(String message, Throwable cause) {
        super(message, cause);
    }
}

class ControllerLayerException extends RuntimeException {
    public ControllerLayerException(String message, Throwable cause) {
        super(message, cause);
    }
}

// Data Access Layer
class UserRepository {
    public User findById(Long id) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("User ID cannot be null");
            }
            if (id <= 0) {
                throw new RuntimeException("Database connection failed");
            }
            if (id == 999) {
                return null; // User not found
            }
            return new User("User " + id, "user" + id + "@example.com", 25);
        } catch (Exception e) {
            throw new DataLayerException("Failed to retrieve user with ID: " + id, e);
        }
    }
    
    public void save(User user) {
        try {
            if (user.name() == null || user.name().trim().isEmpty()) {
                throw new IllegalArgumentException("User name cannot be empty");
            }
            System.out.println("User saved: " + user);
        } catch (Exception e) {
            throw new DataLayerException("Failed to save user: " + user, e);
        }
    }
}

// Service Layer
class UserService {
    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public User getUserById(Long id) {
        try {
            User user = userRepository.findById(id);
            if (user == null) {
                throw new RuntimeException("User not found with ID: " + id);
            }
            return user;
        } catch (DataLayerException e) {
            throw new ServiceLayerException("Service failed to get user with ID: " + id, e);
        } catch (Exception e) {
            throw new ServiceLayerException("Unexpected error in user service for ID: " + id, e);
        }
    }
    
    public void createUser(String name, String email, int age) {
        try {
            if (age < 18) {
                throw new RuntimeException("User must be at least 18 years old");
            }
            User user = new User(name, email, age);
            userRepository.save(user);
        } catch (DataLayerException e) {
            throw new ServiceLayerException("Service failed to create user: " + name, e);
        } catch (Exception e) {
            throw new ServiceLayerException("Unexpected error creating user: " + name, e);
        }
    }
}

// Controller Layer
class UserController {
    private final UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    public void handleGetUser(String idStr) {
        try {
            Long id = Long.parseLong(idStr);
            User user = userService.getUserById(id);
            System.out.println("Retrieved user: " + user);
        } catch (NumberFormatException e) {
            throw new ControllerLayerException("Invalid user ID format: " + idStr, e);
        } catch (ServiceLayerException e) {
            throw new ControllerLayerException("Controller failed to handle get user request", e);
        } catch (Exception e) {
            throw new ControllerLayerException("Unexpected controller error for user ID: " + idStr, e);
        }
    }
    
    public void handleCreateUser(String name, String email, String ageStr) {
        try {
            int age = Integer.parseInt(ageStr);
            userService.createUser(name, email, age);
            System.out.println("User created successfully");
        } catch (NumberFormatException e) {
            throw new ControllerLayerException("Invalid age format: " + ageStr, e);
        } catch (ServiceLayerException e) {
            throw new ControllerLayerException("Controller failed to handle create user request", e);
        } catch (Exception e) {
            throw new ControllerLayerException("Unexpected controller error creating user", e);
        }
    }
}

// Application Layer with Global Error Handler
public class HierarchicalErrorHandling {
    
    public static void handleGlobalException(Exception e) {
        System.err.println("\n=== GLOBAL EXCEPTION HANDLER ===");
        
        // Traverse the exception chain
        Throwable current = e;
        int level = 0;
        
        while (current != null) {
            String indent = "  ".repeat(level);
            String exceptionType = current.getClass().getSimpleName();
            String message = current.getMessage();
            
            System.err.println(indent + "Level " + level + " (" + exceptionType + "): " + message);
            
            current = current.getCause();
            level++;
        }
        
        // Determine error handling strategy based on exception type
        if (e instanceof ControllerLayerException) {
            System.err.println("Returning HTTP 400 Bad Request");
        } else if (e instanceof ServiceLayerException) {
            System.err.println("Returning HTTP 500 Internal Server Error");
        } else if (e instanceof DataLayerException) {
            System.err.println("Returning HTTP 503 Service Unavailable");
        } else {
            System.err.println("Returning HTTP 500 Generic Error");
        }
        
        System.err.println("================================\n");
    }
    
    public static void main(String[] args) {
        UserRepository repository = new UserRepository();
        UserService service = new UserService(repository);
        UserController controller = new UserController(service);
        
        // Test cases that will cause exceptions at different layers
        String[] testCases = {
            "1",      // Valid case
            "0",      // Data layer exception (invalid ID)
            "999",    // Service layer exception (user not found)
            "abc",    // Controller layer exception (invalid format)
            null      // Controller layer exception (null input)
        };
        
        for (String testCase : testCases) {
            try {
                System.out.println("Testing getUserById with ID: " + testCase);
                controller.handleGetUser(testCase);
            } catch (Exception e) {
                handleGlobalException(e);
            }
        }
        
        // Test create user scenarios
        try {
            System.out.println("Testing createUser with underage user");
            controller.handleCreateUser("John", "john@example.com", "16");
        } catch (Exception e) {
            handleGlobalException(e);
        }
        
        try {
            System.out.println("Testing createUser with empty name");
            controller.handleCreateUser("", "test@example.com", "25");
        } catch (Exception e) {
            handleGlobalException(e);
        }
    }
}