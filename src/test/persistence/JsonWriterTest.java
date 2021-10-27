package persistence;

import exceptions.CouldNotFindClockException;
import model.AlarmClock;
import model.Alarms;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {

    @Test
    public void testWriteToInvalidFile() {
        try {
            Alarms alarms = new Alarms();
            JsonWriter jsonWriter = new JsonWriter("./data/Alarms/\0nonExistentFile.json");
            jsonWriter.open();
            fail("FileNotFoundException expected");
        } catch (FileNotFoundException e) {
            // expected
        }
    }

    @Test
    public void testWriteNoAlarms() {
        try {
            Alarms alarms = new Alarms();
            JsonWriter jsonWriter = new JsonWriter("./data/Alarms/writeEmptyAlarms.json");
            jsonWriter.open();
            jsonWriter.write(alarms);
            jsonWriter.close();

            JsonReader jsonReader = new JsonReader("./data/Alarms/writeEmptyAlarms.json");
            alarms = jsonReader.read();
            assertEquals(0,alarms.getAlarms().size());
        } catch (IOException e) {
            fail("Failed to write to file");
        }
    }

    @Test
    public void testWriteManyAlarms() {
        try {
            Alarms alarms = new Alarms();
            AlarmClock ac1 = new AlarmClock("Cheese", 2,0);
            AlarmClock ac2 = new AlarmClock("Caviar", 1,32);
            AlarmClock ac3 = new AlarmClock("Ckjsdf", 6,55);
            alarms.addAlarm(ac1);
            alarms.addAlarm(ac2);
            alarms.addAlarm(ac3);

            JsonWriter jsonWriter = new JsonWriter("./data/Alarms/writeManyAlarms.json");
            jsonWriter.open();
            jsonWriter.write(alarms);
            jsonWriter.close();

            assertEquals(3, alarms.getAlarms().size());
            assertTrue(alarms.getAlarms().contains(ac3));
            AlarmClock testAlarm = alarms.findAlarmClockByName("Ckjsdf");
            assertTrue(testAlarm.getAlarmTime().equals("6:55:0"));
        } catch (IOException e) {
            fail("was not expecting an exception");
        } catch (CouldNotFindClockException e) {
            fail("was not expecting an exception");
        }
    }
}
