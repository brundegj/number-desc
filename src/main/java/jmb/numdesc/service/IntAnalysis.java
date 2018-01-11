/*
 * James Brundege
 * Date: 2018-01-09
 * MIT license: https://opensource.org/licenses/MIT
 */
package jmb.numdesc.service;

import java.util.ArrayList;
import java.util.List;

/**
 *  IntAnalysis derives and stores data about the structure of an integer, used for converting an integer
 *  into words. This class analyzes the number to determine if it:
 *  - is negative
 *  - will need an "and" before the word representation of the final two digits
 *
 *  It also breaks the number into triplets (ignoring the negative sign if present), starting from the least
 *  significant digits. This matches the way english number words are organized, making the conversion to words
 *  easier.
 *
 *  @see NumberDescriber
 */
class IntAnalysis {

    private final boolean isNegative;
    private final boolean requiresAnd;
    private final List<String> triplets;

    public IntAnalysis(int integer) {
        String number = Integer.toString(integer);
        isNegative = number.startsWith("-");
        String absNum = isNegative ? number.substring(1) : number;
        requiresAnd = absNum.length() > 2 && !absNum.endsWith("00");
        triplets = parseTriplets(absNum);
    }

    private List<String> parseTriplets(String absNum) {
        List<String> triples = new ArrayList<>();
        int remainingLength = absNum.length();
        while (remainingLength > 0) {
            int end = remainingLength;
            int start = remainingLength-3;
            if (start < 0) {
                start = 0;
            }
            remainingLength = start;
            triples.add(absNum.substring(start, end));
        }
        return triples;
    }

    public int numTriplets() {
        return triplets.size();
    }

    public String getTriplet(int index) {
        return triplets.get(index);
    }

    public boolean isNegative() {
        return isNegative;
    }

    public boolean requiresAnd() {
        return requiresAnd;
    }
}
