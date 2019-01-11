package chefmodulogame;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;

public class ChefModuloGame {
    public static PrintWriter writer=new PrintWriter(System.out);

    public ChefModuloGame() {
    }
    
    public static void main(String[] args) throws IOException {
        Reader sc=new Reader();
        int t=sc.nextInt();
        while(t-->0)
        {
            int n = sc.nextInt();
            int p=sc.nextInt();
            if(n==1)
            {
                if(p==n)
                {
                    writer.write("1");
                    writer.flush();
                    continue;
                }
                else
                {
                    long pr=p*p*p;
                    writer.write(""+pr);
                    writer.flush();
                    continue;
                }
            }
            if(n==2)
            {
                if(p==n)
                {
                    writer.write("8");
                    writer.flush();
                    continue;
                }
                else
                {
                    long pr=(p-1)*(p-1)*(p-1);
                    writer.write(""+pr);
                    writer.flush();
                    continue;
                }
            }
            int k=n/2+1;
            int alpha=n%k;
            BigInteger numOfWays=BigInteger.ZERO;
//            System.out.println("num of ways is "+numOfWays.toString()+" and alpha = "+alpha+" and k= "+k);
            if(p>n)
            {
                //setting c as k
                numOfWays=factorial(p-n);
                //setting b as k
                int aRange=p-n;
                int cRange=(p-alpha);
//                System.out.println("arange= "+aRange+" and crange= "+cRange);
                int y=cRange*aRange;
//                System.out.println("num of ways are "+numOfWays.toString());
                numOfWays=numOfWays.add(new BigInteger(""+y));
                //setting a as k
                int x=cRange*cRange;
                numOfWays=numOfWays.add(new BigInteger(""+x));
            }
            else
            {
                //here we have to mandatorily set a as k 
                int cRange=(p-alpha);
                int y=cRange*cRange;
                numOfWays=numOfWays.add(new BigInteger(""+y));
            }
            writer.write(numOfWays.toString()+"\n");
            writer.flush();
        }
        writer.close();
    }
    public static BigInteger factorial(int g)
    {
        BigInteger fact=BigInteger.ONE;
        for (int i = 2; i <=g; i++) {
            fact=fact.multiply(new BigInteger(""+i));
        }
        return fact;
    }
    public static class Reader 
    { 
        final private int BUFFER_SIZE = 1 << 16; 
        private DataInputStream din; 
        private byte[] buffer; 
        private int bufferPointer, bytesRead; 
  
        public Reader() 
        { 
            din = new DataInputStream(System.in); 
            buffer = new byte[BUFFER_SIZE]; 
            bufferPointer = bytesRead = 0; 
        } 
  
        public Reader(String file_name) throws IOException 
        { 
            din = new DataInputStream(new FileInputStream(file_name)); 
            buffer = new byte[BUFFER_SIZE]; 
            bufferPointer = bytesRead = 0; 
        } 
  
        public String readLine() throws IOException 
        { 
            byte[] buf = new byte[64]; // line length 
            int cnt = 0, c; 
            while ((c = read()) != -1) 
            { 
                if (c == '\n') 
                    break; 
                buf[cnt++] = (byte) c; 
            } 
            return new String(buf, 0, cnt); 
        } 
  
        public int nextInt() throws IOException 
        { 
            int ret = 0; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
            do
            { 
                ret = ret * 10 + c - '0'; 
            }  while ((c = read()) >= '0' && c <= '9'); 
  
            if (neg) 
                return -ret; 
            return ret; 
        } 
  
        public long nextLong() throws IOException 
        { 
            long ret = 0; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
            do { 
                ret = ret * 10 + c - '0'; 
            } 
            while ((c = read()) >= '0' && c <= '9'); 
            if (neg) 
                return -ret; 
            return ret; 
        } 
  
        public double nextDouble() throws IOException 
        { 
            double ret = 0, div = 1; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
  
            do { 
                ret = ret * 10 + c - '0'; 
            } 
            while ((c = read()) >= '0' && c <= '9'); 
  
            if (c == '.') 
            { 
                while ((c = read()) >= '0' && c <= '9') 
                { 
                    ret += (c - '0') / (div *= 10); 
                } 
            } 
  
            if (neg) 
                return -ret; 
            return ret; 
        } 
  
        private void fillBuffer() throws IOException 
        { 
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE); 
            if (bytesRead == -1) 
                buffer[0] = -1; 
        } 
  
        private byte read() throws IOException 
        { 
            if (bufferPointer == bytesRead) 
                fillBuffer(); 
            return buffer[bufferPointer++]; 
        } 
  
        public void close() throws IOException 
        { 
            if (din == null) 
                return; 
            din.close(); 
        } 
    }
}
