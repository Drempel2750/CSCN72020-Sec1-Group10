package Attributes.Depth;

import Attributes.Attribute.Attribute;
import ScadaBackend.FileHandling.FileHandling;

public class Depth extends Attribute {
    private depthUnit unit;
    public Depth(final float value, final depthUnit unit, float minAcceptable, final float maxAcceptable, final float minWarning, final float maxWarning) {
        super(value, minAcceptable, maxAcceptable, minWarning, maxWarning);
        this.unit = unit;
    }
    public Depth(final Attribute attribute, final depthUnit unit)
    {
        super(attribute);
        this.unit = unit;
    }
    public depthUnit getUnit() {
        return unit;
    }
    public void setUnit(final depthUnit unit) {
        convertBetween(this.unit, unit);
        this.unit = unit;
    }
    public void convertBetween(final depthUnit startingUnit, final depthUnit endingUnit)
    {
        if (startingUnit == endingUnit)
            return;
        if (endingUnit == depthUnit.INCHES) {
            setValue(cmToInches(getValue()));
            setMaxAcceptable(cmToInches(getMaxAcceptable()));
            setMinAcceptable(cmToInches(getMinAcceptable()));
            setMinWarning(cmToInches(getMinWarning()));
            setMaxWarning(cmToInches(getMaxWarning()));
        }
        else {
            setValue(inchesToCm(getValue()));
            setMaxAcceptable(inchesToCm(getMaxAcceptable()));
            setMinAcceptable(inchesToCm(getMinAcceptable()));
            setMinWarning(inchesToCm(getMinWarning()));
            setMaxWarning(inchesToCm(getMaxWarning()));
        }

    }
    //Convert centimeters to inches
    public static float cmToInches(final float cmValue) {
        return cmValue * 0.393701f;
    }
    //Convert inches to centimeters
    public static float inchesToCm(final float inchesValue) {
        return inchesValue * 2.54f;
    }
}
