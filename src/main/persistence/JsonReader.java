package persistence;

// represents a reader that reads alarms from JSON data stored in a file

import model.AlarmClock;
import model.Alarms;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    // from JsonSerializationDemo
    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // from JsonSerializationDemo
    // EFFECTS: reads alarms from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Alarms read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAlarms(jsonObject);
    }

    // from JsonSerializationDemo
    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // from jsonSerializationDemo
    // EFFECTS: parses workroom from JSON object and returns it
    private Alarms parseAlarms(JSONObject jsonObject) {
        Alarms alarms = new Alarms();
        addAlarms(alarms, jsonObject);
        return alarms;
    }

    // from JsonSerializationDemo
    // MODIFIES: alarms
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    public void addAlarms(Alarms alarms, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Alarms");
        for (Object json : jsonArray) {
            JSONObject nextAlarm = (JSONObject) json;
            addAlarm(alarms, nextAlarm);
        }
    }

    // inspired by JsonSerializationDemo
    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addAlarm(Alarms alarms, JSONObject jsonObject) {
        String alarmName = jsonObject.getString("name");
        int timeHours = jsonObject.getInt("AlarmTimeHours");
        int timeMinutes = jsonObject.getInt("AlarmTimeMinutes");
        AlarmClock alarm = new AlarmClock(alarmName, timeHours, timeMinutes);
        alarms.addAlarm(alarm);
    }

}
