package treasuremap.game.util;

import org.carbonit.game.TreasureMap;
import org.carbonit.game.enums.Movement;
import org.carbonit.game.enums.Orientation;
import org.carbonit.game.elements.Adventurer;
import org.carbonit.game.elements.Mountain;
import org.carbonit.game.elements.Treasure;

import java.util.*;

import static treasuremap.game.util.Helpers.transformListToStackAndPreserveOrder;

public abstract class TestObjects {
    public static TreasureMap getTestSampleTreasureMap1() {
        List<Movement> movementsTestFile1 = List.of(
            Movement.A,
            Movement.A,
            Movement.D,
            Movement.A,
            Movement.D,
            Movement.A,
            Movement.G,
            Movement.G,
            Movement.A
        );

        Stack<Movement> stackLara = transformListToStackAndPreserveOrder(movementsTestFile1);

        TreasureMap treasureMap = TreasureMap.builder()
            .cols(3)
            .rows(4)
            .mountainList(
                List.of(
                    new Mountain(1, 0),
                    new Mountain(2, 1)
                )
            )
            .treasureList(
                List.of(
                    new Treasure(0, 3, 2),
                    new Treasure(1, 3, 3)
                )
            )
            .adventurerList(
                List.of(
                    new Adventurer(1, 1, "Lara", Orientation.S, stackLara)
                )
            )
            .map(new HashMap<>())
            .build();

        return treasureMap;
    }


    public static TreasureMap getTestSampleTreasureMap2() {
        List<Movement> listofMovementsAdamTestFile2 = Arrays.asList(
            Movement.G,
            Movement.G,
            Movement.A,
            Movement.D,
            Movement.D
        );

        Stack<Movement> stackAdam = transformListToStackAndPreserveOrder(listofMovementsAdamTestFile2);

        List<Movement> listofMovementsSarahTestFile2 = Arrays.asList(
            Movement.D,
            Movement.G,
            Movement.A
        );

        Stack<Movement> stackSarah = transformListToStackAndPreserveOrder(listofMovementsSarahTestFile2);

        TreasureMap treasureMap = TreasureMap.builder()
            .cols(5)
            .rows(8)
            .mountainList(
                List.of(
                    new Mountain(0, 4),
                    new Mountain(2, 5),
                    new Mountain(2, 7),
                    new Mountain(4, 1)
                )
            )
            .treasureList(
                List.of(
                    new Treasure(1, 2, 1),
                    new Treasure(2, 4, 6),
                    new Treasure(4, 1, 2)
                )
            )
            .adventurerList(
                List.of(
                    new Adventurer(2, 3, "Adam", Orientation.N, stackAdam),
                    new Adventurer(4, 0, "Sarah", Orientation.E, stackSarah)
                )
            )
            .map(new HashMap<>())
            .build();

        return treasureMap;
    }



    public static final TreasureMap outputMap = TreasureMap.builder()
        .cols(3)
        .rows(4)
        .mountainList(
            List.of(
                new Mountain(1, 0),
                new Mountain(2, 1)
            )
        )
        .treasureList(
            List.of(
                new Treasure(1, 3, 2)
            )
        )
        .adventurerList(
            List.of(
                new Adventurer(0, 3, "Lara", Orientation.S, 3)
            )
        )
        .build();
}
