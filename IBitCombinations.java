package ibitcombinations;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class IBitCombinations {

    public static void main(String[] args) {
        ArrayList<Integer> al=new ArrayList<>();
        al.add(1);
        al.add(2);
        al.add(3);
        al.add(4);
        al.add(5);
        System.out.println(wrkfunc(al, 3));
    }
    public static ArrayList<ArrayList<Integer>> wrkfunc(ArrayList<Integer> al,int k)
    {
        ArrayList<ArrayList<Integer>> ans=new ArrayList<>();
        ArrayList<Integer> ltep=new ArrayList<>();
        Map<Integer,ArrayList<ArrayList<Integer>>> mp=new HashMap<>();
        if(al.isEmpty() || k>al.size() || k<=0)
        {
            ans.add(ltep);
            return ans;
        }  
        if(k==al.size())
        {
            ArrayList<Integer> lp=new ArrayList<>();
            for (int i = 0; i < al.size(); i++) {
                lp.add(al.get(i));
            }
            ArrayList<ArrayList<Integer>> pt=new ArrayList<>();
            pt.add(lp);
            return pt;
        }
        if(k==1)
        {
            ArrayList<ArrayList<Integer>> pt=new ArrayList<>();
            for (int i = 0; i < al.size(); i++) {
                ArrayList<Integer> lp=new ArrayList<>();
                lp.add(al.get(i));
                pt.add(lp);
            }
            return pt;
        }      
        for (int i = al.size()-k; i < al.size(); i++) {
            ltep.add(al.get(i));
        }
        ans.add(ltep);
        ArrayList<ArrayList<Integer>> tmp=new ArrayList<>();
        tmp.add(new ArrayList<>(ltep));
        mp.put(al.get(al.size()-k),tmp);
        for (int i =al.size()-k-1; i >=0; i--) {
            ArrayList<ArrayList<Integer>> temp=mp.get(al.get(i+1));
//            System.out.println("temp= "+temp);
            ArrayList<ArrayList<Integer>> r=new ArrayList<>(mfunc(temp, al.get(i), k));
            ans.addAll(r);
            mp.put(al.get(i),r);
        }
        Set<ArrayList<Integer>> st=new TreeSet<>(new Comparator<ArrayList<Integer>>(){
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
        Set<ArrayList<Integer>> stremdup=new HashSet<>();
        stremdup.addAll(ans);
        st.addAll(stremdup);        
        ans.clear();
        ans.addAll(st);
//        System.out.println("initial ans= "+ans);
        ArrayList<ArrayList<Integer>> finAns=new ArrayList<>();
        for (ArrayList<Integer> ag : ans) {
//            System.out.println("ag= "+ag+" and k= "+k);
            if(ag.size()==k)
                finAns.add(ag);
        }
        return finAns;
    }
    public static ArrayList<ArrayList<Integer>> mfunc(ArrayList<ArrayList<Integer>> lists,int numToAppend,int k)
    {
        ArrayList<ArrayList<Integer>> newlists=new ArrayList<>();        
        for(ArrayList<Integer> al:lists)
        {
            int j=k-1;
            ArrayList<Integer> tp=new ArrayList<>();
            tp.add(numToAppend);
            while(j>=0)
            {
                ArrayList<Integer> kp=new ArrayList<>(tp);
                for (int i = 0; i < al.size(); i++) {
                    if(i!=j)
                        kp.add(al.get(i));
                }
                j-=1;
                newlists.add(new ArrayList<>(kp));
            }
        }
        return newlists;
    }
}
