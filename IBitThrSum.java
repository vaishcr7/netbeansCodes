package ibitthrsum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class IBitThrSum {
    public static void main(String[] args) {
        ArrayList<Integer> ap=new ArrayList<>();
        ap.add(7);
        ap.add(2);
        ap.add(11);
        ap.add(-4);
        ap.add(10);
        ap.add(5);
        ap.add(1);
        ap.add(0);
        System.out.println(sumUp(ap,8));
    }
    public static ArrayList<Integer> sumUp(ArrayList<Integer> al,int target)
    {
        ArrayList<Integer> ans=new ArrayList<>();
        if(al.size()<3)
            return ans;
        Map<Integer,ArrayList<Integer>> mp=new HashMap<>();
        for (int i = 0; i < al.size(); i++) {
            ArrayList<Integer> z;
            if(!mp.containsKey(al.get(i)))
            {
                z=new ArrayList<>();
                z.add(i);
                mp.put(al.get(i),new ArrayList<>(z));
            }
            else
            {
                z=new ArrayList<>(mp.get(al.get(i)));
                z.add(i);
                mp.put(al.get(i),z);
            }
        }
        int k=0;
        while(k<target)
        {
        for (int i = 0; i < al.size()-2; i++) {
            System.out.println("k= "+k);
            int fnum=al.get(i);
            for (int j = i+1; j < al.size()-1; j++) {
                int snum=al.get(j);
                System.out.println("( "+fnum+" , "+snum+" )");
//                if(mp.containsKey((target+k)-fnum-snum))
//                    System.out.println("its there for "+fnum+" , "+snum+" --> "+((target+k)-fnum-snum));
                if(mp.containsKey((target+k)-fnum-snum) && diffElem(mp.get((target+k)-fnum-snum), i, j))
                {
                    ans.add(fnum);
                    ans.add(snum);
                    ans.add((target+k)-fnum-snum);
                    System.out.println("found match for "+(target+k));
                    return ans;
//                    return (target+k);
                }
            }
            for (int j = i+1; j < al.size()-1; j++) {
                int snum=al.get(j);
                if(mp.containsKey((target+k)-fnum-snum) && diffElem(mp.get((target+k)-fnum-snum), i, j))
                {
                    ans.add(fnum);
                    ans.add(snum);
                    ans.add((target-k)-fnum-snum);
                    System.out.println("e match exists for "+(target-k));
                    return ans;
//                    return (target-k);
                }
            }
        }
        k+=1;
    }
        return ans;
    }
    public static boolean diffElem(ArrayList<Integer> al,int i,int j)
    {
//        System.out.println("list= "+al+" , i= "+i+" and j= "+j);
        if(al.size()>2)
            return true;
        else if(al.size()==2)
        {
            if((al.get(0).equals(i) && al.get(1).equals(j)) || (al.get(1).equals(i) && al.get(0).equals(j)))
                return false;
            return true;
        }
        else
        {
            if(al.get(0).equals(j) || al.get(0).equals(i))
                return false;
            return true;
        }
    }
}
