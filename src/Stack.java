public class Stack
{
    int size = 5;
    int[] stack = new int[size];
    int top = -1;
    Node topLL = null;

    public void push(int data)
    {
        if (top == size - 1)
        {
            System.out.println("Stack is full");
        }
        else
        {
            top++;
            stack[top] = data;
        }
    }

    public void pop()
    {
        if (top == -1)
        {
            System.out.println("Stack is empty");
        }
        else
        {
            top--;
        }
    }

    public void peek()
    {
        if (top == -1)
        {
            System.out.println("Stack is empty");
        }
        else
        {
            System.out.println("Top element is: " + stack[top]);
        }
    }

    public void display()
    {
        for (int i = top; i >= 0; i--)
        {
            System.out.println(stack[i]);
        }
    }

    public void pushLinkedList(int num)
    {
        Node newNode = new Node(num);
        newNode.next = topLL;
        topLL = newNode;
    }

    public void popLinkedList()
    {
        Node temp = topLL;
        if (topLL == null)
        {
            System.out.println("Stack is empty");
        }
        else
        {
            topLL = topLL.next;
            temp = null;
        }
    }

    public void peekLinkedList()
    {
        if (topLL == null)
        {
            System.out.println("Stack is empty");
        }
        else
        {
            System.out.println("Top element is: " + topLL.data);
        }
    }

    public void displayLinkedList()
    {
        Node temp = topLL;
        if (topLL == null)
        {
            System.out.println("Stack is empty");
        }
        else
        {
            while (temp != null)
            {
                System.out.println(temp.data);
                temp = temp.next;
            }
        }
    }
}
