package spojwordsone;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SpojWordsOne {
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
    public static void main(String[] args) {
    try {
        Reader sc=new Reader();
        int t=Integer.parseInt(sc.readLine());
        while(t-->0)
        {
            int n=Integer.parseInt(sc.readLine());
            graph gf=new graph(n);
            for (int i = 0; i < n; i++) {
                gf.addVertex(new vertex(sc.readLine()), i);
            }
            gf.computeEdges();
            System.out.println(gf.dfs());
        }
    } catch (IOException ex) {
        System.out.println("exception thrown");
    }
    }
    
}
class graph
{
   int [][]adjMat;
   int numOfVertices;
   ArrayList<vertex> vertexList;
   Map<Character,ArrayList<vertex>> mpOfInitialLetters;
   public graph(int numOfVertices) {
       this.adjMat = new int[numOfVertices][numOfVertices];
       this.numOfVertices = numOfVertices;
       vertexList=new ArrayList<>();
       mpOfInitialLetters=new HashMap<>();
   }
   public void addVertex(vertex a,int ind)
   {
       a.matrixIndex=ind;
       vertexList.add(a);
       ArrayList<vertex> al;
       if(mpOfInitialLetters.containsKey(a.label.charAt(0)))
       {
           al=mpOfInitialLetters.get(a.label.charAt(0));
       }
       else
       {
           al=new ArrayList<>();
       }
       al.add(a);
       mpOfInitialLetters.put(a.label.charAt(0),al);
   }
   public boolean dfs()
   {
       if(!checkForZeroDegreeVertex())
           return false;
       int verticesVisited=0;
       int stIndex=0;
       vertex root;
       while(verticesVisited!=numOfVertices && stIndex<vertexList.size())
       {
           root=vertexList.get(stIndex++);
           root.visited=true;
           while(root!=null)
           {
               ArrayList<vertex> ap=mpOfInitialLetters.get(root.label.charAt(root.label.length()-1));
               root=null;// ensuring the next vertex is new
               for (vertex k : ap) {
                if(!k.visited)
                   root=k;
                }
               if(root!=null)
               {
                   root.visited=true;
                   verticesVisited++;
               }
           }
       }
       if(stIndex>=vertexList.size())
           return false;
      /* stIndex-=1;
       for (int i = 0; i < vertexList.size(); i++) {
           vertexList.get(i).visited=false;
       }
       vertexList.get(stIndex).visited=true;*/
      return true;       
   }
   public boolean checkForZeroDegreeVertex()
   {
       for (int i = 0; i < numOfVertices; i++) {
           if(vertexList.get(i).degree==0)
               return false;// found a vertex with zero degree
       }
       return true;// if no vertices with zero degree are found
   }
   public void addEdge(vertex a,vertex b)
   {
       adjMat[a.matrixIndex][b.matrixIndex]=1;
       a.degree++;
   }
   public void computeEdges()
   {
       for (int i = 0; i < vertexList.size(); i++) {
           for (int j = 0; j < vertexList.size(); j++) {
               if(i!=j && vertexList.get(i).label.charAt(vertexList.get(i).label.length()-1)==vertexList.get(j).label.charAt(0))
               {
                   addEdge(vertexList.get(i), vertexList.get(j));
               }
           }
       }
       System.out.println("adj matrix is ");
       for (int i = 0; i < vertexList.size(); i++) {
           for (int j = 0; j < vertexList.size(); j++) {
               System.out.print(adjMat[i][j]+" , ");
           }
           System.out.println("");
           System.out.println("degree for vertex "+vertexList.get(i).label+" is "+vertexList.get(i).degree);
       }
   }
}
class vertex
{
    String label;
    boolean visited;
    int matrixIndex;
    int degree;
    public vertex(String label) {
        this.label = label;
        this.visited = false;
        degree=0;
        matrixIndex=-1;
    }
    
}
