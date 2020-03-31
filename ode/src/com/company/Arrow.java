package com.company;

import java.awt.*;

public class Arrow implements Drawable {
    Studio.Point p1;
    Studio.Point p2;
    Studio.Point pstart;
    Studio.Point pmedin1;
    Studio.Point pmedin2;
    Color mycolor;

    Arrow(Studio.Point p1, Studio.Point p2)
    {
         this.p1=p1;
         this.p2=p2;
        double h=0.1;
        pstart=new Studio.Point((int)(((p2.x)*(1-h)/1)+(p1.x)*(h)/1),(int)(((p2.y)*(1-h)/1)+(p1.y)*(h)/1));
        if((p2.y-p1.y)*(p2.y-p1.y)>h)
        {
            Double trislope = -1.0 * (p2.x - p1.x) / (p2.y - p1.y);


            pmedin1 = new Studio.Point(pstart.x + 10, (int) (pstart.y + 10 * trislope));
            pmedin2 = new Studio.Point(pstart.x - 10, (int) (pstart.y - 10* trislope));
        }
        else
        {
            pmedin1=new Studio.Point(pstart.x,pstart.y+20);
            pmedin2=new Studio.Point(pstart.x,pstart.y-20);
        }
    }
    void setcolor(Color c)
    {mycolor=c;}
    void draw_line(Graphics g, Studio.Point p1, Studio.Point p2)
    {
        g.drawLine(p1.x,p1.y,p2.x,p2.y);
    }
    @Override
    public void draw(Graphics g) {


        g.setColor(mycolor);
        if(Studio.Point.ccw(p1,p2,pmedin1)==1) {

            g.fillPolygon(new int[]{pmedin1.x,pstart.x,p2.x},new int[]{pmedin1.y,pstart.y,p2.y},3);
            draw_line(g, p1, p2);

            g.drawLine(pstart.x, pstart.y, pmedin1.x, pmedin1.y);

            g.drawLine(pmedin1.x, pmedin1.y, p2.x, p2.y);
        }
        else{
            g.fillPolygon(new int[]{pmedin2.x,pstart.x,p2.x},new int[]{pmedin2.y,pstart.y,p2.y},3);
            draw_line(g, p1, p2);
            g.drawLine(pstart.x, pstart.y, pmedin2.x, pmedin2.y);

            g.drawLine(pmedin2.x, pmedin2.y, p2.x, p2.y);
        }
        g.setColor(defaultcolor);




    }
}
