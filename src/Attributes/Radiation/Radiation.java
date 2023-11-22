package Attributes.Radiation;

import Attributes.Attribute.Attribute;

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
