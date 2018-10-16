package spojmicemaze;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SpojMiceMaze {

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
        try
        {
            Reader sc=new Reader();
//            int n=Integer.parseInt(sc.readLine());
//            int exitCell=Integer.parseInt(sc.readLine());
//            int timeLimit=Integer.parseInt(sc.readLine());
//            int numOfEdges=Integer.parseInt(sc.readLine());
//            Scanner sc=new Scanner(System.in);
            int n=sc.nextInt();
            int exitCell=sc.nextInt();
            int timeLimit=sc.nextInt();
            int numOfEdges=sc.nextInt();
            //System.out.println("n= "+n+" ,exitcell= "+exitCell+" ,timelimit= "+timeLimit+" and numedges= "+numOfEdges);
            graph  g=new graph();
            for (int i = 0; i < n; i++) {
                g.addVertex(new vertex(i+1));
            }
           // System.out.println("going to add edges");
            for (int i = 0; i < numOfEdges; i++) {
               // String []sp=(sc.nextLine()).split(" ");
//                int source=Integer.parseInt(sp[0]);
//                int destination=Integer.parseInt(sp[1]);
//                int weight=Integer.parseInt(sp[2]);
                int source=sc.nextInt();
                int destination=sc.nextInt();
                int weight=sc.nextInt();
                g.addEdge(g.vertexList.get(source-1),g.vertexList.get(destination-1),weight);
                //System.out.println("i= "+i);
            }
            g.bfs(g.vertexList.get(exitCell-1),timeLimit);
            System.out.println(g.sum);
        }
        catch(Exception e)
        {       
        }
    }   
}
class graph // it is a directed and weighted graph
{
    public static int sum=1;
    public ArrayList<vertex> vertexList;
    public  int numOfVertices;
    public ArrayList<LinkedList<destedge>> adjList;
    public Queue<destedge> queue;
    public graph() {
        vertexList=new ArrayList<>();
        adjList= new ArrayList<>();
        numOfVertices=0;
        queue=new LinkedList<>();
    }
    public  void addVertex(vertex vert)
    {
       vertexList.add(vert);
       adjList.add(new LinkedList<>());
       numOfVertices++;
    }
    public void addEdge(vertex source,vertex destination,int weight)
    {
        for (int i = 0; i < vertexList.size(); i++) {
            if(vertexList.get(i)==source)
            {
                destedge lp=new destedge(destination,weight);
                adjList.get(i).add(lp);
            }
        }
    }
    public destedge getAdjacent(vertex vert)//,int cutIndex)
    {
        int pos=0;
        for (int i = 0; i < numOfVertices; i++) {
            if(vertexList.get(i)==vert)
            {
                pos=i;
                break;
            }
        }
      LinkedList<destedge> p=adjList.get(pos);
       Iterator it=p.iterator();
        while(it.hasNext())
        {
            destedge j=(destedge)it.next();
            if(!j.destination.visited)
            {
                return j;
            }
        }
        return null;
     // if(cutIndex>=p.size())
       //   return null;
     //return p.get(cutIndex);
    }
    public void bfs(vertex root,int timeLimit)
    {
        if(root==null || timeLimit==0)
            return;
        destedge k=getAdjacent(root);
        root.visited=true;
        while(k!=null)
        {
            if(timeLimit-k.weight>=0)
            {
                queue.offer(k);
                k.destination.visited=true;
                sum+=1;
            }
            k=getAdjacent(root);
        }
        while(!queue.isEmpty())
        {   
            destedge p=queue.poll();
            bfs(p.destination,(timeLimit-p.weight));
        }
    }
}
    class vertex
{
    public int label;
    public boolean visited;
    public vertex(int label) {
        this.label = label;
        this.visited = false;
    }
}
class destedge
{
    vertex destination;
    int weight;

    public destedge(vertex destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }
    
}
