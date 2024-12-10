package edu.sdccd.cisc191.template;

import java.net.*;
import java.io.*;
import javafx.application.Application;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This program is a server that takes connection requests on
 * the port specified in the main method.
 */
public class Server{
    private ServerSocket serverSocket;
    private Socket clientSocket;
    //private ObjectInputStream iStream;
    private ExecutorService threadPool = Executors.newFixedThreadPool(10);

    /*
     * @param the port number
     */
    public void start(int port) throws Exception {
        serverSocket = new ServerSocket(port);

        while (true) {
            Socket clientSocket = serverSocket.accept();

            // Use thread pool
            threadPool.submit(new ThreadHandler(clientSocket));
        }

            //accepts a connection
        //clientSocket = serverSocket.accept();


    }
    /*
     * closes the connection and streams
     */
    public void stop() throws IOException {
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
