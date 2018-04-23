package convDLLtoBST;
import java.util.ArrayDeque;

public class BST {
    public static void main(String[] args) {
        BSTNode root =new BSTNode(0);
        BSTNode node2 =new BSTNode(1);
        BSTNode node3 =new BSTNode(2);
        BSTNode node4 =new BSTNode(3);
        BSTNode node5 =new BSTNode(4);
        BSTNode node6 =new BSTNode(5);
        BSTNode node7 =new BSTNode(6);
        root.left=node2;
        root.right=node3;
        node2.left=node4;
        node2.right=node5;
        node3.left=node6;
        node3.right=node7;
        DllNode node=conv(root);
        if(node.next!=null)
        System.out.println(node.next.node.data+" and "+node.previous.node.data);
        while(node.previous!=null)
            node=node.previous;
        System.out.println("hello");
        while(node.next!=null)
            System.out.print("  "+node.node.data);
    }
    public static DllNode conv(BSTNode root)
    {
       DllNode head=null;
        if(root==null)
            return head;
        BSTNode temp=root;
        head= new DllNode(root);
        ArrayDeque<BSTNode> aq=new ArrayDeque<>();
        if(root.left==null && root.right==null )
            return new DllNode(root);
        boolean done=false;
         DllNode temp1=null,prev1=null;
        while(!done)
        {       
            if(temp!=null)
            {
                aq.push(temp);
                temp=temp.left;
            }
            else
            {
                if(aq.isEmpty())
                {
                    done=true;
                    break;
                }
               // System.out.println("else");
                temp=aq.pop();
                if(temp1==null && prev1==null)
                {  
                 temp1=new DllNode(temp);
                 head=temp1;
                 prev1=new DllNode(aq.peek());
                }
                else
                {
                    prev1=temp1;
                    temp1=new DllNode(temp);
                }
               // System.out.println("temp= "+temp1.node.data+" and prev= "+prev1.node.data);
                prev1.next=temp1;
                temp1.previous=prev1;
                temp=temp.right;
                if(temp!=null && !aq.isEmpty())
                    aq.push(temp);
            }
           // for(BSTNode n: aq)
             //   System.out.print(" n= "+n.data);
           // System.out.println("");
           // if(temp!=null && prev!=null)
           //     System.out.println("temp= "+temp.data+" and prev= "+prev.data);
        }
        return head;
    }
}

class BSTNode
{
    int data;
    BSTNode left,right;

    public BSTNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }    
}
class DllNode
{
    DllNode previous,next;
    BSTNode node;

    public DllNode(BSTNode node) {
        this.previous = null;
        this.next = null;
        this.node = node;
    }
    
}