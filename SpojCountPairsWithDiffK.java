import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class CountPairsWithDiffK {
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
    public static void main(String[] args) {
     Reader sc=new Reader();
     try
     {
         int n=sc.nextInt();
         int k=sc.nextInt();
         int []ar=new int[n];
         for(int i=0;i<n;i++){
             ar[i]=sc.nextInt();
         }
         Arrays.sort(ar);
         int count=0;
         for(int i=0;i<n;i++){
             if(binSrch(ar, ar[i]+k,i))
                count++;
         }
         System.out.println(count);
         sc.close();
     }
     catch(Exception e){
         System.out.println(e);
     }        
    }
    public static boolean binSrch(int []ar,int target,int start){
        int low=start;
        int high=ar.length-1;
        // System.out.println("came for element "+ar[start]);
        while(low<high)
        {
            // System.out.println("intial low: "+low+" high "+high);
            if(ar[(low+high)/2]==target)
                {
                    // System.out.println("true for "+target+" for element "+ar[start]);
                    return true;
                }
            else if(ar[(low+high)/2]<target)
                low=((low+high)/2)+1;
            else
                high=((low+high)/2)-1;
            // System.out.println("low: "+low+" high "+high);
        }
        if(ar[low]==target)
            return true;
        return false;            
    }
}
