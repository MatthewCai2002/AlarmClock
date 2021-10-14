package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlarmClockTest {
    AlarmClock testAlarmClock;

    @BeforeEach
    public void setup() {
        testAlarmClock = new AlarmClock("alarm1",18, 0);
    }

    @Test
    public void testConstructor() {
        int alarmTimeHours = testAlarmClock.getAlarmTimeHours();
        int alarmTimeMinutes = testAlarmClock.getAlarmTimeMinutes();

        assertEquals(0, alarmTimeMinutes);
        assertEquals(18, alarmTimeHours);
    }

    @Test
    public void testTickMinutes() {
        // seconds
        testAlarmClock.tick(0);
        String currentTime = testAlarmClock.getTime();
        assertEquals("0:0:0", currentTime);

        testAlarmClock.tick(30);
        currentTime = testAlarmClock.getTime();
        assertEquals("0:0:30", currentTime);

        // minutes
        testAlarmClock.tick(30);
        currentTime = testAlarmClock.getTime();
        assertEquals("0:1:0", currentTime);

        testAlarmClock.tick(3480);
        currentTime = testAlarmClock.getTime();
        assertEquals("0:59:0", currentTime);

        // hours
        testAlarmClock.tick(60);
        currentTime = testAlarmClock.getTime();
        assertEquals("1:0:0", currentTime);

        testAlarmClock.tick(7200);
        currentTime = testAlarmClock.getTime();
        assertEquals("3:0:0", currentTime);

        // reset
        testAlarmClock.tick(75600);
        currentTime = testAlarmClock.getTime();
        assertEquals("0:0:0", currentTime);

    }

    @Test
    public void testSetClockTimeHours() {
        testAlarmClock.setClockTime(0,0);
        String time = testAlarmClock.getTime();
        assertEquals("0:0:0", time);

        testAlarmClock.setClockTime(12,0);
        time = testAlarmClock.getTime();
        assertEquals("12:0:0", time);

        testAlarmClock.setClockTime(24,0);
        time = testAlarmClock.getTime();
        assertEquals("24:0:0", time);
    }

    @Test
    public void testToggleAlarm() {
        testAlarmClock.toggleAlarmClock();
        Boolean toggled = testAlarmClock.getActive();
        assertFalse(toggled);

        testAlarmClock.toggleAlarmClock();
        toggled = testAlarmClock.getActive();
        assertTrue(toggled);
    }

    // rewrite this test
    @Test
    public void testToggleRinging() {
        testAlarmClock.toggleRinging();
        Boolean toggled = testAlarmClock.getRinging();
        assertTrue(toggled);

        testAlarmClock.toggleRinging();
        toggled = testAlarmClock.getRinging();
        assertFalse(toggled);
    }

    @Test
    public void testGetName() {
        String name = testAlarmClock.getName();
        assertEquals("alarm1", name);
    }
}