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
           //int []filled=new int[n];
           //Arrays.fill(filled,-1);
           for (int i = 0; i < n; i++) {
               ar[i]=sc.nextInt();
           }
           Arrays.sort(ar);
           int []diff=new int[n-1];
           int []diffLeft=new int[n-1];
           int []diffRight=new int[n-1];
             for (int i = 1; i < n; i++) {
                 diff[i-1]=ar[i]-ar[i-1];
             }
             diffLeft[0]=diff[0];
             for (int i = 1; i < diff.length; i++) {
               diffLeft[i]=diff[i]+diffLeft[i-1];
           }
             diffRight[n-2]=diff[n-2];
             for (int i = diff.length-2; i >=0; i--) {
               diffRight[i]=diffRight[i+1]+diff[i];
           }
             System.out.println("both diff arrays are ");
             for (int i = 0; i < diffLeft.length; i++) {
                 System.out.print(diffLeft[i]+" ");
           }
             System.out.println("");
             for (int i = 0; i < diffRight.length; i++) {
                 System.out.print(diffRight[i]+" ");
           }
             System.out.println("");
            c-=2;
            int constC=c;
            int high=ar[n-1];
            int low=ar[0];
            filled[n-1]=filled[0]=1;
            int mid=(high+low)/2;
            int midValue=0;
            
            while(low<=high)
            {
                System.out.println("mid is "+mid+" with low= "+low+" and high= "+high);
                if(findC(diff,mid,c)>=0)
                {
                    midValue=mid;
                    low=mid+1;
                }
                else
                {
                    high=mid-1;
                }
                mid=(low+high)/2;
            }
            
            //finding the index where i will encounter this distance , proceed from both sides
            
            int ind1=0,ind2=0; // find the stall which has been selected to store the cow and find the nearest stall which is already with a cow
            for (int i = 1; i < diffLeft.length; i++) {
               if(diffLeft[i]-diffLeft[i-1]==mid)
                   ind1=i-1;
           }
            for (int i = diffRight.length-2; i >=0 ; i--) {
               System.out.println(diffRight[i]+" <> "+diffRight[i+1]+" and mid= "+mid);
               if((diffRight[i]-diffRight[i+1])==mid)
               {
                   System.out.println("yeogi");
                   ind2=i+1;
               }
           }//1 6 4 1 3 5 7 15 18
            System.out.println("ind2= "+ind2);
            System.out.println(diffLeft[ind1]+"<__>"+diffRight[ind2]);
            midValue=(diffLeft[ind1]<diffRight[ind2])?diffLeft[ind1]:diffRight[ind2];
            System.out.println("minimum distance is : "+midValue);
         }
      }
   public static int findC(int []diff,int k,int c)
   {
       int m=0;
       for (int i = 0; i < diff.length; i++) {
           if(diff[i]>=k)
               m++;
       }
       return (m-c);
   }
   public static boolean findCompat(int []arr,int pos)
   {
       return true;
   }
   public static int binSearchForEqOrGreaterElem(int []arr,int k,int low,int high)
   {
       int mid=(low+high)/2;
       return mid;
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
}
