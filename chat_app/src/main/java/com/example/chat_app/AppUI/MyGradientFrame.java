package com.example.chat_app.AppUI;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class MyGradientFrame extends JPanel{
    int width;
    int height;
    Color sideLeft;
    Color sideLeftCenter;
    Color sideRightCenter;
    Color sideRight;


    MyGradientFrame(int width, int height, Color sideLeft, Color sideLeftCenter, Color sideRightCenter, Color sideRight){
        super();
        this.width = width;
        this.height = height;
        this.sideLeft = sideLeft;
        this.sideLeftCenter = sideLeftCenter;
        this.sideRightCenter = sideRightCenter;
        this.sideRight = sideRight;
        this.setPreferredSize(new Dimension(width, height));

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gp = (Graphics2D) g;


        Point2D start = new Point2D.Float(0f, 0f);
        Point2D end = new Point2D.Float(width, height);
        float[] dist = {0f, 0.3f, 0.5f, 1f};
        Color[] colors = {sideLeft, sideLeftCenter, sideRightCenter, sideRight};
        AffineTransform tf = AffineTransform.getTranslateInstance
                ( -getWidth() ,  -getHeight() );

        LinearGradientPaint paint = new LinearGradientPaint(start, end, dist, colors);

        gp.setPaint(paint);
        g.fillRect(0, 0, getWidth(), getHeight());

    }
}
