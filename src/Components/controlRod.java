package Components;

import java.util.Scanner;

public class controlRod extends Component {
    private Depth depth;
    private final float minAcceptableDepth = 5;
    private final float maxAcceptableDepth = 20;
    private final float minWarningDepth = 2;
    private final float maxWarningDepth = 30;
    public controlRod(int ID)
    {
        super(State.OFF, ID);
        depth = new Depth(0, depthUnit.INCHES, minAcceptableDepth, maxAcceptableDepth, minWarningDepth, maxWarningDepth);
    }
    public Depth getDepth() {
        return depth;
    }
    public void setDepth(final Depth depth) {
        this.depth = depth;
    }
    @Override
    public void getComponentData(final Scanner scanner) {
        depth.updateAttributeFromFile(scanner);
        updateState(depth);
    }
}