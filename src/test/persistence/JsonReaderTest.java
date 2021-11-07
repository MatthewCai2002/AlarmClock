package persistence;

import exceptions.CouldNotFindClockException;
import model.Alarm;
import model.Alarms;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

    @Test
    public void testReadAlarmsNoFile() {
        JsonReader jsonReader = new JsonReader("./data/Alarms/nonExistentFile.json");
        try {
            Alarms alarms = jsonReader.readAlarms();
            fail("Expected IOException");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    public void testReaderNoAlarms() {
        JsonReader jsonReader = new JsonReader("./data/Alarms/noAlarms.json");
        try {
            Alarms alarms = jsonReader.readAlarms();
            assertEquals(0,alarms.getAlarms().size());
        } catch (IOException e) {
            fail("couldn't read file");
        }
    }

    @Test
    public void testReaderManyAlarms() {
        JsonReader jsonReader = new JsonReader("./data/Alarms/manyAlarms.json");
        try {
            Alarms alarms = jsonReader.readAlarms();
            Alarm alarmBread = alarms.findAlarmClockByName("asdf");
            assertEquals(2,alarms.getAlarms().size());
            assertTrue(alarms.getAlarms().contains(alarmBread));
        } catch (IOException e) {
            fail("couldn't read from file");
        } catch (CouldNotFindClockException e) {
            fail("couldn't find clock");
        }
    }
}
