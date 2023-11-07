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

    public static Volume readVolumeFromFile(final String fileName) throws FileNotFoundException {
        int unitOrdinal = 0;
        Attribute attribute = readAttribute(fileName, unitOrdinal);
        for (volumeUnit unit: volumeUnit.values())
        {
            if (unit.ordinal() == unitOrdinal)
                return new Volume(attribute, unit);
        }//find value of unit that the ordinal is associated with
        return null;//no Depth object could be created with information read
    }
}
