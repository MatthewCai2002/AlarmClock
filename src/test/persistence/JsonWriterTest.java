package persistence;

import exceptions.CouldNotFindClockException;
import model.AlarmClock;
import model.Alarms;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {
    Alarms alarms;
    AlarmClock alarm;

    @BeforeEach
    public void setup() {
        alarm = new AlarmClock("", 0, 0);
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
    public void testWriteAlarmToInvalidFile() {
        try {
            AlarmClock alarm = new AlarmClock("",0,0);
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
    public void testWriteAlarmNoAlarm() {
        try {
            JsonWriter jsonWriter = new JsonWriter("./data/Alarms/writeNoAlarm.json");
            jsonWriter.open();
            jsonWriter.writeAlarm(alarm);
            jsonWriter.close();

            JsonReader jsonReader = new JsonReader("./data/Alarms/writeNoAlarm.json");
            alarm = jsonReader.readAlarmClock();
            String clockTime = alarm.getClockTime();

            assertTrue(clockTime.equals("0:0:0"));
        } catch (IOException e) {
            fail("Failed to write to file");
        }
    }

    @Test
    public void testWriteAlarmsManyAlarms() {
        try {
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

    @Test
    public void testWriteAlarmOneAlarm() {
        try {
            alarm.setAlarmTimeHours(6);
            alarm.setAlarmTimeMinutes(55);
            alarm.setName("Cheese");
            alarm.setClockTimeHours(3);
            alarm.setClockTimeMinutes(55);
            alarm.setClockTimeSeconds(1);

            JsonWriter jsonWriter = new JsonWriter("./data/Alarms/writeOneAlarm.json");
            jsonWriter.open();
            jsonWriter.writeAlarm(alarm);
            jsonWriter.close();

            JsonReader jsonReader = new JsonReader("./data/Alarms/writeOneAlarm.json");
            alarm = jsonReader.readAlarmClock();
            String alarmTime = alarm.getAlarmTime();
            String clockTime = alarm.getClockTime();

            assertTrue(alarmTime.equals("6:55:0"));
            assertTrue(clockTime.equals("3:55:1"));
        } catch (IOException e) {
            fail("was not expecting an exception");
        }
    }
}
