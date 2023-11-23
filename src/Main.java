import reactorSystem.reactorSystem;
import java.io.FileNotFoundException;
import GUI.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        GUI gui = new GUI();
        reactorSystem system = new reactorSystem(gui);
        int loopcount = 0;
        for (int i = 0; i <= 10; i++) {

            system.printAllComponentAttributes();
            system.updateLights();
            if (i == 10) {
                system.readAllComponents();
                i = 0;
                loopcount++;
                if (loopcount == 30) {
                    i = 11;
                }
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        system.closeAllScanners();
        System.out.println("Done!");

    }
}
