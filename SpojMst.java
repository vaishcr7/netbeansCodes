package spojmst;

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

public class SpojMst {
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
         try{
            Reader sc=new Reader();
            int n=sc.nextInt();
            int m=sc.nextInt();
            graph g=new graph();
            for (int i = 0; i < n; i++) {
                 g.addVertex(new vertex(i+1));
             }
           for (int i = 0; i < m; i++) {
                 vertex src=g.vertexList.get(sc.nextInt()-1);
                 vertex dest=g.vertexList.get(sc.nextInt()-1);
                 int weight=sc.nextInt();
                 g.addEdge(src, dest, weight);
             }
             System.out.println(g.primAlgo());
         }
        catch(Exception e){
//            System.out.println("error occured");
        }
    }
    
}
class graph // it is a directed and weighted graph
{


    public ArrayList<vertex> vertexList;

    public int numOfVertices;
    public ArrayList<vertex> artPoints;

    public ArrayList<LinkedList<edge>> adjList;

    public graph() {


        vertexList = new ArrayList<>();

        adjList = new ArrayList<>();
        artPoints=new ArrayList<>();

        numOfVertices = 0;

    }

    public void addVertex(vertex vert) {

        vertexList.add(vert);

        adjList.add(new LinkedList<>());

        numOfVertices++;

    }

   public void addEdge(vertex source, vertex destination, int weight) {

                edge lp = new edge(source, destination,weight);
                adjList.get(source.label-1).add(lp);
                lp = new edge(destination, source, weight);
                adjList.get(destination.label-1).add(lp);
        }
        
     public vertex getAdjacent(vertex vert) {
         LinkedList<edge> p = adjList.get(vert.label-1);
//         System.out.println("inside getAdjacent function which has neighbours "+p.size());
         Iterator it = p.iterator();
         while (it.hasNext()) {
             edge j = (edge) it.next();
             if (!j.destination.visited) {
                 j.destination.visited = true;
//                 System.out.println("returning neighbour "+j.destination.label);
                 return j.destination;
             }
         }
         return null;
    }
     public long primAlgo(){
         Map<vertex,Boolean> mp=new  HashMap<>();
         PriorityQueue<vertex> pq=new PriorityQueue<>(new Comparator<vertex>(){
             @Override
             public int compare(vertex t, vertex t1) {
              if(t.value<=t1.value)   
                  return -1;
              else
                  return 1;
             } 
         });
         vertexList.get(0).value=0;
         for (int i = 0; i < vertexList.size(); i++) {
             pq.add(vertexList.get(i));
         }
         while(mp.size()!=vertexList.size()){
             vertex v=pq.peek();
             v.visited=true;
//             System.out.println("considering vertex "+v.label);
             if(mp.containsKey(v))
             {
                 pq.poll();
//                 System.out.println("popping it");
             }
             else{
                 mp.put(v,true);
                 if(v!=vertexList.get(0))
                 {
                     if(v.value==Integer.MAX_VALUE)
                         v.value=0;
                 }
                 LinkedList<edge> lt=adjList.get(v.label-1);
                 for (edge e: lt) {
                     if(!e.destination.visited)
                     {
//                         System.out.println("old value of  "+e.destination.label+" is "+e.destination.value);
                        e.destination.value=Math.min(e.destination.value,e.weight);
//                        System.out.println("new value is "+e.destination.value);
                     }
                 }
             }
         }
         long sum=0;
         for (int i = 0; i < vertexList.size(); i++) {
             sum+=vertexList.get(i).value;
//             System.out.println("updated sum is "+sum);
         }
         return sum;
     }
     
}

class vertex {

    public int label;
    public int value;
    public boolean visited;

    public vertex(int label) {

        this.label = label;
        this.value=Integer.MAX_VALUE;
        this.visited=false;
    }

}

class edge {

    vertex source, destination;

    int weight;

    public edge(vertex source, vertex destination, int weight) {

        this.source = source;

        this.destination = destination;

        this.weight = weight;

    }
}