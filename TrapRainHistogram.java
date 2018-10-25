package traprainhistogram;

import java.util.ArrayDeque;
import java.util.Scanner;

public class TrapRainHistogram {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        ArrayDeque<Integer> st1 = new ArrayDeque<>();
        int[] ar = new int[t];
        for (int i = 0; i < t; i++) {
            ar[i] = sc.nextInt();
        }
        int area = 0;
        for(int i=0;i<t;i++)
        {
            System.out.println("i for k is "+i);
            int k=ar[i];
            st1.push(k);
            int j=i;
            if(i>=(t-1))
                break;
            int temp=ar[i+1];
            System.out.println("k= "+k+" and temp= "+temp);
            while(i<t && temp<k)
            {
                st1.push(temp);
                if(i>=t)
                    break;
                temp=ar[i];
            }
            System.out.println("st1= "+st1);
            System.out.println("temp<k: "+(temp<k));
            System.out.println("i>=t : "+(i>=t));
            if(temp<k && i>=t)
            {
                i=j;
                st1.clear();
                System.out.println("enter");
                continue;
            }
            if(!st1.isEmpty())
            {
                System.out.println("area calculation for i= "+i);
                area+=k*(st1.size()-1);
                while(st1.size()!=1)
                    area-=st1.pop();
                st1.clear();
            }
            System.out.println("ye st1= "+st1);
           System.out.println("ar= "+area);
        }
        System.out.println("area= "+area);
    }

}
