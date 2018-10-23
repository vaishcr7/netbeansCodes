package spojptzerosevenz;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Stack;
class SPojPtzerosevenZ {
  
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
 static class vertex
 {
     int label,visitedTime;
     boolean visited;

        public vertex(int label) {
            this.label = label;
            this.visitedTime =0;
            this.visited = false;
        }
     
 }
static class Graph 
{ 
    public LinkedList<vertex> adj[];
    public Stack<vertex> st;
    public vertex []vertarr;
    Graph(int v) 
    { 
        adj = new LinkedList[v]; 
        for (int i=0; i<v; ++i) 
            adj[i] = new LinkedList(); 
        st=new Stack<>();
        vertarr=new vertex[v];
        for (int i = 0; i < v; i++) {
            vertarr[i]=new vertex(i);
        }
    } 
   public void addEdge(vertex v,vertex w) 
    { 
        adj[v.label].add(w); 
        adj[w.label].add(v); 
        //System.out.println("edge added");
    }
   public int getAdjacent(vertex v)
   {
       if(v.label<0)
           return -1;
       for (int i = 0; i < adj[v.label].size(); i++) {
           if(!adj[v.label].get(i).visited)
               return i;
       }
       return -1;
   }
   public void beforeRunningDfsModif(int v)
   {
       st.clear();
       st.push(vertarr[v]);
       vertarr[v].visited=true;
   }
   public int getLongestPath()
   {
       dfsModif();
       int max=Integer.MIN_VALUE;
       int pos=0;
       for (int i = 0; i < vertarr.length; i++) {
           if(max<vertarr[i].visitedTime)
           {
               max=vertarr[i].visitedTime;
               pos=i;
           }
       }
       return pos;
   }
    public void dfsModif()
    {
        if(st.isEmpty())
            return;
        vertex psuedoNode=new vertex(-10);
        vertex curNode=psuedoNode;
        while(!st.isEmpty())
        {
            if(getAdjacent(curNode)==-1)
            {
                curNode=st.pop();
//                System.out.println("new current root= "+curNode.label+" and has visited time= "+curNode.visitedTime);
            }
            int k=getAdjacent(curNode);
            if(k!=-1)
            {
                vertex g=adj[curNode.label].get(k);
                g.visited=true;
                g.visitedTime=1+curNode.visitedTime;
                st.push(curNode);
                st.push(g);
                curNode=psuedoNode;
            }
        }
    }
}
    public static void main(String args[]) 
    { 
        Reader sc=new Reader();
        try
        {
        int n=sc.nextInt();
        Graph g = new Graph(n); 
        for (int i = 0; i < n-1; i++) {
                int src=sc.nextInt();
                int dest=sc.nextInt();
                //System.out.println("src= "+src+" and dest= "+dest);
                g.addEdge(g.vertarr[src-1],g.vertarr[dest-1]);
        }
        g.beforeRunningDfsModif(0);
        int posOfDeepestNode=g.getLongestPath();
//        System.out.println("vertex to start from "+posOfDeepestNode);
            for (int i = 0; i < n; i++) {
                g.vertarr[i].visited=false;
                g.vertarr[i].visitedTime=0;
            }
        g.beforeRunningDfsModif(posOfDeepestNode);
        int farthestNodeInd=g.getLongestPath();
//        System.out.println("vertex farthest is "+farthestNodeInd);
        System.out.println(g.vertarr[farthestNodeInd].visitedTime);
        }
        catch(Exception e)
        {
        }
    } 
} 
/*
11
1 2
2 4
2 5
1 3
3 6
6 7
7 8
6 9
9 10
10 11


8
1 2
1 3
1 4
3 6
3 7
4 5
7 8
*/
