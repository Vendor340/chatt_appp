package com.example.chat_app.AppUI;

import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.image.ImageObserver;

public class RoundedButton extends JButton{
    private boolean isPressed = false;
    private boolean OverButton = false;
    @Setter
    private Image image;

    RoundedButton(){
        super();
        setOpaque(false);
        setFocusPainted(false);
        setBorderPainted(false);

        MouseAdapter mouseListener = new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (contains(e.getX(), e.getY())){
                    isPressed = true;
                    repaint();
                }


            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isPressed = false;
                repaint();
            }


            @Override
            public void mouseExited(MouseEvent e) {
                isPressed = false;
                OverButton = false;
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                OverButton = contains(e.getX(), e.getY());
                repaint();
            }
        };

        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
    }

    private int getDiameter(){
        return Math.min(getWidth(), getHeight());
    }

    @Override
    public Dimension getPreferredSize(){
        FontMetrics metrics = getGraphics().getFontMetrics(getFont());
        int minDiameter = 10+Math.max(metrics.stringWidth(getText()), metrics.getHeight());
        return new Dimension(minDiameter, minDiameter);

    }

    @Override
    public boolean contains(int x, int y){
        double radius = (double) getDiameter() /2;
        return Point2D.distance(x, y, (double) getWidth()/2, (double) getHeight()/2) < radius;
    }

    @Override
    protected void paintComponent(Graphics g) {
        int diameter = getDiameter();
        int radius = diameter/2;

        if (isPressed){
            g.setColor(Color.blue);
        }else{
            g.setColor(Color.WHITE);
        }

        g.fillOval(getWidth()/2-radius, getHeight()/2-radius, diameter, diameter);
        g.drawImage(image, getWidth()/2-radius, getHeight()/2-radius, diameter, diameter,  null);
        if (OverButton){
            g.setColor(Color.lightGray);
        }else{
            g.setColor(Color.BLACK);
        }
        g.drawOval(getWidth()/2-radius, getHeight()/2-radius, diameter, diameter);



        FontMetrics metrics = getFontMetrics(getFont());
        int stringWidth = metrics.stringWidth(getText());
        int stringHeight = metrics.getHeight();
        g.drawString(getText(), getWidth()/2-stringWidth/2, getHeight()/2+stringHeight/4);
    }
}
