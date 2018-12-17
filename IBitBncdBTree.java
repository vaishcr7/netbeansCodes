package ibitbncdbtree;

import java.util.HashMap;
import java.util.Map;

public class IBitBncdBTree {

    public static void main(String[] args) {
        TreeNode a=new TreeNode(3);
        TreeNode b=new TreeNode(9);
        TreeNode c=new TreeNode(20);
        TreeNode d=new TreeNode(15);
        TreeNode e=new TreeNode(7);
        TreeNode f=new TreeNode(10);
        TreeNode g=new TreeNode(11);
        a.left=b;
//        a.right=c;
//        b.left=f;
//        c.left=d;
//        c.right=e;
//        b.right=f;
        System.out.println(balanc(a));
    }
    public static boolean balanc(TreeNode root)
    {
        Map<TreeNode,Integer> mp=new HashMap<>();
        if(root==null)
            return true;
        if(root.left==null && root.right==null)
        {
            System.out.println("case 1");
            return true;
        }
        else if(root.left!=null && root.right==null && height(root.left, mp)>0)
        {
            System.out.println("case 2");
            System.out.println("height of "+root.left.val+" = "+height(root.left, mp));
            return false;
        }
        else if(root.left!=null && root.right==null && height(root.left, mp)==0)
        {
            System.out.println("case 3");
            return true;
        }
        else if(root.left==null && root.right!=null  && height(root.right, mp)>0)
        {
            System.out.println("case 4");
            return false;
        }
        else if(root.left==null && root.right!=null  && height(root.right, mp)==0)
        {
            System.out.println("case 5");
            System.out.println(height(root.right, mp));
            return true;
        }
        return (Math.abs(height(root.left, mp)-height(root.right, mp)))<=1;
    }
    public static int height(TreeNode a,Map<TreeNode,Integer> mp)
    {
        if(a==null)
        {
            mp.put(a,0);
            return 0;
        }
        if(a.left==null && a.right==null)
        {
            mp.put(a,0);
            return 0;
        }
        if(!mp.containsKey(a))
        {   
            int j=height(a.left,mp)+height(a.right,mp)+1;            
            mp.put(a,j);
            return j;
        }
        else
            return mp.get(a);
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
//[1,2,2,3,null,null,3,4,null,null,4]