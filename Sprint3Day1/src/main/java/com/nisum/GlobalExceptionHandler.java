package com.nisum;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GlobalExceptionHandler {
    
    private static final String LOG_FILE = "error.log";
    
    static {
        // Set up global exception handler
        Thread.setDefaultUncaughtExceptionHandler((thread, exception) -> {
            logException(exception, thread.getName());
        });
    }
    
    private static void logException(Throwable exception, String threadName) {
        try (FileWriter writer = new FileWriter(LOG_FILE, true);
             PrintWriter printWriter = new PrintWriter(writer)) {
            
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            printWriter.println("=== UNCAUGHT EXCEPTION ===");
            printWriter.println("Timestamp: " + timestamp);
            printWriter.println("Thread: " + threadName);
            printWriter.println("Exception: " + exception.getClass().getName());
            printWriter.println("Message: " + exception.getMessage());
            printWriter.println("Stack Trace:");
            exception.printStackTrace(printWriter);
            printWriter.println("========================\n");
            
            System.err.println("Uncaught exception logged to " + LOG_FILE);
            
        } catch (IOException e) {
            System.err.println("Failed to log exception: " + e.getMessage());
        }
    }
    
    public static void simulateException() {
        throw new RuntimeException("This is a simulated uncaught exception");
    }
    
    public static void main(String[] args) {
        System.out.println("Application started. Global exception handler is active.");
        
        // Create a thread that will throw an uncaught exception
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
                simulateException();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "ExceptionThread");
        
        thread.start();
        
        try {
            thread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("Main thread completed.");
    }
}