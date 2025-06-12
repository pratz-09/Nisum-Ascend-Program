package com.nisum;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MultipleExceptionHandling {
    
    public static List<Integer> readIntegersFromFile(String filename) {
        List<Integer> numbers = new ArrayList<>();
        
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    numbers.add(Integer.parseInt(line));
                }
            }
        } catch (IOException | NumberFormatException | SecurityException e) {
            System.err.println("Error processing file '" + filename + "': " + 
                e.getClass().getSimpleName() + " - " + e.getMessage());
            throw new RuntimeException("Failed to read integers from file", e);
        }
        
        return numbers;
    }
    
    public static void createTestFile() {
        try (PrintWriter writer = new PrintWriter("numbers.txt")) {
            writer.println("10");
            writer.println("20");
            writer.println("invalid");
            writer.println("30");
        } catch (IOException e) {
            System.err.println("Error creating test file: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        createTestFile();
        
        try {
            List<Integer> numbers = readIntegersFromFile("numbers.txt");
            System.out.println("Successfully read numbers: " + numbers);
        } catch (RuntimeException e) {
            System.err.println("Failed to process file: " + e.getMessage());
        }
        
        try {
            List<Integer> numbers = readIntegersFromFile("nonexistent.txt");
            System.out.println("This won't be printed");
        } catch (RuntimeException e) {
            System.err.println("Failed to process non-existent file: " + e.getMessage());
        }
    }
}