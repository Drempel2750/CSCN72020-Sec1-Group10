package Components;

import java.util.Scanner;

public class pressureSensor extends Component {
    private Pressure pressure;
    private final float minAcceptablePressure = 50;
    private final float maxAcceptablePressure = 200;
    private final float minWarningPressure = 20;
    private final float maxWarningPressure = 500;
    public pressureSensor(int ID) {
        super(State.OFF, ID);
        pressure = new Pressure(0, pressureUnit.PSI, minAcceptablePressure, maxAcceptablePressure, minWarningPressure, maxWarningPressure);
    }
    public Pressure getPressure() {
        return pressure;
    }
    public void setPressure(final Pressure pressure) {
        this.pressure = pressure;
    }
    @Override
    public void getComponentData(final Scanner scanner) {
        pressure.updateAttributeFromFile(scanner);
        updateState(pressure);
    }
}
