package GUI;

import javax.imageio.metadata.IIOMetadata;
import javax.swing.*;

public class GUI {
    JFrame window;
    ImageIcon logo;
    public GUI() {
        window = new JFrame();
        window.setTitle("Chernobyl Control Panel");
        window.setSize(1600, 900);
        window.setResizable(false);
        window.setVisible(true);
        logo = new ImageIcon("NukeIcon.png");
        window.setIconImage(logo.getImage());
    }





}
