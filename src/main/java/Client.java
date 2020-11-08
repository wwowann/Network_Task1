import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
//        String host = "netology.workhost";
        int serverPort = 8080;
        Scanner scan = new Scanner(System.in);
        Socket socket = new Socket("127.0.0.1", serverPort);
        //отправка данных серверу
        PrintWriter outServer = new PrintWriter(socket.getOutputStream(), true);
        // получение данных с сервера
        BufferedReader inServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String msg;
            outServer.println("Привет сервер!");
        System.out.println("SERVER: " + inServer.readLine());
            while (!(inServer.readLine()==null)) {
                System.out.println("SERVER: " + inServer.readLine());
                msg = scan.nextLine();
            outServer.println(msg);
            if ("end".equals(msg)) break;
            System.out.println("SERVER: " + msg);
        }

        inServer.close();
    }
}