import java.util.Scanner;
public class MyClass {
    public static void main(String args[]) {
       Scanner sc=new Scanner(System.in);
       int t=sc.nextInt();
       while(t-- >0)
       {
        int num=sc.nextInt();  
        StringBuilder sba=new StringBuilder();
        sba.append(""+num);
        int len=sba.length();
        
        int mid=0;
        if(len%2==0)
            mid=(len+1)/2;
        else
            mid=len/2;
            
        int space=1;  
        int i=mid;
        while(i<sba.length() && sba.charAt(i)>8)
        {
            i++;
            space+=1;
        }
        sba.replace(i,i+1,sba.charAt(i)+1);
        sba.replace(i-space,i+1-space,sba.charAt(i)+1);
       }
    }
}
