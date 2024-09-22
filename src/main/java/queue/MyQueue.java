package queue;

import java.util.ArrayList;
import java.util.List;

public class MyQueue
{
    private static int INIT_CAPCITY = 16;

    private int head = 0;
    private int tail = 0;
    private List<Integer> innerQueue = new ArrayList<>();
    private int[] innerArray;

    public MyQueue() {
        innerArray = new int[INIT_CAPCITY];
    }

    public MyQueue (int capacity)
    {
        innerArray = new int[capacity];
    }

    public boolean add(Integer elem)
    {
        innerArray[tail] = elem;
        tail++;
        if (tail == innerArray.length - 1)
            doubleCapacity();

        addLast(elem);
        return true;
    }

    private void doubleCapacity()
    {
        //todo reallocateArray

    }

    public boolean offer(Integer elem)
    {
        addLast(elem);
        return true;
    }

    private void addLast(Integer elem)
    {
        innerQueue.add(elem);
        tail++;
    }


    public Integer remove()
    {
        if (isEmpty())
            throw new IllegalStateException("Queue is empty");

        return getFirst();
    }

    public Integer poll()
    {
        if (isEmpty())
            return null;

        return getFirst();
    }

    private Integer getFirst()
    {
        Integer elem = innerQueue.get(head);
        innerQueue.set(head, null);
        head++;
        return elem;
    }


    public Integer element()
    {
        return innerQueue.get(head);
    }


    public Integer peek()
    {
        return isEmpty() ? null : innerQueue.get(head);
    }

    public boolean isEmpty()
    {
        return head == tail;
    }
}
