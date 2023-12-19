import reactorSystem.reactorSystem;
import java.io.FileNotFoundException;
import GUI.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        GUI gui = new GUI();
        reactorSystem system = new reactorSystem(gui);
        boolean run = true;
        while(run) {
            int loopcount = 0;
            system.openScanners();
            for (int i = 0; i <= 10;) {
                system.printAllComponentAttributes();
                system.updateLights();
                if (!system.isPaused()) {
                    if (i == 10) {
                        system.readAllComponents();
                        i = 0;
                        loopcount++;
                        system.sidepanel.setSimcounter(loopcount);
                        if (loopcount == 220) {
                            i = 11;
                        }
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    i++;
                }
            }
            system.closeAllScanners();
        }
    }
}
