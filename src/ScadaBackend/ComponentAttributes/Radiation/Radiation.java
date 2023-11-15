package ScadaBackend.ComponentAttributes.Radiation;

import ScadaBackend.ComponentAttributes.Attribute.Attribute;
import ScadaBackend.ComponentAttributes.Pressure.Pressure;
import ScadaBackend.ComponentAttributes.Pressure.pressureUnit;

import java.io.FileNotFoundException;

public class Radiation extends Attribute {
    private radiationUnit unit;
    public Radiation(float value, final radiationUnit unit, float minAcceptable, float maxAcceptable, float minWarning, float maxWarning) {
        super(value, minAcceptable, maxAcceptable, minWarning, maxWarning);
    }
    public Radiation(final Attribute attribute, final radiationUnit unit)
    {
        super(attribute);
        this.unit = unit;
    }
    public radiationUnit getUnit() {
        return unit;
    }

}
