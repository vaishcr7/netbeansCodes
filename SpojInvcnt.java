package spojinvcnt;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class SpojInvcnt {
    
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
            graph gf=new graph(n);
            for (int i = 0; i < n; i++) {
                gf.addVertex(new vertex(ar[i]));
            }
           // System.out.println("hello");
            for (int i = 0; i < n; i++) {
                  for (int j = i+1; j < n; j++) {
                    gf.addEdge(i,j);
                }
            }
          //  System.out.println("hi");
            System.out.println(gf.bfsCount());
        }
    }
}
class graph // it is a directed graph
{
    public ArrayList<vertex> vertexList;
    //public int numOfVertices;
    //public ArrayList<LinkedList<vertex>> adjList;
    public int[][] adjMatrix;
    Stack<vertex> st=new Stack<>();
    public graph(int n) {
        vertexList = new ArrayList<>();
        //adjList = new ArrayList<>();
        //numOfVertices = 0;
        adjMatrix=new int[n][n];
    }

    public void addVertex(vertex vert) {
        vertexList.add(vert);
      //  adjList.add(new LinkedList<>());
       // numOfVertices++;
    }

    public void addEdge(int i,int j) {//directed graph
     /*   for (int i = 0; i < numOfVertices; i++) {
            if (vertexList.get(i) == source) {
                adjList.get(i).add(destination);
            }
            if (vertexList.get(i) == destination) {
                adjList.get(i).add(source);
            }
        }*/
     adjMatrix[i][j]=1;
    }

    public  int [] getAdjacent(int pos) {
       /* int pos = 0;
        for (int i = 0; i < numOfVertices; i++) {
            if (vertexList.get(i) == vert) {
                pos = i;
                break;
            }
        }
        LinkedList<vertex> p = adjList.get(pos);
        Iterator it = p.iterator();
        while (it.hasNext()) {
            vertex j = (vertex) it.next();
            if (!j.visited) {
                return j;
            }
        }
        return null;*/
       return adjMatrix[pos];
    }

    public int getIndex(vertex f) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (vertexList.get(i) == f) {
                return i;
            }
        }
        return -1;
    }
/*
    public void printList(LinkedList<vertex> al) {
        for (vertex object : al) {
            System.out.print(object.label + " , ");
        }
        System.out.println("");
    }*/
    public  int bfsCount()
    {
        int count=0;
        ArrayList<vertex> visited =new ArrayList<>();
        vertex root=vertexList.get(0);
        st.push(root);
        while(!st.isEmpty())
        {
            root=st.pop();
            visited.add(root);
            int []ad=getAdjacent(getIndex(root));
            for (int i = 0; i < ad.length; i++) {
              //  System.out.println("hmm");
                if(ad[i]==1)
                {
                    if(!visited.contains(vertexList.get(i)))
                        st.push(vertexList.get(i));
                    if(root.label-vertexList.get(i).label>0)
                        count++;
                }
            }
        }
        return count;
    }
}
class vertex {
    public int label;
    public vertex(int label) {
        this.label = label;
    }
}