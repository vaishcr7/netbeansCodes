package cmplexpr;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CmplExpr {

      public static void main(String[] args) {
     Scanner sc=new Scanner(System.in);
     int t=sc.nextInt();
     HashMap<Character,String> mp=new HashMap<>();
     int chCount=65;
         Map<Character,Integer> mpa=new HashMap<>();
         mpa.put('*',1);
         mpa.put('/',1);
         mpa.put('+',2);
         mpa.put('-',2);
     ArrayDeque<Character> aq=new ArrayDeque<>();
     while(t-->0)
     {
         StringBuilder sba=new StringBuilder(sc.next());
        /* while(sba.charAt(0)=='(' && sba.charAt(sba.length()-1)==')')
         {
             sba.deleteCharAt(0);
             sba.deleteCharAt(sba.length()-1);
         }*/
       //  System.out.println("initial useful sba is "+sba.toString());
         if(sba.length()==0)
         {
             System.out.println("");
             continue;
         }
         for (int i = 0; i < sba.length(); i++) {
            // System.out.println("this aq= "+aq);
             char ch=sba.charAt(i);
             if(ch!=')')
             {
                 aq.push(ch);
               //  System.out.println("pushed "+sba.charAt(i));
             }
             else
             {
                 //System.out.println("pakda");
                 StringBuilder temp=new StringBuilder();
                 while(!aq.isEmpty())
                 {
                     char c=aq.pop();
                     if(c=='(')
                         break;
                     temp.append(c);
                 }
                // System.out.println("that aq= "+aq);//1 (a*(b/c)*d)
                // System.out.println("current temp= "+temp);
                 if(temp.length()==1)
                     aq.push(temp.charAt(0));
                 else
                 {//(a+b)*c wala case
                  if((!aq.isEmpty() && (aq.peek()=='/'))  ||(!aq.isEmpty() && aq.peek()=='-' && (temp.charAt(1)=='+' ||temp.charAt(1)=='-')) || (i<sba.length()-1 && mpa.containsKey(sba.charAt(i+1))&& mpa.get(sba.charAt(i+1))<mpa.get(temp.charAt(1))))
                  {
                       //  System.out.println("naaa");
                         temp=temp.append("(");
                         temp.insert(0,")");
                         mp.put((char)chCount,temp.toString());
                         aq.push((char)chCount);
                         chCount++;
                     }
                     else
                     {
                         for (int j = temp.length()-1; j >=0; j--) 
                         {
                           //  System.out.println("pushing "+temp.charAt(j)+" to aq");
                             aq.push(temp.charAt(j));
                         }
                         //System.out.println("haan");
                     }
                 }
             }
         }
        // System.out.println("current hashmap= "+mp+" and sba= "+sba.toString());
         sba.setLength(0);
         while(!aq.isEmpty())
             sba.append(aq.pollLast());
         if(!mp.isEmpty())
         {
             for (int i = 0; i < sba.length(); i++) 
            {
             if(sba.charAt(i)<'a' && !mpa.containsKey(sba.charAt(i)) && sba.charAt(i)!='(' && sba.charAt(i)!=')')
             {
                 String h=mp.get(sba.charAt(i));
               // System.out.println("key= "+sba.charAt(i));
                 sba.deleteCharAt(i);
                 StringBuilder p=new StringBuilder(h);
                 p.reverse();
                 sba.insert(i,p.toString());
             }
            }
         }// (a+b)-(c-d)-(e/f)
         System.out.println(sba.toString().trim());
     }
    }
    
}
