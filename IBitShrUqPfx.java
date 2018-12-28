package ibitshruqpfx;

import java.util.ArrayList;

public class IBitShrUqPfx {

    public static void main(String[] args) {
        ArrayList<String> m=new ArrayList<>();
        m.add("guwnn");
        m.add("q");
        m.add("nz");
//        m.add("");
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
            System.out.println("string = "+string);
            for (int i = 0; i < string.length(); i++) {
                int n=((int)string.charAt(i))-97; 
                if(t.edges[n]==null)
                {
                    System.out.println("EARLIER was being shared "+t.sharedBy+" words= "+string.charAt(i)+" for string "+string);
                    t.sharedBy+=1;
                    t.edges[n]=new Trie();
                    if(i!=string.length()-1)
                    {
                        t.edges[n].endOfWord=false;
                    }
                    else
                        System.out.print("  end of word reached \n");
                }
                else
                {
                    t.edges[n].sharedBy+=1;
                    t.sharedBy+=1;
                    System.out.println("shared already by "+t.edges[n].sharedBy+" words= "+string.charAt(i)+" for string "+string);
                }
                t=t.edges[n];
            }
        }        
        System.out.println("\n\nmaking unique prefixes \n");
        for (String string : al) {
            t=p;
//            Trie prev=null;
            System.out.println("\nstring = "+string);
            for (int i = 0; i < string.length(); i++) {
                System.out.println(t.sharedBy+" for char "+string.charAt(i));
                if(t.sharedBy>1 && i!=string.length()-1)
                {
//                    System.out.println("filled by");
//                    for (int j = 0; j < 26; j++) {
//                        if(t.edges[j]!=null)
//                            System.out.print(" , "+t.sharedBy);
//                        else
//                            System.out.print(" , "+((char)(97+j)));
//                    }
//                    System.out.println("");
                    System.out.print(t.sharedBy);
//                    prev=t;
                    t=t.edges[((int)string.charAt(i))-97];
                    System.out.println(" and used by other words= "+string.charAt(i));
                }
                else if(i==string.length()-1 && t.sharedBy>1)
                {
                    ans.add(string);
                    System.out.print("  end of word reached \n");
                    break;
                }
                else if(i==string.length()-1 && t.sharedBy<=1)
                {
                    ans.add(string.substring(0, i));
                    System.out.print("  end of word reached but a shorter prefix possible \n");
                    break;
                }
                else
                {
                    String k;
//                    System.out.println("prev is ");
//                    for (int j = 0; j < 26; j++) {
//                        if(prev.edges[j]!=null)
//                            System.out.print(" , "+prev.sharedBy);
//                        else
//                            System.out.print(" , "+((char)(97+j)));
//                    }
//                    System.out.println("");
//                    System.out.println("prev char= "+(string.charAt(i-1))+"  shared by = "+prev.edges[((int)string.charAt(i-1))-97].sharedBy);
                    if(t.sharedBy>1)
                    {
                        k=string.substring(0,i+1);
                    }
                    else
                    {
                        k=string.substring(0,i);
                    }
                    ans.add(k);
                    System.out.print(t.sharedBy);
                    System.out.println("  sole component= "+string.charAt(i));
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