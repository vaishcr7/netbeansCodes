package spojpalin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SpojPalin {

    public static class FastReader {

        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    public static void main(String args[]) {
        FastReader sc = new FastReader();
        int t = sc.nextInt();
        while (t-- > 0) {
            int num = sc.nextInt();
            String s = "" + num;
            StringBuilder sba = new StringBuilder();
            int len = s.length();

            int mid = 0;
            if (len % 2 == 0) {
                mid = (len - 1) / 2;
            } else {
                mid = len / 2;
            }
            if (len == 1) {
                if (num >= 0 && num <= 8) {
                    sba.append(num + 1).append("\n");
                } else {
                    sba.append(num + 2).append("\n");
                }
            } else {
                int space = (len % 2 == 0) ? 0 : 1;
                int i = mid;
                boolean allNines = true;
                for (int j = 0; j < len; j++) {
                    if (s.charAt(j) != '9') {
                        allNines = false;
                        break;
                    }
                }
                if (allNines) {
                    sba.append(num + 2).append("\n");
                    System.out.println("all nines");
                } else {
                    StringBuilder g = new StringBuilder("");
                    if (space == 0) { //for even length string
                        System.out.println("even length number");
                        if(s.charAt(i)<=s.charAt(i+1))
                        {
                            int ii=i;
                            while(ii>=0 && s.charAt(ii)=='9')
                                ii--;
                            for (int j = 0; j <=ii-1; j++) {
                                sba.append(s.charAt(j));
                            }
                            System.out.println("current sba 1 = "+sba.toString());
                            sba.append((char)(s.charAt(ii)+1));
                            System.out.println("current sba 2 = "+sba.toString());
//                            for (int j = ii+1; j <i; j++) {
//                                sba.append(s.charAt(j));
//                            }
                            if(ii!=i)
                                sba.append(s.charAt(i));
                            sba.append(sba.reverse());
                            System.out.println("current sba 3 = "+sba.toString());
//                            sba.deleteCharAt((sba.length()/2)+1);
                        }
                        else // eg) 1213->1221
                        {
                            System.out.println("smaller case for even");
                            for (int j = 0; j <=i; j++) {
                                sba.append(s.charAt(j));
                            }
                            System.out.println("current sba 1 = "+sba.toString());
                            StringBuilder p=new StringBuilder(sba.toString());
                            sba.append(p.reverse());
                            System.out.println("current sba 2 = "+sba.toString());
                        }
                    } 
                    else {  // for odd length string
                        System.out.println("odd length number");
                        boolean restAllNines = true;
                        for (int j = 0; j < len; j++) {
                            if (s.charAt(j) != '9' && j != i) {
                                restAllNines = false;
                                break;
                            }
                        }
                        if (restAllNines) {
                            System.out.println("here");
                            g.append(s);
                            System.out.println("current g 1 = "+g.toString());
                            g.replace(i, i + 1, "" + (char)(g.charAt(i) + 1));
                            System.out.println("current g  2 = "+g.toString());
                            sba.append(g).append("\n");
                        } else {  // normal code for odd length string                    
                            System.out.println("normal code for odd");
                            int ii=i;
                            while(ii>=0 && s.charAt(ii)=='9')
                                ii--;
                            for (int j = 0; j <=ii-1; j++) {
                                sba.append(s.charAt(j));
                            }
                            System.out.println("current sba 1 = "+sba.toString());
                            sba.append((char)(s.charAt(ii)+1));
                            System.out.println("current sba 2 = "+sba.toString());
//                            for (int j = ii+1; j <i; j++) {
//                                sba.append(s.charAt(j));
//                            }
                            if(ii!=i)
                                sba.append(s.charAt(i));
                            StringBuilder p=new StringBuilder(sba.toString());
                            sba.append(p.reverse());
                            System.out.println("current sba 3 = "+sba.toString());
                            sba.deleteCharAt((sba.length()/2));
                            System.out.println("current sba 4 = "+sba.toString());
                        }
                    }
//        			while(i>=0) {
//        				if(s.charAt(i)!)
//        			}
                }
            }
            System.out.println(sba.toString());
        }
    }
}
//1 9228
