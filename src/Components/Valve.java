package ScadaBackend.Components;

import java.util.Scanner;

public class Valve extends Component {

    public Valve (int ID) {
        super(State.OFF, ID);
    }
    @Override
    public void getComponentData(final Scanner scanner) {
        return;
    }
}

