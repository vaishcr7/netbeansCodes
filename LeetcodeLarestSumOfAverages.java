package leetcodelarestsumofaverages;

import java.util.ArrayList;

public class LeetcodeLarestSumOfAverages {

    public static void main(String[] args) {
        int []ar=new int[] {4663,3020,7789,1627,9668,1356,4207,1133,8765,4649,205,6455,8864,3554,3916,5925,3995,4540,3487,5444,8259,8802,6777,7306,989,4958,2921,8155,4922,2469,6923,776,9777,1796,708,786,3158,7369,8715,2136,2510,3739,6411,7996,6211,8282,4805,236,1489,7698};
        System.out.println(largestSumOfAverages(ar, 27));
        
    }
   public static double largestSumOfAverages(int[] ar, int k) {
        double max=Double.MIN_VALUE;
        double [][]avgs=new double[ar.length][ar.length];
        for (int i = 0; i < avgs.length; i++) {
            double sums=ar[i];
            for (int j = i+1; j < avgs.length; j++) {
                sums+=ar[j];
                avgs[i][j]=sums/(j-i+1);
            }
       }
        if(k==0)
        	return avgs[0][ar.length-1];
        for(int i=1;i<ar.length;i++) {
        	ArrayList<Integer> al=new ArrayList<>();
        	al.add(i);
        	max=Math.max(markDiv(al, ar,k-1,avgs), max);
        }
        return max;
    }
    public static double markDiv(ArrayList<Integer> al,int []ar,int kRemaining,double [][]avgs) {
            double sum=0;
            System.out.println(" al: "+al+" and k= "+kRemaining);
            if(kRemaining==1)
            {
                    System.out.println("all partitions covered for this");
                    sum+=avgs[0][al.get(0)-1];
                    for(int i=1;i<al.size();i++) {
//                        System.out.println("sum is "+sum);
                            sum+=avgs[al.get(i-1)][al.get(i)-1];
                    }
                    sum+=avgs[al.get(al.size()-1)][ar.length-1];
                    System.out.println(" returning sum: "+sum);
                    return sum;
            }
            int lastElem=al.get(al.size()-1);
            double max=Double.MIN_VALUE;
            if(lastElem==ar.length-1 && al.size()==1){
                double h=avgs[0][ar.length-1];
//                System.out.println("returning "+h);
                return h;
            }
            for(int i=lastElem+1;i<ar.length;i++) {
                ArrayList<Integer> ap=new ArrayList<>(al);
                ap.add(i);
                max=Math.max(markDiv(ap, ar,kRemaining-1,avgs), max);
            }
            System.out.println(" returning max: "+max+" for lastElement: "+lastElem);
            return max;
    }
//    public static double average(int []ar,int i,int j) {
//            double sum=0;
//            for(int k=i;k<=j;k++)
//                    sum+=ar[k];
//            return (sum/(j-i+1));
//    }    
}