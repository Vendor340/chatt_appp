package com.example.chat_app.Chat_System;

import ch.qos.logback.core.net.server.Client;
import lombok.Getter;

import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
    @Getter
    private Socket ClientSocket;
    private BufferedReader in1;
    private PrintWriter out1;
    private ArrayList<ClientHandler> clients;

    public ClientHandler(Socket client_socket, ArrayList<ClientHandler> clients_list){
        this.ClientSocket = client_socket;
        try{
            out1 = new PrintWriter(ClientSocket.getOutputStream(), true);
            in1 = new BufferedReader(new InputStreamReader(ClientSocket.getInputStream()));
            clients = clients_list;
        } catch(IOException e){
            System.out.println("Error during instantiating input and output: "+e);
        }


    }
    @Override
    public void run() {
        String stringline;
        try{
            while ((stringline = in1.readLine()) != null){
                for (ClientHandler client : clients){
                    client.out1.println(stringline);
                }
            }
        }catch(IOException e){
            System.out.println("Error during sending or receiving a message");
        }
        try{
            ClientSocket.close();
            in1.close();
            out1.close();
        } catch(IOException e){
            System.out.println("Error during closing a message: "+e);
        }
    }
}
