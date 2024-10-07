package edu.sdccd.cisc191.template;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DisplayFlashcardsTest {
    private DisplayFlashcards display;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        display = new DisplayFlashcards();
    }

    @org.junit.jupiter.api.Test
    void setWordCount() {
        display.setWordCount(3);
        assertEquals(display.getWordCount(), 3);
    }
}