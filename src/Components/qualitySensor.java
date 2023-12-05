package Components;

import Attributes.Pressure.Pressure;
import Attributes.Pressure.pressureUnit;
import Attributes.Quality.Quality;

import java.util.Scanner;

public class qualitySensor extends Component {
    protected Quality quality;
    private final float minAcceptableQuality= 0.0f;
    private final float maxAcceptableQuality = 30.0f;
    private final float minWarningQuality = 30.1f;
    private final float maxWarningQuality = 100.0f;
    public qualitySensor(int ID) {
        super(State.OFF, ID);
        quality = new Quality(0, minAcceptableQuality, maxAcceptableQuality, minWarningQuality, maxWarningQuality);
        quality.updateState();
    }
    public qualitySensor(int ID, float minACC, float maxACC, float minWAR, float maxWAR) {
        super(State.OFF, ID);
        quality = new Quality(0, minACC, maxACC, minWAR, maxWAR);
        quality.updateState();
    }
    public Quality getQuality() {return this.quality;}
    @Override
    public void getComponentData(final Scanner scanner) {
        quality.updateAttributeFromFile(scanner);
        quality.updateState();
    }
}
