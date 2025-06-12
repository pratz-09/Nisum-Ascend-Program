package com.nisum;

import java.io.*;
import java.util.function.Supplier;
import java.util.function.Function;

public class ExceptionWrapper {
    
    @FunctionalInterface
    public interface CheckedSupplier<T> {
        T get() throws IOException;
    }
    
    @FunctionalInterface
    public interface CheckedFunction<T, R> {
        R apply(T t) throws IOException;
    }
    
    public static <T> T wrapIOException(CheckedSupplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
    
    public static <T, R> Function<T, R> wrapIOException(CheckedFunction<T, R> function) {
        return t -> {
            try {
                return function.apply(t);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        };
    }
    
    // Legacy API simulation
    public static String readLegacyFile(String filename) throws IOException {
        if (filename.equals("nonexistent.txt")) {
            throw new IOException("File not found: " + filename);
        }
        return "Content of " + filename;
    }
    
    public static void main(String[] args) {
        // Using the wrapper
        try {
            String content = wrapIOException(() -> readLegacyFile("test.txt"));
            System.out.println("Read: " + content);
            
            // This will throw UncheckedIOException
            String failContent = wrapIOException(() -> readLegacyFile("nonexistent.txt"));
            System.out.println("This won't be printed");
            
        } catch (UncheckedIOException e) {
            System.err.println("Wrapped IOException: " + e.getCause().getMessage());
        }
    }
}