package Attributes.Power;

import Attributes.Attribute.Attribute;

public class Power extends Attribute {
    public Power(float value, float minAcceptable, float maxAcceptable, float minWarning, float maxWarning) {
        super(value, minAcceptable, maxAcceptable, minWarning, maxWarning);
    }
    public Power(final Attribute attribute)
    {
        super(attribute);
    }
}
