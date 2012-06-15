package org.biosharing.cli.tasks;

import org.biosharing.dao.BioSharingDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by the ISA team
 *
 * @author Eamonn Maguire (eamonnmag@gmail.com)
 *         <p/>
 *         Date: 14/06/2012
 *         Time: 14:26
 */
public abstract class Task implements TaskSubject {

    private List<TaskObserver> observers;
    BioSharingDAO dao;

    private String taskName;

    protected Task(String taskName) {
        this.taskName = taskName;
        observers = new ArrayList<TaskObserver>();
        dao = new BioSharingDAO();
    }

    public void performTask() {
        System.out.println("Launching " + taskName);
        doTask();
        System.out.println(taskName + " complete!");
        notifyObservers();
    }

    public abstract void doTask();

    public void notifyObservers() {
        for (TaskObserver observer : observers) {
            observer.launchNotifification();
        }
    }

    public void removeObserver(TaskObserver taskObserver) {
        if (observers.contains(taskObserver)) {
            observers.remove(taskObserver);
        }
    }

    public void addObserver(TaskObserver taskObserver) {
        if (!observers.contains(taskObserver)) {
            observers.add(taskObserver);
        }
    }
}
