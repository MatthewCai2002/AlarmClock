package ui;

import exceptions.InvalidDifficultyException;
import model.Alarm;
import model.Alarms;
import model.PuzzleManager;
import persistence.JsonReader;
import persistence.JsonWriter;
import puzzles.EasyMathPuzzle;
import puzzles.MathPuzzle;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AlarmAppUI extends JFrame {
    private static final String JSON_STORE_ALARMS = "./data/Alarms/Alarms.json";
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;

    private Alarms alarms;
    private ClockUI clockUI;
    private PuzzleManager puzzleManager;
    private MathPuzzle puzzle;
    private Boolean ringing;
    private Siren siren;

    private JList list;
    private DefaultListModel listModel;
    private JButton removeButton;

    private JDesktopPane desktop;
    private JInternalFrame controlPanel;

    private JsonWriter jsonWriterAlarms;
    private JsonReader jsonReaderAlarms;

    // inspired by AlarmSystem
    // EFFECTS: constructs an alarm app with a global clock, buttons, and displayed alarms
    public AlarmAppUI() {
        initAlarm();
        initReadWrite();

        desktop = new JDesktopPane();
        desktop.addMouseListener(new DesktopFocusAction());
        controlPanel = new JInternalFrame("", false, false,true,false);
        controlPanel.setLayout(new BorderLayout());

        setContentPane(desktop);
        setTitle("Annoying Alarm Clock");
        setSize(WIDTH,HEIGHT);

        addAlarms();
        addButtons();
        addClock();
        checkRingingEverySecond();

        controlPanel.pack();
        controlPanel.setVisible(true);
        desktop.add(controlPanel);
        Dimension controlPanelSize = controlPanel.getSize();
        controlPanel.setLocation((
                WIDTH - controlPanelSize.width) / 2,
                (HEIGHT - controlPanelSize.height) / 2);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // EFFECTS: initializes alarms, puzzle, puzzleManager,
    //          ringing state, and siren
    private void initAlarm() {
        alarms = new Alarms();
        puzzleManager = new PuzzleManager();
        puzzle = new EasyMathPuzzle();
        ringing = false;
        try {
            siren = new Siren();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: helper that displays the list of alarms in the panel
    public void addAlarms() {
        initListModel();
        initList();
        JScrollPane listScrollPane = new JScrollPane(list);
        listScrollPane.setVisible(true);
        controlPanel.add(listScrollPane, BorderLayout.CENTER);
    }

    // inspired by AlarmSystem
    // EFFECTS: helper that adds the global clock to the panel
    public void addClock() {
        clockUI = new ClockUI();
        controlPanel.add(clockUI, BorderLayout.NORTH);
    }

    // inspired by AlarmSystem
    // EFFECTS: helper that adds all buttons to a panel
    public void addButtons() {
        JPanel buttonPanel = new JPanel();
        removeButton = new JButton(new RemoveAlarmAction());
        buttonPanel.setLayout(new GridLayout(3,2));
        buttonPanel.add(new JButton(new AddAlarmAction()));
        buttonPanel.add(removeButton);
        buttonPanel.add(new JButton(new SetDifficultyAction()));
        buttonPanel.add(new JButton(new SaveAlarmsAction()));
        buttonPanel.add(new JButton(new LoadAlarmsAction()));
        buttonPanel.add(new JButton(new QuitAction()));

        controlPanel.add(buttonPanel, BorderLayout.PAGE_END);
    }

    // from ListDemo
    // EFFECTS: helper that initializes a JList with data from listModel and other parameters
    public void initList() {
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(-1);
        list.addListSelectionListener(new ListListener());
        list.setVisibleRowCount(5);
    }

    // EFFECTS: helper that initializes JListModel and adds all alarms into it
    public void initListModel() {
        listModel = new DefaultListModel();
        for (Alarm ac : alarms.getAlarms()) {
            addToAlarmsModel(ac);
        }
    }

    private void addToAlarmsModel(Alarm ac) {
        String alarm = ac.getName() + ": " + ac.getAlarmTime();
        listModel.addElement(alarm);
    }


    // MODIFIES: this
    // EFFECTS: initializes JSON readers and writers
    public void initReadWrite() {
        jsonWriterAlarms = new JsonWriter(JSON_STORE_ALARMS);
        jsonReaderAlarms = new JsonReader(JSON_STORE_ALARMS);
    }

    // EFFECTS: helper method that displays
    //          an image with a label in a popup
    public void showImage(String fileLocation, String description, String message) {
        ImageIcon shownImage = new ImageIcon(fileLocation, description);
        Image rawImage = shownImage.getImage();
        Image scaledImage = rawImage.getScaledInstance(80,80,Image.SCALE_SMOOTH);
        shownImage = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(message, shownImage, JLabel.CENTER);
        JOptionPane.showMessageDialog(controlPanel,
                imageLabel,
                description,
                JOptionPane.PLAIN_MESSAGE,
                null);
    }

    // EFFECT: rings alarm when current time reaches an alarm time
    public void ringAlarm() {
        javax.swing.Timer t = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ringing) {
                    siren.playAudio();
                }
            }
        });
        t.start();
        puzzle.genRandomPuzzle();
        String tempSol = JOptionPane.showInputDialog(
                controlPanel, "solve " + puzzle.getProblem() + " to turn off the alarm");
        try {
            int solution = Integer.parseInt(tempSol);
            checkSolved(solution);
            if (!ringing) {
                t.stop();
            }
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(controlPanel,
                    "That was an invalid solution",
                    "Error",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // inspired by SpaceInvaders
    // set up timer
    // EFFECTS: initializes timer that updates clock every
    //          second
    public void checkRingingEverySecond() {
        javax.swing.Timer t = new javax.swing.Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkIfRing();
            }
        });
        t.start();
    }

    // EFFECTS: checks whether the current time is the same as an alarm time
    //          if yes then makes the alarm ring
    //              otherwise do nothing
    public void checkIfRing() {
        String formattedTime = clockUI.getFormattedTime();
        for (Alarm ac : alarms.getAlarms()) {
            if (ac.getAlarmTime().equals(formattedTime)) {
                ringing = true;
                ringAlarm();
            }
        }
    }

    // EFFECTS: checks if the problem has been solved
    //          if yes then it turns off the alarm
    //              otherwise it prompts to try again
    public void checkSolved(int solution) {
        puzzle.solvePuzzle(solution);
        boolean solved = puzzle.isSolved();
        if (solved) {
            ringing = false;
            siren.stopAudio();
            showImage("./data/Right.png","Success!","CONGRATULATIONS YOU GOT UP!!!");
        }
    }

    // from AlarmSystem
    /*
    represents the action to be taken when the user clicks on the app
    to switch focus
    */
    private class DesktopFocusAction extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            AlarmAppUI.this.requestFocusInWindow();
        }
    }

    /*
    represents the action to be taken when the user wants
    to add an alarm
     */
    private class AddAlarmAction extends AbstractAction {
        JTextField hourField = new JTextField();
        JTextField minutesField = new JTextField();
        JTextField nameField = new JTextField();

        AddAlarmAction() {
            super("Add Alarm");
        }

        // EFFECTS: resets text fields to empty
        public void resetTextFields() {
            hourField.setText("");
            minutesField.setText("");
            nameField.setText("");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int result = JOptionPane.showOptionDialog(
                    controlPanel, new Object[]{"Please enter hours, minutes, and name", hourField, minutesField,
                            nameField}, "Add An Alarm", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, null, null);
            if (result == JOptionPane.OK_OPTION) {
                String hoursString = hourField.getText();
                String minutesString = minutesField.getText();
                String name = nameField.getText();
                resetTextFields();
                try {
                    int hours = Integer.parseInt(hoursString);
                    int minutes = Integer.parseInt(minutesString);
                    Alarm alarm = new Alarm(name,hours,minutes);
                    alarms.addAlarm(alarm);
                    addToAlarmsModel(alarm);
                    showImage("./data/Right.png","Success", "Successfully added alarm!");
                } catch (RuntimeException re) {
                    showImage("./data/Wrong.png","Error","Sorry that wasn't a valid time");
                    actionPerformed(e);
                }

            }
        }
    }

    // inspired by ListDemo from oracle tutorials
    /*
    represents the action to be taken when a user wants to
    remove an alarm
     */
    private class RemoveAlarmAction extends AbstractAction {
        RemoveAlarmAction() {
            super("Remove Alarm");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int index = list.getSelectedIndex();
            listModel.remove(index);
            if (listModel.size() == 0) {
                this.setEnabled(false);
            } else if (index == listModel.getSize()) {
                index--;
            }
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }
    }

    /*
    represents the action to be taken when a user wants to
    set a difficulty
     */
    private class SetDifficultyAction extends AbstractAction {
        SetDifficultyAction() {
            super("Set Difficulty");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String[] options = new String[] {"easy", "medium"};
            String difficulty = (String) JOptionPane.showInputDialog(
                    controlPanel,
                    "Select a Difficulty",
                    "Difficulty",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if (difficulty != null) {
                try {
                    puzzleManager.setPuzzle(difficulty);
                    puzzle = puzzleManager.getPuzzle();
                    showImage("./data/Right.png",
                            "Successful Set Difficulty",
                            "Set difficulty to " + difficulty);
                } catch (InvalidDifficultyException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    /*
     represents the action to be taken when a user wants to
    save their data
     */
    private class SaveAlarmsAction extends AbstractAction {
        SaveAlarmsAction() {
            super("Save");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            saveAlarms();
        }
    }

    /*
    represents the action to take when a user wants to
    load their data
     */
    private class LoadAlarmsAction extends AbstractAction {
        LoadAlarmsAction() {
            super("Load");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                alarms = jsonReaderAlarms.readAlarms();
                for (Alarm alarm : alarms.getAlarms()) {
                    addToAlarmsModel(alarm);
                }
                list.setSelectedIndex(0);
                JOptionPane.showMessageDialog(controlPanel,
                        "Loaded alarms from " + JSON_STORE_ALARMS,
                        "Successfully Load",JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ie) {
                JOptionPane.showMessageDialog(controlPanel,
                        "Unable to read from file: " + JSON_STORE_ALARMS,
                        "Error",JOptionPane.INFORMATION_MESSAGE);

            }
        }
    }

    /*
    represents the action to be taken when a user wants
    to quit the application
     */
    private class QuitAction extends AbstractAction {
        QuitAction() {
            super("Quit");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }

    }

    // inspired by ListDemo from oracle tutorials
    /*
    represents the action to be taken when a user selects
    an alarm in list of alarms
     */
    private class ListListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                if (list.getSelectedIndex() == -1) {
                    removeButton.setEnabled(false);
                } else {
                    removeButton.setEnabled(true);
                }
            }
        }
    }

    // EFFECTS: saves alarms to file
    public void saveAlarms() {
        try {
            jsonWriterAlarms.open();
            jsonWriterAlarms.write(alarms);
            jsonWriterAlarms.close();
            JOptionPane.showMessageDialog(controlPanel,
                    "saved all your alarms to " + JSON_STORE_ALARMS,
                    "Successfully Saved",JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(controlPanel,
                    "unable to write to file in " + JSON_STORE_ALARMS,
                    "Successfully Saved",JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
