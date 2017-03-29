package oope;

import static org.junit.Assert.*;

import org.junit.Test;

import oope2017ht.Tulkki;

public class TulkkiTest {

    @Test
    public void testWelcome() {
        TestiUI ui = new TestiUI();
        Tulkki tulkki = new Tulkki(ui);
        tulkki.suorita();
        assertEquals(1, ui.tulosteet.koko());
        assertEquals("Welcome to SOS.", ui.tulosteet.alkio(0));
    }

}
