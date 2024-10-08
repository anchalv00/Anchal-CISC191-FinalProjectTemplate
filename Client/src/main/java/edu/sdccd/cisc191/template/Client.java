package edu.sdccd.cisc191.template;

import java.net.*;
import java.io.*;
import java.util.Scanner;

/**
 * This program connects to the server, allows the user to
 * send a line to be added to a file, and then displays
 * the contents of the file.
 */
public class Client {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private ObjectOutputStream objectOut;
    protected String[][] events = new String[][]{
            {"50 free", "100 free", "200 free"},
            {"100 back", "200 back"},
            {"100 breast", "200 breast"},
            {"100 fly", "200 fly"},
            {"200 IM", "400 IM"}};

    /**
     * starts the connection
     * @param ip
     * @param port
     * @throws IOException
     */
    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        objectOut = new ObjectOutputStream(clientSocket.getOutputStream());
        objectOut.flush(); // Ensure the stream is ready for writing
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    /**
     * closes connection
     * @throws IOException
     */
    public void stopConnection() throws IOException {
        in.close();
        out.close();
        objectOut.close();
        clientSocket.close();
    }

    /**
     * sends a request to server
     * @param line
     * @throws IOException
     */
    public void sendUpdateRequest(String line) throws IOException {
        UpdateRequest updateRequest = new UpdateRequest(line);
        objectOut.writeObject(updateRequest);
        objectOut.flush();
    }

    /**
     * displays file contents
     * @throws IOException
     */
    public void displayFileContents() throws IOException {
        String line;
        System.out.println("All times:");
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
    }

    /**
     * displays a list of events you can enter
     * @throws IOException
     */
    public void displayEvents() throws IOException {
        for (String[] event : events) {
            for (String line : event) {
                System.out.print(line + ", ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        Scanner scanner = new Scanner(System.in);
        try {
            client.startConnection("127.0.0.1", 4444);
                // Shows events you can enter
            System.out.println("List of all events: ");
            client.displayEvents();

                // Prompts user to enter event
            System.out.print("Enter the event and time in this format (event: time): ");
            String line = scanner.nextLine();

                // Updates file
            client.sendUpdateRequest(line);

                // Displays file
            client.displayFileContents();
            client.stopConnection();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}