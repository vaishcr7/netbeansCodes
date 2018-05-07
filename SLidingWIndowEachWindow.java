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
     */
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in); 
        int t=sc.nextInt(),k=sc.nextInt(); 
        int []ar=new int[t];
        for (int i = 0; i < t; i++) {
            ar[i]=sc.nextInt();
        }
        Deque<Integer> d=new LinkedList<>();
        int count=0,i=0;
        StringBuilder sba=new StringBuilder();
        while(count<(t-k))
        {
            if(!d.isEmpty() && d.peekLast()==ar[count])
                d.pollLast();
            for (;i <(count+k); i++) 
            {                
              d.offer(ar[i]);
            }
            int node=d.pollLast();
            while(!d.isEmpty() && d.peekLast()>node)
            {
                node=d.pollLast();
            }
            d.offerLast(node);
            sba.append(d.peekLast()).append(" ");
            count++;
        }
        System.out.println(sba.toString());
    }
    
}
