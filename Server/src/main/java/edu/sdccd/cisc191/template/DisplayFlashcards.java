package edu.sdccd.cisc191.template;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.UUID;


public class DisplayFlashcards extends Application{

    //declares the variables
    private static HashMapBuilder map;
    private ArrayList<UUID> keysList;
    private UUID randomKey;
    private Vocabulary[] vocabList;
    private Vocabulary vocab;
    private String word;
    private String meaning;
    private String currentText;
    private int wordCount;
    private boolean b;
    private Label label;
    private static int day;

    /*
     * @param an integer that will be set as the word count
     */
    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    /*
     * @return the word count integer
     */
    public int getWordCount() {
        return wordCount;
    }

    /*
     * displays an application that mimics a flashcard system
     * @param a new Stage
     */
    @Override
    public void start(Stage stage) throws Exception {
        //gets the 1D vocab array
        vocabList = HashMapBuilder.getVocabsList();
        keysList = HashMapBuilder.getKeyList();
        randomKey = HashMapBuilder.chooseRandomKey();
        //gets the first vocab object in the array
        vocab = HashMapBuilder.getVocabByKey(randomKey);
        //gets the word from the vocab object
        word = vocab.getWord();
        meaning = vocab.getMeaning();
        wordCount = 0;
        b = true;
        currentText = word;
        //creates an initial label to display the first word
        label = new Label(currentText);
        HBox hbox1 = new HBox();

        //customizes the label
        label.setFont(new Font(30));
        label.setAlignment(Pos.CENTER);

        //creates the buttons
        Button flipBtn = new Button("Flip");
        Button nextBtn = new Button("Next");
        Button quitBtn = new Button("Quit");
        Button restartBtn = new Button("Restart Studying");
        Button easyBtn = new Button("Easy");
        Button hardBtn = new Button("Hard");


        //customizes the buttons
        flipBtn.setPrefSize(75, 90);
        nextBtn.setPrefSize(75, 90);
        quitBtn.setPrefSize(75, 90);
        restartBtn.setPrefSize(150, 90);
        easyBtn.setPrefSize(75, 90);
        hardBtn.setPrefSize(75, 90);

        flipBtn.setStyle("-fx-font-size: 1em; ");
        nextBtn.setStyle("-fx-font-size: 1em; ");
        quitBtn.setStyle("-fx-font-size: 1em; -fx-background-color: #FFB6C1;");
        restartBtn.setStyle("-fx-font-size: 1em; ");
        easyBtn.setStyle("-fx-font-size: 1em; -fx-background-color: #90EE90;");
        hardBtn.setStyle("-fx-font-size: 1em; -fx-background-color: #ADD8E6;");

        //uses a java lambda expression to act as an event handler
        //flips the word/meaning when clicked
        flipBtn.setOnAction(actionEvent ->  {
            if(currentText.equals(word)){
                currentText = meaning;
            }else{
                currentText = word;
            }
            label.setText(currentText);
        });

        //displays the next word/meaning
        nextBtn.setOnAction(actionEvent ->  {
            if(wordCount <= vocabList.length){
                wordCount++;
                while(vocab.getWord().equals(word)) {
                    randomKey = HashMapBuilder.chooseRandomKey();
                    vocab = HashMapBuilder.getVocabByKey(randomKey);
                }
                word = vocab.getWord();
                meaning = vocab.getMeaning();
                currentText = word;
                label.setText(currentText);
            }else{
                label.setText("Finished studying for today.");
            }
        });

        //closes the application
        quitBtn.setOnAction(actionEvent ->  {
            stage.close();
        });

        //displays the first word
        restartBtn.setOnAction(actionEvent ->  {
            wordCount = 0;
            randomKey = HashMapBuilder.chooseRandomKey();
            vocab = HashMapBuilder.getVocabByKey(randomKey);
            word = vocab.getWord();
            meaning = vocab.getMeaning();
            currentText = word;
            label.setText(currentText);
        });

        //displays the first word
        easyBtn.setOnAction(actionEvent ->  {
            HashMapBuilder.decreaseFrequency(randomKey);
            label.setText(currentText + " - placed on easy!");
        });

        //displays the first word
        hardBtn.setOnAction(actionEvent ->  {
            HashMapBuilder.increaseFrequency(randomKey);
            label.setText(currentText + " - placed on hard!");
        });

        //organizes the nodes into a hbox and vbox
        HBox hbox0 = new HBox(easyBtn, hardBtn);
        hbox0.setAlignment(Pos.CENTER);
        hbox0.setPadding(new Insets(20.0f));
        hbox0.setSpacing(10);
        hbox1 = new HBox(label);
        hbox1.setAlignment(Pos.CENTER);
        hbox1.setPadding(new Insets(20.0f));
        HBox hbox2 = new HBox(flipBtn, nextBtn, restartBtn, quitBtn);
        hbox2.setAlignment(Pos.CENTER);
        hbox0.setPadding(new Insets(20.0f));
        //sets the margins between the buttons
        hbox2.setSpacing(10);

        VBox vbox = new VBox(hbox0, hbox1, hbox2);

        Scene scene = new Scene(vbox, 650, 200, Color.WHITE);
        stage.setTitle("Flashcards");
        stage.setScene(scene);
        stage.show();
    }
}
