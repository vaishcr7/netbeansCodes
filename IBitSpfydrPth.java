package ibitspfydrpth;

import java.util.Stack;

public class IBitSpfydrPth {

    public static void main(String[] args) {
        String s="/../";
//        String s="/a//b////c/d//././/..";
//        String s="//f/d////e/.//..//.././e//";
//        String s="/f/d////e/././e//";
//path = "/home/", => "/home"
//path = "/a/./b/../../c/", => "/c"
//path = "/a/../../b/../c//.//", => "/c"
//path = "/a//b////c/d//././/..", => "/a/b/c"
        System.out.println(spdrpth(s));
    }
    public static String spdrpth(String a)
    {
        Stack<Character> st=new Stack<>();
        for (int i = 0; i < a.length(); i++) {
//            System.out.println("character= "+a.charAt(i));
            if(!st.isEmpty())
            {
                if(a.charAt(i)== '/')
                {
                    if(i<a.length()-1 && a.charAt(i+1)=='/')
                    {
//                        System.out.println(" //  found");
//                        st.pop();
                        i+=1;
                    }
                     while(!st.isEmpty() && st.peek().equals('/'))
                        {
//                            System.out.println("removing extra /");
                            st.pop();
                        }
                    st.push(a.charAt(i));
                }
                else if(a.charAt(i)=='.')
                {
                    if(i<a.length()-1 && a.charAt(i+1)=='.')
                    {
                        st.pop();
                        while(!st.isEmpty() && !st.peek().equals('/'))
                        {
//                            System.out.println("encountered /");
                            st.pop();
                        }
                        if(!st.isEmpty())
                            st.pop();
                        i+=1;
                    }
                    else 
                    {
                        while(!st.isEmpty() && st.peek().equals('/'))
                        {
                            st.pop();
//                            System.out.println("removing current directory");
                        }
                    }
                }
                else
                    st.push(a.charAt(i));
//                System.out.println("current stack is "+st);
            }
            else
                st.push(a.charAt(i));
        }
        while(!st.isEmpty() && st.peek().equals('/'))
            st.pop();
        StringBuilder sba=new StringBuilder();
        while(!st.isEmpty())
            sba.append(st.pop());
        if(sba.length()==0)
            sba.append('/');
        return sba.reverse().toString();
    }
}
