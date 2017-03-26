package oope.tiedot;

import static org.junit.Assert.*;

import org.junit.Test;

import oope2017ht.tiedot.Tieto;

public class TietoTest {

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
}
