//1. What design principles does this code violate?
//      This code breaks the simplicity rule. It attempts to do several different functions in one function.
//2. Refactor the code to improve its design.


public class Matcher {
    public Matcher() {}

    // Clip "too-large" values
    public int[] clip(int[] actual, int clipLimit) {
        // Clip "too-large" values
        for (int i = 0; i < actual.length; i++){
            if (actual[i] > clipLimit){
                actual[i] = clipLimit;
            }
        }
        return actual;
    }

    // Check for length differences
    public boolean lengthDifference(int[] actual, int[] expected) {
        if (actual.length != expected.length){
            return false;
        }
        return true;
    }
    
    // Check that each entry within expected +/- delta
    public boolean checkWithinDelta(int[] expected, int[] actual, int delta) {
        for (int i = 0; i < actual.length; i++){
            if (Math.abs(expected[i] - actual[i]) > delta)
                return false;
        }
        return true;
    }

    public boolean match(int[] expected, int[] actual, int clipLimit, int delta)
    {
        // Clip "too-large" values
        actual = clip(actual, clipLimit);

        // Check for length differences
        if(!lengthDifference(actual, expected)){
            return false;
        }

        // Check that each entry within expected +/- delta
        if(!checkWithinDelta(expected, actual, delta)){
            return false;
        }
        return true;
    }
}
