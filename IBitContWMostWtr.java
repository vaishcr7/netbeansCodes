package ibitcontwmostwtr;

import java.util.ArrayList;

public class IBitContWMostWtr {
    public static void main(String[] args) {
        ArrayList<Integer> al=new ArrayList<>();
        al.add(1);
        al.add(8);
        al.add(6);
        al.add(2);
        al.add(5);
        al.add(4);
        al.add(8);
        al.add(7);
        al.add(3);
        al.add(5);
        al.add(5);
        al.add(5);
        al.add(5);
        al.add(5);
        al.add(5);
        System.out.println(contWater(al));
    }
    public static int contWater(ArrayList<Integer> al)
    {
        int st=0;
        int max=0;
        for (int i = 1; i < al.size(); i++) {
            int area=0;
            area = Math.max((Math.min(al.get(st), al.get(i)) * (i - st)), (Math.min(al.get(i - 1), al.get(i))));
            if ((Math.min(al.get(i - 1), al.get(i))) > (Math.min(al.get(st), al.get(i)) * (i - st)))
            {
                st = i - 1;
                System.out.println("new st= "+st);
            }
            if(max<area)
            {
                max=area;
                System.out.println("area changed for i= "+i);
            }
        }
        return max;
    }
}
