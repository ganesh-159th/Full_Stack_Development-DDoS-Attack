 /*import java.io.OutputStream;
import java.net.Socket;

public class DdosClient {

    public static void main(String[] args) {
        try {
            String serverAddress = "localhost"; // Change to the server's IP address
            int serverPort = 12345; // Change to the server's port

            Socket socket = new Socket(serverAddress, serverPort);
            OutputStream outputStream = socket.getOutputStream();

            // Simulate sending data packets to the server
            for (int i = 0; i < 1000; i++) {
                String dataPacket = "Packet " + i;
                byte[] packetBytes = dataPacket.getBytes();
                outputStream.write(packetBytes);
                System.out.println("Sent: " + dataPacket);
                Thread.sleep(100); // Simulate a delay between packets
            }

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

  */
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.net.Socket;

 public class DdosClient {

     public static void main(String[] args) {
         String serverAddress = "127.0.0.1"; // Replace with the actual server address
         int serverPort = 12345;

         try (Socket socket = new Socket(serverAddress, serverPort);
              PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
              BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

             // Send a message to the server
             out.println("Hello, server!");
             out.println("this is ganesh");
             out.println("\n this one is another server");
             out.println("\n this one is for the 4th task");

             // Receive and print the server's response
             String response = in.readLine();
             System.out.println("Server response: " + response);

         } catch (IOException e) {
             e.printStackTrace();
         }
     }
 }
