package educative.twoPointers;

import java.util.LinkedList;
import java.util.List;

public class MoveNthElemFromLinkedList
{
    public static void main(String[] args)
    {
        List<ListNode> list = new LinkedList<>();
        ListNode head = removeNthElem(list.get(0), 3);
        printList(head);
    }

    private static void printList(ListNode head)
    {
        while (head.next != null)
            System.out.println(head.val + " ");
    }

    private static ListNode removeNthElem(ListNode head, int n)
    {
        if (n == 1 && head.next == null)
            return null;

        ListNode start = head;
        ListNode end = head;

        for (int i = 0; i < n; i++)
            end = end.next;

        // Removal of the head node.
        if (end == null)
        {
            return head.next;
        }

        while (end.next != null)
        {
            start = start.next;
            end = end.next;
        }

        //remove
        ListNode toDel;
        toDel = start.next;
        start.next = toDel.next;

        return head;
    }

    private static class ListNode
    {
        int val;
        ListNode next;

        ListNode()
        {
        }

        ListNode(int val)
        {
            this.val = val;
        }

        ListNode(int val, ListNode next)
        {
            this.val = val;
            this.next = next;
        }
    }
}
