package spojaggrcow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SpojAggrcow {
   public static void main(String[] args) throws IOException {
       MyScanner sc=new MyScanner();
       int t=sc.nextInt();
       while(t-->0)
       {
           int n=sc.nextInt();
           int c=sc.nextInt();
           int []ar=new int[n];
           int []filled=new int[n];
           Arrays.fill(filled,-1);
           for (int i = 0; i < n; i++) {
               ar[i]=sc.nextInt();
           }
           int []diff=new int[n-1];
           for (int i = 1; i < n; i++) {
               diff[i-1]=ar[i]-ar[i-1];
           }
           
       }
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
