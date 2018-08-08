package spojwordsone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class SpojWordsOne {

    public static void main(String[] args) {
        
    }
    
}
class graph
{
   int [][]adjMat;
   int numOfVertices;
   ArrayList<vertex> vertexList;
   Map<Character,ArrayList<vertex>> mpOfInitialLetters;
   public graph(int numOfVertices) {
       this.adjMat = new int[numOfVertices][numOfVertices];
       this.numOfVertices = numOfVertices;
       vertexList=new ArrayList<>();
       mpOfInitialLetters=new HashMap<>();
   }
   public void addVertex(vertex a,int ind)
   {
       a.matrixIndex=ind;
       vertexList.add(a);
       ArrayList<vertex> al;
       if(mpOfInitialLetters.containsKey(a.label.charAt(0)))
       {
           al=mpOfInitialLetters.get(a.label.charAt(0));
       }
       else
       {
           al=new ArrayList<>();
       }
       al.add(a);
       mpOfInitialLetters.put(a.label.charAt(0),al);
   }
   public boolean dfs()
   {
       if(checkForZeroDegreeVertex())
           return true;
       int verticesVisited=0;
       int stIndex=0;
       vertex root=null;
       while(verticesVisited!=numOfVertices && stIndex<=vertexList.size())
       {
           root=vertexList.get(stIndex++);
           root.visited=true;
           while(root!=null)
           {
               ArrayList<vertex> ap=mpOfInitialLetters.get(root.label.charAt(root.label.length()-1));
               root=null;// ensuring the next vertex is new
               for (vertex k : ap) {
                if(!k.visited)
                   root=k;
                }
               if(root!=null)
               {
                   root.visited=true;
                   verticesVisited++;
               }
           }
       }
       if(stIndex>vertexList.size())
           return false;
      /* stIndex-=1;
       for (int i = 0; i < vertexList.size(); i++) {
           vertexList.get(i).visited=false;
       }
       vertexList.get(stIndex).visited=true;*/
      return true;       
   }
   public boolean checkForZeroDegreeVertex()
   {
       for (int i = 0; i < numOfVertices; i++) {
           if(vertexList.get(i).degree==0)
               return true;
       }
       return false;
   }
   public void addEdge(vertex a,vertex b)
   {
       adjMat[a.matrixIndex][b.matrixIndex]=1;
       a.degree++;
   }
    
}
class vertex
{
    String label;
    boolean visited;
    int matrixIndex;
    int degree;
    public vertex(String label, boolean visited) {
        this.label = label;
        this.visited = visited;
        degree=0;
        matrixIndex=-1;
    }
    
}