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

    @Test
    public void lisaaTiedostoonNull() {
        Hakemisto root = new Hakemisto(new StringBuilder("root"), null);
        Tiedosto t = new Tiedosto(new StringBuilder("cat"), 9);
        root.lisaa(t);
        Tiedosto sama = null;
        assertEquals(false, root.lisaa(sama));
        assertEquals(1, root.tiedot().koko());
    }

    @Test
    public void haettavaLoytyyKunKaksiTiedostoa() {
        Hakemisto root = new Hakemisto(new StringBuilder("root"), null);
        Tiedosto t = new Tiedosto(new StringBuilder("cat"), 9);
        root.lisaa(t);
        Tiedosto t2 = new Tiedosto(new StringBuilder("dog"), 8);
        root.lisaa(t2);
        assertSame(t2, root.hae("dog"));
        assertEquals(2, root.tiedot().koko());
    }

    @Test
    public void haettavaLoytyyKunMyosHakemisto() {
        Hakemisto root = new Hakemisto(new StringBuilder("root"), null);
        Tiedosto t = new Tiedosto(new StringBuilder("cat"), 9);
        root.lisaa(t);
        Tiedosto t2 = new Tiedosto(new StringBuilder("dog"), 8);
        root.lisaa(t2);
        Hakemisto h = new Hakemisto(new StringBuilder("kitten"), root);
        root.lisaa(h);
        assertSame(t2, root.hae("dog"));
        assertEquals(3, root.tiedot().koko());
    }

    @Test
    public void haettavanNimistaEiLoydy() {
        Hakemisto root = new Hakemisto(new StringBuilder("root"), null);
        Tiedosto t = new Tiedosto(new StringBuilder("cat"), 9);
        root.lisaa(t);
        Tiedosto t2 = new Tiedosto(new StringBuilder("dog"), 8);
        root.lisaa(t2);
        Hakemisto h = new Hakemisto(new StringBuilder("kitten"), root);
        root.lisaa(h);
        assertSame(null, root.hae("doggo"));
        assertEquals(3, root.tiedot().koko());
    }

}
