package ibitpndrprtbcktrck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class IBitPndrPrtBcktrck {

    public static void main(String[] args) {
        System.out.println(makePts("geeksskeeg"));
    }
    public static boolean isPalin(String s)
    {
        int i=0;
        int j=s.length()-1;
        if(s.length()<=1)
            return true;
        while(i<=j)
        {
            if(s.charAt(i)!=s.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }
    public static ArrayList<ArrayList<String>> makePts(String a)
    {
        ArrayList<ArrayList<String>> ans=new ArrayList<>();
        if(isPalin(a))
        {
            ArrayList<String> p=new ArrayList<>();
            p.add(a);
            ans.add(new ArrayList<>(p));
        }
        int mid=a.length()/2;
        if(a.length()%2==0)
            mid=(a.length()-1)/2;
        Map<String,Boolean> mp=new HashMap<>();
        WeirdObj ob=new WeirdObj(ans, mp);
        for (int i = 1; i <=mid; i++) {
            ob=retPsFrCurrI(i, a, ob);
        }
        System.out.println(ob.al);
        return ans;
    }
    public static WeirdObj retPsFrCurrI(int i,String a,WeirdObj ob)
    {
        if(a.length()==0)
            return ob;
        String sub1=a.substring(0,a.length()-i);
        String sub1rest=a.substring(a.length()-i);
        String sub2=a.substring(i);
        String sub2rest=a.substring(0,i);
        if (!ob.mp.containsKey(sub1)) {
//            System.out.println("sub1= "+sub1);
            ob.mp.put(sub1, isPalin(sub1));
        }
        if (!ob.mp.containsKey(sub1rest)) {
//            System.out.println("sub1rest= "+sub1rest);
            ob.mp.put(sub1rest, isPalin(sub1rest));
        }
        if (!ob.mp.containsKey(sub2)) {
//            System.out.println("sub2= "+sub2);
            ob.mp.put(sub2, isPalin(sub2));
        }
        if (!ob.mp.containsKey(sub2rest)) {
//            System.out.println("sub2rest= "+sub2rest);
            ob.mp.put(sub2rest,isPalin(sub2rest));
        }
        System.out.println("mp= "+ob.mp);
         if (ob.mp.get(sub1) && ob.mp.get(sub1rest)) {
            ArrayList<String> p = new ArrayList<>();
            p.add(sub1);
            p.add(sub1rest);
            ob.al.add(new ArrayList<>(p));
            ob = retPsFrCurrI(i, sub1, ob);
            ob = retPsFrCurrI(i, sub1rest, ob);
        }
        if (ob.mp.get(sub2) && ob.mp.get(sub2rest)) {
            ArrayList<String> p = new ArrayList<>();
            p.add(sub1);
            p.add(sub1rest);
            ob.al.add(new ArrayList<>(p));
            ob = retPsFrCurrI(i, sub2, ob);
            ob = retPsFrCurrI(i, sub2rest, ob);
        }
        return ob;
    }
    
    
}
class WeirdObj
{
    ArrayList<ArrayList<String>> al;
    Map<String,Boolean> mp;

    public WeirdObj(ArrayList<ArrayList<String>> al, Map<String, Boolean> mp) {
        this.al = al;
        this.mp = mp;
    }    
}
