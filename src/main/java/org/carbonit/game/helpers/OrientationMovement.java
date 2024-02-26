package org.carbonit.game.helpers;

import lombok.Getter;
import lombok.Setter;
import org.carbonit.game.elements.Position;
import org.carbonit.game.enums.Movement;
import org.carbonit.game.enums.Orientation;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
public class OrientationMovement {
    private Orientation orientation;
    private Movement movement;

    public static final Map<OrientationMovement, Orientation> tableOfOrientations = new HashMap<>() {{
        put(new OrientationMovement(Orientation.N, Movement.D), Orientation.E);
        put(new OrientationMovement(Orientation.E, Movement.D), Orientation.S);
        put(new OrientationMovement(Orientation.S, Movement.D), Orientation.O);
        put(new OrientationMovement(Orientation.O, Movement.D), Orientation.N);

        put(new OrientationMovement(Orientation.N, Movement.G), Orientation.O);
        put(new OrientationMovement(Orientation.E, Movement.G), Orientation.N);
        put(new OrientationMovement(Orientation.S, Movement.G), Orientation.E);
        put(new OrientationMovement(Orientation.O, Movement.G), Orientation.S);
    }};

    public OrientationMovement(Orientation orientation, Movement movement) {
        this.orientation = orientation;
        this.movement = movement;
    }

    public static Orientation getNewOrientation(Orientation oldOrientation, Movement movement) {
        if(movement == Movement.A) {
            return oldOrientation;
        }

        Orientation newOrientation = tableOfOrientations.get(new OrientationMovement(oldOrientation, movement));
        if(newOrientation != null) {
            return newOrientation;
        }

        return oldOrientation;
    }

    public static Position updateCoordinates(int x, int y, Orientation orientation, int numberOfRows, int numberOfCols) {
        switch (orientation) {
            case N -> {
                if(y > 0) {
                    y--;
                }
            }
            case S -> {
                if(y < numberOfRows - 1) {
                    y++;
                }
            }
            case E -> {
                if(x < numberOfCols - 1) {
                    x++;
                }
            }
            case O -> {
                if(x > 0) {
                    x--;
                }
            }
        }
        return new Position(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrientationMovement that)) return false;
        return orientation == that.orientation && movement == that.movement;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orientation, movement);
    }
}