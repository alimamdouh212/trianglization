package com.company;

public class Dual_edge {
    Dual_edge onext;
    private Dual_edge oprev;
    Phyical_edge myphiscal;
    Dual_edge(Phyical_edge phyicalEdge)
    {
        myphiscal=phyicalEdge;

    }
    Half_edge getroot()
    {
       return myphiscal.getprimalroot(this);
    }
    Dual_edge getsyn()
    {
        return myphiscal.getDuaSyn(this);
    }
    void setnext(Dual_edge e)
    {
        onext=e;
        //e.oprev=this;
    }
    Dual_edge getOnext()
    {return onext;}

}
