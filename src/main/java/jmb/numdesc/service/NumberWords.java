/*
 * James Brundege
 * Date: 2018-01-09
 * MIT license: https://opensource.org/licenses/MIT
 */
package jmb.numdesc.service;

import java.util.HashMap;
import java.util.Map;

/**
 *  NumberWords holds all the individual words needed to represent any int value as words.
 *
 *  @see NumberDescriber
 */
public class NumberWords {

    public static final Map<String, String> singleDigitWords = new HashMap<>();
    public static final Map<String, String> teenWords = new HashMap<>();
    public static final Map<String, String> tensWords = new HashMap<>();
    public static final String[] tripletPositionSuffixes = {"", "thousand", "million", "billion"};
    public static final String HUNDRED = "hundred";
    public static final String ZERO = "zero";
    public static final String NEGATIVE = "negative";
    public static final String AND = "and";

    static {
        singleDigitWords.put("0", "");
        singleDigitWords.put("1", "one");
        singleDigitWords.put("2", "two");
        singleDigitWords.put("3", "three");
        singleDigitWords.put("4", "four");
        singleDigitWords.put("5", "five");
        singleDigitWords.put("6", "six");
        singleDigitWords.put("7", "seven");
        singleDigitWords.put("8", "eight");
        singleDigitWords.put("9", "nine");

        teenWords.put("10", "ten");
        teenWords.put("11", "eleven");
        teenWords.put("12", "twelve");
        teenWords.put("13", "thirteen");
        teenWords.put("14", "fourteen");
        teenWords.put("15", "fifteen");
        teenWords.put("16", "sixteen");
        teenWords.put("17", "seventeen");
        teenWords.put("18", "eighteen");
        teenWords.put("19", "nineteen");

        tensWords.put("0", "");
        tensWords.put("2", "twenty");
        tensWords.put("3", "thirty");
        tensWords.put("4", "forty");
        tensWords.put("5", "fifty");
        tensWords.put("6", "sixty");
        tensWords.put("7", "seventy");
        tensWords.put("8", "eighty");
        tensWords.put("9", "ninety");
    }
}
