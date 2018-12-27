package ibitshruqpfx;

import java.util.ArrayList;

public class IBitShrUqPfx {

    public static void main(String[] args) {
        ArrayList<String> m=new ArrayList<>();
        m.add("zebra");
        m.add("dogg");
        m.add("dove");
        m.add("dack");
        System.out.println(sqp(m));
    }
    public static ArrayList<String> sqp(ArrayList<String> al)
    {
        ArrayList<String> ans=new ArrayList<>();
        Trie t=new Trie();
        if(al.isEmpty())
            return ans;
        t.endOfWord=false;
        Trie p=t;
        for (String string : al) {
            t=p;
//            System.out.println("string = "+string);
            for (int i = 0; i < string.length(); i++) {
                int n=((int)string.charAt(i))-97;
                if(t.edges[n]==null)
                {
                    Trie y=new Trie();
                    t.edges[n]=y;
//                    System.out.println("not visited yet letter= "+string.charAt(i));
                    if(i!=string.length()-1)
                    {
                        y.endOfWord=false;
                    }
//                    else
//                        System.out.print("  end of word reached \n");
                }
                else
                {
                    t.sharedBy+=1;
//                    System.out.println("shared by other words= "+string.charAt(i));
                }
                t=t.edges[n];
            }
        }
        
        for (String string : al) {
            t=p;
            System.out.println("\nstring = "+string);
            for (int i = 0; i < string.length(); i++) {
                if(t.sharedBy>0)
                {
                    System.out.print(t.edges[((int)string.charAt(i))-97].sharedBy);
                    t=t.edges[((int)string.charAt(i))-97];
                    System.out.println(" and used by other words= "+string.charAt(i));
                }
                else if(i==string.length()-1)
                {
                    ans.add(string);
                    System.out.print("  end of word reached \n");
                    break;
                }
                else
                {
                    String k=string.substring(0,i+1);
                    ans.add(k);
                    System.out.println("sole component= "+string.charAt(i));
                    break;
                }
            }
        }
        return ans;
    }
}
class Trie
{
    public Trie[] edges;
    boolean endOfWord;
    int sharedBy;
    public Trie() {
        this.edges = new Trie[26];
        this.endOfWord = true;
        this.sharedBy=0;
    }    
}