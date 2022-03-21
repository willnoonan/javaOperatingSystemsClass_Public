package CPU_Scheduling_Assignment;

import java.util.Comparator;

public class PriorityComparator implements Comparator<Task> {
    /*
        This class is used to sort the tasks by priority in descending order (high to low).
     */

    @Override
    public int compare(Task x, Task y) {
        int startComparison = compare(x.getPriority(), y.getPriority());
        return startComparison != 0 ? startComparison
                : compare(x.getPriority(), y.getPriority());
    }

    private static int compare(long a, long b) {
        return Long.compare(b, a);  // reversing the arg order sorts the tasks in descending order
    }
}
