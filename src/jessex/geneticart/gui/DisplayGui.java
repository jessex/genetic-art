package jessex.geneticart.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.border.SoftBevelBorder;

public class DisplayGui extends JFrame implements ActionListener {

    JPanel source, evolved;             //Panels to hold source, evolved images
    JLabel lblSource, lblEvolved, elapsed, gen, improv, fitness;
    JButton start;

    boolean isRunning, hasStarted;
    private final Dimension FRAMEDIMENSION = new Dimension(420,310);
    private final Dimension BUTTONDIMENSION = new Dimension (140,40);
    private final Font BUTTONFONT = new Font("Sans-Serif", Font.BOLD, 14);
    private final Font LABELFONT = new Font("Sans-Serif", Font.BOLD, 12);
    private DecimalFormat DF = new DecimalFormat("0.00");

    Timer totalTime;
    TimerTask countdown;
    int seconds, minutes;

    public DisplayGui() {
        super("Image Evolution");
        isRunning = false;
        DF.setDecimalSeparatorAlwaysShown(true);

        createGui();
        seconds = minutes = 0;
        hasStarted = false;
    }

    @SuppressWarnings("static-access")
    public void createGui() {
        setSize(FRAMEDIMENSION);
        setMinimumSize(FRAMEDIMENSION);
        setLocation(400,400);
        setResizable(false);
        setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();

        lblSource = new JLabel("Source");
        lblSource.setFont(new Font("Sans-Serif", Font.BOLD, 14));
        g.gridx = g.gridy = 0;
        g.insets = new Insets(5,5,0,0);
        g.anchor = g.WEST;
        add(lblSource, g);

        lblEvolved = new JLabel("Evolution");
        lblEvolved.setFont(new Font("Sans-Serif", Font.BOLD, 14));
        g.gridx = 1;
        g.insets = new Insets(5,0,0,0);
        add(lblEvolved, g);

        source = new JPanel(); //SETTINGS PANEL
        source.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
        source.setPreferredSize(new Dimension(200,200));
        g.gridx = 0;
        g.gridy = 1;
        g.insets = new Insets(5,5,0,10);
        add(source, g);

        evolved = new JPanel();
        evolved.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
        evolved.setPreferredSize(new Dimension(200,200));
        g.gridx = 1;
        g.insets = new Insets(5,0,0,5);
        add(evolved, g);

        elapsed = new JLabel("0m 0s");
        elapsed.setFont(LABELFONT);
        elapsed.setForeground(Color.BLUE);
        g.gridx = 1;
        g.gridy = 2;
        g.anchor = g.EAST;
        g.insets = new Insets(5,0,0,5);
        add(elapsed, g);

        fitness = new JLabel("Fitness: ");
        fitness.setFont(LABELFONT);
        fitness.setForeground(Color.DARK_GRAY);
        g.gridx = 0;
        g.gridy = 2;
        g.anchor = g.WEST;
        g.insets = new Insets(5,5,0,0);
        add(fitness, g);

        gen = new JLabel("Generations: ");
        gen.setFont(LABELFONT);
        gen.setForeground(Color.DARK_GRAY);
        g.gridy = 3;
        g.anchor = g.WEST;
        g.insets = new Insets(5,5,0,0);
        add(gen, g);

        improv = new JLabel("Improvements: ");
        improv.setFont(LABELFONT);
        improv.setForeground(Color.DARK_GRAY);
        g.gridy = 4;
        g.insets = new Insets(5,5,5,0);
        add(improv, g);

        start = new JButton("Start");
        start.setPreferredSize(BUTTONDIMENSION);
        start.setFont(BUTTONFONT);
        start.setForeground(Color.BLUE);
        start.addActionListener(this);
        g.gridx = 1;
        g.gridy = 3;
        g.gridheight = 2;
        g.anchor = g.EAST;
        g.insets = new Insets(5,0,5,0);
        add(start, g);

        pack();
        setVisible(true);
    }

    public void updateEvolved() {
        
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            if (!isRunning) { //Start it up
                isRunning = true;
                //Set up elapsed time timer
                totalTime = new Timer();
                countdown = new ElapsedTime();
                totalTime.scheduleAtFixedRate(countdown, 1000, 1000); //Every second
                start.setText("Pause");
            }
            else {
                isRunning = false;
                countdown.cancel();
                totalTime.cancel();
                start.setText("Start");
            }
        }
    }

    class ElapsedTime extends TimerTask {
        @Override
        public void run() {
            seconds++;
            if (seconds == 60) { //Rollover of minutes hand
                minutes++;
                seconds = 0;
            }
            elapsed.setText(minutes + "m " + seconds + "s");
        }

    }

}
