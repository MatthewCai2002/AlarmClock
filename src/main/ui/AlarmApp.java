package ui;

// alarm application

import exceptions.InvalidDifficultyException;
import model.Alarm;
import model.Alarms;
import model.PuzzleManager;
import persistence.JsonReader;
import persistence.JsonWriter;
import puzzles.MathPuzzle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.util.*;

public class AlarmApp {
    private static final String JSON_STORE_ALARMS = "./data/Alarms/Alarms.json";

    // Alarm
    private Boolean ringing;
    private Alarms alarms;
    private Scanner input;

    // Clock
    private Clock clock;
    private Date time;
    private String formattedTime;
    private SimpleDateFormat timeFormat;

    // JSON Readers and Writers
    private JsonWriter jsonWriterAlarms;
    private JsonReader jsonReaderAlarms;

    // Puzzle
    private PuzzleManager puzzleManager;
    private MathPuzzle puzzle;

    // based off of TellerApp class in TellerApp
    // MODIFIES: this
    // EFFECTS: runs the alarm app and instantiates a new list of alarms
    public AlarmApp() {
        runAlarm();
    }

    // based off of TellerApp class in TellerApp
    // MODIFIES: this
    // EFFECTS: processes user input
    public void runAlarm() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            menuAlarms();
            checkIfRing();
            while (ringing) {
                ringAlarm();
                checkSolved();
            }
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("4.") || command.equals("4") || command.equals("quit")) {
                doSaveAlarms();
                keepGoing = false;
            } else {
                processCommandGeneralMenu(command);
            }
        }
        System.out.println("\n ===== Shutting Down =====");
    }

    // based off of TellerApp class in TellerApp and JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: initializes alarms
    public void init() {
        initAlarm();
        initClock();
        initReadWrite();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        try {
            alarms = jsonReaderAlarms.readAlarms();
            System.out.println("Loaded alarms from " + JSON_STORE_ALARMS);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_ALARMS);
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes app clock
    public void initClock() {
        clock = Clock.systemDefaultZone();
        timeFormat = new SimpleDateFormat("HH:mm:ss");
        formattedTime = "";
    }

    // MODIFIES: this
    // EFFECTS: initializes JSON readers and writers
    public void initReadWrite() {
        jsonWriterAlarms = new JsonWriter(JSON_STORE_ALARMS);
        jsonReaderAlarms = new JsonReader(JSON_STORE_ALARMS);
    }

    // MODIFIES: this
    // EFFECTS: initializes alarm
    public void initAlarm() {
        ringing = false;
        puzzleManager = new PuzzleManager();
    }

    // based off of TellerApp class in TellerApp
    // EFFECTS: shows a menu with all current alarms and 4 options
    public void menuAlarms() {
        System.out.println("====================");
        printTime();
        System.out.println("Current alarms:");
        for (Alarm ac : alarms.getAlarms()) {
            System.out.println("\t" + ac.getName() + ": " + ac.getAlarmTime());
        }
        System.out.println("\n what do you want to do?");
        System.out.println("\t 1. add an alarm");
        System.out.println("\t 2. remove an alarm");
        System.out.println("\t 3. set puzzle difficulty");
        System.out.println("\t 4. quit");
    }

    public void printTime() {
        time = Date.from(clock.instant());
        formattedTime = timeFormat.format(time);
        System.out.println("Current Time: " + formattedTime);
    }


    // based off of TellerApp class in TellerApp
    // MODIFIES: this
    // EFFECTS: processes chosen option
    public void processCommandGeneralMenu(String command) {
        if (command.equals("1.") || command.equals("1") || command.equals("add")) {
            doAddAlarm();
        } else if (command.equals("2.") || command.equals("2") || command.equals("remove")) {
            doRemoveAlarm();
        } else if (command.equals("3.") || command.equals("3") || command.equals("set")) {
            doSetDifficulty();
        } else {
            System.out.println("INVALID CHOICE CHOOSE AGAIN");
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the difficulty of puzzle to given difficulty
    private void doSetDifficulty() {
        System.out.println("\n do you want easy or medium difficulty?");
        String difficulty = input.next();
        try {
            puzzleManager.setPuzzle(difficulty);
        } catch (InvalidDifficultyException e) {
            System.out.println("sorry that was an invalid difficulty");
        }
    }

    // based off of TellerApp class in TellerApp
    // MODIFIES: this
    // EFFECTS: adds an alarm clock to list of alarms
    public void doAddAlarm() {
        Alarm alarm = new Alarm("", 0, 0);
        boolean adding = true;
        while (adding) {
            try {
                doAddAlarmHours(alarm);
                adding = false;
            } catch (InputMismatchException e) {
                System.out.println("sorry that wasn't an integer try again");
                break;
            }
        }
    }

    // MODIFIES: alarm
    // EFFECTS: if 0 <= input hour <= 24 then alarm hour is set to input hour
    //          and progress to the next phase
    //              else input time again
    public void doAddAlarmHours(Alarm alarm) throws InputMismatchException {
        System.out.println("\n what hours do you want for your alarm?");
        int hour = input.nextInt();
        if ((0 <= hour) && (hour <= 24)) {
            alarm.setAlarmTimeHours(hour);
            doAddAlarmMinutes(alarm);
        } else {
            System.out.println("sorry that's an invalid time, try again");
            doAddAlarmHours(alarm);
        }
    }



    // MODIFIES: alarm
    // EFFECTS: if 0 <= input minutes <= 60 then alarm minutes is set to input minutes
    //          and progress to the next phase
    //              else input time again
    public void doAddAlarmMinutes(Alarm alarm) throws InputMismatchException {
        System.out.println("\n what minutes do you want for your alarm?");
        int minutes = input.nextInt();
        if ((0 <= minutes) && (minutes <= 60)) {
            alarm.setAlarmTimeMinutes(minutes);
            doAddAlarmName(alarm);
        } else {
            System.out.println("sorry that's an invalid time, try again");
            doAddAlarmMinutes(alarm);
        }
    }


    // MODIFIES: alarm
    // EFFECTS: sets name of alarm to input name
    public void doAddAlarmName(Alarm alarm) {
        System.out.println("\n what name do you want for your alarm?");
        String name = input.next();
        alarm.setName(name);
        alarms.addAlarm(alarm);
        doSaveAlarms();
    }


    // MODIFIES: this
    // EFFECTS: removes an alarm clock from the list and reloads menu
    public void doRemoveAlarm() {
        boolean removing = true;
        while (removing) {
            System.out.println("which alarm do you want to remove?");
            for (Alarm ac : alarms.getAlarms()) {
                System.out.println("\t" + ac.getName() + ": " + ac.getAlarmTime());
            }
            String remove = input.next();
            int counter = 0;
            for (Alarm ac : alarms.getAlarms()) {
                String name = ac.getName();
                if (name.equals(remove)) {
                    alarms.getAlarms().remove(ac);
                    System.out.println("successfully removed " + remove);
                    removing = false;
                    break;
                }
                counter++;
                if (counter == alarms.getAlarms().size()) {
                    System.out.println("Sorry that's not a valid alarm, try again");
                }
            }
        }
        doSaveAlarms();
    }

    // EFFECT: rings alarm when current time reaches an alarm time
    public void ringAlarm() {
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (ringing) {
                        System.out.println("BEEP!!!");
                    } else if (!ringing) {
                        timer.cancel();
                    }
                }
            }, 0, 1000);
        System.out.println("solve " + puzzle.getProblem() + " to turn off the alarm");
    }

    // EFFECTS: checks whether the current time is the same as an alarm time
    //          if yes then makes the alarm ring
    //              otherwise there does nothing
    public void checkIfRing() {
        for (Alarm ac : alarms.getAlarms()) {
            if (ac.getAlarmTime().equals(formattedTime)) {
                ringAlarm();
            }
        }
    }

    // EFFECTS: checks if the problem has been solved
    //          if yes then it turns off the alarm
    //              otherwise it prompts to try again
    public void checkSolved() {
        int solution = input.nextInt();
        puzzle.solvePuzzle(solution);
        boolean solved = puzzle.isSolved();
        if (solved) {
            ringing = false;
            System.out.println("CONGRATULATIONS YOU GOT UP!!!");
            menuAlarms();
        } else {
            System.out.println("oops that's not quite right, try again");
        }
    }

    // EFFECTS: saves alarms to file
    public void doSaveAlarms() {
        try {
            jsonWriterAlarms.open();
            jsonWriterAlarms.write(alarms);
            jsonWriterAlarms.close();
            System.out.println("saved all your alarms to " + JSON_STORE_ALARMS);
        } catch (FileNotFoundException e) {
            System.out.println("unable to write to file in " + JSON_STORE_ALARMS);
        }

    }

}
