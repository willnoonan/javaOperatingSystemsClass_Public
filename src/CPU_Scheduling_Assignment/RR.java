package CPU_Scheduling_Assignment;

/*
    Author: William Noonan
            CSCI 5362 Operating Systems
            CPU Scheduling Assignment

 */

import java.util.List;

public class RR implements Algorithm
{
    List<Task> queue;
    NodeList taskNodeList = new NodeList();
    ListNode current;
    private int num_complete = 0;
    final int quantum = 10;  // time quantum (milliseconds)


    public RR(List<Task> queue)
    {
        this.queue = queue;

        // create the linked list
        for (Task task : this.queue )
        {
            taskNodeList.add(task);
        }

        // set current node
        current = taskNodeList.getFirstNode();
    }


    @Override
    public void schedule() {
        Task task;
        while ( (task = pickNextTask()) != null )
        {
            // get the task's remaining burst time
            int curTaskBurst = task.getBurst();

            // run the task using the smaller value between it's current burst time or the quantum time
            CPU.run(task, Math.min(curTaskBurst, quantum));

            // Update task burst-time. It's modified to implement the round-robin scheduling.
            if (curTaskBurst > quantum )
            {
                curTaskBurst -= quantum;
            } else {
                curTaskBurst = 0;
            }

            // if the task's burst time hits zero, the task is complete and can be ignored
            if (curTaskBurst <= 0) {
                num_complete++;
            }

            // update the burst time
            task.setBurst(curTaskBurst);
        }

        // Printout final clock time and completion message
        CPU.finish();
    }

    @Override
    public Task pickNextTask() {

        Task nextTask = null;

        // There is a next task as long as num_complete is less than the number of tasks
        if (num_complete < this.queue.size())
        {
            // ignore the completed tasks (with zero or less burst time)
            while(current.getTask().getBurst() <= 0) {
                current = current.nextNode;
            }

            nextTask = current.task;
            current = current.nextNode;  // update current node
        }

        return nextTask;
    }
}
