package ibittrvsl;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Stack;

public class IBitTrvsl {

    public static void main(String[] args) {
//        TreeNode a=new TreeNode(1);
//        TreeNode b=new TreeNode(2);
//        TreeNode c=new TreeNode(3);
//        TreeNode d=new TreeNode(4);
//        TreeNode e=new TreeNode(5);
//        TreeNode f=new TreeNode(6);
//        TreeNode g=new TreeNode(7);
//        TreeNode h=new TreeNode(8);
//        a.left=b;
//        a.right=c;
//        b.left=d;
//        c.right=e;
//        d.left=f;
//        d.right=g;
//        e.left=h;

        TreeNode a=new TreeNode(3);
        TreeNode b=new TreeNode(9);
        TreeNode c=new TreeNode(20);
        TreeNode d=new TreeNode(15);
        TreeNode e=new TreeNode(7);
        TreeNode f=new TreeNode(10);
        a.left=b;
        a.right=c;
        b.left=f;
        c.left=d;
        c.right=e;
        System.out.println(zigzag(a));
//        System.out.println(postOrd(a));
    }
    public static ArrayList<ArrayList<Integer>> zigzag(TreeNode root)
    {
        ArrayList<ArrayList<Integer>> ans=new ArrayList<>();
        if(root==null)
            return ans;
        ArrayDeque<TreeNode> aq=new ArrayDeque<>();
        boolean dir=true;
        aq.offer(root);
        while(!aq.isEmpty())
        {
//            System.err.println("dir= "+dir);
            if(!dir)//go right
            {
//               System.out.println("here");
               int size=aq.size();
               ArrayList<Integer> ansf=new ArrayList<>();
               ArrayDeque<TreeNode> lp=new ArrayDeque<>();
               while(size-->0)
               {
                   TreeNode t=aq.pollLast();
                   ansf.add(0,t.val);
//                   System.out.println("t= "+t.val);
                   if(t.left!=null)
                    lp.push(t.left);
                   if(t.right!=null)
                    lp.push(t.right);
//                   for (TreeNode treeNode : lp) {
//                       System.out.print(" "+treeNode.val);
//                   }
//                   System.out.println("");
               }
               aq.addAll(lp);
               ans.add(new ArrayList<>(ansf));
            }
            else//go left
            {
//               System.out.println("there");
               int size=aq.size();
               ArrayList<Integer> ansf=new ArrayList<>();
               ArrayDeque<TreeNode> lp=new ArrayDeque<>();
               while(size-->0)
               {
                   TreeNode t=aq.pollLast();
//                   System.out.println("t= "+t.val);
                   ansf.add(t.val);
                   if(t.left!=null)
                    lp.push(t.left);
                   if(t.right!=null)
                    lp.push(t.right);
//                    for (TreeNode treeNode : lp) {
//                        System.out.print(" " + treeNode.val);
//                }
//                System.out.println("");
               }
               aq.addAll(lp);
               ans.add(new ArrayList<>(ansf));
            }
            dir=!dir;
        }
        return ans;
    }
    
//    public static ArrayList<Integer> preOrd(TreeNode root)
//    {
//        ArrayList<Integer> ans=new ArrayList<>();
//        if(root==null)
//            return ans;
//        Stack<TreeNode> st=new Stack<>();
//        st.push(root);
//        TreeNode k=root;
//        while(!st.isEmpty())
//        {
//                k=st.pop();
//                ans.add(k.val);
////                System.out.print("k= "+k.val);
//                if(k.right!=null)
//                {
//                    st.push(k.right);
////                    System.out.println("case 3");
//                }
//                if(k.left!=null)
//                {
//                    st.push(k.left);
////                    System.out.println("case 1");
//                }
//          }
//        return ans;
//    }    
//    public static ArrayList<Integer> postOrd(TreeNode root)
//    {
//        ArrayList<Integer> ans=new ArrayList<>();
//        if(root==null)
//            return ans;
//        Stack<TreeNode> st=new Stack<>();
//        st.push(root);
//        TreeNode k=root;
//        while(!st.isEmpty())
//        {
//                k=st.pop();
//                boolean g=false;
////                System.out.print("k= "+k.val);
//                if(k.left!=null || k.right!=null)
//                {
//                    g=true;
////                    System.out.print("  , g= "+g);
//                    boolean f=false;
//                    if (k.right != null) {
//                        st.push(k);
//                        st.push(k.right);
//                        f = true;
//                        k.right = null;
////                    System.out.println("  case 1");
//                    }
//                    if (k.left != null) {
//                        if (!f) {
//                            st.push(k);
//                        }
//                        st.push(k.left);
//                        k.left = null;
////                    System.out.println("  case 2");
//                    }
//                }
//                if(!g)
//                {
////                    System.out.print("  new g= "+g);
//                    ans.add(k.val);
////                    System.out.println("   adding "+k.val+" to answer");
//                }
//          }
//        return ans;
//    }
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
