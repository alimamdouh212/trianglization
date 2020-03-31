package com.company;

import java.lang.reflect.Array;
import java.util.*;

public class Serial_map<T,K> {
    int nolevels;
    HashMap<T,Serial_map<T,K>> hashMap;
    HashMap<T,K> hashMap0;
    Serial_map(int nolevels)
    {
        this.nolevels=nolevels;
        if(nolevels==1)
            hashMap0=new HashMap<>();
        else hashMap=new HashMap<>();

    }
    boolean empty()
    {
        if(nolevels==1)
        return hashMap0.isEmpty();
        else
        {
           return hashMap.isEmpty();
        }

    }
    K remove(LinkedList<T> l)
    {
        T firsr=(T)l.removeFirst();
        if(nolevels==1)
            return hashMap0.remove(firsr);
        else
        {
            Serial_map<T,K> son=hashMap.get(firsr);
            if(son==null)
                return null;
            K returned=son.remove(l);
            if(son.empty())
                hashMap.remove(firsr);
           return returned;


        }

    }
    void put(LinkedList<T> list,K item)
    {

        T firsr=(T)list.removeFirst();
        if(nolevels==1)
        {
            hashMap0.put(firsr,item);
            return;
        }

        Serial_map<T,K> son;
            if(hashMap.containsKey(firsr))
        {
          son =hashMap.get(firsr);


        } else
        {
            son=new Serial_map<>(nolevels-1);
            hashMap.put(firsr,son);

        }
        son.put(list,item);

    }
    K get(LinkedList<T> list)
    {

        T firsr=(T)list.removeFirst();
        if(nolevels==1)
        {
            return hashMap0.get(firsr);
        }
        else
        {
            Serial_map<T,K> son=hashMap.get(firsr);
            if(son!=null)
            {

                return son.get(list);

            } else
            {
               return null;

            }

        }
    }


}
