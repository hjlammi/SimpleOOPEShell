package oope2017ht;

import static org.junit.Assert.*;

import org.junit.Test;

import oope2017ht.Tulkki;

public class TulkkiTest {

    // Tulkki näyttää käynnistyessään welcome-viestin.
    @Test
    public void testWelcome() {
        TestiUI ui = new TestiUI();
        ui.syotteet.lisaaLoppuun("exit");
        Tulkki tulkki = new Tulkki(ui);

        tulkki.suorita();

        assertEquals(1, ui.tulosteet.koko());
        assertEquals("Welcome to SOS.", ui.tulosteet.alkio(0));
    }

    // Tulkki näyttää virheviestin väärästä komennosta.
    @Test
    public void testError() {
        TestiUI ui = new TestiUI();
        ui.syotteet.lisaaLoppuun("hjkl");
        ui.syotteet.lisaaLoppuun("ghfdhfsx");
        ui.syotteet.lisaaLoppuun("exit");
        Tulkki tulkki = new Tulkki(ui);

        tulkki.suorita();

        assertEquals(3, ui.tulosteet.koko());
        assertEquals("Error!", ui.tulosteet.alkio(1));
        assertEquals("Error!", ui.tulosteet.alkio(2));
    }

}