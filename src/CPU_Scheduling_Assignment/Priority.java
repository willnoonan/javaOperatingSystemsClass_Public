package CPU_Scheduling_Assignment;

/*
    Author: William Noonan
            CSCI 5362 Operating Systems
            CPU Scheduling Assignment

 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Priority implements Algorithm {
    Iterator<Task> tasks;

    public Priority(List<Task> queue)
    {
        List<Task> tasks = new ArrayList<>(queue);
        tasks.sort(new PriorityComparator());
        this.tasks = tasks.iterator();
    }


    @Override
    public void schedule() {
        Task task;
        while ( (task = pickNextTask()) != null )
        {
            CPU.run(task, task.getBurst());
        }

        CPU.finish();
    }

    @Override
    public Task pickNextTask() {
        if (this.tasks.hasNext())
            return this.tasks.next();
        return null;
    }
}
