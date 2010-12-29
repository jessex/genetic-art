package jessex.geneticart.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainGui extends JFrame implements ActionListener {

    DisplayGui ac;

    JPanel settings;
    JButton start, quit, load;
    DoubleJSlider picAdd, picDel, picMove, polyAdd, polyDel;
    DoubleJSlider vertMax, vertMid, vertMin;
    DoubleJSlider colorRate, colorInt, alphaRate, alphaInt;
    JLabel pa, pd, pm, poA, poD, vMa, vMi, vMn, cR, cI, aR, aI;
    JRadioButton def, cus;
    ButtonGroup set;

    boolean isRunning;
    private final Dimension FRAMEDIMENSION = new Dimension(450,400);
    private final Dimension BUTTONDIMENSION = 
            new Dimension ((FRAMEDIMENSION.width-30)/3,40);
    private final Dimension SLIDERDIMENSION =
            new Dimension((FRAMEDIMENSION.width-50)/3, 20);
    private final Font BUTTONFONT = new Font("Sans-Serif", Font.BOLD, 14);
    private final Font LABELFONT = new Font("Sans-Serif", Font.PLAIN, 10);
    private DecimalFormat DF = new DecimalFormat("0.000");
    


    public MainGui() {
        super("Evolution Settings");
        isRunning = false;
        DF.setDecimalSeparatorAlwaysShown(true);
        
        createGui();
    }

    public void createGui() {
        setSize(FRAMEDIMENSION);
        setMinimumSize(FRAMEDIMENSION);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();

        settings = new JPanel(); //SETTINGS PANEL
        settings.setBorder(new TitledBorder("Mutation Rates and Settings"));
        settings.setLayout(new GridBagLayout());
        settings.setPreferredSize(new Dimension(FRAMEDIMENSION.width,
                FRAMEDIMENSION.height-50));
        GridBagConstraints p = new GridBagConstraints();

        /* ********************************************************************
         * **************** START OF SETTINGS PANEL COMPONENTS ****************
         * ********************************************************************
         */

        // ****************************** TOP ROW ******************************

        pa = new JLabel("Polygon Addition:");
        pa.setFont(LABELFONT);
        pa.setHorizontalAlignment(SwingConstants.LEFT);
        p.gridx = p.gridy = 0;
        p.insets = new Insets(5,5,0,10);
        settings.add(pa, p);

        pd = new JLabel("Polygon Deletion:");
        pd.setFont(LABELFONT);
        pd.setHorizontalAlignment(SwingConstants.LEFT);
        p.gridx = 1;
        p.insets = new Insets(5,0,0,10);
        settings.add(pd, p);

        pm = new JLabel("Polygon Movement:");
        pm.setFont(LABELFONT);
        pm.setHorizontalAlignment(SwingConstants.LEFT);
        p.gridx = 2;
        p.insets = new Insets(5,0,0,5);
        settings.add(pm, p);

        picAdd = new DoubleJSlider(0,100,0,1000);
        picAdd.setPreferredSize(SLIDERDIMENSION);
        picAdd.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                pa.setText("Polygon Addition: " + DF.format(picAdd.getScaledValue()));
            }
        });
        pa.setText("Polygon Addition: " + DF.format(picAdd.getScaledValue()));
        p.gridx = 0;
        p.gridy = 1;
        p.insets = new Insets(5,5,0,10);
        settings.add(picAdd, p);

        picDel = new DoubleJSlider(0,100,0,1000);
        picDel.setPreferredSize(SLIDERDIMENSION);
        picDel.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                pd.setText("Polygon Deletion: " + DF.format(picDel.getScaledValue()));
            }
        });
        pd.setText("Polygon Deletion: " + DF.format(picDel.getScaledValue()));
        p.gridx = 1;
        p.insets = new Insets(5,0,0,10);
        settings.add(picDel, p);

        picMove = new DoubleJSlider(0,100,0,1000);
        picMove.setPreferredSize(SLIDERDIMENSION);
        picMove.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                pm.setText("Polygon Movement: " + DF.format(picMove.getScaledValue()));
            }
        });
        pm.setText("Polygon Movement: " + DF.format(picMove.getScaledValue()));
        p.gridx = 2;
        p.insets = new Insets(5,0,0,5);
        settings.add(picMove, p);


        // **************************** SECOND ROW *****************************

        poA = new JLabel("Vertex Addition:");
        poA.setFont(LABELFONT);
        poA.setHorizontalAlignment(SwingConstants.LEFT);
        p.gridx = 0;
        p.gridy = 2;
        p.insets = new Insets(5,5,0,10);
        settings.add(poA, p);

        poD = new JLabel("Vertex Deletion:");
        poD.setFont(LABELFONT);
        poD.setHorizontalAlignment(SwingConstants.LEFT);
        p.gridx = 1;
        p.insets = new Insets(5,0,0,10);
        settings.add(poD, p);

        vMa = new JLabel("Vertex Min Move:");
        vMa.setFont(LABELFONT);
        vMa.setHorizontalAlignment(SwingConstants.LEFT);
        p.gridx = 2;
        p.insets = new Insets(5,0,0,5);
        settings.add(vMa, p);

        polyAdd = new DoubleJSlider(0,100,0,1000);
        polyAdd.setPreferredSize(SLIDERDIMENSION);
        polyAdd.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                poA.setText("Vertex Addition: " + DF.format(polyAdd.getScaledValue()));
            }
        });
        poA.setText("Vertex Addition: " + DF.format(polyAdd.getScaledValue()));
        p.gridx = 0;
        p.gridy = 3;
        p.insets = new Insets(5,5,0,10);
        settings.add(polyAdd, p);

        polyDel = new DoubleJSlider(0,100,0,1000);
        polyDel.setPreferredSize(SLIDERDIMENSION);
        polyDel.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                poD.setText("Vertex Deletion: " + DF.format(polyDel.getScaledValue()));
            }
        });
        poD.setText("Vertex Deletion: " + DF.format(polyDel.getScaledValue()));
        p.gridx = 1;
        p.insets = new Insets(5,0,0,10);
        settings.add(polyDel, p);

        vertMin = new DoubleJSlider(0,100,0,1000);
        vertMin.setPreferredSize(SLIDERDIMENSION);
        vertMin.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                vMa.setText("Vertex Min Move: " + DF.format(vertMin.getScaledValue()));
            }
        });
        vMa.setText("Vertex Min Move: " + DF.format(vertMin.getScaledValue()));
        p.gridx = 2;
        p.insets = new Insets(5,0,0,5);
        settings.add(vertMin, p);

        // **************************** THIRD ROW ******************************

        poA = new JLabel("Vertex Addition:");
        poA.setFont(LABELFONT);
        poA.setHorizontalAlignment(SwingConstants.LEFT);
        p.gridx = 0;
        p.gridy = 4;
        p.insets = new Insets(5,5,0,10);
        settings.add(poA, p);

        poD = new JLabel("Vertex Deletion:");
        poD.setFont(LABELFONT);
        poD.setHorizontalAlignment(SwingConstants.LEFT);
        p.gridx = 1;
        p.insets = new Insets(5,0,0,10);
        settings.add(poD, p);

        vMa = new JLabel("Vertex Min Move:");
        vMa.setFont(LABELFONT);
        vMa.setHorizontalAlignment(SwingConstants.LEFT);
        p.gridx = 2;
        p.insets = new Insets(5,0,0,5);
        settings.add(vMa, p);

        polyAdd = new DoubleJSlider(0,100,0,1000);
        polyAdd.setPreferredSize(SLIDERDIMENSION);
        polyAdd.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                poA.setText("Vertex Addition: " + DF.format(polyAdd.getScaledValue()));
            }
        });
        poA.setText("Vertex Addition: " + DF.format(polyAdd.getScaledValue()));
        p.gridx = 0;
        p.gridy = 3;
        p.insets = new Insets(5,5,0,10);
        settings.add(polyAdd, p);

        polyDel = new DoubleJSlider(0,100,0,1000);
        polyDel.setPreferredSize(SLIDERDIMENSION);
        polyDel.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                poD.setText("Vertex Deletion: " + DF.format(polyDel.getScaledValue()));
            }
        });
        poD.setText("Vertex Deletion: " + DF.format(polyDel.getScaledValue()));
        p.gridx = 1;
        p.insets = new Insets(5,0,0,10);
        settings.add(polyDel, p);

        vertMin = new DoubleJSlider(0,100,0,1000);
        vertMin.setPreferredSize(SLIDERDIMENSION);
        vertMin.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                vMa.setText("Vertex Min Move: " + DF.format(vertMin.getScaledValue()));
            }
        });
        vMa.setText("Vertex Min Move: " + DF.format(vertMin.getScaledValue()));
        p.gridx = 2;
        p.insets = new Insets(5,0,0,5);
        settings.add(vertMin, p);



        /* ********************************************************************
         * ***************** END OF SETTINGS PANEL COMPONENTS *****************
         * ********************************************************************
         */

        g.gridx = g.gridy = 0;
        g.gridwidth = 3;
        g.insets = new Insets(5,5,10,5); //Insets - top left bottom right
        add(settings, g);

        start = new JButton("Start"); //START BUTTON
        start.setPreferredSize(BUTTONDIMENSION);
        start.setFont(BUTTONFONT);
        start.setForeground(Color.BLUE);
        start.addActionListener(this);
        g.gridy = 1;
        g.gridwidth = 1;
        g.insets = new Insets(0,5,5,10);
        add(start, g);

        load = new JButton("Load"); //LOAD BUTTON
        load.setPreferredSize(BUTTONDIMENSION);
        load.setFont(BUTTONFONT);
        load.setForeground(Color.BLUE);
        load.addActionListener(this);
        g.gridx = 1;
        g.insets = new Insets(0,0,5,10);
        add(load, g);

        quit = new JButton("Quit"); //QUIT BUTTON
        quit.setPreferredSize(BUTTONDIMENSION);
        quit.setFont(BUTTONFONT);
        quit.setForeground(Color.BLUE);
        quit.addActionListener(this);
        g.gridx = 2;
        g.insets = new Insets(0,0,5,5);
        add(quit, g);

        pack(); //Pack it up and show it
        setVisible(true);
    }




    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainGui.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(MainGui.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MainGui.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainGui.class.getName()).log(Level.SEVERE, null, ex);
        }
        MainGui gui = new MainGui();

    }

    class DoubleJSlider extends JSlider {
        final int scale;

        public DoubleJSlider(int min, int max, int value, int scale) {
            super(min, max, value);
            this.scale = scale;
        }

        public double getScaledValue() {
            return ((double)super.getValue()) / this.scale;
        }
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) { //CLICKED START

        }
        else if (e.getSource() == load) {

        }
        else if (e.getSource() == quit) {
            System.exit(0);
        }
    }

}
