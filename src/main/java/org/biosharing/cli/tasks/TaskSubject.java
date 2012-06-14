package org.biosharing.cli.tasks;

/**
 * Created by the ISA team
 *
 * @author Eamonn Maguire (eamonnmag@gmail.com)
 *         <p/>
 *         Date: 14/06/2012
 *         Time: 14:31
 */
public interface TaskSubject {

    public void notifyObservers();
    public void addObserver(TaskObserver taskObserver);
    public void removeObserver(TaskObserver taskObserver);
}
