package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {
    @Test
    public void testChaineVide() {
        assertTrue(StringUtils.estEquilibree(""));
    }

    @Test
    public void testSymbolesEquilibres() {
        assertTrue(StringUtils.estEquilibree("{[()]}"));
        assertTrue(StringUtils.estEquilibree("({})"));
    }

    @Test
    public void testSymbolesDesequilibres() {
        assertFalse(StringUtils.estEquilibree("]["));
        assertFalse(StringUtils.estEquilibree("([)]"));
        assertFalse(StringUtils.estEquilibree("{(}{})"));
    }

    @Test
    public void testAutresCaracteres() {
        assertTrue(StringUtils.estEquilibree("abc123"));
    }

    @Test
    public void testSymboleFermetureRedondant() {
        assertFalse(StringUtils.estEquilibree("{[}]"));
    }

    @Test
    public void testSymboleFermetureNonApparie() {
        assertFalse(StringUtils.estEquilibree("{[})"));
    }

}