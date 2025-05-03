package com.example.chat_app.AppUI;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedBorder extends AbstractBorder {
    private final Color fgcolor;
    private final int gap;
    public RoundedBorder(Color fg_c, int g){
        fgcolor = fg_c;
        gap = g;
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }



    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2D = (Graphics2D) g.create();
        g2D.setColor(fgcolor);
        g2D.draw(new RoundRectangle2D.Double(x, y, width-1, height-1, gap, gap));


        g2D.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return (getBorderInsets(c, new Insets(gap,gap,gap,gap)));
    }

    @Override
    public Insets getBorderInsets(Component c, Insets ins){
        ins.left=ins.right=ins.top=ins.bottom=gap/2;
        return ins;
    }
}
