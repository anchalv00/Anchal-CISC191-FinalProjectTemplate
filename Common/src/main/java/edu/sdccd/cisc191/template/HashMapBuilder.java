package edu.sdccd.cisc191.template;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class HashMapBuilder implements Serializable {

    //2D array with words and meanings
    private static String[][] words = {{"どうぶつ、動物 ", "チーター", "いぬ、犬 ", "ねこ、猫", "うし、牛", "ぶた、豚 ", "うま、馬 ", "ひつじ、羊", "さる、猿", "ねずみ、鼠", "とら、虎", "オオカミ、狼 "}, {"animal", "cheetah", "dog", "cat", "cow", "pig", "horse", "sheep", "monkey", "mouse", "tiger", "wolf"}};
    //1D array that will store Vocabulary objects
    private static Vocabulary[] vocabs = new Vocabulary[words[0].length];
    private static int numWords;
    private static HashMap<UUID, Vocabulary> map = new HashMap<>();
    private static ArrayList<UUID> keyList = new ArrayList<UUID>();

    public static void buildMap(int day){
        constructVocab(day);

        for(Vocabulary vocab : vocabs) {
            UUID uniqueID = UUID.randomUUID();
            map.put(uniqueID, vocab);
            keyList.add(uniqueID);
        }
    }

    public static <UUID, E> Set<UUID> getKeyByVocab(HashMap<UUID, E> map, E vocab){
       return map.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), vocab))
                .map(HashMap.Entry::getKey)
                .collect(Collectors.toSet());
    }

    public static Vocabulary getVocabByKey(UUID id){
        return map.get(id);
    }

    public static ArrayList<UUID> getKeyList(){
        return keyList;
    }

    public static void increaseFrequency(UUID id){
        keyList.add(id);
    }

    public static void decreaseFrequency(UUID id){
        Map<UUID, Long> countsMap =
                keyList.stream()
                        .collect((Collectors.groupingBy(e -> e, Collectors.counting())));

        Long count = countsMap.get(id);
        if(count != 1){
            keyList.remove(id);
        }
    }

    public static UUID chooseRandomKey(){
        Random random = new Random();
        int randomNum = random.nextInt(keyList.size());

        return keyList.get(randomNum);
    }

    /*
     * Stores elements in the vocabs array
     * @param an integer that specifies the day request from the client
     */
    public static void constructVocab(int day){
        for(int i = 0; i < (3*day); i++){
            vocabs[i] = new Vocabulary(words[0][i], words[1][i]);
        }
    }

    /*
     * @return a Vocabulary 1D array
     */
    public static Vocabulary[] getVocabsList() {
        return vocabs;
    }

    /*
     * @return the number of words/meanings in the 2d array
     */
    public static int getNumWords() {
        numWords = words[0].length;
        return numWords;
    }

    /*
     * @return a String that prints the contents of the 2D array
     */
    public static String printAll(){
        String string = "";
        for(int i = 0; i < words[0].length; i++){
            string += words[0][i] + " " + words[1][i] + " ";
        }

        return string;
    }

    /*
     * @param a String that wants to be searched
     * @return an int that specifies the index value of a certain String element
     */
    public static int findIndexOf(String string){
        int index = 0;
        for(int i = 0; i < words[0].length; i++){
            if(words[1][i].equals(string)){
                index = i;
            }
        }
        return index;
    }


//    public static void main(String[] args) {
//        HashMapBuilder.constructVocab(4);
//        Vocabulary[] vocabs = getVocabsList();
//
//        System.out.print(HashMapBuilder.printAll());
//    }
}
