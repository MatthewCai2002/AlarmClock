package model;

/*

Represents a list of alarms

*/

import exceptions.CouldNotFindClockException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class Alarms implements Writable {
    private List<Alarm> alarms;        // list of alarms

    // EFFECTS: constructs a new ArrayList of AlarmCLocks
    public Alarms() {
        alarms = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: add given alarm to list of alarms
    public void addAlarm(Alarm alarm) {
        alarms.add(alarm);
        EventLog.getInstance().logEvent(
                new Event("Added Alarm: " + alarm.getName() + "-" + alarm.getAlarmTime() + " to Alarms"));
    }

    // EFFECTS: if name is in alarms returns the alarm clock with given name
    //          otherwise throws CouldNotFindClockException
    public Alarm findAlarmClockByName(String name) throws CouldNotFindClockException {
        Alarm foundClock;
        for (Alarm ac : alarms) {
            if (ac.getName().equals(name)) {
                foundClock = ac;
                return foundClock;
            }
        }
        throw new CouldNotFindClockException();
    }

    // EFFECTS: returns alarms
    public List<Alarm> getAlarms() {
        return alarms;
    }

    // MODIFIES: this
    // EFFECTS: removes alarm at given index
    public void removeAlarmIndex(int index) {
        alarms.remove(index);
        Alarm alarm = alarms.get(index);
        EventLog.getInstance().logEvent(new Event("Removed Alarm: "
                + alarm.getName() + "-" + alarm.getAlarmTime() + " from Alarms"));
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
        for (Alarm ac : alarms) {
            jsonArray.put(ac.toJson());
        }
        return jsonArray;
    }

}
