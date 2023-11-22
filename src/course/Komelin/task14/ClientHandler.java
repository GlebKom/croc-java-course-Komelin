package course.Komelin.task14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private final Socket clientSocket;
    private BufferedReader reader;
    private PrintWriter writer;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new PrintWriter(clientSocket.getOutputStream(), true);

            String nickname = reader.readLine();
            System.out.println("Client nickname: " + nickname);

            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println(nickname + ": " + message);
                sendMessageToAllClients(nickname + ": " + message);
            }
        } catch (IOException e) {
            System.out.println("User disconnected");
        } finally {
            try {
                reader.close();
                writer.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendMessageToAllClients(String message) {
        for (ClientHandler client : Server.clients) {
            client.writer.println(message);
        }
    }
}
