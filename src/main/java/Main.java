import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Main {
    public static void main(String[] args) throws IOException {
        String userName = null;
        int serverPort = 8080;
        ServerSocket serverSocket = new ServerSocket(serverPort);
        Socket server = serverSocket.accept();
        server.setSoTimeout(10000);
        //получение данных от клиента
        BufferedReader inClient = new BufferedReader(new InputStreamReader(server.getInputStream()));
        //отправка данных клиенту
        PrintWriter outClient = new PrintWriter(server.getOutputStream(), true);
        boolean bool = true;
        try {
            outClient.println("Привет! Как тебя зовут?");
            userName = inClient.readLine();
            outClient.println("Привет " + userName + "! Если Вы не робот, введите число от 1 до 10");
            String msg;
            while (bool) {
                msg = inClient.readLine();
                try {
                    int cod = Integer.parseInt(msg.trim());
                    if (cod > 0 && cod < 11) {
                        outClient.println("Добро пожаловать, " + userName + "! Ты еще ребенок? (да/нет)");
                        bool = false;
                    } else throw new NumberFormatException("Вы ввели некорректное значение, повторите ввод");
                } catch (NumberFormatException e) {
                    outClient.println("Вы ввели некорректное значение, повторите ввод");
                }
            }
            msg = inClient.readLine();
            while (!serverSocket.isClosed()) {
                if (msg.equals("да")) {
                    outClient.println("Извини, этот сайт для взрослых, До свидания! Сервер прощается с Вами. всего хорошего!");
                    serverSocket.close();
                } else if (msg.equals("нет")) {
                    outClient.println(userName + ", Вы попали на сайт налоговой службы! " +
                            "Давайте Ваши денежки!");
                    serverSocket.close();
                } else {
                    outClient.println("Введите корректное значение");
                    msg = inClient.readLine();
                }
            }
            outClient.println("Сервер прощается с Вами. Всего хорошего!");
            serverSocket.close();
        }catch (SocketTimeoutException e){
            System.out.println("Время отклика клиента закончилось. Соединение разорвано");
        }
    }
}



