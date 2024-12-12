package edu.sdccd.cisc191.template;

import java.net.*;
import java.io.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * This program opens a connection to a computer specified
 * as the first command-line argument.  If no command-line
 * argument is given, it prompts the user for a computer
 * to connect to.
 */

@SpringBootApplication
public class Client {
    private Socket clientSocket;
    private ObjectOutputStream oStream ;
    private ObjectInputStream iStream;

    /**
     * Stores objects to database using springboot
     *
     * @param repo to store objects
     * @return
     */
    @Bean
    CommandLineRunner commandLineRunner(CustomerRequestRepository repo){
        return args -> {
            CustomerRequest request = new CustomerRequest(4);
            repo.save(request);
        };
    }

    /**
     * Creates a connection with a server and sends a request down a stream
     * @param port device name and a port number
     */
    public void startConnection(String ip, int port) throws Exception {
        clientSocket = new Socket(ip, port);
        oStream = new ObjectOutputStream(clientSocket.getOutputStream());

        //creates object to send over
        CustomerRequest request = new CustomerRequest(4);

        //sends request to server
        oStream.writeObject(request);
        oStream.flush();
    }

    /**
     * Stops the connection by closing the stream and socket connection
     */
    public void stopConnection() throws IOException {
        oStream.close();
        clientSocket.close();
    }

    /**
     * Starts and ends a connection with the Server
     */
    public static void main(String[] args) {
        Client client = new Client();
        SpringApplication.run(Client.class, args);

        try {
            client.startConnection(InetAddress.getLocalHost().getHostName(), 4444);
            client.stopConnection();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

