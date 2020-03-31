package com.company;

import java.awt.*;

public class Circle implements Drawable {
    int r;
    Studio.Point point;
    Color mycolor;
    Circle(Studio.Point point, int r)
    {
        this.point=point;
        this.r=r;
    }
    void setcolor(Color c)
    {mycolor=c;}
    @Override
    public void draw(Graphics g)
    {
        g.setColor(mycolor);
        g.drawOval(point.x-r,point.y-r,2*r,2*r);
        g.setColor(defaultcolor);
    }
}
