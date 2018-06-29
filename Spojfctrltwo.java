package spojfctrltwo;

import java.util.Arrays;
import java.util.Scanner;

public class Spojfctrltwo {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        int []ar=new int[158];// number of digits in factorial of 100! 
        Arrays.fill(ar,-1);
        ar[0]=1;
        while(t-->0)
        {
            int n=sc.nextInt();
            for (int i = 2; i <=n; i++) {
                ar=multiply(ar,i);
            }
            for (int i = getInd(ar)-1;i>=0;i--) {
                System.out.print(ar[i]);
            }
            System.out.println("");
            Arrays.fill(ar,-1);
            ar[0]=1;
        }
    }
    public static int[] multiply(int []ar,int i)
    {
       System.out.println("in multiply function for multiplication with "+i);
        int carry=0;
        int endIndex=getInd(ar);
        for (int j = 0; j <endIndex; j++) {
            int a=ar[j]*i;
            String s=""+(a+carry);
            if(s.length()>1)
            {
                ar[j]=Integer.parseInt(s.substring(1,s.length()));
                carry=Integer.parseInt(""+s.charAt(0));
            }
            else
            {
                ar[j]=Integer.parseInt(""+s.charAt(0));
                carry=0;
            }
       System.out.println("for j= "+j+" ,ar= "+ar[j]+" and carry= "+carry);
        }
        if(carry!=0)
            ar[endIndex]=carry;
        return ar;
    }
    public static int getInd(int []ar)
    {
        for (int i = 0; i < ar.length; i++) {
            if(ar[i]==-1)
                return i;
        }
    return ar.length-1;
    }
    
}
