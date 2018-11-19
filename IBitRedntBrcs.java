package ibitredntbrcs;

import java.util.Stack;

public class IBitRedntBrcs {

    public static void main(String[] args) {
        String s="((2+3)*(3+4))/6";
//        String s="(a+((b*c)+c))";
        System.out.println(hasRdntBrcs(s));
    }
    public static boolean hasRdntBrcs(String a)
    {
        Stack<Character> st=new Stack<>();
        if(a.isEmpty())
            return true;
        if(a.length()==1 && (a.equals("(") || a.equals(")")))
            return true;
        else if(a.length()==1 && !(a.equals("(") || a.equals(")")))
            return false;
        
        if(a.length()==2 && a.equals("()") )
            return false;
        int i=0;
        int p=0;
        if(a.charAt(0)=='(' && a.charAt(a.length()-1)==')')
        {
            i=1;
            p=1;
            if(a.length()==3)
                return true;
        }
        for (; i < a.length()-p; i++) {
            System.out.println("st= "+st);
            if(a.charAt(i)!=')')
                st.push(a.charAt(i));
            else
            {
                char ch=st.pop();
                int count=0;
                while(!st.isEmpty() && st.peek()!='(')
                {
                    count+=1;
                    st.pop();
                }
                if(!st.isEmpty() && st.peek()=='(')
                {
                    st.pop();
                    count+=1;
                }
                System.out.println("current st= "+st+" and count= "+count);
                if(count<2)
                    return true;
            }
        }
        if(!st.isEmpty())
            return false;
        System.out.println("returning true");
        return true;
    }
    
}
