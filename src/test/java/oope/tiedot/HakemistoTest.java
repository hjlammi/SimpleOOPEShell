package oope.tiedot;

import static org.junit.Assert.*;

import org.junit.Test;

import oope2017ht.tiedot.*;

public class HakemistoTest {

    @Test
    public void toStringTesti() {
        Hakemisto root = new Hakemisto(new StringBuilder("root"), null);
        Hakemisto h = new Hakemisto(new StringBuilder("cat"), root);
        String tuloste = "cat/ 0";
        assertEquals(tuloste, h.toString());
    }

    @Test
    public void lisaaTiedosto() {
        Hakemisto root = new Hakemisto(new StringBuilder("root"), null);
        Tiedosto t = new Tiedosto(new StringBuilder("cat"), 9);
        assertEquals(true, root.lisaa(t));
        assertSame(t, root.tiedot().alkio(0));
    }

}
