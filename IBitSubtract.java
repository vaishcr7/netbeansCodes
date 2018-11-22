package ibitsubtract;

import java.util.List;

public class IBitSubtract {

    public static void main(String[] args) {
        ListNode a=new ListNode(1);
//        ListNode b=new ListNode(2);
//        ListNode c=new ListNode(3);
//        ListNode d=new ListNode(4);
//        ListNode e=new ListNode(5);
//        ListNode f=new ListNode(6);
//        ListNode g=new ListNode(7);
//        ListNode h=new ListNode(8);
//        a.next=b;
//        b.next=c;
//        c.next=d;
//        d.next=e;
//        e.next=f;
//        f.next=g;
//        g.next=h;
        ListNode k=subtr(a);
        while(k!=null)
        {
            System.out.print(k.val+" -> ");
            k=k.next;
        }
        System.out.println("");
    }
    public static ListNode getMid(ListNode a)
    {
        ListNode sp=a;
        ListNode tp=a;
        while(tp!=null && tp.next!=null)
        {
            sp=sp.next;
            tp=tp.next.next;
        }
        while(a.next!=sp)
            a=a.next;
        return a;
    }
    public static ListNode subtr(ListNode a)
    {
        if(a==null || a.next==null)
            return a;
        if(a.next.next==null)
        {
            a.val=a.next.val-a.val;
            return a;
        }
        if(a.next.next.next==null)
        {
            a.val=a.next.next.val-a.val;
            return a;
        }
        ListNode mid=getMid(a);
        int lenList=0;
        ListNode tp=a;
        while(tp!=null)
        {
            tp=tp.next;
            lenList++;
        }
        int len=lenList/2;
        tp=a;
        ListNode end=mid.next;
        ListNode tempMid=mid;
//        System.out.println("mid is "+mid.val);
        if(lenList%2!=0)
        {
            end=end.next;
            tempMid=tempMid.next;
        }
        tempMid.next=null;
        ListNode t=end.next;
        while(end.next!=null)
        {
            ListNode temp=t.next;
//            System.out.println("temp= "+temp.val);
            t.next=end;
//            System.out.println("t next= "+t.next.val);
            end.next=tempMid;
//            System.out.println("end next= "+end.next.val);
            tempMid=end;
//            System.out.println("new tempmid= "+tempMid.val);
            end=t;
//            System.out.println("end= "+end.val);
            if(temp==null)
                break;
            t=temp;
//            System.out.println("t= "+temp.val);
        }
//        System.out.println("t IS "+t.val);
        ListNode p=t;
        ListNode k=a;
        while(k!=null && p!=null)
        {
            if(lenList%2!=0 && k==mid.next)
                break;
            k.val=p.val-k.val;
//            System.out.println("k= "+k.val+" and p= "+p.val);
            k=k.next;
            p=p.next;
//            if(k==null)
//                System.out.println("k is null");
//            if(p==null)
//                System.out.println("p is null");
        }
        System.out.println(p==mid);        
        k=a;
        p=t;
        ListNode g=null;
        ListNode temp=p.next;
        while(temp!=null)
        {
//            System.out.println("temp= "+temp.val);
            ListNode z=temp.next;
            p.next=g;
            g=p;
            temp.next=p;
            p=temp;
            temp=z;
        }
//        System.out.println("p= "+p.val);
        return a;
    }
}
class ListNode {
      public int val;
      public ListNode next;
      ListNode(int x) { val = x; next = null; }
 }