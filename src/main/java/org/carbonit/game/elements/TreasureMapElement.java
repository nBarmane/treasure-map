package org.carbonit.game.elements;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public abstract class TreasureMapElement {
    private Position position;

    public TreasureMapElement(int x, int y) {
        this.position = new Position(x, y);
    }

}
