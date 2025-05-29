// File: demo/src/main/java/com/nisum/demo/TextEditorConstructor.java
package com.nisum.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TextEditorConstructor {
    private final SpellChecker spellChecker;

    public TextEditorConstructor(SpellChecker spellChecker) {
        this.spellChecker = spellChecker;
    }

    @GetMapping("/editor/constructor")
    public String check() {
        return spellChecker.checkSpelling() + " (Constructor Injection)";
    }
}