package ScadaBackend.Components;

import ScadaBackend.ComponentAttributes.Pressure.Pressure;
import ScadaBackend.ComponentAttributes.Temperature.Temperature;

public class Reactor extends Component{
    private Temperature temperature;
    private Pressure pressure;

    //setters
    public Reactor(final Temperature temperature, final Pressure pressure, final State state)
    {
        super(state);
        this.temperature = temperature;
        this.pressure = pressure;
    }
    //getters
    public Pressure getPressure() {
        return pressure;
    }
    public Temperature getTemperature() {
        return temperature;
    }

    //setters
    public void setPressure(final Pressure pressure) {
        this.pressure = pressure;
    }
    public void setTemperature(final Temperature temperature) {
        this.temperature = temperature;
    }

    @Override
    public void updateState() {

    }

    @Override
    public void getComponentData(String fileName) {

    }
}
