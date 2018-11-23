package ibitbktrcksubset;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class IBitBktrckSUbset {
    public static void main(String[] args) {
        ArrayList<Integer> al=new ArrayList<>();
        al.add(28);
        al.add(20);
        al.add(12);
        al.add(9);
        al.add(4);
        System.out.println(subsets(al));
    }
    public static ArrayList<ArrayList<Integer>> subsets(ArrayList<Integer> al) {
        ArrayList<ArrayList<Integer>> ans=new ArrayList<>();
        Collections.sort(al);
        System.out.println("al= "+al);
        if(al.isEmpty())
            return ans;
        ans.add(new ArrayList<>());
        Map<Integer,LinkedHashSet<LinkedHashSet<Integer>>> mp=generateMap(al);
//        System.out.println("mp= "+mp);
        Set<Entry<Integer,LinkedHashSet<LinkedHashSet<Integer>>>> st=mp.entrySet();
        Iterator it=st.iterator();
        while(it.hasNext())
        {
            Map.Entry<Integer,LinkedHashSet<LinkedHashSet<Integer>>> entry=(Map.Entry<Integer,LinkedHashSet<LinkedHashSet<Integer>>>)it.next();
            LinkedHashSet<LinkedHashSet<Integer>> p=(LinkedHashSet<LinkedHashSet<Integer>>)entry.getValue();
            Iterator it1=p.iterator();
            while(it1.hasNext())
            {
                HashSet<Integer> q =(HashSet<Integer>)it1.next();
//                System.out.println("q= "+q);
                ArrayList<Integer> ap=new ArrayList<>();
                ap.addAll(q);
                ans.add(new ArrayList<>(ap));
            }
        }
        Collections.sort(ans,new Comparator<ArrayList<Integer>>(){
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                if(o1.isEmpty())
                {
                    if(o2.isEmpty())
                        return -1;
                    else
                        return 1;
                }
                else if(!o1.isEmpty() && o2.isEmpty())
                {
                        return 1;
                }
                else
                {
                    for (int i = 0; i < Math.min(o1.size(), o2.size()); i++) 
                    {
                        if(o1.get(i)<o2.get(i))
                            return -1;
                        else if(o1.get(i)>o2.get(i))
                            return 1;
                    }
                    if(o1.size()>o2.size())
                        return 1;
                    else
                        return -1;
                }
            }
            
        });
        return ans;
    }
    public static Map<Integer,LinkedHashSet<LinkedHashSet<Integer>>> generateMap(ArrayList<Integer> al)
    {
        Map<Integer,LinkedHashSet<LinkedHashSet<Integer>>> mp=new HashMap<>();
        for (int i = al.size()-1; i >=0; i--) {
//            System.out.println("curent elem= "+al.get(i));
            LinkedHashSet<Integer> lt=new LinkedHashSet<>();
            lt.add(al.get(i));
//            System.out.println("lt= "+lt);
            LinkedHashSet<LinkedHashSet<Integer>> p=new LinkedHashSet<>();
            p.add(lt);
//            System.out.println("p= "+p);
            mp.put(al.get(i),new LinkedHashSet<>(p));
//            System.out.println("initial mp for it is "+mp.get(al.get(i)));
            for (int j = i+1; j < al.size(); j++) {
                LinkedHashSet<LinkedHashSet<Integer>> temp=mp.get(al.get(j));
//                System.out.println("using "+al.get(j)+"'s lists: "+mp.get(al.get(j)));
                Iterator it=temp.iterator();
                while(it.hasNext())
                {                   
//                    System.out.println("ran");
                      LinkedHashSet<Integer> g=(LinkedHashSet<Integer>)it.next();
                      LinkedHashSet<Integer> t=new LinkedHashSet<>(lt);
                      Iterator it1=g.iterator();
                      while(it1.hasNext())
                      {
                          t.add((int)it1.next());
                      }
//                      System.out.println("adding "+t);
                      mp.get(al.get(i)).add(new LinkedHashSet<>(t));
                }
            }
//            System.out.println("set of lists for "+al.get(i)+" are: "+mp.get(al.get(i)));
        }
        return mp;
    }
}