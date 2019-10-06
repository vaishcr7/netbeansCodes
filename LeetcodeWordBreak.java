package leetcodewordbreak;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LeetcodeWordBreak {
    public static void main(String[] args) {
        List<String> wordDict=new ArrayList<>();
        wordDict.add("a");
        wordDict.add("apple");
        wordDict.add("p");
        wordDict.add("pen");
//        wordDict.add("s");
        wordDict.add("l");
        wordDict.add("e");
        wordDict.add("n");
        System.out.println(wordBreak("applepenapples", wordDict));
    }
    public static int count=0;
    public static boolean wordBreak(String s,List<String> wordDict){
        Set<String> st=new HashSet<>(wordDict);
         Map<String,Boolean>mp=new HashMap<>();
         mp= word(mp, s, st);
//         System.out.println("mp= "+mp);
//        System.out.println(count);
         return mp.get(s.intern());
    }
    public static Map<String,Boolean> word(Map<String,Boolean> mp,String s,Set<String> st){
//        System.out.println("here for "+s);
        if(st.contains(s.intern()))
        {
            mp.put(s.intern(),true);
//            System.out.println("list contains "+s.intern());
            return mp;
        }
        if(mp.containsKey(s.intern()))
        {
            count++;
            return  mp;
        }
        if(s.length()==1)
        {
            if(!st.contains(s.intern()))
                mp.put(s.intern(),false);
            else
                mp.put(s.intern(),true);
//            System.out.println("list contains "+s.intern()+" : "+mp.get(s.intern()));
            return mp;
        }
        if(s.length()==2){            
            if(st.contains(s.intern()))
            {
                mp.put(s.intern(),true);
                return mp;
            }
            if(st.contains(s.substring(0, 1).intern()) && st.contains(s.substring(1).intern()))
            {
                mp.put(s.intern(),true);
                return mp;
            }
            mp.put(s.intern(),false);
            return mp;
        }
        boolean finalResult=false;
        for (int i = 1; i < s.length(); i++) {
            String s1=s.substring(0,i).intern();
            String s2=s.substring(i).intern();
//            System.out.println("breaking into "+s1+" and "+s2);
                mp=word(mp, s1, st);
                mp=word(mp, s2, st);    
            finalResult=finalResult || (mp.get(s1) && mp.get(s2));
        }
//        System.out.println(s.intern()+"'s result was "+finalResult);
        if(!mp.containsKey(s.intern()) || (finalResult==true && mp.containsKey(s.intern())))
        {
            mp.put(s.intern(),finalResult);
        }
//        System.out.println(s.intern()+"'s result was "+mp.get(s.intern()));
        return mp;
    }
    
//    public static boolean wordBreak(String s, List<String> wordDict) {
//        Map<Character,ArrayList<String>> mp=new HashMap<>();
//        wordDict.forEach((s1) -> {
//            char ch=s1.charAt(0);
//            ArrayList<String> al;
//            if(mp.containsKey(ch)){
//                al=mp.get(ch);
//                al.add(s1);
//            }
//            else{
//                al=new ArrayList<>();
//                al.add(s1);
//            }
//            mp.put(ch,al);
//        });
//        Map<String,Boolean> ans=new HashMap<>();
//        ans=findPossible(s, ans,mp, 0);
//        if(ans.containsKey(s))
//            return ans.get(s);
//        return false;
//    }
//    public static  Map<String,Boolean> findPossible(String s, Map<String,Boolean> mp,Map<Character,ArrayList<String>> chStr,int ind){
////        System.out.println("covered till "+s.substring(0, ind)+" and map= ");
////        printMap(mp);
//        if(ind==s.length())
//        {
//            mp.put(s,true);
////            System.out.println("reached end returning true");
//            return mp;
//        }
//        if(mp.containsKey(s) && mp.get(s))
//        {
////            System.out.println("already exists in map");
//            return mp;
//        }
//        if(ind>s.length()){
//            mp.put(s,false);
////            System.out.println("longer word encountered, returning false");
//            return mp;
//        }
//        boolean temp=false;
//        if(chStr.containsKey(s.charAt(ind)))
//        {
////            System.out.println("testing for char "+s.charAt(ind));
//            for (int i = 0; i < chStr.get(s.charAt(ind)).size(); i++) {
////                System.out.println("\n starting from "+chStr.get(s.charAt(ind)).get(i));
//                String y=chStr.get(s.charAt(ind)).get(i);
////                System.out.println("y= "+y);
////                System.out.println("res= "+(ind+chStr.get(s.charAt(ind)).get(i).length())+" and s length= "+s.length());
//                if((ind+y.length())<=s.length() && y.equals(s.substring(ind, ind+y.length())))
//                    mp=findPossible(s, mp, chStr, ind+y.length());
////                else
////                    System.out.println("false happening");
//                if(mp.containsKey(s))
//                    temp=temp || mp.get(s);
//                }
//        }
//        mp.put(s,temp);
//        return mp;
//    }
//    public static void printMap(Map<String,Boolean> mp){        
//        Set<Map.Entry<String,Boolean>> st=mp.entrySet();
//        st.forEach((entry) -> {
//            System.out.println(entry.getKey()+" , "+entry.getValue());
//        });
//    }
}
