package ScadaBackend.ComponentAttributes.Power;

import ScadaBackend.ComponentAttributes.Attribute.Attribute;
import ScadaBackend.ComponentAttributes.Depth.depthUnit;

public class Power extends Attribute {
    public Power(float value, float minAcceptable, float maxAcceptable, float minWarning, float maxWarning) {
        super(value, minAcceptable, maxAcceptable, minWarning, maxWarning);
    }
    public Power(final Attribute attribute)
    {
        super(attribute);
    }
}
