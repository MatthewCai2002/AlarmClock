package model;

import exceptions.CouldNotFindClockException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AlarmsTest {
    Alarms testAlarms;
    Alarm alarm1;
    Alarm alarm2;
    Alarm alarm3;

    @BeforeEach
    public void setup() {
        testAlarms = new Alarms();
        alarm1 = new Alarm("alarm1",1,0);
        alarm2 = new Alarm("alarm2",2,2);
        alarm3 = new Alarm("alarm3",3,3);
        testAlarms.addAlarm(alarm1);
        testAlarms.addAlarm(alarm2);
        testAlarms.addAlarm(alarm3);
    }

    @Test
    public void testAddAlarm() {
        assertTrue(testAlarms.getAlarms().contains(alarm1));
        assertEquals(3, testAlarms.getAlarms().size());
    }

    @Test
    public void testRemoveAlarmName() {
        testAlarms.removeAlarmName("alarm3");
        assertFalse(testAlarms.getAlarms().contains(alarm3));

        testAlarms.removeAlarmName("cheese");
        assertEquals(2, testAlarms.getAlarms().size());

        testAlarms.removeAlarmName("alarm1");
        testAlarms.removeAlarmName("alarm2");
        assertEquals(0, testAlarms.getAlarms().size());
    }

    @Test
    public void testGetAlarms() {
        assertEquals(3, testAlarms.getAlarms().size());

    }

    @Test
    public void testFindAlarmClockByNameInAlarms() {
        try {
            Alarm alarm2 = testAlarms.findAlarmClockByName("alarm2");
            assertTrue(alarm2.getAlarmTime().equals("02:02:00"));
        } catch (CouldNotFindClockException e) {
            fail("did not expect exception");
        }
    }

    @Test
    public void testFindAlarmClockByNameNotInAlarms() {
        testAlarms.removeAlarmName("alarm1");
        testAlarms.removeAlarmName("alarm2");
        testAlarms.removeAlarmName("alarm3");

        try {
            Alarm alarm4 = testAlarms.findAlarmClockByName("alarm4");
            fail("expected exception");
        } catch (CouldNotFindClockException e) {
            // expected
        }

    }

    @Test
    public void testFindAlarmClockByNameEmptyAlarms() {
        testAlarms.removeAlarmName("alarm1");
        testAlarms.removeAlarmName("alarm2");
        testAlarms.removeAlarmName("alarm3");

        try {
            Alarm alarm4 = testAlarms.findAlarmClockByName("alarm2");
            fail("expected exception");
        } catch (CouldNotFindClockException e) {
            // expected
        }
    }
}
