import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) throws IOException {
        int serverPort = 8080;
        ServerSocket serverSocket = new ServerSocket(serverPort);
        Socket server = serverSocket.accept();
        //получение данных от клиента
        BufferedReader inClient = new BufferedReader(new InputStreamReader(server.getInputStream()));
        String line = inClient.readLine();
        System.out.println(line);
        //передача данных клиенту
        PrintWriter outClient = new PrintWriter(server.getOutputStream(), true);
        outClient.println("Привет клиент " + server.getLocalSocketAddress());

    }
}
