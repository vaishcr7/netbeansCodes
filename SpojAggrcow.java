package spojaggrcow;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
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
           //int []filled=new int[n];
           //Arrays.fill(filled,-1);
           for (int i = 0; i < n; i++) {
               ar[i]=sc.nextInt();
           }
           Arrays.sort(ar);
           int []diff=new int[n-1];
              Map<Integer,Integer> mp=new HashMap<>();
             for (int i = 1; i < n; i++) {
                 diff[i-1]=ar[i]-ar[i-1];
                mp.put(i,diff[i-1]);
             }
             Arrays.sort(diff);
            
            //filled[0]=1;
            //filled[filled.length-1]=1;
            c-=2;
            int constC=c;
            int high=n-1;
            int low=0;
            int mid=(high+low)/2;
            int midValStack;
            int midValue=Integer.MIN_VALUE;
            if(c==0)
                midValStack=mp.get(high);
            else
                midValStack=mid;
            
           //ArrayDeque<Integer> midValStack=new ArrayDeque<>();
           
           System.out.println("hashmap is ");
           Set<Map.Entry<Integer,Integer>> st =mp.entrySet();
           for (Map.Entry<Integer, Integer> entry : st) {
           System.out.println(entry.getKey()+" : "+entry.getValue());
           }
            while(c>0 && low<high)
            {
                int prev=0;
                for (int i = 1; i < ar.length-1; i++) 
                {
                    if(c==0)
                        break;
                    if((mp.get(i)+prev)>=mid)
                    {
                        //filled[i]=1;
                        while(i<ar.length-1 && (mp.get(i)+prev)>=mid)
                        {
                            System.out.println("ran for i: "+i);
                            i++;
                            prev+=mp.get(i);
                        }
                        c--;
                        i-=1;
                    }
                }
                System.out.println("c for "+mid+" was : "+c);
                if(c!=0)
                {
                    high=mid-1;
                }
                else // checking to see if any more mid values which are bigger than the found one, do satisfy our req.
                {
                    System.out.println("new dist : "+mid);
                    midValStack=mid;
                    low=mid+1;
                }
                c=constC;
                //Arrays.fill(filled,-1);
               // filled[0]=1;
               // filled[filled.length-1]=-1;
                mid=(low+high)/2;
            }
           System.out.println(midValStack);  
         }
      }
} /*
1 6 4 
1
 3
 5
 11
 14 15
*/
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
}//1 6 3 1 3 5 7 15 18
