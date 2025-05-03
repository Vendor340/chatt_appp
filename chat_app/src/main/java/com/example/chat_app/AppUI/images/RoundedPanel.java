package com.example.chat_app.AppUI.images;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class RoundedPanel extends JPanel {

    public int getRoundTopRight() {
        return RoundTopRight;
    }

    public void setRoundTopRight(int roundTopRight) {
        RoundTopRight = roundTopRight;
        repaint();
    }

    public int getRoundTopLeft() {
        return RoundTopLeft;
    }

    public void setRoundTopLeft(int roundTopLeft) {
        RoundTopLeft = roundTopLeft;
        repaint();
    }

    public int getRoundBottomLeft() {
        return RoundBottomLeft;
    }

    public void setRoundBottomLeft(int roundBottomLeft) {
        RoundBottomLeft = roundBottomLeft;
        repaint();
    }

    public int getRoundBottomRight() {
        return RoundBottomRight;
    }

    public void setRoundBottomRight(int roundBottomRight) {
        RoundBottomRight = roundBottomRight;
        repaint();
    }

    private int RoundTopRight = 0;
    private int RoundTopLeft = 0;
    private int RoundBottomLeft = 0;
    private int RoundBottomRight = 0;

    public RoundedPanel(int first_angle, int second_angle, int third_angle, int forth_angle){
        RoundTopLeft = first_angle;
        RoundTopRight = second_angle;
        RoundBottomRight = third_angle;
        RoundBottomLeft = forth_angle;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        Area area = new Area(CreateRoundTopLeft());
        if (RoundTopRight > 0) {
            area.intersect(new Area(CreateRoundTopRight()));
        }
        if(RoundBottomLeft > 0){
            area.intersect(new Area(CreateRoundBottomLeft()));
        }
        if (RoundBottomRight > 0 ){
            area.intersect(new Area(CreateRoundBottomRight()));
        }
        g2d.fill(area);
        g2d.dispose();
        super.paintComponent(g);
    }

    private Shape CreateRoundTopRight(){
        int width = getWidth();
        int height = getHeight();
        int roundedX = Math.min(width, RoundTopRight);
        int roundedY = Math.min(height, RoundTopRight);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundedX, roundedY));
        area.add(new Area(new Rectangle2D.Double(0, 0, width-(double)roundedX/2, width)));
        area.add(new Area(new Rectangle2D.Double(0, (double)roundedY/2,
                width, height-(double)roundedY/2)));
        return area;
    }

    private Shape CreateRoundTopLeft(){
        int width = getWidth();
        int height = getHeight();
        int roundedX = Math.min(width, RoundTopLeft);
        int roundedY = Math.min(height, RoundTopLeft);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundedX, roundedY));
        area.add(new Area(new Rectangle2D.Double((double)roundedX / 2, 0, width-(double)roundedX / 2,height)));
        area.add(new Area(new Rectangle2D.Double(0, (double)roundedY/2,
                width, height-(double)roundedY /2 )));
        return area;
    }

    private Shape CreateRoundBottomLeft(){
        int width = getWidth();
        int height = getHeight();
        int roundedX = Math.min(width, RoundBottomLeft);
        int roundedY = Math.min(height, RoundBottomLeft);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundedX, roundedY));
        area.add(new Area(new Rectangle2D.Double((double)roundedX / 2, 0, width-(double)roundedX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, 0,
                width, height-(double)roundedY /2 )));
        return area;
    }

    private Shape CreateRoundBottomRight(){
        int width = getWidth();
        int height = getHeight();
        int roundedX = Math.min(width, RoundBottomRight);
        int roundedY = Math.min(height, RoundBottomRight);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundedX, roundedY));
        area.add(new Area(new Rectangle2D.Double(0, 0, width-(double)roundedX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, 0,
                width, height-(double)roundedY /2 )));
        return area;
    }

}
