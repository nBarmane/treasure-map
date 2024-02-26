package org.carbonit.game;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.carbonit.game.elements.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class TreasureMap {
    private int rows;
    private int cols;

    private List<Mountain> mountainList;
    private List<Treasure> treasureList;
    private List<Adventurer> adventurerList;

    private Map<Position, Object> map = new HashMap<>();

    public TreasureMap(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
    }

    public <T extends TreasureMapElement> void setElementAt(int x, int y, T element) {
        map.put(new Position(x, y), element);
    }

    public <T extends TreasureMapElement> T getElementAt(int x, int y) {
        return (T) map.get(new Position(x, y));
    }

    public void clearElementAt(int x, int y) {
        map.remove(new Position(x, y));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TreasureMap that)) return false;
        return rows == that.rows
                && cols == that.cols
                && Objects.equals(mountainList, that.mountainList)
                && Objects.equals(treasureList, that.treasureList)
                && Objects.equals(adventurerList, that.adventurerList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rows, cols, mountainList, treasureList, adventurerList);
    }
}
