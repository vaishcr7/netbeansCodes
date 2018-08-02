package spojyodaness;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

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
            for (int i = 0; i < n; i++) {
                incorrWords[i]=new stObj(sp[i],i);
            }
            String []sp2=(sc.readLine()).split(" ");
            for (int i = 0; i < n; i++) {
                corrWords[i]=new stObj(sp2[i],i);
            }
            System.out.println(yodaLevelMergeSort(incorrWords,corrWords));
        }
    }
    public static long yodaLevelMergeSort(stObj[] wrong,stObj[] correct)
    {
        long pairs=0;
        
    }
       public static stObj[] merge(stObj []arr, int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l +1;
        int n2 = r - m;
        System.out.println("l= "+l+", m= "+m+", r= "+r+", n1= "+n1+" and n2= "+n2);
        /* Create temp arrays */
        stObj L[] = new stObj [n1];
        stObj R[] = new stObj [n2];
 
        /*Copy data to temp arrays*/
        for (int i=0; i<n1; ++i)
            L[i] = arr[l + i];
        for (int j=m+1; j<=r; ++j)
            R[j-m-1] = arr[j];
        for (int i = 0; i < L.length; i++) {
            System.out.print(L[i]+" , ");
        }
        System.out.println("");
        for (int i = 0; i < R.length; i++) {
            System.out.print(R[i]+" , ");
        }
        System.out.println("");
 
        /* Merge the temp arrays */
 
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;
 
        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2)
        {
            if (L[i].pos <= R[j].pos)
            {
                arr[k] = L[i];
                sum+=1;
                //arr[k].pos= 
                //L[i].pos=
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
        return arr;
    }
 
    // Main function that sorts arr[l..r] using
    // merge()
    public static void sort(stObj []incWds, int l, int r)
    {
        System.out.println("calling for l= "+l+" and r= "+r);
        if (l < r)
        {
            // Find the middle point
            int m = (l+r)/2;
 
            // Sort first and second halves
            sort(incWds, l, m);
            sort(incWds, m+1, r);
 
            // Merge the sorted halves
            incWds=merge(incWds, l, m, r);
            System.out.println("calling merge");
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
