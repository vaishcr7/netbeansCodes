package spojinvcnt;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
class SpojInvcnt {
    
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

    public static void main(String[] args) throws IOException {
        Reader sc=new Reader();
        int t=sc.nextInt();
        while(t-->0)
        {
         int n=sc.nextInt();
         int []ar=new int[n];
          for (int i = 0; i < n; i++) {
                ar[i]=sc.nextInt();
          }
          int []bitree=new int[n+1];
          Map<Integer,Integer> mp=new HashMap<>();
            for (int i = 1; i < n+1; i++) {
                mp=getRange(i, mp);
                System.out.println("ran with mp: "+mp);
            }
            System.out.println("map is of size: "+mp.size());
          for(Map.Entry<Integer,Integer> entry: mp.entrySet())
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
    public static int getRelative(int num,char ch)
    {
        BitSet b=new BitSet(num);
        b.flip(0,b.length());
        int lastRIghtZeroPos,lastRightOnePos=0,i;
        if(b.get(b.length()-1))
            lastRightOnePos=b.length()-1;
         for (i = b.nextClearBit(0); i >= 0; i = b.nextClearBit(i+1)){
            if(true);
        }
        lastRIghtZeroPos=i;
        if(lastRightOnePos>lastRIghtZeroPos)
        {
            b.clear(lastRIghtZeroPos+1,b.length());
            b.set(lastRIghtZeroPos);
        }
        else
        {
            b.set(lastRIghtZeroPos);
        }
        int value = 0;
        for (int j = 0; j < b.length(); ++i) {
            value += b.get(i) ? (1<< j) : 0;
         } 
        value&=num;
        if(ch=='-')             //getParent
            value-=num;
        else                      //getNext
            value+=num;
        return value;
    }    
    public static Map<Integer,Integer> getRange(int ind,Map<Integer,Integer> mp)
    {
        int pow=0;
        int k=(int)Math.pow(2,pow);
        int sum=0;
        if(ind!=1 && Math.ceil((int)Math.log(ind)/Math.log(2))==Math.floor((int)Math.log(ind)/Math.log(2)))
        {
            mp.put((int)Math.ceil((int)Math.log(ind)/Math.log(2)),0);
            System.out.println("found a perfect power at ind= "+ind);
            return mp;
        }
        while(sum!=ind)
        {
            while(k<=(ind-sum))
            {
                System.out.println("k loop= "+k+" and ind= "+ind);
                if(k==ind)
                {
                    if(sum==0)
                        mp.put(ind,0);
                    else
                        mp.put(sum,sum+k-1);
                    break;
                }
                else if(k<ind)
                {
                    pow+=1;
                    k=(int)Math.pow(2,pow);
                    System.out.println("going to higher ");
                }
            }
            if(sum==ind)
                break;
            System.out.println("current k= "+k);
            if(pow!=0)
                pow-=1;
            sum+=(int)Math.pow(2,pow);
            pow=0;
            k=1;
        }  
        System.out.println("returning mp of size "+mp.size());
        return mp;
    }
}//1 5 2 3 8 6 1
