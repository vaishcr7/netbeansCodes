package ibitbktrcksubset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class IBitBktrckSUbset {
    public static void main(String[] args) {
        ArrayList<Integer> al=new ArrayList<>();
        al.add(1);
        al.add(2);
        al.add(3);
        al.add(6);
        al.add(8);
        System.out.println(subsets(al));
    }
    public static ArrayList<ArrayList<Integer>> subsets(ArrayList<Integer> al) {
        SortedSet<Set<Integer>> ss=new TreeSet<>();
        Map<Integer,LinkedHashSet<LinkedHashSet<Integer>>> mp=generateMap(al);
        Set<Entry<Integer,LinkedHashSet<LinkedHashSet<Integer>>>> st=mp.entrySet();
        Iterator it=st.iterator();
        while(it.hasNext())
        {
            Map.Entry<Integer,LinkedHashSet<LinkedHashSet<Integer>>> entry=(Map.Entry<Integer,LinkedHashSet<LinkedHashSet<Integer>>>)it.next();
            Iterator it1=entry.getValue().iterator();
            while(it1.hasNext())
            {
                LinkedHashSet<Integer> p=(LinkedHashSet<Integer>)it.next();
                ss.add(new LinkedHashSet<>(p));
            }
        }
        it=ss.iterator();
        ArrayList<ArrayList<Integer>> ans=new ArrayList<>();
        while(it.hasNext())
        {
            ArrayList<Integer> ap=new ArrayList<>();
            Iterator it1=((Set<Integer>)it.next()).iterator();
            while(it1.hasNext())
            {
                ap.add((int)it1.next());
            }
            ans.add(new ArrayList<>(ap));
        }
        return ans;
    }
    public static Map<Integer,LinkedHashSet<LinkedHashSet<Integer>>> generateMap(ArrayList<Integer> al)
    {
        Map<Integer,LinkedHashSet<LinkedHashSet<Integer>>> mp=new HashMap<>();
        for (int i = al.size()-1; i >=0; i--) {
            LinkedHashSet<Integer> lt=new LinkedHashSet<>();
            lt.add(al.get(i));
            LinkedHashSet<LinkedHashSet<Integer>> p=new LinkedHashSet<>();
            p.add(lt);
            mp.put(al.get(i),new LinkedHashSet<>(p));
            for (int j = i+1; j < al.size()-1; j++) {
                LinkedHashSet<LinkedHashSet<Integer>> temp=mp.get(al.get(j));
                Iterator it=temp.iterator();
                while(it.hasNext())
                {                    
                      LinkedHashSet<Integer> g=(LinkedHashSet<Integer>)it.next();
                      LinkedHashSet<Integer> t=lt;
                      Iterator it1=g.iterator();
                      while(it1.hasNext())
                      {
                          t.add((int)it1.next());
                      }
                      LinkedHashSet<LinkedHashSet<Integer>> k=mp.get(al.get(i));
                      k.add(t);
                      LinkedHashSet<LinkedHashSet<Integer>> kk=new LinkedHashSet<>(k);
                      mp.put(al.get(i),kk);
                      lt=t;
                }
            }
        }
        return mp;
    }
}
