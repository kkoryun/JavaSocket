package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        Client client1 = new Client("localhost", 1234,"player1");
        Client client2 = new Client("localhost", 1234,"player2");
        client1.run();
        client2.run();
    }

    public Client(Socket socket) {
        gameOff = false;
        this.socket = socket;
    }

    public Client(String ip,Integer port,String id) {
        try {
            this.id =id;
            socket = new Socket(ip,port);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean regist(){
        try {
            dataOutputStream.writeUTF(id);
            System.out.println("wait answer");
            String ans = dataInputStream.readUTF();

            System.out.println(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return true;
    }

    public void run(){
        try {
            regist();
            while(!gameOff) {
                //blocked
                String msg = dataInputStream.readUTF();
                //System.out.println(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private Socket socket;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private String id;
    private Graph gameGraph;
    private boolean gameOff;
}
