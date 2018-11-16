package ibitpndrlinklst;
public class IBitPndrLinkLst {
    public static void main(String[] args) {
        ListNode a=new ListNode(1);
        ListNode b=new ListNode(2);
        ListNode c=new ListNode(2);
        ListNode d=new ListNode(3);
        ListNode e=new ListNode(2);
        ListNode f=new ListNode(2);
        ListNode g=new ListNode(1);
        a.next=b;
        b.next=c;
        c.next=d;
        d.next=e;
        e.next=f;
        f.next=g;
//        ListNode t=revLinkLst(a);
//        while(t!=null)
//        {
//            System.out.print(t.val+" -> ");
//            t=t.next;
//        }
//        System.out.println("");
        ListNode mid=retMidOfLList(a);
        ListNode l1=revLinkLst(mid.next);
        mid.next=null;
        ListNode l2=a;
        System.out.println("printing list 2");
        ListNode t=l1;
        while(t!=null)
        {
            System.out.print(t.val+" -> ");
            t=t.next;
        }
        System.out.println("");
         System.out.println("printing list 1");
        ListNode t2=l2;
        while(t2!=null)
        {
            System.out.print(t2.val+" -> ");
            t2=t2.next;
        }
        System.out.println("");
        boolean isPalin=true;
        while(l2!=null && l1!=null)
        {
            if(l2.val!=l1.val)
            {
                isPalin=false;
                break;
            }
            l2=l2.next;
            l1=l1.next;
        }
        if(isPalin)
            System.out.println("list is palindrome");
        else
            System.out.println("list is not palindrome");
    }
 public static ListNode revLinkLst(ListNode a)
 {
     if(a.next==null)
         return a;
     ListNode b=a.next;
     a.next=null;
     while(b.next!=null)
     {
         ListNode t=b.next;
         b.next=a;
         a=b;
         b=t;
     }
     b.next=a;
     return b;
 }
 public static ListNode retMidOfLList(ListNode root)
 {
     if(root.next==null || root.next.next==null)
         return root;
     ListNode a=root;
     ListNode b=root;
     while(b.next!=null && b.next.next!=null)
     {
         a=a.next;
         b=b.next.next;
     }
     return a;
 }
}
class ListNode
{
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val=val;
        this.next=null;
    }
    
}