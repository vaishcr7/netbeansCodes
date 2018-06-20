package tspcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class TSPCode {

    
    public static void main(String args[]) {
         graph gf=new graph();
         vertex a=new vertex('A');
         vertex b=new vertex('B');
         vertex c=new vertex('C');
         vertex d=new vertex('D');
         gf.addVertex(a);
         gf.addVertex(b);
         gf.addVertex(c);
         gf.addVertex(d);
         gf.addEdge(a,b,1);
         gf.addEdge(a,c,15);
         gf.addEdge(a,d,6);
         gf.addEdge(b,a,2);
         gf.addEdge(b,c,7);
         gf.addEdge(b,d,3);
         gf.addEdge(c,a,9);
         gf.addEdge(c,b,6);
         gf.addEdge(c,d,12);
         gf.addEdge(d,a,10);
         gf.addEdge(d,b,4);
         gf.addEdge(d,c,8);
         int distance[][] = {{0, 1, 15, 6},
                             {2, 0, 7, 3},
                             {9, 6, 0, 12},
                             {10, 4, 8, 0},
         };
         ArrayList<Set<vertex>> allS=gf.makeSets();
         for (Set<vertex> set : allS) {
             for (vertex object : set) {
                 System.out.print(object.label+" ");
             }
             System.out.println("");
        }
         //int minCost = gf.minCost(distance);
    }
}
class graph // it is a directed and weighted graph
{
    public ArrayList<vertex> vertexList;
    public  int numOfVertices;
    public ArrayList<LinkedList<vertex>> adjList;
    public ArrayDeque<vertex> stack;
    public Map<String,Integer> edWeights; // edges will be defined as source-destination as keys and their weights as values 
    public graph() {
        vertexList=new ArrayList<>();
        adjList= new ArrayList<>();
        numOfVertices=0;
        stack=new ArrayDeque<>();
        edWeights=new HashMap<>();
    }
    public  void addVertex(vertex vert)
    {
       vertexList.add(vert);
       adjList.add(new LinkedList<>());
       numOfVertices++;
    }
    public void addEdge(vertex source,vertex destination,int weight)
    {
        for (int i = 0; i < vertexList.size(); i++) {
            if(vertexList.get(i)==source)
            {
                adjList.get(i).add(destination);
                edWeights.put(""+source+"-"+destination,weight);
            }
        }
    }
    public  int getEdgeWeight(vertex source,vertex destination)
    {
        return edWeights.get(""+source+"-"+destination);
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
    
    public int getIndex(vertex f)
    {
        for (int i = 0; i < vertexList.size(); i++) {
            if(vertexList.get(i)==f)
                return i;
        }
        return -1;
    }
    public ArrayList<Set<vertex>> makeSets()
    {
        ArrayList<Set<vertex>> allsets=new ArrayList<>();
        Set<vertex> newSet=new HashSet<>();
        allsets.add(newSet);
        for (int i = 0; i <vertexList.size(); i++) {
            Iterator it=allsets.iterator();
            ArrayList<Set<vertex>> tempAllsets=new ArrayList<>();
            while(it.hasNext())
            {
                Set<vertex> currSet=(HashSet<vertex>)it.next();
                for (int j = 0; j < vertexList.size(); j++) {
                    Set<vertex> lp=currSet;
                    lp.add(vertexList.get(j));
                    tempAllsets.add(lp);
                    System.out.println("added this set");
                    Iterator it1=lp.iterator();
                    while(it1.hasNext())
                    {
                        System.out.print(((vertex)it1.next()).label+" ");
                    }
                    System.out.println("");
                }
            }
            allsets.addAll(tempAllsets);
        }
        return allsets;
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
