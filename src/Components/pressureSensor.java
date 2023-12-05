package Components;

import Attributes.Pressure.Pressure;
import Attributes.Pressure.pressureUnit;

import java.util.Scanner;

public class pressureSensor extends Component {
    Pressure pressure;
    private final float minAcceptablePressure = 50;
    private final float maxAcceptablePressure = 100;
    private final float minWarningPressure = 30;
    private final float maxWarningPressure = 150;
    public pressureSensor(int ID) {
        super(State.OFF, ID);
        pressure = new Pressure(0, pressureUnit.PSI, minAcceptablePressure, maxAcceptablePressure, minWarningPressure, maxWarningPressure);
        pressure.updateState();
    }
    public pressureSensor(int ID, float minACC, float maxACC, float minWAR, float maxWAR) {
        super(State.OFF, ID);
        pressure = new Pressure(0, pressureUnit.PSI, minACC, maxACC, minWAR, maxWAR);
        pressure.updateState();
    }
    public Pressure getPressure() {
        return pressure;
    }
    public void setUnit(pressureUnit unit) {
        this.pressure.setUnit(unit);
    }
    public void setPressure(final Pressure pressure) {
        this.pressure = pressure;
    }
    @Override
    public void getComponentData(final Scanner scanner) {
        pressure.readAttribute(scanner);
        pressure.updateState();
    }
}

