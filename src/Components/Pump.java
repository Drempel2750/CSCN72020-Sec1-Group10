package ScadaBackend.Components;

import ScadaBackend.ComponentAttributes.Volume.Volume;
import ScadaBackend.ComponentAttributes.Volume.volumeUnit;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Pump extends Component {
    private Volume pumpVolume;
    private final float minAcceptableVolume = 10;
    private final float maxAcceptableVolume = 15;
    private final float minWarningVolume = 5;
    private final float maxWarningVolume = 20;

    public Pump(int ID) {
        super(State.ON, ID);
        pumpVolume = new Volume(12, volumeUnit.LITRE, minAcceptableVolume, maxAcceptableVolume, minWarningVolume, maxWarningVolume);
        pumpVolume.updateState();
    }
    public void incVolume() {
        if (this.pumpVolume.getValue() == maxWarningVolume + 5)
            return;
        this.pumpVolume.setValue(this.pumpVolume.getValue() + 1);
        pumpVolume.updateState();
    }
    public void decVolume() {
        if (this.pumpVolume.getValue() == 0)
            return;
        this.pumpVolume.setValue(this.pumpVolume.getValue() - 1);
        pumpVolume.updateState();
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
        pumpVolume.updateState();
    }
}

