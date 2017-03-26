package oope.tiedot;

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
        StringBuilder s = new StringBuilder();
        s.append("jooo+");
        assertEquals(false, Tieto.vainSallittujaMerkkeja(s));
    }

    @Test
    public void kaksiVaaraaNimessa() {
        StringBuilder s = new StringBuilder();
        s.append("j&oo+o");
        assertEquals(false, Tieto.vainSallittujaMerkkeja(s));
    }

    @Test
    public void numerotSallittuja() {
        StringBuilder s = new StringBuilder();
        s.append("j1ooo2");
        assertEquals(true, Tieto.vainSallittujaMerkkeja(s));
    }

    @Test
    public void alaviivaSallittu() {
        StringBuilder s = new StringBuilder();
        s.append("j1oo_o");
        assertEquals(true, Tieto.vainSallittujaMerkkeja(s));
    }

    @Test
    public void yksiPisteSallittu() {
        StringBuilder s = new StringBuilder();
        s.append("j1oo.o");
        assertEquals(true, Tieto.pisteitaMaxYksi(s));
    }

    @Test
    public void kaksiPistetta() {
        StringBuilder s = new StringBuilder();
        s.append("j1o.o.o");
        assertEquals(false, Tieto.pisteitaMaxYksi(s));
    }

    @Test
    public void pelkkaPiste() {
        StringBuilder s = new StringBuilder();
        s.append(".");
        assertEquals(false, Tieto.pisteitaMaxYksi(s));
    }

    @Test
    public void pelkkaPisteNimessa() {
        StringBuilder s = new StringBuilder();
        s.append(".");
        assertEquals(false, Tieto.nimiOk(s));
    }

    @Test
    public void oikeantyyppinenNimi() {
        StringBuilder s = new StringBuilder();
        s.append("joku1_joku1.txt");
        assertEquals(true, Tieto.nimiOk(s));
    }

    @Test
    public void liikaaPisteita() {
        StringBuilder s = new StringBuilder();
        s.append("joku1.joku1.txt");
        assertEquals(false, Tieto.nimiOk(s));
    }
}
