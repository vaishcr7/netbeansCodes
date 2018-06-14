package findingarticulationpoints;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author vibhor.vaish
 */
public class FindingArticulationPoints {

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
     gf.addEdge(e,g);
     gf.addEdge(f,g);
     gf.addEdge(f,h);
     gf.tarjanAlgo(a);
    }
}
class graph // it is a directed and weighted graph
{
    public ArrayList<vertex> vertexList;
    public  int numOfVertices;
    public ArrayList<LinkedList<vertex>> adjList;
    public ArrayDeque<vertex> stack;
    
    Map<vertex,Integer> discTime,lowTime;
    
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
    public vertex getAdjacent(vertex vert,ArrayList<vertex> temp)
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
       discTime=new HashMap<>();
       lowTime=new HashMap<>();
       Map<vertex,vertex> parent=new HashMap<>();
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
       System.out.println("the starting stack is ");
       printStack(stack);
       while(!stack.isEmpty())
       {
          if(k!=null)
           {
               System.out.println("k= "+k.label);
               discTime.put(k,time);
               lowTime.put(k,time);
               parent.put(k,root);
               visited.add(k);
               if(stack.contains(k))
                   stack.remove(k);
               stack.push(k);
               System.out.println("current stack is ");
               printStack(stack);
               System.out.println("visited is ");
               printList(visited);
           }
          else
           {
               k=stack.pop();//checking if this vertex is  an articulation point
               int yp=lowTime.get(k);
               System.out.println("disctime = ");
               Set<Map.Entry<vertex,Integer>> st1=discTime.entrySet();
               for (Map.Entry<vertex, Integer> entry : st1) {
                   System.out.print(entry.getKey().label+" - "+entry.getValue()+" || ");
               }
               System.out.println("");
               System.out.println("lowtime = ");
               Set<Map.Entry<vertex,Integer>> st2=lowTime.entrySet();
               for (Map.Entry<vertex, Integer> entry : st2) {
                   System.out.print(entry.getKey().label+" - "+entry.getValue()+" || ");
               }
               System.out.println("");
               int pp=testAP(k,discTime,lowTime);
               if(yp<pp)
                   artPoints.add(k);
           }
           // check for articulation point first condition if it has two independent children
          if(parent.get(k)==null)
           {
               int numC=getNumOfChildren(k);
               boolean flag=false;
               LinkedList<vertex> al=adjList.get(getIndex(k));
               for (int i=0;i<numC;i++) {
                   vertex t=al.get(i);
                   LinkedList<vertex> pal=adjList.get(getIndex(t));
                   for (vertex object : pal) {
                       if(al.contains(object))
                       {
                           flag=true;
                           break;
                       }
                   }
                   if(flag)
                       break;
               }
               if(!flag)
                   artPoints.add(k);
           }
          k=getAdjacent(k,visited);
           System.out.println("adjacent k is "+k.label);
          time++;
       }
       Set<vertex> st=new TreeSet<>(artPoints);
       System.out.println("these are the articulation points");
       Iterator it=st.iterator();
       while(it.hasNext())
       {
           vertex b=(vertex)it.next();
           System.out.print(b.label+" , ");
       }
       System.out.println("");
   }
   public void printStack(ArrayDeque<vertex> stack)
   {
       for (vertex t : stack) {
           System.out.print(t.label+" ");
       }
       System.out.println("");
   }
   public int getNumOfChildren(vertex f)
   {
       int g=getIndex(f);
       return adjList.get(g).size();
   }
   public int testAP(vertex k,Map<vertex,Integer> discTime,Map<vertex,Integer> lowTime)
   {
       int ind=getIndex(k);
       boolean flag=false;
       LinkedList<vertex> adLList=adjList.get(ind);
       for (vertex f : adLList) {
           if(f!=null)
               System.out.println("f is "+f.label);
           if(k!=null)
               System.out.println("k is "+k.label);
           if(lowTime.get(k)>=discTime.get(f))
           {
               lowTime.put(k,discTime.get(f));
               flag=true;
               System.out.println(k.label+" not an articulation point");
               break;
           }
       }
       if(!flag)
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
   public void printList(List<vertex> al)
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
