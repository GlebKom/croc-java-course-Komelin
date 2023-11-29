package course.Komelin.task14new.server;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ClientHandler implements Runnable{

    private final Socket clientSocket;
    private final List<ClientHandler> allHandlers;
    private static final Lock R_LOCK = new ReentrantLock();
    private final PrintWriter printWriter;
    private String clientNickname;
    public ClientHandler(Socket clientSocket, List<ClientHandler> allHandlers) throws IOException {
        this.clientSocket = clientSocket;
        this.allHandlers = allHandlers;
        this.printWriter = new PrintWriter(clientSocket.getOutputStream(), true, StandardCharsets.UTF_8);
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public PrintWriter getPrintWriter() {
        return printWriter;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(),
                StandardCharsets.UTF_8))){

            clientNickname = reader.readLine();

            String message;
            System.out.println(clientNickname);
            while ((message = reader.readLine()) != null) {
                sendMessageToAllClients(clientNickname, message);
            }
        } catch (IOException e) {
            allHandlers.remove(this);
            if (clientNickname != null) {
                sendMessageToAllClients("Server", clientNickname + " has left");
            }
        } finally {
            printWriter.close();
        }
    }


    private void sendMessageToAllClients(String nickname, String message) {
        List<ClientHandler> handlersCopy = new ArrayList<>(allHandlers);
        Server.SERVER_LOCK.lock();
        for (ClientHandler handler : handlersCopy) {
            if (handler.equals(this)) {
                continue;
            }

            if (handler.getClientSocket().isClosed()) {
                allHandlers.remove(handler);
                continue;
            }
            PrintWriter writer = handler.getPrintWriter();
            writer.println(nickname + ": " + message);
        }
        Server.SERVER_LOCK.unlock();
    }
}
