package Components;

import Attributes.Attribute.Attribute;
import Attributes.Attribute.attributeState;

import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class Component {
    private State state;
    protected final int ID;
    public Component(State state, final int ID) {
        this.state = state;
        if (ID < 0) {
            System.out.println("ERROR: Invalid ID Value Passed!");
            System.exit(1);
        }
        this.ID = ID;
    }
    public State getState() {
        return state;
    }
    protected void setState(State state) {
        this.state = state;
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