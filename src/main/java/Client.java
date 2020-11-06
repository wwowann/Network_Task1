import com.sun.xml.internal.ws.api.policy.subject.BindingSubject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) throws IOException {
        String host = "127.0.0.1";
        int serverPort = 8080;
        Socket socket = new Socket(host, serverPort);
        //отправка данных серверу
        PrintWriter outServer = new PrintWriter(socket.getOutputStream(), true);
        outServer.println("Привет сервер!");
        // получение данных с сервера
        BufferedReader inServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println(inServer.readLine());
        inServer.close();

    }

}