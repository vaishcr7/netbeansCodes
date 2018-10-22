package spojptzerosevenz;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;
public class SPojPtzerosevenZ {
  
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
     vertex left,right,parent;

        public vertex(int label) {
            this.label = label;
            this.parent=null;
            this.visitedTime =0;
            this.visited = false;
            this.left=null;
            this.right=null;
        }
     
 }
static class Graph 
{ 
    public LinkedList<vertex> adj[];
    public Stack<vertex> st;
    public vertex []vertarr;
    Map<vertex,Integer> mp;
    Graph(int v) 
    { 
        mp=new HashMap<>();
        adj = new LinkedList[v]; 
        for (int i=0; i<v; ++i) 
            adj[i] = new LinkedList(); 
        st=new Stack<>();
        vertarr=new vertex[v];
        for (int i = 0; i < v; i++) {
            vertarr[i]=new vertex(i);
        }
    } 
    public void dirOfChild(vertex a,vertex b)
    {
        if(a.left!=null)
            a.right=b;
        else
            a.left=b;
        b.parent=a;
    }
   public void addEdge(vertex v,vertex w) 
    { 
        adj[v.label].add(w);
        dirOfChild(v, w);
        adj[w.label].add(v); 
        //dirOfChild(w, v);
        //System.out.println("edge added");
    }
   public int getAdjacent(vertex v,int parent)
   {
       if(v.label<0)
           return -1;
       for (int i = 0; i < adj[v.label].size(); i++) {
           if(!adj[v.label].get(i).visited && i!=parent)
               return i;
       }
       return -1;
   }
   public void beforeRunningDfsModif(int v)
   {
       st.clear();
//       System.out.println("starting for main root= "+vertarr[v].label);
       st.push(vertarr[v]);
       vertarr[v].visited=true;
   }
   public int getLongestPath(int parent)
   {
       dfsModif(parent);
       int max=Integer.MIN_VALUE;
       for (int i = 0; i < vertarr.length; i++) {
           max=max<vertarr[i].visitedTime?vertarr[i].visitedTime:max;
       }
       return max;
   }
    public void dfsModif(int parent)
    {
        if(st.isEmpty())
            return;
        vertex psuedoNode=new vertex(-10);
        vertex curNode=psuedoNode;
        while(!st.isEmpty())
        {
            if(getAdjacent(curNode,parent)==-1)
            {
                curNode=st.pop();
//                System.out.println("new current root= "+curNode.label);
            }
            int k=getAdjacent(curNode,parent);
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
    public int getDiameter(vertex root)
    {
        if(root==null)
           return 0;
        int lheight,rheight;
        if(!mp.containsKey(root.left))
        {
            lheight=height(root.left);
            mp.put(root.left,lheight);
        }
        else
            lheight=mp.get(root.left);
//        System.out.println("lheight= "+lheight);
        if(!mp.containsKey(root.right))
        {
            rheight=height(root.right);
            mp.put(root.right,rheight);
        }
        else
            rheight=height(root.right);
//        System.out.println("rheight= "+rheight);
//        System.out.println("going for left diameter");
        int ldiam=getDiameter(root.left);
//        System.out.println("ldiam= "+ldiam);
//        System.out.println("going for right diameter");
        int rdiam=getDiameter(root.right);
//        System.out.println("rdiam= "+rdiam);
//        System.out.println("returning "+Math.max((1+lheight+rheight),Math.max(ldiam, rdiam)));
        return Math.max((1+lheight+rheight),Math.max(ldiam, rdiam));
    }
    public int height(vertex node)
    {
        
        if(node==null)
            return 0;
        if(!mp.containsKey(node))
            return (1+(int)Math.max(height(node.left),height(node.right)));
        else
            return mp.get(node);
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
//        int vpos=-1;
//        for (int i = 0; i < n; i++) {
//            System.out.println("current vertex considered is "+g.vertarr[i].label);
//            if(g.vertarr[i].left!=null && g.vertarr[i].right!=null)
//            {
//                System.out.println("left-> "+g.vertarr[i].left.label);
//                System.out.println("right-> "+g.vertarr[i].right.label);
//                vpos=i;
//                break;
//            }
//        }
//        if(vpos==-1)
//            vpos=0;
//        System.out.println("vpos = "+vpos);
//        boolean leftN=(g.vertarr[vpos].left==null);
//        boolean rightN=(g.vertarr[vpos].right==null);
//        System.out.println("leftN= "+leftN);
//            System.out.println("rightN= "+rightN);
//        if(leftN && rightN)
//        {
//            System.out.println("0");
//        }
//        else if((leftN && !rightN) || (!leftN && rightN))
//        {
//            System.out.println("second block");
//            g.beforeRunningDfsModif(vpos);
//            System.out.println(g.getLongestPath(vpos));
//        }
//        else
//        {
//            System.out.println("third block and vpos= "+vpos);
//            g.beforeRunningDfsModif(g.vertarr[vpos].left.label);
//            int leftLen=g.getLongestPath(vpos);
//            System.out.println("left length= "+leftLen);
//            g.beforeRunningDfsModif(g.vertarr[vpos].right.label);
//            int rightLen=g.getLongestPath(vpos);
//            System.out.println("right length= "+rightLen);
//            System.out.println(leftLen+rightLen+2);
        //}
        int rootpos=-1;
            for (int i = 0; i < n; i++) {
                if(g.vertarr[i].parent==null)
                {
                    rootpos=i;
                    break;
                }
            }
            System.out.println(g.getDiameter(g.vertarr[rootpos])-1);
        }
        catch(Exception e)
        {
        } 
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


11
8 2
2 4
2 5
1 3
3 6
6 7
7 8
6 9
9 10
10 11
*/
