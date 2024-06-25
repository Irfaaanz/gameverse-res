public class Node
{
    //package access data so class List can access it directly
    GameVerse data;
    Node next;

    //Constructor: create a ListNode that refer to Object o
    Node(GameVerse gv)
    {
        this(gv, null);
    }

    //Constructor: create a ListNode that refers to Object o and 
    //to the next ListNode in the List
    Node(GameVerse gv, Node nextNode)
    {
        data = gv;
        next = nextNode;
    }

    //return a reference to the Object in this node
    Object getData()
    {
        return data;
    }

    //return the next node
    Node getNext()
    {
        return next;
    }
}