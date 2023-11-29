package course.Komelin.task14new.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Server {

    static final Lock SERVER_LOCK = new ReentrantLock();

    private static final List<ClientHandler> handlers = new ArrayList<>();

    public static void main(String[] args) {
        listenConnections(7777);
    }

    private static void listenConnections(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started. Waiting for clients...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);
                startNewHandler(clientSocket);

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void startNewHandler(Socket socket) throws IOException{
        ClientHandler handler = new ClientHandler(socket, handlers);
        handlers.add(handler);
        new Thread(handler).start();
    }
}
