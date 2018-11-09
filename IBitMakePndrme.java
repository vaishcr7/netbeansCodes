package ibitmakepndrme;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class IBitMakePndrme {

    public static void main(String[] args) {
        System.out.println(returnPatternArray("AACECAAAA"));
//        System.out.println(makePndrm("AACECAAAA"));
        System.out.println(solve("AACECAAAA"));
    }
    public static int makePndrm(String str)
    {
        int n=str.length();
        int table[][] = new int[n][n]; 
        int l, h, gap; 
        for (gap = 1; gap < n; ++gap) 
        for (l = 0, h = gap; h < n; ++l, ++h) 
            table[l][h] = (str.charAt(l) == str.charAt(h))? 
                           table[l+1][h-1] : 
                          (Integer.min(table[l][h-1], 
                                 table[l+1][h]) + 1); 
        return table[0][n-1]; 
    }
    public static ArrayList<Integer> returnPatternArray(String sal)
    {
        System.out.println("called with func "+sal);
        ArrayList<Integer> ap=new ArrayList<>();
        if(sal.isEmpty())
        {
            return ap;
        }
        if(sal.length()==1)
        {
            ap.add(0);
            return ap;
        }
        int i=0,j=1;
        ap.add(0);
        for (j = 1; j < sal.length(); j++) {
            if(sal.charAt(i)==sal.charAt(j))
            {
               ap.add(i+1);
               i++;
            }
            else
            {
                while(i>0 && sal.charAt(i)!=sal.charAt(j))
                {
                    i=ap.get(i-1);
                }
                if(sal.charAt(i)==sal.charAt(j))
                {
                    ap.add(i+1);
                }
                else
                    ap.add(0);
            }
        }
        return ap;
    }
    public static int solve(String str) {
        if(str.length()<=1)
            return 0;
        StringBuilder sba=new StringBuilder();
        String s=sba.append(str).reverse().toString();
        sba.delete(0, sba.length());
        sba.append(str).append('$').append(s);
        System.out.println("new string is "+sba.toString());
        ArrayList<Integer> lps=returnPatternArray(sba.toString());
        return 0;
    }
}
