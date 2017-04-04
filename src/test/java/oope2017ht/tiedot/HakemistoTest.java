package oope2017ht.tiedot;

import static org.junit.Assert.*;

import org.junit.Test;

public class HakemistoTest {

    @Test
    public void toStringTesti() {
        Hakemisto root = new Hakemisto(new StringBuilder("root"), null);
        Hakemisto h = new Hakemisto(new StringBuilder("cat"), root);
        String tuloste = "cat/ 0";
        assertEquals(tuloste, h.toString());
    }

    // lisaa() method in Hakemisto

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

    // hae method in Hakemisto

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

    @Test
    public void haettavaOnNull() {
        Hakemisto root = new Hakemisto(new StringBuilder("root"), null);
        Tiedosto t = new Tiedosto(new StringBuilder("cat"), 9);
        root.lisaa(t);
        Tiedosto t2 = new Tiedosto(new StringBuilder("dog"), 8);
        root.lisaa(t2);
        Hakemisto h = new Hakemisto(new StringBuilder("kitten"), root);
        root.lisaa(h);
        assertSame(null, root.hae(null));
        assertEquals(3, root.tiedot().koko());
    }

    // poista method in Hakemisto

    @Test
    public void poistetaanToinenTiedostoista() {
        Hakemisto root = new Hakemisto(new StringBuilder("root"), null);
        Tiedosto t = new Tiedosto(new StringBuilder("cat"), 9);
        root.lisaa(t);
        Tiedosto t2 = new Tiedosto(new StringBuilder("dog"), 8);
        root.lisaa(t2);
        Hakemisto h = new Hakemisto(new StringBuilder("kitten"), root);
        root.lisaa(h);
        assertSame(t2, root.poista("dog"));
        assertEquals(2, root.tiedot().koko());
        assertEquals(t, root.tiedot().alkio(0));
        assertEquals(h, root.tiedot().alkio(1));
    }

    @Test
    public void poistettavaaeiLoydy() {
        Hakemisto root = new Hakemisto(new StringBuilder("root"), null);
        Tiedosto t = new Tiedosto(new StringBuilder("cat"), 9);
        root.lisaa(t);
        Tiedosto t2 = new Tiedosto(new StringBuilder("dog"), 8);
        root.lisaa(t2);
        Hakemisto h = new Hakemisto(new StringBuilder("kitten"), root);
        root.lisaa(h);
        assertSame(null, root.poista("doggo"));
        assertEquals(3, root.tiedot().koko());
        assertEquals(t, root.tiedot().alkio(0));
        assertEquals(t2, root.tiedot().alkio(1));
        assertEquals(h, root.tiedot().alkio(2));
    }

    @Test
    public void poistetaanHakemisto() {
        Hakemisto root = new Hakemisto(new StringBuilder("root"), null);
        Tiedosto t = new Tiedosto(new StringBuilder("cat"), 9);
        root.lisaa(t);
        Tiedosto t2 = new Tiedosto(new StringBuilder("dog"), 8);
        root.lisaa(t2);
        Hakemisto h = new Hakemisto(new StringBuilder("kitten"), root);
        root.lisaa(h);
        Tiedosto t3 = new Tiedosto(new StringBuilder("kitty"), 7);
        h.lisaa(t3);
        assertSame(h, root.poista("kitten"));
        assertEquals(2, root.tiedot().koko());
        assertEquals(t, root.tiedot().alkio(0));
        assertEquals(t2, root.tiedot().alkio(1));
    }

    @Test
    public void poistettavaOnNull() {
        Hakemisto root = new Hakemisto(new StringBuilder("root"), null);
        Tiedosto t = new Tiedosto(new StringBuilder("cat"), 9);
        root.lisaa(t);
        assertSame(null, root.poista(null));
        assertEquals(1, root.tiedot().koko());
        assertEquals(t, root.tiedot().alkio(0));
    }
}
