package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        int []ar=new int[4];
        ar[0]=1;
        ar[1]=2;
        ar[2]=3;
        ar[3]=4;
//        ar[4]=5;
//        ar[5]=2;
//        ar[6]=2;
//        ar[7]=-1;
//        ar[8]=7;
        System.out.println(numberOfArithmeticSlices(ar));
    }
    public static int numberOfArithmeticSlices(int[] A) {
        ArrayList<Integer> al=new ArrayList<>();
        Map<Integer,Boolean> mp=new HashMap<>();
        for(int i=0;i<A.length;i++)
            al.add(A[i]);
        if(al.size()<=1)
            return al.size();
        int maxLen=2;
        for(int i=al.size()-2;i>0;i--)
        {
            int temp=i;
            int l=i-1;
            int r=i+1;
            int diff=Integer.MAX_VALUE;
            // System.out.println(" here ");
            int len=2;
            while(l>=0)
            {
                if((al.get(l)+al.get(r))==(2*al.get(i)) && (mp.isEmpty() || (!mp.isEmpty() && !mp.containsKey(al.get(l)-al.get(i)))))
                {
                    len++;
                    System.out.println("len increased for i= "+i);
                    i=l;
                    l--;
                    r=i;
                    diff=al.get(l)-al.get(i);
                }
                else if((al.get(l)+al.get(r))!=(2*al.get(i)) )
                {
                    l--;
                }
            }
            i=temp-1;
            l=i-1;
            r=i+1;
            while(l>=0 && r<al.size())
            {

                if((al.get(l)+al.get(r))==(2*al.get(i)) && (mp.isEmpty() || (!mp.isEmpty() && !mp.containsKey(al.get(l)-al.get(i)))))
                {
                    len++;
                    System.out.println("len IIIIncreased for i= "+i);
                    l=i;
                    i=r;
                    r++;
                    diff=al.get(l)-al.get(i);
                }
                else if((al.get(l)+al.get(r))!=(2*al.get(i)))
                {
                    r++;
                }
            }
            i=temp;
            System.out.println("i= "+i);
            if(len>maxLen)
            {
                maxLen=len;
                mp.put(diff,true);
                System.out.println("map is "+mp);
            }
            // System.out.println("maxlen is "+maxLen);
        }
        return maxLen;
    }
}
