package model;

import exceptions.InvalidTimeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import puzzles.MathPuzzle;

import static org.junit.jupiter.api.Assertions.*;

class AlarmTest {
    Alarm testAlarm;

    @BeforeEach
    public void setup() {
        testAlarm = new Alarm("alarm1",18, 0);
    }

    @Test
    public void testConstructorNoException() {
        try {
            String alarmTime = testAlarm.getAlarmTime();
            assertTrue(alarmTime.equals("18:0:0"));
        } catch (InvalidTimeException e) {
            fail();
        }
    }

    @Test
    public void testConstructorInvalidTime() {
        try {
            testAlarm = new Alarm("name", 12321, 123);
            fail();
        } catch (InvalidTimeException e) {
            // expected
        }
    }

    @Test
    public void testSetAlarmTimeHours() {
        try {
            testAlarm.setAlarmTimeHours(0);
            String time = testAlarm.getAlarmTime();
            assertEquals("00:00:0", time);

            testAlarm.setAlarmTimeHours(12);
            time = testAlarm.getAlarmTime();
            assertEquals("12:0:0", time);

            testAlarm.setAlarmTimeHours(24);
            time = testAlarm.getAlarmTime();
            assertEquals("24:0:0", time);
        } catch (InvalidTimeException e) {
            fail();
        }
    }

    @Test
    public void testSetAlarmTimeBigHours() {
        try {
            testAlarm.setAlarmTimeHours(25);
            fail();
        } catch (InvalidTimeException e) {
            // expected
        }
    }

    @Test
    public void testSetAlarmTimeNegativeHours() {
        try {
            testAlarm.setAlarmTimeHours(-1);
            fail();
        } catch (InvalidTimeException e) {
            // expected
        }
    }

    @Test
    public void testSetAlarmTimeMinutes() {
        try {
            testAlarm.setAlarmTimeMinutes(0);
            String time = testAlarm.getAlarmTime();
            assertEquals("18:0:0", time);

            testAlarm.setAlarmTimeMinutes(30);
            time = testAlarm.getAlarmTime();
            assertEquals("18:30:0", time);

            testAlarm.setAlarmTimeMinutes(60);
            time = testAlarm.getAlarmTime();
            assertEquals("18:60:0", time);

        } catch (InvalidTimeException e) {
            fail();
        }
    }

    @Test
    public void testSetAlarmTimeBigMinutes() {
        try {
            testAlarm.setAlarmTimeMinutes(600);
            fail();
        } catch (InvalidTimeException e) {
            // expected
        }
    }

    @Test
    public void testSetAlarmTimeNegativeMinutes() {
        try {
            testAlarm.setAlarmTimeMinutes(-1);
            fail();
        } catch (InvalidTimeException e) {
            // expected
        }
    }



    @Test
    public void testGetName() {
        String name = testAlarm.getName();
        assertTrue(name.equals("alarm1"));
    }

    @Test
    public void testSetName() {
        testAlarm.setName("cheese");
        String name = testAlarm.getName();
        assertTrue(name.equals("cheese"));
    }

}