package ui;

import model.Alarms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AlarmAppUI extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final String ADD_ALARM = "Add Alarm";
    private static final String REMOVE_ALARM = "Remove Alarm";
    private static final String SET_DIFFICULTY = "Set Problem Difficulty";
    private static final String QUIT = "Quit";

    private Alarms alarms;
    private ClockUI clockUI;

    private JDesktopPane desktop;
    private JInternalFrame controlPanel;

    public AlarmAppUI() {
        alarms = new Alarms();

        desktop = new JDesktopPane();
        desktop.addMouseListener(new DesktopFocusAction());
        controlPanel = new JInternalFrame("", false, false,false,false);
        controlPanel.setLayout(new BorderLayout());

        setContentPane(desktop);
        setTitle("Annoying Alarm Clock");
        setSize(WIDTH,HEIGHT);

        addButtons();
        addClock();

        controlPanel.pack();
        controlPanel.setVisible(true);
        desktop.add(controlPanel);
        Dimension controlPanelSize = controlPanel.getSize();
        controlPanel.setLocation((WIDTH - controlPanelSize.width)/2,
                (HEIGHT - controlPanelSize.height)/2);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void addClock() {
        clockUI = new ClockUI();
        controlPanel.add(clockUI, BorderLayout.NORTH);
    }

    private void addButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4,1));
        buttonPanel.add(new JButton(new AddAlarmAction()));
        buttonPanel.add(new JButton(new RemoveAlarmAction()));
        buttonPanel.add(new JButton(new SetDifficultyAction()));
        buttonPanel.add(new JButton(new QuitAction()));

        controlPanel.add(buttonPanel, BorderLayout.SOUTH);
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

    public static void main(String[] args) {
        new AlarmAppUI();
    }
}
