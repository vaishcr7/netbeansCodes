package ibitwndstr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class IBitWndStr {
    public static void main(String[] args) {
        System.out.println(wstr("adobecodba","abc"));
    }
    public static String wstr(String a,String t)
    {
        StringBuilder ans=new StringBuilder();
        Map<Character,ArrayList<Integer>> mp=new HashMap<>();
        for (int i = 0; i < a.length(); i++) {
            ArrayList<Integer> p;
            if(!mp.containsKey(a.charAt(i)))
                p=new ArrayList<>();
            else
                p=new ArrayList<>(mp.get(a.charAt(i)));
            p.add(i);
            mp.put(a.charAt(i),p);
        }
        ArrayList<ArrayList<Integer>> al=new ArrayList<>();
        for (int i = 0; i < t.length(); i++) {
            if(!mp.containsKey(t.charAt(i)))
                return "";
            al.add(mp.get(t.charAt(i)));
        }
        ArrayList<ArrayList<Integer>> z=new ArrayList<>();
        for (int i = 0; i <al.get(al.size()-1).size(); i++) {
            ArrayList<Integer> np=new ArrayList<>();
            np.add(al.get(al.size()-1).get(i));
            z.add(new ArrayList<>(np));
        }
        for (int i = al.size()-2; i >=0; i--) {
            z=new ArrayList<>(mgsrt(al.get(i), z));
        }
        System.out.println("z= "+z);
        int stPos=z.get(0).get(0);
        int endPos=z.get(0).get(z.get(0).size()-1);
        for (int i = 1; i < z.size(); i++) {
            int x=z.get(i).get(z.get(i).size()-1);
            int y=z.get(i).get(0);
            if((x-y)<(endPos-stPos))
            {
                stPos=y;
                endPos=x;
            }
        }
        return a.substring(stPos, endPos+1);
    }
    public static ArrayList<ArrayList<Integer>> mgsrt(ArrayList<Integer> a,ArrayList<ArrayList<Integer>> b)
    {
        ArrayList<ArrayList<Integer>> g=new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            ArrayList<Integer> f=new ArrayList<>();
            f.add(a.get(i));
            for (int j = 0; j < b.size(); j++) {
                ArrayList<Integer> temp=new ArrayList<>(f);
                temp.addAll(b.get(j));
                Collections.sort(temp);
                g.add(new ArrayList<>(temp));
            }
        }
        return g;
    }
    
}
