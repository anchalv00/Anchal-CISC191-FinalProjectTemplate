package edu.sdccd.cisc191.template;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;


public class NetworkAndStreamsTest
{

    private CustomerRequest request;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        request = new CustomerRequest(1);
    }

    /**
     * Uses a try-catch to connect a client to a server and create
     * its output/input streams
     * Writes an object over the stream and is read and tested
     * to see if the contents are accurate
     */
    @Test
    public void testNetworking() throws IOException {
        try (Socket socket = new Socket(InetAddress.getLocalHost().getHostName(), 4444)) {
            //output stream
            ObjectOutputStream oStream = new ObjectOutputStream(socket.getOutputStream());
            //input stream
            ObjectInputStream iStream = new ObjectInputStream(socket.getInputStream());
            int day;

            oStream.writeObject(request);
            oStream.flush();

            CustomerRequest receivedObj = (CustomerRequest) iStream.readObject();
            day = receivedObj.getDay();
            Assertions.assertEquals(day, 1);
        } catch (IOException | ClassNotFoundException e)
        {
            System.err.println(e);
        }
    }
}