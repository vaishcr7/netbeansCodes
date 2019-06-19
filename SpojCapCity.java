package spojcapcity;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class SpojCapCity {
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
                int src=sc.nextInt();
                int dest=sc.nextInt();
                g.addEdge(g.vertexList.get(src-1),g.vertexList.get(dest-1));
            }
            ArrayList<ArrayList<vertex>> ans=g.secondDfs();
//            System.out.println("printing the answers");
            for (ArrayList<vertex> an : ans) {
                if(an.size()>1)
                {
                    Collections.sort(an, new Comparator<vertex>(){
                        @Override
                        public int compare(vertex t, vertex t1) {
                            if(t.label<t1.label)
                                return -1;
                            else
                                return 1;
                        }
                        
                    });
                System.out.println(an.size());
                for (int i = 0; i < an.size(); i++) {
                    System.out.print(an.get(i).label+" ");
                }
//                System.out.println("");
                }
            }
            sc.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    //  4 6 1 2 2 3 3 4 4 2 2 1
}
class graph // it is a directed and weighted graph
{


    public ArrayList<vertex> vertexList;

    public int numOfVertices;

    public ArrayList<LinkedList<edge>> adjList;

    public graph() {


        vertexList = new ArrayList<>();

        adjList = new ArrayList<>();

        numOfVertices = 0;

    }

    public void addVertex(vertex vert) {

        vertexList.add(vert);

        adjList.add(new LinkedList<>());

        numOfVertices++;

    }

   public void addEdge(vertex source, vertex destination){  //, int weight) {

                edge lp = new edge(source, destination);  //, weight);
                adjList.get(source.label-1).add(lp);
                
//                lp = new edge(destination, source, weight);
//                adjList.get(destination.label-1).add(lp);
        }
        
     public vertex getAdjacent(vertex vert) {
         LinkedList<edge> p = adjList.get(vert.label-1);
//         System.out.println("inside getAdjacent function which has neighbours "+p.size());
         Iterator it = p.iterator();
         while (it.hasNext()) {
             edge j = (edge) it.next();
             if (!j.destination.visited) {
//                 j.destination.visited = true;
//                 System.out.println("returning neighbour "+j.destination.label);
                 return j.destination;
             }
         }
         return null;
    }
     public ArrayDeque<vertex> dfs(){
         ArrayDeque<vertex>q=new ArrayDeque<>();
         Map<Integer,Boolean> mp=new HashMap<>();
         for(int i=2;i<=vertexList.size();i++)
             mp.put(i,false);
         vertex curVertex=vertexList.get(0);
         while(q.size()!=vertexList.size() && !mp.isEmpty()){
             q.offer(curVertex);
             curVertex.visited=true;
             mp.remove(curVertex.label);
             curVertex=getAdjacent(curVertex);
             if(curVertex==null && !mp.isEmpty())
             {
                    Iterator<Map.Entry<Integer, Boolean>> it = mp.entrySet().iterator();
                    if(it.hasNext()){
                        Map.Entry<Integer, Boolean> entry=(Map.Entry<Integer, Boolean>)it.next();
                        curVertex=vertexList.get(entry.getKey());
                    }
             }
         }
         return q;
     }
     public void reverseEdges(){
         for (vertex v : vertexList) 
             v.visited=false;
         ArrayList<LinkedList<edge>> newAdjList=new ArrayList<>();
             for (int i = 0; i < vertexList.size(); i++) {
                 newAdjList.add(new LinkedList<edge>());
             }
        for (LinkedList<edge> linkedList : adjList) {
            for (edge e : linkedList) {
                edge p=new edge(e.destination, e.source);//, e.weight);
                newAdjList.get(e.destination.label-1).add(p);
            }
         adjList=new ArrayList<>(newAdjList);
        }
//         System.out.println("new edges are ");
//         for (LinkedList<edge> linkedList : newAdjList) {
//             for (edge object : linkedList) {
//                 System.out.print(object.source.label+"->"+object.destination.label+" , ");
//             }
//             System.out.println("");
//         }
    }
    public ArrayList<ArrayList<vertex>> secondDfs(){
        ArrayDeque<vertex> st=new ArrayDeque<>(dfs());
//        System.out.println("arraydeque received is ");
//        int count=st.size();
//        while(count-->0){
//            vertex t=st.pop();
//            System.out.print(t.label+" ");
//            st.offer(t);
//        }
        System.out.println("");
        reverseEdges();
        ArrayList<ArrayList<vertex>> al=new ArrayList<>();
        while(!st.isEmpty()){
            vertex v=st.pop();
            if(!v.visited){
                ArrayList<vertex> ap=new ArrayList<>();
//                System.out.println("considering "+v.label);
                vertex temp=getAdjacent(v);
                if(temp==null)
                {
                    ap.add(v);
                    v.visited=true;
                }
                while(temp!=null){
                    v=getAdjacent(v);
                    temp=getAdjacent(v);
//                    System.out.println("adjacent is "+v.label);
                    v.visited=true;
                    ap.add(v);
                }
                al.add(new ArrayList<>(ap));
            }
//            System.out.println("new gp is ");
//            for (ArrayList<vertex> arrayList : al) {
//                for (vertex object : arrayList) {
//                    System.out.print(object.label+" , ");
//                }
//                System.out.println("");
//            }
        }
        return al;
    } 
     
}//4 6 1 2 2 3 3 4 4 2 2 1

class vertex {

    public int label;

    public boolean visited;

    public vertex(int label) {

        this.label = label;

        this.visited = false;

    }

}

class edge {

    vertex source, destination;

//    int weight;

    public edge(vertex source, vertex destination){  //, int weight) {

        this.source = source;

        this.destination = destination;

//        this.weight = weight;

    }
}