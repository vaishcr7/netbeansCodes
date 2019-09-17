import java.util.ArrayList;

public class abcd {
	public static void main(String []args) {
		int []ar=new int[] {9,1,2,1,9};
		System.out.println(largestSumOfAverages(ar, 3));
	}
	public static double largestSumOfAverages(int[] ar, int k) {
        double max=Double.MIN_VALUE;
        if(k==0)
        	return average(ar, 0, ar.length-1);
        for(int i=1;i<ar.length;i++) {
        	ArrayList<Integer> al=new ArrayList<Integer>();
        	al.add(i);
        	max=Math.max(markDiv(al, ar,k-1), max);
        }
        return max;
    }
	public static double markDiv(ArrayList<Integer> al,int []ar,int kRemaining) {
		double sum=0;
		if(kRemaining==1)
		{
			sum+=average(ar, 0, al.get(0)-1);
			for(int i=1;i<al.size()-1;i++) {
				sum+=average(ar, al.get(i-1), al.get(i)-1);
			}
			sum+=average(ar, al.get(al.size()-1),ar.length-1);
			return sum;
		}
		int lastElem=al.get(al.size()-1);
		double max=Double.MIN_VALUE;
		for(int i=lastElem+1;i<ar.length;i++) {
        	ArrayList<Integer> ap=new ArrayList<Integer>(al);
        	ap.add(i);
        	max=Math.max(markDiv(ap, ar,kRemaining-1), max);
        }
		return max;
	}
	public static double average(int []ar,int i,int j) {
		double sum=0;
		for(int k=i;k<=j;k++)
			sum+=ar[k];
		return (sum/(j-i+1));
	}
}
