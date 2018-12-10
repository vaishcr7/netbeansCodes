package mergeksrtdlsts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MergeKsrtdLsts {

    public static void main(String[] args) {
        ArrayList<ListNode> al=new ArrayList<>();
        ArrayList<ListNode> a=new ArrayList<>();
        ListNode w=new ListNode(1);
        ListNode x=new ListNode(4);
        ListNode y=new ListNode(7);
        ListNode z=new ListNode(10);
        ListNode g=new ListNode(12);
        w.next=x;
        x.next=y;
        y.next=z;
        z.next=g;
        a.add(w);
        a.add(x);
        a.add(y);
        a.add(z);
        a.add(g);
        ArrayList<ListNode> b=new ArrayList<>();
        ListNode w1=new ListNode(2);
        ListNode x1=new ListNode(5);
        ListNode y1=new ListNode(8);
        ListNode z1=new ListNode(11);
        w1.next=x1;
        x1.next=y1;
        y1.next=z1;
        b.add(w1);
        b.add(x1);
        b.add(y1);
        b.add(z1);
        ArrayList<ListNode> c=new ArrayList<>();
       ListNode  w2=new ListNode(3);
        ListNode x2=new ListNode(6);
        ListNode y2=new ListNode(9);
        w2.next=x2;
        x2.next=y2;
        c.add(w2);
        c.add(x2);
        c.add(y2);
        al.add(a.get(0));
        al.add(b.get(0));
        al.add(c.get(0));
        ListNode m=mgkslt(al);
        while(m!=null)
        {
            System.out.print(m.val+" -> ");
            m=m.next;
        }
    }
    public static ListNode mgkslt(ArrayList<ListNode> a)
    {
        ArrayList<ArrayList<ListNode>> al=new ArrayList<>();
        for (ListNode listNode : a) {
            ArrayList<ListNode> t=new ArrayList<>();
            while(listNode!=null)
            {
                t.add(listNode);
                listNode=listNode.next;
            }
            al.add(new ArrayList<>(t));
        }
        if(al.isEmpty())
            return null;
        if(al.size()==1)
            return al.get(0).get(0);
        ArrayList<ListNode> ans=new ArrayList<>();
        ListNode []heads=new ListNode[al.size()];
        int i=0;
        ListNode root=al.get(0).get(0);
        int numOfNodes=0;
        for (ArrayList<ListNode> arrayList : al) {
            if(!arrayList.isEmpty() )
            {   
                heads[i] = arrayList.get(0);
                if (root.val > heads[i].val) {
                    root = heads[i];
                }
                i++;
                numOfNodes+=arrayList.size();
            }
        }
        i=0;
        HashMap<ListNode,ListNode> mp=new HashMap<>();
        ListNode prevNode=null;
        while(i<numOfNodes)
        {
            ListNode g=null;
            int z=-1;
            for (int j=0;j<heads.length;j++) 
            {
                if(g==null && heads[j]!=null)
                {
                    g=heads[j];
                    z=j;
                }
                if(g!=null && heads[j]!=null && g.val>heads[j].val)
                {
                    g=heads[j];
                    z=j;
                }
//                if(prevNode!=null && prevNode.val==3)
//                {
//                    if(heads[j]==null)
//                        System.out.println("j= "+j);
//                }
            }
//            System.out.println("z= " + z);
//            System.out.println("g= " + g.val + " and heads[" + z + "]= " + heads[z].val + " i= " + i);
            if(mp.isEmpty())
                mp.put(null,g);
            else
                mp.put(prevNode,g);
//            dispMp(mp);
            heads[z]=heads[z].next;
            prevNode=g;
//            System.out.println("previous node= "+prevNode.val);
            i++;
        }
        i=0;
        prevNode=null;
        while(i<numOfNodes)
        {
            ans.add(mp.get(prevNode));
            System.out.print(mp.get(prevNode).val+" -> ");
            prevNode=mp.get(prevNode);
            i++;
        }
        System.out.println("");
        for (int j = 0; j < ans.size()-1; j++) {
            ans.get(j).next=ans.get(j+1);
        }
        ans.get(ans.size()-1).next=null;
        return ans.get(0);
    }    
    public static void dispMp(HashMap<ListNode,ListNode> mp)
    {
        for (Map.Entry<ListNode,ListNode> entry: mp.entrySet()) {
            if(entry.getKey()==null)
                System.out.println("null -> "+entry.getValue().val);
            else if(entry.getValue()==null)
                System.out.println("null value for "+entry.getKey().val);
            else
                System.out.println(entry.getKey().val+" -> "+entry.getValue().val);
        }
    }
}
class ListNode {
      int val;
      ListNode next;
      ListNode(int x)
      {
          val = x;
          next=null;
      }
  }