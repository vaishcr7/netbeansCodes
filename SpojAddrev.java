package spojaddrev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author vibhor.vaish
 */
public class SpojAddrev {

    public static void main(String[] args) throws IOException {
        MyScanner sc=new MyScanner();
        int t=sc.nextInt();
        while(t-->0)
        {
            int n1=sc.nextInt();
            int n2=sc.nextInt();
            int sum=reversedNum(n1)+reversedNum(n2);
            sum=reversedNum(sum);
            System.out.println(sum);
        }
    }
   public static int reversedNum(int n)
   {
       StringBuilder sba=new StringBuilder("");
       sba.append(n);
       sba.reverse();
       for (int i = 0; i < sba.length(); i++) {
           if(sba.charAt(i)!='0')
               break;
           sba.deleteCharAt(0);
       }
       return Integer.parseInt(sba.toString());
   }
}
class MyScanner
{
    BufferedReader br;
    StringTokenizer st;
    public MyScanner()
    {
        br=new BufferedReader(new InputStreamReader(System.in));
    }
    public String next() throws IOException
    {
        while(st==null || !st.hasMoreTokens())
        {
            st=new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }
    public int nextInt() throws IOException
    {
        return Integer.parseInt(next());
    }
    public String nextLine() throws IOException
    {
        String s;
        s=br.readLine();
        return s;
    }
}