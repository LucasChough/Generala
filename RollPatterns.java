public class RollPatterns {

    public static int[] countFrequencies(int[] cup) { // count the frequencies of a number in a roll and store it in an
                                                      // array
        int[] frequencies = new int[6]; // array to store frequencies (1-6)
        for (int i = 0; i < cup.length; i++) {
            frequencies[cup[i] - 1]++; // add one to index in frequencie array corresponding to the number rolled
        }
        return frequencies;
    } // end countFrquencies

    public static boolean isDoubleGenerala(int[] frequencies) {
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] == 5) {
                // Assuming that Double Generala means having two sets of five of a kind
                return true; // Placeholder: Add logic for Double Generala if needed
            } // end if
        } // end for
        return false;
    } // end isDoubleGenerala

    public static boolean isGenerala(int[] frequencies) {
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] == 5) {
                return true;
            } // end if
        } // end for
        return false;
    } // end isGenerala

    public static boolean isPoker(int[] frequencies) {
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] == 4) {
                return true;
            } // end if
        } // end for
        return false;
    } // end isPoker

    public static boolean isFull(int[] frequencies) {
        boolean hasThree = false;
        boolean hasTwo = false;
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] == 3) {
                hasThree = true;
            } // end if
            if (frequencies[i] == 2) {
                hasTwo = true;
            } // end if
        } // end for
        return hasThree && hasTwo;
    } // end isFull

    public static boolean isStraight(int[] frequencies) {
        // Check for straights: (1, 2, 3, 4, 5), (2, 3, 4, 5, 6), (6, 1, 2, 3, 4), (3,
        // 4, 5, 6, 1)
        return (frequencies[0] == 1 && frequencies[1] == 1 && frequencies[2] == 1 && frequencies[3] == 1
                && frequencies[4] == 1) || // (1, 2, 3, 4, 5)
                (frequencies[1] == 1 && frequencies[2] == 1 && frequencies[3] == 1 && frequencies[4] == 1
                        && frequencies[5] == 1)
                || // (2, 3, 4, 5, 6)
                (frequencies[5] == 1 && frequencies[0] == 1 && frequencies[1] == 1 && frequencies[2] == 1
                        && frequencies[3] == 1)
                || // (6, 1, 2, 3, 4)
                (frequencies[2] == 1 && frequencies[3] == 1 && frequencies[4] == 1 && frequencies[5] == 1
                        && frequencies[0] == 1); // (3, 4, 5, 6, 1)
    } // end isStraight

} // end RollPatterns
