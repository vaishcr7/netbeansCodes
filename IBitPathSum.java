package ibitpathsum;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class IBitPathSum {

    public static void main(String[] args) {
 TreeNode a=new TreeNode(10);
        TreeNode b=new TreeNode(8);
        TreeNode c=new TreeNode(16);
        a.left=b;
        a.right=c;
        TreeNode d=new TreeNode(5);
        b.left=d;
        TreeNode e=new TreeNode(3);
        TreeNode f=new TreeNode(7);
        d.left=e;
        d.right=f;
        TreeNode o=new TreeNode(6);
        f.left=o;
        TreeNode g=new TreeNode(4);
        e.right=g;
        TreeNode h=new TreeNode(1);
        e.left=h;
//        TreeNode i=new TreeNode(2);
//        h.right=i;
        TreeNode j=new TreeNode(15);
        TreeNode k=new TreeNode(22);
        c.left=j;
        c.right=k;
        TreeNode l=new TreeNode(12);
        j.left=l;
        TreeNode m=new TreeNode(17);
        k.left=m;
        TreeNode n=new TreeNode(20);
        m.right=n;
        ArrayList<Integer> al=new ArrayList<>(levOrd(a));
        System.out.println("initial trvsl");
        for (int t : al) {
            System.out.print(" "+t);
        }
        System.out.println("");
        System.out.println(path(a,13,0));
    }
    public static boolean path(TreeNode root,int sum,int currSum)
    {
        if(root==null && sum!=currSum)
        {
            System.out.println("root is null and sum != 0");
            return false;
        }
        if(root==null && sum==currSum)
        {
            System.out.println("root is null and sum = 0");
            return true;
        }
        if(root.left==null && root.right==null && (currSum+root.val)==sum)
        {
            System.out.println("leafnode= "+root.val+" and sum-curr = 0");
            return true;
        }
        else if(root.left==null && root.right==null && (currSum+root.val)!=sum)
        {
            System.out.println("leafnode= "+root.val+" and sum-curr != 0");
            return false;
        }
        boolean i=false;
        boolean j=false;
        if(root.left!=null)
            i=path(root.left,sum,currSum+root.val);
        if(root.right!=null)
            j=path(root.right,sum,currSum+root.val);
//        System.out.println("i= "+i+" and j= "+j+" for root= "+root.val);
        if(root.left!=null && root.right!=null)
        {
            return (i || j);
        }
        else if(root.left!=null && root.right==null)
        {
            return i;
        }
        else if(root.left==null && root.right!=null)
        {
            return j;
        }
        return sum==0;
    }
     public static ArrayList<Integer> levOrd(TreeNode root)
    {
        ArrayList<Integer> ans=new ArrayList<>();
        Queue<TreeNode> q=new LinkedList<>();
        q.offer(root);
        q.offer(null);
        while(!q.isEmpty())
        {
            TreeNode k=q.poll();
            if(k!=null)
            {
               ans.add(k.val);
               if(k.left!=null)
                   q.offer(k.left);
               if(k.right!=null)
                   q.offer(k.right);
            }
            else
            {
                if(!q.isEmpty())
                    q.offer(null);
            }
        }
        return ans;
    }
}
class TreeNode
{
    int val;
    TreeNode left,right;
    public TreeNode(int val) {
        this.val=val;
    }
    
}