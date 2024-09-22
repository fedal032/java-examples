package educative.kElems;

import java.util.PriorityQueue;
import java.util.Queue;

public class MinHeap
{
    private final Queue<Integer> minHeap;
    private final int initialSize;

    public MinHeap(int k, int nums[])
    {
        minHeap = new PriorityQueue<>(k);
        initialSize = k;
        for (int num : nums)
            minHeap.add(num);
    }

    public Integer add(int val)
    {
        if (minHeap.size() < initialSize)
        {
            minHeap.offer(val);
        }
        else
        {
            if (val > minHeap.peek())
            {
                //minHeap.remove();
                //minHeap.add(val);
                minHeap.poll();
                minHeap.offer(val);
            }
        }
        return minHeap.peek();
    }

    public void view()
    {
        System.out.print("[ ");
        for (Integer i : minHeap)
        {
            System.out.print(" " + i + " ");
        }
        System.out.print(" ] ");
    }

    public static void main(String[] args)
    {
        //3,[4,5,8,2]],[3],[5],[10],[9],[4]
        //[[5,[1,2,3,4,5,-1,-5,50,80]],[-1],[-5],[50],[80],[100],[123],[38]]
        MinHeap minHeap = new MinHeap(5, new int[]{1, 2, 3, 4, 5, -1, -5, 50, 80});
        int[] testArray = new int[]{-1, -5, 50, 80, 100, 123, 38};
        for (int i : testArray)
        {
            System.out.println(minHeap.add(i));
            minHeap.view();
        }
        //[null, 3, 3, 4, 5, 50, 50, 50]
    }
}

//import java.util.*;
//
//class KthLargest {
//    public PriorityQueue<Integer> topKHeap;
//    public int k;
//
//	// Constructor to initialize heap and add values in it
//    public KthLargest(int k, int[] nums) {
//        this.k = k;
//        topKHeap = new PriorityQueue<>();
//
//        for (int element : nums) {
//            add(element);
//        }
//    }
//
//	// Adds element in the heap and return the Kth largest
//    public int add(int val) {
//        if (topKHeap.size() < k) {
//            topKHeap.offer(val);
//        } else if (val > topKHeap.peek()) {
//            topKHeap.poll();
//            topKHeap.offer(val);
//        }
//
//        return topKHeap.peek();
//    }
//
//    public static void main(String[] args) {
//        int[] nums = {3, 6, 9, 10};
//        int[] temp = {3, 6, 9, 10};
//        System.out.print("Initial stream: ");
//        printArray(nums);
//        System.out.println("\nk: " + 3);
//        KthLargest kLargest = new KthLargest(3, nums);
//        int[] val = {4, 7, 10, 8, 15};
//        for (int i = 0; i < val.length; i++) {
//            System.out.println("\tAdding a new number " + val[i] + " to the stream");
//            temp = Arrays.copyOf(temp, temp.length + 1);
//            temp[temp.length - 1] = val[i];
//            System.out.print("\tNumber stream: ");
//            printArray(temp);
//            System.out.println("\n\tKth largest element in the stream: " + kLargest.add(val[i]));
//            System.out.println(new String(new char[100]).replace('\0', '-'));
//        }
//    }
//
//	public static void printArray(int[] arr) {
//		System.out.print("[");
//        for (int i = 0; i < arr.length; i++) {
//            System.out.print(arr[i]);
//            if (i != arr.length - 1) {
//                System.out.print(", ");
//            }
//        }
//		System.out.print("]");
//    }
//}
