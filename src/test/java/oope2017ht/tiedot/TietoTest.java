package oope2017ht.tiedot;

import static org.junit.Assert.*;

import org.junit.Test;

import oope2017ht.tiedot.Tieto;

public class TietoTest {

    /*
     * Testataan onko Stringbuilder nimi oikeanlainen.
     */

    @Test
    public void tyhja() {
        StringBuilder s = new StringBuilder();
        assertEquals(false, Tieto.nimiOk(s));
    }

    @Test
    public void plusmerkkiNimessa() {
        StringBuilder s = new StringBuilder("jooo+");
        assertEquals(false, Tieto.vainSallittujaMerkkeja(s));
    }

    @Test
    public void kaksiVaaraaNimessa() {
        StringBuilder s = new StringBuilder("j&oo+o");
        assertEquals(false, Tieto.vainSallittujaMerkkeja(s));
    }

    @Test
    public void numerotSallittuja() {
        StringBuilder s = new StringBuilder("j1ooo2");
        assertEquals(true, Tieto.vainSallittujaMerkkeja(s));
    }

    @Test
    public void alaviivaSallittu() {
        StringBuilder s = new StringBuilder("j1oo_o");
        assertEquals(true, Tieto.vainSallittujaMerkkeja(s));
    }

    @Test
    public void yksiPisteSallittu() {
        StringBuilder s = new StringBuilder("j1oo.o");
        assertEquals(true, Tieto.pisteitaMaxYksi(s));
    }

    @Test
    public void kaksiPistetta() {
        StringBuilder s = new StringBuilder("j1o.o.o");
        assertEquals(false, Tieto.pisteitaMaxYksi(s));
    }

    @Test
    public void pelkkaPiste() {
        StringBuilder s = new StringBuilder(".");
        assertEquals(false, Tieto.pisteitaMaxYksi(s));
    }

    @Test
    public void pelkkaPisteNimessa() {
        StringBuilder s = new StringBuilder(".");
        assertEquals(false, Tieto.nimiOk(s));
    }

    @Test
    public void oikeantyyppinenNimi() {
        StringBuilder s = new StringBuilder("joku1_joku1.txt");
        assertEquals(true, Tieto.nimiOk(s));
    }

    @Test
    public void liikaaPisteita() {
        StringBuilder s = new StringBuilder("joku1.joku1.txt");
        assertEquals(false, Tieto.nimiOk(s));
    }

    @Test
    public void yksiKauttaviiva() {
        Hakemisto h = new Hakemisto();
        assertEquals("/", h.nimi().toString());
    }

    @Test
    public void kaksiKauttaviivaa() {
        StringBuilder s = new StringBuilder("//");
        assertEquals(false, Tieto.nimiOk(s));
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullNimi() {
        Tiedosto t = new Tiedosto(new StringBuilder("lol"), 123);
        t.nimi(null);
    }

    @Test
    public void verrataanTietoaStringBuilderiin() {
        StringBuilder s = new StringBuilder();
        s.append("joku1.txt");
        Tiedosto t = new Tiedosto(s, 1);
        assertEquals(false, t.equals(s));
    }

}
