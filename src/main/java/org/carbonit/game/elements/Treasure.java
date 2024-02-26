package org.carbonit.game.elements;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
public class Treasure extends TreasureMapElement {
    private int remainingTreasures;
    private boolean vacant = true;

    public Treasure(int x, int y, int treasureCount) {
        super(x, y);
        this.remainingTreasures = treasureCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Treasure treasure)) return false;
        return remainingTreasures == treasure.remainingTreasures;
    }

    @Override
    public int hashCode() {
        return Objects.hash(remainingTreasures);
    }
}
