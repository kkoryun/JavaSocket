package com.company;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.Map;


import com.company.Graph;

public class Server {

    public Server(int port){
        try {
            serverSocket = new ServerSocket(port);

            cr = new ClientRegistrator();
            registrationThread = new Thread(cr);
            registrationThread.start();

            graph = new Graph();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        try {
            //blocked
            Socket socket = serverSocket.accept();

            ClientStruct clientStruct = new ClientStruct(socket);
            clients.put(clientStruct.getId(), clientStruct);

            registrationThread.run();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Server server = new Server(1234);
        while(true) {
            server.run();
        }
    }

    private ServerSocket serverSocket;

    private Graph graph;

    private Map<String, String> players;
    private Map<String, ClientStruct> clients;

    private Thread registrationThread;
    private Thread gameThread;
    private ClientRegistrator cr;

}
