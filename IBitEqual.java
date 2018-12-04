package ibitequal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IBitEqual {

    public static void main(String[] args) {
      
    }
    public static ArrayList<Integer> mksums(ArrayList<Integer> a)
    {
        ArrayList<ArrayList<Integer>> al=new ArrayList<>();
        if(a.isEmpty())
            return a;
        Map<Integer,Set<ArrayList<Integer>>> mp=new HashMap<>();
        for (int i = 0; i < a.size(); i++) {
            for (int j = i+1; j < a.size(); j++) {
                ArrayList<Integer> pair=new ArrayList<>();
                pair.add(a.get(i));
                pair.add(a.get(j));
                if(mp.containsKey(a.get(i)+a.get(j)))
                {
                    Set<ArrayList<Integer>> st=mp.get(a.get(i)+a.get(j));
                    st.add(pair);
                    mp.put(a.get(i)+a.get(j),st);
                }
                else
                {
                    Set<ArrayList<Integer>> st=new HashSet<>();
                    st.add(pair);
                    mp.put(a.get(i)+a.get(j),st);
                }
            }
        }
    }    
}