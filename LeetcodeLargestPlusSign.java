package leetcodelargestplussign;

import java.util.Arrays;

public class LeetcodeLargestPlusSign {

    public static void main(String[] args) {
        int []ar0=new int[]{0,1};
        int []ar1=new int[]{1,0};
        int []ar2=new int[]{1,1};
        int[][] mines=new int[][] {ar0,ar1};
        
        System.out.println(orderOfLargestPlusSign(2, mines));
    }
     public static int orderOfLargestPlusSign(int N, int[][] mines) {
        int [][]ar=new int[N][N];
         for (int i = 0; i < N; i++) {
            Arrays.fill(ar[i],1);             
         }
        for (int[] mine : mines) {
            if(mine.length==0)
            {
//                System.out.println("breaking");
                break;
            }
            ar[mine[0]][mine[1]]=0;
        }
//         System.out.println("after mines were set");
//         printArray(ar);
        //up
        int [][]arUp=new int[N][N];
        for(int j=0;j<N;j++){
            if(ar[0][j]==1)
                arUp[0][j]=1;
        }
         for (int i = 1; i < N; i++) {
            for (int j = 0; j < N; j++) {
            if(ar[i][j]!=0){
                if(arUp[i-1][j]!=0)
                    arUp[i][j]=arUp[i-1][j]+1;
                else
                    arUp[i][j]=ar[i][j];
                }
            }
        }
//         printArray(arUp);
         //left
         int [][]arLeft=new int[N][N];
        for(int j=0;j<N;j++){
            if(ar[j][0]==1)
                arLeft[j][0]=1;
        }
         for (int i = 0; i < N; i++) {
            for (int j = 1; j < N; j++) {
            if(ar[i][j]!=0){
                if(arLeft[i][j-1]!=0)
                    arLeft[i][j]=arLeft[i][j-1]+1;
                else
                    arLeft[i][j]=ar[i][j];
                }
            }
        }
//         printArray(arLeft);
         //down
         int [][]arDown=new int[N][N];
        for(int j=0;j<N;j++){
            if(ar[N-1][j]==1)
                arDown[N-1][j]=1;
        }
         for (int i = N-2; i >=0; i--) {
            for (int j = 0; j < N; j++) {
            if(ar[i][j]!=0){
                if(arDown[i+1][j]!=0)
                    arDown[i][j]=arDown[i+1][j]+1;
                else
                    arDown[i][j]=ar[i][j];
                }        
            }
        }
//         printArray(arDown);
         //right
         int [][]arRight=new int[N][N];
        for(int j=0;j<N;j++){
            if(ar[j][N-1]==1)
                arRight[j][N-1]=1;
        }
         for (int i = 0; i < N; i++) {
            for (int j = N-2; j >=0; j--) {
            if(ar[i][j]!=0){
                if(arRight[i][j+1]!=0)
                    arRight[i][j]=arRight[i][j+1]+1;
                else
                    arRight[i][j]=ar[i][j];
                }        
            }
        }
//         printArray(arRight);
//        for(int i=0;i<N;i++){
//            for(int j=0;j<N;j++){
//                System.out.print(arUp[i][j]+","+arDown[i][j]+","+arLeft[i][j]+","+arRight[i][j]);
//                System.out.print(" ;  ");
//            }
//            System.out.println("");
//        }
        
         //computing All
         int max=0;
         for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ar[i][j]=Math.min(arUp[i][j],Math.min(arDown[i][j], Math.min(arRight[i][j], arLeft[i][j]))); 
                max=Math.max(ar[i][j], max);
            }
        }
        return max;
    }
     public static void printArray(int [][]ar){
         for (int[] is : ar) {
             for (int i : is) {
                 System.out.print(i+" , ");
             }
             System.out.println("");
         }
         System.out.println("");
     }
}
