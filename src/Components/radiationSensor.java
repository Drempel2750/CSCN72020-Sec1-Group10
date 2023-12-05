package Components;

import Attributes.Radiation.Radiation;
import Attributes.Radiation.radiationUnit;

import java.util.Scanner;

public class radiationSensor extends Component {
    private Radiation radiation;
    private final float minAcceptableRadiation = 0f;
    private final float maxAcceptableRadiation = 10.2f;
    private final float minWarningRadiation = 10.21f;
    private final float maxWarningRadiation = 25.5f;
    public radiationSensor(int ID) {
        super(State.OFF, ID);
        radiation = new Radiation(0, radiationUnit.MILLISIEVERT, minAcceptableRadiation, maxAcceptableRadiation, minWarningRadiation, maxWarningRadiation);
        radiation.updateState();
    }

    public Radiation getRadiation() {
        return radiation;
    }

    @Override
    public void getComponentData(final Scanner scanner) {
        radiation.readAttribute(scanner);
        radiation.updateState();
    }
}
