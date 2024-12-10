package edu.sdccd.cisc191.template;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;

class HashMapBuilderTest {
    private ArrayList<UUID> keysList;
    private HashMap<UUID, Vocabulary> map;
    private Vocabulary[] vocabList;
    private Set<UUID> set;


//    @BeforeEach
//    public void setUp() {
//        HashMapBuilder.buildMap(4);
//        map = HashMapBuilder.getMap();
//        vocabList = HashMapBuilder.getVocabsList();
//        keysList = HashMapBuilder.getKeyList();
//    }

    @org.junit.jupiter.api.Test
    void testBuildMap() {
        HashMapBuilder.buildMap(4);
        vocabList = HashMapBuilder.getVocabsList();

        assertEquals(12, vocabList.length);
    }

    @org.junit.jupiter.api.Test
    void testGetKeyByVocab() {
        HashMapBuilder.buildMap(4);
        map = HashMapBuilder.getMap();
        vocabList = HashMapBuilder.getVocabsList();
        keysList = HashMapBuilder.getKeyList();

        set = HashMapBuilder.getKeyByVocab(map, vocabList[0]);
        UUID element = UUID.randomUUID();
        for (UUID key : set) {
            element = key;
        }

        assertEquals(element, keysList.get(0));
    }

    @org.junit.jupiter.api.Test
    void testGetVocabByKey() {
        HashMapBuilder.buildMap(4);
        map = HashMapBuilder.getMap();
        vocabList = HashMapBuilder.getVocabsList();
        keysList = HashMapBuilder.getKeyList();

        UUID element = keysList.get(0);

        assertEquals(HashMapBuilder.getVocabByKey(element), vocabList[0]);
    }

    @org.junit.jupiter.api.Test
    void testIncreaseFrequency() {
        HashMapBuilder.buildMap(4);
        map = HashMapBuilder.getMap();
        vocabList = HashMapBuilder.getVocabsList();
        keysList = HashMapBuilder.getKeyList();

        //UUID element = UUID.randomUUID();
        HashMapBuilder.increaseFrequency(keysList.get(0));
        keysList = HashMapBuilder.getKeyList();

        assertEquals(13, keysList.size());
    }

    @org.junit.jupiter.api.Test
    void testDecreaseFrequency() {
        HashMapBuilder.buildMap(4);
        map = HashMapBuilder.getMap();
        vocabList = HashMapBuilder.getVocabsList();
        keysList = HashMapBuilder.getKeyList();

        //UUID element = UUID.randomUUID();
        HashMapBuilder.increaseFrequency(keysList.get(0));

        HashMapBuilder.decreaseFrequency(keysList.get(0));
        keysList = HashMapBuilder.getKeyList();

        assertEquals(12, keysList.size());
    }

    @org.junit.jupiter.api.Test
    void testChooseRandomKey () {
        HashMapBuilder.buildMap(4);
        map = HashMapBuilder.getMap();
        vocabList = HashMapBuilder.getVocabsList();
        keysList = HashMapBuilder.getKeyList();

        UUID random = HashMapBuilder.chooseRandomKey();

        assertTrue(keysList.contains(random));
    }
}