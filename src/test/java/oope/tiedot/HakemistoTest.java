package oope.tiedot;

import static org.junit.Assert.*;

import org.junit.Test;

import oope2017ht.tiedot.Hakemisto;

public class HakemistoTest {

    @Test
    public void toStringTesti() {
        Hakemisto root = new Hakemisto(new StringBuilder("root"), null);
        Hakemisto h = new Hakemisto(new StringBuilder("cat"), root);
        String tuloste = "cat/ 0";
        assertEquals(tuloste, h.toString());
    }

}
