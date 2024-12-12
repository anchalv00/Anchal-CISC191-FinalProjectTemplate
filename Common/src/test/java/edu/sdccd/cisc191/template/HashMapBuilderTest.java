package edu.sdccd.cisc191.template;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HashMapBuilderTest {
    private static ArrayList<UUID> keysList;
    private static HashMap<UUID, Vocabulary> map;
    private static Vocabulary[] vocabList;

    /**
     * initializes the HashMap, ArrayList, and Array in the HashMapBuilder
     * class so that the data being accessed in all the test methods
     * are consistent
     */
    @BeforeAll
    public static void setUp() {
        HashMapBuilder.buildMap(4);
        map = HashMapBuilder.getMap();
        vocabList = HashMapBuilder.getVocabsList();
        keysList = HashMapBuilder.getKeyList();
    }

    /**
     * tests the buildMap() method by checking the length
     * of the vocabulary array, which was initialized in that method
     */
    @Test
    void testBuildMap() {
        assertEquals(12, vocabList.length);
    }

    /**
     * tests the getKeyByVocab() method by checking if the key stored
     * in the set matches the actual key of the first vocab
     */
    @Test
    void testGetKeyByVocab() {
        //set will contain the key of the vocab element provided in the parameter
        Set<UUID> set = HashMapBuilder.getKeyByVocab(map, vocabList[0]);
        UUID element = UUID.randomUUID();

        //retrieves the key in the set
        for (UUID key : set) {
            element = key;
        }

        assertEquals(keysList.get(0), element);
    }

    /**
     * tests the getVocabByKey() method by checking if the first
     * key in the keysList Arraylist matches with the first vocab word
     * in the vocabsList array
     */
    @Test
    void testGetVocabByKey() {
        UUID element = keysList.get(0);

        assertEquals(vocabList[0], HashMapBuilder.getVocabByKey(element));
    }

    /**
     * tests the increaseFrequency() method by adding the first key in the
     * keysList ArrayList again at the end of the list and checks if
     * the new size of the list is correct
     * Removes the added key to return to the original list
     */
    @Test
    void testIncreaseFrequency() {
        HashMapBuilder.increaseFrequency(keysList.get(0));

        assertEquals(13, keysList.size());

        HashMapBuilder.decreaseFrequency(keysList.get(0));

        assertEquals(12, keysList.size());
    }

    /**
     * tests the decreaseFrequency() method by adding the first key in the
     * keysList ArrayList again at the end of the list (twice) and then
     * removes one key from the end and checks if the new size of the
     * list is correct
     * Removes one more key to return to the original list
     */
    @Test
    void testDecreaseFrequency() {
        HashMapBuilder.increaseFrequency(keysList.get(0));
        HashMapBuilder.increaseFrequency(keysList.get(0));

        HashMapBuilder.decreaseFrequency(keysList.get(0));

        assertEquals(13, keysList.size());

        HashMapBuilder.decreaseFrequency(keysList.get(0));

        assertEquals(12, keysList.size());
    }

    /**
     * tests the chooseRandomKey() method by choosing a random key
     * from the keysList ArrayList and checking if that key
     * is present in the actual list
     */
    @Test
    void testChooseRandomKey () {
        UUID random = HashMapBuilder.chooseRandomKey();

        assertTrue(keysList.contains(random));
    }
}