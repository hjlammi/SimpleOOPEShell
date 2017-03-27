package oope.tiedot;

import static org.junit.Assert.*;

import oope2017ht.tiedot.Tiedosto;

import org.junit.Test;

public class TiedostoTest {

    @Test
    public void toStringTesti() {
        Tiedosto t = new Tiedosto(new StringBuilder("cat"), 7);
        String tuloste = "cat 7";
        assertEquals(tuloste, t.toString());
    }

    @Test
    public void equalsSamaNimiTesti() {
        Tiedosto t1 = new Tiedosto(new StringBuilder("cat"), 7);
        Tiedosto t2 = new Tiedosto(new StringBuilder("cat"), 8);
        assertEquals(true, t1.equals(t2));
    }

    @Test
    public void equalsEriNimiTesti() {
        Tiedosto t1 = new Tiedosto(new StringBuilder("cat"), 7);
        Tiedosto t2 = new Tiedosto(new StringBuilder("dog"), 8);
        assertEquals(false, t1.equals(t2));
    }

    @Test
    public void compareToSuurempiTesti() {
        Tiedosto t1 = new Tiedosto(new StringBuilder("cat"), 7);
        Tiedosto t2 = new Tiedosto(new StringBuilder("dog"), 8);
        assertEquals(-1, t1.compareTo(t2));
    }



}
