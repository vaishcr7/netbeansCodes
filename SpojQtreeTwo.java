package spojqtreetwo;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;

public class SpojQtreeTwo {
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
           graph g=new graph(n);
           for (int i = 1; i <=n; i++) {
               g.addVertex(new vertex(i));
           }
           for (int i = 0; i < n-1; i++) {
               g.addEdge(g.vertexList.get(sc.nextInt()-1),g.vertexList.get(sc.nextInt()-1), sc.nextInt());
           }
           String s=sc.readLine();
           while(!"DONE".equals(s))
           {
               String[] st=s.split(" ");
               if(st.length==3)
               {
                   int source=Integer.parseInt(st[1]);
                   int destination=Integer.parseInt(st[2]);
               }
               else
               {
                    int source=Integer.parseInt(st[1]);
                   int destination=Integer.parseInt(st[2]);
                   int k=Integer.parseInt(st[3]);
               }
           }
       }
       
    }
    
}
class graph // it is a non-directed and weighted graph
{
    public int [][]ar;
    public ArrayList<vertex> vertexList;
    public Map<String,Integer> edWeights; // edges will be defined as source-destination as keys and their weights as values 
    public  int numOfVertices;
    //public ArrayList<LinkedList<vertex>> adjList;
    public PriorityQueue<vertex> pq;
    public graph(int size) {
        numOfVertices=size;
        ar=new int[numOfVertices][numOfVertices];
        for (int i = 0; i < numOfVertices; i++) {
            Arrays.fill(ar[i],-1);
        }
        //adjList=new ArrayList<>();
        vertexList=new ArrayList<>();
        pq=new PriorityQueue<>();
    }
    public  void addVertex(vertex vert)
    {
       vertexList.add(vert);
      // numOfVertices++;
    }
    public void addEdge(vertex source,vertex destination,int weight)
    {
       /* for (int i = 0; i < vertexList.size(); i++) {
            if(vertexList.get(i)==source)
            {
                adjList.get(i).add(destination);
                edWeights.put(""+source+"-"+destination,weight);
            }
        }*/
       ar[source.label][destination.label]=weight; 
        ar[destination.label][source.label]=weight;
    }
    public void primsAlgo(vertex source,vertex destination)
    {
        source.key=0;
        
    }
    
    public int getAdjacent(vertex vert)
    {
        System.out.println("inside adjacent function");
        int pos=0;
        /*for (int i = 0; i < numOfVertices; i++) {
            if(vertexList.get(i)==vert)
            {
                pos=i;
                break;
            }
        }
        return null;*/
        int minPos=Integer.MAX_VALUE;
        for (int i = 0; i < numOfVertices; i++) {
            if(i!=vert.label-1)
            {
                if(!vertexList.get(i).visited && ar[vert.label-1][i]<minPos)
                    minPos=ar[vert.label-1][i];
             }
        }
        vertexList.get(minPos).visited=true;
        return minPos;
    }
   /* public int returnVertexIndex(vertex v)
    {
        for (int i = 0; i < vertexList.size(); i++) {
            if(vertexList.get(i)==v)
                return i;
        }
        return 0;// had to return something for the function to work properly
    }*/
    public  int getEdgeWeight(vertex source,vertex destination)
    {
        //return edWeights.get(""+source+"-"+destination);
        return ar[source.label][destination.label];
    }
}
class vertex
{
    public int label;
    public int key;
    public boolean visited;
    public vertex(int label) {
        this.label = label;
        this.key=Integer.MAX_VALUE;
        this.visited=false;
    }
}
