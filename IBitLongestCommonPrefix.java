package ibitlongestcommonprefix;

import static java.lang.System.out;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class IBitLongestCommonPrefix {

    public static void main(String[] args) {
        ArrayList<String> al=new ArrayList<>();
//        al.add("shot");
//        al.add("apple");
        al.add("orange");
//        al.add("ape");
        al.add("orangutan");
        System.out.println(longestCmPfx(al));
    }
    public static TrieNode root=new TrieNode();
    public static String longestCmPfx(ArrayList<String> al)
    {
        for (int i = 0; i < al.size(); i++) {
            insert(root, al.get(i));
            
//            TrieNode k=root;
//            System.out.println("");
//            System.out.println("");
//            Map<TrieNode,Integer> mp=new HashMap<>();
//            while(!k.isLeaf)
//            {
//                System.out.println("its children number are "+k.numOfChildren+"and k= "+k+" mp contains it ? "+mp.containsKey(k));
//                for (int h = 0;h < 26; h++) {
//                if(k.children[h]!=null)
//                {
//                    k=k.children[h];
//                    mp.put(k,h);
//                    System.out.println("new char= "+(char)(h+97));
//                    System.out.println("k= "+k);
//                    break;
//                }
//              }
//                Set<Map.Entry<TrieNode,Integer>> st=mp.entrySet();
//                for (Map.Entry<TrieNode,Integer> entry : st) {
//                    System.out.println(entry.getKey()+" , "+entry.getValue());
//                }
//                System.out.println("is a leaf ? "+k.isLeaf);
//            }
//            System.out.println("");
//            System.out.println("");
        }
        System.out.println("kskdcdskhkdshk its children are "+root.numOfChildren);
        StringBuilder sba=new StringBuilder();
        int lenOfSmallestString=Integer.MAX_VALUE;
        for (int i = 0; i < al.size(); i++) {
            if(al.get(i).length()<lenOfSmallestString)
                lenOfSmallestString=al.get(i).length();
        }
        
        while(lenOfSmallestString-->0)
        {
            for (int i = 0; i < 26; i++) {
                if(root.children[i]!=null)
                {
                    sba.append((char)97+i);
                    root=root.children[i];
                    System.out.println("new sba= "+sba.toString());
                    break;
                }
            }
        }
        return sba.toString();
    }
    public static void insert(TrieNode root,String s)
    {
        System.out.println("insert func called for string "+s);
        for (int i = 0; i < s.length(); i++) {
            if(root.isLeaf)
            {
                root.isLeaf=false;
                root.children[(int)Character.toLowerCase(s.charAt(i))-97]=new TrieNode();
                System.out.println("assigning new child to "+Character.toLowerCase(s.charAt(i)));
                root.numOfChildren+=1;
            }
            else
            {
                if(root.children[(int)Character.toLowerCase(s.charAt(i))-97]==null)
                {
                    System.out.println("adding another path to char "+Character.toLowerCase(s.charAt(i)));
                    root.children[(int)Character.toLowerCase(s.charAt(i))-97]=new TrieNode();
                    root.numOfChildren+=1;
                }
//                root=root.children[(int)Character.toLowerCase(s.charAt(i))-97];
            }
            System.out.println("number of children for "+Character.toLowerCase(s.charAt(i))+" are "+root.numOfChildren);
            root=root.children[(int)Character.toLowerCase(s.charAt(i))-97];
        }
    }
}
class TrieNode
{
    boolean isLeaf;
    int numOfChildren=0;
    public TrieNode[] children=new TrieNode[26];

    public TrieNode() {
        isLeaf=true;
        for (int i = 0; i < 26; i++) {
            children[i]=null;
        }
    }
    
}
