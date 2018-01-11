package jmb.numdesc.service;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberDescriberTest {

    private static Map<Integer, String> correctResults;

    private NumberDescriber numberDescriber;

    @BeforeClass
    public static void setupCorrectResults() {
        correctResults = new HashMap<>();

        // Examples given
        correctResults.put(0, "Zero");
        correctResults.put(13, "Thirteen");
        correctResults.put(85, "Eighty five");
        correctResults.put(5237, "Five thousand two hundred and thirty seven");

        // Single digits
        correctResults.put(1, "One");
        correctResults.put(9, "Nine");

        // Double digits
        correctResults.put(10, "Ten");
        correctResults.put(19, "Nineteen");
        correctResults.put(20, "Twenty");
        correctResults.put(22, "Twenty two");
        correctResults.put(87, "Eighty seven");

        // Excercise each suffix
        correctResults.put(345, "Three hundred and forty five");
        correctResults.put(6789, "Six thousand seven hundred and eighty nine");
        correctResults.put(98765, "Ninety eight thousand seven hundred and sixty five");
        correctResults.put(876543, "Eight hundred seventy six thousand five hundred and forty three");
        correctResults.put(7654321, "Seven million six hundred fifty four thousand three hundred and twenty one");
        correctResults.put(2145678901, "Two billion one hundred forty five million six hundred seventy eight thousand nine hundred and one");
        correctResults.put(Integer.MAX_VALUE, "Two billion one hundred forty seven million four hundred eighty three thousand six hundred and forty seven");

        // Some odd cases
        correctResults.put(202, "Two hundred and two");
        correctResults.put(2002, "Two thousand and two");
        correctResults.put(20002, "Twenty thousand and two");
        correctResults.put(200002, "Two hundred thousand and two");
        correctResults.put(2000002, "Two million and two");
        correctResults.put(20000002, "Twenty million and two");
        correctResults.put(2000000002, "Two billion and two");
        correctResults.put(2000000000, "Two billion");
        correctResults.put(500400300, "Five hundred million four hundred thousand three hundred");

        // Negatives
        correctResults.put(-1, "Negative one");
        correctResults.put(-13, "Negative thirteen");
        correctResults.put(-218, "Negative two hundred and eighteen");
        correctResults.put(-2145678901, "Negative two billion one hundred forty five million six hundred seventy eight thousand nine hundred and one");
        correctResults.put(-2000000002, "Negative two billion and two");
        correctResults.put(-500400300, "Negative five hundred million four hundred thousand three hundred");
        correctResults.put(Integer.MIN_VALUE, "Negative two billion one hundred forty seven million four hundred eighty three thousand six hundred and forty eight");
    }

    @Before
    public void setup() {
        numberDescriber = new NumberDescriber();
    }

    @Test
    public void testWordConversions() {
        correctResults.forEach((numeric, word) -> {
            assertThat(numberDescriber.describe(numeric)).as("%s", numeric).isEqualTo(word);
        });
    }

}
