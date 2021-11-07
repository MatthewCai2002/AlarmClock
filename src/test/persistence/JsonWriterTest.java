package persistence;

import exceptions.CouldNotFindClockException;
import model.Alarm;
import model.Alarms;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {
    Alarms alarms;
    Alarm alarm;

    @BeforeEach
    public void setup() {
        alarm = new Alarm("", 0, 0);
        alarms = new Alarms();
    }
    @Test
    public void testWriteAlarmsToInvalidFile() {
        try {
            JsonWriter jsonWriter = new JsonWriter("./data/Alarms/\0nonExistentFile.json");
            jsonWriter.open();
            fail("FileNotFoundException expected");
        } catch (FileNotFoundException e) {
            // expected
        }
    }

    @Test
    public void testWriteAlarmsNoAlarms() {
        try {
            JsonWriter jsonWriter = new JsonWriter("./data/Alarms/writeEmptyAlarms.json");
            jsonWriter.open();
            jsonWriter.write(alarms);
            jsonWriter.close();

            JsonReader jsonReader = new JsonReader("./data/Alarms/writeEmptyAlarms.json");
            alarms = jsonReader.readAlarms();

            assertEquals(0,alarms.getAlarms().size());
        } catch (IOException e) {
            fail("Failed to write to file");
        }
    }

    @Test
    public void testWriteAlarmsManyAlarms() {
        try {
            Alarm ac1 = new Alarm("Cheese", 2,0);
            Alarm ac2 = new Alarm("Caviar", 1,32);
            Alarm ac3 = new Alarm("Ckjsdf", 6,55);
            alarms.addAlarm(ac1);
            alarms.addAlarm(ac2);
            alarms.addAlarm(ac3);

            JsonWriter jsonWriter = new JsonWriter("./data/Alarms/writeManyAlarms.json");
            jsonWriter.open();
            jsonWriter.write(alarms);
            jsonWriter.close();

            JsonReader jsonReader = new JsonReader("./data/Alarms/writeManyAlarms.json");
            alarms = jsonReader.readAlarms();
            ac3 = alarms.findAlarmClockByName("Ckjsdf");

            assertEquals(3, alarms.getAlarms().size());
            assertTrue(alarms.getAlarms().contains(ac3));
            assertTrue(ac3.getAlarmTime().equals("6:55:0"));
        } catch (IOException e) {
            fail("was not expecting an exception");
        } catch (CouldNotFindClockException e) {
            fail("was not expecting an exception");
        }
    }
}
