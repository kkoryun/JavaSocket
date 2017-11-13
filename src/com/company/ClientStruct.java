package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import java.util.Queue;

public class ClientStruct {
    public ClientStruct(Socket socket)
    {
        this.socket =socket;
        try {
            this.dataInputStream = new DataInputStream(this.socket.getInputStream());
            this.dataOutputStream = new DataOutputStream(this.socket.getOutputStream());
            this.id = socket.getInetAddress().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getId()
    {
        return this.id;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void addCommand(Command command) {
        this.commandQueue.add(command);
    }

    public void setId(String id) {
        this.id = id;
    }

    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    private Queue<Command> commandQueue;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private Socket socket;
    private String id;
}
