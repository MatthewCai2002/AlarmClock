package ui;

import exceptions.InvalidTimeException;
import model.Alarm;
import model.Alarms;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
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

    private JList list;
    private DefaultListModel listModel;

    private JDesktopPane desktop;
    private JInternalFrame controlPanel;

    private JsonWriter jsonWriterAlarms;
    private JsonReader jsonReaderAlarms;

    // inspired by AlarmSystem
    // EFFECTS: constructs an alarm app with a global clock, buttons, and displayed alarms
    public AlarmAppUI() {
        alarms = new Alarms();
        initReadWrite();

        desktop = new JDesktopPane();
        desktop.addMouseListener(new DesktopFocusAction());
        controlPanel = new JInternalFrame("", false, false,false,false);
        controlPanel.setLayout(new BorderLayout());

        setContentPane(desktop);
        setTitle("Annoying Alarm Clock");
        setSize(WIDTH,HEIGHT);

        addAlarms();
        addButtons();
        addClock();

        controlPanel.pack();
        controlPanel.setVisible(true);
        desktop.add(controlPanel);
        Dimension controlPanelSize = controlPanel.getSize();
        controlPanel.setLocation((WIDTH - controlPanelSize.width)/2,(HEIGHT - controlPanelSize.height)/2);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
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
        buttonPanel.setLayout(new GridLayout(4,1));
        buttonPanel.add(new JButton(new AddAlarmAction()));
        buttonPanel.add(new JButton(new RemoveAlarmAction()));
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
        list.setSelectedIndex(0);
        list.addListSelectionListener(new ListListener());
        list.setVisibleRowCount(5);
    }

    // EFFECTS: helper that initializes JListModel and adds all alarms into it
    public void initListModel() {
        listModel = new DefaultListModel();
        for (Alarm ac : alarms.getAlarms()) {
            String alarm = ac.getName() + ": " + ac.getAlarmTime();
            listModel.addElement(alarm);
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes JSON readers and writers
    public void initReadWrite() {
        jsonWriterAlarms = new JsonWriter(JSON_STORE_ALARMS);
        jsonReaderAlarms = new JsonReader(JSON_STORE_ALARMS);
    }

    // from AlarmSystem
    /* represents the action to be taken when the user clicks on the app
    to switch focus */
    private class DesktopFocusAction extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            AlarmAppUI.this.requestFocusInWindow();
        }
    }

    /* represents the action to be taken when the user wants
    to add an alarm
     */
    private class AddAlarmAction extends AbstractAction {
        JTextField hourField = new JTextField();
        JTextField minutesField = new JTextField();
        JTextField nameField = new JTextField();

        AddAlarmAction() {
            super("Add Alarm");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int result = JOptionPane.showOptionDialog(
                    null,
                    new Object[]{"Please enter a clock hours, minutes, and name",
                            hourField,
                            minutesField,
                            nameField},
                    "Add An Alarm",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    null,
                    null);

            if (result == JOptionPane.OK_OPTION) {
                String hoursString = hourField.getText();
                String minutesString = minutesField.getText();
                String name = nameField.getText();
                try {
                    int hours = Integer.parseInt(hoursString);
                    int minutes = Integer.parseInt(minutesString);
                    Alarm alarm = new Alarm(name,hours,minutes);
                    alarms.addAlarm(alarm);
                    String alarmString = alarm.getName() + ": " + alarm.getAlarmTime();
                    listModel.addElement(alarmString);
                } catch (NumberFormatException nfe) {
                    System.out.println("sorry that was an invalid time");
                    System.exit(0);
                } catch (InvalidTimeException ite) {
                    System.out.println("sorry that was an invalid time");
                    System.exit(0);
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
            }
            if (index == listModel.getSize()) {
                index--;
            }
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }
    }

    /* represents the action to be taken when a user wants to
    set a difficulty
     */
    private class SetDifficultyAction extends AbstractAction {
        SetDifficultyAction() {
            super("Set Difficulty");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO: Implement

        }
    }

    private class SaveAlarmsAction extends AbstractAction {
        SaveAlarmsAction() {
            super("Save");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            saveAlarms();
        }
    }

    private class LoadAlarmsAction extends AbstractAction {
        LoadAlarmsAction() {
            super("Load");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                alarms = jsonReaderAlarms.readAlarms();
                for (Alarm alarm : alarms.getAlarms()) {
                    String alarmString = alarm.getName() + ": " + alarm.getAlarmTime();
                    listModel.addElement(alarmString);
                }
                System.out.println("Loaded alarms from " + JSON_STORE_ALARMS);
            } catch (IOException ie) {
                System.out.println("Unable to read from file: " + JSON_STORE_ALARMS);
            }
        }
    }

    /* represents the action to be taken when a user wants
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
    /* represents the action to be taken when a user selects
    an alarm in list of alarms
     */
    private class ListListener implements ListSelectionListener {


        @Override
        public void valueChanged(ListSelectionEvent e) {
           // does not do anything
        }

    }
    // EFFECTS: saves alarms to file

    public void saveAlarms() {
        try {
            jsonWriterAlarms.open();
            jsonWriterAlarms.write(alarms);
            jsonWriterAlarms.close();
            System.out.println("saved all your alarms to " + JSON_STORE_ALARMS);
        } catch (FileNotFoundException e) {
            System.out.println("unable to write to file in " + JSON_STORE_ALARMS);
        }
    }

    public static void main(String[] args) {
        new AlarmAppUI();
    }
}
