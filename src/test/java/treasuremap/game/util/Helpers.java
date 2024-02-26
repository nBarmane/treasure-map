package treasuremap.game.util;

import java.nio.file.Paths;
import java.util.List;
import java.util.Stack;

public class Helpers {

    public final static String TEST_SAMPLES_PATH = Paths.get("src", "test", "file_samples").toString();

    public static <T> Stack<T> transformListToStackAndPreserveOrder(List<T> list) {
        Stack<T> stack = new Stack<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            stack.push(list.get(i));
        }
        return stack;
    }
}