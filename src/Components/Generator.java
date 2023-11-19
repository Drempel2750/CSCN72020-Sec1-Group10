package Components;

import java.util.Scanner;

public class Generator extends Component {
    private Power power;
    private final float minAcceptablePower = 100;
    private final float maxAcceptablePower = 1000;
    private final float minWarningPower = 0;
    private final float maxWarningPower = 1500;
    public Generator(int ID)
    {
        super(State.OFF, ID);
        power = new Power(0, minAcceptablePower, maxAcceptablePower, minWarningPower, maxWarningPower);
    }
    @Override
    public void getComponentData(final Scanner scanner) {
        power.updateAttributeFromFile(scanner);
        updateState(power);
    }
    public Power getPower() {
        return power;
    }
    public void setPower(final Power power) {
        this.power = power;
    }

}
