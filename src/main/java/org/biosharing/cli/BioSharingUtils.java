package org.biosharing.cli;

import org.biosharing.cli.tasks.*;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

/**
 * Created by the ISA team
 *
 * @author Eamonn Maguire (eamonnmag@gmail.com)
 *         <p/>
 *         Date: 14/06/2012
 *         Time: 14:14
 */
public class BioSharingUtils implements TaskObserver{

    public static final int ONTOLOGIES = 1;
    public static final int PUBLICATIONS = 2;
    public static final int DOMAINS = 3;
    public static final int EXIT = 4;

    public static void main(String[] args) {
        BioSharingUtils bioSharingUtils = new BioSharingUtils();
        bioSharingUtils.startClient();
    }

    private void startClient() {
        showCLIHeader();
        showMenu();
    }

    private void showCLIHeader() {
        out.println("BioSharing Utils 0.1");
        out.println("Created by the ISA team (http://www.isa-tools.org)");
        out.println("--------------------------------------------------");
    }

    private void showMenu() {
        out.println();
        out.println("Menu");
        out.println("1. Update ontologies from BioPortal");
        out.println("2. Retrieve publications for current standards");
        out.println("3. Ontologise standard domains");
        out.println("4. Exit");
        out.println();
        out.println("Enter option 1-4:");
        readInput();
    }

    /**
     * A Method that reads ask for an accession number and checks it is well formed.
     */
    public void readInput() {

        Scanner inputstr = new Scanner(in);

        try {
            int option = Integer.valueOf(inputstr.nextLine());
            Task task;
            switch (option) {
                case ONTOLOGIES:
                    task = new OntologyUpdateTask();
                    task.addObserver(this);
                    task.performTask();
                    break;
                case PUBLICATIONS:
                    task = new PublicationAssignmentTask();
                    task.addObserver(this);
                    task.performTask();
                    break;
                case DOMAINS:
                    task = new DomainAssignmentTask();
                    task.addObserver(this);
                    task.performTask();
                    break;
                case EXIT:
                    exit();
                    break;
                default:
                    System.out.println("Invalid option, please enter again");
                    readInput();
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid option, please enter a value between 1 and 4");
            readInput();
        }
    }

    private void exit() {
        System.out.println("Exiting BioSharingUtils");
        System.exit(0);
    }

    public void launchNotifification() {
        showMenu();
    }
}
