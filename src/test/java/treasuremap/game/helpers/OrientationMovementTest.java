package treasuremap.game.helpers;


import org.carbonit.game.elements.Position;
import org.carbonit.game.enums.Movement;
import org.carbonit.game.enums.Orientation;
import org.junit.jupiter.api.Test;

import static org.carbonit.game.helpers.OrientationMovement.getNewOrientation;
import static org.carbonit.game.helpers.OrientationMovement.updateCoordinates;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrientationMovementTest {

    @Test
    public void testDifferentCasesOfChangeOfOrientations() {
        assertAll(
            "test method getNewOrientation",
            () -> assertEquals(Orientation.O, getNewOrientation(Orientation.O, Movement.A)),
            () -> assertEquals(Orientation.E, getNewOrientation(Orientation.E, Movement.A)),
            () -> assertEquals(Orientation.O, getNewOrientation(Orientation.S, Movement.D)),
            () -> assertEquals(Orientation.N, getNewOrientation(Orientation.E, Movement.G)),
            () -> assertEquals(Orientation.N, getNewOrientation(Orientation.O, Movement.D)),
            () -> assertEquals(Orientation.S, getNewOrientation(Orientation.O, Movement.G)),
            () -> assertEquals(Orientation.S, getNewOrientation(Orientation.E, Movement.D))
        );
    }

    @Test
    public void testDifferentCasesOfChangeOfPositions() {
        assertAll(
            "Test method updateCoordinates",
            () -> assertEquals(new Position(1, 2), updateCoordinates(1, 1, Orientation.S, 3, 4)),
            () -> assertEquals(new Position(1, 0), updateCoordinates(1, 1, Orientation.N, 3, 4)),
            () -> assertEquals(new Position(1, 0), updateCoordinates(1, 0, Orientation.N, 4, 2)),
            () -> assertEquals(new Position(0, 0), updateCoordinates(1, 0, Orientation.O, 3, 4)),
            () -> assertEquals(new Position(0, 4), updateCoordinates(0, 4, Orientation.O, 6, 4)),
            () -> assertEquals(new Position(5, 3), updateCoordinates(5, 2, Orientation.S, 6, 4)),
            () -> assertEquals(new Position(5, 3), updateCoordinates(5, 3, Orientation.E, 6, 4))
        );
    }
}
