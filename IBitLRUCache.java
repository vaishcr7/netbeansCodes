package ibitlrucache;

import java.util.ArrayDeque;
import java.util.HashMap;

public class IBitLRUCache {

  
    public static void main(String[] args) {
        Lrucache cache = new Lrucache(3);
        cache.set(1,1);
        cache.set(2,2);
        cache.set(3,3);
        cache.set(4,4);
        System.out.print(cache.get(4)+"  ,");
        System.out.print(cache.get(3)+"  ,");
        System.out.print(cache.get(2)+"  ,");
        System.out.print(cache.get(1)+"  ,");
        cache.set(5,5);
        System.out.print(cache.get(1)+"  ,");
        System.out.print(cache.get(2)+"  ,");
        System.out.print(cache.get(3)+"  ,");
        System.out.print(cache.get(4)+"  ,");
        System.out.print(cache.get(5)+"  ,");
//        System.out.println(cache.get(2));   //-1
//        cache.set(2, 6);    
//        System.out.println(cache.get(1));   //-1
//        cache.set(1, 5);    
//        cache.set(1, 2);    
//        System.out.println(cache.get(1));   // 2
//        System.out.println(cache.get(2));   // 6
    } 
    
}
class Lrucache
{
    int capacity;
    HashMap<Integer,Integer> mp;
    HashMap<Integer,Integer> c;
    HashMap<Integer,Integer> d;
    int t;
    public Lrucache(int capacity) {
        this.capacity = capacity;
        mp=new HashMap<>(capacity);
        c=new HashMap<>();
        d=new HashMap<>();
        t=0;
    }
    public void set(int key,int val)
    {
//        System.out.println("\n set method called for key: "+key+" and value: "+val+"\n");
        if(mp.size()==capacity && !mp.containsKey(key))
        {
              int z=c.get(t-capacity+1);
//              System.out.println("key to remove: "+z);
              d.remove(z);
              c.remove(t-capacity+1);
              mp.remove(z);
//              System.out.println("new mp= "+mp);
        }
        else if(mp.size()==capacity && mp.containsKey(key))
        {
                int dz=d.get(key);
                d.remove(key);
                c.remove(dz);
        }
//        System.out.println("INITIAL mp= "+mp+" and c= "+c+" and d= "+d);
        t+=1;
        mp.put(key,val);
        c.put(t,key);
        d.put(key, t);
//        System.out.println("current mp= "+mp+" and c= "+c+" and d= "+d);
    }
    public int get(int key)
    {
//        System.out.println("\n get method called for key: "+key+"\n");
        if(mp.containsKey(key))
        {
            int dz=d.get(key);
            d.remove(key);
            c.remove(dz);
            t += 1;
            c.put(t, key);
            d.put(key, t);
            return mp.get(key);
        }
        return -1;    
    }
}
