package ui;

import javafx.scene.effect.Effect;

public class AlarmApp {

    // EFFECTS: prints the names of all alarms in the list
    public void printAllAlarms() {
        System.out.println(alarms);
    }

    // EFFECTS: prints the active state of the alarm
    public void printAlarmActiveState() {
        if (!active) {
            System.out.println("Alarm is no longer active");
        } else {
            System.out.println("Alarm is active");
        }
    }

    // EFFECTS: prints ringing every second until puzzle is solved
    public void ringAlarm() {
        // stub
    }

    // EFFECTS: adds an alarm to list of alarms
    public void addAnAlarm() {
        // stub
    }

    // EFFECTS: removes an alarm from list of alarms
    public void removeAnAlarm() {
        // stub
    }

    // EFFECTS: prints the puzzle to be solved
    public void showPuzzle() {
        // stub
    }

}
