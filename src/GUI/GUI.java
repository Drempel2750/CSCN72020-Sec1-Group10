package GUI;
import Abstract_Classes.*;
import Components.*;

import javax.imageio.metadata.IIOMetadata;
import javax.swing.*;

public class GUI {
    private JFrame window;
    ImageIcon redlight = new ImageIcon(".png");
    ImageIcon greenlight = new ImageIcon("GreenLight.png");
    public JLabel StatusLights[] = new JLabel[9];
    public GUI() {
        window = new JFrame();
        window.setTitle("Chernobyl Control Panel");
        window.setSize(1600, 900);
        window.setResizable(false);
        window.setVisible(true);
        ImageIcon logo = new ImageIcon("NukeIcon.png");
        window.setIconImage(logo.getImage());
        InitStatusIndicators();
    }
    public void InitStatusIndicators() {
        for (int i = 0; i < 9; i++) {
            StatusLights[i].setIcon
        }
    }




}
