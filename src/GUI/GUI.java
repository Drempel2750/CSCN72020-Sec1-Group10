package GUI;

import javax.swing.*;
import java.awt.*;

public class GUI {
    protected JFrame window;
    ImageIcon logo = new ImageIcon("NukeIcon.png");
    JLabel background = new JLabel(new ImageIcon("Layout 2.jpg"));
    ImageIcon redlight = new ImageIcon("Red_Light_1.jpg");
    ImageIcon greenlight = new ImageIcon("Green_Light_1.jpg");
    ImageIcon controlrod = new ImageIcon("ControlRod_1.png");
    public JLabel[] statuslights = new JLabel[5];
    public JLabel[] numfields = new JLabel[11];
    public GUI() {
        window = new JFrame();
        window.setTitle("Nuclear Plant Control Panel");
        window.setSize(1600, 900);
        window.setContentPane(background);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);
        window.setIconImage(logo.getImage());





        InitStatuslights();
        InitNumfields();
    }
    public void InitStatuslights() {
        Image newredlight = redlight.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT); // rescaling red and green lights to fit properly
        redlight.setImage(newredlight);
        Image newgreenlight = greenlight.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        greenlight.setImage(newgreenlight);
        for (int i = 0; i < 5; i++) {
            statuslights[i] = new JLabel(); // initalizing array of JLables and setting lights to red
            statuslights[i].setIcon(redlight);
            window.add(statuslights[i]);
            statuslights[i].setVisible(true);
        }
        statuslights[0].setBounds(720, 405, 40, 40); // light for water pump
        statuslights[1].setBounds(835, 95, 40, 40); // light for turbine
        statuslights[2].setBounds(925, 255, 40, 40); // light for steam valve 2
        statuslights[3].setBounds(1130, 40, 40, 40); // light for generator
        statuslights[4].setBounds(543, 70, 40, 40); // light for steam valve 1

    }
    public void InitNumfields() {
        for (int i = 0; i < 11; i++) {
            numfields[i] = new JLabel();
            numfields[i].setFont(new Font("Tahoma", Font.BOLD, 16));
            numfields[i].setHorizontalAlignment(JLabel.CENTER);
            numfields[i].setVerticalAlignment(JLabel.CENTER);
            numfields[i].setHorizontalTextPosition(JLabel.CENTER);
            numfields[i].setVerticalTextPosition(JLabel.CENTER);
            window.add(numfields[i]);
            numfields[i].setVisible(true);
        }
        numfields[0].setBounds(341, 13, 120, 60); // radiation sensor text field
        numfields[1].setBounds(570, 60, 120, 60); // outlet steam pressure text field
        numfields[2].setBounds(752, 35, 120, 60); // turbine speed text field
        numfields[3].setBounds(838, 35, 120, 60); // turbine breaks text field
        numfields[4].setBounds(1227, 115, 120, 60); // generator output text field
        numfields[5].setBounds(742, 240, 120, 60); // steam valve 2 pressure text field
        numfields[6].setBounds(513, 400, 120, 60); // pump speed text field
        numfields[7].setBounds(436, 243, 120, 60); // inlet water pressure text field
        numfields[8].setBounds(212, 393, 120, 60); // rod 2 text field
        numfields[9].setBounds(138, 393, 120, 60); // rod 1  text field
        numfields[10].setBounds(35, 170, 120, 60); // core temperature text field

    }
    public void UpdateStatusLight(int ID) {
        if (ID < 0 || ID > 5) { // checking that ID won't go above/below buffer contents
            System.out.println("ERROR: ID value caused buffer under/overflow. Exiting program.");
            System.exit(-1);
        }
        if (statuslights[ID].getIcon()==redlight) { // replacing red light with green
            statuslights[ID].setVisible(false);
            statuslights[ID].setIcon(greenlight);
            statuslights[ID].setVisible(true);
        }
        else {
            statuslights[ID].setVisible(false); // replacing green light with red
            statuslights[ID].setIcon(redlight);
            statuslights[ID].setVisible(true);
        }
    }




}
