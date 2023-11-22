package Components;

import java.util.Scanner;

public abstract class Component {
    private State state;
    protected final int ID;
    public Component(final State state, final int ID) {
        this.state = state;
        this.ID = ID;
    }
    public State getState() {
        return state;
    }
    protected void setState(State state) {
        this.state = state;
    }
    public void updateState(final Attribute attribute) {
        if (getState() == State.OFF)
            return;
        if (attribute.getState() == attributeState.OK)
            setState(State.ON);
        if (attribute.getState() == attributeState.WARNING)
            setState(State.WARNING);
        if (attribute.getState() == attributeState.ERROR)
            setState(State.ERROR);
    }
    public abstract void getComponentData(final Scanner scanner);
    public int getID () {return this.ID;}
    public void turnOn() {
        setState(State.ON);
    }
    public void turnOff() {
        setState(State.OFF);
    }

}
