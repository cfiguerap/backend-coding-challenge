package es.adevinta.spain.friends.utils;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AlphanumericUtilsTest {

    @Test
    public void shouldBeAlphanumericUsingSimpleText() {
        assertTrue(AlphanumericUtils.isAlphanumeric("aeiouAeiou0123456789"));
    }

    @Test
    public void shouldBeNotBeAlphanumericUsingNull() {
        assertFalse(AlphanumericUtils.isAlphanumeric(""));
    }

    @Test
    public void shouldBeNotBeAlphanumericUsingEmpty() {
        assertFalse(AlphanumericUtils.isAlphanumeric(null));
    }
}
