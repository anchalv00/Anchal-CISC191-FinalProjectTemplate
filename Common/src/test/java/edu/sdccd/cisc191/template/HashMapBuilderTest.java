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

    @BeforeAll
    public static void setUp() {
        HashMapBuilder.buildMap(4);
        map = HashMapBuilder.getMap();
        vocabList = HashMapBuilder.getVocabsList();
        keysList = HashMapBuilder.getKeyList();
    }

    @Test
    void testBuildMap() {
        assertEquals(12, vocabList.length);
    }

    @Test
    void testGetKeyByVocab() {
        Set<UUID> set = HashMapBuilder.getKeyByVocab(map, vocabList[0]);
        UUID element = UUID.randomUUID();
        for (UUID key : set) {
            element = key;
        }

        assertEquals(keysList.get(0), element);
    }

    @Test
    void testGetVocabByKey() {
        UUID element = keysList.get(0);

        assertEquals(vocabList[0].getWord(), HashMapBuilder.getVocabByKey(element).getWord());
    }

    @Test
    void testIncreaseFrequency() {
        HashMapBuilder.increaseFrequency(keysList.get(0));

        assertEquals(13, keysList.size());

        HashMapBuilder.decreaseFrequency(keysList.get(0));

        assertEquals(12, keysList.size());
    }

    @Test
    void testDecreaseFrequency() {
        HashMapBuilder.increaseFrequency(keysList.get(0));
        HashMapBuilder.increaseFrequency(keysList.get(0));

        HashMapBuilder.decreaseFrequency(keysList.get(0));

        assertEquals(13, keysList.size());

        HashMapBuilder.decreaseFrequency(keysList.get(0));

        assertEquals(12, keysList.size());
    }

    @Test
    void testChooseRandomKey () {
        UUID random = HashMapBuilder.chooseRandomKey();

        assertTrue(keysList.contains(random));
    }
}