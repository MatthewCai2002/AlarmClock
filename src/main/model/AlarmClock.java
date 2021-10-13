package model;

/*

Represents an individual digital 24 hour clock that ticks every
second and keeps track of the current time with the ability to set an alarm

*/

import puzzles.MathPuzzle;

public class AlarmClock {
    private static final int SECONDS_PER_MINUTE = 60;
    private static final int MINUTES_PER_HOUR = 60;
    private static final int HOURS_PER_DAY = 24;

    private int currentTimeSeconds;
    private int currentTimeMinutes;
    private int currentTimeHours;
    private Boolean active;
    private Boolean ringing;
    private int alarmTimeHours;
    private int alarmTimeMinutes;
    private MathPuzzle puzzle;
    private String name;

    // REQUIRES: 0 < time < 24
    // EFFECTS: constructs an alarm clock with given alarm time
    public AlarmClock(int hours, int minutes) {
        currentTimeHours = 0;
        currentTimeMinutes = 0;
        currentTimeSeconds = 0;
        active = true;
        ringing = false;
        alarmTimeHours = hours;
        alarmTimeMinutes = minutes;
        puzzle = new MathPuzzle();
        name = "";
    }

    // REQUIRES: 0 <= stopMinutes <= 60 and 0 <= stopHours <= 24
    // MODIFIES: this
    // EFFECTS: progresses the current time by 1 second
    //          if time seconds = 60 seconds then add 1 to minutes
    //          if time minutes = 60 then add 1 to hours
    //          if time hours = 24 then reset time back to 0
    //          stops at given hours and minutes
    protected void tick(int stopHours, int stopMinutes, int stopSeconds) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: flips the active state of an alarm
    //          prints the active state of the alarm
    public void toggleAlarmClock() {
        active = !active;
    }

    // MODIFIES: this
    // EFFECTS:  toggles the alarm on or off
    public void toggleRinging() {
        ringing = !ringing;
    }

    // REQUIRES: 0 <= hours <= 24 and 0 <= minutes <= 60
    // MODIFIES: this
    // EFFECTS: sets the current time to given hours and minutes and seconds to 0
    protected void setClockTime(int hours, int minutes) {
        currentTimeHours = hours;
        currentTimeMinutes = minutes;
        currentTimeSeconds = 0;
    }

    // MODIFIES: this
    // EFFECTS: sets the solve puzzle to what's given
    public void setPuzzle(MathPuzzle puzzle) {
        this.puzzle = puzzle;
    }

    // EFFECTS: returns the current time in the form Hours:Minutes:Seconds
    protected String getTime() {
        return currentTimeHours + ":" + currentTimeMinutes + ":" + currentTimeSeconds;
    }

    // EFFECTS: returns the hour the alarm was set for
    public int getAlarmTimeHours() {
        return alarmTimeHours;
    }

    // EFFECTS: returns the minute the alarm was set for
    public int getAlarmTimeMinutes() {
        return alarmTimeMinutes;
    }

    // EFFECTS: returns the puzzle needed to turn off the alarm
    public MathPuzzle getPuzzle() {
        return puzzle;
    }

    // EFFECTS: returns the ringing state of the alarm
    public Boolean getRinging() {
        return ringing;
    }

    // EFFECTS: returns the whether  the alarm is active
    public Boolean getActive() {
        return active;
    }



}
