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

    @Test
    public void lisaaTiedostoonSama() {
        Hakemisto root = new Hakemisto(new StringBuilder("root"), null);
        Tiedosto t = new Tiedosto(new StringBuilder("cat"), 9);
        root.lisaa(t);
        Tiedosto sama = new Tiedosto(new StringBuilder("cat"), 8);
        assertEquals(false, root.lisaa(sama));
        assertEquals(1, root.tiedot().koko());
    }

}
