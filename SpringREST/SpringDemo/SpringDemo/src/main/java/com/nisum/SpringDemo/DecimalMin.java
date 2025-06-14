package com.nisum.SpringDemo;

public @interface DecimalMin {
    String message();

    String value();

    boolean inclusive();
}
