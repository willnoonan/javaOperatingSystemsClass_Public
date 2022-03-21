package CPU_Scheduling_Assignment;

/*
    Author: William Noonan
            CSCI 5362 Operating Systems
            CPU Scheduling Assignment

 */


class ListNode
{
    Task task;
    ListNode nextNode;


    ListNode(Task task )
    {
        this.task = task;
        nextNode = null;
    }

    Task getTask()
    {
        return task;
    }
}


public class NodeList
{
    private ListNode firstNode;
    private ListNode lastNode;
    private int length = 0;


    public NodeList( )
    {
        firstNode = lastNode = null; // initialize first and last node objects to null
    }

    public int getLength()
    {
        return length;
    }

    public ListNode getFirstNode()
    {
        return firstNode;
    }

    public void add( Task task )
    {
        if ( isEmpty() ) // firstNode and lastNode refer to same object
            firstNode = lastNode = new ListNode(task);
        else // lastNode's nextNode refers to new node
            lastNode = lastNode.nextNode = new ListNode( task ); // updates original lastNode's nextNode and lastNode. Read right to left
        // Equivalent to:
//         ListNode newNode = new ListNode( task );
//         lastNode.nextNode = newNode; // current lastNode's nextNode link becomes newNode
//         lastNode = newNode; // resets lastNode to newNode

        lastNode.nextNode = firstNode;  // makes it circular
        length++; // update list length
    }

    // determine whether list is empty
    public boolean isEmpty()
    {
        return firstNode == null; // return true if list is empty
    }
}


