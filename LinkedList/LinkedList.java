public class LinkedList
{
    private Node first;
    private Node last;
    private Node current; //use to traverse the list

    //constructor: constructor an empty List with s as the name
    public LinkedList()
    {
        first = last = current = null;
    }

    //insert an Object at the front of the List
    //If List is empty, firstNode and lastNodfe will refer to the same 
    //object. Otherwise, firstNode refers to new node
    public void insertAtFront(GameVerse gv)
    {
        if(isEmpty())
            first = last = new Node(gv);
        else
            first = new Node(gv, first);
    }

    //insert an Object at the end of the List
    //If List is empty, firstNode and lastNodfe will refer to the 
    //same object.
    //Otherwise, lastNode's next instance variable refers to new node
    public void insertAtBack(GameVerse gv)
    {
        if(isEmpty())
            first = last = new Node(gv);
        else
            last = last.next = new Node(gv);
    }

    //remove the first node from the List
    public GameVerse removeFromFront()throws EmptyListException
    {
        GameVerse removeItem = null;
        if(isEmpty())
            throw new EmptyListException();
        removeItem = first.data; //retrieve the data

        //reset the firstNode and lastNode references
        if(first.equals(last))
            first = last = null;
        else
            first= first.next;
        return removeItem;
    }

    //remove the last node from the list

    public GameVerse removeFromBack()throws EmptyListException
    {
        GameVerse removeItem = null;
        if(isEmpty())
            throw new EmptyListException();
        removeItem = last.data; //retrieve the data

        //reset the firstNode and lastNode references
        if(first.equals(last))
            first = last = null;
        else
        {
            current = first;

            while(current.next != last)
            {
                current = current.next;
            }

            last = current;
            current.next = null;
        }

        return removeItem;
    }

    public boolean isEmpty()
    {
        return first == null;
    }

    //return first object

    public GameVerse getFirst()
    {
        if(isEmpty())
        {
            return null;
        }
        else
        {
            current = first;
            return current.data;
        }
    }

    //return next object

    public GameVerse getNext()
    {
        if(current != last)
        {
            current = current.next;
            return current.data;
        }
        else
        {
            return null;
        }
    }
}