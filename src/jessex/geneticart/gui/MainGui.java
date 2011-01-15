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
import jessex.geneticart.engine.Settings;

public class MainGui extends JFrame implements ActionListener {

    DisplayGui ac;

    JPanel settings, control;
    JButton reset, quit, load, export, imports;
    DoubleJSlider picAdd, picDel, picMove, polyAdd, polyDel;
    DoubleJSlider vertMax, vertMid, vertMin;
    DoubleJSlider colorRate, alphaRate;
    JSlider maxPoly, maxVert, minPoly, minVert, colorInt, alphaInt;
    JLabel pa, pd, pm, poA, poD, vMa, vMi, vMn, cR, cI, aR, aI, mP, mV, iP, iV;
    JRadioButton def, cus;
    ButtonGroup set;

    boolean isRunning;
    private final Dimension FRAMEDIMENSION = new Dimension(450,310);
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

    @SuppressWarnings("static-access")
    public void createGui() {
        setSize(FRAMEDIMENSION);
        setMinimumSize(FRAMEDIMENSION);
        setLocation(200,200);
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
        p.anchor = p.WEST;
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
        picAdd.setToolTipText("Rate of polygon addition");
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
        picDel.setToolTipText("Rate of polygon deletion");
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
        picMove.setToolTipText("Rate of polygon movement between layers");
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

        // *************************** SECOND ROW ******************************

        vMa = new JLabel("Vertex Max Move:");
        vMa.setFont(LABELFONT);
        vMa.setHorizontalAlignment(SwingConstants.LEFT);
        p.gridx = 0;
        p.gridy = 2;
        p.insets = new Insets(5,5,0,10);
        settings.add(vMa, p);

        vMi = new JLabel("Vertex Mid Move:");
        vMi.setFont(LABELFONT);
        vMi.setHorizontalAlignment(SwingConstants.LEFT);
        p.gridx = 1;
        p.insets = new Insets(5,0,0,10);
        settings.add(vMi, p);

        vMn = new JLabel("Vertex Min Move:");
        vMn.setFont(LABELFONT);
        vMn.setHorizontalAlignment(SwingConstants.LEFT);
        p.gridx = 2;
        p.insets = new Insets(5,0,0,5);
        settings.add(vMn, p);

        vertMax = new DoubleJSlider(0,100,0,1000);
        vertMax.setToolTipText("Rate of maximal vertex movement");
        vertMax.setPreferredSize(SLIDERDIMENSION);
        vertMax.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                vMa.setText("Vertex Max Move: " + DF.format(vertMax.getScaledValue()));
            }
        });
        vMa.setText("Vertex Max Move: " + DF.format(vertMax.getScaledValue()));
        p.gridx = 0;
        p.gridy = 3;
        p.insets = new Insets(5,5,0,10);
        settings.add(vertMax, p);

        vertMid = new DoubleJSlider(0,100,0,1000);
        vertMid.setToolTipText("Rate of medium vertex movement");
        vertMid.setPreferredSize(SLIDERDIMENSION);
        vertMid.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                vMi.setText("Vertex Mid Move: " + DF.format(vertMid.getScaledValue()));
            }
        });
        vMi.setText("Vertex Mid Move: " + DF.format(vertMid.getScaledValue()));
        p.gridx = 1;
        p.insets = new Insets(5,0,0,10);
        settings.add(vertMid, p);

        vertMin = new DoubleJSlider(0,100,0,1000);
        vertMin.setToolTipText("Rate of minimal vertex movement");
        vertMin.setPreferredSize(SLIDERDIMENSION);
        vertMin.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                vMn.setText("Vertex Min Move: " + DF.format(vertMin.getScaledValue()));
            }
        });
        vMn.setText("Vertex Min Move: " + DF.format(vertMin.getScaledValue()));
        p.gridx = 2;
        p.insets = new Insets(5,0,0,5);
        settings.add(vertMin, p);


        // **************************** THIRD ROW *****************************

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

        polyAdd = new DoubleJSlider(0,100,0,1000);
        polyAdd.setToolTipText("Rate of vertex addition");
        polyAdd.setPreferredSize(SLIDERDIMENSION);
        polyAdd.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                poA.setText("Vertex Addition: " + DF.format(polyAdd.getScaledValue()));
            }
        });
        poA.setText("Vertex Addition: " + DF.format(polyAdd.getScaledValue()));
        p.gridx = 0;
        p.gridy = 5;
        p.insets = new Insets(5,5,0,10);
        settings.add(polyAdd, p);

        polyDel = new DoubleJSlider(0,100,0,1000);
        polyDel.setToolTipText("Rate of vertex deletion");
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

        // *************************** FOURTH ROW ******************************
        /*
        cR = new JLabel("Color Rate:");
        cR.setFont(LABELFONT);
        cR.setHorizontalAlignment(SwingConstants.LEFT);
        p.gridx = 0;
        p.gridy = 6;
        p.insets = new Insets(5,5,0,10);
        settings.add(cR, p);

        cI = new JLabel("Color Intensity:");
        cI.setFont(LABELFONT);
        cI.setHorizontalAlignment(SwingConstants.LEFT);
        p.gridx = 1;
        p.insets = new Insets(5,0,0,10);
        settings.add(cI, p);

        colorRate = new DoubleJSlider(0,100,0,1000);
        colorRate.setToolTipText("Rate of color mutation");
        colorRate.setPreferredSize(SLIDERDIMENSION);
        colorRate.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                cR.setText("Color Rate: " + DF.format(colorRate.getScaledValue()));
            }
        });
        cR.setText("Color Rate: " + DF.format(colorRate.getScaledValue()));
        p.gridx = 0;
        p.gridy = 7;
        p.insets = new Insets(5,5,0,10);
        settings.add(colorRate, p);

        colorInt = new JSlider(JSlider.HORIZONTAL, 10, 255, Settings.colorIntensity);
        colorInt.setToolTipText("Intensity of color mutation");
        colorInt.setPreferredSize(SLIDERDIMENSION);
        colorInt.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                cI.setText("Color Intensity: " + colorInt.getValue());
            }
        });
        cI.setText("Color Intensity: " + colorInt.getValue());
        p.gridx = 1;
        p.insets = new Insets(5,0,0,10);
        settings.add(colorInt, p);

        // *************************** FIFTH ROW *******************************

        aR = new JLabel("Alpha Rate:");
        aR.setFont(LABELFONT);
        aR.setHorizontalAlignment(SwingConstants.LEFT);
        p.gridx = 0;
        p.gridy = 8;
        p.insets = new Insets(5,5,0,10);
        settings.add(aR, p);

        aI = new JLabel("Alpha Intensity:");
        aI.setFont(LABELFONT);
        aI.setHorizontalAlignment(SwingConstants.LEFT);
        p.gridx = 1;
        p.insets = new Insets(5,0,0,10);
        settings.add(aI, p);

        alphaRate = new DoubleJSlider(0,100,0,1000);
        alphaRate.setToolTipText("Rate of transparency mutation");
        alphaRate.setPreferredSize(SLIDERDIMENSION);
        alphaRate.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                aR.setText("Alpha Rate: " + DF.format(alphaRate.getScaledValue()));
            }
        });
        aR.setText("Alpha Rate: " + DF.format(alphaRate.getScaledValue()));
        p.gridx = 0;
        p.gridy = 9;
        p.insets = new Insets(5,5,0,10);
        settings.add(alphaRate, p);

        alphaInt = new JSlider(JSlider.HORIZONTAL, Settings.minColorTransparency,
                Settings.maxColorTransparency, 50);
        alphaInt.setToolTipText("Intensity of transparency mutation");
        alphaInt.setPreferredSize(SLIDERDIMENSION);
        alphaInt.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                aI.setText("Alpha Intensity: " + alphaInt.getValue());
            }
        });
        aI.setText("Alpha Intensity: " + alphaInt.getValue());
        p.gridx = 1;
        p.insets = new Insets(5,0,0,10);
        settings.add(alphaInt, p);
        */
        // *************************** SIXTH ROW *******************************

        iP = new JLabel("Min Polygons:");
        iP.setFont(LABELFONT);
        iP.setHorizontalAlignment(SwingConstants.LEFT);
        p.gridx = 0;
        p.gridy = 6;
        p.insets = new Insets(5,10,0,10);
        settings.add(iP, p);

        mP = new JLabel("Max Polygons:");
        mP.setFont(LABELFONT);
        mP.setHorizontalAlignment(SwingConstants.LEFT);
        p.gridx = 1;
        p.insets = new Insets(5,0,0,10);
        settings.add(mP, p);

        minPoly = new JSlider(JSlider.HORIZONTAL, Settings.MINPOLMIN,
                Settings.MINPOLMAX, Settings.minPolygons);
        minPoly.setToolTipText("Minimum amount of polygons in the picture");
        minPoly.setPreferredSize(SLIDERDIMENSION);
        minPoly.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                iP.setText("Min Polygons: " + minPoly.getValue());
            }
        });
        iP.setText("Min Polygons: " + minPoly.getValue());
        p.gridx = 0;
        p.gridy = 7;
        p.insets = new Insets(5,10,0,10);
        settings.add(minPoly, p);

        maxPoly = new JSlider(JSlider.HORIZONTAL, Settings.MAXPOLMIN,
                Settings.MAXPOLMAX, Settings.maxPolygons);
        maxPoly.setToolTipText("Maximum amount of polygons in the picture");
        maxPoly.setPreferredSize(SLIDERDIMENSION);
        maxPoly.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                mP.setText("Max Polygons: " + maxPoly.getValue());
            }
        });
        mP.setText("Max Polygons: " + maxPoly.getValue());
        p.gridx = 1;
        p.insets = new Insets(5,0,0,10);
        settings.add(maxPoly, p);

        // *************************** SEVENTH ROW *****************************

        iV = new JLabel("Min Vertices:");
        iV.setFont(LABELFONT);
        iV.setHorizontalAlignment(SwingConstants.LEFT);
        p.gridx = 0;
        p.gridy = 8;
        p.insets = new Insets(5,10,0,10);
        settings.add(iV, p);

        mV = new JLabel("Max Vertices:");
        mV.setFont(LABELFONT);
        mV.setHorizontalAlignment(SwingConstants.LEFT);
        p.gridx = 1;
        p.insets = new Insets(5,0,0,10);
        settings.add(mV, p);

        minVert = new JSlider(JSlider.HORIZONTAL, Settings.MINPIMIN,
                Settings.MINPIMAX, Settings.minPolyPoints);
        minVert.setToolTipText("Minimum amount of vertices per polygon");
        minVert.setPreferredSize(SLIDERDIMENSION);
        minVert.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                iV.setText("Min Vertices: " + minVert.getValue());
            }
        });
        iV.setText("Min Vertices: " + minVert.getValue());
        p.gridx = 0;
        p.gridy = 9;
        p.insets = new Insets(5,10,0,10);
        settings.add(minVert, p);

        maxVert = new JSlider(JSlider.HORIZONTAL, Settings.MAXPIMIN,
                Settings.MAXPIMAX, Settings.maxPolyPoints);
        maxVert.setToolTipText("Maximum amount of vertices per polygon");
        maxVert.setPreferredSize(SLIDERDIMENSION);
        maxVert.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                mV.setText("Max Vertices: " + maxVert.getValue());
            }
        });
        mV.setText("Max Vertices: " + maxVert.getValue());
        p.gridx = 1;
        p.insets = new Insets(5,0,0,10);
        settings.add(maxVert, p);


        export = new JButton("Export");
        export.setPreferredSize(new Dimension(BUTTONDIMENSION.width-5,
                BUTTONDIMENSION.height-5));
        export.setFont(BUTTONFONT);
        export.setForeground(Color.BLUE);
        export.addActionListener(this);
        p.gridy = 6;
        p.gridx = 2;
        p.gridheight = 2;
        p.insets = new Insets(0,0,0,5);
        settings.add(export, p);

        imports = new JButton("Import");
        imports.setPreferredSize(new Dimension(BUTTONDIMENSION.width-5,
                BUTTONDIMENSION.height-5));
        imports.setFont(BUTTONFONT);
        imports.setForeground(Color.BLUE);
        imports.addActionListener(this);
        p.gridy = 8;
        settings.add(imports, p);


        /* ********************************************************************
         * ***************** END OF SETTINGS PANEL COMPONENTS *****************
         * ********************************************************************
         */

        g.gridx = g.gridy = 0;
        g.anchor = g.WEST;
        g.gridwidth = 3;
        g.insets = new Insets(5,5,10,5); //Insets - top left bottom right
        add(settings, g);

        reset = new JButton("Reset"); //RESET BUTTON
        reset.setPreferredSize(BUTTONDIMENSION);
        reset.setFont(BUTTONFONT);
        reset.setForeground(Color.BLUE);
        reset.addActionListener(this);
        g.gridy = 1;
        g.gridwidth = 1;
        g.insets = new Insets(0,5,5,10);
        add(reset, g);

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


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == reset) { //CLICKED RESET
            
        }
        else if (e.getSource() == load) { //CLICKED LOAD
            ac = new DisplayGui();
        }
        else if (e.getSource() == quit) { //CLICKED QUIT
            System.exit(0);
        }
        else if (e.getSource() == export) { //CLICKED EXPORT

        }
        else if (e.getSource() == imports) { //CLICKED IMPORT

        }
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
            return ((double) super.getValue()) / this.scale;
        }

        public void setScaledValue(double value) {
            super.setValue((int) (value * this.scale));
        }

    }

}
