package ScadaBackend.Components;

import Attributes.Temperature.Temperature;
import Attributes.Temperature.tempUnit;

import java.util.Scanner;

public class temperatureSensor extends Component{
    private Temperature temperature;
    private final int minAcceptableTemperature = 240;
    private final int maxAcceptableTemperature = 850;
    private final int minWarningTemperature = 150;
    private final int maxWarningTemperature = 2200;
    //setters
    public temperatureSensor(int ID)
    {
        super(State.OFF, ID);
        temperature = new Temperature(0, tempUnit.CELSIUS, minAcceptableTemperature, maxAcceptableTemperature, minWarningTemperature, maxWarningTemperature);
        temperature.updateState();
    }
    //getters
    public Temperature getTemperature() {
        return temperature;
    }

    //setters
    public void setTemperature(final Temperature temperature) {
        this.temperature = temperature;
    }
    public void setUnit(tempUnit unit) {
        this.temperature.setUnit(unit);
    }
    @Override
    public void getComponentData(final Scanner scanner) {
        temperature.updateAttributeFromFile(scanner);
        temperature.updateState();
    }
}
