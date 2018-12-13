package ibitnextgreaternumberbst;
public class IBitNextGreaterNumberBST {

    public static void main(String[] args) {
        TreeNode a=new TreeNode(101);
        TreeNode b=new TreeNode(102);
        TreeNode c=new TreeNode(99);
        TreeNode d=new TreeNode(100);
        TreeNode e=new TreeNode(96);
        TreeNode f=new TreeNode(97);
        a.right=b;
        a.left=c;
        c.left=e;
        c.right=d;
        e.right=f;
        System.out.println(getNlgNd(a, 101).val);
    }
    public static TreeNode getNlgNd(TreeNode root,int b)
    {
        if(root==null || (root.right==null && root.val==b))
            return null;
        TreeNode p=root;
        TreeNode up=p;
        TreeNode down=p;
        while(p.val!=b)
        {
            if(p.left!=null && p.right!=null)
            {
                if(p.left.val>b && p.right.val>b )
                {
                    p=p.left;
                    up=p;
                }
                else if(p.left.val<b && p.right.val>b)
                {
                    if(p.val<b)
                    {
                        p=p.right;
                        up=p;
                    }
                    else
                    {
                        break;
                    }
                }
            }
            else if(p.left==null)
            {
                if(p.val<=b)
                {
                    p=p.right;
                    up=p;
                }
            }
            else if(p.right==null)
            {
                if(p.val>b && p.left.val>b)
                {
                    p=p.left;
                    up=p;
                }
            }
        }
        p=root;
        while(p.val!=b)
        {
            if(p.val>b)
                p=p.left;
            else
                p=p.right;
        }
        if(p.right!=null)
        {
            p=p.right;
            down=p;
        }
        while(p.val>b)
        {
            if(p.left!=null && p.right!=null)
            {
                if(p.left.val>b && p.right.val>b )
                {
                    p=p.left;
                    down=p;
                }
                else if(p.left.val<b && p.right.val>b)
                {
                    if(p.val<b)
                    {
                        p=p.right;
                        if(down.val>p.val)
                            down=p;
                    }
                    else
                    {
                        break;
                    }
                }
            }
            else if(p.right==null)
            {
                if(p.val>b && p.left.val>b)
                {
                    p=p.left;
                    down=p;
                }
            }
        }
        return (up.val>down.val?down:up);
    }
}

 class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
}
