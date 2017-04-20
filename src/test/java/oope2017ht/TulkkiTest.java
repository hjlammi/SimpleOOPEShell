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
        ui.syotteet.lisaaLoppuun("md   pics");
        ui.syotteet.lisaaLoppuun("exit ");
        ui.syotteet.lisaaLoppuun(" exit");
        ui.syotteet.lisaaLoppuun("exit");
        Tulkki tulkki = new Tulkki(ui);

        tulkki.suorita();

        assertEquals(6, ui.tulosteet.koko());
        assertEquals("Error!", ui.tulosteet.alkio(1));
        assertEquals("Error!", ui.tulosteet.alkio(2));
        assertEquals("Error!", ui.tulosteet.alkio(3));
        assertEquals("Error!", ui.tulosteet.alkio(4));
        assertEquals("Shell terminated.", ui.tulosteet.alkio(5));
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
        Tiedosto jee = new Tiedosto(new StringBuilder("jee.txt"), 11);
        juurihakemisto.lisaa(jee);
        Hakemisto foo = new Hakemisto(new StringBuilder("foo"), juurihakemisto);
        juurihakemisto.lisaa(foo);
        Tiedosto t = new Tiedosto(new StringBuilder("foo.txt"), 123);
        foo.lisaa(t);
        Tulkki tulkki = new Tulkki(ui, juurihakemisto);
        tulkki.tyohakemisto(foo);

        tulkki.suorita();

        assertEquals(3, ui.tulosteet.koko());
        assertEquals("foo.txt 123", ui.tulosteet.alkio(1));
        assertEquals("foo", tulkki.tyohakemisto().nimi().toString());
    }

    @Test
    public void testLsWithAnArgument() {
        TestiUI ui = new TestiUI();
        ui.syotteet.lisaaLoppuun("ls jee.txt");
        ui.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("root"), null);
        Hakemisto foo = new Hakemisto(new StringBuilder("foo"), juurihakemisto);
        juurihakemisto.lisaa(foo);
        Tiedosto jee = new Tiedosto(new StringBuilder("jee.txt"), 11);
        foo.lisaa(jee);
        Tulkki tulkki = new Tulkki(ui, juurihakemisto);
        tulkki.tyohakemisto(foo);

        tulkki.suorita();

        assertEquals(3, ui.tulosteet.koko());
        assertEquals("jee.txt 11", ui.tulosteet.alkio(1));
    }

    @Test
    public void testLsWithNonexistentName() {
        TestiUI ui = new TestiUI();
        ui.syotteet.lisaaLoppuun("ls joo.txt");
        ui.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("root"), null);
        Hakemisto foo = new Hakemisto(new StringBuilder("foo"), juurihakemisto);
        juurihakemisto.lisaa(foo);
        Tiedosto jee = new Tiedosto(new StringBuilder("jee.txt"), 11);
        juurihakemisto.lisaa(jee);
        Tulkki tulkki = new Tulkki(ui, juurihakemisto);

        tulkki.suorita();

        assertEquals(3, ui.tulosteet.koko());
        assertEquals("Error!", ui.tulosteet.alkio(1));
    }

    @Test
    public void testMdNewDir() {
        TestiUI ui = new TestiUI();
        ui.syotteet.lisaaLoppuun("md bar");
        ui.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("root"), null);
        Hakemisto foo = new Hakemisto(new StringBuilder("foo"), juurihakemisto);
        juurihakemisto.lisaa(foo);
        Tulkki tulkki = new Tulkki(ui, juurihakemisto);
        tulkki.tyohakemisto(foo);

        tulkki.suorita();

        assertEquals(1, tulkki.tyohakemisto().tiedot().koko());
        Hakemisto alihakemisto = (Hakemisto)tulkki.tyohakemisto().tiedot().alkio(0);
        assertEquals("bar", alihakemisto.nimi().toString());
        assertEquals(foo, alihakemisto.ylihakemisto());
    }

    // Samanniminen hakemisto on jo.
    @Test
    public void testMdWithSameName() {
        TestiUI ui = new TestiUI();
        ui.syotteet.lisaaLoppuun("md foo");
        ui.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("root"), null);
        Hakemisto foo = new Hakemisto(new StringBuilder("foo"), juurihakemisto);
        juurihakemisto.lisaa(foo);
        Tulkki tulkki = new Tulkki(ui, juurihakemisto);

        tulkki.suorita();

        assertEquals(1, tulkki.tyohakemisto().tiedot().koko());
        assertEquals("Error!", ui.tulosteet.alkio(1));
    }

    // Luodaan uusi tiedosto.
    @Test
    public void testMfNewFile() {
        TestiUI ui = new TestiUI();
        ui.syotteet.lisaaLoppuun("mf foo.txt 123");
        ui.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("root"), null);
        Hakemisto foo = new Hakemisto(new StringBuilder("foo"), juurihakemisto);
        juurihakemisto.lisaa(foo);
        Tulkki tulkki = new Tulkki(ui, juurihakemisto);
        tulkki.tyohakemisto(foo);

        tulkki.suorita();

        assertEquals(1, tulkki.tyohakemisto().tiedot().koko());
        Tiedosto lisatty = (Tiedosto)tulkki.tyohakemisto().tiedot().alkio(0);
        assertEquals("foo.txt", lisatty.nimi().toString());
    }

    // Liikaa välilyöntejä ja samanniminen tiedosto on jo olemassa.
    @Test
    public void testMfWithSpaces() {
        TestiUI ui = new TestiUI();
        ui.syotteet.lisaaLoppuun("mf foo.txt 123");
        ui.syotteet.lisaaLoppuun("mf jee.txt  123");
        ui.syotteet.lisaaLoppuun("mf  jee.txt 123");
        ui.syotteet.lisaaLoppuun("mf foo.txt 123");
        ui.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("root"), null);
        Tulkki tulkki = new Tulkki(ui, juurihakemisto);

        tulkki.suorita();

        assertEquals(1, juurihakemisto.tiedot().koko());
        assertEquals("Error!", ui.tulosteet.alkio(1));
        assertEquals("Error!", ui.tulosteet.alkio(3));
    }

    // Koon täytyy olla <= 0.
    @Test
    public void testMfNegativeSize() {
        TestiUI ui = new TestiUI();
        ui.syotteet.lisaaLoppuun("mf foo.txt -123");
        ui.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("root"), null);
        Tulkki tulkki = new Tulkki(ui, juurihakemisto);

        tulkki.suorita();

        assertEquals(0, juurihakemisto.tiedot().koko());
        assertEquals("Error!", ui.tulosteet.alkio(1));
    }

    // Nimetään tieto uudelleen.
    @Test
    public void testMvRename() {
        TestiUI ui = new TestiUI();
        ui.syotteet.lisaaLoppuun("mv fool.txt foo.txt");
        ui.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("root"), null);
        Hakemisto foo = new Hakemisto(new StringBuilder("foo"), juurihakemisto);
        Tiedosto foo2 = new Tiedosto(new StringBuilder("foo.txt"), 234);
        juurihakemisto.lisaa(foo2);
        Hakemisto bar = new Hakemisto(new StringBuilder("bar"), foo);
        foo.lisaa(bar);
        Tiedosto fool = new Tiedosto(new StringBuilder("fool.txt"), 123);
        bar.lisaa(fool);
        Tulkki tulkki = new Tulkki(ui, juurihakemisto);
        tulkki.tyohakemisto(bar);

        tulkki.suorita();

        assertEquals(1, tulkki.tyohakemisto().tiedot().koko());
        Tiedosto uusiNimi = (Tiedosto)tulkki.tyohakemisto().tiedot().alkio(0);
        assertEquals("foo.txt", uusiNimi.nimi().toString());
    }

    // Uusi nimi ei saa olla jo käytössä hakemistossa.
    @Test
    public void testMvRenameWithExistingName() {
        TestiUI ui = new TestiUI();
        ui.syotteet.lisaaLoppuun("mv fool.txt foo.txt");
        ui.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("root"), null);
        Tiedosto fool = new Tiedosto(new StringBuilder("fool.txt"), 123);
        juurihakemisto.lisaa(fool);
        Tiedosto foo = new Tiedosto(new StringBuilder("foo.txt"), 243);
        juurihakemisto.lisaa(foo);
        Tulkki tulkki = new Tulkki(ui, juurihakemisto);

        tulkki.suorita();

        assertEquals(2, juurihakemisto.tiedot().koko());
        assertEquals("Error!", ui.tulosteet.alkio(1));
        Tiedosto vaihdettava = (Tiedosto)juurihakemisto.tiedot().alkio(1);
        assertEquals("fool.txt", vaihdettava.nimi().toString());
    }

    // Vaihdettavan nimistä ei ole.
    @Test
    public void testMvRenameWithWrongName() {
        TestiUI ui = new TestiUI();
        ui.syotteet.lisaaLoppuun("mv fool.txt foo.txt");
        ui.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("root"), null);
        Tiedosto cats = new Tiedosto(new StringBuilder("cats.txt"), 123);
        juurihakemisto.lisaa(cats);
        Tulkki tulkki = new Tulkki(ui, juurihakemisto);

        tulkki.suorita();

        assertEquals(1, tulkki.tyohakemisto().tiedot().koko());
        assertEquals("Error!", ui.tulosteet.alkio(1));
        Tiedosto vaihdettava = (Tiedosto)tulkki.tyohakemisto().tiedot().alkio(0);
        assertEquals("cats.txt", vaihdettava.nimi().toString());
    }

    // Kopioidaan tiedosto.
    @Test
    public void testCpCopyFile() {
        TestiUI ui = new TestiUI();
        ui.syotteet.lisaaLoppuun("cp fool.txt foo.txt");
        ui.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("root"), null);
        Hakemisto foo = new Hakemisto(new StringBuilder("foo"), juurihakemisto);
        juurihakemisto.lisaa(foo);
        Tiedosto fool = new Tiedosto(new StringBuilder("fool.txt"), 123);
        foo.lisaa(fool);
        Tulkki tulkki = new Tulkki(ui, juurihakemisto);
        tulkki.tyohakemisto(foo);

        tulkki.suorita();

        assertEquals(2, tulkki.tyohakemisto().tiedot().koko());
        Tiedosto kopio = (Tiedosto)tulkki.tyohakemisto().tiedot().alkio(0);
        assertEquals("foo.txt", kopio.nimi().toString());
    }

    // Hakemistoa ei voi kopioida.
    @Test
    public void testCpCopyDir() {
        TestiUI ui = new TestiUI();
        ui.syotteet.lisaaLoppuun("cp fool foo");
        ui.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("root"), null);
        Hakemisto fool = new Hakemisto(new StringBuilder("foo"), juurihakemisto);
        juurihakemisto.lisaa(fool);
        Tulkki tulkki = new Tulkki(ui, juurihakemisto);

        tulkki.suorita();

        assertEquals(1, juurihakemisto.tiedot().koko());
        assertEquals("Error!", ui.tulosteet.alkio(1));
    }

    // Kopioitavan nimistä ei ole hakemistossa.
    @Test
    public void testCpFileNotFound() {
        TestiUI ui = new TestiUI();
        ui.syotteet.lisaaLoppuun("cp fool.txt foo.txt");
        ui.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("root"), null);
        Tiedosto cat = new Tiedosto(new StringBuilder("cat.txt"), 123);
        juurihakemisto.lisaa(cat);
        Tulkki tulkki = new Tulkki(ui, juurihakemisto);

        tulkki.suorita();

        assertEquals(1, juurihakemisto.tiedot().koko());
        assertEquals("Error!", ui.tulosteet.alkio(1));
    }

    // Kopion nimi varattu.
    @Test
    public void testCpCopyNameTaken() {
        TestiUI ui = new TestiUI();
        ui.syotteet.lisaaLoppuun("cp cat.txt kitten.txt");
        ui.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("root"), null);
        Tiedosto cat = new Tiedosto(new StringBuilder("cat.txt"), 123);
        juurihakemisto.lisaa(cat);
        Tiedosto kitten = new Tiedosto(new StringBuilder("kitten.txt"), 123);
        juurihakemisto.lisaa(kitten);
        Tulkki tulkki = new Tulkki(ui, juurihakemisto);

        tulkki.suorita();

        assertEquals(2, juurihakemisto.tiedot().koko());
        assertEquals("Error!", ui.tulosteet.alkio(1));
    }

    // Tiedoston poistaminen.
    @Test
    public void testRmRemoveFile() {
        TestiUI ui = new TestiUI();
        ui.syotteet.lisaaLoppuun("rm cat.txt");
        ui.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("root"), null);
        Hakemisto foo = new Hakemisto(new StringBuilder("foo"), juurihakemisto);
        juurihakemisto.lisaa(foo);
        Tiedosto cat = new Tiedosto(new StringBuilder("cat.txt"), 123);
        foo.lisaa(cat);
        Tulkki tulkki = new Tulkki(ui, juurihakemisto);
        tulkki.tyohakemisto(foo);

        tulkki.suorita();

        assertEquals(0, tulkki.tyohakemisto().tiedot().koko());
        assertEquals("foo", tulkki.tyohakemisto().nimi().toString());
    }

    // Hakemiston poistaminen.
    @Test
    public void testRmRemoveDir() {
        TestiUI ui = new TestiUI();
        ui.syotteet.lisaaLoppuun("rm cat");
        ui.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("root"), null);
        Hakemisto cat = new Hakemisto(new StringBuilder("cat"), juurihakemisto);
        juurihakemisto.lisaa(cat);
        Tulkki tulkki = new Tulkki(ui, juurihakemisto);

        tulkki.suorita();

        assertEquals(0, juurihakemisto.tiedot().koko());
    }

    // Poisto ei onnistu, jos hakemistoa tai tiedostoa ei ole.
    @Test
    public void testRmNothingToRemove() {
        TestiUI ui = new TestiUI();
        ui.syotteet.lisaaLoppuun("rm cat");
        ui.syotteet.lisaaLoppuun("rm cat");
        ui.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("root"), null);
        Hakemisto kitten = new Hakemisto(new StringBuilder("kitten"), juurihakemisto);
        juurihakemisto.lisaa(kitten);
        Tiedosto cat = new Tiedosto(new StringBuilder("cat.jpg"), 234);
        juurihakemisto.lisaa(cat);
        Tulkki tulkki = new Tulkki(ui, juurihakemisto);

        tulkki.suorita();

        assertEquals(2, juurihakemisto.tiedot().koko());
        assertEquals("Error!", ui.tulosteet.alkio(1));
        assertEquals("Error!", ui.tulosteet.alkio(2));
    }

    // Siirrytään juurihakemiston alihakemistoon.
    @Test
    public void testCdChangeToSubdir() {
        TestiUI ui = new TestiUI();
        // Tiedostoon ei voi siirtyä.
        ui.syotteet.lisaaLoppuun("cd kitten123.jpg");
        ui.syotteet.lisaaLoppuun("cd kitten");
        // Virheellinen alihakemiston nimi.
        ui.syotteet.lisaaLoppuun("cd kiten");
        ui.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("root"), null);
        Hakemisto kitten = new Hakemisto(new StringBuilder("kitten"), juurihakemisto);
        juurihakemisto.lisaa(kitten);
        Tiedosto t = new Tiedosto(new StringBuilder("kitten123.jpg"), 123);
        kitten.lisaa(t);
        Tulkki tulkki = new Tulkki(ui, juurihakemisto);

        tulkki.suorita();

        assertEquals(1, tulkki.tyohakemisto().tiedot().koko());
        assertEquals("Error!", ui.tulosteet.alkio(1));
        assertEquals("Error!", ui.tulosteet.alkio(2));
        assertEquals("kitten", tulkki.tyohakemisto().nimi().toString());
    }

    // Siirrytään ylihakemistoon.
    @Test
    public void testCdChangeToParentDir() {
        TestiUI ui = new TestiUI();
        ui.syotteet.lisaaLoppuun("cd ..");
        ui.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("root"), null);
        Hakemisto kitten = new Hakemisto(new StringBuilder("kitten"), juurihakemisto);
        juurihakemisto.lisaa(kitten);
        Tulkki tulkki = new Tulkki(ui, juurihakemisto);
        tulkki.tyohakemisto(kitten);

        tulkki.suorita();

        assertEquals(1, tulkki.tyohakemisto().tiedot().koko());
        assertEquals("root", tulkki.tyohakemisto().nimi().toString());
    }

    // Siirrytään juurihakemistoon.
    @Test
    public void testCdChangeToTopDir() {
        TestiUI ui = new TestiUI();
        ui.syotteet.lisaaLoppuun("cd");
        ui.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("root"), null);
        Hakemisto cat = new Hakemisto(new StringBuilder("cat"), juurihakemisto);
        juurihakemisto.lisaa(cat);
        Hakemisto kitten = new Hakemisto(new StringBuilder("kitten"), cat);
        cat.lisaa(kitten);
        Tulkki tulkki = new Tulkki(ui, juurihakemisto);
        tulkki.tyohakemisto(kitten);

        tulkki.suorita();

        assertEquals(1, tulkki.tyohakemisto().tiedot().koko());
        assertEquals("root", tulkki.tyohakemisto().nimi().toString());
    }

    // Tulostetaan virheilmoitus jos yritetään siirtyä juurihakemiston ylihakemistoon.
    @Test
    public void testCdErrorWhenChangingToParentOfRoot() {
        TestiUI ui = new TestiUI();
        ui.syotteet.lisaaLoppuun("cd ..");
        ui.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("root"), null);
        Tulkki tulkki = new Tulkki(ui, juurihakemisto);

        tulkki.suorita();

        assertEquals("Error!", ui.tulosteet.alkio(1));
    }

    // Tulostetaan sijainti ennen kehotetta.
    @Test
    public void testPromptInRoot() {
        TestiUI ui = new TestiUI();
        ui.syotteet.lisaaLoppuun("exit");
        Tulkki tulkki = new Tulkki(ui);

        tulkki.suorita();

        assertEquals("/>", ui.kehotteet.alkio(0));
    }

    @Test
    public void testPromptWithPath() {
        TestiUI ui = new TestiUI();
        ui.syotteet.lisaaLoppuun("cd cat");
        ui.syotteet.lisaaLoppuun("cd kitten");
        ui.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("/"), null);
        Hakemisto cat = new Hakemisto(new StringBuilder("cat"), juurihakemisto);
        juurihakemisto.lisaa(cat);
        Hakemisto kitten = new Hakemisto(new StringBuilder("kitten"), cat);
        cat.lisaa(kitten);
        Tulkki tulkki = new Tulkki(ui, juurihakemisto);
        tulkki.tyohakemisto(kitten);

        tulkki.suorita();

        assertEquals("/cat/kitten/>", ui.kehotteet.alkio(1));
    }

    @Test
    public void testUpdatePromptToRoot() {
        TestiUI ui = new TestiUI();
        ui.syotteet.lisaaLoppuun("cd");
        ui.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("/"), null);
        Hakemisto kitten = new Hakemisto(new StringBuilder("kitten"), juurihakemisto);
        juurihakemisto.lisaa(kitten);
        Hakemisto kitten2 = new Hakemisto(new StringBuilder("kitten2"), kitten);
        kitten.lisaa(kitten2);
        Tulkki tulkki = new Tulkki(ui, juurihakemisto);
        tulkki.tyohakemisto(kitten2);

        tulkki.suorita();

        assertEquals("/>", ui.kehotteet.alkio(1));
    }

    @Test
    public void testFindWithDirs() {
        TestiUI ui = new TestiUI();
        ui.syotteet.lisaaLoppuun("find");
        ui.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("/"), null);
        Hakemisto kitten1 = new Hakemisto(new StringBuilder("kitten1"), juurihakemisto);
        juurihakemisto.lisaa(kitten1);
        Hakemisto kitten2 = new Hakemisto(new StringBuilder("kitten2"), juurihakemisto);
        juurihakemisto.lisaa(kitten2);
        Hakemisto kitten3 = new Hakemisto(new StringBuilder("kitten3"), kitten2);
        kitten2.lisaa(kitten3);
        Tulkki tulkki = new Tulkki(ui, juurihakemisto);

        tulkki.suorita();

        assertEquals("/kitten1/ 0", ui.tulosteet.alkio(1));
        assertEquals("/kitten2/ 1", ui.tulosteet.alkio(2));
        assertEquals("/kitten2/kitten3/ 0", ui.tulosteet.alkio(3));
    }

    @Test
    public void testFindWithFilesAndDirs() {
        TestiUI ui = new TestiUI();
        ui.syotteet.lisaaLoppuun("find");
        ui.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("/"), null);
        Hakemisto kitten1 = new Hakemisto(new StringBuilder("kitten1"), juurihakemisto);
        juurihakemisto.lisaa(kitten1);
        Hakemisto kitten2 = new Hakemisto(new StringBuilder("kitten2"), juurihakemisto);
        juurihakemisto.lisaa(kitten2);
        Hakemisto kitten3 = new Hakemisto(new StringBuilder("kitten3"), kitten2);
        kitten2.lisaa(kitten3);
        Tiedosto t1 = new Tiedosto(new StringBuilder("party_kitten.jpg"), 256);
        kitten2.lisaa(t1);
        Tiedosto t2 = new Tiedosto(new StringBuilder("farty_kitten.jpg"), 123);
        kitten2.lisaa(t2);
        Tulkki tulkki = new Tulkki(ui, juurihakemisto);

        tulkki.suorita();

        assertEquals("/kitten1/ 0", ui.tulosteet.alkio(1));
        assertEquals("/kitten2/ 3", ui.tulosteet.alkio(2));
        assertEquals("/kitten2/farty_kitten.jpg 123", ui.tulosteet.alkio(3));
        assertEquals("/kitten2/kitten3/ 0", ui.tulosteet.alkio(4));
        assertEquals("/kitten2/party_kitten.jpg 256", ui.tulosteet.alkio(5));
    }

    @Test
    public void testMdAndCd() {
        TestiUI ui = new TestiUI();
        ui.syotteet.lisaaLoppuun("md cat");
        ui.syotteet.lisaaLoppuun("cd cat");
        ui.syotteet.lisaaLoppuun("md kitten");
        ui.syotteet.lisaaLoppuun("cd kitten");
        ui.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("/"), null);
        Tulkki tulkki = new Tulkki(ui, juurihakemisto);

        tulkki.suorita();

        assertEquals("/cat/kitten/>", ui.kehotteet.alkio(4));
    }

}
