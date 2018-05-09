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
        int count=0,i=0;
        StringBuilder sba=new StringBuilder();
        int max=Integer.MIN_VALUE;
        while(count<(t-k+1))
        {
            System.out.println("");
            System.out.println("initial index= "+count);
            System.out.println("");
            if(!d.isEmpty() && d.peekFirst()==ar[count-1])
                d.pollFirst();
            if(count!=0 && max==ar[count-1])
            {
                max=d.peekFirst();
                System.out.println("chala");
            }
            else if(count!=0)
            {
                System.out.println("isliye max= "+max+" and ar[c-1]= "+ar[count-1]);
            }
            for (;i <(count+k); i++) 
            {                
              d.offer(ar[i]);
              max=max<ar[i]?ar[i]:max;
            }
            System.out.print("max= "+max+" and current d= "+d);
            int node=d.pollFirst();
            System.out.println(" and node= "+node);
            while(!d.isEmpty() && d.peekFirst()>node)
            {
                node=d.pollFirst();
                max=node>max?node:max;
            }
            d.offerFirst(node);
            System.out.println("yo d here is "+d);
            boolean hop=true;
            while(!d.isEmpty() && node<max)
            {
                node=d.pollFirst();
                hop=false;
            }
            if(!hop)
                d.offerFirst(node);
            System.out.println("ok d  here is "+d);
            sba.append(d.peekFirst()).append(" ");
            System.out.println("sba= "+sba.toString());
            System.out.println("max carried over= "+max);
            count++;
        }
        System.out.println(sba.toString());
    }
    //8 5 9 10 9 -7 -4 -8 2 -6
}
