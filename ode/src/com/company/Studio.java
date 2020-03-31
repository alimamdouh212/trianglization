package com.company;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

import static java.awt.Color.blue;
import static java.awt.Color.white;

public class Studio {
    Half_edge edge;
    final static double eps = 0.001;
    static Studio thestudio;
    static Sheet myshett;

    private Studio(Sheet sheet) {
        myshett = sheet;

    }

    void newedge(Point p1, Point p2) {
        myshett.make_line(p1, p2, Color.green);
        myshett.changelinecolor(p1, p2, Color.black);
    }

    static void  visitedge(Point p1, Point p2) {
       // myshett.changelinecolor(p1, p2, Color.red);

       // myshett.changelinecolor(p1, p2, Color.black);

    }

    void travel(LinkedList<Point> list) {
        Point p1 = list.getLast();
        Point p2 = list.getLast();
        if (list.size() == 0) {
            visitedge(p1, p2);
            return;
        }
        myshett.changelinecolor(p1, p2, Color.cyan);
        travel(list);
        myshett.changelinecolor(p1, p2, Color.black);


    }


    static Studio getstudio() {
        if (thestudio == null) {
            myshett = new Sheet();

            thestudio = new Studio(myshett);
            Phyical_edge.getready(thestudio);
        }
        return thestudio;

    }

    static JFrame f = new JFrame("");

    static void print(Half_edge e11) {
        System.out.println(e11.getorgin().x + " " + e11.getorgin().y + " " + "    " + e11.getdes().x + " " + e11.getdes().y);
    }

    static void rotate(Half_edge e) {

        Point pstart = e.getorgin();
        visitedge(e.getorgin(),e.getdes());
        while (e.getdes() != pstart) {
            e = e.lnext();
            visitedge(e.getorgin(),e.getdes());
        }



    }

    static void start() {

        LinkedList<Point> list = new LinkedList<>();
        list.add(new Point(100, 50));
        list.add(new Point(1600, 1800));
        list.add(new Point(20,  1800));
        list.add(new Point(400,1000));
        list.add(new Point(400,1200));
        list.add(new Point(400, 500));
        list.add(new Point(600, 1500));

        incremental_algortjim(list);

        /*Half_edge e1 = Phyical_edge.make_edge(list.get(0), list.get(1));
        Half_edge e2 = Phyical_edge.make_edge(list.get(0), list.get(2));
        e1.splice(e2);
        Half_edge e11 = e1.getroot().getroot();


        if (e1.getsyn().onext == e2)
            System.out.println("");
        if (e2.onext == e1.getsyn())
            System.out.println("");
        Half_edge e3 = Phyical_edge.conncect(e1, e2.getsyn());
        rotate(e1);
        rotate(e1.getsyn());

       Half_edge e4 = Phyical_edge.make_edge(list.get(1), list.get(3));
        e4.splice(e3);
        Phyical_edge.conncect(e3, e4.getsyn());
        rotate(e3);
        rotate(e3.getsyn());

        rotate(e1);
        rotate(e1.getsyn());
        e3=Phyical_edge.swap(e3);


        Half_edge e5=Phyical_edge.make_edge(list.get(1),list.get(4));
        e5.splice(e1.getsyn());
        Half_edge e6=Phyical_edge.conncect(e5,e1);
        Half_edge e7=Phyical_edge.make_edge(list.get(3),list.get(5));
        e7.splice(e3.onext);
        Half_edge e8=Phyical_edge.conncect(e7,e3.onext.getsyn());
        Half_edge e9=Phyical_edge.conncect(e8.getsyn(),e5.getsyn());
        rotate(e9);
        rotate(e9.getsyn());*/










        f.add(myshett);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.setSize(1000, 1800);
        f.setVisible(true);
        myshett.start();


    }


    void deletarrow(Half_edge e)
    {
        myshett.delet_line(e.getorgin(),e.getdes());
    }
    private static double[] helper(Point p)
    {
        return new double[]{(double)p.x,(double)p.y,(double)(p.x*p.x+p.y*p.y),1.0};
    }
    static void drawcircle(Point p,int r)
    {
        myshett.make_cicrle(r,p,Color.cyan);

    }
    static void changecirclorclor(Point p,int r,Color c)
    {

        myshett.change_circlecolor(r,p,c);
    }
    static boolean incircle(Point p1,Point p2,Point p3,Point p4)
    {
        try {


        circlecoordinate cc=circleFromPoints(p1,p2,p3);



       drawcircle(cc.p,cc.r);

        double x[][]={
                helper(p1),helper(p2),helper(p3),helper(p4)

                   };
        double resut=MatrixOperations.matrixDeterminant(x);
        if(resut>0)
        {

            changecirclorclor(cc.p,cc.r,Color.red);
            myshett.deletcircle(cc.r,cc.p);
            return true;}
        else {
            changecirclorclor(cc.p,cc.r,Color.green);
            myshett.deletcircle(cc.r,cc.p);
            return false;

        }}
        catch (Exception e)
        {
            return false;
        }
    }


    Half_edge e1;
     static Half_edge firstrectangle(Point p1,Point p2,Point p3)
    {

        Half_edge e1=Phyical_edge.make_edge(p1,p2);
        Half_edge e2=Phyical_edge.make_edge(p1,p3);
        e1.splice(e2);
        Half_edge e3=Phyical_edge.conncect(e1,e2.getsyn());

        return e1.getsyn();
    }

    static void incremental_algortjim(LinkedList<Point> list)
    {
            Point p1=list.removeFirst();
            Point p2=list.removeFirst();
            Point p3=list.removeFirst();
            Half_edge e1= firstrectangle(p1,p2,p3);
            int c=0;
            int cf=2;
           while (!list.isEmpty()) {
               Point pcurrent=list.removeFirst();
               myshett.drawpoint(pcurrent);

               Half_edge ec = find(pcurrent,e1);
               visitedge(ec.getorgin(),ec.getdes());
               print(ec);
               hanelpoint(pcurrent,ec);
                c++;
           }


    }
    static void  suspedge(Half_edge e)
    {
        myshett.changelinecolor(e.getorgin(),e.getdes(),Color.cyan);
    }
    static void unsupes(Half_edge e)
    {
        myshett.changelinecolor(e.getorgin(),e.getdes(),Color.black);
    }
    static void hanelpoint(Point p,Half_edge e1)
    {

        Half_edge base=Phyical_edge.make_edge(e1.getorgin(),p);
        base.splice(e1);
        Point start= e1.getorgin();
        Stack<Half_edge> stack=new Stack<>();
        do{
            stack.push(e1);
            suspedge(e1);
            print(base);
            visitedge(e1.getorgin(),e1.getdes());
            base=Phyical_edge.conncect(e1,base.getsyn());
            print(base);
            e1=base.get_obrev();
            print(e1);
        }while ((e1.getdes().x!=start.x)||(e1.getdes().y!=start.y));

        e1=base.get_obrev();
        stack.push(e1);
        suspedge(e1);
        Half_edge t;
        myshett.changepointcolor(p,Color.cyan);
        while (true)
        {
            t=e1.get_obrev();
            if(stack.empty())
                print(e1);
            stack.pop();
            print(e1);
            print(t);
            if(Point.isright(e1,t.getdes()) &&!incircle(e1.getorgin(),t.getdes(),e1.getdes(),p))
            {

                e1=Phyical_edge.swap(e1);
                stack.push(e1.onext.getsyn());
                suspedge(e1.onext.getsyn());
                stack.push(e1.get_obrev());
                suspedge(e1.get_obrev());
                e1=e1.get_obrev();
            }else
            {
                unsupes(e1);
                if(e1.getorgin().x==start.x&&e1.getorgin().y==e1.getorgin().y)
            {
                myshett.changepointcolor(p,Color.black);

                return;
            } else
            {

                print(e1);
                e1=e1.onext;
                print(e1);
                e1=e1.lprev();
                print(e1);
            }}


        }

    }
    static Half_edge find(Point p,Half_edge e1)

    {
        Half_edge ec=e1;

        while (true) {
            print(e1);
            if(Point.onsegemnt(ec,p))
                return ec;
            if (Point.isright(ec, p)) {
                {  print(ec.getsyn());
                    ec=ec.getsyn();}
            } else if(!Point.isright(ec.onext,p))
            {

                print(ec.onext);
                ec=ec.onext;

                }
            else if(!Point.isright(ec.dprev(),p))
            {
                print(ec.dprev());
                ec=ec.dprev();
            }
            else {

                visitedge(ec.getorgin(), ec.getdes());
                print(ec);
                return ec;

            }

        }

    }
   static class Point {
       int x;
       int y;

       Point(int x, int y) {
           this.x = x;
           this.y = y;
       }
       static double findangel(double x,double y )
       {
           double angel=Math.atan2(y,x);
           if(0>angel)
               angel=Math.PI*2+angel;
           return angel;
       }
       static double dae(double e)
       {
           if(e>2*Math.PI)
               e-=2*Math.PI;
           return 360.0*e/(2*Math.PI);
       }
       public static int ccw(Point a, Point b, Point c) {
           // return a.x*b.y - a.y*b.x + a.y*c.x - a.x*c.y + b.x*c.y - b.y*c.x
           double angel1=findangel(b.x-a.x,-b.y+a.y);

           double angel2=findangel(c.x-b.x,-c.y+b.y);

           if((angel1==angel2)||(angel1+Math.PI==angel2)||(angel1==angel2+Math.PI))
               return 0;
           double d1=dae(angel1);
           double d2=dae(angel2);
           double a11=angel1+Math.PI;
           double d11=dae(a11);
           if(a11>2*Math.PI) {
               a11 -= 2*Math.PI;
               if(angel2>angel1||angel2<a11)
                   return 1;
               else return -1;
           } else {
               if(angel2>angel1&&angel2<a11)
                   return 1;
               else return -1;


           }


       }
       static boolean isleft(Half_edge  e,Point p)
       {
           return (ccw(e.getorgin(),e.getdes(),p)>0);
       }
       static boolean isright(Half_edge  e,Point p)
       {
           Boolean r=(ccw(e.getorgin(),e.getdes(),p)<0);
           return r;
       }
       static boolean onsegemnt(Half_edge e,Point p)
       {
           Point p1=e.getorgin();
           Point p2=e.getdes();
           if(p1.x<p2.x)
           {
               Point tewmp=p1;
               p1=p2;
               p2=tewmp;
           }
           if(p.x<p1.x)
               return false;

           double slop1=1.0*(p1.y-p.y)/(p1.x-p.x);
           double slop2=1.0*(p1.y-p2.y)/(p1.x-p2.x);
           if((slop1-slop2)*(slop1-slop2)<eps)
               return true;
           else return false;

       }

   }
    static final double TOL = 0.0000001;
    static  circlecoordinate circleFromPoints(Point p1,  Point p2,  Point p3) throws Exception
    {
        final double offset = Math.pow(p2.x,2) + Math.pow(p2.y,2);
        final double bc =   ( Math.pow(p1.x,2) + Math.pow(p1.y,2) - offset )/2.0;
        final double cd =   (offset - Math.pow(p3.x, 2) - Math.pow(p3.y, 2))/2.0;
        final double det =  (p1.x - p2.x) * (p2.y - p3.y) - (p2.x - p3.x)* (p1.y - p2.y);

        if (Math.abs(det) < TOL) { throw new Exception("hjkd"); }

        final double idet = 1/det;

        final double centerx =  (bc * (p2.y - p3.y) - cd * (p1.y - p2.y)) * idet;
        final double centery =  (cd * (p1.x - p2.x) - bc * (p2.x - p3.x)) * idet;
        final double radius =
                Math.sqrt( Math.pow(p2.x - centerx,2) + Math.pow(p2.y-centery,2));

        return new circlecoordinate(new Point((int)centerx,(int)centery),(int)radius);
    }
    static class circlecoordinate
    {
        Point p;
        int r;
        circlecoordinate(Point p,int r)
        {
            this.p=p;
            this.r=r;
        }


    }
}
