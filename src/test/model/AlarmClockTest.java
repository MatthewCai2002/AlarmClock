package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import puzzles.MathPuzzle;

import static org.junit.jupiter.api.Assertions.*;

class AlarmClockTest {
    AlarmClock testAlarmClock;

    @BeforeEach
    public void setup() {
        testAlarmClock = new AlarmClock("alarm1",18, 0);
    }

    @Test
    public void testConstructor() {
        String alarmTime = testAlarmClock.getAlarmTime();
        assertTrue(alarmTime.equals("18:0:0"));
    }

    @Test
    public void testTick() {
        // seconds
        testAlarmClock.tick(0);
        String currentTime = testAlarmClock.getClockTime();
        assertEquals("0:0:0", currentTime);

        testAlarmClock.tick(30);
        currentTime = testAlarmClock.getClockTime();
        assertEquals("0:0:30", currentTime);

        // minutes
        testAlarmClock.tick(30);
        currentTime = testAlarmClock.getClockTime();
        assertEquals("0:1:0", currentTime);

        testAlarmClock.tick(3480);
        currentTime = testAlarmClock.getClockTime();
        assertEquals("0:59:0", currentTime);

        // hours
        testAlarmClock.tick(60);
        currentTime = testAlarmClock.getClockTime();
        assertEquals("1:0:0", currentTime);

        testAlarmClock.tick(7200);
        currentTime = testAlarmClock.getClockTime();
        assertEquals("3:0:0", currentTime);

        // reset to 0
        testAlarmClock.tick(75600);
        currentTime = testAlarmClock.getClockTime();
        assertEquals("0:0:0", currentTime);

    }

    @Test
    public void testSetAlarmTimeHours() {
        testAlarmClock.setAlarmTimeHours(0);
        String time = testAlarmClock.getAlarmTime();
        assertEquals("0:0:0", time);

        testAlarmClock.setAlarmTimeHours(12);
        time = testAlarmClock.getAlarmTime();
        assertEquals("12:0:0", time);

        testAlarmClock.setAlarmTimeHours(24);
        time = testAlarmClock.getAlarmTime();
        assertEquals("24:0:0", time);
    }

    @Test
    public void testSetAlarmTimeMinutes() {
        testAlarmClock.setAlarmTimeMinutes(0);
        String time = testAlarmClock.getAlarmTime();
        assertEquals("18:0:0", time);

        testAlarmClock.setAlarmTimeMinutes(30);
        time = testAlarmClock.getAlarmTime();
        assertEquals("18:30:0", time);

        testAlarmClock.setAlarmTimeMinutes(60);
        time = testAlarmClock.getAlarmTime();
        assertEquals("18:60:0", time);
    }

    @Test
    public void testToggleRinging() {

        // time 0:0
        testAlarmClock.toggleRinging();
        Boolean toggled = testAlarmClock.getRinging();
        assertFalse(toggled);

        // time 0:10
        testAlarmClock.tick(600);
        testAlarmClock.toggleRinging();
        toggled = testAlarmClock.getRinging();
        assertFalse(toggled);

        // time 18:0
        testAlarmClock.tick(64200);
        testAlarmClock.toggleRinging();
        toggled = testAlarmClock.getRinging();
        assertTrue(toggled);

        // time 18:10
        testAlarmClock.tick(600);
        testAlarmClock.toggleRinging();
        toggled = testAlarmClock.getRinging();
        assertFalse(toggled);
    }

    @Test
    public void testGetName() {
        String name = testAlarmClock.getName();
        assertTrue(name.equals("alarm1"));
    }

    @Test
    public void testGetPuzzle() {
        MathPuzzle puzzle = testAlarmClock.getPuzzle();
        String problem = puzzle.getProblem();
        assertEquals("1 + 1", problem);

    }

    @Test
    public void testSetName() {
        testAlarmClock.setName("cheese");
        String name = testAlarmClock.getName();
        assertTrue(name.equals("cheese"));
    }

//    @Test
//    public void testGetAlarmClockHoursMinutes() {
//        testAlarmClock.tick(1200);
//        String time = testAlarmClock.getClockTimeHoursMinutes();
//
//        assertTrue(time.equals("0:20"));
//    }
}