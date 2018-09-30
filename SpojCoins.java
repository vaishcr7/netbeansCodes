package spojcoins;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SpojCoins {
    static class Reader {

        final private int BUFFER_SIZE = 1 << 32;
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
            byte[] buf = new byte[5010]; // line length
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
    
 public static Map<Integer,Long> mp;
 public static void main(String[] args) {
Reader sc=new Reader();
StringBuilder sba=new StringBuilder();
try
{
mp=new HashMap<>();
mp.put(0,0L);
mp.put(1,1L);
mp.put(2,2L);
mp.put(3,3L);
mp.put(4,4L);
String s=sc.readLine();
while(s!=null || s.length()>0)
{
    int num=Integer.parseInt(s);
    sba.append(""+coins(num)).append("\n");
    s=sc.readLine();
}
//System.out.println("sba= "+sba.toString());
//System.out.print((sba.delete(sba.length()-2,sba.length()-1)).toString());
}
catch(Exception e)
{
   //System.out.println("yup this is an exception");
    //sba.delete(sba.length()-1,sba.length());
    System.out.print(sba.toString());
}
    }
 public static long coins(int num)
 {
    //System.out.println("inside coins function for number: "+num);
    long sum=0L;
    int num2=(int)Math.ceil(num/2);
    if(!mp.containsKey(num2))
            mp.put(num2,coins(num2));
    //System.out.println("for "+num2+" coins , change is "+mp.get(num2));
    int num3=(int)Math.ceil(num/3);
    if(!mp.containsKey(num3))
            mp.put(num3,coins(num3));
    //System.out.println("for "+num3+" coins , change is "+mp.get(num3));
    int num4=(int)Math.ceil(num/4);
     if(!mp.containsKey(num4))
            mp.put(num4,coins(num4));
     //System.out.println("for "+num4+" coins , change is "+mp.get(num4));
     sum+=mp.get(num2)+mp.get(num3)+mp.get(num4);
     if(sum<num)
         sum=num;
     return sum;
 }    
}
