package findingarticulationpoints;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author vibhor.vaish
 */
public class Findingarticulationpoints {

    public static void main(String[] args) {
     graph gf=new graph();
     vertex a=new vertex('A');
     vertex b=new vertex('B');
     vertex c=new vertex('C');
     vertex d=new vertex('D');
     vertex e=new vertex('E');
     vertex f=new vertex('F');
     vertex g=new vertex('G');
     vertex h=new vertex('H');
     gf.addVertex(a);
     gf.addVertex(b);
     gf.addVertex(c);
     gf.addVertex(d);
     gf.addVertex(e);
     gf.addVertex(f);
     gf.addVertex(g);
     gf.addVertex(h);
     gf.addEdge(c,b);
     gf.addEdge(b,a);
     gf.addEdge(a,c);
     gf.addEdge(c,d);
     gf.addEdge(d,e);
     gf.addEdge(e,f);
     gf.addEdge(h,f);
     gf.addEdge(f,g);
     gf.addEdge(g,e);
     ArrayList<vertex> temp=new ArrayList<>(gf.vertexList);
        System.out.println("size of temp= "+temp.size());
     a.visited=true;
     ArrayList<vertex> visited=new ArrayList<>();
     visited.add(a);
     temp.remove(a);
    }
}
class graph // it is a directed and weighted graph
{
    public ArrayList<vertex> vertexList;
    public  int numOfVertices;
    public ArrayList<LinkedList<vertex>> adjList;
    public ArrayDeque<vertex> stack;
    public graph() {
        vertexList=new ArrayList<>();
        adjList= new ArrayList<>();
        numOfVertices=0;
        stack=new ArrayDeque<>();
    }
    public  void addVertex(vertex vert)
    {
       vertexList.add(vert);
       adjList.add(new LinkedList<>());
       numOfVertices++;
    }
    public void addEdge(vertex source,vertex destination)
    {
        for (int i = 0; i < vertexList.size(); i++) {
            if(vertexList.get(i)==source)
            {
                adjList.get(i).add(destination);
            }
        }
        for (int i = 0; i < vertexList.size(); i++) {
            if(vertexList.get(i)==destination)
            {
                adjList.get(i).add(source);
            }
        }
    }
    public vertex getAdjacent(vertex vert,ArrayList<vertex> temp)//,int cutIndex)
    {
        int pos=0;
        for (int i = 0; i < numOfVertices; i++) {
            if(vertexList.get(i)==vert)
            {
                pos=i;
                break;
            }
        }
      LinkedList<vertex> p=adjList.get(pos);
       Iterator it=p.iterator();
        while(it.hasNext())
        {
            vertex j=(vertex)it.next();
            if(!temp.contains(j))
            {
                return j;
            }
        }
        return null;
     // if(cutIndex>=p.size())
       //   return null;
     //return p.get(cutIndex);
    }
   public void tarjanAlgo(vertex root)
   {
       Map<vertex,Integer> discTime=new HashMap<>();
       Map<vertex,Integer> lowTime=new HashMap<>();
       Map<vertex,vertex> parent=new HashMap<>();
       ArrayDeque<vertex> stack=new ArrayDeque<>();
       ArrayList<vertex> visited =new ArrayList<>();
       ArrayList<vertex> artPoints=new ArrayList<>();
       
       int time=0;
       stack.add(root);
       visited.add(root);
       discTime.put(root,time);
       lowTime.put(root,time);
       parent.put(root,null);
       time++;
       vertex k=getAdjacent(root,visited);
       while(!stack.isEmpty())
       {
          if(k!=null)
           {
               discTime.put(k,time);
               lowTime.put(k,time);
               parent.put(k,root);
               visited.add(k);
               if(stack.contains(k))
                   stack.remove(k);
               stack.push(k);
           }
          else
           {
               k=stack.pop();//checking if this vertex is  an articulation point
               int yp=lowTime.get(k);
               int pp=testAP(k,discTime,lowTime);
               if(yp<pp)
                   artPoints.add(k);
           }
           // check for articulation point first condition if it has two independent children
          if(parent.get(k)==null)
           {

           }
          k=getAdjacent(k,visited);
          time++;
       }
   }
   public int testAP(vertex k,Map<vertex,Integer> discTime,Map<vertex,Integer> lowTime)
   {
       int ind=getIndex(k);
       boolean flag=false;
       LinkedList<vertex> adLList=adjList.get(ind);
       for (vertex f : adLList) {
           if(lowTime.get(k)>=discTime.get(f))
           {
               lowTime.put(k,discTime.get(f));
               flag=true;
           }
       }
       if(flag)
            return lowTime.get(k);
       else
           return Integer.MAX_VALUE;
   }
    public int getIndex(vertex f)
    {
        for (int i = 0; i < vertexList.size(); i++) {
            if(vertexList.get(i)==f)
                return i;
        }
        return -1;
    }
   public void printList(LinkedList<vertex> al)
   {
       for (vertex object : al) {
           System.out.print(object.label+" , ");
       }
       System.out.println("");
   }
}
    class vertex
{
    public char label;
    public boolean visited;
    public vertex(char label) {
        this.label = label;
        this.visited = false;
    }
}
