import java.io.*;
import java.net.ServerSocket;
import creator_package.Creator;

public class Server {
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(8000))
        {
            System.out.println("Server starting");
            while(true) {
                Creator creator = new Creator(serverSocket);
                        new Thread(() -> {
                            String request = creator.readLine();
                            System.out.println("Request: " + request);
                            String response = "Hello from server! " + request.length();
                            try {Thread.sleep(2000);} catch (InterruptedException e){}
                            creator.writeLine(response);
                            System.out.println("Response: " + response);
                            try{creator.close(); }catch(IOException e){}

                        }).start();

            }
        } catch (IOException e )
        {
            throw new RuntimeException(e);
        }
    }
}
