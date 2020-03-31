package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;

public  class Sheet extends JLabel implements ActionListener {

    final static int defaulttime=1000;
    LinkedBlockingQueue<Event> events=new LinkedBlockingQueue<>();
    LinkedList<Drawable> drawables=new LinkedList<>();
    Serial_map<Integer,Circle> circleSerial_map=new Serial_map<>(3);
    Serial_map<Integer,Arrow> line_map=new Serial_map<>(4);
    Serial_map<Integer,SolidPoint> pointsmap=new Serial_map<>(2);
    @Override
    public void actionPerformed(ActionEvent e) {
        start();

    }

    static private class Event
    {
        int time;
        Runnable run;
        Event(int t,Runnable r)
        {
           time=t;
           run=r;
        }
        int getTime()
        {
            return time;
        }



    }
    private LinkedList makecirclelist(Studio.Point p,int r)
    {
        LinkedList list=new LinkedList();
        list.add(p.x);
        list.add(p.y);
        list.add(r);
        return list;
    }
    private LinkedList makelinelist(Studio.Point p1, Studio.Point p2)
    {

        LinkedList list=new LinkedList();
        list.add(p1.x);
        list.add(p1.y);
        list.add(p2.x);
        list.add(p2.y);
        return list;
    }
     void make_cicrle(int r,Studio.Point p,Color c)
    {

        try {
            events.put(new Event(5*defaulttime, new Runnable() {
                @Override
                public void run()
                {
                    drawables.add(new Circle(p,r));

                    ((Circle)drawables.getLast()).setcolor(c);
                    circleSerial_map.put(makecirclelist(p,r), (Circle) drawables.getLast());
                }

            }));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
    void change_circlecolor(int r, Studio.Point p,Color c)
    {
        try {
            events.put(new Event(5*defaulttime, new Runnable() {
                @Override
                public void run()
                {
                   Circle cir=circleSerial_map.get(makecirclelist(p,r));
                    cir.mycolor=c;

                }

            }));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    void make_line(Studio.Point p1,Studio.Point p2,Color c)
    {

        try {
            events.put(new Event(0, new Runnable() {
                @Override
                public void run()
                {
                    drawables.add(new Arrow(p1,p2));
                    ((Arrow)drawables.getLast()).setcolor(c);
                    line_map.put(makelinelist(p1,p2),(Arrow) drawables.getLast());


                }

            }));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
    LinkedList<Integer> makepointlist(Studio.Point p)
    {
        LinkedList<Integer> li=new LinkedList<>();
        li.add(p.x);
        li.add(p.y);
       return li;
    }
    void drawpoint(Studio.Point point)
    {
        try {
            events.put(new Event(0, new Runnable() {
                @Override
                public void run() {
                    drawables.add(new SolidPoint(point));

                    pointsmap.put(makepointlist(point),(SolidPoint)drawables.getLast());
                }
            }));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    void changepointcolor(Studio.Point p,Color c)
    {
        try {
            events.put(new Event(0, new Runnable() {
                @Override
                public void run() {


                   SolidPoint pi= pointsmap.get(makepointlist(p));
                   pi.mycolor=c;
                }
            }));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }
    void changelinecolor(Studio.Point p1, Studio.Point p2,Color  c)
    {


        try {
            events.put(new Event(defaulttime, new Runnable() {
                @Override
                public void run() {
                    Arrow arrow = line_map.get(makelinelist(p1, p2));
                    arrow.mycolor = c;
                }
            }));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    void delet_line(Studio.Point p1,Studio.Point p2)
    {

        try {
            events.put(new Event(defaulttime, new Runnable() {
                @Override
                public void run()
                {


                   Arrow arrow = line_map.remove(makelinelist(p1,p2));
                   drawables.remove(arrow);

                }

            }));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
    void deletcircle(int r,Studio.Point p)  {

        try {
            events.put(new Event(defaulttime, new Runnable() {
                @Override
                public void run() {


                   Circle c= circleSerial_map.remove(makecirclelist(p,r));
                   drawables.remove(c);


                }
            }));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        removeAll();

        for(int i=0;i<drawables.size();i++) {
            drawables.get(i).draw(g);




        }

    }

    void start()
    {

        try {

            Event event=events.take();
            event.run.run();
            repaint();
            Timer t=new Timer(event.time,this);
            t.setRepeats(false);
            t.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }






}
