package model;

/*

Represents a list of all alarms set

*/

import java.util.ArrayList;
import java.util.List;

public class Alarms {

    private List<AlarmClock> alarms;

    public Alarms() {
        alarms = new ArrayList<AlarmClock>();
    }


    // MODIFIES: this
    // EFFECTS: add given alarm to list of alarms
    public void addAlarm(AlarmClock alarm) {
        // stub
    }

    // REQUIRES: alarm to be removed is in the list
    // MODIFIES: this
    // EFFECTS: removes the indexed alarm from the list of alarms
    public void removeAlarm(int i) {
        // stub
    }



    // REQUIRES: index >= 0
    // EFFECTS: returns alarm at given index
    public AlarmClock getAlarm(int index) {
        return new AlarmClock(0,0); // stub
    }

    // EFFECTS: prints all alarms in the list
    public void printAlarms() {
        // stub
    }


}
