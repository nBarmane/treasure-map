package treasuremap.game.resolver;

import org.carbonit.game.TreasureMap;
import org.carbonit.game.core.TreasureMapParser;
import org.carbonit.game.core.TreasureMapRunner;
import org.junit.jupiter.api.Test;
import treasuremap.game.util.TestObjects;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static treasuremap.game.parser.ParseTreasureMapToFileTest.readFileToString;
import static treasuremap.game.util.TestObjects.getTestSampleTreasureMap1;
import static treasuremap.game.util.Helpers.TEST_SAMPLES_PATH;

public class TreasureMapResolverTest {

    @Test
    public void testResolvedTestCase1() {
        TreasureMap treasureMap = getTestSampleTreasureMap1();
        TreasureMapRunner.runTreasureMap(treasureMap);

        TreasureMap expectedResult = TestObjects.outputMap;

        assertEquals(expectedResult, treasureMap);
    }

    @Test
    public void testParseAndResolveTreasureMapSample1() {
        Path path = Paths.get(TEST_SAMPLES_PATH, "test_file_1");
        TreasureMap treasureMapFile = TreasureMapParser.parseTreasureMapFromFile(path.toString());
        TreasureMapRunner.runTreasureMap(treasureMapFile);
        String result = TreasureMapParser.parseTreasureMapToString(treasureMapFile);

        Path pathToSolution = Paths.get(TEST_SAMPLES_PATH, "output_test_file_1");
        String expectedResult = readFileToString(pathToSolution.toString());

        assertEquals(expectedResult, result);
    }

    @Test
    public void testParseAndResolveTreasureMapSample2() {
        Path path = Paths.get(TEST_SAMPLES_PATH, "test_file_3");
        TreasureMap treasureMapFile = TreasureMapParser.parseTreasureMapFromFile(path.toString());
        TreasureMapRunner.runTreasureMap(treasureMapFile);
        String result = TreasureMapParser.parseTreasureMapToString(treasureMapFile);

        Path pathToSolution = Paths.get(TEST_SAMPLES_PATH, "output_test_file_3");
        String expectedResult = readFileToString(pathToSolution.toString());

        assertEquals(expectedResult, result);
    }
}
