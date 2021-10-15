package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AlarmsTest {
    Alarms testAlarms;
    AlarmClock alarm1;
    AlarmClock alarm2;
    AlarmClock alarm3;

    @BeforeEach
    public void setup() {
        testAlarms = new Alarms();
        alarm1 = new AlarmClock("alarm1",1,0);
        alarm2 = new AlarmClock("alarm2",2,2);
        alarm3 = new AlarmClock("alarm3",3,3);
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
    public void testRemoveAlarmIndex(){
        testAlarms.removeAlarmIndex(1);
        assertFalse(testAlarms.getAlarms().contains(alarm2));
    }

    @Test
    public void testRemoveAlarmName() {
        testAlarms.removeAlarmName("alarm3");
        assertFalse(testAlarms.getAlarms().contains(alarm3));

        testAlarms.removeAlarmName("cheese");
        assertEquals(2, testAlarms.getAlarms().size());
    }

    @Test
    public void testGetAlarmIndex() {
        AlarmClock grabbedAlarm = testAlarms.getAlarmIndex(2);
        assertEquals(alarm3, grabbedAlarm);
    }

}
