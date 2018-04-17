package segmentedsieve;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class SegmentedSieve {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n1 = sc.nextInt();
        int n2 = sc.nextInt();
        ArrayList<Integer> prime = simpleSieve(n2);
        ArrayList<Integer> answer = new ArrayList<>();
        int sizeEachList = prime.size();
        while ((n1 + sizeEachList) < n2) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int i = 0; i < 8; i++) {
                temp.add(n1 + i);
            }
            for (int j = 0; j < prime.size(); j++) {
                Iterator<Integer> it = temp.iterator();
                while (it.hasNext()) {
                    if ((int) it.next() % prime.get(j) == 0) {
                        it.remove();
                    }
                }
            }
            answer.addAll(temp);
            n1 += sizeEachList;
        }
        System.out.println("answer");
    }

    public static ArrayList<Integer> simpleSieve(int n2) {
        ArrayList<Integer> al = new ArrayList<>();
        int n = (int) Math.sqrt(n2);
        for (int i = 2; i < n; i++) {
            al.add(i);
        }
        for (int i = 2; i < n; i++) {
            Iterator<Integer> it = al.iterator();
            while (it.hasNext()) {
                int h = (int) it.next();
                if (h != i && h % i == 0) {
                    it.remove();
                }
            }
        }
        return al;
    }

}
