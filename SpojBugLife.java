package spojbuglife;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
class SpojBugsLife {

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
        Reader sc = new Reader();
        int t = sc.nextInt();
        int caseNumber = 1;
        while (t-- > 0) {
            int numOfBugs = sc.nextInt();
            int scenarios = sc.nextInt();
            graph gf = new graph(numOfBugs);
            for (int i = 0; i < numOfBugs; i++) {
                vertex v = new vertex(i + 1);
                gf.addVertex(v);
            }
            for (int i = 0; i < scenarios; i++) {
                int n1 = sc.nextInt();
                int n2 = sc.nextInt();
                gf.addEdge(gf.vertexList.get(n1 - 1), gf.vertexList.get(n2 - 1));
            }
            boolean match = gf.colorNumber();
            if (match) {
                System.out.println("Scenario #" + caseNumber++ + ":\n" + "No suspicious bugs found!");
            } else {
                System.out.println("Scenario #" + caseNumber++ + ":\n" + "Suspicious bugs found!");
            }
        }
    }
}
class graph // it is a directed graph
{

    public ArrayList<vertex> vertexList;
    //public int numOfVertices;
    //public ArrayList<LinkedList<vertex>> adjList;
    public int[][] adjMatrix;
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

    public void addEdge(vertex source, vertex destination) {//undirected graph
     /*   for (int i = 0; i < numOfVertices; i++) {
            if (vertexList.get(i) == source) {
                adjList.get(i).add(destination);
            }
            if (vertexList.get(i) == destination) {
                adjList.get(i).add(source);
            }
        }*/
     adjMatrix[source.label-1][destination.label-1]=1;
     adjMatrix[destination.label-1][source.label-1]=1;
    }

    public  int [] getAdjacent(vertex vert) {
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
       return adjMatrix[vert.label-1];
    }

   /* public int getIndex(vertex f) {
        for (int i = 0; i < numOfVertices; i++) {
            if (vertexList.get(i) == f) {
                return i;
            }
        }
        return -1;
    }

    public void printList(LinkedList<vertex> al) {
        for (vertex object : al) {
            System.out.print(object.label + " , ");
        }
        System.out.println("");
    }*/
    
    public boolean colorNumber() {
       /* for (int i = 0; i < adjList.size(); i++) {
            LinkedList<vertex> list = adjList.get(i);
            if(vertexList.get(i).color==0)
                vertexList.get(i).color=1;
            for (int j = 0; j < list.size(); j++) {
                if(vertexList.get(i)==list.get(j))// if there is a self edge
                    return false;
                if (list.get(j).color==0)
                    list.get(j).color=assignColor(vertexList.get(i).color);
                else if(list.get(j).color==vertexList.get(i).color)
                    return false;
            }
        }
        return true;*/
        for (int i = 0; i < adjMatrix[0].length; i++) {
            int []adjArr=adjMatrix[i];
            if(vertexList.get(i).color==0)
            {
                vertexList.get(i).color=1;
            }
            for (int j = 0; j < adjArr.length; j++) {
                if(adjMatrix[i][j]==1)
                {
                    if(vertexList.get(j).color==0)
                    {
                        vertexList.get(j).color=(vertexList.get(i).color==1?2:1);
                    }
                    else if(vertexList.get(i).color==vertexList.get(j).color)
                        return false;
                }
            }
        }
        return true;
    }

   /* public int assignColor(int i) {
        switch (i) {
            case 1:
                return 2;
            default:
                return 1;
        }
    }*/
    
}
class vertex {
    public int label;
    public boolean visited;
    public int color;

    public vertex(int label) {
        this.label = label;
        visited = false;
        color = 0;
    }
}