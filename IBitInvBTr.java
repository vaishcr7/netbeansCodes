package ibitinvbtr;

import java.util.ArrayList;
import java.util.Stack;

public class IBitInvBTr {

    public static void main(String[] args) {
        TreeNode a=new TreeNode(10);
        TreeNode b=new TreeNode(8);
        TreeNode c=new TreeNode(16);
        a.left=b;
        a.right=c;
        TreeNode d=new TreeNode(5);
        b.left=d;
        TreeNode e=new TreeNode(3);
        TreeNode f=new TreeNode(17);
        d.left=e;
        d.right=f;
        TreeNode o=new TreeNode(6);
        f.left=o;
        TreeNode g=new TreeNode(4);
        e.right=g;
        TreeNode h=new TreeNode(1);
        e.left=h;
        TreeNode i=new TreeNode(2);
        h.right=i;
        TreeNode j=new TreeNode(15);
        TreeNode k=new TreeNode(22);
        c.left=j;
        c.right=k;
        TreeNode l=new TreeNode(12);
        j.left=l;
        TreeNode m=new TreeNode(7);
        k.left=m;
        TreeNode n=new TreeNode(20);
        m.right=n;
        ArrayList<TreeNode> al=new ArrayList<>(inord(a));
        System.out.println("initial trvsl");
        for (TreeNode treeNode : al) {
            System.out.print(" "+treeNode.val);
        }
        System.out.println("");
        a=inordInv(a);
        al=new ArrayList<>(inord(a));
        System.out.println("final trvsl");
        for (TreeNode treeNode : al) {
            System.out.print(" "+treeNode.val);
        }
        System.out.println("");
    }
    public static ArrayList<TreeNode> inord(TreeNode root)
    {
        ArrayList<TreeNode> ans=new ArrayList<>();
        Stack<TreeNode> st=new Stack<>();
        TreeNode f=root;
        while(f.left!=null)
        {
            st.push(f);
            f=f.left;
        }
        st.push(f);
        while(!st.isEmpty())
        {
            TreeNode k=st.pop();
            ans.add(k);
            if(k.right!=null)
            {
                k=k.right;
                while(k.left!=null)
                {
                    st.push(k);
                    k=k.left;
                }
                st.push(k);
            }
        }
        return ans;
    }
public static TreeNode inordInv(TreeNode root)
    {
        ArrayList<TreeNode> ans=new ArrayList<>();
        Stack<TreeNode> st=new Stack<>();
        TreeNode f=root;
        while(f.left!=null)
        {
            st.push(f);
            f=f.left;
        }
        st.push(f);
        while(!st.isEmpty())
        {
            TreeNode k=st.pop();
            if(k.right!=null)
            {
                TreeNode v=k;
                k=k.right;
                while(k.left!=null)
                {
                    st.push(k);
                    k=k.left;
                }
                st.push(k);
                TreeNode r=v.right;
                v.right=v.left;
                v.left=r;
            }
            else
            {
                k.right=k.left;
                k.left=null;
            }
        }
        return root;
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
