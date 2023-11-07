package ScadaBackend.ComponentAttributes.Pressure;

import ScadaBackend.ComponentAttributes.Attribute.Attribute;

import java.io.FileNotFoundException;

public class Pressure extends Attribute {
    private pressureUnit unit;
    public Pressure(final float value, pressureUnit unit, final float minAcceptable, final float maxAcceptable, final float minWarning, final float maxWarning) {
        super(value, minAcceptable, maxAcceptable, minWarning, maxWarning);
        this.unit = unit;
    }

    public Pressure(final Attribute attribute, final pressureUnit unit)
    {
        super(attribute);
        this.unit = unit;
    }
    public pressureUnit getUnit() {
        return unit;
    }
    public void setUnit(pressureUnit unit) {
        convertBetween(this.unit, unit);
        this.unit = unit;
    }

    public void convertBetween(final pressureUnit startingUnit, final pressureUnit endingUnit)
    {
        if (startingUnit == endingUnit)
            return;
        if (endingUnit == pressureUnit.PSI)
            setValue(barToPsi(getValue()));
        else
            setValue(psiToBar(getValue()));
    }

    //Convert PSI to BAR
    public float psiToBar(float psiValue) {
        return psiValue * 0.0689475729f;
    }

    //Convert BAR to PSI
    public float barToPsi(float barValue) {
        return barValue / 0.0689475729f;
    }

    public static Pressure readPressureFromFile(final String fileName) throws FileNotFoundException {
        int unitOrdinal = 0;
        Attribute attribute = readAttribute(fileName, unitOrdinal);
        for (pressureUnit unit: pressureUnit.values())
        {
            if (unit.ordinal() == unitOrdinal)
                return new Pressure(attribute, unit);
        }//find value of unit that the ordinal is associated with
        return null;//no Depth object could be created with information read
    }
}
