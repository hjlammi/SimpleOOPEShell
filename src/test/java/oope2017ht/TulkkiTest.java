package oope2017ht;

import static org.junit.Assert.*;

import org.junit.Test;

import oope2017ht.Tulkki;
import oope2017ht.tiedot.Hakemisto;
import oope2017ht.tiedot.Tiedosto;

public class TulkkiTest {

    // Tulkki näyttää käynnistyessään welcome-viestin.
    @Test
    public void testWelcome() {
        TestiUI ui = new TestiUI();
        ui.syotteet.lisaaLoppuun("exit");
        Tulkki tulkki = new Tulkki(ui);

        tulkki.suorita();

        assertEquals(2, ui.tulosteet.koko());
        assertEquals("Welcome to SOS.", ui.tulosteet.alkio(0));
    }

    // Tulkki näyttää virheviestin väärästä komennosta.
    @Test
    public void testError() {
        TestiUI ui = new TestiUI();
        ui.syotteet.lisaaLoppuun("hjkl");
        ui.syotteet.lisaaLoppuun("ghfdhfsx");
        ui.syotteet.lisaaLoppuun("exit ");
        ui.syotteet.lisaaLoppuun("exit");
        Tulkki tulkki = new Tulkki(ui);

        tulkki.suorita();

        assertEquals(5, ui.tulosteet.koko());
        assertEquals("Error!", ui.tulosteet.alkio(1));
        assertEquals("Error!", ui.tulosteet.alkio(2));
        assertEquals("Error!", ui.tulosteet.alkio(3));
        assertEquals("Shell terminated.", ui.tulosteet.alkio(4));
    }

    // Tulkki näyttää lopetusviestin.
    @Test
    public void testExit() {
        TestiUI ui = new TestiUI();
        ui.syotteet.lisaaLoppuun("exit");
        Tulkki tulkki = new Tulkki(ui);

        tulkki.suorita();

        assertEquals(2, ui.tulosteet.koko());
        assertEquals("Shell terminated.", ui.tulosteet.alkio(1));
    }

    // Juurihakemisto on tyhjä.
    @Test
    public void testLsEmptyRoot() {
        TestiUI ui = new TestiUI();
        ui.syotteet.lisaaLoppuun("ls");
        ui.syotteet.lisaaLoppuun("exit");
        Tulkki tulkki = new Tulkki(ui);

        tulkki.suorita();

        assertEquals(2, ui.tulosteet.koko());
    }

    // Ls listaa alihakemistot ja tiedostot.
    @Test
    public void testLsDirsAndFiles() {
        TestiUI ui = new TestiUI();
        ui.syotteet.lisaaLoppuun("ls");
        ui.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("root"), null);
        Hakemisto foo = new Hakemisto(new StringBuilder("foo"), juurihakemisto);
        juurihakemisto.lisaa(foo);
        Tiedosto jee = new Tiedosto(new StringBuilder("jee.txt"), 11);
        juurihakemisto.lisaa(jee);
        Tulkki tulkki = new Tulkki(ui, juurihakemisto);

        tulkki.suorita();

        assertEquals(4, ui.tulosteet.koko());
        assertEquals("foo/ 0", ui.tulosteet.alkio(1));
        assertEquals("jee.txt 11", ui.tulosteet.alkio(2));
    }

    @Test
    public void testMdNewDir() {
        TestiUI ui = new TestiUI();
        ui.syotteet.lisaaLoppuun("md foo");
        ui.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("root"), null);
        Tulkki tulkki = new Tulkki(ui, juurihakemisto);

        tulkki.suorita();

        assertEquals(1, juurihakemisto.tiedot().koko());
        Hakemisto alihakemisto = (Hakemisto)juurihakemisto.tiedot().alkio(0);
        assertEquals("foo", alihakemisto.nimi().toString());
        assertEquals(juurihakemisto, alihakemisto.ylihakemisto());
    }

    // Tulkki käynnistyy juurihakemistoon.


}
