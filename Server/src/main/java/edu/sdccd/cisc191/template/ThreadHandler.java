package edu.sdccd.cisc191.template;

import javafx.application.Application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ThreadHandler implements Runnable {
    private Socket clientSocket;
    private ObjectInputStream iStream;
    private int day;

    /**
     * @param clientSocket
     */
    public ThreadHandler(Socket clientSocket){
        this.clientSocket = clientSocket;
    }

    /**
     * executes a response to the client request using threads that
     * act simultaneously
     */
    @Override
    public void run(){
        try {
            //gets input stream from client
            iStream = new ObjectInputStream(clientSocket.getInputStream());

            try{
                //reads object from stream
                CustomerRequest receivedObj = (CustomerRequest) iStream.readObject();
                day = receivedObj.getDay();

            }catch (IOException | ClassNotFoundException e){
                e.printStackTrace();
            }

            //fills map with UUID-Vocabulary object pairs
            HashMapBuilder.buildMap(day);

            Vocabulary[] vocabs = HashMapBuilder.getVocabsList();

            //makes sure there are enough words in the list to display for the specified day #
            if((day*3) <= HashMapBuilder.getNumWords()){
                for(Vocabulary vocab: vocabs){
                    System.out.println(vocab.getWord());
                }
                //launches display application
                Application.launch(DisplayFlashcards.class, new String[]{});
            }else{
                System.out.println("We currently don't have more words for you to study from.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //closes socket and stream
                clientSocket.close();
                iStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
