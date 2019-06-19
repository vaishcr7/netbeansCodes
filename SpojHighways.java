package spojhighways;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

public class SpojHighways {
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

        StringBuilder sba = new StringBuilder();

        try {

            Reader sc = new Reader();
            int t = sc.nextInt();
            while (t-- > 0) {

//            int n=Integer.parseInt(sc.readLine());
//            int exitCell=Integer.parseInt(sc.readLine());
//            int timeLimit=Integer.parseInt(sc.readLine());
//            int numOfEdges=Integer.parseInt(sc.readLine());
//            Scanner sc=new Scanner(System.in);
                int n = sc.nextInt();

                int numOfEdges = sc.nextInt();

                int src = sc.nextInt();
                int dest = sc.nextInt();

            //System.out.println("n= "+n+" ,exitcell= "+exitCell+" ,timelimit= "+timeLimit+" and numedges= "+numOfEdges);
                graph g = new graph(src - 1, dest - 1);

                for (int i = 0; i < n; i++) {

                    g.addVertex(new vertex(i + 1));

                }

            // System.out.println("going to add edges");
                for (int i = 0; i < numOfEdges; i++) {

               // String []sp=(sc.nextLine()).split(" ");
//                int source=Integer.parseInt(sp[0]);
//                int destination=Integer.parseInt(sp[1]);
//                int weight=Integer.parseInt(sp[2]);
                    int source = sc.nextInt();

                    int destination = sc.nextInt();

                    int weight = sc.nextInt();
                    // System.out.println("num of vertices: "+g.vertexList.size());

                    g.addEdge(g.vertexList.get(source - 1), g.vertexList.get(destination - 1), weight);

                // System.out.println("i= "+i);
                }

                // g.beforeRunningbfs();
                // int count=g.controllingFunc(timeLimit);
                // System.out.println("calling disjktra ");
                int count = g.disjkstra(g.vertexList.get(g.srcCellVertexIndex), g.vertexList.get(g.destCellVertexIndex));
                if (count != Integer.MAX_VALUE) {
                    sba.append(count).append("\n");
                } else {
                    sba.append("NONE").append("\n");
                }
            }
            System.out.println(sba.toString());
        } catch (Exception e) {
            
            System.out.println("eror occured"+e.getMessage());

        }
    }

}

class graph // it is a directed and weighted graph
{

    public int srcCellVertexIndex, destCellVertexIndex;
    ;

    public ArrayList<vertex> vertexList;

    public int numOfVertices;

    public ArrayList<LinkedList<edge>> adjList;

    public graph(int src, int dest) {

        srcCellVertexIndex = src;
        destCellVertexIndex = dest;

        vertexList = new ArrayList<>();

        adjList = new ArrayList<>();

        numOfVertices = 0;

    }

    public void addVertex(vertex vert) {

        vertexList.add(vert);

        adjList.add(new LinkedList<>());

        numOfVertices++;

    }

   public void addEdge(vertex source, vertex destination, int weight) {

            

                edge lp = new edge(source, destination, weight);

                adjList.get(source.label-1).add(lp);

            

                lp = new edge(destination, source, weight);

                adjList.get(destination.label-1).add(lp);


        }

    // public edge getAdjacent(vertex vert) {

    //     int pos = 0;

    //     for (int i = 0; i < numOfVertices; i++) {

    //         if (vertexList.get(i) == vert) {

    //             pos = i;

    //             break;

    //         }

    //     }

    //     LinkedList<edge> p = adjList.get(pos);

    //     Iterator it = p.iterator();

    //     while (it.hasNext()) {

    //         edge j = (edge) it.next();

    //         if (!j.destination.visited) {

    //             j.destination.visited = true;

    //             return j;

    //         }

    //     }

    //     return null;

    // }
    public int disjkstra(vertex v, vertex exitCell) {
        // System.out.println("src cell "+v.label);
        // System.out.println("dest cell "+exitCell.label);

        if (v == exitCell) {
            return 0;
        }

        v.visited = true;

        v.timeExhausted = 0;

        PriorityQueue<edge> pq = new PriorityQueue<>(new Comparator<edge>() {

            @Override

            public int compare(edge t, edge t1) {

                if (t.weight <= t1.weight) {
                    return -1;
                } else {
                    return 1;
                }

            }

        });

        adjList.get(v.label - 1).forEach(e -> {
            if(e.destination!=e.source)
                pq.add(e);
        });

        Map<vertex, Integer> mp = new HashMap<>();

        while (!pq.isEmpty()) {

            edge e = pq.poll();

            mp.put(e.source, 0);

            vertex destAdjListHead = e.destination;

            if (!adjList.get(e.destination.label - 1).isEmpty()) {

                adjList.get(destAdjListHead.label - 1).forEach(p -> {

                    if (!mp.containsKey(p.destination)) {
                        pq.add(p);
                    }

                });

            }

            if (e.destination.timeExhausted > (e.weight + e.source.timeExhausted)) {
                e.destination.timeExhausted = (e.weight + e.source.timeExhausted);

            }

        }

        // System.out.println("returning "+exitCell.timeExhausted);
        return exitCell.timeExhausted;

    }

}

class vertex {

    public int label, timeExhausted;

    public boolean visited;

    public vertex(int label) {

        this.label = label;

        this.visited = false;

        this.timeExhausted = Integer.MAX_VALUE;

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