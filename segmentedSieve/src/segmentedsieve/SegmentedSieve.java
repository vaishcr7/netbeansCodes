package segmentedsieve;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class SegmentedSieve {

       public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t=sc.nextInt();
        while(t-- >0)
        {
        int n1 = sc.nextInt();
        int n2 = sc.nextInt();
        if(n1<2)
            n1=2;
        ArrayList<Integer> prime = simpleSieve(n2);
        ArrayList<Integer> answer = new ArrayList<>();
        int sizeEachList = prime.size();
        while (n1 <=n2) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int i = 0; i < sizeEachList; i++)
            {
                int y=n1+i;
                if(y>n2)
                    break;
                temp.add(y);
            }
            //System.out.println("temp= "+temp);
            for (int j = 0; j <prime.size(); j++) {
                Iterator<Integer> it = temp.iterator();
                while (it.hasNext()) {
                    int h=(int) it.next();
                    if (h!=prime.get(j) &&  h% prime.get(j) == 0) {
                        it.remove();
                    }
                }
            }
            answer.addAll(temp);
          //  System.out.println("new answer= "+answer);
            n1 += sizeEachList;
        }
        Iterator it=(new TreeSet<Integer>(answer)).iterator();
        while(it.hasNext())
            System.out.println((int)it.next());
        System.out.println("");
        }
    }

    public static ArrayList<Integer> simpleSieve(int n2) {
        ArrayList<Integer> al = new ArrayList<>();
        int n = (int) Math.sqrt(n2);
        for (int i = 2; i <=n; i++) 
            al.add(i);
        for (int i = 2; i <=n; i++) 
        {
            Iterator<Integer> it = al.iterator();
            while (it.hasNext()) 
            {
                int h = (int) it.next();
                if (h != i && h % i == 0) 
                    it.remove();
            }
        }
        return al;
    }
}