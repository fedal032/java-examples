package educative.twoHeaps;

import java.util.*;

public class ScheduleTask {

    public static void main(String[] args) {
        List<List<Integer>> taskList = new ArrayList<>();
        //[[1, 7], [8, 13], [5, 6], [10, 14], [6, 7]]
        taskList.add(Arrays.asList(1, 7));
        taskList.add(Arrays.asList(8, 13));
        taskList.add(Arrays.asList(5, 6));
        taskList.add(Arrays.asList(10, 14));
        taskList.add(Arrays.asList(6, 7));
        System.out.println(tasks(taskList));
    }

    public static int tasks(List<List<Integer>> tasksList) {
        int counter = 0;
        //наверху всегда меньшее время завершения
        PriorityQueue<int[]> machinesAvailable = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        PriorityQueue<int[]> tasksQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        for (List<Integer> t : tasksList) {
            tasksQueue.offer(new int[]{t.get(0), t.get(1)});
        }

        while (!tasksQueue.isEmpty()) {
            int[] currentTask = tasksQueue.poll();
            int[] machineInUse;

            if (!machinesAvailable.isEmpty() && currentTask[0] >= machinesAvailable.peek()[0]) {
                // top element is deleted from "machinesAvailable"
                machineInUse = machinesAvailable.poll();
                // schedule task on the current machine
                machineInUse = new int[]{ currentTask[1], machineInUse[1] };
            } else {
                // if there's a conflicting task, increment the
                // counter for machines and store this task's
                // end time on heap "machinesAvailable"
                counter += 1;
                machineInUse = new int[]{ currentTask[1], counter };
            }
            machinesAvailable.offer(machineInUse);


        }

        return counter;
    }
}
