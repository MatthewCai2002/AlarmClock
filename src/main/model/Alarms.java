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
        alarms.add(alarm);
    }

    // REQUIRES: alarm to be removed is in the list
    // MODIFIES: this
    // EFFECTS: removes the indexed alarm from the list of alarms
    public void removeAlarmIndex(int index) {
        alarms.remove(index);
    }

    // REQUIRES: alarm to be removed is in the list
    // MODIFIES: this
    // EFFECTS: removes the alarm with a matching name from list of alarms
    //          if there are multiple alarms with the same name,
    //              it removes the first occurrence
    public void removeAlarmName(String name) {
        for (AlarmClock ac : alarms) {
            if (name == ac.getName()) {
                alarms.remove(ac);
                break;
            }
        }
    }

    // REQUIRES: index >= 0
    // EFFECTS: returns alarm at given index
    public AlarmClock getAlarm(int index) {
        AlarmClock alarm = alarms.get(index);
        return alarm;
    }

    // EFFECTS: returns alarms
    public List<AlarmClock> getAlarms() {
        return alarms;
    }


}
