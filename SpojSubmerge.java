package spojsubmerge;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class SpojSubmerge {
    
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
            while(n!=0 && m!=0){
                graph g=new graph();
                for (int i = 1; i <=n; i++) {
                    g.addVertex(new vertex(i));
                }
                for (int i = 1; i <=m; i++)
                {
                    g.addEdge(g.vertexList.get(sc.nextInt()-1), g.vertexList.get(sc.nextInt()-1));
                }
                g.dfs();
                System.out.println("articulation points for this case: "+g.artPoints.size());
//                for (vertex v:g.artPoints) {
//                    System.out.print(v.label+" , ");
//                }
                System.out.println("-----------------");
                n=sc.nextInt();
                m=sc.nextInt();
            }
        }
        catch(Exception e){
            System.out.println("error occured");
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
                 j.destination.visited = true;
//                 System.out.println("returning neighbour "+j.destination.label);
                 return j.destination;
             }
         }
         return null;
    }
     public void dfs(){
         ArrayDeque<vertex> st=new ArrayDeque<>();
         st.push(vertexList.get(0));
         int pt=1;
        vertexList.get(0).discTime=0;
        vertexList.get(0).lowTime=0;
        vertexList.get(0).visited=true;
        int numberOfverticesCovered=0;
         while(!st.isEmpty()){
             vertex v=st.peek();
             System.out.println("considering "+v.label);
             vertex p=getAdjacent(v);
             if(p==null)
             {
                 System.out.println("left with no unvisited adjacent vertices "+v.label);
                 if(v!=vertexList.get(0)){
                     LinkedList<edge> le=adjList.get(v.label-1);
                     for (edge e : le)
                     {
//                         System.out.println("one");
//                         if((e.source!=e.destination.parent && e.destination.visited))  // || (e.source==e.destination.parent && e.destination.visited))
//                         {
//                             System.out.println("two ");
                             if(v.discTime<=e.destination.lowTime)
                             {
                                artPoints.add(v);
                                 System.out.println("new articulation point found "+v.label+" for edge "+e.source.label+" and "+e.destination.label);
                                 System.out.println("disc time of "+v.label+"  is "+v.discTime+" and low time is "+v.lowTime);
                                 System.out.println("disc time of adj "+e.destination.label+"  is "+e.destination.discTime+" and low time is "+e.destination.lowTime);
                                break;
                             }
//                         }
//                            else 
//                            {
                                v.lowTime=Math.min(v.lowTime, e.destination.lowTime);
                                System.out.println("new low time of "+v.label+"  is "+v.lowTime);
//                            }
//                         }
                     }
//                     v.lowTime=Math.min(v.lowTime, .lowTime);
//                                System.out.println("new low time of "+v.label+"  is "+v.lowTime);
                 }
                 numberOfverticesCovered+=1;
                 st.pop();
             }
             else
             {
                 v.children+=1;
//                 System.out.println("adding child "+p.label+" to parent "+v.label);
                 p.discTime=pt;
                 p.lowTime=pt;
                 p.parent=v;
                 st.push(p);
                 pt+=1;
             }
             if(st.isEmpty()&& numberOfverticesCovered!=vertexList.size()){
//                 System.out.println("CURRENT COVERED VERTICES NUMBER IS "+numberOfverticesCovered);
                 for (vertex object : vertexList) {
                     if(!object.visited)
                     {
                         object.discTime=pt;
                         object.lowTime=pt;
                         object.visited=true;
                         st.push(object);
                         break;
                     }
                 }
             }
         }

         if(vertexList.get(0).children>1 ) //  put in check for back edge for the other children so that they are not connected to each other.
         {             
             artPoints.add(vertexList.get(0));
             System.out.println("added root as an articulation point and it has "+vertexList.get(0).children);
         }

     } 
     
}

class vertex {

    public int label;
    public int children;
    public boolean visited;
    public int discTime;
    public int lowTime;
    public vertex parent;

    public vertex(int label) {

        this.label = label;
        this.children=0;
        this.visited = false;
        this.discTime=-1;
        this.lowTime=-1;
        this.parent=null; 
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
/*
3 3
1 2
2 3
1 3
0 0
6 8
1 3
6 1
6 3
4 1
6 4
5 2
3 2
3 5
0 0
*/