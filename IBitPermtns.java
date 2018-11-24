package ibitpermtns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class IBitPermtns {

    public static void main(String[] args) {
        ArrayList<Integer> al=new ArrayList<>();
        al.add(15);
        al.add(20);
        al.add(12);
        al.add(19);
        al.add(4);
        System.out.println(combs(al));
    }
    public static ArrayList<ArrayList<Integer>> combs(ArrayList<Integer>  al)
    {
        ArrayList<ArrayList<Integer>> ans=new ArrayList<>();
        ans.add(new ArrayList<>());
        if(al.isEmpty())
            return ans;
        ans.clear();
        ArrayList<Integer> lt=new ArrayList<>();
        lt.add(al.get(al.size()-1));
        ans.add(new ArrayList<>(lt));
        if(al.size()==1)
            return ans;
//        System.out.println("initial answer= "+ans);
        for (int i = al.size()-2; i >=0; i--) {
            ArrayList<ArrayList<Integer>> p=new ArrayList<>();
//            System.out.println("adding elem: "+al.get(i));
            for (ArrayList<Integer> a : ans) {
                for (int j = 0; j < a.size(); j++) {
                    ArrayList<Integer> tmp=new ArrayList<>(a);
                    tmp.add(j,al.get(i));
                    p.add(new ArrayList<>(tmp));
                }
                ArrayList<Integer> tmp=new ArrayList<>(a);
                tmp.add(a.size(),al.get(i));
                p.add(new ArrayList<>(tmp));
            }
//            System.out.println("p= "+p);
            ans.clear();
            ans.addAll(p);
        }
        Set<ArrayList<Integer>> st=new HashSet<>();
        ans.forEach((an) -> {
            st.add(an);
        });
        ans.clear();
        st.forEach((cnsmr)->{
            ans.add(cnsmr);
        });
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
}