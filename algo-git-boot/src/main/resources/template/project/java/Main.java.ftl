package beyondalgo.${projectName};

import com.beyondalgo.*;
import com.beyondalgo.algo.*;
import com.beyondalgo.data.*;
import com.google.gson.*;
import java.util.*;

/**
 * This class defines your algorithm, and its input/output.
 * Algorithms must define at least one apply(...) method.
 *
 * Examples:
 *   public int apply(int[][] array) {...}
 *   public String apply(Map<String,String> object) {...}
 *   public List<Double> apply(double a, double b, double c) {...}
 */
public class ${projectName} {
    // The input and output of apply() automatically turns into JSON
    public String apply(String input) throws Exception {
        // Your algorithm code goes here
        Integer i = 1;
        return "Hello " + input + i;
    }
}