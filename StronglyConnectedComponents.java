package stronglyconnectedcomponents;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author vibhor.vaish
 */
public class StronglyConnectedComponents {

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
     vertex k=new vertex('K');
     gf.addVertex(a);
     gf.addVertex(b);
     gf.addVertex(c);
     gf.addVertex(d);
     gf.addVertex(e);
     gf.addVertex(f);
     gf.addVertex(g);
     gf.addVertex(h);
     gf.addVertex(k);
     gf.addEdge(c,b);
     gf.addEdge(b,a);
     gf.addEdge(a,c);
     gf.addEdge(c,d);
     gf.addEdge(d,e);
     gf.addEdge(e,h);
     gf.addEdge(h,f);
     gf.addEdge(f,d);
     gf.addEdge(g,d);
     gf.addEdge(f,k);
     ArrayList<vertex> temp=new ArrayList<>(gf.vertexList);
        System.out.println("size of temp= "+temp.size());
     a.visited=true;
     ArrayList<vertex> visited=new ArrayList<>();
     visited.add(a);
     temp.remove(a);
     gf.kosarajuAlgoPartOne(a,visited,temp);
     gf.kosarajuAlgoPartTwo();
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
    }
    public vertex getAdjacent(vertex vert)//,int cutIndex)
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
            if(!j.visited)
            {
                return j;
            }
        }
        return null;
     // if(cutIndex>=p.size())
       //   return null;
     //return p.get(cutIndex);
    }
     public vertex getAdjacent1(vertex vert,ArrayList<LinkedList<vertex>> adjList1)//created for kosarajuparttwo
    {
        int pos=0;
        for (int i = 0; i < numOfVertices; i++) {
            if(vertexList.get(i)==vert)
            {
                pos=i;
                break;
            }
        }
      LinkedList<vertex> p=adjList1.get(pos);
       Iterator it=p.iterator();
        while(it.hasNext())
        {
            vertex j=(vertex)it.next();
            if(!j.visited)
            {
                return j;
            }
        }
        return null;
     // if(cutIndex>=p.size())
       //   return null;
     //return p.get(cutIndex);
    }
    public int getIndex(vertex f)
    {
        for (int i = 0; i < vertexList.size(); i++) {
            if(vertexList.get(i)==f)
                return i;
        }
        return -1;
    }
   public void kosarajuAlgoPartOne(vertex source,ArrayList<vertex> visited,ArrayList<vertex> temp)
   {
       System.out.println("source is "+source.label);
       vertex k=getAdjacent(source);
       while(k!=null)
       {
           k.visited=true;
           visited.add(k);// so that i can determine the points where i do my backtracking
           temp.remove(k);// so that i am able to cover  all vertices, the reason why i used temp
           System.out.println("called me ");
           kosarajuAlgoPartOne(k,visited,temp);
           System.out.println("____________back to vertex_____________   "+k.label);
           k=getAdjacent(k);
       }
       if(k==null && !stack.contains(source))//do not add the already added vertices that were added before backtracking
       {
           System.out.println("pushing "+source.label+" to the stack" );
           stack.push(source);
           temp.remove(source);//this step is necessary for the vertices that will be along in the scc like 'G' in the considered example.
           Iterator it=visited.iterator();
           while(it.hasNext())
               System.out.print(((vertex)it.next()).label+" , ");
           System.out.println("");
           visited.remove(source);
       }
       if(!visited.isEmpty())
       {
           System.out.println("called it for vertex");
           kosarajuAlgoPartOne(visited.get(visited.size()-1),visited,temp);
       }
       //now using leftover vertices
          System.out.println("printing left over vertices");
          Iterator it1=temp.iterator();
          while(it1.hasNext())
              System.out.print(((vertex)it1.next()).label+" , ");
          System.out.println("");
       while(!temp.isEmpty())
       {
           System.out.println("hello");
           vertex pp=temp.get(0);
           kosarajuAlgoPartOne(temp.get(0),visited,temp);
       }
   }
   public void kosarajuAlgoPartTwo()// separate getAdjacent function has to created for this to access the correct adjList1
   {
       System.out.println("inside part two function");
       System.out.println("the stack is "+stack.size());
       Iterator it1=stack.iterator();
       while(it1.hasNext())
         System.out.print(((vertex)it1.next()).label+" , ");
       System.out.println("");
       
       System.out.println("marking them unvisited");
       for (int i = 0; i < vertexList.size(); i++) {
           vertexList.get(i).visited=false;
       }
       
       System.out.println("reversing the edges");
       ArrayList<LinkedList<vertex>> adjList1=new ArrayList<>();
       for (int i = 0; i < adjList.size(); i++) {
           adjList1.add(new LinkedList<>());
       }
       for (int i = 0; i < vertexList.size(); i++) {
           vertex x=vertexList.get(i);
          // System.out.print("SEARCHING FOR VERTEX "+x.label);
           for (int j = 0; j < adjList1.size(); j++) 
           {
               if(j!=i)
               {
                    LinkedList<vertex> linkedList=adjList.get(j);
                    for (int k = 0; k < linkedList.size(); k++) {
                       // System.out.println("  comparing with "+linkedList.get(k).label);
                        if(linkedList.get(k)==x)
                        {
                            adjList1.get(i).add(vertexList.get(j));
                            //System.out.println("added");
                        }
               }
               }
           }
       }
           System.out.println("adjList1 size= "+adjList1.size());
           for (LinkedList<vertex> linkedList : adjList1) {
               for (vertex object : linkedList) {
                   System.out.print(object.label+" , ");
               }
               System.out.println("");
           }
       
       ArrayList<vertex> visited=new ArrayList<>();
       ArrayList<StringBuilder> vars=new ArrayList<>();
       while(!stack.isEmpty())
       {
           vertex z=stack.pop();
           z.visited=true;
           System.out.println("popped vertex= "+z.label);
           if(!visited.contains(z))
           {
           StringBuilder sba=new StringBuilder();
           sba.append(z.label);
           System.out.println("adjacent nodes of z= ");
           printList(adjList1.get(getIndex(z)));
           vertex k=getAdjacent1(z,adjList1);
           while(k!=null && !visited.contains(k))
           {
               sba.append(k.label);
               visited.add(k);
               k.visited=true;
               System.out.println("added "+k.label+" to visited");
               k=getAdjacent1(k,adjList1);
           }
           vars.add(sba);
           }
       }
       System.out.println("vars size = "+vars.size());
       Iterator it=vars.iterator();
       while(it.hasNext())
       {
           System.out.println(((StringBuilder)it.next()).toString());
       }
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