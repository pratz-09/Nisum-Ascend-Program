package com.nisum;

public class BasicExceptionHandlingDemo {
    
    public static int divide(int dividend, int divisor) {
        if (divisor == 0) {
            throw new ArithmeticException("Division by zero is not allowed");
        }
        return dividend / divisor;
    }
    
    public static void main(String[] args) {
        int[] dividends = {10, 15, 20, 8};
        int[] divisors = {2, 3, 0, 4};
        
        for (int i = 0; i < dividends.length; i++) {
            try {
                int result = divide(dividends[i], divisors[i]);
                System.out.println(dividends[i] + " / " + divisors[i] + " = " + result);
            } catch (ArithmeticException e) {
                System.err.println("Error: " + e.getMessage() + 
                    " (attempting to divide " + dividends[i] + " by " + divisors[i] + ")");
            }
        }
    }
}