package Tutorials;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        ClientImp client = new ClientImp("localhost", 7777);
        client.startMessaging();
    }
}
class ClientImp{

    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public ClientImp(String host, int port){
        System.out.println("Trying to connect server...");
        try {
            socket = new Socket("localhost", port);
            dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            System.out.println("Successfully connected");
        } catch (Exception e) {
            System.err.println("Client cannot connect to server!");
        }
    }

    public void sentMessage(String message){
        try {
            dataOutputStream.writeUTF(message);
            dataOutputStream.flush();
        } catch (IOException e) {
            System.out.println("Cannot send message!");
        }
    }
    public String getMessage(){

        String text = "";
        while (text.equals("")){
            try {
                text = dataInputStream.readUTF();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return text;
    }

    public void startMessaging(){
        try {
            Thread t = new Thread(()->{
                //reading thread
                new Thread(()->{
                    while (true){
                        String text = this.getMessage();

                        System.out.println(text);
                    }
                }).start();

                //writing thread
                new Thread(()->{
                    Scanner scanner = new Scanner(System.in);
                    while(true){
                        String input = scanner.nextLine();

                        this.sentMessage(input);
                    }
                }).start();
            });
            t.start();
            t.join();
        } catch (InterruptedException e) {
            System.err.println("cant join");
        }
    }

}