package sequencepredictpolynomial;

import java.util.ArrayList;
import java.util.Scanner;


public class SequencePredictPolynomial {

    public static void main(String[] args) {
      Scanner sc=new Scanner(System.in);
      int t=sc.nextInt();
      while(t-- >0)
      {
        int s=sc.nextInt();
        int c=sc.nextInt();
        String []num=new String[s];
          for (int i = 0; i < s; i++) {
              num[i]=(sc.next().trim());
          }
          /*System.out.println("num= ");
          for (int i = 0; i < num.length; i++) {
              System.out.print(num[i]+" ");
          }
          System.out.println("");*/
        StringBuilder sba=new StringBuilder();
        if(num.length==1)// case where only one number was present in the list
        {
            while(c-->0)
                sba.append(num[0]).append(" ");
            System.out.println(sba.toString().trim());
        }
        else
        {
            ArrayList<Integer> al=new ArrayList<>();
            int i=0;
            while(i<num.length)
            {
                al.add(Integer.parseInt(num[i++]));
            }
            i=1;
            int levelOfconstDiff=0;
            ArrayList<ArrayList<Integer>> als=new ArrayList<>();
            als.add(al);
            newDs match=new newDs();
            match=checkSame(als.get(0));
            while(match.matched==false)
            {
                //System.out.println("hi");
                /*4
 6 3 
1 2 3 4 5 6
 8 2 
1 2 4 7 11 16 22 29 
10 2 
1 1 1 1 1 1 1 1 1 2 
1 10 
3*/
                ArrayList<Integer> temp=new ArrayList<>();
                while( i<als.get(levelOfconstDiff).size())
                {
                    temp.add(Math.abs(als.get(levelOfconstDiff).get(i-1)-als.get(levelOfconstDiff).get(i)));
                    i+=1;
                }
                match=checkSame(als.get(levelOfconstDiff));
                if(match.matched==false)
                    levelOfconstDiff+=1;
                else
                    break;    
                als.add(new ArrayList<>(temp));
                i=1;
                System.out.println("als= "+als+" and levelof= "+levelOfconstDiff);
            }
            int yp=als.get(levelOfconstDiff).size();
             while(yp++<(c+s))
                als.get(levelOfconstDiff).add(match.diff);
             int last=als.get(levelOfconstDiff).size()-1;
             while(levelOfconstDiff>0)
             {
                 System.out.println("size of current arraylist is "+last);
                 while(last>0)
                 {
                     ArrayList<Integer> p=als.get(levelOfconstDiff-1);
                     int y=p.get(p.size()-1);
                     ArrayList<Integer> pp=als.get(levelOfconstDiff);
                     int yy=pp.get(pp.size()-1);
                     als.get(levelOfconstDiff-1).add(y+yy);
                     last--;
                 }
                 System.out.println("moving up with this "+als.get(levelOfconstDiff));
                 levelOfconstDiff-=1;
             }
             for (int j = 0; j < als.get(0).size(); j++) {
                 System.out.print(als.get(0).get(j)+" ");
            }
             System.out.println("");
      }
      }
    }
    public static newDs checkSame(ArrayList<Integer> al)
    {
        newDs obj=new newDs();
        int temp=al.get(0);
        for (int i = 1; i < al.size(); i++) {
            if(temp!=al.get(i))
                return obj;
            }
        obj.matched=true;
        obj.diff=temp;
        return obj;
        }        
    }
class newDs
{
    boolean matched;
    int diff;

    public newDs() {
        matched=false;
        diff=0;
    }
    
}
