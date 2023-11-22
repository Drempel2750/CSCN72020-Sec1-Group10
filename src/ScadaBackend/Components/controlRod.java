package ScadaBackend.Components;

import ScadaBackend.ComponentAttributes.Attribute.attributeState;
import ScadaBackend.ComponentAttributes.Depth.Depth;

public class controlRod extends Component{
    private Depth depth;
    public controlRod(final Depth depth)
    {
        super(State.OFF);
        this.depth = depth;
    }
    Depth getDepth() {
        return depth;
    }
    public void setDepth(final Depth depth) {
        this.depth = depth;
    }

    @Override
    public void updateState() {
        if (getState() == State.OFF)
            return;
        if (depth.getState() == attributeState.ERROR) {
            setState(State.ERROR);
            return;
        }
        else if (depth.getState() == attributeState.WARNING) {
            setState(State.WARNING);
            return;
        }
        setState(State.ON);
    }

    @Override
    public void getComponentData(String fileName) {

    }
}
