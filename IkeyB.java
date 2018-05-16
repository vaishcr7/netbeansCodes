package ikeyb;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class IkeyB {

    public static void main(String[] args) {
     Scanner sc=new Scanner(System.in);
     int t=sc.nextInt();
     while(t-- >0)
     {
         int k=sc.nextInt();
         int l=sc.nextInt();
         char []keys=new char[k];
         String keystr=sc.next();
         keys=keystr.toCharArray();
         Map<Character,Integer> lettersF=new HashMap<>();
         String let=sc.next();
         for (int i = 0; i < l; i++) 
             lettersF.put(let.charAt(i),sc.nextInt());    
         SortedSet<Map.Entry<Character,Integer>> sortedset = new TreeSet<Map.Entry<Character, Integer>>(
            new Comparator<Map.Entry<Character,Integer>>() {
             @Override
             public int compare(Map.Entry<Character, Integer> t, Map.Entry<Character, Integer> t1) {
                 return t.getValue().compareTo(t1.getValue());
             }
            });
         sortedset.addAll(lettersF.entrySet());
        // System.out.println(sortedset);
        
     }
    }
    
}
/*
1
 8 26
23456789 
ABCDEFGHIJKLMNOPQRSTUVWXYZ 
3371
 589
 1575
 1614
 6212
 971
 773
 1904
 2989
 123
 209
 1588
 1513
 2996
 3269
 1080 
121
 2726
 3083
 4368 
1334
 518
 752 
427
 733
 871
*/