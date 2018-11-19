package ibitspfydrpth;

import java.util.Stack;

public class IBitSpfydrPth {

    public static void main(String[] args) {
       
    }
    public static String spdrpth(String a)
    {
        Stack<Character> st=new Stack<>();
        for (int i = 0; i < a.length(); i++) {
            if(!st.isEmpty())
            {
                if(a.charAt(i)=='/')
                {
                    if(i<a.length()-1 && a.charAt(i+1)=='/')
                    {
                        st.pop();
                        i+=1;
                    }
                    else
                    {
                        st.push(a.charAt(i));
                    }
                }
                else if(a.charAt(i)=='.')
                {
                    if(i<a.length()-1 && a.charAt(i+1)=='.')
                    {
                        while(!st.peek().equals('/'))
                            st.pop();
                        //removed the escape char /
                         while(!st.peek().equals('/'))
                            st.pop();
                         //remove the last directory
                        i+=1;
                    }
                }
                else
                    st.push(a.charAt(i));
            }
            else
                st.push(a.charAt(i));
        }
        while(st.peek().equals('/'))
            st.pop();
        StringBuilder sba=new StringBuilder();
        while(!st.isEmpty())
            sba.append(st.pop());
        return sba.reverse().toString();
    }
}
