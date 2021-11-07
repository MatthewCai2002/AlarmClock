package model;

/*

Represents an alarm clock with time in hours, minutes, seconds,
alarm hours and minutes a puzzle to solve, name, and ringing state.

*/

import org.json.JSONObject;
import persistence.Writable;
import puzzles.MathPuzzle;

import javax.jws.WebResult;

public class Alarm implements Writable {
    private int alarmTimeHours;         // the hours the alarm is set for
    private int alarmTimeMinutes;       // the minutes the alarm is set for
    private Boolean ringing;            // the current ringing state of the alarm
    private MathPuzzle puzzle;          // the puzzle required to solve to turn off alarm
    private String name;                // alarm name

    // REQUIRES: 0 <= hours <= 24 && 0 <= minutes <= 60
    // EFFECTS: constructs an alarm clock with given alarm time and name
    public Alarm(String name, int hours, int minutes) {
        ringing = false;
        alarmTimeHours = hours;
        alarmTimeMinutes = minutes;
        puzzle = new MathPuzzle();
        this.name = name;
    }

    // REQUIRES: 0 <= hours <= 24
    // MODIFIES: this
    // EFFECTS: sets the current time to given hours
    public void setAlarmTimeHours(int hours) {
        alarmTimeHours = hours;
    }

    // REQUIRES: 0 <= minutes <= 60
    // MODIFIES: this
    // EFFECTS: sets the current time to given minutes
    public void setAlarmTimeMinutes(int minutes) {
        alarmTimeMinutes = minutes;
    }

    // MODIFIES: this
    // EFFECTS: sets the name of alarm clock to given name
    public void setName(String name) {
        this.name = name;
    }

    // EFFECTS: returns the set alarm time in the form Hours:Minutes:0
    public String getAlarmTime() {
        return alarmTimeHours + ":" + alarmTimeMinutes + ":0";
    }

    // EFFECTS: returns the puzzle needed to turn off the alarm
    public MathPuzzle getPuzzle() {
        return puzzle;
    }

    // EFFECTS: returns the ringing state of the alarm
    public Boolean getRinging() {
        return ringing;
    }

    // EFFECTS: returns name of the alarm clock
    public String getName() {
        return name;
    }

    // from JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("AlarmTimeHours", alarmTimeHours);
        json.put("AlarmTimeMinutes", alarmTimeMinutes);
        return json;
    }
}
