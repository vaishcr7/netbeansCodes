package slidingwindoweachwindow;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class SLidingWIndowEachWindow {

    /**
     Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Therefore, return the max sliding window as [3,3,5,5,6,7].
* 8 3 1  3  -1 -3  5  3  6  7
     */
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in); 
        int t=sc.nextInt(),k=sc.nextInt(); 
        int []ar=new int[t];
        for (int i = 0; i < t; i++) {
            ar[i]=sc.nextInt();
        }
        Deque<Integer> d=new LinkedList<>();
        int count=k-1,i=0;
        StringBuilder sba=new StringBuilder();
        int max=Integer.MIN_VALUE;
        int []ans=new int[ar.length-k+1];
        int tab=0,initial_pos=0;
        for (int j = 0; j < k-1; j++) {
            d.offerLast(ar[j]);
            while(d.peekFirst()<d.peekLast())
                    d.pollFirst();
        }
        while(count<t)
        {
                d.offerLast(ar[count]);
                while(!d.isEmpty() && d.peekFirst()<d.peekLast())
                    d.pollFirst();
            System.out.println("this d= "+d);
            ans[tab]=d.peekFirst();
            tab++;
            System.out.print("for count= "+count+"  ");
            count++;
            if(!d.isEmpty() && d.peekFirst()==ar[initial_pos])
                    d.pollFirst();
            while(!d.isEmpty() && d.peekFirst()<d.peekLast())
                    d.pollFirst();
            System.out.println("d= "+d);
            initial_pos+=1;
            System.out.println("answer is ");
            
        for (int j = 0; j < ans.length; j++) {
            System.out.print(ans[j]+" ");
        }
            System.out.println("");
        }
        }
    }
    //8 5 9 10 9 -7 -4 -8 2 -6