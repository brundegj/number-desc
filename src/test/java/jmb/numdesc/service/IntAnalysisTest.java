/*
 * James Brundege
 * Date: 2018-01-10
 * MIT license: https://opensource.org/licenses/MIT
 */
package jmb.numdesc.service;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IntAnalysisTest {

    @Test
    public void shouldHandleSingleDigit() {
        IntAnalysis intAnalysis = new IntAnalysis(0);
        assertThat(intAnalysis.isNegative()).isFalse();
        assertThat(intAnalysis.requiresAnd()).isFalse();
        assertThat(intAnalysis.numTriplets()).isEqualTo(1);
        assertThat(intAnalysis.getTriplet(0)).isEqualTo("0");
    }

    @Test
    public void shouldRequireAndWithThreeOrMoreDigits() {
        assertThat(new IntAnalysis(1).requiresAnd()).isFalse();
        assertThat(new IntAnalysis(12).requiresAnd()).isFalse();
        assertThat(new IntAnalysis(50).requiresAnd()).isFalse();

        assertThat(new IntAnalysis(123).requiresAnd()).isTrue();
        assertThat(new IntAnalysis(-123).requiresAnd()).isTrue();
        assertThat(new IntAnalysis(1234).requiresAnd()).isTrue();
        assertThat(new IntAnalysis(12340).requiresAnd()).isTrue();
    }

    @Test
    public void shouldNotRequireAndIfLastTwoDigitsAreZero() {
        assertThat(new IntAnalysis(100).requiresAnd()).isFalse();
        assertThat(new IntAnalysis(-100).requiresAnd()).isFalse();
        assertThat(new IntAnalysis(10000000).requiresAnd()).isFalse();
        assertThat(new IntAnalysis(-100200300).requiresAnd()).isFalse();
    }

    @Test
    public void shouldFlagNegativeNumbers() {
        assertThat(new IntAnalysis(0).isNegative()).isFalse();
        assertThat(new IntAnalysis(-0).isNegative()).isFalse();
        assertThat(new IntAnalysis(1).isNegative()).isFalse();
        assertThat(new IntAnalysis(465132).isNegative()).isFalse();

        assertThat(new IntAnalysis(-1).isNegative()).isTrue();
        assertThat(new IntAnalysis(-55).isNegative()).isTrue();
        assertThat(new IntAnalysis(-340978532).isNegative()).isTrue();
    }

    @Test
    public void shouldCountAndStoreTripletsFromLeastToMostSignificantDigits() {
        IntAnalysis intAnalysis = new IntAnalysis(0);
        assertThat(intAnalysis.numTriplets()).isEqualTo(1);
        assertThat(intAnalysis.getTriplet(0)).isEqualTo("0");

        intAnalysis = new IntAnalysis(123);
        assertThat(intAnalysis.numTriplets()).isEqualTo(1);
        assertThat(intAnalysis.getTriplet(0)).isEqualTo("123");

        intAnalysis = new IntAnalysis(12345);
        assertThat(intAnalysis.numTriplets()).isEqualTo(2);
        assertThat(intAnalysis.getTriplet(0)).isEqualTo("345");
        assertThat(intAnalysis.getTriplet(1)).isEqualTo("12");

        intAnalysis = new IntAnalysis(1789456123);
        assertThat(intAnalysis.numTriplets()).isEqualTo(4);
        assertThat(intAnalysis.getTriplet(0)).isEqualTo("123");
        assertThat(intAnalysis.getTriplet(1)).isEqualTo("456");
        assertThat(intAnalysis.getTriplet(2)).isEqualTo("789");
        assertThat(intAnalysis.getTriplet(3)).isEqualTo("1");
    }

    @Test
    public void shouldIgnoreNegativeSymbolForCountingTriplets() {
        IntAnalysis intAnalysis = new IntAnalysis(0);
        assertThat(intAnalysis.numTriplets()).isEqualTo(1);
        assertThat(intAnalysis.getTriplet(-0)).isEqualTo("0");

        intAnalysis = new IntAnalysis(-123);
        assertThat(intAnalysis.numTriplets()).isEqualTo(1);
        assertThat(intAnalysis.getTriplet(0)).isEqualTo("123");

        intAnalysis = new IntAnalysis(-12345);
        assertThat(intAnalysis.numTriplets()).isEqualTo(2);
        assertThat(intAnalysis.getTriplet(0)).isEqualTo("345");
        assertThat(intAnalysis.getTriplet(1)).isEqualTo("12");

        intAnalysis = new IntAnalysis(-1789456123);
        assertThat(intAnalysis.numTriplets()).isEqualTo(4);
        assertThat(intAnalysis.getTriplet(0)).isEqualTo("123");
        assertThat(intAnalysis.getTriplet(1)).isEqualTo("456");
        assertThat(intAnalysis.getTriplet(2)).isEqualTo("789");
        assertThat(intAnalysis.getTriplet(3)).isEqualTo("1");
    }

}