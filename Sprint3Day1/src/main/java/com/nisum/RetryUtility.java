package com.nisum;

import java.io.IOException;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.function.Predicate;

public class RetryUtility {
    
    private final int maxRetries;
    private final Duration initialDelay;
    private final double backoffMultiplier;
    private final Duration maxDelay;
    private final Predicate<Exception> retryCondition;
    
    public RetryUtility(int maxRetries, Duration initialDelay, double backoffMultiplier, 
                       Duration maxDelay, Predicate<Exception> retryCondition) {
        this.maxRetries = maxRetries;
        this.initialDelay = initialDelay;
        this.backoffMultiplier = backoffMultiplier;
        this.maxDelay = maxDelay;
        this.retryCondition = retryCondition;
    }
    
    public static RetryUtility defaultNetworkRetry() {
        return new RetryUtility(
            3,
            Duration.ofMillis(1000),
            2.0,
            Duration.ofSeconds(30),
            e -> e instanceof IOException || e instanceof RuntimeException
        );
    }
    
    public <T> T execute(Callable<T> operation) throws Exception {
        Exception lastException = null;
        Duration currentDelay = initialDelay;
        
        for (int attempt = 0; attempt <= maxRetries; attempt++) {
            try {
                System.out.println("Attempt " + (attempt + 1) + "/" + (maxRetries + 1));
                return operation.call();
            } catch (Exception e) {
                lastException = e;
                
                if (attempt == maxRetries || !retryCondition.test(e)) {
                    System.err.println("Max retries reached or non-retryable exception. Giving up.");
                    throw e;
                }
                
                System.err.println("Attempt " + (attempt + 1) + " failed: " + e.getMessage());
                System.out.println("Retrying in " + currentDelay.toMillis() + "ms...");
                
                try {
                    Thread.sleep(currentDelay.toMillis());
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Retry interrupted", ie);
                }
                
                // Calculate next delay with exponential backoff
                currentDelay = Duration.ofMillis(
                    Math.min((long)(currentDelay.toMillis() * backoffMultiplier), maxDelay.toMillis())
                );
            }
        }
        
        throw lastException;
    }
    
    // Simulate network service
    static class NetworkService {
        private static final Random random = new Random();
        private int callCount = 0;
        
        public String makeNetworkCall() throws IOException {
            callCount++;
            
            // Simulate network failures
            if (callCount <= 2 && random.nextBoolean()) {
                throw new IOException("Network timeout - attempt " + callCount);
            }
            
            return "Success after " + callCount + " attempts";
        }
    }
    
    public static void main(String[] args) {
        RetryUtility retry = RetryUtility.defaultNetworkRetry();
        NetworkService service = new NetworkService();
        
        try {
            String result = retry.execute(service::makeNetworkCall);
            System.out.println("Result: " + result);
        } catch (Exception e) {
            System.err.println("Final failure: " + e.getMessage());
        }
    }
}