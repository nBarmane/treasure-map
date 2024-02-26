package org.carbonit.game.elements;

import lombok.Getter;
import lombok.Setter;
import org.carbonit.game.enums.Movement;
import org.carbonit.game.enums.Orientation;

import java.util.Objects;
import java.util.Stack;

@Setter
@Getter
public class Adventurer extends TreasureMapElement {
    private String name;

    private Orientation orientation;

    private Stack<Movement> movements = new Stack<>();

    private int treasuresCollected = 0;

    private boolean onTreasure = false;

    public Adventurer(int x, int y, String name, Orientation orientation, Stack<Movement> movements) {
        super(x, y);
        this.name = name;
        this.orientation = orientation;
        this.movements = movements;
    }

    public Adventurer(int x, int y, String name, Orientation orientation, int treasuresCollected) {
        super(x, y);
        this.name = name;
        this.orientation = orientation;
        this.treasuresCollected = treasuresCollected;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Adventurer that)) return false;
        return treasuresCollected == that.treasuresCollected
                && Objects.equals(name, that.name)
                && orientation == that.orientation
                && Objects.equals(movements, that.movements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, orientation, movements, treasuresCollected);
    }
}
