package ibitlongestcommonprefix;

import java.util.ArrayList;

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
    public static String longestCmPfx(ArrayList<String> al)
    {
        TrieNode root=new TrieNode();
        for (int i = 0; i < al.size(); i++) {
            root=insert(root, al.get(i));
        }
        System.out.println("its children are "+root.numOfChildren);
        StringBuilder sba=new StringBuilder();
        while(root.numOfChildren==1)
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
    public static TrieNode insert(TrieNode root,String s)
    {
        System.out.println("insert func called for string "+s);
        TrieNode mainRoot=root;
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
                }
                root.numOfChildren+=1;
                root=root.children[(int)Character.toLowerCase(s.charAt(i))-97];
            }
            System.out.println("number of children for "+Character.toLowerCase(s.charAt(i))+" are "+root.numOfChildren);
        }
        return mainRoot;
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