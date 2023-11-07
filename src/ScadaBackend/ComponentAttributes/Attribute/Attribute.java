package ScadaBackend.ComponentAttributes.Attribute;

import ScadaBackend.Components.State;
import ScadaBackend.FileHandling.FileHandling;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Attribute {
    private float value;
    private attributeState state;
    private final float minAcceptable;
    private final float maxAcceptable;
    private final float minWarning;
    private final float maxWarning;
    public Attribute(final float value, final float minAcceptable, final float maxAcceptable, final float minWarning, final float maxWarning)
    {
        this.value = value;
        this.minAcceptable = minAcceptable;
        this.maxAcceptable = maxAcceptable;
        this.minWarning = minWarning;
        this.maxWarning = maxWarning;
    }

    public Attribute(final Attribute attribute)
    {
        this.state = attribute.getState();
        this.value = attribute.getValue();
        this.minAcceptable = attribute.getMinAcceptable();
        this.maxAcceptable = attribute.getMaxAcceptable();
        this.minWarning = attribute.getMinWarning();
        this.maxWarning = attribute.getMaxWarning();
    }
    //getters
    public float getValue() {
        return value;
    }
    //setters
    public void setValue(final float value) {
        this.value = value;
    }

    public attributeState getState() {
        return state;
    }
    public void updateState() {
        if (value < getMaxAcceptable() && value > getMinAcceptable())
            state = attributeState.OK;//if value is between min and max acceptable, continue with on state
        else if (value < getMaxWarning() || value > getMinWarning())
            state = attributeState.WARNING;
        /*
        if value is greater or less than min and max acceptable, but not greater or less than maxWarning
        and minWarning, the component is in an error state.
        */
        state = attributeState.ERROR;
        //any other condition, the component is in an error state which must be manually dealt with
    }

    //operation ranges
    public float getMaxAcceptable() {
        return maxAcceptable;
    }
    public float getMaxWarning() {
        return maxWarning;
    }
    public float getMinAcceptable() {
        return minAcceptable;
    }
    public float getMinWarning() {
        return minWarning;
    }

    protected static Attribute readAttributeFromStream(final Scanner scanner) {

        float attributeValue = scanner.nextFloat();
        float minAccept = scanner.nextFloat();
        float maxAccept = scanner.nextFloat();
        float minWarning = scanner.nextFloat();
        float maxWarning = scanner.nextFloat();

        return new Attribute(attributeValue, minAccept, maxAccept, minWarning, maxWarning);
    }
    protected static Attribute readAttribute(final String fileName, int ordinal) throws FileNotFoundException {
        Scanner scanner = FileHandling.openReadStream(fileName);
        if (scanner == null)
            return null;
        ordinal = scanner.nextInt();
        return readAttributeFromStream(scanner);
    }
}
