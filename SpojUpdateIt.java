package spojupdateit;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

public class SpojUpdateIt {
   
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

    public static  Map<Integer,pair> mp;
    public static long[]ar,bitree;
    public static void main(String[] args) {
    	try{
        Reader sc=new Reader();
        int t=Integer.parseInt(sc.readLine());
        String []sp=(sc.readLine()).split(" ");
        int n=Integer.parseInt(sp[0]);
        ar=new long[n];
        bitree=new long[n+1];
        mp=new HashMap<>();
//        for (int i = 1; i < n+1; i++) {
//            getRange(i);
//         }
         int i=1;        
//         for(Map.Entry<Integer,pair> entry: mp.entrySet())
//         {  
//            bitree[i++]=sumUp(entry.getValue());
//          }
            int numOfQueries=Integer.parseInt(sp[1]);
            while(numOfQueries-->0)
            {
                String []qp=(sc.readLine()).split(" ");
                int u=Integer.parseInt(qp[0]);
                int r=Integer.parseInt(qp[1]);
                int val=Integer.parseInt(qp[2]);
                //System.out.println("u= "+u+" and r= "+r+" and val= "+val);
                for (int j = u; j <=r; j++) {
                   //System.out.println("running updates");
                    runUpdate(j, val);
                }
            }
            numOfQueries=Integer.parseInt(sc.readLine());
            while(numOfQueries-->0)
            {
                int ind=Integer.parseInt(sc.readLine());
                int l=ind-1,r=ind;
                long k=getSum(r);
                long h=getSum(l);
                //System.out.println("sum till "+(r)+" = "+k);
                //System.out.println("sum till "+(l)+" = "+h);
                System.out.println((k-h));
            }
    	}
    	catch(Exception e)
    	{
    		
    	}
        }
    public static long sumUp(pair p)
    {
        long sum=0;
        int a=p.a;
        int b=p.b;
        for (int i = b; i <=a; i++) {
            sum+=ar[i];
        }
        return sum;
    }
    public static BitSet convertIntToBits(int num)
    {
        BitSet s=new BitSet();
        int i=0;
        while(num>0)
        {
            if(num%2!=0)
                s.set(i);
            i++;
            num=num>>1;
        }
        return s;
    }
    public static int getRelative(int num,char ch) //handles both functionalities by getting next (which will be used the propagate the changes in element and ) by getting parent (for calculating range sums)
    {
        BitSet b=convertIntToBits(num);
        b.flip(0,b.length());
        int lastRIghtZeroPos,lastRightOnePos=0,i,j;
        lastRightOnePos=b.nextSetBit(0);
        lastRIghtZeroPos=b.nextClearBit(0);
        int value = 0;
        for (int jj = 0; jj < b.length(); ++jj) {
            value += b.get(jj) ? (1<< jj) : 0;
         } 
        value+=1;
        //2's  complement complete
        value&=num;
        if(ch=='-')             //getParent
            value=num-value;
        else                      //getNext
            value=num+value;
        return value;
    }    
    public static void getRange(int ind)
    {
        int pow=0;
        int k=(int)Math.pow(2,pow);
        int sum=0;
        BitSet b=BitSet.valueOf(new long[]{ind});
       // System.out.println("b for ind= "+ind+" is "+b);
        if(ind!=1 && b.cardinality()==1)//returning sum of elements from 0 to ind-1 ( '-1' added for ind because 0 or the root of the tree is a dummy ) and it's a perfect power of 2
        {
            mp.put(ind,new pair(ind-1,0));
            //System.out.println("found perfect power of 2");
            return ;
        }
        while(sum!=ind)
        {
            while(k<=(ind-sum)) //  find the highest possible power of 2 which can be subtracted
            {
                //System.out.println("k loop= "+k+" and ind= "+ind);
                if(k==(ind-sum))
                {
                    if(sum==0)
                        mp.put(ind,new pair(ind-1,0)); //returning sum of elements from 0 to ind-1 ( '-1' added for ind because 0 or the root of the tree is a dummy ) and it's a perfect power of 2
                    else
                        mp.put(ind,new pair(sum+k-1,sum));
                    break;
                }
                else if(k<ind)
                {
                    pow+=1;
                    k=(int)Math.pow(2,pow);
                    //System.out.println("going to higher ");
                }
            }
            if((sum+k)==ind) // found some power of 2+constant 
                break;
          //  System.out.println("current k= "+k);
            if(pow!=0)
                pow-=1;
            sum+=(int)Math.pow(2,pow);
            pow=0;
            k=1;
        }  
       // System.out.println("returning mp of size "+mp.size());
        return ;
    }
    public static long getSum(int pos)
    {
        long  sum=0;
        int i=pos+1;
        //System.out.println("called for node: "+i);
        while(i>0 && i<bitree.length)
        {
            sum+=bitree[i];
            i=getRelative(i,'-');
        }
        return sum;
    }
    public static void runUpdate(int pos,int val)
    {
        int i=pos+1;
        while(i<bitree.length)
        {
            //System.out.println("pos= "+pos);
            bitree[i]+=val;
            ar[i-1]+=val;
            i=getRelative(i,'+');
        }
    }
}
class pair
{
    int a,b;

    public pair(int a, int b) {
        this.a = a;
        this.b = b;
    }
    
}
