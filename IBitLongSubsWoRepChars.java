package ibitlongsubsworepchars;

import java.util.HashSet;
import java.util.Set;

public class IBitLongSubsWoRepChars {

    public static void main(String[] args) {
//        System.out.println(longsub("madrealmadridbarca"));
//System.out.println(longsub("sxzridbac"));
System.out.println(longsub("au"));
    }
    public static int longsub(String s)
    {
        if(s.length()<=1)
            return s.length();
        int i=0;
        int maxLen=1;
        Set<Character> st=new HashSet<>();
        st.add(s.charAt(0));
        for (int j = 1; j < s.length(); j++) {
            int initialSize=st.size();
            st.add(s.charAt(j));
            System.out.print("set is "+st+",i=  "+i+" ,j= "+j);
            System.out.println(" and substring is "+s.substring(i,j+1));
            if(st.size()==initialSize)
            {
                System.out.println("size was same");
                maxLen=(st.size()>maxLen?(st.size()):maxLen);
                st.clear();
                i=j;
                initialSize=0;
                System.out.println("st= "+st+" and maxLen= "+maxLen);
                while(i>=0)
                {
                    st.add(s.charAt(i));
                    if(initialSize==st.size())
                    {
                        i++;
                        System.out.println("new unique string is "+s.substring(i, j+1));
                        maxLen=((j-i)>maxLen?(j-i):maxLen);
                        break;
                    }
                    initialSize=st.size();
                    i--;
                }
            }
            else
            {
                if(i!=0)
                    maxLen=((j-i+1)>maxLen?(j-i+1):maxLen);
                else
                    maxLen=((j-i)>maxLen?(j-i):maxLen);
            }
            if(i<0)
                i=0;
        }
        if(i==0)
            maxLen+=1;
        return maxLen;
    }
    
}
