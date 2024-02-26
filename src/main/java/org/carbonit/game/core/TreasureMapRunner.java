package org.carbonit.game.core;

import org.carbonit.game.TreasureMap;
import org.carbonit.game.elements.*;
import org.carbonit.game.enums.Movement;
import org.carbonit.game.enums.Orientation;
import org.carbonit.game.helpers.OrientationMovement;

import java.util.List;
import java.util.stream.Collectors;

import static org.carbonit.game.helpers.OrientationMovement.*;

public abstract class TreasureMapRunner {
    public static void runTreasureMap(TreasureMap treasureMap) {
        // 1) place all the objects on the map
        for(Mountain mountain : treasureMap.getMountainList()) {
            final int x = mountain.getPosition().getX();
            final int y = mountain.getPosition().getY();
            treasureMap.setElementAt(x, y, mountain);
        }
        for(Treasure treasure : treasureMap.getTreasureList()) {
            final int x = treasure.getPosition().getX();
            final int y = treasure.getPosition().getY();
            treasureMap.setElementAt(x, y, treasure);
        }
        for(Adventurer adventurer : treasureMap.getAdventurerList()) {
            final int x = adventurer.getPosition().getX();
            final int y = adventurer.getPosition().getY();
            treasureMap.setElementAt(x, y, adventurer);
        }

        // 2) Movements of adventurers
        boolean stillGotMovements = true;
        while(stillGotMovements) {
            stillGotMovements = false;
            for(Adventurer adventurer : treasureMap.getAdventurerList()) {
                if(!adventurer.getMovements().isEmpty()) {
                    moveAdventurer(adventurer, treasureMap);
                }

                if(!stillGotMovements && !adventurer.getMovements().isEmpty()) {
                    stillGotMovements = true;
                }
            }
        }
    }

    public static void moveAdventurer(Adventurer adventurer, TreasureMap treasureMap) {
        int x = adventurer.getPosition().getX();
        int y = adventurer.getPosition().getY();

        final Orientation orientation = adventurer.getOrientation();
        final Movement nextMovement = adventurer.getMovements().pop();

        Orientation newOrientation = getNewOrientation(orientation, nextMovement);

        Position newPosition = updateCoordinates(x, y, newOrientation, treasureMap.getRows(), treasureMap.getCols());
        int newX = newPosition.getX();
        int newY = newPosition.getY();

        adventurer.setOrientation(newOrientation);

        if(newX != x || newY != y) {
            // check if adventurer is leaving a treasure
            TreasureMapElement elementOnMap = treasureMap.getElementAt(x, y);
            if(!elementOnMap.equals(adventurer)) {
                if(elementOnMap instanceof Treasure) {
                    Treasure treasure = (Treasure) elementOnMap;

                    treasure.setVacant(true);
                    adventurer.setOnTreasure(false);
                }
            }

            TreasureMapElement element = treasureMap.getElementAt(newX, newY);
            if(element != null) {
                if(element instanceof Mountain || element instanceof Adventurer) {
                    // don't do anything (the adventurer is blocked)
                } else if(element instanceof Treasure) {
                    Treasure treasure = (Treasure) element;
                    if(treasure.isVacant()) {
                        treasure.setVacant(false);
                        adventurer.setOnTreasure(true);
                        if(elementOnMap.equals(adventurer)) {
                            treasureMap.clearElementAt(x, y);
                        }
                        int remainingTreasures = treasure.getRemainingTreasures();
                        if(remainingTreasures > 0) {
                            treasure.setRemainingTreasures(--remainingTreasures);
                            int treasureCollected = adventurer.getTreasuresCollected();
                            adventurer.setTreasuresCollected(++treasureCollected);
                            adventurer.setPosition(new Position(newX, newY));
                        }


                        if(remainingTreasures == 0) {
                            // remove treasure from TreasureMap
                            List<Treasure> newTreasureList = treasureMap.getTreasureList().stream()
                                .filter(elem -> !elem.getPosition().equals(treasure.getPosition()))
                                .collect(Collectors.toList());

                            treasureMap.setTreasureList(newTreasureList);

                            // replace it by the adventurer on the map
                            adventurer.setOnTreasure(false);
                            treasureMap.setElementAt(newX, newY, adventurer);
                        }
                    } else {
                        // don't do anything (the adventurer is blocked)
                    }
                }
            } else {
                adventurer.setPosition(new Position(newX, newY));
                treasureMap.setElementAt(newX, newY, adventurer);

                if(elementOnMap.equals(adventurer)) {
                    treasureMap.clearElementAt(x, y);
                }
            }
        }
    }
}


