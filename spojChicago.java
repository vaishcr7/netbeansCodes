package spojchicago;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class SpojChicago {

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
            String[] h = sc.readLine().split(" ");

            int n = Integer.parseInt(h[0]);
            if (n != 0) {

                int numOfEdges = Integer.parseInt(h[1]);

                //System.out.println("n= "+n+" ,exitcell= "+exitCell+" ,timelimit= "+timeLimit+" and numedges= "+numOfEdges);
                graph g = new graph();

                for (int i = 0; i < n; i++) {

                    g.addVertex(new vertex(i + 1));

                }

                // System.out.println("going to add edges");
                for (int i = 0; i < numOfEdges; i++) {
                    String[] p = sc.readLine().split(" ");
                    int source = Integer.parseInt(p[0]);

                    int destination = Integer.parseInt(p[1]);

                    double weight = Double.parseDouble(p[2]);
                    // System.out.println("num of vertices: "+g.vertexList.size());

                    g.addEdge(g.vertexList.get(source - 1), g.vertexList.get(destination - 1), weight);

                    // System.out.println("i= "+i);
                }
                // System.out.println("calling disjktra ");
                double count = g.primAlgo(g.vertexList.get(0), g.vertexList.get(n - 1));
                if (count != Integer.MAX_VALUE) {
                    sba.append(count).append("\n");
//                    System.out.println(count);
                } else {
                    sba.append("NONE").append("\n");
//                    System.out.println("NONE");
                }
            }
//            sba.deleteCharAt(sba.length()-1);
            System.out.println(sba.toString());
        } catch (Exception e) {
            System.out.println("error= " + e.getMessage());
//        return;

        }

    }
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

    public void addEdge(vertex source, vertex destination, double weight) {

        edge lp = new edge(source, destination, weight);

        adjList.get(source.label - 1).add(lp);

        lp = new edge(destination, source, weight);

        adjList.get(destination.label - 1).add(lp);

    }

    public double primAlgo(vertex entryCell, vertex exitCell) {
//         System.out.println("src cell "+entryCell.label);
//         System.out.println("dest cell "+exitCell.label);

        PriorityQueue<vertex> pq = new PriorityQueue<>(new Comparator<vertex>() {

            @Override

            public int compare(vertex t, vertex t1) {

                if (t.key < t1.key) {
                    return 1;
                } else {
                    return -1;
                }

            }

        });

        int count = 0;
        entryCell.key = Double.MAX_VALUE;
        entryCell.visited = true;
        for (int i = 0; i < vertexList.size(); i++) {
            pq.offer(vertexList.get(i));
        }
        double percent = 1;
        double lastPercent=0;
        while (count < vertexList.size()) {
            vertex curr = pq.poll();
            if (curr.key !=0 && curr.key!=Double. MAX_VALUE) {
                percent *= (curr.key-lastPercent);
                lastPercent=curr.key;
                System.out.println("polled "+curr.label);
            }
//    	System.out.println("visited "+curr.label+" , ");
            count += 1;
            curr.visited = true;
            adjList.get(curr.label - 1).forEach(e -> {
                if (e.destination != e.source && !e.destination.visited) //                {
                {
                    if(e.source.key==Double.MAX_VALUE)
                        e.source.key=0;
                    if (e.destination.key < (e.source.key + e.weight)) {
                        pq.remove(e.destination);
//                        System.out.println(e.source.key+" , "+e.weight);
                        e.destination.key = e.source.key + e.weight;
//                        System.out.println(e.destination.label+" new key is "+e.destination.key);
                        pq.add(e.destination);
                    }
                }
//                }
            });
//            System.out.println("current pq is ");
//            pq.forEach((t) -> {
//                System.out.println(t.label+"  , "+t.key);
//            });
//            System.out.println("");
            if (curr == exitCell) {
                System.out.println(" found the destination ");
                break;
            }
        }
        // System.out.println("returning "+exitCell.timeExhausted);
        double temp=percent;
        int numzeroes=0;
        while(temp/10>=10)
        {
            numzeroes+=1;
            temp/=10;
        }
        return Math.round((percent/(Math.pow(10,numzeroes))));
    }
}

class vertex {

    public int label;
    public double key;

    public boolean visited;

    public vertex(int label) {

        this.label = label;

        this.visited = false;

        this.key = 0;

    }
}

class edge {

    vertex source, destination;

    double weight;

    public edge(vertex source, vertex destination, double weight) {

        this.source = source;

        this.destination = destination;

        this.weight = weight;

    }
}
