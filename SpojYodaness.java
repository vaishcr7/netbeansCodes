package spojyodaness;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class SpojYodaness {
static class Reader {

        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n') {
                    break;
                }
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg) {
                c = read();
            }
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg) {
                return -ret;
            }
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg) {
                c = read();
            }
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg) {
                return -ret;
            }
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg) {
                c = read();
            }

            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg) {
                return -ret;
            }
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1) {
                buffer[0] = -1;
            }
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead) {
                fillBuffer();
            }
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null) {
                return;
            }
            din.close();
        }
    }
    public static long sum=0;
    public static Map<String,Integer> mp;
    public static void main(String[] args) throws IOException {
        Reader sc=new Reader();
        int t=Integer.parseInt(sc.readLine());
        while(t-->0)
        {
            int n=Integer.parseInt(sc.readLine());
            String []incorrWords=new String[n];
            String []corrWords=new String[n];
            String []sp=(sc.readLine()).split(" ");
            for (int i = 0; i < sp.length; i++) {
                incorrWords[i]=new String(sp[i]);
            }
            String []sp2=(sc.readLine()).split(" ");
            for (int i = 0; i < sp2.length; i++) {
                corrWords[i]=new String(sp2[i]);
            }
            mp=new HashMap<>();
            for (int i = 0; i < corrWords.length; i++) {
                mp.put(corrWords[i],i);
            }
            int []arIncorrWords=new int[n];
            for (int i = 0; i < n; i++) {
             arIncorrWords[i]=mp.get(incorrWords[i]);
                //System.out.print(arIncorrWords[i]+" , ");
            }
            //System.out.println("");
            sort(arIncorrWords,0, n-1);
            System.out.println(sum);
            sum=0;
        }
    }
       public static void merge(int []arr, int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l +1;
        int n2 = r - m;
        //System.out.println("l= "+l+", m= "+m+", r= "+r+", n1= "+n1+" and n2= "+n2);
        /* Create temp arrays */
        int L[] = new int [n1];
        int R[] = new int [n2];

        /*Copy data to temp arrays*/
        for (int i=0; i<n1; ++i)
            L[i] = arr[l + i];
        for (int j=m+1; j<=r; ++j)
            R[j-m-1] = arr[j];
       /* for (int i = 0; i < L.length; i++) {
            System.out.print(L[i]+" , ");
        }
        System.out.println("");
        for (int i = 0; i < R.length; i++) {
            System.out.print(R[i]+" , ");
        }
        System.out.println("");*/
 
        /* Merge the temp arrays */
 
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;
 
        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2)
        {
           // System.out.println("k= "+k);
            if (L[i] <= R[j])
            {
                arr[k] = L[i];
                i++;
            }
            else
            {
                /*System.out.print("L= ");
                for (int p = 0; p < L.length; p++) {
                    System.out.print(L[p]+" , ");
                }
                System.out.println("");
                System.out.print("R= ");
                for (int p = 0; p < R.length; p++) {
                    System.out.print(R[p]+" , ");
                }
                System.out.println("");*/
                //System.out.println("i= "+i+" ,j= "+j+" ,k= "+k+" and L length= "+L.length);
                if(k>L.length)
                    sum+=(L.length+j-i);
                else if(k==L.length)
                    sum+=(i+j+1-k);
                else
                    sum+=(L.length+j-k);
                //System.out.print("sum increased to "+sum);
                arr[k] = R[j];
                //System.out.println(" for R["+j+"]= "+R[j]);
                j++;
            }
            k++;
        }
 
        /* Copy remaining elements of L[] if any */
        while (i < n1)
        {
            arr[k] = L[i];
            
            i++;
            k++;
        }
 
        /* Copy remaining elements of R[] if any */
        while (j < n2)
        {
            arr[k] = R[j];
            j++;
            k++;
        }
        
            /*System.out.print("final array is ");
            for (int b = 0; b < arr.length; b++) {
                System.out.print(arr[b]+" , ");
            }
            System.out.println("");*/
    }
 
    // Main function that sorts arr[l..r] using
    // merge()
    public static void sort(int[] incWds, int l, int r)
    {
        //System.out.println("calling for l= "+l+" and r= "+r);
        if (l < r)
        {
            // Find the middle point
            int m = (l+r)/2;
 
            // Sort first and second halves
            sort(incWds, l, m);
            sort(incWds, m+1, r);
 
            // Merge the sorted halves
            merge(incWds, l, m, r);
            //System.out.println("calling merge");
        }
    }
}
/*
2
6
in the force strong you are
you are strong in the force
6
or i will help you not
or i will not help you

1
5
e b c d a
a b c d e
*/