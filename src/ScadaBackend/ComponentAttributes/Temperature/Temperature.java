package ScadaBackend.ComponentAttributes.Temperature;

import ScadaBackend.ComponentAttributes.Attribute.Attribute;

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
        if (startingUnit == tempUnit.CELSIUS){
            if (endingUnit == tempUnit.KELVIN)
                setValue(celsiusToKelvin());
            else if (endingUnit == tempUnit.FAHRENHEIT)
                setValue(celsiusToFahrenheit());
        } else if (startingUnit == tempUnit.FAHRENHEIT) {
            if (endingUnit == tempUnit.CELSIUS)
                setValue(fahrenheitToCelsius());
            else if (endingUnit == tempUnit.KELVIN) {
                setValue(fahrenheitToCelsius());
                setValue(celsiusToKelvin());
            }
        } else {
            if (endingUnit == tempUnit.CELSIUS)
                setValue(kelvinToCelsius());
            else if (endingUnit == tempUnit.FAHRENHEIT) {
                setValue(kelvinToCelsius());
                setValue(celsiusToFahrenheit());
            }
        }
    }
    private float celsiusToFahrenheit() {
        return (getValue() * (9f / 5f)) + 32f;
    }
    private float fahrenheitToCelsius() {
        return (getValue() - 32f) * (5f / 9f);
    }
    private float celsiusToKelvin() {
        return getValue() + 273.15f;
    }
    private float kelvinToCelsius() {
        return getValue() - 273.15f;
    }

    public static Temperature readTemperatureFromFile(final String fileName) throws FileNotFoundException {
        int unitOrdinal = 0;
        Attribute attribute = readAttribute(fileName, unitOrdinal);
        for (tempUnit unit: tempUnit.values())
        {
            if (unit.ordinal() == unitOrdinal)
                return new Temperature(attribute, unit);
        }//find value of unit that the ordinal is associated with
        return null;//no Depth object could be created with information read
    }
}
