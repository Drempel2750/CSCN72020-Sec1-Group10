package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Attributes.Attribute.Attribute;
import Attributes.Attribute.attributeState;
import Components.State;

public class GUI extends JFrame {
    ImageIcon logo = new ImageIcon("NukeIcon.png");
    JLabel background = new JLabel(new ImageIcon("Layout 3.jpg"));
    public LightTextpanel centerpanel;
    ImageIcon controlrod = new ImageIcon("ControlRod_1.png");
    public GUI() {
        this.setTitle("Nuclear Plant Control Panel");
        this.setLayout(null);
        this.setSize(1600, 900);
        this.setContentPane(background);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setIconImage(logo.getImage());
        centerpanel = new LightTextpanel();
        this.add(centerpanel);
        this.setVisible(true);
    }
    public class LightTextpanel extends JPanel {
        public JLabel[] statuslights = new JLabel[5];
        public JLabel[] numfields = new JLabel[11];
        public JLabel rod1 = new JLabel();
        public JLabel rod2 = new JLabel();
        ImageIcon redlight = new ImageIcon("Red_Light_1.jpg");
        ImageIcon greenlight = new ImageIcon("Green_Light_1.jpg");

        public LightTextpanel() {
            this.setLayout(null);
            this.setOpaque(false);
            this.setBounds(0, 0, 1400, 500);
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
                this.add(statuslights[i]);
                statuslights[i].setVisible(true);
            }
            statuslights[0].setBounds(720, 405, 40, 40); // light for water pump
            statuslights[1].setBounds(835, 95, 40, 40); // light for turbine
            statuslights[2].setBounds(925, 255, 40, 40); // light for steam valve 2
            statuslights[3].setBounds(1130, 40, 40, 40); // light for generator
            statuslights[4].setBounds(543, 70, 40, 40); // light for steam valve 1
        }
        public void initControlRods(Attribute a) {
            rod1.setIcon(controlrod);
            rod2.setIcon(controlrod);
            this.add(rod1);
            this.add(rod2);
            rod1.setVisible(true);
            rod2.setVisible(true);
            rod1.setBounds(223, 212 + ((int)a.getValue() * 2), 9, 62);
            rod2.setBounds(242, 212 + ((int)a.getValue() * 2), 9, 62);
        }
        public void rodUp() {
            if (rod1.getY() == 212)
                return;
            rod1.setBounds(223, rod1.getY() - 2, 9, 62);
            rod2.setBounds(242, rod2.getY() - 2, 9, 62);
        }
        public void rodDown() {
            if (rod1.getY() == 272)
                return;
            rod1.setBounds(223, rod1.getY() + 2, 9, 62);
            rod2.setBounds(242, rod2.getY() + 2, 9, 62);
        }
        public void InitNumfields() {
            for (int i = 0; i < 11; i++) {
                numfields[i] = new JLabel();
                numfields[i].setFont(new Font("Tahoma", Font.BOLD, 15));
                numfields[i].setHorizontalAlignment(JLabel.CENTER);
                numfields[i].setVerticalAlignment(JLabel.CENTER);
                numfields[i].setHorizontalTextPosition(JLabel.CENTER);
                numfields[i].setVerticalTextPosition(JLabel.CENTER);
                this.add(numfields[i]);
                numfields[i].setVisible(true);
            }
            numfields[0].setBounds(513, 400, 120, 60); // pump speed text field
            numfields[1].setBounds(752, 35, 120, 60); // turbine speed text field
            numfields[2].setBounds(742, 240, 120, 60); // steam valve 2 pressure text field
            numfields[3].setBounds(1227, 115, 120, 60); // generator output text field
            numfields[4].setBounds(570, 60, 120, 60); // outlet steam pressure text field
            numfields[5].setBounds(838, 35, 120, 60); // turbine breaks text field (add +4 to id to use)
            numfields[6].setBounds(341, 13, 120, 60); // radiation sensor text field
            numfields[7].setBounds(436, 243, 120, 60); // inlet water pressure text field
            numfields[8].setBounds(212, 393, 120, 60); // rod 2 text field
            numfields[9].setBounds(138, 393, 120, 60); // rod 1  text field
            numfields[10].setBounds(35, 170, 120, 60); // core temperature text field

        }
        public void UpdateStatusLight(int ID, State s) {
            if (ID < 0 || ID > 4) { // checking that ID won't go above/below buffer contents
                System.out.println("ERROR: ID value caused buffer under/overflow. Exiting program.");
                System.exit(-1);
            }
            if (s == State.OFF)
                statuslights[ID].setIcon(redlight);
            else
                statuslights[ID].setIcon(greenlight);
        }
        public void updateTextField (int ID, Attribute a, String unit) {
            numfields[ID].setText((a.getValue()) + unit);
            if (a.getState() == attributeState.OK)
                numfields[ID].setForeground(new Color(0x00720B));
            else if (a.getState() == attributeState.WARNING)
                numfields[ID].setForeground(Color.YELLOW);
            else
                numfields[ID].setForeground(Color.RED);
        }
    }
}
