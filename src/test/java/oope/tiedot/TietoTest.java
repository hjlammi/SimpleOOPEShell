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

}
