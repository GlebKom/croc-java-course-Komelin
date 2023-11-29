package course.Komelin.task14new.client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class Client {

    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 7777);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(),
                     StandardCharsets.UTF_8));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true, StandardCharsets.UTF_8);
             BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {

            register(writer, consoleReader);
            readNewMessages(reader);
            writeNewMessages(writer, consoleReader);

        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }
    }

    private static void readNewMessages(BufferedReader reader) {
        new Thread(() -> {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println(message);
                }
            } catch (IOException e) {
                System.out.println("Server closed");
            }
        }).start();
    }

    private static void register(PrintWriter writer, BufferedReader consoleReader) throws IOException {
        System.out.print("Введите имя пользователя : ");
        String nickname = consoleReader.readLine();
        writer.println(nickname);
    }

    private static void writeNewMessages(PrintWriter writer, BufferedReader consoleReader) throws IOException {
        String input;
        while (!(input = consoleReader.readLine()).equals("/stop-chat")) {
            writer.println(input);
        }
    }
}
