package com.nisum.demo;

public class TextEditor {
    private SpellChecker spellChecker;
    private String editorName;

    // Constructor Injection
    public TextEditor(SpellChecker spellChecker) {
        this.spellChecker = spellChecker;
    }

    // Setter Injection
    public void setEditorName(String editorName) {
        this.editorName = editorName;
    }

    public void spellCheck() {
        System.out.println(spellChecker.checkSpelling() + " in " + editorName);
    }
}