package com.example.chat_app;

import com.example.chat_app.AppUI.MainController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class BootUI implements CommandLineRunner {

    private final MainController controller;

    @Autowired
    public BootUI(MainController controller){
        this.controller = controller;
    }
    @Override
    public void run(String... args){
        EventQueue.invokeLater(()-> {
            controller.setVisible(true);
        }) ;
    }
}


