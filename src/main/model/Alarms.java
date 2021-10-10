package model;

import java.util.ArrayList;
import java.util.List;

public class Alarms {

    private List<Alarm> alarms;

    public Alarms() {
        alarms = new ArrayList<Alarm>();
    }


    // MODIFIES: this
    // EFFECTS: add given alarm to list of alarms
    public void addAlarm(Alarm alarm) {
        // stub
    }

    // REQUIRES: alarm to be removed is in the list
    // MODIFIES: this
    // EFFECTS: removes the indexed alarm from the list of alarms
    public void removeAlarm(int i) {
        // stub
    }



    // REQUIRES: i > 0 or i = 0
    // EFFECTS: returns alarm at given index
    public Alarm getAlarm(int i) {
        return new Alarm();
    }

    // EFFECTS: prints all alarms in the list
    public void printAlarms() {
        // stub
    }


}
