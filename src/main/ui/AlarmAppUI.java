package ui;

import model.Alarm;
import model.Alarms;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class AlarmAppUI extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final String ADD_ALARM = "Add Alarm";
    private static final String REMOVE_ALARM = "Remove Alarm";
    private static final String SET_DIFFICULTY = "Set Problem Difficulty";
    private static final String QUIT = "Quit";

    private Alarms alarms;
    private ClockUI clockUI;

    private JList list;
    private DefaultListModel listModel;

    private JDesktopPane desktop;
    private JInternalFrame controlPanel;

    public AlarmAppUI() {
        alarms = new Alarms();
        alarms.addAlarm(new Alarm("A",1,1));
        alarms.addAlarm(new Alarm("A",1,1));
        alarms.addAlarm(new Alarm("A",1,1));
        alarms.addAlarm(new Alarm("A",1,1));
        alarms.addAlarm(new Alarm("A",1,1));
        alarms.addAlarm(new Alarm("A",1,1));

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

    public void addAlarms() {
        initListModel();
        initList();
        JScrollPane listScrollPane = new JScrollPane(list);
        listScrollPane.setVisible(true);
        controlPanel.add(listScrollPane, BorderLayout.CENTER);
    }

    public void addClock() {
        clockUI = new ClockUI();
        controlPanel.add(clockUI, BorderLayout.NORTH);
    }

    public void addButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4,1));
        buttonPanel.add(new JButton(new AddAlarmAction()));
        buttonPanel.add(new JButton(new RemoveAlarmAction()));
        buttonPanel.add(new JButton(new SetDifficultyAction()));
        buttonPanel.add(new JButton(new QuitAction()));

        controlPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void initList() {
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(new ListListener());
        list.setVisibleRowCount(5);
    }

    public void initListModel() {
        listModel = new DefaultListModel();
        for (Alarm ac : alarms.getAlarms()) {
            String alarm = ac.getName() + ": " + ac.getAlarmTime();
            listModel.addElement(alarm);
        }
    }

    // from AlarmSystem
    private class DesktopFocusAction extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            AlarmAppUI.this.requestFocusInWindow();
        }
    }

    private class AddAlarmAction extends AbstractAction {
        AddAlarmAction() {
            super("Add Alarm");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO: Implement

        }
    }

    private class RemoveAlarmAction extends AbstractAction {
        RemoveAlarmAction() {
            super("Remove Alarm");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO: Implement
        }
    }

    private class SetDifficultyAction extends AbstractAction {
        SetDifficultyAction() {
            super("Set Difficulty");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO: Implement

        }
    }

    private class QuitAction extends AbstractAction {
        QuitAction() {
            super("Quit");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO: Implement

        }
    }

    private class ListListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {

        }
    }

    public static void main(String[] args) {
        new AlarmAppUI();
    }
}
