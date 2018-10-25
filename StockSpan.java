package stockspan;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

public class StockSpan {

    public static void main(String[] args) {
      Scanner sc=new Scanner(System.in);
      int t=sc.nextInt();
      ArrayDeque<Integer> st1=new ArrayDeque<>();
      ArrayDeque<Integer> st2=new ArrayDeque<>();
      ArrayList<Integer> al=new ArrayList<>();
      for(int i=0;i<t;i++)
      {
          int num=sc.nextInt();
          int span=1;
          if(!st1.isEmpty())
          {
              while(st1.peek()<=num)
              {
                  st2.push(st1.pop());
                  span++;
              }
              while(!st2.isEmpty())
                  st1.push(st2.pop());
          }
        st1.push(num);
        al.add(span);
      }
        System.out.println(al);
    }
    
}
