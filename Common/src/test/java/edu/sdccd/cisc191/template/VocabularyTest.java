package edu.sdccd.cisc191.template;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VocabularyTest {
    private Vocabulary[] vocab;

    /**
     * constructs the vocab array and returns it in a variable
     */
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        ArrayBuilder.constructVocab(1);
        vocab = ArrayBuilder.getVocabs();
    }

    /**
     * tests the getMeaning() method in the Vocabulary class
     */
    @org.junit.jupiter.api.Test
    void getMeaning() {
        assertEquals(vocab[0].getMeaning(), "animal");
    }

    /**
     * tests the getWord() method in the Vocabulary class
     */
    @org.junit.jupiter.api.Test
    void getWord() {
        assertEquals(vocab[0].getWord(), "どうぶつ、動物 ");
    }
}