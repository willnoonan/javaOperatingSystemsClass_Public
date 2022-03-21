package CPU_Scheduling_Assignment;

/*
    Author: William Noonan
            CSCI 5362 Operating Systems
            CPU Scheduling Assignment

    Driver class, executes the appropriate CPU Scheduling algorithm.

 */


/*
 * Driver.java
 * 
 * Demonstrates different CPU scheduling algorithms.
 * 
 * Usage:
 *  
 *  java Driver <schedule> <algorithm>
 *
 * where 
 *  schedule is schedule of tasks
 *
 *  algorithm = [FCFS, PRI, RR]
 */
  
import java.util.*;
import java.io.*;

public class Driver
{
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("Usage: java Driver <algorithm> <schedule>");
            System.exit(0);
        }

        BufferedReader inFile = new BufferedReader(new FileReader(args[1]));

        String schedule;

        // create the queue of tasks
        List<Task> queue = new ArrayList<>();

        // read in the tasks and populate the ready queue        
        while ( (schedule = inFile.readLine()) != null) {
            String[] params = schedule.split(",\\s*");
            queue.add(new Task(params[0], Integer.parseInt(params[1]), Integer.parseInt(params[2])));
        }

        inFile.close();

        
        Algorithm scheduler = null;
        String choice = args[0].toUpperCase();

        switch(choice) {
            case "FCFS":  /* First-Come, First-Served Scheduling */
                scheduler = new FCFS(queue);
                break;
            case "PRI":  /* Priority Scheduling */
                scheduler = new Priority(queue);
                break;
            case "RR":  /* Round-Robin Scheduling */
                scheduler = new RR(queue);
                break;
            default:
                System.err.println("Invalid algorithm");
                System.exit(0);
        }

        // start the scheduler
        scheduler.schedule();
    }
}
