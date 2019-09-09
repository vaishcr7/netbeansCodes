package leetcodeDivisorGame;



public class LeetcodeDivisorGame {
	
	public static void main(String []args) {
		System.out.println((new LeetcodeDivisorGame()).minSteps(6));
	}
	public int minSteps(int n) {
	       if(n<=1)
                    return 0;
//            if(n==2)
//                return 2;
            return step('c', 1, 1, n, 1);
    }
    public static int step(char lastStep,int n,int t,int dest,int curSteps) {
//            System.out.println("lastStep= "+lastStep+" n= "+n+" t= "+t+" dest= "+dest+" curSteps= "+curSteps);
            if(n>dest)
                    return Integer.MAX_VALUE;
            if(n==dest)
                    return curSteps;
            if(lastStep=='c') {
                    n+=t;
                    int y= step('p',n,t,dest,curSteps+1);
//                    System.out.println("returning "+y);
                    return y;
            }
            else {
                    int p=step('c', n, n, dest, curSteps+1);
                    n+=t;
                    int q=step('p', n, t, dest, curSteps+1);
//                    System.out.println("p= "+p+" and q= "+q+" for lastStep= "+lastStep+" n= "+(n-t)+" t= "+t+" dest= "+dest+" curSteps= "+curSteps);
//                    System.out.println("returning "+Math.min(p, q));
                    return Math.min(p,q);
            }
    }
}
