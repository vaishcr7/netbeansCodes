
package dpproblems;
//10 4 11 6 7 24 35 3 2 40 17

import java.util.Scanner;

public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int []ar=new int[sc.nextInt()];
        for (int i = 0; i < ar.length; i++) {
            ar[i]=sc.nextInt();
        }
        System.out.println("longest inc subsequence= "+calcIncSubs(ar));
    }
    public static int calcIncSubs(int []ar)
    {
        int [][]arr=new int[ar.length][ar.length];
        for (int i = 0; i < ar.length; i++) 
        {
            int currMax=0;
            for (int j = 1; j < ar.length; j++)
            {
                if(i==0)// filling first row for the rest of the below comparisons
                {
                    if(ar[i]<ar[j])
                        arr[i][j]=1;
                }
                else
                {
                    if(j<=i)// for copying the values already precomputed from above
                        arr[i][j]=arr[i-1][j];
                    else if(ar[i]<ar[j])//  compare the current sequence or the one from above.
                    {
                        System.out.println("case for j= "+j+" and i= "+i);
                        int y=arr[i-1][j];
                        arr[i][j]=1+arr[i][currMax];
                        System.out.println("y= "+y+" and currmax= "+currMax);
                        arr[i][j]=(arr[i][j]>y)?arr[i][j]:y;
                    }
                    else//continue the largest running sequence as this one is excluded anyway.
                    {
                            arr[i][j]=arr[i][currMax];
                    }
                }
                currMax=(arr[i][currMax]>arr[i][j]?currMax:j);// to track the largest running sequence till now.
            }
        }
        for (int i = 0; i < ar.length; i++) {
            for (int j = 0; j < ar.length; j++) {
                System.out.print(" ,"+arr[i][j]);
            }
            System.out.println("");
        }
        int len=ar.length;
        int max=arr[len-1][0];
        for (int i = 1; i < len; i++) {
            if(max<arr[len-1][i])
                max=arr[len-1][i];
        }
        return max+1;
    }
    
}
