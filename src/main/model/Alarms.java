package model;

/*

Represents a list of alarms

*/

import exceptions.InvalidNameException;

import java.util.ArrayList;
import java.util.List;

public class Alarms {
    private List<AlarmClock> alarms;        // list of alarms

    public Alarms() {
        alarms = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: add given alarm to list of alarms
    public void addAlarm(AlarmClock alarm) {
        alarms.add(alarm);
    }

    // REQUIRES: alarm to be removed is in the list
    // MODIFIES: this
    // EFFECTS: removes the indexed alarm from the list of alarms
    public void removeAlarmIndex(int index) {
        alarms.remove(index);
    }

    // MODIFIES: this
    // EFFECTS: removes the alarm with a matching name from list of alarms
    //          if there are multiple alarms with the same name,
    //              it removes the first occurrence
    public void removeAlarmName(String name) {
        for (AlarmClock ac : alarms) {
            if (ac.getName().equals(name)) {
                alarms.remove(ac);
                break;
            }
        }
    }

    // REQUIRES: index >= 0
    // EFFECTS: returns alarm at given index
    public AlarmClock getAlarmIndex(int index) {
        return alarms.get(index);

    }

    // EFFECTS: returns alarms
    public List<AlarmClock> getAlarms() {
        return alarms;
    }


}
