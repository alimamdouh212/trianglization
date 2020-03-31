package com.company;

public class Phyical_edge {
    static boolean ready=false;
    static Studio mystudio;



    Studio.Point p1;
    Studio.Point p2;
    Half_edge half_edge1;
    Half_edge half_edge2;
    Dual_edge dual_edge1;
    Dual_edge dual_edge2;

    static void getready(Studio studio)
    {
        mystudio=studio;
        ready=true;
    }

    private Phyical_edge(Studio.Point p1,Studio.Point p2)
    {
        this.p1=p1;
        this.p2=p2;
        half_edge1=new Half_edge(p1,this);
        half_edge2=new Half_edge(p2,this);
        dual_edge1=new Dual_edge(this);

        dual_edge2=new Dual_edge(this);
        dual_edge1.onext=dual_edge2;
        dual_edge2.onext=dual_edge1;

    }
    Half_edge getprimalobrev(Half_edge e)  {
        int x=0;

      /*try {


           throw new Exception();
       } catch (Exception ex)
       {
           ex.printStackTrace();
       }*/
        Dual_edge d=getdualroot(e);
        Dual_edge d3=d.onext;
        Dual_edge d2=d.getOnext();
        Half_edge returned= d2.getroot();
        //visit(returned);
        return returned;
    }
    Half_edge onextviistreq(Half_edge e)
    {
        Half_edge returned=e.onext;
        //visit(returned);
        return returned;
    }

    void visit(Half_edge e)
    {
        mystudio.visitedge(e.getorgin(),e.getdes());
    }
    Half_edge lnextprimal(Half_edge e)
    {

        Dual_edge t1=getdualroot(e).getsyn();
        Dual_edge t2=t1.getOnext();
        Half_edge next=t2.getroot();


        return next;
    }
    Half_edge lnextshow(Half_edge e)
    {

        Half_edge returned=lnextprimal(e);
        visit(returned);
        return returned;
    }
    static Half_edge  make_edge(Studio.Point p1,Studio.Point p2)
    {

        if(!ready)
            return null;
        mystudio.newedge(p1,p2);
        mystudio.newedge(p2,p1);
        return new Phyical_edge(p1,p2).half_edge1;

    }
    Half_edge getprimalroot(Dual_edge e)

    {
        if(e==dual_edge1)
        {
            return half_edge2;
        }
        else if(e==dual_edge2)
        {return half_edge1;}
        else {return null;}
    }
    Dual_edge getdualroot(Half_edge e)

    {
        if(e==half_edge1)
            return dual_edge1;
        else if(e==half_edge2)
            return dual_edge2;
        else return null;
    }
    Dual_edge getDuaSyn( Dual_edge e)
    {
        if(e==dual_edge2)
            return dual_edge1;
        else if(e==dual_edge1)
            return dual_edge2;
        else return null;
    }
    Half_edge getprimalsyn(Half_edge e)
    {
        if(e==half_edge1)
            return half_edge2;
        else if(e==half_edge2)
            return half_edge1;
        else return null;
    }
    Studio.Point getorgin(Half_edge e)
    {
        if(e==half_edge1)
            return p1;
        else if(e==half_edge2)
            return p2;
        else return null;
    }
    void setorgin(Half_edge e,Studio.Point p)
    {
        if(e==half_edge1)
             p1=p;
        else if(e==half_edge2)
            p2=p;

    }
    Studio.Point getdist(Half_edge e)
    {
        if(e==half_edge1)
            return p2;
        else if(e==half_edge2)
            return p1;
        else return null;
    }

    static Half_edge conncect(Half_edge a,Half_edge b)
    {


        Half_edge e=Phyical_edge.make_edge(a.getdes(),b.getorgin());
        a.lnext().splice(e);
        e.getsyn().splice(b);
        return e;

    }
    static void delete(Half_edge e)
    {

        e.splice(e.get_obrev());
        e.getsyn().splice(e.getsyn().get_obrev());
        mystudio.deletarrow(e);
        mystudio.deletarrow(e.getsyn());
        e.myphsical.half_edge1=e.myphsical.half_edge2=null;
        e.myphsical.dual_edge2=e.myphsical.dual_edge1=null;
    }
    Half_edge dprev(Half_edge e)
    {
        return getdualroot(e).getsyn().onext.getroot().getsyn();
    }
    Half_edge dprevshow(Half_edge e)
    {
       Half_edge returned =getdualroot(e).getsyn().onext.getroot().getsyn();
       visit(returned);
       return returned;
    }
    static Half_edge swap(Half_edge e)
    {
       Half_edge a=e.get_obrev();
       Half_edge b=e.getsyn().get_obrev();

       /*e.getsyn().splice(b);
        e.splice(a.lnext());*/
        delete(e);
        e=make_edge(a.getdes(),b.getdes());

        e.splice(a.lnext());
        e.getsyn().splice(b.lnext());
        e.getsyn().setorg(b.getsyn().getorgin());
        return e;

    }
    static void  print(Half_edge e11)
    {
        System.out.println(e11.getorgin().x+" "+e11.getorgin().y+" "+ "    "+e11.getdes().x+" "+e11.getdes().y);
    }
}
