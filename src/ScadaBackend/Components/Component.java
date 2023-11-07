package ScadaBackend.Components;

public abstract class Component {
    private State state;
    public Component(final State state) {
        this.state = state;
    }
    public State getState() {
        return state;
    }
    protected void setState(State state) {
        this.state = state;
    }
    public abstract void updateState();
    public abstract void getComponentData(final String fileName);
    public void turnOn() {
        setState(State.ON);
    }
    public void turnOff() {
        setState(State.OFF);
    }

}
