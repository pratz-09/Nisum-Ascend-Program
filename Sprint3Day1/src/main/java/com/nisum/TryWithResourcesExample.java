package com.nisum;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TryWithResourcesExample {
    
    public static void writeToFile(String filename, String content) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename))) {
            writer.write(content);
            System.out.println("Successfully wrote to file: " + filename);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    
    public static void readFromFile(String filename) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Read: " + line);
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        String filename = "test.txt";
        String content = "Hello, World!\nThis is a test file.";
        
        writeToFile(filename, content);
        readFromFile(filename);
    }
}