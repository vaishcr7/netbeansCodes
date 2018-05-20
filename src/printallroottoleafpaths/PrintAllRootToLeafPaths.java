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
        System.out.println("final answer is : "+paths(a));
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
            BinaryTreeNode parent;
            if(t==-1)
                parent=root;
            else
            {
                int len=ans.get(prevt).size();
                parent=ans.get(prevt).get(len-1);
                temp=ans.get(prevt);
                prevt++;
               }
            if(parent.left!=null)
            {
              ArrayList<BinaryTreeNode>p=  temp;
              p.add(parent.left);
              ans.add(p);
              t++;
            }
            if(parent.right!=null)
            {
               ArrayList<BinaryTreeNode>p=  temp;
               p.add(parent.right);
               ans.add(p);
               t++;
            }
            if(parent.left==null && parent.right==null)
            {
                correctInd.add(prevt-1);
            }
            count++;
        }
        for (int i = 0; i < correctInd.size(); i++) {
            ans1.add(ans.get(correctInd.get(i)));
        }
        return ans1;
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
