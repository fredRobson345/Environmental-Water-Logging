package orplog;

/**
 * Small launcher to run the Server, the Client, or both together.
 *
 * Usage:
 *  java orplog.Main server [port]
 *  java orplog.Main client [host] [port]
 *  java orplog.Main both [host] [port]
 *
 * When running `both` the server is started in a background thread and then
 * the client is started and connects to it. Default host=localhost, port=5000.
 */
public class Main {
    public static void main(String[] args) {
        String mode = args.length > 0 ? args[0] : "both";
        String host = "localhost";
        int port = 5000;

        try {
            switch (mode) {
                case "server":
                    if (args.length > 1) port = Integer.parseInt(args[1]);
                    runServer(port);
                    break;
                case "client":
                    if (args.length > 1) host = args[1];
                    if (args.length > 2) port = Integer.parseInt(args[2]);
                    runClient(host, port);
                    break;
                case "both":
                    if (args.length > 1) host = args[1];
                    if (args.length > 2) port = Integer.parseInt(args[2]);
                    runBoth(host, port);
                    break;
                default:
                    printUsage();
            }
        } catch (NumberFormatException nfe) {
            System.err.println("Port must be a number");
            printUsage();
        }
    }

    private static void runServer(int port) {
        System.out.println("Starting server on port " + port);
        Server server = new Server(port);
        server.startServer();
    }

    private static void runClient(String host, int port) {
        System.out.println("Starting client connecting to " + host + ":" + port);
        Client client = new Client(host, port);
        client.connectAndSendData();
    }

    private static void runBoth(String host, int port) {
        System.out.println("Starting server in background and client to " + host + ":" + port);
        Thread serverThread = new Thread(() -> {
            Server server = new Server(port);
            server.startServer();
        }, "Server-Thread");
        serverThread.setDaemon(true);
        serverThread.start();

        // Give server a moment to bind the socket
        try {
            Thread.sleep(300);
        } catch (InterruptedException ignored) {
        }

        runClient(host, port);
    }

    private static void printUsage() {
        System.out.println("Usage:");
        System.out.println("  java orplog.Main server [port]");
        System.out.println("  java orplog.Main client [host] [port]");
        System.out.println("  java orplog.Main both [host] [port]");
    }
}
