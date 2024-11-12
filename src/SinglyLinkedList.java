import java.util.Scanner;

public class SinglyLinkedList
{
    Node head;

    public int getSize()
    {
        int count = 0;
        Node temp = head;
        while (temp != null)
        {
            temp = temp.next;
            count++;
        }
        return count;
    }

    public void insertAtBeginning(int data)
    {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    public void insertAtEnd(int data)
    {
        Node newNode = new Node(data);
        if (head == null)
        {
            head = newNode;
            return;
        }

        Node temp = head;
        while (temp.next != null)
        {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    public void insertAfterGivenPosition(int data)
    {
        int i = 1;

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the position where you want to insert the element: ");
        int position = sc.nextInt();

        if (position > getSize())
        {
            System.out.println("Invalid position");
        }
        else
        {
            Node temp = head;
            while (i < position)
            {
                temp = temp.next;
                i++;
            }

            Node newNode = new Node(data);
            newNode.next = temp.next;
            temp.next = newNode;
        }
    }

    public void insertAtGivenPosition(int data)
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the position where you want to insert the element: ");
        int position = sc.nextInt();

        if (position == 0)
        {
            insertAtBeginning(data);
            return;
        }

        Node temp = head;
        for (int i = 0; temp != null && i < position - 1; i++)
        {
            temp = temp.next;
        }

        if (temp == null)
        {
            System.out.println("Invalid position");
            return;
        }

        Node newNode = new Node(data);
        newNode.next = temp.next;
        temp.next = newNode;
    }

    public void deleteAtBeginning()
    {
        if (head == null)
        {
            System.out.println("List is empty");
            return;
        }
        else
        {
            Node temp = head;
            head = head.next;
            temp = null;
        }
    }

    public void deleteAtEnd()
    {
        Node temp = head;
        while (temp.next.next != null)
            {
                temp = temp.next;
            }

        if (temp == head)
        {
            head = null;
        }
        else
        {
            temp.next = null;
        }

        temp = null;
    }

    public void deleteAtGivenPosition()
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the position where you want to delete the element: ");
        int position = sc.nextInt();

        int i = 0;
        Node temp = head;

        while (i < position - 1)
        {
            temp = temp.next;
            i++;
        }

        temp.next = temp.next.next;
        temp = null;
    }
}
