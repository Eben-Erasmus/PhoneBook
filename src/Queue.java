public class Queue
{
    int size = 3;
    int[] queue = new int[size];
    int front = -1;
    int rear = -1;
    Node frontLL = null;
    Node rearLL = null;

    public void enqueue(int data)
    {
        if (rear == size -1 )
        {
            System.out.println("Queue is full");
        }
        else if (front == -1 && rear == -1)
        {
            front = 0;
            rear = 0;
            queue[rear] = data;
        }
        else
        {
            rear ++;
            queue[rear] = data;
        }
    }

    public void dequeue()
    {
        if (front == -1 && rear == -1)
        {
            System.out.println("Queue is empty");
        }
        else if (front == rear)
        {
            front = -1;
            rear = -1;
        }
        else
        {
            front ++;
        }
    }

    public void peek()
    {
        if (front == -1 && rear == -1)
        {
            System.out.println("Queue is empty");
        }
        else
        {
            System.out.println("Front element is: " + queue[front]);
        }
    }

    public void display()
    {
        if(front == -1 && rear == -1)
        {
            System.out.println("Queue is empty");
        }
        else
        {
            for (int i = front; i <= rear; i++)
            {
                System.out.println(queue[i]);
            }
        }
    }

    public void enqueueLinkedList(int num)
    {
        Node newNode = new Node(num);
        newNode.next = null;

        if (frontLL == null && rearLL == null)
        {
            frontLL = newNode;
            rearLL = newNode;
        }
        else
        {
            rearLL.next = newNode;
            rearLL = newNode;
        }
    }

    public void dequeueLinkedList()
    {
        if (frontLL == null && rearLL == null)
        {
            System.out.println("Queue is empty");
        }
        else if (frontLL == rearLL)
        {
            frontLL = null;
            rearLL = null;
        }
        else
        {
            frontLL = frontLL.next;
        }
    }

    public void peekLinkedList()
    {
        if (frontLL == null && rearLL == null)
        {
            System.out.println("Queue is empty");
        }
        else
        {
            System.out.println("Front element is: " + frontLL.data);
        }
    }

    public void displayLinkedList()
    {
        if (frontLL == rearLL)
        {
            System.out.print("Queue is empty");
        }
        else
        {
            Node temp = frontLL;
            while (temp != null)
            {
                System.out.println(temp.data);
                temp = temp.next;
            }
        }
    }
}
