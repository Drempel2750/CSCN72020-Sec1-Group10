package ScadaBackend.ComponentAttributes.Temperature;

import ScadaBackend.ComponentAttributes.Attribute.Attribute;
import ScadaBackend.ComponentAttributes.Speed.speedUnit;

import java.io.FileNotFoundException;

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
        convertBetween(this.unit, unit);//update value when different unit used.
        this.unit = (unit);
    }
    //conversion functions
    public void convertBetween(final tempUnit startingUnit, final tempUnit endingUnit) {
        if (startingUnit == endingUnit)
            return;
        if (startingUnit == tempUnit.CELSIUS)
        {
            setValue(celsiusToFahrenheit(getValue()));
            setMaxAcceptable(celsiusToFahrenheit(getMaxAcceptable()));
            setMinAcceptable(celsiusToFahrenheit(getMinAcceptable()));
            setMinWarning(celsiusToFahrenheit(getMinWarning()));
            setMaxWarning(celsiusToFahrenheit(getMaxWarning()));
        }
        else
        {
            setValue(fahrenheitToCelsius(getValue()));
            setMaxAcceptable(fahrenheitToCelsius(getMaxAcceptable()));
            setMinAcceptable(fahrenheitToCelsius(getMinAcceptable()));
            setMinWarning(fahrenheitToCelsius(getMinWarning()));
            setMaxWarning(fahrenheitToCelsius(getMaxWarning()));
        }
    }
    private float celsiusToFahrenheit(final float value) {
        return (value * (9f / 5f)) + 32f;
    }
    private float fahrenheitToCelsius(final float value) {
        return (value - 32f) * (5f / 9f);
    }

}
