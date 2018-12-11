package ibitlrucache;

import java.util.HashMap;

public class IBitLRUCache {

  
    public static void main(String[] args) {
        Lrucache cache = new Lrucache(3);// 2 /* capacity */ );

        cache.set(1, 1);
        cache.set(2, 2);
        System.out.println(cache.get(1));       // returns 1
        cache.set(3, 3);    
        System.out.println(cache.get(2));       // returns 2
        cache.set(4, 4);    // evicts key 1
        System.out.println(cache.get(1));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3
        System.out.println(cache.get(4));       // returns 4
    }
    
}
class Lrucache
{
    int capacity;
    HashMap<Integer,Integer> mp;
    long t;
    HashMap<Integer,Long> arval;
    HashMap<Long,Integer> dept;
    public Lrucache(int capacity) {
        this.capacity = capacity;
        mp=new HashMap<>(capacity);
        arval=new HashMap<>();
        dept=new HashMap<>();
        t=0;
    }
    public void set(int key,int val)
    {
        if(mp.size()==capacity)
        {
            int f=dept.get(t-capacity+1);
//            System.out.print("f= "+f);
            mp.remove(f);
        }
        mp.put(key,val);
        t += 1;
        arval.put(key,t);
        dept.put(t, key);
        dept.remove(t-capacity);
//        System.out.println(" , "+t+" and arval= "+arval+", mp= "+mp+"   ");
    }
    public int get(int key)
    {
        if(mp.containsKey(key))
        {
            t+=1;
            arval.put(key,t);
            dept.put(t,key);
            dept.remove(t-capacity);
//            System.out.print("mp= "+mp+"dept= "+dept+"arval= "+arval+"    ");
            return mp.get(key);
        }
        return -1;    
    }
}
