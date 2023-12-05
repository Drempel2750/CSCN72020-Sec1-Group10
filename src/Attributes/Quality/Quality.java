package Attributes.Quality;

import Attributes.Attribute.Attribute;
import Attributes.Pressure.pressureUnit;

public class Quality extends Attribute {
    public Quality(final float value, final float minAcceptable, final float maxAcceptable, final float minWarning, final float maxWarning) {
        super(value, minAcceptable, maxAcceptable, minWarning, maxWarning);
    }
    public Quality (final Attribute attribute) {
        super(attribute);
    }
}
