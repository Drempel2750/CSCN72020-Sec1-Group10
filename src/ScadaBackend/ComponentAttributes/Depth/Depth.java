package ScadaBackend.ComponentAttributes.Depth;

import ScadaBackend.ComponentAttributes.Attribute.Attribute;

import java.io.FileNotFoundException;

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
        if (endingUnit == depthUnit.INCHES)
            setValue(cmToInches(getValue()));
        else
            setValue(inchesToCm(getValue()));

    }
    //Convert centimeters to inches
    public static float cmToInches(final float cmValue) {
        return cmValue * 0.393701f;
    }
    //Convert inches to centimeters
    public static float inchesToCm(final float inchesValue) {
        return inchesValue * 2.54f;
    }
    public static Depth readDepthFromFile(final String fileName) throws FileNotFoundException {
        int unitOrdinal = 0;
        Attribute attribute = readAttribute(fileName, unitOrdinal);
        for (depthUnit unit: depthUnit.values())
        {
            if (unit.ordinal() == unitOrdinal)
                return new Depth(attribute, unit);
        }//find value of unit that the ordinal is associated with
        return null;//no Depth object could be created with information read
    }
}
