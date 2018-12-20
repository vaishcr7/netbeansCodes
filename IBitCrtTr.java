// package ibitcrttr;

// import java.util.ArrayList;
// import java.util.Stack;

// public class IBitCrtTr {

//     public static void main(String[] args) {
//         //1,2,3,4,5,6
//         TreeNode a=new TreeNode(1);
//         TreeNode b=new TreeNode(2);
//         TreeNode c=new TreeNode(3);
//         TreeNode d=new TreeNode(4);
//         TreeNode e=new TreeNode(5);
//         TreeNode f=new TreeNode(6);
// //        TreeNode g=new TreeNode(6);
// //        TreeNode h=new TreeNode(3);
// //        TreeNode i=new TreeNode(5);
// //        a.left=b;
// //        a.right=c;
// //        b.left=d;
// //        b.right=e;
// //        c.left=f;
// //        c.right=g;
// //        e.right=h;
// //        g.left=i;
//         ArrayList<TreeNode> al=new ArrayList<>();
//         al.add(a);
//         al.add(c);
//         al.add(b);
//         al.add(d);
//         al.add(f);
//         al.add(e);
// //        al.add(c);
// //        al.add(i);
// //        al.add(g);
//         TreeNode head=formTr(al);
//         System.out.println(inorderTraversal(head));
//     }
//     public static TreeNode formTr(ArrayList<TreeNode> al)
//     {
//         if(al.isEmpty())
//             return null;
//         if(al.size()==1)
//             return al.get(0);
//         TreeNode head=al.get(0);
//         int pos=0;
//         for (int i = 1; i < al.size(); i++) {
//             if(al.get(i).val>head.val)
//             {
//                 head=al.get(i);
//                 pos=i;
//             }
//         }
// //        System.out.println("pos= "+pos);
//         if(pos>0)
//         {
//             TreeNode lh = al.get(pos - 1);
            
//             for (int i = pos - 2; i >= 0; i--) {
//                 if(lh!=null)
//                     System.out.println("lh= "+lh.val);
//                 if (lh.val > al.get(i).val) 
//                 {
//                     if (lh.left == null) 
//                     {
//                         lh.left = al.get(i);
//                         System.out.println("lh left= "+lh.left.val);
//                     } 
//                     else if(lh.right==null && lh.left.val<al.get(i).val) 
//                     {
//                         TreeNode temp=lh.left;
//                         lh.left=al.get(i);
//                         lh.left.left=temp;
//                         System.out.println("SWAPPED "+lh.left.val+" and "+lh.left.left.val);
//                     }
//                     else if(lh.right==null && lh.left.val>al.get(i).val) 
//                     {
//                         TreeNode j=lh;
//                         //??
//                         while(j.left!=null)
//                             j=j.left;
//                         j.left = al.get(i);
//                         System.out.println("new lh left= "+j.left.val);
//                     }                    
//                 } 
//                 else 
//                 {
//                     al.get(i).right = lh;
//                     lh = al.get(i);
//                     System.out.println("new lh= "+lh.val+" its right= "+lh.right.val);
//                 }
//             }
//             head.left = lh;
//             System.out.println("head's immediate left= "+head.left.val);
//         }
//         if(pos<al.size()-1)
//         {
//             TreeNode rh = al.get(al.size()-1);
//             System.out.println("rh= "+rh.val);
//             for (int i = al.size()-2; i >pos; i--) {
//                  if (rh.val > al.get(i).val) 
//                {
//                     if (rh.left == null) 
//                     {
//                         rh.left = al.get(i);
//                         System.out.println("rh left= "+rh.left.val);
//                     } 
//                     else if(rh.right==null) {
//                         rh.right = al.get(i);
//                         System.out.println("rh right= "+rh.right.val);
//                         TreeNode temp=rh.right;
//                         rh.right=rh.left;
//                         rh.left=temp;
//                         System.out.println("SWAPPED "+rh.right.val+" and "+rh.left.val);
//                     }
// //                    else
// //                    {
// //                        rh=rh.right;
// //                        i-=1;
// //                        System.out.println("shifted to new ");
// //                    }
//                 } 
//                  else {
//                     al.get(i).right = rh;
//                     rh = al.get(i);
//                     System.out.println("new rh= "+rh.val+" its right= "+rh.right.val);
//                 }
//             }
//             System.out.println("RH= "+rh.val);
//             head.right = rh;
//             System.out.println("head's immediate right= "+head.right.val);
//         }
        
        
// //        if(pos<al.size()-1)
// //        {
// //            TreeNode rh = al.get(pos + 1);
// //            System.out.println("rh= "+rh.val);
// //            for (int i = pos + 2; i < al.size(); i++) {
// //               if (rh.val > al.get(i).val) 
// //               {
// //                    if (rh.left == null) 
// //                    {
// //                        rh.left = al.get(i);
// //                        System.out.println("rh left= "+rh.left.val);
// //                    } 
// //                    else if(rh.right==null) {
// //                        rh.right = al.get(i);
// //                        System.out.println("rh right= "+rh.right.val);
// //                    }
// //                    else
// //                    {
// //                        rh=rh.right;
// //                        i-=1;
// //                        System.out.println("shifted to new ");
// //                    }
// //                } else {
// //                    al.get(i).left = rh;
// //                    rh = al.get(i);
// //                    System.out.println("new rh= "+rh.val+" its left= "+rh.left.val);
// //                }
// //            }
// //            head.right = rh;
// //            System.out.println("head's immediate right= "+head.right.val);
// //        }
//         return head;
//     }
//     public static ArrayList<Integer> inorderTraversal(TreeNode root) {
//         ArrayList<Integer> ans=new ArrayList<>();
//         if(root==null)
//             return ans;
//         Stack<TreeNode> st=new Stack<>();
//         st.push(root);
//         boolean done =false;
//         TreeNode k=root;
//         while(!st.isEmpty())
//         {
//                 k=st.pop();
// //                System.out.print("k= "+k.val);
//                 if(k.left!=null)
//                 {
//                     st.push(k);
//                     st.push(k.left);
//                     k.left=null;
// //                    System.out.println("case 1");
//                 }
//                 else if(k.right==null)
//                 {
//                     ans.add(k.val);
// //                    System.out.println("case 2");
//                 }
//                 else if(k.right!=null)
//                 {
//                     ans.add(k.val);
//                     st.push(k.right);
// //                    System.out.println("case 3");
//                 }
//           }
//         return ans;
//     }
// }
// class TreeNode
// {
//     int val;
//     TreeNode left,right;
//     public TreeNode(int val) {
//         this.val=val;
//     }
    
// }


import java.util.ArrayList;
import java.util.Stack;

public class IBitCrtTr {

    public static void main(String[] args) {
        //1,2,3,4,5,6
        TreeNode a=new TreeNode(1);
        TreeNode b=new TreeNode(2);
        TreeNode c=new TreeNode(3);
        TreeNode d=new TreeNode(4);
        TreeNode e=new TreeNode(5);
        TreeNode f=new TreeNode(6);
//        TreeNode g=new TreeNode(6);
//        TreeNode h=new TreeNode(3);
//        TreeNode i=new TreeNode(5);
//        a.left=b;
//        a.right=c;
//        b.left=d;
//        b.right=e;
//        c.left=f;
//        c.right=g;
//        e.right=h;
//        g.left=i;
        ArrayList<TreeNode> al=new ArrayList<>();
        al.add(a);
        al.add(c);
        al.add(b);
        al.add(d);
        al.add(f);
        al.add(e);
//        al.add(c);
//        al.add(i);
//        al.add(g);
        al=new ArrayList<>(formTr(al));
        for(TreeNode p: al)
        {
            System.out.print("node: "+p.val);
            if(p.left==null)
                System.out.print("  , left is null");
            else
                System.out.print(",left= "+p.left.val);
            if(p.right==null)    
                System.out.println("  , right is null");
            else
                System.out.println(",right= "+p.right.val);
        }
        // System.out.println("\n head is "+head.val);
        // System.out.println(" = "+head.left.val);
        // System.out.println(" = "+head.right.val);
        // System.out.println(" = "+head.left.left.val);
        // System.out.println(" = "+head.left.left.left.val);
        // System.out.println(" = "+head.left.left.right.val);
    }
    public static ArrayList<TreeNode> formTr(ArrayList<TreeNode> al)
    {
        if(al.isEmpty())
            return null;
        if(al.size()==1)
            return al;// return al.get(0);
        TreeNode head=al.get(0);
        int pos=0;
        for (int i = 1; i < al.size(); i++) {
            if(al.get(i).val>head.val)
            {
                head=al.get(i);
                pos=i;
            }
        }
//        System.out.println("pos= "+pos);
        if(pos>0)
        {
            TreeNode l=null,m=al.get(0),r=null;
            m.left=l;
            m.right=r;
            for (int i = 1;i<pos ;i++) {
                if(m!=null)
                    System.out.println("m= "+m.val);
                if (m.val > al.get(i).val) 
                {
                    if (r == null) 
                    {
                        r = al.get(i);
                        m.right=r;
                        System.out.println("r= "+r.val);
                    } 
                    else
                    {
                        al.get(i).left=l;
                        al.get(i).right=r;
                        m.right=al.get(i);
                        r=m.right;
                        System.out.println("SWAPPED "+m.val+" and "+al.get(i).val);
                    }                   
                } 
                else 
                {
                    l=m;
                    m=al.get(i);
                    m.left=l;
                    System.out.println("m= "+m.val+" and l= "+l.val);
                }
            }
            head.left = m;
            System.out.println("head's immediate left= "+m.val);
        }
        if(pos<al.size()-1)
        {
            TreeNode l=null,m=al.get(al.size()-1),r=null;
            m.left=l;
            m.right=r;
            for (int i = al.size()-1; i >pos; i--) {
                 if (m.val > al.get(i).val) 
               {
                     if (l == null) 
                    {
                        l = al.get(i);
                        m.left=l;
                        System.out.println("r= "+r.val);
                    } 
                    else
                    {
                        al.get(i).left=l;
                        al.get(i).right=r;
                        m.left=al.get(i);
                        l=m.left;
                        System.out.println("SWAPPED "+m.val+" and "+al.get(i).val);
                    }                   
                } 
                else 
                {
                    r=m;
                    m=al.get(i);
                    m.right=r;
                    System.out.println("m= "+m.val+" and r= "+r.val);
                }
            }
            System.out.println("RH= "+m.val);
            head.right = m;
            System.out.println("head's immediate right= "+m.val);
        }
        System.out.println(" = "+head.val);
        System.out.println(" = "+head.left.val);
        System.out.println(" = "+head.right.val);
        System.out.println(" = "+head.left.left.val);
        System.out.println(" = "+head.left.left.left.val);
        System.out.println(" = "+head.left.left.right.val);
        // return head;
        return al;
    }
    public static ArrayList<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> ans=new ArrayList<>();
        if(root==null)
            return ans;
        Stack<TreeNode> st=new Stack<>();
        st.push(root);
        boolean done =false;
        TreeNode k=root;
        while(!st.isEmpty())
        {
                k=st.pop();
                System.out.print("k= "+k.val);
                if(k.left!=null)
                {
                    st.push(k);
                    st.push(k.left);
                    k.left=null;
                    System.out.println("case 1");
                }
                else if(k.right==null)
                {
                    ans.add(k.val);
                    System.out.println("case 2");
                }
                else if(k.right!=null)
                {
                    ans.add(k.val);
                    st.push(k.right);
                    System.out.println("case 3");
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
