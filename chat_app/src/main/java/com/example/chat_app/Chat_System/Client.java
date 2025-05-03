package com.example.chat_app.Chat_System;


import lombok.Getter;

import java.io.*;
import java.net.*;
import java.util.function.Consumer;

public class Client{
    private BufferedReader in;
    private PrintWriter out;
    private Consumer<String> onMessageReceived;
    private Consumer<Byte> onImageReceived;
    @Getter
    private Socket socket;

    public Client(String host, int port, Consumer<String> onMessageReceived,
                  Consumer<Byte> onImageReceived) throws IOException{
            socket = new Socket(host, port);
            System.out.println("Client is connected to the server");
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.onMessageReceived = onMessageReceived;
            this.onImageReceived = onImageReceived;
    }

    public void SendMessage(String text){
        out.println(text);
    }

    public void startClient(){
        new Thread(()->{
            try{
                String line;
                int file_read = -1;
                byte[] file_bytes = new byte[1024];
                while ((line = in.readLine())!=null || in.read() != -1){
                    this.onMessageReceived.accept(line);
                }
            }catch (IOException e){
                System.out.println("Starting client went wrong: "+ e);
            }

        }).start();
    }



}


