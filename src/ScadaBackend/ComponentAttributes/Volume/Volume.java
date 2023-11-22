package ScadaBackend.ComponentAttributes.Volume;

import ScadaBackend.ComponentAttributes.Attribute.Attribute;

import java.io.FileNotFoundException;

public class Volume extends Attribute {
    private final volumeUnit unit;
    public Volume(final float value, final volumeUnit unit, final float minAcceptable, final float maxAcceptable, final float minWarning, final float maxWarning) {
        super(value, minAcceptable, maxAcceptable, minWarning, maxWarning);
        this.unit = unit;
    }
    public Volume(final Attribute attribute, final volumeUnit unit)
    {
        super(attribute);
        this.unit = unit;
    }
    public volumeUnit getUnit() {
        return unit;
    }
}
