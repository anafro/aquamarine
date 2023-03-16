package ru.anafro.lush.utils.strings;

import java.util.Comparator;

public class StringSimilarity {
    public static class StringComparator implements Comparator<String> {
        private final String originalString;

        public StringComparator(String originalString) {
            this.originalString = originalString;
        }

        public static StringComparator of(String originalString) {
            return new StringComparator(originalString);
        }

        public String getOriginalString() {
            return originalString;
        }

        @Override
        public int compare(String first, String second) {
            return Double.compare(similarity(originalString, first), similarity(originalString, second));
        }
    }

    public static double similarity(String first, String second) {
        String longer = first, shorter = second;

        if (first.length() < second.length()) {
            longer = second; shorter = first;
        }

        int longerLength = longer.length();
        if (longerLength == 0) {
            return 1.0;
        }

        return (longerLength - editDistance(longer, shorter)) / (double) longerLength;
    }

    private static int editDistance(String first, String second) {
        first = first.toLowerCase();
        second = second.toLowerCase();

        int[] costs = new int[second.length() + 1];
        for (int i = 0; i <= first.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= second.length(); j++) {
                if (i == 0)
                    costs[j] = j;
                else {
                    if (j > 0) {
                        int newValue = costs[j - 1];
                        if (first.charAt(i - 1) != second.charAt(j - 1))
                            newValue = Math.min(Math.min(newValue, lastValue),
                                    costs[j]) + 1;
                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            if (i > 0)
                costs[second.length()] = lastValue;
        }
        return costs[second.length()];
    }
}
