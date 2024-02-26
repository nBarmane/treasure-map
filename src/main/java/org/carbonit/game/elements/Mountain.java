package org.carbonit.game.elements;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Mountain extends TreasureMapElement {
    public Mountain(int x, int y) {
        super(x, y);
    }
}
