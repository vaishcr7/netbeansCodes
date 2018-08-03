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
    public static void main(String[] args) throws IOException {
        Reader sc=new Reader();
        int t=sc.nextInt();
        while(t-->0)
        {
            int n=sc.nextInt();
            stObj []incorrWords=new stObj[n];
            stObj []corrWords=new stObj[n];
            String []sp=(sc.readLine()).split(" ");
            for (int i = 0; i < sp.length; i++) {
                incorrWords[i]=new stObj(sp[i],i);
            }
            String []sp2=(sc.readLine()).split(" ");
            for (int i = 0; i < sp2.length; i++) {
                corrWords[i]=new stObj(sp2[i],i);
            }
            yodaLevelMergeSort(incorrWords,corrWords);
            System.out.println("answer is = "+sum);
        }
    }
    public static void yodaLevelMergeSort(stObj[] wrong,stObj[] correct)
    {
        Map<stObj,Integer> mp=new HashMap<>();
        for (int i = 0; i < correct.length; i++) {
            mp.put(correct[i],i);
            System.out.println(correct[i].str+" -> "+i);
        }
        sort(wrong, 0, wrong.length-1, mp);
    }
       public static stObj[] merge(stObj []arr, int l, int m, int r,Map<stObj,Integer> pm)
    {
        // Find sizes of two subarrays to be merged
        Map<stObj,Integer> mp=new HashMap<>();
        int n1 = m - l +1;
        int n2 = r - m;
        //System.out.println("l= "+l+", m= "+m+", r= "+r+", n1= "+n1+" and n2= "+n2);
        /* Create temp arrays */
        stObj L[] = new stObj [n1];
        stObj R[] = new stObj [n2];
 
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
            System.out.println("L[i] is "+L[i].str+" and r[j]= "+R[j].str);
            System.out.println(pm.get(R[j]));
           Set<Entry<stObj,Integer>> st1=pm.entrySet();
            for (Entry<stObj, Integer> entry : st1) {
                System.out.println(entry.getKey().str+" , "+entry.getValue());
            }
            if (L[i].pos <= pm.get(R[j]))
            {
                arr[k] = L[i];
                mp.put(L[i],k);
                i++;
            }
            else
            {
                arr[k] = R[j];
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
        for (int z = 0; z < arr.length; z++) {
            if(mp.containsKey(arr[z]))
            {
                sum+=Math.abs(arr[z].pos-mp.get(arr[z]));
                arr[z].pos=l+mp.get(arr[z]);// ell + mp.get...
            }
        }
        return arr;
    }
 
    // Main function that sorts arr[l..r] using
    // merge()
    public static void sort(stObj []incWds, int l, int r,Map<stObj,Integer> pm)
    {
        //System.out.println("calling for l= "+l+" and r= "+r);
        if (l < r)
        {
            // Find the middle point
            int m = (l+r)/2;
 
            // Sort first and second halves
            sort(incWds, l, m,pm);
            sort(incWds, m+1, r,pm);
 
            // Merge the sorted halves
            incWds=merge(incWds, l, m, r,pm);
            //System.out.println("calling merge");
        }
    }
}
class stObj
{
    String str;
    int pos;
    public stObj(String str, int pos) {
        this.str = str;
        this.pos = pos;
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
*/
