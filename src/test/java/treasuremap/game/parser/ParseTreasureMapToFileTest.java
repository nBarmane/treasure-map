package treasuremap.game.parser;

import org.carbonit.game.core.TreasureMapParser;
import org.junit.jupiter.api.Test;
import treasuremap.game.util.TestObjects;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static org.carbonit.game.core.TreasureMapParser.NEW_LINE_SEPARATOR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static treasuremap.game.util.Helpers.TEST_SAMPLES_PATH;

public class ParseTreasureMapToFileTest {

    @Test
    public void testParseTreasureMapObjectToString() {
        String output = TreasureMapParser.parseTreasureMapToString(TestObjects.outputMap);

        if (!output.isEmpty()) {
            Path path = Paths.get(TEST_SAMPLES_PATH, "output_test_file_1");
            String expectedResult = readFileToString(path.toString());

            assertEquals(expectedResult, output);
        }
    }

    public static String readFileToString(String filepath) {
        StringBuilder sb = new StringBuilder();

        Scanner sc = null;
        try {
            File file = new File(filepath);
            sc = new Scanner(file);

            while(sc.hasNextLine()) {
                sb.append(sc.nextLine());
                sb.append(NEW_LINE_SEPARATOR);
            }
        } catch(FileNotFoundException e) {
            System.err.println("An error occured while reading the file (check if the file really exists)");
            return null;
        } finally {
            if(sc != null){
                sc.close();
            }
        }

        return sb.toString();
    }
}
