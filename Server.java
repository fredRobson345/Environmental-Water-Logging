package orplog;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.text.SimpleDateFormat;
// server class
public class Server {
    private int port; // port number where the server will be
    private String csvFilePath = "log.csv"; // path to csv to log the data
    private ExecutorService threadPool = Executors.newFixedThreadPool(1); // thread-pool to allow up to 1 client

    // Initialise the server port
    public Server(int port) {
        this.port = port;
    }
    // method to start server 
    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(port)) { // creates a server on the specific port
            System.out.println("Server started on port " + port);

            while (true) { // listens for client connections
                Socket clientSocket = serverSocket.accept(); // accepts a new client
                System.out.println("New client connected");
                ClientThread clientThread = new ClientThread(clientSocket, this); // creates a new ClientThread for each connected client
                threadPool.execute(clientThread);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // logs data to csv file
    public synchronized void logData(ClientData data) {
        try (FileWriter fileWriter = new FileWriter(csvFilePath, true); // opens csv file
             BufferedWriter writer = new BufferedWriter(fileWriter)) { 
            writer.write(data.formatForCSV()); // writes formatted data to csv file
            writer.newLine(); // add new line
            System.out.println("Logged data: " + data); //logs data to console
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
