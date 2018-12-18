package ibitsymtr;

public class IBitSymTr {

    public static void main(String[] args) {
        TreeNode a=new TreeNode(1);
        TreeNode b=new TreeNode(2);
        TreeNode c=new TreeNode(3);
        TreeNode d=new TreeNode(4);
        TreeNode e=new TreeNode(5);
        TreeNode f=new TreeNode(6);
        a.left=b;
        a.right=c;
        b.left=d;
        c.right=e;
        d.right=f;
        System.out.println(isSym(a));
    }
    public static boolean isSym(TreeNode root)
    {
        return compute(root.left, root.right);
    }
    public static boolean compute(TreeNode l,TreeNode r)
    {
        if((l==null && r!=null) || (l!=null && r==null))
        {
            return false;
        }
        else if((l.right==null && l.left==null) && (r.right==null && r.left==null))
        {
            return l.val==r.val;
        }
        boolean a=true,b=true;
        if(l.left!=null && r.right!=null)
            a=compute(l.left, r.right);
        if(l.right!=null && r.left!=null)
            b=compute(l.right, r.left);
        return a==b;
    }
}
class TreeNode
{
    int val;
    TreeNode left,right;
    public TreeNode(int val)
    {
        this.val=val;
    }
}
