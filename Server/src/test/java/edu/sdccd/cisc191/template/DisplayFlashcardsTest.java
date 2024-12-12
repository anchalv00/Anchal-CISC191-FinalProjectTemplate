package edu.sdccd.cisc191.template;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DisplayFlashcardsTest {
    private DisplayFlashcards display;

    /**
     * creates an instance of the DisplayFlashcards class
     */
    @BeforeEach
    void setUp() {
        display = new DisplayFlashcards();
    }

    @Test
    void setWordCount() {
        display.setWordCount(3);
        assertEquals(display.getWordCount(), 3);
    }
}