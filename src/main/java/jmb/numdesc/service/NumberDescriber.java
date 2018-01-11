package jmb.numdesc.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.*;

@Service
public class NumberDescriber {

    /**
     * Returns an English word representation of the given int.
     *
     * @param num any integer
     * @return A String representation of num as English words.
     */
    public String describe(int num) {
        // English organizes number words by every 3 digits (triplets). This class will break the number
        // into triplets, starting with the least significant digits (i.e. right to left)
        IntAnalysis intAnalysis = new IntAnalysis(num);

        // Get a description for each of the triplets. The least significant digits are first in the list.
        List<String> sections = IntStream.range(0, intAnalysis.numTriplets())
                .mapToObj(index -> getDescription(intAnalysis, index))
                .collect(toList());

        // Reverse the list so the most significant digits are first, then join the triplet sections into one String.
        Collections.reverse(sections);
        String result = join(sections);

        // Handle 0 and negative numbers
        if (isBlank(result)) {
            result = NumberWords.ZERO;
        } else if (intAnalysis.isNegative()) {
            result = NumberWords.NEGATIVE + " " + result;
        }

        return StringUtils.capitalize(result);
    }

    private String getDescription(IntAnalysis intAnalysis, int tripletIndex) {
        List<String> words = new ArrayList<>();
        String triplet = intAnalysis.getTriplet(tripletIndex);

        int length = triplet.length();
        if (length == 3) {
            words.add(getHundredsWord(triplet.substring(0, 1)));
        }

        if (tripletIndex == 0 && intAnalysis.requiresAnd()) {
            words.add(NumberWords.AND);
        }

        if (triplet.length() >= 2) {
            String doublet = triplet.substring(length - 2, length);
            words.add(getDoubleDigitWord(doublet));
        } else {
            words.add(getSingleDigitWord(triplet.substring(0, 1)));
        }

        String section = join(words);
        if (isNotBlank(section)) {
            section = section + " " + getSuffix(tripletIndex);
        }
        return trim(section);
    }

    private String getHundredsWord(String digit) {
        String word = getSingleDigitWord(digit);
        if (isNotBlank(word)) {
            word = word + " " + NumberWords.HUNDRED;
        }
        return word;
    }

    private String getSingleDigitWord(String digit) {
        return NumberWords.singleDigitWords.get(digit);
    }

    private String getDoubleDigitWord(String doublet) {
        String firstDigit = doublet.substring(0, 1);
        String secondDigit = doublet.substring(1, 2);
        if ("0".equals(firstDigit)) {
            return NumberWords.singleDigitWords.get(secondDigit);
        } else if ("1".equals(firstDigit)) {
            return NumberWords.teenWords.get(doublet);
        } else {
            String firstWord = NumberWords.tensWords.get(firstDigit);
            String secondWord = NumberWords.singleDigitWords.get(secondDigit);
            if (isBlank(secondWord)) {
                return firstWord;
            } else {
                return firstWord + " " + secondWord;
            }
        }
    }

    private String getSuffix(int tripletIndex) {
        return NumberWords.tripletPositionSuffixes[tripletIndex];
    }

    private String join(List<String> words) {
        return words.stream()
                .filter(StringUtils::isNotBlank)
                .collect(joining(" "));
    }

}
