package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.util.Date;
import javax.swing.Timer;

public class ClockUI extends JPanel {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 50;
    private static final int FONT_SIZE = 24;

    private JLabel clockLabel;
    private Clock clock;
    private String formattedTime;
    private SimpleDateFormat timeFormat;
    private Date time;

    // EFFECTS: constructs a panel with current system time that
    //          ticks every second
    public ClockUI() {
        initClock();
        clockLabel = new JLabel("Current Time");
        clockLabel.setText(formattedTime);
        clockLabel.setFont(new Font("Dialog", Font.BOLD, FONT_SIZE));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addTimer();
        add(clockLabel);
        setVisible(true);
    }

    // set up clock
    // EFFECTS: initializes clock that matches system clock
    //          and time format
    public void initClock() {
        clock = Clock.systemDefaultZone();
        timeFormat = new SimpleDateFormat("HH:mm:ss");
        formattedTime = "";
    }

    // inspired by SpaceInvaders
    // set up timer
    // EFFECTS: initializes timer that updates clock every
    //          second
    public void addTimer() {
        Timer t = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateClock();
            }
        });
        t.start();
    }

    // EFFECTS: sets clock label to current system time
    public void updateClock() {
        time = Date.from(clock.instant());
        formattedTime = timeFormat.format(time);
        clockLabel.setText(formattedTime);
    }

    // EFFECTS: converts formattedTime from HH:mm:ss
    //          to HH:mm:0
    public String getNewFormattedTime() {
        SimpleDateFormat newFormat = new SimpleDateFormat("HH:mm:0");
        String newFormattedTime = newFormat.format(time);
        return newFormattedTime;
    }

}
