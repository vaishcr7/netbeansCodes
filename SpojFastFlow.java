import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class FastFlow {
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
    public static void main(String []args){

        StringBuilder sba = new StringBuilder();

        try {

            Reader sc = new Reader();
//            int t = sc.nextInt();
//            while (t-- > 0) {

                int n = sc.nextInt();

                int m = sc.nextInt();

//                int src = sc.nextInt();
//                int dest = sc.nextInt();

                //System.out.println("n= "+n+" ,exitcell= "+exitCell+" ,timelimit= "+timeLimit+" and numedges= "+numOfEdges);
                graph g = new graph();

                for (int i = 0; i < n; i++) {
                    g.addVertex(new vertex(i + 1));
                }

                // System.out.println("going to add edges");
                for (int i = 0; i < m; i++) {
                    int source = sc.nextInt();

                    int destination = sc.nextInt();

                    int weight = sc.nextInt();
                    // System.out.println("num of vertices: "+g.vertexList.size());

                    g.addEdge(g.vertexList.get(source - 1), g.vertexList.get(destination - 1), weight);

                    // System.out.println("i= "+i);
                }
                // System.out.println("calling disjktra ");
//            }
//            sba.deleteCharAt(sba.length()-1);
            System.out.println(sba.toString());
        } catch (Exception e) {

            System.out.println(e.getMessage());

        }
    }
}
class graph // it is a directed and weighted graph
{
    ;

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

    public void addEdge(vertex source, vertex destination, int weight) {



        edge lp = new edge(source, destination, weight);

        adjList.get(source.label-1).add(lp);



        lp = new edge(destination, source, weight);

        adjList.get(destination.label-1).add(lp);


    }

    public void disjkstra(vertex v, vertex exitCell) {
//        // System.out.println("src cell "+v.label);
//        // System.out.println("dest cell "+exitCell.label);
//
//        if (v == exitCell) {
//            return 0;
//        }
//
//        v.capacity = 0;
//
//        PriorityQueue<vertex> pq = new PriorityQueue<>(new Comparator<vertex>() {
//
//            @Override
//
//            public int compare(vertex t, vertex t1) {
//
//                if (t.capacity <= t1.capacity) {
//                    return -1;
//                } else {
//                    return 1;
//                }
//
//            }
//
//        });
//
////        adjList.get(v.label - 1).forEach(e -> {
////            if(e.destination!=e.source)
////                pq.add(e);
////        });
//        pq.add(v);
//
////        Map<vertex, Integer> mp = new HashMap<>();
//
//        while (!pq.isEmpty()) {
//
////            edge e = pq.poll();
//            vertex e=pq.poll();
//            if(e.visited)
//                continue;
//            e.visited=true;
////            mp.put(e.source, 0);
//
////            vertex destAdjListHead = e.destination;
//
//            if (!adjList.get(e.label - 1).isEmpty()) {
//
//                adjList.get(e.label - 1).forEach(p -> {
//
//                    if (!p.destination.visited) {
//                        pq.add(p.destination);
//                    }
//                    if (p.destination.capacity > (p.weight + e.capacity)) {
//                        p.destination.capacity = (p.weight + e.capacity);
//                    }
//                });
//            }
//        }
////        System.out.println("returning "+exitCell.capacity);
//        return exitCell.capacity;
//
    }

    public void bfs(){  // assigning level to all the vertices
        ArrayDeque<vertex> q=new ArrayDeque<>();
        q.offer(vertexList.get(0));
        vertexList.get(0).visited=true;
        int currentLevel=0;
        while(!q.isEmpty()){
            vertex v=q.poll();
            currentLevel+=1;
            for(edge el:adjList.get(v.label-1)){
                if(!el.destination.visited)
                {
                    el.destination.visited=true;
                    el.destination.level=currentLevel;
                    q.offer(el.destination);
                }
            }
        }
    }
    public int drivingFunc(){
        bfs();
        int max_flow=0;
        if(vertexList.get(vertexList.size()-1).level==-1)
            return max_flow;
        
    }

}

class vertex {

    public int label,level;

    public boolean visited;

    public vertex(int label) {

        this.label = label;

        this.visited = false;
        this.level=-1;

    }

}

class edge {

    vertex source, destination;

    public long capacity,flow;

    public edge(vertex source, vertex destination, int capacity) {

        this.source = source;

        this.destination = destination;

        this.capacity = capacity;
        this.flow=0;

    }
} 
