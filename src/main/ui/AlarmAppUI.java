package ui;

import model.Alarm;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class AlarmAppUI extends JPanel implements ListSelectionListener {
    private static final String ADD_ALARM = "Add Alarm";
    private static final String REMOVE_ALARM = "Remove Alarm";
    private static final String SET_DIFFICULTY = "Set Problem Difficulty";
    private static final String QUIT = "Quit";

    // list and buttons
    private JList listAlarms;
    private DefaultListModel listAlarmsModel;
    private JButton addButton;
    private JButton removeButton;
    private JButton setDifficultyButton;
    private JButton quitButton;

    // listeners
    private AddAlarmListener addAlarmListener;
    private RemoveAlarmListener removeAlarmListener;
    private SetDifficultyListener setDifficultyListener;
    private QuitListener quitListener;

    private Alarm alarm;

    // inspired by ListDemo from oracle java tutorials
    // EFFECTS: constructs app ui with an empty default list model and initializes a list
    public AlarmAppUI() {
        super(new BorderLayout());

        listAlarmsModel = new DefaultListModel();

        // list and panel
        listAlarms = new JList(listAlarmsModel);
        listAlarms.setLayoutOrientation(JList.VERTICAL);
        listAlarms.setVisibleRowCount(-1);
        listAlarms.addListSelectionListener(this);
        JScrollPane listAlarmsScrollPane = new JScrollPane();

        // addButton
        JButton addButton = new JButton(ADD_ALARM);
//        addAlarmListener = new AddAlarmListener(addButton);
        addButton.setActionCommand(ADD_ALARM);
        addButton.addActionListener(addAlarmListener);
        addButton.setEnabled(false);

        // removeButton
        JButton removeButton = new JButton(REMOVE_ALARM);
//        removeAlarmListener = new RemoveAlarmListener();
        removeButton.setActionCommand(REMOVE_ALARM);
        removeButton.addActionListener(removeAlarmListener);

        // setDifficultyButton
        JButton setDifficultyButton = new JButton(SET_DIFFICULTY);
//        setDifficultyButton = new SetDifficultyListener();
        setDifficultyButton.setActionCommand(SET_DIFFICULTY);
        setDifficultyButton.addActionListener(setDifficultyListener);
        setDifficultyButton.setEnabled(false);

        // quitButton
        JButton quitButton = new JButton(QUIT);
//        QuitListener = new QuitListener();
        quitButton.setActionCommand(QUIT);
        quitButton.addActionListener(quitListener);
        quitButton.setEnabled(false);
    }

    // inspired by ListDemo from oracle java tutorials
    // MODIFIES:
    // EFFECTS:
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            if (listAlarms.getSelectedIndex() == -1) {
                addButton.setEnabled(false);
                removeButton.setEnabled(false);
                setDifficultyButton.setEnabled(false);
                quitButton.setEnabled(false);
            } else {
                addButton.setEnabled(true);
                removeButton.setEnabled(true);
                setDifficultyButton.setEnabled(true);
                quitButton.setEnabled(true);
            }
        }
    }
}
