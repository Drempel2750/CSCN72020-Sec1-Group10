package Attributes.Pressure;

import Attributes.Attribute.Attribute;

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
        //convertBetween(this.unit, unit);
        this.unit = unit;
    }
    @Override
    public float getValue() {
        if (this.unit == pressureUnit.PSI)
            return super.getValue();
        else
            return psiToBar(super.getValue());
    }

    //Convert PSI to BAR
    public float psiToBar(float psiValue) {
        float barValue = psiValue * 0.0689475729f;
        return (float) (Math.round(barValue * 100.0)/100.0);
    }

}
