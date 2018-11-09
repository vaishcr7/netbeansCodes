package kmpalgo;

import java.util.ArrayList;

public class KmpAlgo {
    public static void main(String[] args) {
        ArrayList<Character> a=new ArrayList<>();
        a.add('v');
        a.add('i');
        a.add('b');
        a.add('h');
        a.add('o');
        a.add('r');
        System.out.println(returnPatternArray(a));
        containsPattern("vibGotModestyvibhorroxxvibhor","vibhor");
    }
    public static void containsPattern(String text,String pattern)
    {
        char[]ar=pattern.toCharArray();
        ArrayList<Character> al=new ArrayList<>();
        for (int i = 0; i < ar.length; i++) {
            al.add(ar[i]);
        }
        ArrayList<Integer> lps=new ArrayList<>(returnPatternArray(al));
        int M = pattern.length(); 
        int N = text.length();
        // create lps[] that will hold the longest 
        // prefix suffix values for pattern 
        int j = 0; // index for pat[] 
  
        int i = 0; // index for txt[] 
        while (i < N) { 
            if (pattern.charAt(j) == text.charAt(i)) { 
                j++; 
                i++; 
            } 
            if (j == M) { 
                System.out.println("Found pattern "
                                   + "at index " + (i - j)); 
                j = lps.get(j - 1); 
            } 
  
            // mismatch after j matches 
            else if (i < N && pattern.charAt(j) != text.charAt(i)) { 
                // Do not match lps[0..lps[j-1]] characters, 
                // they will match anyway 
                if (j != 0) 
                    j = lps.get(j - 1); 
                else
                    i = i + 1; 
            } 
        } 
    }
    public static ArrayList<Integer> returnPatternArray(ArrayList<Character> al)
    {
        ArrayList<Integer> ap=new ArrayList<>();
        if(al.isEmpty())
        {
            return ap;
        }
        if(al.size()==1)
        {
            ap.add(0);
            return ap;
        }
        int i=0,j=1;
        ap.add(0);
        for (j = 1; j < al.size(); j++) {
            if(al.get(i)==al.get(j))
            {
               ap.add(i+1);
               i++;
            }
            else
            {
                while(i>0 && al.get(i)!=al.get(j))
                {
                    i=ap.get(i-1);
                }
                if(al.get(i)==al.get(j))
                {
                    ap.add(i+1);
                }
                else
                    ap.add(0);
            }
        }
        return ap;
    }
}
