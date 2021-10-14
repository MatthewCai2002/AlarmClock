package model;

/*

Represents an individual digital 24 hour clock that ticks every
second and keeps track of the current time with the ability to set an alarm

*/

import puzzles.MathPuzzle;

import java.util.Timer;
import java.util.TimerTask;


public class AlarmClock {
    private static final int SECONDS_PER_MINUTE = 60;
    private static final int MINUTES_PER_HOUR = 60;
    private static final int HOURS_PER_DAY = 24;

    private int clockTimeSeconds;
    private int clockTimeMinutes;
    private int clockTimeHours;
    private Boolean active;
    private Boolean ringing;
    private int alarmTimeHours;
    private int alarmTimeMinutes;
    private MathPuzzle puzzle;
    private String name;
//    private Timer timer;

    // REQUIRES: 0 < time < 24
    // EFFECTS: constructs an alarm clock with given alarm time
    public AlarmClock(String name,int hours, int minutes) {
        clockTimeHours = 0;
        clockTimeMinutes = 0;
        clockTimeSeconds = 0;
        active = true;
        ringing = false;
        alarmTimeHours = hours;
        alarmTimeMinutes = minutes;
        puzzle = new MathPuzzle();
        this.name = name;
//        timer = new Timer();
//        timer.schedule(new TimerTask() {
//            int runFor = secondsPassed;
//            @Override
//            public void run() {
//                if (runFor > 0) {
//                    clockTimeSeconds++;
//                    runFor--;
//                    if (clockTimeSeconds == SECONDS_PER_MINUTE) {
//                        clockTimeMinutes++;
//                        clockTimeSeconds = 0;
//                        if (clockTimeMinutes == MINUTES_PER_HOUR) {
//                            clockTimeHours++;
//                            clockTimeMinutes = 0;
//                            if (clockTimeHours == HOURS_PER_DAY) {
//                                clockTimeHours = 0;
//                                clockTimeMinutes = 0;
//                                clockTimeSeconds = 0;
//                            }
//                        }
//                    }
//                } else {
//                    timer.cancel();
//
//                }
//            }
//        }, 500, 500);

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
    // EFFECTS: flips the active state of an alarm
    public void toggleAlarmClock() {
        active = !active;
    }

    // MODIFIES: this
    // EFFECTS:  toggles the alarm on when current time has reached
    //           the alarm time
    //           toggles alarm off if problem was solved
    public void toggleRinging() {
        if ((clockTimeHours == alarmTimeHours) && (clockTimeMinutes == alarmTimeMinutes)) {
            ringing = true;
        } else {
            ringing = !ringing;
        }
    }

    // REQUIRES: 0 <= hours <= 24 and 0 <= minutes <= 60
    // MODIFIES: this
    // EFFECTS: sets the current time to given hours and minutes and seconds to 0
    public void setClockTime(int hours, int minutes) {
        clockTimeHours = hours;
        clockTimeMinutes = minutes;
        clockTimeSeconds = 0;
    }

    // MODIFIES: this
    // EFFECTS: sets the solve puzzle to what's given
    public void setPuzzle(MathPuzzle puzzle) {
        this.puzzle = puzzle;
    }

    // EFFECTS: returns the current time in the form Hours:Minutes:Seconds
    public String getTime() {
        return clockTimeHours + ":" + clockTimeMinutes + ":" + clockTimeSeconds;
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

    // EFFECTS: returns whether the alarm is active or not
    public Boolean getActive() {
        return active;
    }

    // EFFECTS: returns name of the alarm clock
    public String getName() {
        return name;
    }

}
