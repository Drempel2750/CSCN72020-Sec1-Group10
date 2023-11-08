package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    ImageIcon logo = new ImageIcon("NukeIcon.png");
    JLabel background = new JLabel(new ImageIcon("Layout 2.jpg"));
    public LightTextpanel centerpanel;
    //public Controlpanel bottompanel;

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
        //bottompanel = new Controlpanel();
        //this.add(bottompanel);
        this.setVisible(true);
    }

    public class LightTextpanel extends JPanel {
        public JLabel[] statuslights = new JLabel[5];
        public JLabel[] numfields = new JLabel[11];
        ImageIcon redlight = new ImageIcon("Red_Light_1.jpg");
        ImageIcon greenlight = new ImageIcon("Green_Light_1.jpg");

        public LightTextpanel() {
            setLayout(null);
            setOpaque(false);
            setBounds(0, 0, 1400, 500);
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
                this.add(statuslights[i]);
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
                numfields[i].setText("TEST");
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
        public void UpdateStatusLight(int ID) {
            if (ID < 0 || ID > 4) { // checking that ID won't go above/below buffer contents
                System.out.println("ERROR: ID value caused buffer under/overflow. Exiting program.");
                System.exit(-1);
            }
            if (statuslights[ID].getIcon() == redlight) { // replacing red light with green
                statuslights[ID].setVisible(false);
                statuslights[ID].setIcon(greenlight);
                statuslights[ID].setVisible(true);
            } else {
                statuslights[ID].setVisible(false); // replacing green light with red
                statuslights[ID].setIcon(redlight);
                statuslights[ID].setVisible(true);
            }
        }
    }
    /*public class Controlpanel extends JPanel /*implements ActionListener*/ //{
        /*public JButton[] buttons = new JButton[11];
        Controlpanel() {
            for (int i = 0; i < 11; i++) {
                setLayout(null);
                setBounds(0, 500, 1400, 400);
                setOpaque(false);
                setVisible(true);
                buttons[i] = new JButton("0");
                buttons[i].setFont(new Font("Tahoma", Font.BOLD, 16));
            }




        }


    }*/



}
