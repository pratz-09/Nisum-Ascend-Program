// File: demo/src/main/java/com/nisum/demo/SpellChecker.java
package com.nisum.demo;

import org.springframework.stereotype.Component;

@Component
public class SpellChecker {
    public String checkSpelling() {
        return "Spell checking performed.";
    }
}