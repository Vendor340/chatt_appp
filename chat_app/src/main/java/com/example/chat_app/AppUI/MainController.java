package com.example.chat_app.AppUI;

import com.example.chat_app.AppUI.images.RoundedPanel;
import com.example.chat_app.Chat_System.Client;

import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;



@Component
public class MainController extends JFrame{
    private Client client;
    private final JTextField text_field;
    private final JPanel message_area;
    private final RoundedButton send_message_button;
    private final String nickname;

    public MainController(){

        nickname = JOptionPane.showInputDialog(this, "Enter your name, please?",
                "Login", JOptionPane.PLAIN_MESSAGE);


        setTitle("Chat_Swinger - "+nickname);
        getContentPane().setBackground(new Color(15, 11, 44));
        setSize(700, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setSize(700, 60);
        add(panel, BorderLayout.NORTH);

        panel.add(new MyGradientFrame( getWidth(),70,  new Color(53, 31, 181),
                new Color(19, 198, 255), new Color(185, 77, 251), new Color(243, 185, 96)));


        JPanel user_panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        user_panel.setBackground(new Color(18, 13, 52));


        text_field = new JTextField();
        text_field.setOpaque(false);
        text_field.setForeground(Color.WHITE);
        text_field.setCaretColor(Color.WHITE);
        text_field.setText("Type some text...");
        text_field.setBorder(new LineBorder(new Color(21, 15, 55, 1), 2, true));
        user_panel.add(text_field);


        ImageIcon send_icon = new ImageIcon("src/main/java/com/example/chat_app/AppUI/images/send-icon-md.png");
        ImageIcon send_icon_1 = new ImageIcon(send_icon.getImage().
                getScaledInstance(30, 30, Image.SCALE_SMOOTH));

        ImageIcon send_image = new ImageIcon("src/main/java/com/example/chat_app/AppUI/images/send_image.png");
        ImageIcon send_image_1 = new ImageIcon(send_image.getImage().
                getScaledInstance(30, 30, Image.SCALE_SMOOTH));


        //Button for sending a message
        send_message_button = new RoundedButton();
        send_message_button.setImage(send_icon_1.getImage());
        send_message_button.setSize(new Dimension(30, 25));
        send_message_button.setFocusable(false);
        send_message_button.setBackground(Color.WHITE);
        send_message_button.addActionListener(e -> {
            String message = "["+new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] "+nickname
                    +": "+text_field.getText();
            client.SendMessage(message);
            text_field.setText("");
        });
        user_panel.add(send_message_button);


        add(user_panel, BorderLayout.SOUTH);


        message_area = new JPanel();
        message_area.setLayout(new BoxLayout(message_area, BoxLayout.Y_AXIS));
        message_area.setBackground(new Color(15, 11, 44));
        message_area.setFocusable(false);



        JScrollPane scroll_pane = new JScrollPane(message_area);
        scroll_pane.getViewport().setOpaque(false);
        scroll_pane.setBackground(new Color(18, 13, 52));
        scroll_pane.setBorder(new LineBorder(new Color(0, 0, 0, 0)));
        add(scroll_pane, BorderLayout.CENTER);


        try{
            client = new Client("127.0.0.1", 3331, this::onMessageReceived, this::onImageReceived);
            client.startClient();
        }catch(IOException e){
            System.out.println("Error connecting to the server: "+e);
            JOptionPane.showMessageDialog(this, "Error connecting to the server",
                    "Connecting to the server", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

    }

    private void onImageReceived(byte image){
        SwingUtilities.invokeLater(() -> {

                    try {
                        InputStream input_stream = client.getSocket().getInputStream();
                        byte[] SizeArr = new byte[4];
                        input_stream.read(SizeArr);
                        int size = ByteBuffer.wrap(SizeArr).getInt();


                        byte[] imageArr = new byte[size];
                        input_stream.read(imageArr);
                        BufferedImage image1 = ImageIO.read(new ByteArrayInputStream(imageArr));

                        ImageIO.write(image1, "jpg", new File("test_image_1.jpg"));
                    } catch (IOException e) {

                    }
                }
        );
    }

    private void onMessageReceived(String text){
        SwingUtilities.invokeLater(() -> {

            JPanel user_message = new JPanel();
            user_message.setOpaque(false);

            String user = text.split(" ")[1].replace(":", "");
            String new_text = text.replace(user, "");


            JLabel show_user = new JLabel(user);
            show_user.setFont(new Font("Red Hat Display", Font.BOLD, 15));
            show_user.setForeground(Color.WHITE); //Set a font and color for message

            user_message.setLayout(new BoxLayout(user_message, BoxLayout.Y_AXIS));

            RoundedPanel background;
            JLabel text_message;
            if (user.equals(nickname)) {
                background = new RoundedPanel(50, 0, 50, 50);
                //Panel for making a background for message.
                text_message = new JLabel(new_text);
                text_message.setForeground(Color.BLACK);
                background.setForeground(Color.WHITE);

                text_message.setAlignmentX(java.awt.Component.RIGHT_ALIGNMENT);
                background.setAlignmentX(java.awt.Component.RIGHT_ALIGNMENT);
                user_message.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

            }else{
                background = new RoundedPanel(0, 50, 50, 50);
                //Panel for making a background for message.
                text_message = new JLabel(new_text);
                text_message.setForeground(Color.WHITE);
                background.setForeground(new Color(70, 41, 242));
                background.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
                text_message.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
                user_message.setAlignmentX(java.awt.Component.RIGHT_ALIGNMENT);
            }
            background.add(text_message);

            text_message.setPreferredSize(text_message.getPreferredSize());
            text_message.setMaximumSize(text_message.getPreferredSize());

            background.setPreferredSize(background.getPreferredSize());
            background.setMaximumSize(background.getPreferredSize());


            user_message.add(show_user);
            user_message.add(background);


            message_area.add(user_message);
            message_area.revalidate();
        }
        );
    }

}
