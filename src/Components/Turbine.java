package ScadaBackend.Components;

import ScadaBackend.ComponentAttributes.Breaks.Breaks;
import Attributes.Speed.Speed;
import Attributes.Speed.speedUnit;

import java.util.Scanner;

public class Turbine extends Component {
    private Speed speed;
    private Breaks breaks;
    private final int minAcceptableSpeed = 300;
    private final int maxAcceptableSpeed = 2500;
    private final int minWarningSpeed = 150;
    private final int maxWarningSpeed = 3200;
    private final int minAcceptableBreaks = 20;
    private final int maxAcceptableBreaks = 80;
    private final int minWarningBreaks = 10;
    private final int maxWarningBreaks = 90;
    public Turbine(int ID) {
        super(State.ON, ID);
        speed = new Speed(0, speedUnit.RPM,  minAcceptableSpeed, maxAcceptableSpeed, minWarningSpeed, maxWarningSpeed);
        breaks = new Breaks(30, minAcceptableBreaks, maxAcceptableBreaks, minWarningBreaks, maxWarningBreaks);
        speed.updateState();
        breaks.updateState();
    }
    public Speed getSpeed() {
        return speed;
    }
    public void setSpeed(Speed speed) {
        this.speed = speed;
    }

    public void setBreaks(Breaks breaks) {
        this.breaks = breaks;
    }
    public Breaks getBreaks() {
        return this.breaks;
    }
    public void incBreaks() {
        if (this.breaks.getValue() == maxWarningBreaks + 10)
            return;
        this.breaks.setValue(this.breaks.getValue() + 5);
        this.breaks.updateState();
    }
    public void decBreaks() {
        if (this.breaks.getValue() == minWarningBreaks - 10)
            return;
        this.breaks.setValue(this.breaks.getValue() - 5);
        this.breaks.updateState();
    }
    @Override
    public void getComponentData(final Scanner scanner) {
        if (super.getState() == State.OFF) {
            speed.setValue(0);
        }
        else
            speed.updateAttributeFromFile(scanner);
        speed.updateState();
    }
}

