import javafx.scene.transform.Scale;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String userName = null;
        int serverPort = 8080;
        ServerSocket serverSocket = new ServerSocket(serverPort);
        Socket server = serverSocket.accept();
        //получение данных от клиента
        BufferedReader inClient = new BufferedReader(new InputStreamReader(server.getInputStream()));
        PrintWriter outClient = new PrintWriter(server.getOutputStream(), true);
        outClient.println("Соединение с " + server.getLocalSocketAddress() + " установлено");
        String messageClient = inClient.readLine();
        System.out.println(messageClient);
//        while (!server.isClosed()) {
            outClient.println("Как тебя зовут?");
            System.out.println(inClient.readLine());
            userName = inClient.readLine();
            outClient.println("Привет " + userName + "!");
            outClient.println("Если Вы не робот, введите число от 1 до 10");
            System.out.println(inClient.readLine());
            try {
                if (Integer.getInteger(inClient.readLine()) > 0 || Integer.getInteger(inClient.readLine()) < 11)
                    outClient.println("Добро пожаловать, " + userName + "!");
            } catch (NullPointerException e) {
                outClient.println("Вы ввели некорректное значение, повторите");
                messageClient = inClient.readLine();
            }
        outClient.println("Ты еще ребенок? (да/нет)");
        messageClient = inClient.readLine();
        while (!messageClient.equals("да")) {
            if ("нет".equals(messageClient)) {
                outClient.println("Привет! " + userName + ", Вы попали на сайт налоговой службы! " +
                        "Давайте Ваши денежки!");
            } else {
                outClient.println("Введите корректное значение");
                messageClient = inClient.readLine();
                continue;
            }
            outClient.println("Извини, этот сайт для взрослых, До свидания");
        }
            server.close();
//    }
}}



