package ibitpndrprtbcktrck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class IBitPndrPrtBcktrck {

    public static void main(String[] args) {
        ArrayList<ArrayList<String>> k=makeSets("");
        System.out.println(k);
//        for (ArrayList<String> at : k) {
//            System.out.println(at);
//        }
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
    public static Map<Integer,ArrayList<String>> makePts(String a)
    {
        Map<Integer,ArrayList<String>> mp=new HashMap<>();
        for (int i = 0; i < a.length(); i++) {
            String str=a.substring(i);
//            System.out.println("\n***************");
//            System.out.println("str= "+str);
            for (int ik = i+1; ik <=str.length()+i; ik++) {
                String temp=str.substring(0,ik-i);
//                System.out.println("considering "+temp);
                if(isPalin(temp))
                {
                    ArrayList<String> tp;
                    if(!mp.containsKey(i))
                    {
                        tp=new ArrayList<>();
                    }
                    else
                    {
                        tp=new ArrayList<>(mp.get(i));
                    }
                    tp.add(temp);
                    mp.put(i,new ArrayList<>(tp));
//                    System.out.println("therefore "+mp.get(i));
                }
            }
//                System.out.println("***************\n");
        }
//        System.out.println(mp);
        return mp;
    }       
    public static ArrayList<ArrayList<String>> makeSets(String a)
    {
        if(a.isEmpty())
            return new ArrayList<>();
        Map<Integer,ArrayList<String>> mp=new HashMap<>(makePts(a));
        Set<ArrayList<String>> ans=new HashSet<>();
        Set<Integer> st=new TreeSet<>(mp.keySet());
        ArrayList<Integer> keys=new ArrayList<>(st);
        Collections.sort(keys);
        ArrayList<ArrayList<String>> t=new ArrayList<>();
        t=new ArrayList<>(formGps(mp, keys, t, a));
        Set<ArrayList<String>> ansSet=new HashSet<>(t);
//        System.out.println(ansSet);
        t.clear();
        t=new ArrayList<>(ansSet);
        Collections.sort(t,new Comparator<ArrayList<String>>(){
            @Override
            public int compare(ArrayList<String> t, ArrayList<String> t1) {
               int minLen=Math.min(t.size(), t1.size());
                for (int i = 0; i < minLen; i++) {
                    if(t.get(i).length()<t1.get(i).length())
                        return -1;
                    else if(t.get(i).length()>t1.get(i).length())
                        return 1;
                }
                return 0;
            }            
        });
        return t;
    }
    public static ArrayList<ArrayList<String>> formGps(Map<Integer,ArrayList<String>> mp,ArrayList<Integer> keys,ArrayList<ArrayList<String>> ans,String a)
    {
        if(keys.isEmpty())
            return ans;
        int currKey=keys.remove(0);
        ArrayList<String> p=mp.get(currKey);
        if(ans.isEmpty())
        {
            for (String s : p) {
                ArrayList<String> y=new ArrayList<>();
                y.add(s);
                ans.add(new ArrayList<>(y));
            }
        }
        else
        {
            ArrayList<ArrayList<String>> ansTemp=new ArrayList<>();
            for ( ArrayList<String> al: ans) {
                String s=convAlToStr(al);
                int nextIndex=s.length();
                if(currKey>=nextIndex)
                {
                    for (String str : mp.get(currKey)) {
                        ArrayList<String> tp=new ArrayList<>(al);
                        tp.add(str);
                        ansTemp.add(tp);
                    }
                }
                else
                    ansTemp.add(al);
            }
            ans=new ArrayList<>(ansTemp);
        }
        return formGps(mp, keys, ans, a);
    }
    public static String convAlToStr(ArrayList<String> a)
    {
        StringBuilder sba=new StringBuilder();
        for (String string : a) {
            sba.append(string);
        }
        return sba.toString();
    }
}
