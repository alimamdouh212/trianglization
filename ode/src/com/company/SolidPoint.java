package com.company;

import java.awt.*;

public class SolidPoint implements Drawable {

    Studio.Point p1;
    final static  private  int r=5 ;
    Color mycolor=Color.BLACK;
    SolidPoint(Studio.Point p1)
    {
        this.p1=p1;

    }
    @Override
    public void draw(Graphics g) {
        g.setColor(mycolor);
        g.fillOval(p1.x-r,p1.y+r,2*r,2*r);
        g.setColor(defaultcolor);
    }
}
