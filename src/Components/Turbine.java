package Components;

import java.util.Scanner;

public class Turbine extends Component {
    private Speed speed;
    private final int minAcceptableSpeed = 300;
    private final int maxAcceptableSpeed = 2500;
    private final int minWarningSpeed = 150;
    private final int maxWarningSpeed = 3200;
    public Turbine(int ID) {
        super(State.OFF, ID);
        speed = new Speed(0, speedUnit.RPM,  minAcceptableSpeed, maxAcceptableSpeed, minWarningSpeed, maxWarningSpeed);
    }
    public Speed getSpeed() {
        return speed;
    }
    public void setSpeed(Speed speed) {
        this.speed = speed;
    }

    @Override
    public void getComponentData(final Scanner scanner) {
        speed.updateAttributeFromFile(scanner);
        updateState(speed);
    }
}
