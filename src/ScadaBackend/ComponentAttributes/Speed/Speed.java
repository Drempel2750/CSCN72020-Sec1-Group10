package ScadaBackend.ComponentAttributes.Speed;

import ScadaBackend.ComponentAttributes.Attribute.Attribute;
import ScadaBackend.ComponentAttributes.Temperature.Temperature;
import ScadaBackend.ComponentAttributes.Temperature.tempUnit;

import java.io.FileNotFoundException;

public class Speed extends Attribute {
    private speedUnit unit;
    public Speed(float value, final speedUnit unit, float minAcceptable, float maxAcceptable, float minWarning, float maxWarning) {
        super(value, minAcceptable, maxAcceptable, minWarning, maxWarning);
    }
    public Speed(final Attribute attribute, final speedUnit unit)
    {
        super(attribute);
        this.unit = unit;
    }



}
