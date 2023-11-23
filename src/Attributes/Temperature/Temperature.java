package Attributes.Temperature;

import Attributes.Attribute.Attribute;

public class Temperature extends Attribute {
    private tempUnit unit;//unit of temperature (Celsius, Fahrenheit, or Kelvin)
    public Temperature(final float value, final tempUnit unit, final float minAcceptable, final float maxAcceptable, final float minWarning, final float maxWarning)
    {
        super(value, minAcceptable, maxAcceptable, minWarning, maxWarning);
        this.unit = unit;
    }
    public Temperature(final Attribute attribute, final tempUnit unit)
    {
        super(attribute);
        this.unit = unit;
    }
    //setters
    public void setUnit(final tempUnit unit) {
        //convertBetween(this.unit, unit);
        this.unit = (unit);
    }
    // getters
    public tempUnit getUnit() {
        return this.unit;
    }
    //conversion functions
    @Override
    public float getValue() {
        if (this.unit == tempUnit.CELSIUS)
            return super.getValue();
        else
            return celsiusToFahrenheit(super.getValue());
    }
    private float celsiusToFahrenheit(final float value) {
        float fval = (value * 9/5) + 32;
        return (float) (Math.round(fval * 100.0)/100.0);
    }


}
