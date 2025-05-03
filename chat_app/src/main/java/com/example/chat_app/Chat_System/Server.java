package com.example.chat_app.Chat_System;

import org.springframework.stereotype.Component;

import java.net.*;
import java.io.*;
import java.util.ArrayList;

@Component
public class Server {
    static private ArrayList<ClientHandler> clients = new ArrayList<>();

    public static void main(String args[]) throws IOException{
        ServerSocket server_socket = new ServerSocket(3331);
        System.out.println("Server has been started!");

        while(true){
            try{
                Socket client_address = server_socket.accept();
                System.out.println("Connection is established!");
                ClientHandler clientThread = new ClientHandler(client_address, clients);
                clients.add(clientThread);
                new Thread(clientThread).start();
            }catch(IOException e){
                System.out.println("Problem with connection: "+e.getMessage());
            }
        }


    }
}
