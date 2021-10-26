package model;

/*

Represents a list of alarms

*/

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class Alarms implements Writable {
    private List<AlarmClock> alarms;        // list of alarms

    // EFFECTS: constrcucts a new ArrayList of AlarmCLocks
    public Alarms() {
        alarms = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: add given alarm to list of alarms
    public void addAlarm(AlarmClock alarm) {
        alarms.add(alarm);
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

    // REQUIRES: name is in alarms
    // EFFECTS: returns the alarm clock with given name
    public AlarmClock findAlarmClockByName(String name) {
        AlarmClock foundClock = new AlarmClock("",0,0);
        for (AlarmClock ac : alarms) {
            if (ac.getName().equals(name)) {
                foundClock = ac;
            }
        }
        return foundClock;
    }

    // EFFECTS: returns alarms
    public List<AlarmClock> getAlarms() {
        return alarms;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Alarms", toJsonArray());
        return jsonObject;
    }

    // from by JsonSerializationDemo
    // EFFECTS: returns alarms in the list of alarms as a JSON array
    public JSONArray toJsonArray() {
        JSONArray jsonArray = new JSONArray();
        for (AlarmClock ac : alarms) {
            jsonArray.put(ac.toJson());
        }
        return jsonArray;
    }

}
