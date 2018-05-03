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
        String nums=sc.next();
        String []num=nums.trim().split(nums);
        StringBuilder sba=new StringBuilder();
        if(nums.length()==1)// case where only one number was present in the list
        {
            while(c-->0)
                sba.append(num[0]).append(" ");
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
            while(match.matched==false && i<als.get(levelOfconstDiff).size())
            {
                ArrayList<Integer> temp=new ArrayList<>();
                temp.add(Math.abs(als.get(levelOfconstDiff).get(i-1)-als.get(levelOfconstDiff).get(i)));
                match=checkSame(als.get(levelOfconstDiff));
                als.add(new ArrayList<>(temp));
                if(match.matched==false)
                    levelOfconstDiff+=1;
                i=1;
        }
             int yp=als.get(levelOfconstDiff).size();
             while(yp++<(c+s))
                als.get(levelOfconstDiff).add(match.diff);
             while(levelOfconstDiff>0)
             {
                 int gt=0;
                 while(gt<c)
                 {
                     ArrayList<Integer> p=als.get(levelOfconstDiff-1);
                     int y=p.get(p.size()-(1+c-gt));
                     ArrayList<Integer> pp=als.get(levelOfconstDiff);
                     int yy=pp.get(pp.size()-1);
                     als.get(levelOfconstDiff-1).add(y+yy);
                     gt++;
                 }
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
