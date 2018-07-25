package spojqtreetwo;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class SpojQtreeTwo {
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
       Scanner sc=new Scanner(System.in);
       int t=sc.nextInt();
       while(t-->0)
       {
           int n=sc.nextInt();
           graph g=new graph(n);
           for (int i = 1; i <=n; i++) {
               g.addVertex(new vertex(i));
           }
           for (int i = 0; i < n-1; i++) {
               g.addEdge(g.vertexList.get(sc.nextInt()-1),g.vertexList.get(sc.nextInt()-1), sc.nextInt());
           }
           String s=sc.next();
           System.out.println("s= "+s);
           while(!"DONE".equals(s))
           {
               /*String[] st=s.split(" ");
               System.out.println("string is ");
               for (int i = 0; i < st.length; i++) {
                   System.out.print(st[i]+"  ");
               }*/
               System.out.println("");
               System.out.println("adj matrix: ");
               for (int i = 0; i < g.vertexList.size(); i++) {
                   for (int j = 0; j < g.vertexList.size(); j++) {
                       System.out.print(g.ar[i][j]+" ");
                   }
                   System.out.println("");
               }
 
               if("DIST".equals(s))//st.length==3)
               {
                   int source=sc.nextInt();
                   int destination=sc.nextInt();
                   //int source=Integer.parseInt(st[1]);
                   //int destination=Integer.parseInt(st[2]);
                   System.out.println(g.primsAlgo(g.vertexList.get(source-1),g.vertexList.get(destination-1)));
               }
               else
               {
                   int source=sc.nextInt();
                   int destination=sc.nextInt();
                   int k=sc.nextInt();
                 //int source=Integer.parseInt(st[1]);
                 //int destination=Integer.parseInt(st[2]);
                   //int k=Integer.parseInt(st[3]);
                   System.out.println(g.primsAlgo(g.vertexList.get(source-1),g.vertexList.get(destination-1)));
                   g.paths.get(""+source+""+destination);
               }
           }
       }
       
    }
    
}
class graph // it is a non-directed and weighted graph
{
    public int [][]ar;
    public ArrayList<vertex> vertexList;
    public Map<String,Integer> edWeights; // edges will be defined as source-destination as keys and their weights as values 
    public  int numOfVertices;
    //public ArrayList<LinkedList<vertex>> adjList;
    public PriorityQueue<vertex> pq;
    public Map<String,String> paths;
    public graph(int size) {
        numOfVertices=size;
        ar=new int[numOfVertices][numOfVertices];
        for (int i = 0; i < numOfVertices; i++) {
            Arrays.fill(ar[i],Integer.MAX_VALUE);
        }
        //adjList=new ArrayList<>();
        vertexList=new ArrayList<>();
        pq=new PriorityQueue<>();
    }
    public  void addVertex(vertex vert)
    {
       vertexList.add(vert);
      // numOfVertices++;
    }
    public void addEdge(vertex source,vertex destination,int weight)
    {
       /* for (int i = 0; i < vertexList.size(); i++) {
            if(vertexList.get(i)==source)
            {
                adjList.get(i).add(destination);
                edWeights.put(""+source+"-"+destination,weight);
            }
        }*/
       ar[source.label-1][destination.label-1]=weight; 
       ar[destination.label-1][source.label-1]=weight;
    }
    public long primsAlgo(vertex source,vertex destination)
    {
        source.key=0;
        ArrayList<vertex> visited=new ArrayList<>();
        visited.add(source);
        updateKeys(source, visited);
        Map<vertex,vertex> mp1=new HashMap<>();// for adding the relevant edges only.
        Map<vertex,vertex> mp2=new HashMap<>();// for adding the relevant edges in reverse.
        while(visited.size()!=vertexList.size())
        {
            int pos=getMinAdjacent(source, visited);
            visited.add(vertexList.get(pos));
            mp1.put(source,vertexList.get(pos));
            mp2.put(vertexList.get(pos),source);
            source=vertexList.get(pos);
            updateKeys(source, visited);
        }
        System.out.println("final keys= ");
        for (int i = 0; i < vertexList.size(); i++) {
            System.out.print(vertexList.get(i).label+" -> "+vertexList.get(i).key+" , ");
        }
        return returnDistance(source, destination, mp1, mp2);
    }
    
    public int getMinAdjacent(vertex vert,ArrayList<vertex> visited)
    {
        System.out.println("inside minimum adjacent function for vertex= "+vert.label);
        /*for (int i = 0; i < numOfVertices; i++) {
            if(vertexList.get(i)==vert)
            {
                pos=i;
                break;
            }
        }
        return null;*/
        int minPos=0,minVal=Integer.MAX_VALUE;
        System.out.print("visited list is ");
        for (int i = 0; i < visited.size(); i++) {
            System.out.print(visited.get(i).label+" , ");
        }
        System.out.println("");
        for (int i = 0; i < numOfVertices; i++) {
            if(i!=vert.label-1)
            {
                if(!visited.contains(vertexList.get(i)) && ar[vert.label-1][i]<minVal)
                {
                    minPos=i;
                    System.out.println("found one");
                    minVal=ar[vert.label-1][minPos];
                }
             }
        }
        /*if(visited.contains(vertexList.get(minPos)))
        {
            for (int i = 0; i < vertexList.size(); i++) {
                if(!visited.contains(vertexList.get(i)))
                {
                    minPos=i;
                    break;
                }
            }
        }*/
        System.out.println("minpos= "+minPos);
        return minPos;
    }
    public long returnDistance(vertex source,vertex destination,Map<vertex,vertex> mp1,Map<vertex,vertex> mp2)
    {
        long sum=0;
        if(mp1.get(source)==destination)
        {
            paths.put(""+source.label+""+destination.label,""+source.label+","+destination.label);
            return ar[source.label-1][destination.label-1];
        }
        vertex a=mp1.get(source);
        vertex b=mp2.get(destination);
        if(a==b)
        {
            paths.put(""+source.label+""+destination.label,""+source.label+","+a.label+","+destination.label);
            return (ar[source.label-1][a.label-1]+ar[a.label-1][destination.label-1]);
        }
        Stack<vertex> tempStorage=new Stack<>();
        tempStorage.add(a);
        tempStorage.add(b);
        int fpointer=1;
        StringBuilder sba=new StringBuilder();
        sba.append(source.label);
        sba.append(",").append(destination.label);
        while(a!=b)
        {
         System.out.println("ar= "+ar[b.label-1][mp2.get(b).label-1]);
         sum+=ar[b.label-1][mp2.get(b).label-1];
         sba.insert(fpointer++,","+b.label);
         b=mp2.get(tempStorage.pop());
         sum+=ar[mp2.get(a).label-1][a.label-1];
         sba.insert((fpointer+1),","+a.label);
         a=mp1.get(tempStorage.pop());
         if(mp1.get(a)==b)
         {
             sum+=ar[a.label-1][b.label-1];
             sba.insert(fpointer,","+mp1.get(a).label);
             paths.put(""+source.label+""+destination.label,sba.toString());
             return sum;
         }
        }
        paths.put(""+source.label+""+destination.label,sba.toString());
        return sum;
    }
    public void updateKeys(vertex v,ArrayList<vertex> visited)
    {
        System.out.println("old keys= ");
        for (int i = 0; i < vertexList.size(); i++) {
            System.out.print(vertexList.get(i).label+" -> "+vertexList.get(i).key+" , ");
        }
        System.out.println("");
        for (int i = 0; i < numOfVertices; i++) {
            if(i!=v.label-1 && !visited.contains(vertexList.get(i)))
            {
                vertexList.get(i).key = (vertexList.get(i).key) > (ar[v.label-1][i]) ? (ar[v.label-1][i]) : (vertexList.get(i).key);
            }
        }
        System.out.println("new keys= ");
        for (int i = 0; i < vertexList.size(); i++) {
            System.out.print(vertexList.get(i).label+" -> "+vertexList.get(i).key+" , ");
        }
        System.out.println("");
    }
   /* public int returnVertexIndex(vertex v)
    {
        for (int i = 0; i < vertexList.size(); i++) {
            if(vertexList.get(i)==v)
                return i;
        }
        return 0;// had to return something for the function to work properly
    }*/
    public  int getEdgeWeight(vertex source,vertex destination)
    {
        //return edWeights.get(""+source+"-"+destination);
        return ar[source.label-1][destination.label-1];
    }
}
class vertex
{
    public int label;
    public int key;
    public vertex(int label) {
        this.label = label;
        this.key=Integer.MAX_VALUE;
    }
}
/*
1 

6 
1 2 1 
2 4 1 
2 5 2 
1 3 1 
3 6 2 
DIST 4 6 
KTH 4 6 4 
DONE
*/
