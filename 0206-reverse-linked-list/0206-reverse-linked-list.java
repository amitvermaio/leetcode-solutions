class Solution {
    public ListNode reverseList(ListNode head) {
        if (head==null || head.next==null) {
            return head;
        }        
        ListNode curr = head;
        ListNode newHead = reverseList(head.next);
        ListNode temp = newHead;
        while (temp!=null && temp.next!=null) {
            temp = temp.next;
        }
        temp.next = curr;
        curr.next = null;
        return newHead;
    }
}