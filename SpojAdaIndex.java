package spojadaindex;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class SpojAdaIndex {
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
        int n=-1,q=-1;
        try{
            n=sc.nextInt();
            q=sc.nextInt();
        }
        catch(Exception e){
            System.out.println(e.getStackTrace());
        }
            StringBuilder sba=new StringBuilder("");
            TrieNode root=new TrieNode();
            while(n-->0){
                TrieNode temp=root;
                try
                {
                sba.append(sc.readLine());
//                    System.out.println("sba= "+sba.toString());
                }
                catch(Exception e){
                    System.out.println(e.getStackTrace());
                }
                for (int i = 0; i < sba.length(); i++) {
                    int val=(int)(sba.charAt(i)-97);
//                    System.out.println("sba is "+sba.toString()+" and val for char "+sba.charAt(i)+" is "+val);
                    if(temp.children[val]==null){
                        temp.children[val]=new TrieNode();
                    }
                    temp.isEndOfWord=false;
                    temp.count+=1;
                    temp=temp.children[val];
                }
//                System.out.println("sba= "+sba.toString()+" and n= "+n);
                sba.delete(0, sba.length());
            }
            while(q-->0){
                TrieNode temp=root;
                try{
                sba.append(sc.readLine());
                }       
                catch (Exception e) {
                    System.out.println(e.getStackTrace());
                }
                boolean flag=false;
                for (int i = 0; i < sba.length()-1; i++) {
                    int val=(int)(sba.charAt(i)-97);
                    if(temp.isEndOfWord || temp.children[val]==null ){
                        flag=true;
                        break;
                    }
                    temp=temp.children[val];
                }
                if(flag || temp.isEndOfWord)
                    System.out.println("0");
                if(!flag){
//                    System.out.println("sba here is "+sba.toString());
                    int h=(int)(sba.charAt(sba.length()-1)-97);
                    temp=temp.children[h];
                    System.out.println(temp.count);
                }
                sba.delete(0, sba.length());
            }
    }    
}
class TrieNode
{
    public TrieNode [] children;
    public boolean isEndOfWord;
    public int count;

    public TrieNode() {
        children=new TrieNode[26];
        Arrays.fill(children,null);
        isEndOfWord=true;
        count=0;
    }    
}
