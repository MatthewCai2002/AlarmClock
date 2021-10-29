package ui;

// alarm application

import model.AlarmClock;
import model.Alarms;
import persistence.JsonReader;
import persistence.JsonWriter;
import puzzles.MathPuzzle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class AlarmApp {
    private static final String JSON_STORE_ALARMS = "./data/Alarms/Alarms.json";
    private static final String JSON_STORE_ALARM = "./data/Alarms/Alarm.json";

    private Boolean ringing;
    private MathPuzzle puzzle;
    private AlarmClock globalAlarm;
    private Alarms alarms;
    private Scanner input;
    private JsonWriter jsonWriterAlarms;
    private JsonWriter jsonWriterAlarm;
    private JsonReader jsonReaderAlarms;
    private JsonReader jsonReaderAlarm;


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
                doSaveAlarm();
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
        ringing = false;
        puzzle = new MathPuzzle();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        jsonWriterAlarms = new JsonWriter(JSON_STORE_ALARMS);
        jsonWriterAlarm = new JsonWriter(JSON_STORE_ALARM);
        jsonReaderAlarms = new JsonReader(JSON_STORE_ALARMS);
        jsonReaderAlarm = new JsonReader(JSON_STORE_ALARM);
        try {
            alarms = jsonReaderAlarms.readAlarms();
            globalAlarm = jsonReaderAlarm.readAlarmClock();
            System.out.println("Loaded alarms from " + JSON_STORE_ALARMS);
            System.out.println("Loaded current time from" + JSON_STORE_ALARM);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_ALARMS);
        }
    }

    // based off of TellerApp class in TellerApp
    // EFFECTS: shows a menu with all current alarms and 4 options
    public void menuAlarms() {
        System.out.println("====================");
        System.out.println(globalAlarm.getName() + ": " + globalAlarm.getClockTime());
        System.out.println("Current alarms:");
        for (AlarmClock ac : alarms.getAlarms()) {
            System.out.println("\t" + ac.getName() + ": " + ac.getAlarmTime());
        }
        System.out.println("\n what do you want to do?");
        System.out.println("\t 1. add an alarm");
        System.out.println("\t 2. remove an alarm");
        System.out.println("\t 3. pass time");
        System.out.println("\t 4. quit");
    }

    // based off of TellerApp class in TellerApp
    // MODIFIES: this
    // EFFECTS: processes chosen option
    public void processCommandGeneralMenu(String command) {
        if (command.equals("1.") || command.equals("1") || command.equals("add")) {
            doAddAlarm();
        } else if (command.equals("2.") || command.equals("2") || command.equals("remove")) {
            doRemoveAlarm();
        } else if (command.equals("3.") || command.equals("3") || command.equals("pass time")) {
            doPassTime();
        } else {
            System.out.println("INVALID CHOICE CHOOSE AGAIN");
        }
    }

    // based off of TellerApp class in TellerApp
    // MODIFIES: this
    // EFFECTS: adds an alarm clock to list of alarms
    public void doAddAlarm() {
        AlarmClock alarm = new AlarmClock("", 0, 0);
        try {
            doAddAlarmHours(alarm);
        } catch (InputMismatchException e) {
            System.out.println("sorry that wasn't an integer try again");
            doAddAlarm();
        }
    }

    // MODIFIES: alarm
    // EFFECTS: if 0 <= input hour <= 24 then alarm hour is set to input hour
    //          and progress to the next phase
    //              else input time again
    public void doAddAlarmHours(AlarmClock alarm) {
        System.out.println("\n what hours do you want for your alarm?");
        int hour = input.nextInt();
        if ((0 <= hour) && (hour <= 24)) {
            alarm.setAlarmTimeHours(hour);
            try {
                doAddAlarmMinutes(alarm);
            } catch (InputMismatchException e) {
                System.out.println("sorry that wasn't an integer try again");
            }
        } else {
            System.out.println("sorry that's an invalid time, try again");
            doAddAlarmHours(alarm);
        }
    }



    // MODIFIES: alarm
    // EFFECTS: if 0 <= input minutes <= 60 then alarm minutes is set to input minutes
    //          and progress to the next phase
    //              else input time again
    public void doAddAlarmMinutes(AlarmClock alarm) {
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
    public void doAddAlarmName(AlarmClock alarm) {
        System.out.println("\n what name do you want for your alarm?");
        String name = input.next();
        alarm.setName(name);
        alarms.addAlarm(alarm);
        doSaveAlarms();
    }


    // MODIFIES: this
    // EFFECTS: removes an alarm clock from the list and reloads menu
    public void doRemoveAlarm() {
        System.out.println("which alarm do you want to remove?");
        for (AlarmClock ac : alarms.getAlarms()) {
            System.out.println("\t" + ac.getName() + ": " + ac.getAlarmTime());
        }
        String remove = input.next();
        int counter = 0;
        for (AlarmClock ac : alarms.getAlarms()) {
            String name = ac.getName();
            if (name.equals(remove)) {
                alarms.getAlarms().remove(ac);
                System.out.println("successfully removed " + remove);
                break;
            }
            counter++;
            if (counter == alarms.getAlarms().size()) {
                System.out.println("Sorry that's not a valid alarm, try again");
                doRemoveAlarm();
            }
        }
        doSaveAlarms();
    }

    // MODIFIES: this
    // EFFECTS: passes the time input time in seconds
    public void doPassTime() {
        System.out.println("fast forward by how many seconds?");
        int passTime = input.nextInt();
        if (passTime >= 0) {
            globalAlarm.tick(passTime);
        } else {
            System.out.println("Sorry that's an invalid time try again");
            doPassTime();
        }
        doSaveAlarm();
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
    //              otherwise there it does not
    public void checkIfRing() {
        for (AlarmClock ac : alarms.getAlarms()) {
            if (ac.getAlarmTime().equals(globalAlarm.getClockTime())) {
                ringing = true;
            } else {
                ringing = false;
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

    // EFFECTS: saves global alarm to file
    public void doSaveAlarm() {
        try {
            jsonWriterAlarm.open();
            jsonWriterAlarm.writeAlarm(globalAlarm);
            jsonWriterAlarm.close();
            System.out.println("saved the current time to " + JSON_STORE_ALARM);
        } catch (FileNotFoundException e) {
            System.out.println("unable to write to file in " + JSON_STORE_ALARM);
        }

    }

}
