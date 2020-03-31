package com.company;

import java.util.HashMap;

class Half_edge {
    Half_edge onext;


    Phyical_edge myphsical;
    Half_edge(Studio.Point p,Phyical_edge phical)
    {
        myphsical=phical;

        onext=this;
    }
   Half_edge getsyn()
   {
       return myphsical.getprimalsyn(this);

   }
   Half_edge dprev()
   {
       return myphsical.dprev(this);
   }

   Dual_edge getroot()
   {

       return myphsical.getdualroot(this);
   }
    static void  print(Half_edge e11)
    {
        System.out.println(e11.getorgin().x+" "+e11.getorgin().y+" "+ "    "+e11.getdes().x+" "+e11.getdes().y);
    }
   Half_edge Onextshow()
   {   myphsical.onextviistreq(this);
       return onext;}
    void setnext(Half_edge e)
    {
        onext=e;

    }
    void splice(Half_edge e)
    {

        Dual_edge alpha=onext.getroot();
        Dual_edge beta=e.onext.getroot();

        Half_edge edgetemp=onext;
        setnext(e.onext);
        e.setnext(edgetemp);

        Dual_edge dualtemp=alpha.getOnext();
        Dual_edge dual_edge2=beta.getOnext();
        alpha.setnext(beta.getOnext());
        beta.setnext(dualtemp);




    }
     Half_edge get_obrev()
    {
        return myphsical.getprimalobrev(this);
    }
    Half_edge lprev()
    {
        return onext.getsyn();
    }
    Studio.Point getorgin()
    {
        return myphsical.getorgin(this);
    }
    Studio.Point getdes()
    {
        return myphsical.getdist(this);
    }
    Half_edge lnext()
    {
        return myphsical.lnextprimal(this);
    }
    Half_edge lnextshow()
    {
        return myphsical.lnextshow(this);
    }

    void setorg(Studio.Point p)

    {
        myphsical.setorgin(this,p);
    }





}
