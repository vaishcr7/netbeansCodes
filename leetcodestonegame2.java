package leetcodeDivisorGame;

public class LeetcodeDivisorGame {
	public static void main(String []args) {
		int []ar=new int[]{2,7,9,4,4};
		System.out.println(stoneGame(ar));
	}
	 public static int stoneGame(int[] piles) {
		 return stoneGame2(piles,0,0,'a',0,1);
	    }
	 public static int stoneGame2(int []ar,int ascore,int bscore,char user,int arIndex,int m){
	        System.out.println("current ascore= "+ascore+",bscore= "+bscore+" and cur user= "+user+" ,arindex= "+arIndex+" and m= "+m);
	        if(arIndex==ar.length )
	        	return -1;
	        int h=-1;
	        if(user=='a') {
	        	for(int i=arIndex;i<=2*m;){
	        		if(i==ar.length)
	        			break;
        		ascore+=ar[i];
        		i+=1;
        		h=Math.max(stoneGame2(ar,ascore,bscore,'b', i,Math.max(m, (i-arIndex))),ascore);
             }
	        }
	        else {
	             for(int i=arIndex;i<=2*m;){
	            	 if(i==ar.length)
	            		 break;
            	 bscore+=ar[i];
            	 i+=1;
            	 h=Math.max(bscore,stoneGame2(ar,ascore,bscore,'a', i,Math.max(m, (i-arIndex))));
	             }
	        }
	        if(user=='a' && h>ascore) {
	        	return h;
	        }
	        else if(user=='b' && h>bscore) {
	        	return h;
	        }
        	return ascore;
	    }  
}
