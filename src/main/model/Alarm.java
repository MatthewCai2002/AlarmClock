package model;

import puzzles.MathPuzzle;
import puzzles.Puzzle;
import sound.RingTone;

public class Alarm extends Clock {

    private Boolean active;
    private Boolean ringing;
    private int alarmTime;
    private Puzzle puzzle;
    private RingTone ringer;

    public Alarm() {
        active = true;
        ringing = false;
        alarmTime = 0;
        puzzle = new MathPuzzle();
        ringer = new RingTone(); //TODO: not sure how this is going to pan out, change for later
    }

    // MODIFIES: this
    // EFFECTS: flips the active state of an alarm
    //          prints the active state of the alarm
    public void toggleAlarm() {

    }

    // MODIFIES: this
    // EFFECTS:  toggles the alarm on or off
    public void toggleRinging() {

    }

    // REQUIRES: 0 < time < 24
    // MODIFIES: this
    // EFFECTS: sets an alarm at given time
    //          prints the time the alarm was set and if it was successful
    public void setAlarmTime(int time) {

    }

    // MODIFIES: this
    // EFFECTS: sets the solve puzzle to what's given
    public void setPuzzle(Puzzle puzzle) {

    }

    // MODIFIES: this
    // EFFECTS: sets the ringer to what's given
    public void setRingTone(RingTone ringer) {

    }

    // EFFECTS: returns the time the alarm was set for
    public void getAlarmTime() {

    }

    // EFFECTS: returns the puzzle needed to turn off the alarm
    public void getPuzzle() {

    }

    // EFFECTS: returns the ringer of the alarm
    public void getRingTone() {

    }

    // EFFECTS: returns the ringing state of the alarm
    public void getRinging() {

    }

}
