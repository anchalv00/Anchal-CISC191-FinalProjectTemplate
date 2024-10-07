package edu.sdccd.cisc191.template;


import java.io.Serializable;

public class Vocabulary implements Serializable {
    private String word;
    private String meaning;

    /*
     * @param one String for the word and another for its repective meaning
     */
    public Vocabulary(String word, String meaning){
        this.word = word;
        this.meaning = meaning;
    }

    /*
     * @return a String with the meaning
     */
    public String getMeaning() {
        return meaning;
    }

    /*
     * @return a String with the word
     */
    public String getWord() {
        return word;
    }
}
