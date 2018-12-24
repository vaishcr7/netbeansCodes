package ibitrecovbtr;

import java.util.ArrayList;
import java.util.Stack;

public class IBitRecovBTr {

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
        TreeNode newRoot=fix(a);
        al=new ArrayList<>(inord(newRoot));
        System.out.println("final trvsl");
        for (TreeNode treeNode : al) {
            System.out.print(" "+treeNode.val);
        }
        System.out.println("");
    }
   public static obj proc(TreeNode root)
   {
       boolean p=false;
        if(root.left!=null && root.right!=null)
       {
           System.out.print("  case 1");
          if(root.left.val>root.val && root.right.val<root.val)
          {
             TreeNode temp=root.left;
             TreeNode l=root.left.left;
             TreeNode r=root.left.right;
             TreeNode lb=root.right.left;
             TreeNode rb=root.right.right;
             root.left=root.right;
             root.left.left=lb;
             root.left.right=rb;
             root.right=temp;
             root.right.left=l;
             root.right.right=r;
             System.out.println(" a");
             p=true;
          }
          else if(root.left.val>root.val && root.right.val>root.val)
          {
             TreeNode temp=root;
             TreeNode l=root.left.left;
             TreeNode r=root.left.right;
             TreeNode rb=root.right;
             root=root.left;
             root.right=rb;
             root.left=temp;
             temp.left=l;
             temp.right=r;
             System.out.println(" b");
             p=true;
          }
          else if(root.left.val<root.val && root.right.val<root.val)
          {
             TreeNode temp=root;
             TreeNode l=root.right.left;
             TreeNode r=root.right.right;
             TreeNode lb=root.left;
             root=root.right;
             root.left=lb;
             root.right=temp;
             temp.left=l;
             temp.right=r;
             System.out.println(" c");
             p=true;
          }
          else if(root.left.val<root.val && root.right.val>root.val)
          {
              System.out.println(" 4");
          }
       }
       else if(root.left==null && root.right!=null)
       {
          if(root.right.val<root.val)
          {
             TreeNode temp=root;
             TreeNode l=root.right.left;
             TreeNode r=root.right.right;
             TreeNode lb=root.left;
             root=root.right;
             root.left=lb;
             root.right=temp;
             temp.left=l;
             temp.right=r;
             System.out.println("case 2");
             p=true;
          }
       }
       else if(root.left!=null && root.right==null)
       {
           if(root.left.val>root.val)
          {
             TreeNode temp=root;
             TreeNode l=root.left.left;
             TreeNode r=root.left.right;
             TreeNode rb=root.right;
             root=root.left;
             root.right=rb;
             root.left=temp;
             temp.left=l;
             temp.right=r;
             System.out.println("case 3");
             p=true;
          }
       }
       return new obj(p, root);
   }
   public static TreeNode fix(TreeNode root)
   {
        ArrayList<TreeNode> al=new ArrayList<>(inord(root));
        TreeNode pf=null;
        TreeNode ps=null;
        TreeNode f=null;
        TreeNode s=null;
        if(proc(root).t)
            return root;
        for (int i = 1; i < al.size()-1; i++) {
           TreeNode a=al.get(i-1);
           TreeNode b=al.get(i);
           TreeNode c=al.get(i+1);
           System.out.println("CONSIDERING a= "+a.val+" b= "+b.val+" c= "+c.val);
           if(b.val>a.val && b.val>c.val && c.val>a.val)
           {
               System.out.println("case Large->a= "+a.val+" b= "+b.val+" c= "+c.val);
               if(f==null)
               {
                   pf=al.get(i-1);
                   f=b;
               }
               else 
               {
                   if(a!=f)
                   {    
                       ps=al.get(i-1);
                       s=b;
                   }
               }
           }
           else if(((f!=null && a!=f) || (f==null)) && b.val<a.val && b.val<c.val && a.val<c.val)
           {
               System.out.println("case Small->a= "+a.val+" b= "+b.val+" c= "+c.val);
                if(f==null)
               {
                   pf=al.get(i-1);
                   f=b;
               }
               else 
               {
                   if(a!=f)
                   {    
                       ps=al.get(i-1);
                       s=b;
                   }
               }
           }
           if(f!=null && s!=null)
           {
               System.out.println("breaking");
               break;
           }
       }
        if(al.get(0).val>al.get(1).val && f==null && s==null)
        {
            System.out.println("first case");
            f=al.get(0);
            s=al.get(1);            
        }
        else if(al.get(al.size()-1).val<al.get(al.size()-2).val  && f==null && s==null)
        {
            System.out.println("last case");
            f=al.get(al.size()-1);
            s=al.get(al.size()-2);            
        }
        if(s==null) // in case the nodes to be swapped are beside each other.
        {
            for (int i = 1; i < al.size(); i++) {
                if(s==al.get(i))
                {
                    f=al.get(i-1);
                    break;
                }
            }
        }
        TreeNode temp=s;
        TreeNode temp2=f;
        TreeNode l=f.left;
        TreeNode r=f.right;
        TreeNode sl=s.left;
        TreeNode sr=s.right;
        System.out.println("s= "+temp.val+" and f= "+f.val);
        if(l!=null)
            System.out.print("l= "+l.val);
        if(r!=null)
            System.out.print(" ,r= "+r.val);
        System.out.println("");
        if(sl!=null)
            System.out.print("sl= "+sl.val);
        if(sr!=null)
            System.out.print(" ,sr= "+sr.val);
        System.out.println("");
        s=f;
        s.left=l;
        s.right=r;
        f=temp;
        f.left=sl;
        f.right=sr;
        System.out.println("inside function inorder = ");
        for(TreeNode a: inord(root))
            System.out.print(" "+a.val);
        System.out.println("");
        return root;
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
    
}
class TreeNode
{
    int val;
    TreeNode left,right;
    public TreeNode(int val) {
        this.val=val;
    }
    
}
class obj
{
    boolean t;
    TreeNode tr;

    public obj(boolean t, TreeNode tr) {
        this.t = t;
        this.tr = tr;
    }    
}
