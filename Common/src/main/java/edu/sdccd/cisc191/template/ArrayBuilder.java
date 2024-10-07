package edu.sdccd.cisc191.template;

import java.io.Serializable;

public class ArrayBuilder implements Serializable {

    //2D array with words and meanings
    private static String[][] words = {{"どうぶつ、動物 ", "チーター", "いぬ、犬 ", "ねこ、猫", "うし、牛", "ぶた、豚 ", "うま、馬 ", "ひつじ、羊", "さる、猿", "ねずみ、鼠", "とら、虎", "オオカミ、狼 "}, {"animal", "cheetah", "dog", "cat", "cow", "pig", "horse", "sheep", "monkey", "mouse", "tiger", "wolf"}};
    //1D array that will store Vocabulary objects
    private static Vocabulary[] vocabs = new Vocabulary[words[0].length];
    private static int numWords;

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
    public static Vocabulary[] getVocabs() {
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



    public static void main(String[] args) {
        ArrayBuilder.constructVocab(4);
        Vocabulary[] vocabs = getVocabs();

        System.out.print(ArrayBuilder.printAll());
    }
}
