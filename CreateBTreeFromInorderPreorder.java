package createbtreefrominorder.preorder;

import java.util.Arrays;

public class CreateBTreeFromInorderPreorder {

    public static void main(String[] args) {
      BinaryTreeNode a=new BinaryTreeNode('A');
      BinaryTreeNode b=new BinaryTreeNode('B');
      BinaryTreeNode c=new BinaryTreeNode('C');
      a.left=b;
      a.right=c;
      BinaryTreeNode d=new BinaryTreeNode('D');
      b.left=d;
      BinaryTreeNode e=new BinaryTreeNode('E');
      d.left=e;
      BinaryTreeNode f=new BinaryTreeNode('F');
      BinaryTreeNode g=new BinaryTreeNode('G');
      b.right=f;
      f.left=g;
      BinaryTreeNode k=new BinaryTreeNode('K');
      c.right=k;
      BinaryTreeNode h=new BinaryTreeNode('H');
      k.left=h;
      BinaryTreeNode m=new BinaryTreeNode('M');
      k.right=m;
        System.out.println("printing original inorder");
      printInorder(a);
      System.out.println("");
      System.out.println("");
      BinaryTreeNode[] preorder=new BinaryTreeNode[]{a,b,d,e,f,g,c,k,h,m};
      BinaryTreeNode[] inorder=new BinaryTreeNode[]{e,d,b,g,f,a,c,h,k,m};
      a=createTree(null,preorder,inorder,0);
      System.out.println("PRINTING NEW INORDER");
      printInorder(a);
    }
    public static void printInorder(BinaryTreeNode root)
    {
        if(root!=null)
        {
            printInorder(root.left);
            System.out.print(" ,"+root.data);
            printInorder(root.right);
        }
    }
    public static BinaryTreeNode createTree(BinaryTreeNode root,BinaryTreeNode[] preorder,BinaryTreeNode[] inorder,int preOrderIndex)
    {
        if(inorder.length==0 || preOrderIndex>=preorder.length)// when the current node has no children return child as null
        {
            System.out.println("returning null node");
            return null;
        }
        root=preorder[preOrderIndex];
        System.out.print("root= "+root.data+" and preorder= ");
        for (int p = 0; p < preorder.length; p++) {
            System.out.print(" ,"+preorder[p].data);   
        }
        System.out.print("  inorder= ");
        for (int p = 0; p < inorder.length; p++) {
            System.out.print(" ,"+inorder[p].data);   
        }
        BinaryTreeNode[] leftSide,rightSide;
        int i=0;
        while(i<inorder.length && inorder[i]!=preorder[preOrderIndex])
            i++;
        leftSide=new BinaryTreeNode[i];
        for (int j = 0; j < i; j++) {
            leftSide[j]=inorder[j];
        }
        System.out.println("");
        System.out.println("leftside= ");
        for (int p = 0; p < leftSide.length; p++) {
            System.out.print(" ,"+leftSide[p].data);   
        }
        rightSide=new BinaryTreeNode[inorder.length-i-1];
        System.out.println("");
        System.out.print("rightside= ");
        for (int j = i+1; j < inorder.length; j++) {
            rightSide[j-i-1]=inorder[j];
        }
        for (int p = 0; p < rightSide.length; p++) {
            System.out.print(" ,"+rightSide[p].data);   
        }
        System.out.println("");
        root.left=createTree(root,preorder,leftSide, preOrderIndex+1);
        int preOrderIndForRightSide=0;
        while(i<inorder.length-1 && preOrderIndForRightSide<preorder.length && preorder[preOrderIndForRightSide]!=inorder[i+1])
            preOrderIndForRightSide++;
        root.right=createTree(root, preorder, rightSide, preOrderIndForRightSide);
        return root;
    }
}
class BinaryTreeNode 
{
    char data;
    BinaryTreeNode left,right;

    public BinaryTreeNode(char data) {
        this.data = data;
        this.left = this.right = null;
    }
    
}