
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class DdosServer {

    public static void main(String[] args) {
        try {
           int serverPort = 12345; // Use the same port as the client



            ServerSocket serverSocket = new ServerSocket(serverPort);
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();

            // Simulate receiving data packets from the client
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                String receivedData = new String(buffer, 0, bytesRead);
                System.out.println("Received: " + receivedData);
            }

            socket.close();
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

