/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(m>=n || head==null || head.next==null)
            return head;
        ListNode temp=head;
        int len=0;
        while(temp!=null){
            temp=temp.next;
            len++;
        }
        temp=head;
        ListNode prev=null,curr=head;
        int i=1;
        while(i<=m){
            prev=curr;
            curr=curr.next;
            i++;
        }
//        traverse(head);
//        System.out.println("initial curr= "+curr.val+" and prev= "+prev.val);
//        if(prev!=null)
//           System.out.println(" and prev= "+prev.val);
//        else
//            System.out.println(" and prev is null");
        prev.next=null;
        while(i<=n){
            temp=curr.next;
            curr.next=prev;
            prev=curr;
//            if(curr.next!=null)
//                System.out.println(" curr next= "+curr.next.val+" and curr = "+curr.val);
//            else
//                System.out.println(" curr next= null and curr = "+curr.val);
//            traverse(curr);
            if(m==1)
                head=curr;
            curr=temp;
            i++;
        }
        temp=head;
//        System.out.println("new traverse");
//        traverse(temp);
        i=1;
        while(i++<m-1){
            temp=temp.next;
//            System.out.println("temp= "+temp.val);
       }
//           System.out.println("prev= "+prev.val+" and curr= "+curr.val);
//        if(n!=len)
//            temp.next=curr;
//        else
        if(m!=1)
        {
            temp.next=prev;
            while (temp.next != null) {
                temp = temp.next;
                i += 1;
            }            
            if(i!=n){
                temp.next=curr;
            }   
        }
        else{
//            System.out.println("curr= "+curr.val);
            while(temp.next!=null)
                temp=temp.next;
            temp.next=curr;
        } 
//        traverse(temp);
        return head;
    }
}
