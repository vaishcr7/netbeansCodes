package spojmicemaze;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

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
            graph  g=new graph(exitCell-1);
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
            if(timeLimit<1)
                System.out.println("0");
            else
            {
                g.beforeRunningbfs();
                g.controllingFunc(timeLimit);
                System.out.println(g.sum);
            }
        }
        catch(Exception e)
        {       
        }
    }   
}
class graph // it is a directed and weighted graph
{
    public static int sum=0;
    public  int exitCellVertexIndex;
    public ArrayList<vertex> vertexList;
//    public ArrayList<vertex> visitedVertices;
    public  int numOfVertices;
    public ArrayList<LinkedList<edge>> adjList;
    public Queue<vertex> queue;
    public graph(int index) {
        exitCellVertexIndex=index;
        vertexList=new ArrayList<>();
        adjList= new ArrayList<>();
        numOfVertices=0;
        queue=new LinkedList<>();
//        visitedVertices=new ArrayList<>();
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
                edge lp=new edge(source,destination,weight);
                adjList.get(i).add(lp);
            }
        }
    }
    public edge getAdjacent(vertex vert)//,int cutIndex)
    {
        int pos=0;
        for (int i = 0; i < numOfVertices; i++) {
            if(vertexList.get(i)==vert)
            {
                pos=i;
                break;
            }
        }
      LinkedList<edge> p=adjList.get(pos);
       Iterator it=p.iterator();
        while(it.hasNext())
        {
            edge j=(edge)it.next();
            if(!j.destination.visited)
            {
                j.destination.visited=true;
                return j;
            }
        }
        return null;
     // if(cutIndex>=p.size())
       //   return null;
     //return p.get(cutIndewx);
    }
    public void beforeRunningbfs()
    {
        queue.offer(vertexList.get(exitCellVertexIndex));
        vertexList.get(exitCellVertexIndex).visited=true;
    }
    public void printQueueContents()
    {
        Iterator it=queue.iterator();
        System.out.println("--------------------------------");
        while(it.hasNext())
        {
            vertex k=(vertex)it.next();
            System.out.println(k.label+" -> "+k.timeExhausted);
        }
        System.out.println("--------------------------------");
    }
    public void bfsmodif(int timeLimit)
    {
//        if(queue.isEmpty())
//            return;
//        vertex root=queue.poll();
//        System.out.println("current root= "+root.label);
//        if(timeLimit-root.timeExhausted==0)
//        {
//            System.out.println("not using any path through this root");
//            bfsmodif(timeLimit);
//        }
//        System.out.println("still some time left");
//        root.visited=true;
//        edge p=getAdjacent(root);
//        ArrayList<vertex> markUnvisited=new ArrayList<>();
//        if(p==null)
//            System.out.println("no adjacent nodes");
//        while(p!=null)
//        {
//              System.out.println("adjacent vertex is "+p.destination.label+" for which net time= "+(timeLimit-(root.timeExhausted+p.weight)));
//              if(p.destination.visited)
//                  System.out.println("already visited earlier");
//              p.destination.visited=true;
//              if(timeLimit-(root.timeExhausted+p.weight)>=0)
//              {
//                  sum+=1;
//                  System.out.println("this adjacent vertex can be reached from exit cell");
//                  p.destination.timeExhausted+=(root.timeExhausted+p.weight);
//                  queue.offer(p.destination);
//                  printQueueContents();
//              }
//              else
//                  markUnvisited.add(p.destination);// maybe we can reach this from other vertices 3rd test case
//             p=getAdjacent(root); 
//        }
//        for (int i = 0; i < markUnvisited.size(); i++) {
//            markUnvisited.get(i).visited=false;
//        }
//        bfsmodif(timeLimit);
    }
    public  int controllingFunc(int tL){
        int count=0;
        vertex cell=vertexList.get(exitCellVertexIndex);
        for(int i=0;i<vertexList.size();i++){
            disjkstra(vertexList.get(i),cell);
            if(cell.timeExhausted<=tL)
                count++;
            if(i==vertexList.size()-1)  // no need to reset the time and visited pointers for the last iteration
                break;
            for (int j = 0; j < vertexList.size(); j++) {
                vertexList.get(j).visited=false;
                vertexList.get(j).timeExhausted=0;
            }
        }
        return count;
    }
    public int disjkstra(vertex v,vertex exitCell){
        v.visited=true;  
        v.timeExhausted=0;
        PriorityQueue<edge> pq=new PriorityQueue<>(new Comparator<edge>(){
            @Override
            public int compare(edge t, edge t1) {
                if(t.weight<=t1.weight)
                    return -1;
                else
                    return 1;
            }
        });

        adjList.get(v.label).forEach(e-> {  pq.add(e); });
        
        while(!pq.isEmpty()){
            edge e=pq.poll();
            vertex destAdjListHead=e.destination;
            if(!adjList.get(e.destination.label).isEmpty())
            {
                adjList.get(destAdjListHead.label).forEach(p -> {  pq.add(p);  });
            }
            if(e.destination.timeExhausted>(e.weight+e.source.timeExhausted))
                e.destination.timeExhausted=(e.weight+e.source.timeExhausted);
        }
        return exitCell.timeExhausted;
    }
}
    class vertex
{
    public int label,timeExhausted;
    public boolean visited;
    public vertex(int label) {
        this.label = label;
        this.visited = false;
        this.timeExhausted=Integer.MAX_VALUE;
    }
}
class edge
{
    vertex source,destination;
    int weight;

    public edge(vertex source,vertex destination, int weight) {
        this.source=source;
        this.destination = destination;
        this.weight = weight;
    }
    
}