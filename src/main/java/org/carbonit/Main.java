package org.carbonit;

import org.carbonit.game.TreasureMap;
import org.carbonit.game.core.TreasureMapParser;
import org.carbonit.game.core.TreasureMapRunner;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        // Read the input from file
        Path filepath = Paths.get("input", "input_sample");
        String inputPathString = filepath.toString(); // you put in "inputPathString" a path to you test file
        TreasureMap treasureMap = TreasureMapParser.parseTreasureMapFromFile(inputPathString);

        if(treasureMap != null) {
            // Execute the runner
            TreasureMapRunner.runTreasureMap(treasureMap);

            String outputPathString = Paths.get("output", "output").toString();
            TreasureMapParser.parseTreasureMapToFile(treasureMap, outputPathString);
        }
    }
}