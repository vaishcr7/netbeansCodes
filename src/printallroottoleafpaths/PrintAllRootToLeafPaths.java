/*
 *print all possible paths from root to rest leaves
 */
package printallroottoleafpaths;

import java.util.ArrayList;
import java.util.Scanner;

public class PrintAllRootToLeafPaths {

    public static void main(String[] args) {
      Scanner sc=new Scanner(System.in);
      BinaryTreeNode a=new BinaryTreeNode(1);
      BinaryTreeNode b=new BinaryTreeNode(2);
      BinaryTreeNode c=new BinaryTreeNode(3);
      a.left=b;
      a.right=c;
      BinaryTreeNode d=new BinaryTreeNode(4);
      b.left=d;
      BinaryTreeNode e=new BinaryTreeNode(5);
      b.right=e;
      BinaryTreeNode f=new BinaryTreeNode(6);
      BinaryTreeNode g=new BinaryTreeNode(7);
      c.right=f;
      f.right=g;
      BinaryTreeNode pol=new BinaryTreeNode(8);
      g.left=pol;
      paths(a);
    }
    public static ArrayList<ArrayList<BinaryTreeNode>> paths(BinaryTreeNode root)
    {
        ArrayList<Integer> correctInd=new ArrayList<>();
        ArrayList<ArrayList<BinaryTreeNode>> ans=new ArrayList<>();
        ArrayList<ArrayList<BinaryTreeNode>> ans1=new ArrayList<>();
        ArrayList<BinaryTreeNode> temp=new ArrayList<>();
        
        temp.add(root);
        
        int t=-1,prevt=0,count=-1;
        while(count==-1 ||  (ans.size()>1 && !ans.get(ans.size()-2) .equals(ans.get(ans.size()-1))))
        {
            if(ans.size()>1 && !ans.get(ans.size()-2) .equals(ans.get(ans.size()-1)))
                System.out.println("second condition satisfied");
            BinaryTreeNode parent;
            if(t==-1)
                parent=root;
            else
            {
                if(prevt>=ans.size())
                     break;
                int len=ans.get(prevt).size();
                parent=ans.get(prevt).get(len-1);
                temp=ans.get(prevt);
                prevt++;
            }
            System.out.print("new base arlist or temp= ");
            for (int i = 0; i < temp.size(); i++) {
                System.out.print(" ,"+temp.get(i).data);
            }
            System.out.println("");
            System.out.println("parent is "+parent.data);
            if(parent.left!=null)
            {
              ArrayList<BinaryTreeNode>p=new ArrayList<>(temp);
              p.add(parent.left);
              ans.add(p);
              /*
               System.out.print("p left=  ");
            for (int i = 0; i < p.size(); i++) {
                System.out.print(" ,"+p.get(i).data);
            }
                System.out.println("");*/
              t++;
              System.out.println("t= "+t+" and left node added = "+parent.left.data);
            }
            if(parent.right!=null)
            {
               ArrayList<BinaryTreeNode>p=new ArrayList<>(temp);
               p.add(parent.right);
               ans.add(p);
               /*
                System.out.print("p right");
            for (int i = 0; i < p.size(); i++) {
                System.out.print(" ,"+p.get(i).data);
            }
                System.out.println("");
                */
               t++;
               System.out.println("t= "+t+" and right node added = "+parent.right.data);
            }
            if(parent.left==null && parent.right==null)
            {
                correctInd.add(prevt-1);
                System.out.println("leaf node found");
            }
            count++;
            System.out.println("count= "+count+" and prevt= "+prevt+" and ans= ");
            printArr(ans);
        }
        for (int i = 0; i < correctInd.size(); i++) 
            ans1.add(ans.get(correctInd.get(i)));
        printArr(ans1);
        return ans1;
    }
    public static void printArr(ArrayList<ArrayList<BinaryTreeNode>> a)
    {
        for(ArrayList<BinaryTreeNode> k:a)
        { for (int i = 0; i < k.size(); i++)
        { System.out.print(" ,"+k.get(i).data);   } 
                System.out.println("");
        }
    }
}
class BinaryTreeNode 
{
    int data;
    BinaryTreeNode left,right;

    public BinaryTreeNode(int data) {
        this.data = data;
        this.left = this.right = null;
    }
    
}
