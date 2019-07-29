package chicago;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class chicago {
  public static class FastReader 
    { 
        BufferedReader br; 
        StringTokenizer st; 
  
        public FastReader() 
        { 
            br = new BufferedReader(new
                     InputStreamReader(System.in)); 
        } 
  
        String next() 
        { 
            while (st == null || !st.hasMoreElements()) 
            { 
                try
                { 
                    st = new StringTokenizer(br.readLine()); 
                } 
                catch (IOException  e) 
                { 
                    e.printStackTrace(); 
                } 
            } 
            return st.nextToken(); 
        } 
  
        int nextInt() 
        { 
            return Integer.parseInt(next()); 
        } 
  
        String nextLine() 
        { 
            String str = ""; 
            try
            { 
                str = br.readLine(); 
            } 
            catch (IOException e) 
            { 
                e.printStackTrace(); 
            } 
            return str; 
        } 
    } 
    public static void main(String args[]) {
      	FastReader sc=new FastReader();
        int t = sc.nextInt();
        while (t-- > 0) {
            int num = sc.nextInt();
            String s=""+num;
            StringBuilder sba = new StringBuilder();
            int len = sba.length();

            int mid = 0;
            if (len % 2 == 0) {
                mid = (len - 1) / 2;
            } else {
                mid = len / 2;
            }
        	if(len==1) {
        		if(num>=0 && num<=8)
        			sba.append(num+1).append("\n");
        		else
        			sba.append(num+2).append("\n");
        	}
        	else
        	{
        		int space = (len%2==0)?0:1;
        		int i = mid;
//            while (i < sba.length() && sba.charAt(i) > '8') {
//                i++;
//                space += 1;
//            }
//            if (i < sba.length()) {
//                sba.replace(i, i + 1,""+(char)(sba.charAt(i) + 1));
//                if (len%2==0) {
//                    sba.replace(i+1-space, i + 2 - space,""+(char)(sba.charAt(i+1) + 1));
//                }
//            } else {
//                sba=new StringBuilder("").append(num+2);
//            }
        		boolean allNines=true;
        		for(int j=0;j<len;j++) {
        			if(s.charAt(j)!='9')
        				{
        					allNines=false;
        					break;
        				}
        		}
        		if(allNines)
        			sba.append(num+2).append("\n");
        		else
        		{
        			StringBuilder g=new StringBuilder("");
        			if(space==0)
        			{
        				
        			}
        			else {  // for odd length string
        				boolean restAllNines=true;
        				for(int j=0;j<len;j++) {
                			if(s.charAt(j)!='9' && j!=i)
                				{
                					restAllNines=false;
                					break;
                				}
                		}
                		if(restAllNines)
                			{
                			g.append(s);
                			g.replace(i,i+1,""+(g.charAt(i)+1));
                			sba.append(g).append("\n");
                			}
                		else {  // normal code for odd length string
                			
                		}
        			}
//        			while(i>=0) {
//        				if(s.charAt(i)!)
//        			}
        		}
        	}
            System.out.println(sba.toString());
        }
    }
}
