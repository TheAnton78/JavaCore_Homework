import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try (Socket clientSocket = new Socket("netology.homework", Server.LOCALHOST_PORT);
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))){
            System.out.println(reader.readLine());
            String name = scanner.nextLine();
            writer.println(name);
            System.out.println(reader.readLine());
            String answer = scanner.nextLine();
            writer.println(answer);
            String resp = reader.readLine();
            System.out.println(resp);

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }
}
