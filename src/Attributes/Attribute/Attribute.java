package Attributes.Attribute;

import Components.State;
import FileHandling.FileHandling;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Attribute {
    private float value;
    private attributeState state;
    private float minAcceptable;
    private float maxAcceptable;
    private float minWarning;
    private float maxWarning;
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

    protected void setMaxAcceptable(final float maxAcceptable) { this.maxAcceptable = maxAcceptable;}
    protected void setMinAcceptable(final float minAcceptable) {this.minAcceptable = minAcceptable;}
    protected void setMinWarning(final float minWarning) {this.minWarning = minWarning;}
    protected void setMaxWarning(final float maxWarning) {this.maxWarning = maxWarning;}

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

    public boolean updateAttributeFromFile(final Scanner scanner) {
        if (scanner == null)
            return false;
        float value;
        try {
            value = scanner.nextFloat();
        } catch (InputMismatchException e) {
            System.out.println("ERROR: Could not read from file");
            return false;
        }
        setValue(value);
        return true;
    }
}
