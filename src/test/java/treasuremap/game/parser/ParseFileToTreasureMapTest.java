package treasuremap.game.parser;

import org.carbonit.game.TreasureMap;
import org.carbonit.game.core.TreasureMapParser;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static treasuremap.game.util.TestObjects.getTestSampleTreasureMap1;
import static treasuremap.game.util.TestObjects.getTestSampleTreasureMap2;
import static treasuremap.game.util.Helpers.TEST_SAMPLES_PATH;

public class ParseFileToTreasureMapTest {

    @Test
    public void testParseFileToMapObject1() {
        Path path = Paths.get(TEST_SAMPLES_PATH, "test_file_1");
        TreasureMap treasureMapFile = TreasureMapParser.parseTreasureMapFromFile(path.toString());

        TreasureMap expectedResult = getTestSampleTreasureMap1();

        assertEquals(expectedResult, treasureMapFile);
    }

    @Test
    public void testParseFileToMapObject2() {
        Path path = Paths.get(TEST_SAMPLES_PATH, "test_file_2");
        TreasureMap treasureMapFile = TreasureMapParser.parseTreasureMapFromFile(path.toString());

        TreasureMap expectedResult = getTestSampleTreasureMap2();

        assertEquals(expectedResult, treasureMapFile);
    }
}
