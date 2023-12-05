package Components;

import Attributes.Attribute.*;
import Attributes.Power.*;

import java.io.FileNotFoundException;
import java.util.Scanner;
public class Generator extends Component {
    private Power power;
    private final float minAcceptablePower = 100;
    private final float maxAcceptablePower = 750;
    private final float minWarningPower = 0.1f;
    private final float maxWarningPower = 1500;
    public Generator(int ID)
    {
        super(State.ON, ID);
        power = new Power(0, minAcceptablePower, maxAcceptablePower, minWarningPower, maxWarningPower);
        power.updateState();
    }
    @Override
    public void getComponentData(final Scanner scanner) {
        if (super.getState() == State.OFF) {
            power.setValue(0);
        }
        else
            power.readAttribute(scanner);
        power.updateState();
    }
    public Power getPower() {
        return power;
    }
    public void setPower(final Power power) {
        this.power = power;
    }

}


