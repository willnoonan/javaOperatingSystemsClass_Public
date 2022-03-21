package CPU_Scheduling_Assignment;

/*
    Author: William Noonan
            CSCI 5362 Operating Systems
            CPU Scheduling Assignment

    Virtual CPU

 */
 
public class CPU
{
    /**
     * Run the specified task for the specified slice of time.
     */
    static int clock = 0;


    public static void run(Task task, int slice) {
        System.out.println("Clock: " + clock);
        System.out.println("Running...\n" + task);
        clock += slice;
    }

    public static void finish() {
        System.out.println("Clock: " + clock);
        System.out.println("All tasks complete");
    }

}
