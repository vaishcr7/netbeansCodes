package ibitcrtrpointrvs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class IBitCrTrPoInTrvs {

    public static void main(String[] args) {
        ArrayList<Integer> ie=new ArrayList<>();
        ie.add(10);
        ie.add(15);
        ie.add(6);
        ie.add(5);
        ie.add(1);
        ie.add(8);
        ie.add(11);
        ie.add(18);
        ie.add(20);
        ie.add(7);
        ie.add(4);
        ie.add(12);
        ie.add(9);
        
        ArrayList<Integer> pe=new ArrayList<>();
        pe.add(20);
        pe.add(18);
        pe.add(15);
        pe.add(10);
        pe.add(11);
        pe.add(8);
        pe.add(6);
        pe.add(5);
        pe.add(1);
        pe.add(7);
        pe.add(12);
        pe.add(4);
        pe.add(9);
        
        ArrayList<TreeNode> in=new ArrayList<>();
        ArrayList<TreeNode> po=new ArrayList<>();
        Map<Integer,TreeNode> mp=new HashMap<>();
        for (Integer integer : ie) {
            mp.put(integer,new TreeNode(integer));
            in.add(mp.get(integer));
        }
        for (Integer integer : pe) {
            po.add(mp.get(integer));
        }
       TreeNode head=convFromInPre(in, po);
//        TreeNode head=convFromInPost(in, po);
//        System.out.println(head.val); 
//        System.out.println(head.left.val); 
//        System.out.println(head.right.val); 
    }
    public static TreeNode convFromInPost(ArrayList<TreeNode>in,ArrayList<TreeNode>po)
    {
        if(po.isEmpty())
        {
//            System.out.println("returning null");
            return null;
        }
        if(po.size()==1)
        {
//            System.out.println("returning "+po.get(po.size()-1).val);
            return po.get(po.size()-1);
        }
        TreeNode root=po.get(po.size()-1);
//        System.out.println("current root is "+root.val);
        int pos=0;
        for (int i = 0; i < in.size(); i++) {
            if(in.get(i).equals(root))
            {
                pos=i;
                break;
            }
        }
//        System.out.println("pos= "+pos);
        ArrayList<TreeNode> leftIn=new ArrayList<>();
//        System.out.print("leftin is ");
        for (int i = 0; i < pos; i++) {
            leftIn.add(in.get(i));
//            System.out.print(" "+in.get(i).val);
        }
//        System.out.println("");
//        System.out.print("leftPo is ");
        ArrayList<TreeNode> leftPo=new ArrayList<>();
        for (int i = 0; i < pos; i++) {
            leftPo.add(po.get(i));
//            System.out.print(" "+po.get(i).val);
        }
//        System.out.println("");
        ArrayList<TreeNode> rightIn=new ArrayList<>();
//        System.out.print("rightin is ");        
        for (int i = pos+1; i < in.size(); i++) {
             rightIn.add(in.get(i));
//            System.out.print(" "+in.get(i).val);
        }
//        System.out.println("");
        ArrayList<TreeNode> rightPo=new ArrayList<>();
//        System.out.print("rightPo is ");
        for (int i = pos; i < po.size()-1; i++) {
             rightPo.add(po.get(i));
            System.out.print(" "+po.get(i).val);
        }
//        System.out.println("");
        root.left=convFromInPost(leftIn, leftPo);
        root.right=convFromInPost(rightIn,rightPo);
//        System.out.println("\n\n\n root= "+root.val);
//        if(root.left!=null)
//            System.out.println("root left= "+root.left.val);
//        else
//            System.out.println("root's left is null");
//        if(root.right!=null)
//            System.out.println("root right= "+root.right.val+" \n\n\n");
//        else
//            System.out.println("root's right is null \n\n\n");
        return root;
    }
    
    public static TreeNode convFromInPre(ArrayList<TreeNode>in,ArrayList<TreeNode>pre)
    {
        if(pre.isEmpty())
        {
//            System.out.println("returning null");
            return null;
        }
        if(pre.size()==1)
        {
//            System.out.println("returning "+pre.get(pre.size()-1).val);
            return pre.get(0);
        }
        TreeNode root=pre.get(0);
//        System.out.println("current root is "+root.val);
        int pos=0;
        for (int i = 0; i < in.size(); i++) {
            if(in.get(i).equals(root))
            {
                pos=i;
                break;
            }
        }
//        System.out.println("pos= "+pos);
        ArrayList<TreeNode> leftIn=new ArrayList<>();
//        System.out.print("leftin is ");
        for (int i = 0; i < pos; i++) {
            leftIn.add(in.get(i));
//            System.out.print(" "+in.get(i).val);
        }
//        System.out.println("");
//        System.out.print("leftPre is ");
        ArrayList<TreeNode> leftPre=new ArrayList<>();
        for (int i = 1; i <=pos; i++) {
            leftPre.add(pre.get(i));
//            System.out.print(" "+pre.get(i).val);
        }
//        System.out.println("");
        ArrayList<TreeNode> rightIn=new ArrayList<>();
//        System.out.print("rightin is ");        
        for (int i = pos+1; i < in.size(); i++) {
             rightIn.add(in.get(i));
//            System.out.print(" "+in.get(i).val);
        }
//        System.out.println("");
        ArrayList<TreeNode> rightPre=new ArrayList<>();
//        System.out.print("rightPre is ");
        for (int i = pos+1; i < pre.size(); i++) {
             rightPre.add(pre.get(i));
//            System.out.print(" "+pre.get(i).val);
        }
//        System.out.println("");
        root.left=convFromInPre(leftIn, leftPre);
        root.right=convFromInPre(rightIn,rightPre);
//        System.out.println("\n\n\n root= "+root.val);
//        if(root.left!=null)
//            System.out.println("root left= "+root.left.val);
//        else
//            System.out.println("root's left is null");
//        if(root.right!=null)
//            System.out.println("root right= "+root.right.val+" \n\n\n");
//        else
//            System.out.println("root's right is null \n\n\n");
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
