package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlarmClockTest {
    AlarmClock testAlarmClock;

    @BeforeEach
    public void setup() {
        testAlarmClock = new AlarmClock(18, 0);
    }

    @Test
    public void testConstructor() {
        int alarmTimeHours = testAlarmClock.getAlarmTimeHours();
        int alarmTimeMinutes = testAlarmClock.getAlarmTimeMinutes();

        assertEquals(0, alarmTimeMinutes);
        assertEquals(18, alarmTimeHours);
    }

    @Test
    public void testTickSeconds() {
        testAlarmClock.tick(18, 0, 1);
        String currentTime = testAlarmClock.getTime();

        assertEquals("18:0:1", currentTime);

        testAlarmClock.tick(18, 0, 60);
        currentTime = testAlarmClock.getTime();

        assertEquals("18:0:60", currentTime);

        testAlarmClock.tick(18, 0, 30);
        currentTime = testAlarmClock.getTime();

        assertEquals("18:0:30", currentTime);
    }

    @Test
    public void testTickMinutes() {
        testAlarmClock.tick(18,1, 0);
        String currentTime = testAlarmClock.getTime();
        assertEquals("18:1:0", currentTime);

        testAlarmClock.tick(18, 60, 0);
        currentTime = testAlarmClock.getTime();
        assertEquals("18:60:60", currentTime);

        testAlarmClock.tick(18, 30, 0);
        currentTime = testAlarmClock.getTime();
        assertEquals("18:30:30", currentTime);
    }

    @Test
    public void testTickHours() {
        testAlarmClock.tick(19, 0, 0);
        String currentTime = testAlarmClock.getTime();
        assertEquals("19:0:0", currentTime);

        testAlarmClock.tick(24, 0, 0);
        currentTime = testAlarmClock.getTime();
        assertEquals("24:0:0", currentTime);

        testAlarmClock.tick(0, 0, 0);
        currentTime = testAlarmClock.getTime();
        assertEquals("0:0:0", currentTime);
    }

    @Test
    public void testSetClockTimeMinutes() {
        testAlarmClock.setClockTime(0,0);
        String time = testAlarmClock.getTime();
        assertEquals("0:0:0", time);

        testAlarmClock.setClockTime(0,30);
        time = testAlarmClock.getTime();
        assertEquals("0:30:0", time);

        testAlarmClock.setClockTime(0,60);
        time = testAlarmClock.getTime();
        assertEquals("0:60:0", time);
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

    @Test
    public void testToggleRinging() {
        testAlarmClock.toggleRinging();
        Boolean toggled = testAlarmClock.getRinging();
        assertTrue(toggled);

        testAlarmClock.toggleRinging();
        toggled = testAlarmClock.getRinging();
        assertFalse(toggled);
    }

}