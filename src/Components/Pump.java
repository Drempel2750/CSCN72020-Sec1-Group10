package Components;

import Attributes.Attribute.Attribute;
import Attributes.Volume.Volume;
import Attributes.Volume.volumeUnit;

import java.io.FileNotFoundException;
import java.lang.annotation.Native;
import java.util.Scanner;

public class Pump extends Component {
    private Volume pumpVolume;
    private float prevVolume;
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
        if (super.getState() == State.OFF)
            return;
        if (this.pumpVolume.getValue() == maxWarningVolume + 5)
            return;
        this.pumpVolume.setValue(this.pumpVolume.getValue() + 1);
        pumpVolume.updateState();
    }

    public Attribute getPumpVolume() {
        if (super.getState() == State.OFF) {
            if (this.pumpVolume.getValue() != 0)
                this.prevVolume = pumpVolume.getValue();
            this.pumpVolume.setValue(0);
        }
        else if (this.pumpVolume.getValue() == 0 && super.getState() == State.ON)
            this.pumpVolume.setValue(prevVolume);
        this.pumpVolume.updateState();
        return this.pumpVolume;

    }
    public void decVolume() {
        if (super.getState() == State.OFF)
            return;
        if (this.pumpVolume.getValue() == 0)
            return;
        this.pumpVolume.setValue(this.pumpVolume.getValue() - 1);
        pumpVolume.updateState();
    }
    public void setPumpVolume(Volume pumpVolume) {
        this.pumpVolume = pumpVolume;
        this.pumpVolume.updateState();
    }

    @Override
    public void getComponentData(final Scanner scanner) {
        if (super.getState() == State.OFF) {
            pumpVolume.setValue(0);
        }
        else
            pumpVolume.readAttribute(scanner);
        pumpVolume.updateState();
    }
}

