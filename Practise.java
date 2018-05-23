package practise;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class Practise {

    public static void main(String[] args) {
      
          
    }
    
}
class graph
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
                adjList.get(i).add(destination);
            else if(vertexList.get(i)==destination)
                adjList.get(i).add(source);
        }
    }
    public void dfs()
    {
        vertexList.get(0).visited=true;
        stack.push(vertexList.get(0));
        while(!stack.isEmpty())
        {
            vertex k=getAdjacent(stack.peek());
            if(k!=null)
                stack.push(k);
            else
                stack.pop();
        }
    }
    public vertex getAdjacent(vertex vert)
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
        for (int i = 0; i < p.size(); i++) {
           if(!p.get(i).visited)
           {
               p.get(i).visited=true;
               return p.get(i);
           }
        }
        return null;
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
