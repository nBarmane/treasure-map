package org.carbonit.game.core;

import org.carbonit.game.TreasureMap;
import org.carbonit.game.elements.Adventurer;
import org.carbonit.game.elements.Mountain;
import org.carbonit.game.elements.Treasure;
import org.carbonit.game.enums.Movement;
import org.carbonit.game.enums.Orientation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.PatternSyntaxException;

public abstract class TreasureMapParser {
    public static final String SEPARATOR = "-";
    public static final String COMMENT_SYMBOL = "#";

    public static final String CARTE_SYMBOL = "C";
    public static final String MOUNTAIN_SYMBOL = "M";
    public static final String TREASURE_SYMBOL = "T";
    public static final String ADVENTURER_SYMBOL = "A";

    public static final String NEW_LINE_SEPARATOR = System.lineSeparator();

    public static TreasureMap parseTreasureMapFromFile(String filepath) {
        TreasureMap treasureMap;

        int cols = 0, rows = 0;
        List<Mountain> mountainList = new ArrayList<>();
        List<Treasure> treasureList = new ArrayList<>();
        List<Adventurer> adventurerList = new ArrayList<>();

        Scanner sc = null;
        try {
            File file = new File(filepath);
            sc = new Scanner(file);

            while(sc.hasNextLine()) {
                String currentLine = sc.nextLine();
                if(!currentLine.isEmpty()) {
                    currentLine = currentLine.trim();

                    if(currentLine.isEmpty() || String.valueOf(currentLine.charAt(0)).equals(COMMENT_SYMBOL)) {
                        // it's a commment, skip the line!
                        continue;
                    }

                    String[] parts;

                    try {
                        parts = currentLine.split(SEPARATOR);
                    } catch (PatternSyntaxException e) {
                        parts = null;
                    }

                    if(parts != null && parts.length > 0) {
                        String typeOfElement = parts[0];

                        switch (typeOfElement) {
                            case CARTE_SYMBOL: {
                                cols = Integer.parseInt(parts[1]);
                                rows = Integer.parseInt(parts[2]);

                                break;
                            }

                            case MOUNTAIN_SYMBOL: {
                                int x = Integer.parseInt(parts[1]);
                                int y = Integer.parseInt(parts[2]);

                                Mountain mountain = new Mountain(x, y);
                                mountainList.add(mountain);

                                break;
                            }

                            case TREASURE_SYMBOL: {
                                int x = Integer.parseInt(parts[1]);
                                int y = Integer.parseInt(parts[2]);

                                int treasureCounts = Integer.parseInt(parts[3]);

                                Treasure treasure = new Treasure(x, y, treasureCounts);
                                treasureList.add(treasure);

                                break;
                            }

                            case ADVENTURER_SYMBOL: {
                                String name = parts[1];

                                int x = Integer.parseInt(parts[2]);
                                int y = Integer.parseInt(parts[3]);

                                Orientation orientation;
                                try {
                                    orientation = Orientation.valueOf(parts[4]);
                                } catch (IllegalArgumentException e) {
                                    orientation = null;
                                }

                                String seqOfMouvements = parts[5];

                                Stack<Movement> movements = new Stack<>();

                                try {
                                    for (int i = seqOfMouvements.length() - 1; i >= 0; i--){
                                        String mvt = String.valueOf(seqOfMouvements.charAt(i));
                                        Movement m = Movement.valueOf(mvt);

                                        movements.push(m);
                                    }
                                } catch (IllegalArgumentException e) {
                                    movements = null;
                                }

                                Adventurer adventurer = new Adventurer(x, y, name, orientation, movements);
                                adventurerList.add(adventurer);
                            }

                        }
                    }
                }
            }
        } catch(FileNotFoundException e) {
            System.err.println("An error occured while reading the file (check if the file really exists)");
            return null;
        } finally {
            if(sc != null){
                sc.close();
            }
        }

        treasureMap = new TreasureMap(cols, rows);
        treasureMap.setMountainList(mountainList);
        treasureMap.setTreasureList(treasureList);
        treasureMap.setAdventurerList(adventurerList);

        return treasureMap;
    }

    public static void parseTreasureMapToFile(TreasureMap treasureMap, String filepathToWriteTo) {
        final String output = parseTreasureMapToString(treasureMap);

        writeStringToFile(output, filepathToWriteTo);
    }

    public static String parseTreasureMapToString(TreasureMap treasureMap) {
        StringBuilder sb = new StringBuilder();

        sb.append(CARTE_SYMBOL + SEPARATOR + treasureMap.getCols() + SEPARATOR + treasureMap.getRows());
        sb.append(NEW_LINE_SEPARATOR);

        for(Mountain mountain : treasureMap.getMountainList()) {
            sb.append(MOUNTAIN_SYMBOL + SEPARATOR + mountain.getPosition().getX() + SEPARATOR + mountain.getPosition().getY());
            sb.append(NEW_LINE_SEPARATOR);
        }

        for(Treasure treasure : treasureMap.getTreasureList()) {
            sb.append(TREASURE_SYMBOL + SEPARATOR + treasure.getPosition().getX() + SEPARATOR + treasure.getPosition().getY()
                    + SEPARATOR + treasure.getRemainingTreasures());
            sb.append(NEW_LINE_SEPARATOR);
        }

        for(Adventurer adventurer : treasureMap.getAdventurerList()) {
            sb.append(ADVENTURER_SYMBOL + SEPARATOR + adventurer.getName()
                    + SEPARATOR + adventurer.getPosition().getX() + SEPARATOR + adventurer.getPosition().getY()
                    + SEPARATOR + adventurer.getOrientation()
                    + SEPARATOR + adventurer.getTreasuresCollected());
            sb.append(NEW_LINE_SEPARATOR);
        }

        return sb.toString();
    }

    public static void writeStringToFile(String output, String filepathToWriteTo) {
        File file = new File(filepathToWriteTo);
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(output);
        } catch (IOException e) {
            System.err.println("Couldn't write to specified directory (check if directory really exists)");
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                System.err.println("An error occured while writing output to file");
            }
        }
    }
}
