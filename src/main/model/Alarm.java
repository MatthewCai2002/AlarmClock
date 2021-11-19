package model;

/*

Represents an alarm clock with time in hours, minutes, seconds,
alarm hours and minutes a puzzle to solve, name, and ringing state.

*/

import exceptions.InvalidTimeException;
import org.json.JSONObject;
import persistence.Writable;
import puzzles.MathPuzzle;

public class Alarm implements Writable {
    private int alarmTimeHours;         // the hours the alarm is set for
    private int alarmTimeMinutes;       // the minutes the alarm is set for
    private String name;                // alarm name

    // EFFECTS: if 0 <= hours <= 24 and 0 <= minutes <= 60
    //              then constructs an alarm clock with given alarm time and name
    //          otherwise throws InvalidTimeException
    public Alarm(String name, int hours, int minutes) throws InvalidTimeException {
        if ((0 <= hours && hours <= 24)) {
            alarmTimeHours = hours;
            if ((0 <= minutes && minutes <= 60)) {
                alarmTimeMinutes = minutes;
                this.name = name;
            } else {
                throw new InvalidTimeException();
            }
        } else {
            throw new InvalidTimeException();
        }
    }


    // MODIFIES: this
    // EFFECTS: if 0 <= hours <= 24
    //              then sets the current time to given hours
    //          otherwise throws InvalidTimeException
    public void setAlarmTimeHours(int hours) throws InvalidTimeException {
        if (0 <= hours && hours <= 24) {
            alarmTimeHours = hours;
        } else {
            throw new InvalidTimeException();
        }
    }

    // MODIFIES: this
    // EFFECTS: if 0 <= minutes <= 60
    //              then sets the current time to given minutes
    //          otherwise throws InvalidTimeException
    public void setAlarmTimeMinutes(int minutes) throws InvalidTimeException {
        if (0 <= minutes && minutes <= 60) {
            alarmTimeMinutes = minutes;
        } else {
            throw new InvalidTimeException();
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the name of alarm clock to given name
    public void setName(String name) {
        this.name = name;
    }

    // EFFECTS: returns the set alarm time in the form Hours:Minutes:0
    public String getAlarmTime() {
        if (alarmTimeHours < 10 && alarmTimeMinutes < 10) {
            return "0" + alarmTimeHours + ":0" + alarmTimeMinutes + ":00";
        } else if (alarmTimeHours < 10) {
            return "0" + alarmTimeHours + ":" + alarmTimeMinutes + ":00";
        } else if (alarmTimeMinutes < 10) {
            return alarmTimeHours + ":0" + alarmTimeMinutes + ":00";
        }
        return alarmTimeHours + ":" + alarmTimeMinutes + ":00";
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
