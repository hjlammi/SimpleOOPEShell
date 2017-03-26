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

}
