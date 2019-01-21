import java.util.Scanner;

public class IBitEditDist {
    public static void main(String []args)
    {
        Scanner sc=new Scanner(System.in);
        String w1=sc.next();
        String w2=sc.next();
        if(w1.isEmpty() || w2.isEmpty())
        {
            System.out.println(Math.abs(w1.length()-w2.length()));
            return;
        }
        int i=w1.length()-1;
        int j=w2.length()-1;
        int minLen=(int)Math.min(i,j);
        int sum=0;
        while(i>0 && j>0)
        {
            if(w1.charAt(i)==w2.charAt(j))
            {
                i--;
                j--;
                System.out.println("case 1");
            }
            else
            {
                if(i==0)
                {
                    j--;
                    System.out.println("case 2");
                    break;
                }
                else
                {
                    if(w1.charAt(i-1)!=w2.charAt(j))
                    {
                        if(j==0)
                        {
                            i--;
                            System.out.println("case 3");
                            break;
                        }
                        else
                        {
                            if(w1.charAt(i)==w2.charAt(j-1))
                            {
                                j--;
                                System.out.println("case 4");
                            }
                            else
                            {
                                i--;
                                j--;
                                System.out.println("case 5");
                            }
                        }
                    }
                    else
                    {
                        if(j==0 && i==0)
                        {
                            i--;
                            j--;
                            System.out.println("case 6");
                            break;
                        }
                        else if(i==0)
                        {
                            j--;
                            System.out.println("case 7");
                            break;
                        }
                        else if(j==0)
                        {
                            i--;
                            System.out.println("case 8");
                            break;
                        }
                        else
                        {
                            if(w1.charAt(i-1)==w2.charAt(j-1))
                            {
                                i--;
                                j--;
                                System.out.println("case 9");
                            }
                            else
                            {
                                i--;
                                System.out.println("case 10");
                            }
                        }
                    }
                }
                sum+=1;
            }
        }
        if(i==0 && j==0)
        {
            System.out.println("here");
            if(w1.charAt(0)!=w2.charAt(0))
                sum+=1;
        }
        System.out.println("sum = "+sum);
        sum+=Math.abs(i-j);
        System.out.println("minimum steps taken are "+sum);
    }
}
