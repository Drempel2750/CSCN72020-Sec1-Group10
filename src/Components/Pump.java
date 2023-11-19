package Components;

import java.util.Scanner;

public class Pump extends Component {
    private Volume pumpVolume;
    private final float minAcceptableVolume = 25;
    private final float maxAcceptableVolume = 75;
    private final float minWarningVolume = 15;
    private final float maxWarningVolume = 95;

    public Pump(int ID) {
        super(State.OFF, ID);
        pumpVolume = new Volume(0, volumeUnit.LITRE, minAcceptableVolume, maxAcceptableVolume, minWarningVolume, maxWarningVolume);
    }
    public void setPumpVolume(Volume pumpVolume) {
        this.pumpVolume = pumpVolume;
    }
    public Volume getPumpVolume() {
        return pumpVolume;
    }
    @Override
    public void getComponentData(final Scanner scanner) {
        pumpVolume.updateAttributeFromFile(scanner);
        updateState(pumpVolume);
    }
}
