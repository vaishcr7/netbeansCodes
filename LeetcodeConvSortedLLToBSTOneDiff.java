package ibitpartitionlst;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class LeetcodeConvSortedLLToBSTOneDiff {
   
 //Definition for singly-linked list.
 static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
 

//* Definition for a binary tree node.
  static class TreeNode {
     int val;
     TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
    public static void main(String[] args) {
         ListNode a=new  ListNode(1);
         ListNode b=new  ListNode(2);
         ListNode c=new  ListNode(3);
         ListNode d=new  ListNode(4);
         ListNode e=new  ListNode(5);
         ListNode f=new  ListNode(6);
         ListNode g=new  ListNode(7);
         ListNode h=new  ListNode(8);
         ListNode i=new  ListNode(9);
        a.next=b;
        b.next=c;
        c.next=d;
        d.next=e;
        e.next=f;
        f.next=g;
        g.next=h;
        h.next=i;        
        TreeNode root=new LeetcodeConvSortedLLToBSTOneDiff().sortedListToBST(a);
         new LeetcodeConvSortedLLToBSTOneDiff().levelOrder(root);
    }
    public TreeNode sortedListToBST(ListNode head) {
        TreeNode root=null;
        int len=0;
        if(head==null)
            return root;
        else if(head.next==null){
            root=new TreeNode(head.val);
            return root;
        }
        ListNode temp=head;
        while(temp!=null){
            len+=1;
            temp=temp.next;
        }
        temp=head;
        int u=0;
        while(u<len/2){
            temp=temp.next;
            u++;
        }
        ListNode end=head;
        while(end.next!=null)
            end=end.next;
        root=new TreeNode(temp.val);
//        System.out.println("current root= "+root.val);
        ListNode p=getPrevNode(head, temp);
        if(p!=null)
            root.left=left(head,p);
        temp=getNextNode(temp,end);
        if(temp!=null)
            root.right=right(temp,end);
        return root;
    } 
// all params are inclusive 
    public TreeNode left(ListNode start,ListNode end){ 
        ListNode temp=start;
        int u=0;
        while(temp!=end){
            temp=temp.next;
            u++;
        }
        temp=start;
        int y=0;
        while(y<u/2)
        {
            y++;
            temp=temp.next;            
        }
        TreeNode root=new TreeNode(temp.val);
//        System.out.println("Inside LEFT current node is "+root.val);
        if(temp==start && temp==end)  
        {
//            System.out.println("reached child node  for start "+start.val+" , temp= "+temp.val+" and end= "+end.val+"\n");
            return root;
        }
//        System.out.println("for start= "+start.val+" and end= "+end.val+" root= "+root.val);
        ListNode p=getPrevNode(start, temp);
        if(p!=null)
            root.left=left(start, p);
        temp=getNextNode(temp,end);
        if(temp!=null)
            root.right=right(temp,end);
//        if(root.left!=null)
//            System.out.println(root.val+"'s left is "+root.left.val);
//        if(root.right!=null)
//            System.out.println(root.val+"'s right is "+root.right.val);
//        System.out.println("");
        return root;
    }
    public TreeNode right(ListNode start,ListNode end){
        ListNode temp=start;
        int u=0;
        while(temp!=end){
            temp=temp.next;
            u++;
        }
        temp=start;
        int y=0;
        while(y<u/2)
        {
            y++;
            temp=temp.next;            
        }
//        System.out.println("Inside RIGHT current node is "+temp.val+" and start= "+start.val+" and end= "+end.val);
        TreeNode root=new TreeNode(temp.val);
        if(temp==end && temp==start){
//            System.out.println("RIGHT reached child node  "+temp.val+" ,end= "+end.val+" and start= "+start.val+"\n");
            return root;
        }
        ListNode p=getPrevNode(start, temp);
        if(p!=null)
        {
//            System.out.println("p right= "+p.val);
            root.left=left(start, p);
        }
        temp=getNextNode(temp, end);
        if(temp!=null)
            root.right=right(temp,end);
//        System.out.println("for start= "+start.val+" and end= "+end.val+" root= "+root.val);
//        if(root.left!=null)
//            System.out.println(root.val+"'s left is "+root.left.val);
//        if(root.right!=null)
//            System.out.println(root.val+"'s right is "+root.right.val);
//        System.out.println("");
        return root;
    }
    public ListNode getPrevNode(ListNode head,ListNode curr){
        if(head==curr)
            return null;
        while(head.next!=curr)
            head=head.next;
        return head;
    }
    public ListNode getNextNode(ListNode curr,ListNode end){
        if(curr==end )
            return null;
        return curr.next;
    }
    public void levelOrder(TreeNode root){
        Queue<TreeNode> aq=new LinkedList<>();
        aq.offer(root);
        aq.offer(null);
        ArrayList<ArrayList<TreeNode>> ans=new ArrayList<>();
        ArrayList<TreeNode> ap=new ArrayList<>();
        while(!aq.isEmpty()){            
            TreeNode p=aq.poll();            
            if(p!=null){
                if(p.left!=null)
                    aq.offer(p.left);
                if(p.right!=null)
                    aq.offer(p.right);
                ap.add(p);
            }
            else{
                if(!aq.isEmpty()){
                    aq.offer(null);
                    ans.add(ap);
                    ap=new ArrayList<>();
                }
            }
        }
        if(!ap.isEmpty())
            ans.add(ap);
        for (ArrayList<TreeNode> an : ans) {
            for (TreeNode treeNode : an) {
                System.out.print(treeNode.val+" , ");
            }
            System.out.println("");
        }
    }
    
}
