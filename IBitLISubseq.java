package ibitlisubs;

import java.util.ArrayList;

public class IBitLISubseq {

    public static void main(String[] args) {
        ArrayList<Integer> nums=new ArrayList<>();
//        nums.add(1);
//        nums.add(3);
//        nums.add(5);
//        nums.add(6);
//        nums.add(4);
//        nums.add(8);
        nums.add(1);
        nums.add(1);
        nums.add(2);
        nums.add(1);
        nums.add(1);
        System.out.println(lengthOfLIS(nums));
    }
    public static int lengthOfLIS(ArrayList<Integer> nums) {
        int maxLenInc=0;
        int maxLenDec=0;
        int mid=0;
        int []ap=new int[nums.size()];
        for (int i = 0; i < ap.length; i++) {
            ap[i]=1;
        }
        int []at=new int[nums.size()];
        for (int i = 0; i < at.length; i++) {
            at[i]=1;
        }
//        for (int i = 2; i < at.length; i++) {
//            if(nums.get(i)<=nums.get(1))
//                at[0]+=1;
//        }
        for (int i = 1; i < ap.length; i++) {
            for (int j = 0; j < i; j++) {
                if(nums.get(i)>nums.get(j) && ap[i]<ap[j]+1)
                {
                    ap[i]=ap[j]+1;
//                    if(i==0)
//                    {
//                        System.out.println("considered for inc = "+nums.get(j));
//                    }
                }
            }
        }
        for (int i =at.length-2; i >=0; i--) {
            for (int j = i+1; j <at.length; j++) {
//                if(i==5)
//                {
//                    System.out.println("comparing with "+nums.get(j)+" at i= "+at[i]+" and at j= "+at[j]);
//                }
                if(nums.get(i)>nums.get(j) && at[i]<at[j]+1)
                {
//                    System.out.println("here");
                    at[i]=at[j]+1;
//                    if(i==5)
//                    {
//                        System.out.println("considered for dec= "+nums.get(j)+" and j= "+j);
//                    }
                }
//                else
//                {
//                    if(i==5)
//                        System.out.println("not here");
//                }
            }
        }
        for (int i = 0; i < ap.length; i++) {
            System.out.println("ap= "+ap[i]+" and at= "+at[i]+" for mid= "+nums.get(i));
            if((maxLenInc+maxLenDec)<(ap[i]+at[i]))
            {
                maxLenInc=ap[i];
                maxLenDec=at[i]-1;
                mid=i;
//                System.out.println("inc= "+maxLenInc+" and dec= "+maxLenDec+" for mid= "+nums.get(i));
            }
        }
        if(nums.size()==1)
            return 1;
        if(mid!=0 && mid!=nums.size()-1)
        {
//            System.out.println("\nmid= "+mid);
            return maxLenInc+maxLenDec;
        }
        else
            return maxLenDec+maxLenInc;
    }
    
}
