package course.Komelin.task14;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 1234);
            System.out.println("Connected to server.");

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter nickname: ");
            String nickname = scanner.nextLine();
            writer.println(nickname);

            Thread thread = new Thread(() -> {
                String message;
                try {
                    while ((message = reader.readLine()) != null) {
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    System.out.println("Server closed");
                }
            });
            thread.start();

            String input;
            while (!(input = scanner.nextLine()).equals("exit")) {
                writer.println(input);
            }

            reader.close();
            writer.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Server closed");
        }
    }
}
