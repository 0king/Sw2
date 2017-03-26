package g.sw2;

public class Hexagon extends HexagonPiece {
    public Hexagon() {
        this(6, 90, false);
    }

    protected Hexagon(int numSides, int rotateDegrees, boolean shouldStretch) {
        super(numSides, rotateDegrees, shouldStretch);
    }
}
