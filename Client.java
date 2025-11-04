package orplog;

import java.io.*;
import java.net.*;
import java.util.Scanner;

// Client class to handle client-side connection and data input
public class Client {
    private String hostIP; //IP address of the server
    private int port; // port number to connect to the server

    // initialise client with IP and port
    public Client(String hostIP, int port) {
        this.hostIP = hostIP;
        this.port = port;
    }
//collects and sends the data to be formatted to CSV file
    public void connectAndSendData() {
        try (Socket socket = new Socket(hostIP, port);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            // Receive and print the welcome message from the server
            System.out.println(reader.readLine());

            // Prompt user for User ID and send to server
            System.out.println(reader.readLine()); 
            String userID = scanner.nextLine();
            writer.println(userID);
            

            // Prompt user for postcode and send to server
            System.out.println(reader.readLine()); 
            String postcode = scanner.nextLine();
            writer.println(postcode);

            // Prompt user for temperature reading and send to server
            System.out.println(reader.readLine()); 
            float temp = Float.parseFloat(scanner.nextLine());
            writer.println(temp);

            // Prompt user for pH level and send to server
            System.out.println(reader.readLine()); 
            int acidity = Integer.parseInt(scanner.nextLine());
            writer.println(acidity);
            

            // Prompt user for oxide-reduction potential and send to server
            System.out.println(reader.readLine());
            float orp = Float.parseFloat(scanner.nextLine());
            writer.println(orp);
            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
