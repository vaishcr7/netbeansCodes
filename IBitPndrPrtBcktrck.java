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
    public static Map<Integer,ArrayList<String>> makePts(String a)
    {
        Map<Integer,ArrayList<String>> mp=new HashMap<>();
        for (int i = 0; i < a.length(); i++) {
            String str=new String(a.substring(i));
            System.out.println("\n***************");
            System.out.println("str= "+str);
            for (int ik = i+1; ik <=str.length(); ik++) {
                String temp=new String(str.substring(i, ik));
                System.out.println("considering "+temp);
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
                    System.out.println("therefore "+mp.get(i));
                }
            }
                System.out.println("***************\n");
        }
        System.out.println(mp);
        return mp;
    }       
}
