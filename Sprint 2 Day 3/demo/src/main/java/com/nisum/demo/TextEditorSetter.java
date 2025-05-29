// File: demo/src/main/java/com/nisum/demo/TextEditorSetter.java
package com.nisum.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TextEditorSetter {
    private SpellChecker spellChecker;

    @Autowired
    public void setSpellChecker(SpellChecker spellChecker) {
        this.spellChecker = spellChecker;
    }

    @GetMapping("/editor/setter")
    public String check() {
        return spellChecker.checkSpelling() + " (Setter Injection)";
    }
}