package spojaibohp;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Spojaibohp {
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
            byte[] buf = new byte[6110]; // line length
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
 public static Map<String,Integer> mp;
    public static void main(String[] args) {
 Reader sc=new Reader();
 try
 {
 int t=Integer.parseInt(sc.readLine());
 mp=new HashMap<>();
  while(t-->0)
 {
     String s=sc.readLine();
     System.out.println(convToPndme(s));
 }
 }
 catch(Exception e)
 {
     
 }
 } 
    public static int convToPndme(String a)
    {   
     //System.out.println("---------------------FOR STRING  "+a+"   -----------------------");
     if(mp.containsKey(a))
     {
         //System.out.println("returning stored answer");
         return mp.get(a);
     }
     if(checkIfPndme(a) || a.length()<=1)
     {
         //System.out.println("case 1");
         mp.put(a,0);
     }
     else if((a.charAt(0)==a.charAt(a.length()-1)) && a.length()>2)
     {
         //System.out.println("case 2");
        mp.put(a,convToPndme(a.substring(1, a.length()-1)));
     }
     else if((a.charAt(0)!=a.charAt(a.length()-1)) && a.length()==2)
     {
         //System.out.println("case 3");
         mp.put(a,1);
     }
     else
     {
         //System.out.println("case 4");
         mp.put(a,(1+(int)Math.min(convToPndme(a.substring(0,a.length()-1)),convToPndme(a.substring(1, a.length()))) ));
         //mp.put(a,(convToPndme(a.substring(0,a.length()-1))+convToPndme(a.substring(1, a.length()))));
     }
     if(mp.containsKey(a))
     {
        // System.out.println("returning value "+mp.get(a)+" for string "+a);
         return mp.get(a);
     }
     else
     {
        //System.out.println("couldn't find the string "+a+" in the map");
         return -1;
     }
    }
    public static boolean checkIfPndme(String s)
    {
        System.out.println("checking if "+s+" is a palindrome");
        int i=0;
        int j=s.length()-1;
        while(i<j)
        {
            if(s.charAt(i)!=s.charAt(j))
                break;
            i++;
            j--;
        }
        if(i<j)
        {
            System.out.println("not a palindrome");
            return false;
        }
        return true;
    }
}
