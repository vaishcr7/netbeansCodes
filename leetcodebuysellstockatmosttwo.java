package leetcodebuysellstockatmosttwo;

TLE leetcode for testcase 199/200

/*
rows are transactions and columns are days
The current transaction will only happen if the previous one happened i.e. on min I*2 days, transactions have occurred. Transactions involve buying on one and selling on other day. 
Transactions cannot overlap.
In the starting of a transaction row ,for first even day we cannot have a transaction as due to above condition we would have needed an extra day for buying 
different way to pre compute for 1st transaction since we dont have any transactions before that
we compute transaction such that  we take in account the days just after the day that the previous transaction had finished and ensure this transaction does happen.
We can skip transacting on a day if profits were smaller or negative . 
ar[i-1][m-1] gives us the maximum profit that could have happened for the days before m for the previous transaction
we copy from left since for eg) for transaction 3 for days 0,1,2,3 would be the minimum requirement for transactions 1 & 2 to have happened already.
*/

import java.util.Arrays;

public class LeetcodeBuySellStockAtMostTwo {
    public static void main(String[] args) {
        int []ar=new int[]{3,3,5,0,0,3,1,4};
        System.out.println(new LeetcodeBuySellStockAtMostTwo().maxProfit(ar)); 
    }
    public int maxProfit(int[] prices) {
        if(prices.length<=1)
            return 0;
        if(prices.length==2){
            return (prices[1]-prices[0]>0?prices[1]-prices[0]:0);
        }
        int n=3;
        int [][]ar= new int[n][prices.length];
        for (int i = 0; i < prices.length; i++) {
            ar[0][i]=0;
        }
        for (int i = 0; i < n; i++) {
            ar[i][0]=0;
        }
        ar[1][1]=Math.max(0, prices[1]-prices[0]);        
        for (int i = 1; i < n; i++) {
            ar[i][1]=ar[1][1];
            
            for (int j = 2; j <=i-1; j++) {
                ar[i][j]=ar[i][j-1];
            }
            
            for (int j = i; j < prices.length; j++) {
            
                if(j==1)
                    continue;
                ar[i][j]=Math.max(0,ar[i][j-1]);
                int h=i-1;
                if(i%2==0)
                    h=i;
                for (int m = h; m <=j-1; m++) {
                        if(i>1)
                        {
                            ar[i][j]=Math.max(ar[i][j],(prices[j]-prices[m]+ar[i-1][m-1]));
//                            System.out.println("i= "+i+" and j= "+j+" and ar= "+ar[i][j]+" and m= "+m);
                        }
                        else
                        {
                            ar[i][j]=Math.max(prices[j]-prices[m],ar[i][j]);
//                            System.out.println("i= "+i+" and j= "+j+" and ar= "+ar[i][j]);
                        }
                }
            }
            
        }
        int max=Integer.MIN_VALUE;
        for (int i = 0; i < ar.length; i++) {
            for (int j : ar[i]) {
                System.out.print(j+" , ");
                max=Math.max(max,j);
            }
            System.out.println("");
        }
        return max;
    }
    
}
//  for m =i to j-1 ar[i][j]= p[j]-p[m]+T[i-1][m-1]
// if(i>=(j-1)) copy from above 
