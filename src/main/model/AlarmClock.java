package model;

/*

Represents an alarm clock with time in hours, minutes, seconds,
alarm hours and minutes a puzzle to solve, name, and ringing state.

*/

import puzzles.MathPuzzle;

public class AlarmClock {
    private static final int SECONDS_PER_MINUTE = 60;   // seconds in a minute
    private static final int MINUTES_PER_HOUR = 60;     // minutes in a hour
    private static final int HOURS_PER_DAY = 24;        // hours in a day

    private int clockTimeSeconds;       // the current time of clock in seconds
    private int clockTimeMinutes;       // the current time of clock in minutes
    private int clockTimeHours;         // the current time of clock in hours
    private int alarmTimeHours;         // the hours the alarm is set for
    private int alarmTimeMinutes;       // the minutes the alarm is set for
    private Boolean ringing;            // the current ringing state of the alarm
    private MathPuzzle puzzle;          // the puzzle required to solve to turn off alarm
    private String name;                // alarm name

    // REQUIRES: 0 < time < 24
    // EFFECTS: constructs an alarm clock with given alarm time and name
    public AlarmClock(String name, int hours, int minutes) {
        clockTimeHours = 0;
        clockTimeMinutes = 0;
        clockTimeSeconds = 0;
        ringing = false;
        alarmTimeHours = hours;
        alarmTimeMinutes = minutes;
        puzzle = new MathPuzzle();
        this.name = name;
    }

    // REQUIRES: timePassageSeconds >= 0
    // MODIFIES: this
    // EFFECTS: progresses the current time by 1 second
    //          if time seconds = 60 seconds then add 1 to minutes
    //          if time minutes = 60 then add 1 to hours
    //          if time hours = 24 then reset time back to 0
    //          stops ticking at timePassageSeconds
    public void tick(int timePassageSeconds) {
        for (int i = timePassageSeconds; i > 0; i--) {
            clockTimeSeconds++;
            if (clockTimeSeconds == SECONDS_PER_MINUTE) {
                clockTimeMinutes++;
                clockTimeSeconds = 0;
                if (clockTimeMinutes == MINUTES_PER_HOUR) {
                    clockTimeHours++;
                    clockTimeMinutes = 0;
                    if (clockTimeHours == HOURS_PER_DAY) {
                        clockTimeHours = 0;
                        clockTimeMinutes = 0;
                        clockTimeSeconds = 0;
                    }
                }
            }
        }
    }

    // MODIFIES: this
    // EFFECTS:  toggles the alarm on when current time has reached
    //           the alarm time
    //           toggles alarm off if problem was solved
    public void toggleRinging() {
        if ((clockTimeHours == alarmTimeHours) && (clockTimeMinutes == alarmTimeMinutes)) {
            ringing = true;
        } else {
            ringing = false;
        }
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

    // EFFECTS: returns the current time in the form Hours:Minutes:Seconds
    public String getClockTime() {
        return clockTimeHours + ":" + clockTimeMinutes + ":" + clockTimeSeconds;
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

}
