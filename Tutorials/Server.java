package Tutorials;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        ServerImp server = new ServerImp();
        server.serverOn(7777);
        server.startMessaging();
    }

}
class ServerImp{

    private ServerSocket serverSocket = null;
    private DataInputStream dataInputStream = null;
    private DataOutputStream dataOutputStream = null;
    private Socket clientSocket = null;

    public ServerImp(){

    }

    public void serverOn(int port){
        System.out.println("Waiting for client to connect...");

        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            dataInputStream = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
            dataOutputStream = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Client successfully connected!");
    }
    public void sendMessage(String text){
        try {
            dataOutputStream.writeUTF(text);
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getMessage() throws IOException {
        return dataInputStream.readUTF();
    }

    public void startMessaging(){

        //writing thread
        Thread writingThread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String text = scanner.nextLine();
                this.sendMessage(text);
            }
        });

        //reading thread
        Thread readingThread = new Thread(() -> {
            boolean contin = true;
            while (contin) {
                try {
                    String text = this.getMessage();
                    if (text.equals("exit")){
                        clientSocket.close();
                        contin = false;
                    }
                    else
                        System.out.println(text);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Client disconnected.");
            writingThread.interrupt();
            Thread.currentThread().interrupt();
        });
        readingThread.start();
        writingThread.start();
    }
}