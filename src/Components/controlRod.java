package Components;

import ScadaBackend.ComponentAttributes.Attribute.attributeState;
import ScadaBackend.ComponentAttributes.Depth.Depth;
import ScadaBackend.ComponentAttributes.Depth.depthUnit;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class controlRod extends Component {
    protected Depth depth;
    private final float minAcceptableDepth = 10;
    private final float maxAcceptableDepth = 20;
    private final float minWarningDepth = 0;
    private final float maxWarningDepth = 30;
    public controlRod(int ID)
    {
        super(State.OFF, ID);
        depth = new Depth(15, depthUnit.INCHES, minAcceptableDepth, maxAcceptableDepth, minWarningDepth, maxWarningDepth);
        depth.updateState();
    }
    public Depth getDepth() {
        return depth;
    }
    public void setDepth(final Depth depth) {
        this.depth = depth;
    }
    public void incDepth() {
        if (this.depth.getValue() == maxWarningDepth)
            return;
        this.depth.setValue(this.depth.getValue()+1);
        depth.updateState();
    }
    public void decDepth() {
        if (this.depth.getValue() == minWarningDepth)
            return;
        this.depth.setValue(this.depth.getValue()-1);
        depth.updateState();
    }
    @Override
    public void getComponentData(final Scanner scanner) {
        depth.updateAttributeFromFile(scanner);
        depth.updateState();
    }
}