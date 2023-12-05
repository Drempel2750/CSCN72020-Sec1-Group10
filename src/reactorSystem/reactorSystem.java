package reactorSystem;

import Attributes.Attribute.*;
import Attributes.Depth.depthUnit;
import Attributes.Pressure.pressureUnit;
import Attributes.Quality.Quality;
import Attributes.Speed.speedUnit;
import Attributes.Temperature.tempUnit;
import Attributes.Volume.volumeUnit;
import Components.*;
import FileHandling.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import GUI.*;

import javax.naming.ldap.Control;
import javax.swing.*;


public class reactorSystem {
    private GUI gui;
    public Controlpanel bottompanel;
    public SidePanel sidepanel;
    //components
    protected final controlRod rod;
    protected final pressureSensor gauge1, gauge2, gauge3;
    protected final Pump pump;
    protected final Valve valve1, valve2;
    protected final Generator generator;
    protected final radiationSensor radSensor;
    protected final temperatureSensor tempSensor;
    protected final Turbine turbine;
    protected final qualitySensor qsensor;

    //character streams of component attribute values
    private Scanner pressureScanner, pressureScanner2, pressureScanner3;
    private Scanner radScanner;
    private Scanner reactorScanner;
    private Scanner turbineScanner;
    private Scanner generatorScanner;
    private Scanner qualityScanner;

    //bool to check whether user has paused the simulation
    protected boolean simstate;


    public reactorSystem(GUI gui) throws FileNotFoundException {
        this.gui = gui;
        this.bottompanel = new Controlpanel();
        gui.add(bottompanel);
        this.sidepanel = new SidePanel();
        gui.add(sidepanel);
        rod = new controlRod(8);
        gauge1 = new pressureSensor(7, 20, 40, 10, 50);
        gauge2 = new pressureSensor(4);
        gauge3 = new pressureSensor(2);
        valve1 = new Valve(5);
        valve2 = new Valve(9);
        pump = new Pump(0);
        generator = new Generator(3);
        radSensor = new radiationSensor(6);
        tempSensor = new temperatureSensor(10);
        turbine = new Turbine(1);
        qsensor = new qualitySensor(11);
        gui.centerpanel.initControlRods(rod.getDepth());
        simstate = true; // start the application with the simulation paused
    }
    public void openScanners() throws FileNotFoundException {

        pressureScanner = FileHandling.FileScanner("pressureFile.txt");
        pressureScanner2 = FileHandling.FileScanner("pressureFile2.txt");
        pressureScanner3 = FileHandling.FileScanner("pressureFile3.txt");
        radScanner = FileHandling.FileScanner("radSensor.txt");
        reactorScanner = FileHandling.FileScanner("reactorFile.txt");
        turbineScanner = FileHandling.FileScanner("turbineFile.txt");
        generatorScanner = FileHandling.FileScanner("generatorFile.txt");
        qualityScanner = FileHandling.FileScanner("qualityFile.txt");
    }
    public void closeAllScanners()
    {
        pressureScanner.close();
        pressureScanner2.close();
        pressureScanner3.close();
        radScanner.close();
        reactorScanner.close();
        turbineScanner.close();
        generatorScanner.close();
        qualityScanner.close();
    }

    public void readAllComponents()
    {
        gauge1.getComponentData(pressureScanner);
        gauge2.getComponentData(pressureScanner2);
        gauge3.getComponentData(pressureScanner3);
        radSensor.getComponentData(radScanner);
        tempSensor.getComponentData(reactorScanner);
        turbine.getComponentData(turbineScanner);
        generator.getComponentData(generatorScanner);
        qsensor.getComponentData(qualityScanner);
    }
    public void updateLights() {
        gui.centerpanel.UpdateStatusLight(0, pump.getState());
        gui.centerpanel.UpdateStatusLight(1, turbine.getState());
        gui.centerpanel.UpdateStatusLight(2, valve2.getState());
        gui.centerpanel.UpdateStatusLight(3, generator.getState());
        gui.centerpanel.UpdateStatusLight(4, valve1.getState());
    }
    public void printAllComponentAttributes() {
        if (rod.getDepth().getUnit() == depthUnit.INCHES) {
            gui.centerpanel.updateTextField(rod.getID(), rod.getDepth(), " IN");
            gui.centerpanel.updateTextField(rod.getID()+1, rod.getDepth(), " IN");
        }
        else {
            gui.centerpanel.updateTextField(rod.getID(), rod.getDepth(), " CM");
            gui.centerpanel.updateTextField(rod.getID()+1, rod.getDepth(), " CM");
        }
        if (gauge1.getPressure().getUnit() == pressureUnit.PSI) {
            gui.centerpanel.updateTextField(gauge1.getID(), gauge1.getPressure(), " PSI");
            gui.centerpanel.updateTextField(gauge2.getID(), gauge2.getPressure(), " PSI");
            gui.centerpanel.updateTextField(gauge3.getID(), gauge3.getPressure(), " PSI");
        }
        else {
            gui.centerpanel.updateTextField(gauge1.getID(), gauge1.getPressure(), " BAR");
            gui.centerpanel.updateTextField(gauge2.getID(), gauge2.getPressure(), " BAR");
            gui.centerpanel.updateTextField(gauge3.getID(), gauge3.getPressure(), " BAR");
        }
        if (tempSensor.getTemperature().getUnit() == tempUnit.CELSIUS)
            gui.centerpanel.updateTextField(tempSensor.getID(), tempSensor.getTemperature(), " C");
        else
            gui.centerpanel.updateTextField(tempSensor.getID(), tempSensor.getTemperature(), " F");
        gui.centerpanel.updateTextField(radSensor.getID(), radSensor.getRadiation(), " mSv");
        gui.centerpanel.updateTextField(generator.getID(), generator.getPower(), " kW");
        gui.centerpanel.updateTextField(turbine.getID(), turbine.getSpeed(), " RPM");
        gui.centerpanel.updateTextField(turbine.getID()+4, turbine.getBreaks(), "%");
        gui.centerpanel.updateTextField(pump.getID(), pump.getPumpVolume(), " L/s");
        gui.centerpanel.updateTextField(qsensor.getID(), qsensor.getQuality(), " PPM");
    }
    public boolean isPaused() {
        return this.simstate;
    }
    public class Controlpanel extends JPanel implements ActionListener {
        public JButton[] buttons = new JButton[12];
        Controlpanel() {
            this.setLayout(null);
            this.setBounds(0, 500, 1400, 400);
            this.setOpaque(false);
            this.setVisible(true);
            for (int i = 0; i < 12; i++) {
                buttons[i] = new JButton();
                buttons[i].setForeground(Color.WHITE);
                buttons[i].setBackground(new Color(0x000C72));
                buttons[i].setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));
                buttons[i].setFont(new Font("Tahoma", Font.BOLD, 16));
                buttons[i].addActionListener(this);
                this.add(buttons[i]);
                buttons[i].setVisible(true);
            }
            buttons[0].setBounds(100, 25, 125, 70); //rod up button
            buttons[0].setText("Rod Up");
            buttons[1].setBounds(250, 25, 125, 70); //rod down button
            buttons[1].setText("Rod Down");
            buttons[2].setBounds(370, 250, 125, 70); //pump up button
            buttons[2].setText("Vol. Up");
            buttons[3].setBounds(505, 250, 125, 70); //pump up button
            buttons[3].setText("Vol. Down");
            buttons[4].setBounds(720, 250, 125, 70); //turbine breaks up button
            buttons[4].setText("Breaks Up");
            buttons[5].setBounds(855, 250, 125, 70); //turbine breaks down button
            buttons[5].setText("Breaks Down");
            buttons[6].setBounds(1050, 0, 125, 70); //steam valve 1 button
            buttons[6].setText("OPEN");
            buttons[6].setForeground(Color.GREEN);
            buttons[7].setBounds(1050, 100, 125, 70); //steam valve 2 button
            buttons[7].setText("OPEN");
            buttons[7].setForeground(Color.GREEN);
            buttons[8].setBounds(1250, 0, 125, 70); //pump state button
            buttons[8].setText("PUMP OFF");
            buttons[8].setForeground(Color.RED);
            buttons[9].setBounds(1250, 100, 125, 70); //turbine state button
            buttons[9].setText("TURBINE OFF");
            buttons[9].setForeground(Color.RED);
            buttons[10].setBounds(1250, 200, 125, 70); //generator state button
            buttons[10].setText("GEN OFF");
            buttons[10].setForeground(Color.RED);
            buttons[11].setBounds(50, 150, 175, 125); // SCRAM shutdown button
            buttons[11].setBackground(new Color(0xA10003));
            buttons[11].setForeground(new Color(0xFFFFFF));
            buttons[11].setFont(new Font("Tahoma", Font.BOLD, 28));
            buttons[11].setText("SCRAM");
        }
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == buttons[0]) {
                rod.incDepth();
                gui.centerpanel.rodUp();
            }
            else if (e.getSource() == buttons[1]) {
                rod.decDepth();
                gui.centerpanel.rodDown();
            }
            else if (e.getSource() == buttons[2])
                pump.incVolume();
            else if (e.getSource() == buttons[3])
                pump.decVolume();
            else if (e.getSource() == buttons[4])
                turbine.incBreaks();
            else if (e.getSource() == buttons[5])
                turbine.decBreaks();
            else if (e.getSource() == buttons[6]) {
                if (valve1.getState() == State.ON) {
                    valve1.turnOff();
                    buttons[6].setText("OPEN");
                    buttons[6].setForeground(Color.GREEN);
                }
                else {
                    valve1.turnOn();
                    buttons[6].setText("CLOSE");
                    buttons[6].setForeground(Color.RED);
                }
            }
            else if (e.getSource() == buttons[7]) {
                if (valve2.getState() == State.ON) {
                    valve2.turnOff();
                    buttons[7].setText("OPEN");
                    buttons[7].setForeground(Color.GREEN);
                }
                else {
                    valve2.turnOn();
                    buttons[7].setText("CLOSE");
                    buttons[7].setForeground(Color.RED);
                }
            }
            else if (e.getSource() == buttons[8]) {
                if (pump.getState() == State.ON) {
                    pump.turnOff();
                    buttons[8].setText("PUMP ON");
                    buttons[8].setForeground(Color.GREEN);
                }
                else {
                    pump.turnOn();
                    buttons[8].setText("PUMP OFF");
                    buttons[8].setForeground(Color.RED);
                }
            }
            else if (e.getSource() == buttons[9]) {
                if (turbine.getState() == State.ON) {
                    turbine.turnOff();
                    buttons[9].setText("TURBINE ON");
                    buttons[9].setForeground(Color.GREEN);
                }
                else {
                    turbine.turnOn();
                    buttons[9].setText("TURBINE OFF");
                    buttons[9].setForeground(Color.RED);
                }
            }
            else if (e.getSource() == buttons[10]) {
                if (generator.getState() == State.ON) {
                    generator.turnOff();
                    buttons[10].setText("GEN ON");
                    buttons[10].setForeground(Color.GREEN);
                }
                else {
                    generator.turnOn();
                    buttons[10].setText("GEN OFF");
                    buttons[10].setForeground(Color.RED);
                }
            }
            else if (e.getSource() == buttons[11]) {
                pump.turnOn();
                buttons[8].setText("PUMP OFF");
                buttons[8].setForeground(Color.RED);
                valve2.turnOn();
                buttons[7].setText("CLOSE");
                buttons[7].setForeground(Color.RED);
                valve1.turnOn();
                buttons[6].setText("CLOSE");
                buttons[6].setForeground(Color.RED);
                for (int i = 0; i < 30; i++) {
                    rod.incDepth();
                    gui.centerpanel.rodUp();
                    turbine.incBreaks();
                    pump.incVolume();
                }
            }
        }
    }
    public class SidePanel extends JPanel implements ActionListener {
        public JButton[] unitcontrol = new JButton[2];
        public JButton simcontrol = new JButton();
        public JLabel simcounter = new JLabel();
        public SidePanel() {
            this.setLayout(null);
            this.setBounds(1400, 0, 200, 900);
            this.setOpaque(false);
            this.setVisible(true);
            for (int i = 0; i < 2; i++) {
                unitcontrol[i] = new JButton();
                unitcontrol[i].setForeground(Color.WHITE);
                unitcontrol[i].setBackground(new Color(0x003612));
                unitcontrol[i].setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));
                unitcontrol[i].setFont(new Font("Tahoma", Font.BOLD, 16));
                unitcontrol[i].addActionListener(this);
                this.add(unitcontrol[i]);
                unitcontrol[i].setVisible(true);
            }
            unitcontrol[0].setBounds(0, 300, 125, 70);
            unitcontrol[0].setText("Fahrenheit");
            unitcontrol[1].setBounds(0, 450, 125, 70);
            unitcontrol[1].setText("Barometric");
            simcontrol.setForeground(Color.WHITE);
            simcontrol.setBackground(new Color(0x520002));
            simcontrol.setFont(new Font("Tahoma", Font.BOLD, 16));
            simcontrol.addActionListener(this);
            this.add(simcontrol);
            simcontrol.setVisible(true);
            simcontrol.setBounds(0, 100, 125, 70);
            simcontrol.setText("START");
            this.add(simcounter);
            simcounter.setForeground(Color.BLACK);
            simcounter.setFont(new Font("Gotham", Font.ITALIC, 16));
            simcounter.setText("0 / 240");
            simcounter.setBounds(30, 10, 100, 75);
            simcounter.setVisible(true);
        }
        public void setSimcounter(int num) {
            simcounter.setText(num + " / 220");
        }
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == unitcontrol[0]) {
                if (tempSensor.getTemperature().getUnit() == tempUnit.CELSIUS) {
                    unitcontrol[0].setText("Celsius");
                    tempSensor.setUnit(tempUnit.FAHRENHEIT);
                }
                else {
                    unitcontrol[0].setText("Fahrenheit");
                    tempSensor.setUnit(tempUnit.CELSIUS);
                }
            }
            else if (e.getSource() == unitcontrol[1]) {
                if (gauge1.getPressure().getUnit() == pressureUnit.PSI) {
                    unitcontrol[1].setText("PSI");
                    gauge1.setUnit(pressureUnit.BAR);
                    gauge2.setUnit(pressureUnit.BAR);
                    gauge3.setUnit(pressureUnit.BAR);
                }
                else {
                    unitcontrol[1].setText("BAR");
                    gauge1.setUnit(pressureUnit.PSI);
                    gauge2.setUnit(pressureUnit.PSI);
                    gauge3.setUnit(pressureUnit.PSI);
                }
            }
            else {
                if (!simstate) {
                    simstate = true;
                    simcontrol.setText("START");
                }
                else {
                    simstate = false;
                    simcontrol.setText("STOP");
                }
            }
        }

    }
}