package chefluckygame;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class ChefLuckyGame {
    public static PrintWriter writer;
    public ChefLuckyGame() {
        writer=new PrintWriter(System.out);
    }
    public static void main(String[] args) throws IOException {
        Reader sc=new Reader();
        ChefLuckyGame c=new ChefLuckyGame();
        int t=sc.nextInt();
        while(t-->0)
        {
            int n = sc.nextInt();
            int bob = sc.nextInt();
            int alice = sc.nextInt();
            ArrayList<Integer> al = new ArrayList<>();
            ArrayList<Integer> common=new ArrayList<>();
            ArrayList<Integer> a=new ArrayList<>();
            ArrayList<Integer> b=new ArrayList<>();
            for (int i = 0; i < n; i++) {
                al.add(sc.nextInt());
            }
            if(bob==alice)
            {
                writer.write("BOB\n");
                writer.flush();
                continue;
            }
            Collections.sort(al);
            for (Integer ip : al) {
                if(ip%bob==0)
                {
                    if(ip%alice==0)
                    {
                        common.add(ip);
                        a.add(ip);
                    }
                    b.add(ip);
                }
                else
                    if(ip%alice==0)
                        a.add(ip);
            }
            if(bob==1 || alice==1)  //handling in case any of them have 1 as lucky number
            {
                if(bob==1)
                {
                    writer.write("BOB\n");
                    writer.flush();
                    continue;
                }
                else
                {
                    writer.write("ALICE\n");
                    writer.flush();
                    continue;
                }
            }
            if(!common.isEmpty() && compareLists(b, common) && a.size()<b.size())
            {
                 writer.write("BOB\n");
                 writer.flush();
                 continue;
            }
            if(b.size()>a.size())
            {
                 writer.write("BOB\n");
                 writer.flush();
            }
            else
            {
                 writer.write("ALICE\n");
                 writer.flush();
            }
        }
        writer.close();
    }
    public static boolean compareLists(ArrayList<Integer> a,ArrayList<Integer> temp)
    {
        if(a.size()!=temp.size())
            return false;
        for (int i = 0; i < a.size(); i++) {
            if(!a.get(i).equals(temp.get(i)))
                return false;
        }
        return true;
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
