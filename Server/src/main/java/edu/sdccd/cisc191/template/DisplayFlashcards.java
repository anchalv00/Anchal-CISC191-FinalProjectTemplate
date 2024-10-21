package edu.sdccd.cisc191.template;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class DisplayFlashcards extends Application{

    //declares the variables
    private static ArrayBuilder array;
    private Vocabulary[] list;
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

    private Button createButton(String text) {
        //customizes the buttons
        Button button = new Button(text);
        button.setPrefSize(100, 150);
        button.setStyle("-fx-font-size: 2em; ");
        return button;
    }

    private void setupFlipButton(Button flipBtn) {
        //uses a java lambda expression to act as an event handler
        //flips the word/meaning when clicked
        flipBtn.setOnAction(actionEvent ->  {
            if(currentText.equals(word)){
                currentText = meaning;
            } else {
                currentText = word;
            }
            label.setText(currentText);
        });
    }

    private void setupNextButton(Button nextBtn) {
        //displays the next word/meaning
        nextBtn.setOnAction(actionEvent ->  {
            if(wordCount < list.length - 1){
                wordCount++;
            } else {
                wordCount = 0; // Reset to start if at the end of list
            }
            vocab = list[wordCount];
            word = vocab.getWord();
            meaning = vocab.getMeaning();
            currentText = word;
            label.setText(currentText);
        });
    }

    private void setupQuitButton(Button quitBtn, Stage stage) {
        //closes the application
        quitBtn.setOnAction(actionEvent -> stage.close());
    }

    private void setupRestartButton(Button restartBtn) {
        restartBtn.setOnAction(actionEvent ->  {
            //displays the first word
            wordCount = 0;
            vocab = list[wordCount];
            word = vocab.getWord();
            meaning = vocab.getMeaning();
            currentText = word;
            label.setText(currentText);
        });
    }

    /*
     * displays an application that mimics a flashcard system
     * @param a new Stage
     */
    @Override
    public void start(Stage stage) throws Exception {
        //gets the 1D vocab array
        list = ArrayBuilder.getVocabs();
        wordCount = 0;
        //gets the first vocab object in the array
        vocab = list[wordCount];
        //gets the word from the vocab object
        word = vocab.getWord();
        meaning = vocab.getMeaning();
        b = true;
        currentText = word;
        //creates an initial label to display the first word
        label = new Label(currentText);
        //customizes the label
        label.setFont(new Font(30));
        label.setAlignment(Pos.CENTER);

        //creates the buttons
        Button flipBtn = createButton("Flip");
        Button nextBtn = createButton("Next");
        Button quitBtn = createButton("Quit");
        Button restartBtn = createButton("Restart");

        //setup buttons
        setupFlipButton(flipBtn);
        setupNextButton(nextBtn);
        setupQuitButton(quitBtn, stage);
        setupRestartButton(restartBtn);

        //organizes the nodes into a hbox and vbox
        HBox hbox1 = new HBox(label);
        hbox1.setAlignment(Pos.CENTER);
        HBox hbox2 = new HBox(flipBtn, nextBtn, restartBtn, quitBtn);
        hbox2.setAlignment(Pos.CENTER);
        //sets the margins between the buttons
        hbox2.setSpacing(10);

        VBox vbox = new VBox(hbox1, hbox2);
        Scene scene = new Scene(vbox, 650, 150, Color.WHITE);
        stage.setTitle("Flashcards");
        stage.setScene(scene);
        stage.show();
    }
}
