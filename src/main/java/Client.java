import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        String host = "netology.homework";
        int serverPort = 8080;
//        InetAddress inetAddress = InetAddress.getByName(host);
        Socket socket = new Socket(host, serverPort);
        Scanner scan = new Scanner(System.in);
        socket.setSoTimeout(10000);
        //отправка данных серверу
        PrintWriter outServer = new PrintWriter(socket.getOutputStream(), true);
        // получение данных с сервера
        BufferedReader inServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String msg;
        try {
            while (!socket.isClosed() && socket.isConnected()) {
                System.out.println("SERVER: " + inServer.readLine());
                msg = scan.nextLine();
                outServer.println(msg);
                if ("end".equals(msg)) break;
            }
        } catch (SocketException e) {
            System.out.println("Сервер закончил свою работу");
        }
        inServer.close();
        outServer.close();
        socket.close();
    }
}