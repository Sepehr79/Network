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
    public boolean isClientConnected(){
        if(clientSocket != null)
            return true;
        return false;
    }

    public void startMessaging(){
        try {
            Thread t = new Thread(()-> {
                //reading thread
                new Thread(() -> {
                    while (true) {
                        try {
                            String text = this.getMessage();
                            System.out.println(text);
                        } catch (Exception e) {

                        }
                    }
                }).start();

                //writing thread
                new Thread(() -> {
                    Scanner scanner = new Scanner(System.in);
                    while (true) {
                        String text = scanner.nextLine();

                        this.sendMessage(text);
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