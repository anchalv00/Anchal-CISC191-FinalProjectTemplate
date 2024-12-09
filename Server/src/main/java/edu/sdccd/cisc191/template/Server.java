package edu.sdccd.cisc191.template;

import java.net.*;
import java.io.*;
import javafx.application.Application;

/**
 * This program is a server that takes connection requests on
 * the port specified in the main method.
 */
public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ObjectInputStream iStream;

    /*
     * @param the port number
     */
    public void start(int port) throws Exception {
        serverSocket = new ServerSocket(port);
        //accepts a connection
        clientSocket = serverSocket.accept();

        //gets input stream from client
        ObjectInputStream iStream = new ObjectInputStream(clientSocket.getInputStream());

        //reads object from stream
        CustomerRequest receivedObj = (CustomerRequest) iStream.readObject();
        int day = receivedObj.getDay();

        //constructs a 1D vocabs array
        HashMapBuilder.buildMap(day);

        //makes sure there are enough words in the list to display for the specified day #
        if((day*3) <= HashMapBuilder.getNumWords()){
            //launches display application
            Application.launch(DisplayFlashcards.class, new String[]{});
        }else{
            System.out.println("We currently don't have more words for you to study from.");
        }
    }
    /*
     * closes the connection and streams
     */
    public void stop() throws IOException {
        iStream.close();
        clientSocket.close();
        serverSocket.close();
    }

    /*
     * allows for a connection to occur
     */
    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.start(4444);
            server.stop();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
} //end class Server
