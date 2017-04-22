package oope2017ht;

import static org.junit.Assert.*;

import org.junit.Test;

import oope2017ht.Kayttoliittyma;
import oope2017ht.tiedot.Hakemisto;
import oope2017ht.tiedot.Tiedosto;

public class KayttoliittymaTest {

    // Käyttöliittymä näyttää käynnistyessään welcome-viestin.
    @Test
    public void testWelcome() {
        TestiTerminaali terminaali = new TestiTerminaali();
        terminaali.syotteet.lisaaLoppuun("exit");
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(terminaali);

        kayttoliittyma.suorita();

        assertEquals(2, terminaali.tulosteet.koko());
        assertEquals("Welcome to SOS.", terminaali.tulosteet.alkio(0));
    }

    // Käyttöliittymä näyttää virheviestin väärästä komennosta.
    @Test
    public void testError() {
        TestiTerminaali terminaali = new TestiTerminaali();
        terminaali.syotteet.lisaaLoppuun("hjkl");
        terminaali.syotteet.lisaaLoppuun("md   pics");
        terminaali.syotteet.lisaaLoppuun("exit ");
        terminaali.syotteet.lisaaLoppuun(" exit");
        terminaali.syotteet.lisaaLoppuun("exit");
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(terminaali);

        kayttoliittyma.suorita();

        assertEquals(6, terminaali.tulosteet.koko());
        assertEquals("Error!", terminaali.tulosteet.alkio(1));
        assertEquals("Error!", terminaali.tulosteet.alkio(2));
        assertEquals("Error!", terminaali.tulosteet.alkio(3));
        assertEquals("Error!", terminaali.tulosteet.alkio(4));
        assertEquals("Shell terminated.", terminaali.tulosteet.alkio(5));
    }

    // Käyttöliittymä näyttää lopetusviestin.
    @Test
    public void testExit() {
        TestiTerminaali terminaali = new TestiTerminaali();
        terminaali.syotteet.lisaaLoppuun("exit");
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(terminaali);

        kayttoliittyma.suorita();

        assertEquals(2, terminaali.tulosteet.koko());
        assertEquals("Shell terminated.", terminaali.tulosteet.alkio(1));
    }

    // Juurihakemisto on tyhjä.
    @Test
    public void testLsEmptyRoot() {
        TestiTerminaali terminaali = new TestiTerminaali();
        terminaali.syotteet.lisaaLoppuun("ls");
        terminaali.syotteet.lisaaLoppuun("exit");
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(terminaali);

        kayttoliittyma.suorita();

        assertEquals(2, terminaali.tulosteet.koko());
    }

    // Ls listaa alihakemistot ja tiedostot.
    @Test
    public void testLsDirsAndFiles() {
        TestiTerminaali terminaali = new TestiTerminaali();
        terminaali.syotteet.lisaaLoppuun("ls");
        terminaali.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("root"), null);
        Tiedosto jee = new Tiedosto(new StringBuilder("jee.txt"), 11);
        juurihakemisto.lisaa(jee);
        Hakemisto foo = new Hakemisto(new StringBuilder("foo"), juurihakemisto);
        juurihakemisto.lisaa(foo);
        Tiedosto t = new Tiedosto(new StringBuilder("foo.txt"), 123);
        foo.lisaa(t);
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(terminaali, juurihakemisto);
        kayttoliittyma.tulkki.tyohakemisto(foo);

        kayttoliittyma.suorita();

        assertEquals(3, terminaali.tulosteet.koko());
        assertEquals("foo.txt 123", terminaali.tulosteet.alkio(1));
        assertEquals("foo", kayttoliittyma.tulkki.tyohakemisto().nimi().toString());
    }

    @Test
    public void testLsWithAnArgument() {
        TestiTerminaali terminaali = new TestiTerminaali();
        terminaali.syotteet.lisaaLoppuun("ls jee.txt");
        terminaali.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("root"), null);
        Hakemisto foo = new Hakemisto(new StringBuilder("foo"), juurihakemisto);
        juurihakemisto.lisaa(foo);
        Tiedosto jee = new Tiedosto(new StringBuilder("jee.txt"), 11);
        foo.lisaa(jee);
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(terminaali, juurihakemisto);
        kayttoliittyma.tulkki.tyohakemisto(foo);

        kayttoliittyma.suorita();

        assertEquals(3, terminaali.tulosteet.koko());
        assertEquals("jee.txt 11", terminaali.tulosteet.alkio(1));
    }

    @Test
    public void testLsWithNonexistentName() {
        TestiTerminaali terminaali = new TestiTerminaali();
        terminaali.syotteet.lisaaLoppuun("ls joo.txt");
        terminaali.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("root"), null);
        Hakemisto foo = new Hakemisto(new StringBuilder("foo"), juurihakemisto);
        juurihakemisto.lisaa(foo);
        Tiedosto jee = new Tiedosto(new StringBuilder("jee.txt"), 11);
        juurihakemisto.lisaa(jee);
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(terminaali, juurihakemisto);

        kayttoliittyma.suorita();

        assertEquals(3, terminaali.tulosteet.koko());
        assertEquals("Error!", terminaali.tulosteet.alkio(1));
    }

    @Test
    public void testMdNewDir() {
        TestiTerminaali terminaali = new TestiTerminaali();
        terminaali.syotteet.lisaaLoppuun("md bar");
        terminaali.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("root"), null);
        Hakemisto foo = new Hakemisto(new StringBuilder("foo"), juurihakemisto);
        juurihakemisto.lisaa(foo);
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(terminaali, juurihakemisto);
        kayttoliittyma.tulkki.tyohakemisto(foo);

        kayttoliittyma.suorita();

        assertEquals(1, kayttoliittyma.tulkki.tyohakemisto().tiedot().koko());
        Hakemisto alihakemisto = (Hakemisto)kayttoliittyma.tulkki.tyohakemisto().tiedot().alkio(0);
        assertEquals("bar", alihakemisto.nimi().toString());
        assertEquals(foo, alihakemisto.ylihakemisto());
    }

    // Samanniminen hakemisto on jo.
    @Test
    public void testMdWithSameName() {
        TestiTerminaali terminaali = new TestiTerminaali();
        terminaali.syotteet.lisaaLoppuun("md foo");
        terminaali.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("root"), null);
        Hakemisto foo = new Hakemisto(new StringBuilder("foo"), juurihakemisto);
        juurihakemisto.lisaa(foo);
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(terminaali, juurihakemisto);

        kayttoliittyma.suorita();

        assertEquals(1, kayttoliittyma.tulkki.tyohakemisto().tiedot().koko());
        assertEquals("Error!", terminaali.tulosteet.alkio(1));
    }

    // Luodaan uusi tiedosto.
    @Test
    public void testMfNewFile() {
        TestiTerminaali terminaali = new TestiTerminaali();
        terminaali.syotteet.lisaaLoppuun("mf foo.txt 123");
        terminaali.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("root"), null);
        Hakemisto foo = new Hakemisto(new StringBuilder("foo"), juurihakemisto);
        juurihakemisto.lisaa(foo);
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(terminaali, juurihakemisto);
        kayttoliittyma.tulkki.tyohakemisto(foo);

        kayttoliittyma.suorita();

        assertEquals(1, kayttoliittyma.tulkki.tyohakemisto().tiedot().koko());
        Tiedosto lisatty = (Tiedosto)kayttoliittyma.tulkki.tyohakemisto().tiedot().alkio(0);
        assertEquals("foo.txt", lisatty.nimi().toString());
    }

    // Liikaa välilyöntejä ja samanniminen tiedosto on jo olemassa.
    @Test
    public void testMfWithSpaces() {
        TestiTerminaali terminaali = new TestiTerminaali();
        terminaali.syotteet.lisaaLoppuun("mf foo.txt 123");
        terminaali.syotteet.lisaaLoppuun("mf jee.txt  123");
        terminaali.syotteet.lisaaLoppuun("mf  jee.txt 123");
        terminaali.syotteet.lisaaLoppuun("mf foo.txt 123");
        terminaali.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("root"), null);
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(terminaali, juurihakemisto);

        kayttoliittyma.suorita();

        assertEquals(1, juurihakemisto.tiedot().koko());
        assertEquals("Error!", terminaali.tulosteet.alkio(1));
        assertEquals("Error!", terminaali.tulosteet.alkio(3));
    }

    // Koon täytyy olla <= 0.
    @Test
    public void testMfNegativeSize() {
        TestiTerminaali terminaali = new TestiTerminaali();
        terminaali.syotteet.lisaaLoppuun("mf foo.txt -123");
        terminaali.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("root"), null);
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(terminaali, juurihakemisto);

        kayttoliittyma.suorita();

        assertEquals(0, juurihakemisto.tiedot().koko());
        assertEquals("Error!", terminaali.tulosteet.alkio(1));
    }

    // Nimetään tieto uudelleen.
    @Test
    public void testMvRename() {
        TestiTerminaali terminaali = new TestiTerminaali();
        terminaali.syotteet.lisaaLoppuun("mv fool.txt foo.txt");
        terminaali.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("root"), null);
        Hakemisto foo = new Hakemisto(new StringBuilder("foo"), juurihakemisto);
        Tiedosto foo2 = new Tiedosto(new StringBuilder("foo.txt"), 234);
        juurihakemisto.lisaa(foo2);
        Hakemisto bar = new Hakemisto(new StringBuilder("bar"), foo);
        foo.lisaa(bar);
        Tiedosto fool = new Tiedosto(new StringBuilder("fool.txt"), 123);
        bar.lisaa(fool);
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(terminaali, juurihakemisto);
        kayttoliittyma.tulkki.tyohakemisto(bar);

        kayttoliittyma.suorita();

        assertEquals(1, kayttoliittyma.tulkki.tyohakemisto().tiedot().koko());
        Tiedosto uusiNimi = (Tiedosto)kayttoliittyma.tulkki.tyohakemisto().tiedot().alkio(0);
        assertEquals("foo.txt", uusiNimi.nimi().toString());
    }

    // Uusi nimi ei saa olla jo käytössä hakemistossa.
    @Test
    public void testMvRenameWithExistingName() {
        TestiTerminaali terminaali = new TestiTerminaali();
        terminaali.syotteet.lisaaLoppuun("mv fool.txt foo.txt");
        terminaali.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("root"), null);
        Tiedosto fool = new Tiedosto(new StringBuilder("fool.txt"), 123);
        juurihakemisto.lisaa(fool);
        Tiedosto foo = new Tiedosto(new StringBuilder("foo.txt"), 243);
        juurihakemisto.lisaa(foo);
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(terminaali, juurihakemisto);

        kayttoliittyma.suorita();

        assertEquals(2, juurihakemisto.tiedot().koko());
        assertEquals("Error!", terminaali.tulosteet.alkio(1));
        Tiedosto vaihdettava = (Tiedosto)juurihakemisto.tiedot().alkio(1);
        assertEquals("fool.txt", vaihdettava.nimi().toString());
    }

    // Vaihdettavan nimistä ei ole.
    @Test
    public void testMvRenameWithWrongName() {
        TestiTerminaali terminaali = new TestiTerminaali();
        terminaali.syotteet.lisaaLoppuun("mv fool.txt foo.txt");
        terminaali.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("root"), null);
        Tiedosto cats = new Tiedosto(new StringBuilder("cats.txt"), 123);
        juurihakemisto.lisaa(cats);
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(terminaali, juurihakemisto);

        kayttoliittyma.suorita();

        assertEquals(1, kayttoliittyma.tulkki.tyohakemisto().tiedot().koko());
        assertEquals("Error!", terminaali.tulosteet.alkio(1));
        Tiedosto vaihdettava = (Tiedosto)kayttoliittyma.tulkki.tyohakemisto().tiedot().alkio(0);
        assertEquals("cats.txt", vaihdettava.nimi().toString());
    }

    // Kopioidaan tiedosto.
    @Test
    public void testCpCopyFile() {
        TestiTerminaali terminaali = new TestiTerminaali();
        terminaali.syotteet.lisaaLoppuun("cp fool.txt foo.txt");
        terminaali.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("root"), null);
        Hakemisto foo = new Hakemisto(new StringBuilder("foo"), juurihakemisto);
        juurihakemisto.lisaa(foo);
        Tiedosto fool = new Tiedosto(new StringBuilder("fool.txt"), 123);
        foo.lisaa(fool);
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(terminaali, juurihakemisto);
        kayttoliittyma.tulkki.tyohakemisto(foo);

        kayttoliittyma.suorita();

        assertEquals(2, kayttoliittyma.tulkki.tyohakemisto().tiedot().koko());
        Tiedosto kopio = (Tiedosto)kayttoliittyma.tulkki.tyohakemisto().tiedot().alkio(0);
        assertEquals("foo.txt", kopio.nimi().toString());
    }

    // Hakemistoa ei voi kopioida.
    @Test
    public void testCpCopyDir() {
        TestiTerminaali terminaali = new TestiTerminaali();
        terminaali.syotteet.lisaaLoppuun("cp fool foo");
        terminaali.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("root"), null);
        Hakemisto fool = new Hakemisto(new StringBuilder("foo"), juurihakemisto);
        juurihakemisto.lisaa(fool);
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(terminaali, juurihakemisto);

        kayttoliittyma.suorita();

        assertEquals(1, juurihakemisto.tiedot().koko());
        assertEquals("Error!", terminaali.tulosteet.alkio(1));
    }

    // Kopioitavan nimistä ei ole hakemistossa.
    @Test
    public void testCpFileNotFound() {
        TestiTerminaali terminaali = new TestiTerminaali();
        terminaali.syotteet.lisaaLoppuun("cp fool.txt foo.txt");
        terminaali.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("root"), null);
        Tiedosto cat = new Tiedosto(new StringBuilder("cat.txt"), 123);
        juurihakemisto.lisaa(cat);
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(terminaali, juurihakemisto);

        kayttoliittyma.suorita();

        assertEquals(1, juurihakemisto.tiedot().koko());
        assertEquals("Error!", terminaali.tulosteet.alkio(1));
    }

    // Kopion nimi varattu.
    @Test
    public void testCpCopyNameTaken() {
        TestiTerminaali terminaali = new TestiTerminaali();
        terminaali.syotteet.lisaaLoppuun("cp cat.txt kitten.txt");
        terminaali.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("root"), null);
        Tiedosto cat = new Tiedosto(new StringBuilder("cat.txt"), 123);
        juurihakemisto.lisaa(cat);
        Tiedosto kitten = new Tiedosto(new StringBuilder("kitten.txt"), 123);
        juurihakemisto.lisaa(kitten);
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(terminaali, juurihakemisto);

        kayttoliittyma.suorita();

        assertEquals(2, juurihakemisto.tiedot().koko());
        assertEquals("Error!", terminaali.tulosteet.alkio(1));
    }

    // Tiedoston poistaminen.
    @Test
    public void testRmRemoveFile() {
        TestiTerminaali terminaali = new TestiTerminaali();
        terminaali.syotteet.lisaaLoppuun("rm cat.txt");
        terminaali.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("root"), null);
        Hakemisto foo = new Hakemisto(new StringBuilder("foo"), juurihakemisto);
        juurihakemisto.lisaa(foo);
        Tiedosto cat = new Tiedosto(new StringBuilder("cat.txt"), 123);
        foo.lisaa(cat);
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(terminaali, juurihakemisto);
        kayttoliittyma.tulkki.tyohakemisto(foo);

        kayttoliittyma.suorita();

        assertEquals(0, kayttoliittyma.tulkki.tyohakemisto().tiedot().koko());
        assertEquals("foo", kayttoliittyma.tulkki.tyohakemisto().nimi().toString());
    }

    // Hakemiston poistaminen.
    @Test
    public void testRmRemoveDir() {
        TestiTerminaali terminaali = new TestiTerminaali();
        terminaali.syotteet.lisaaLoppuun("rm cat");
        terminaali.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("root"), null);
        Hakemisto cat = new Hakemisto(new StringBuilder("cat"), juurihakemisto);
        juurihakemisto.lisaa(cat);
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(terminaali, juurihakemisto);

        kayttoliittyma.suorita();

        assertEquals(0, juurihakemisto.tiedot().koko());
    }

    // Poisto ei onnistu, jos hakemistoa tai tiedostoa ei ole.
    @Test
    public void testRmNothingToRemove() {
        TestiTerminaali terminaali = new TestiTerminaali();
        terminaali.syotteet.lisaaLoppuun("rm cat");
        terminaali.syotteet.lisaaLoppuun("rm cat");
        terminaali.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("root"), null);
        Hakemisto kitten = new Hakemisto(new StringBuilder("kitten"), juurihakemisto);
        juurihakemisto.lisaa(kitten);
        Tiedosto cat = new Tiedosto(new StringBuilder("cat.jpg"), 234);
        juurihakemisto.lisaa(cat);
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(terminaali, juurihakemisto);

        kayttoliittyma.suorita();

        assertEquals(2, juurihakemisto.tiedot().koko());
        assertEquals("Error!", terminaali.tulosteet.alkio(1));
        assertEquals("Error!", terminaali.tulosteet.alkio(2));
    }

    // Siirrytään juurihakemiston alihakemistoon.
    @Test
    public void testCdChangeToSubdir() {
        TestiTerminaali terminaali = new TestiTerminaali();
        // Tiedostoon ei voi siirtyä.
        terminaali.syotteet.lisaaLoppuun("cd kitten123.jpg");
        terminaali.syotteet.lisaaLoppuun("cd kitten");
        // Virheellinen alihakemiston nimi.
        terminaali.syotteet.lisaaLoppuun("cd kiten");
        terminaali.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("root"), null);
        Hakemisto kitten = new Hakemisto(new StringBuilder("kitten"), juurihakemisto);
        juurihakemisto.lisaa(kitten);
        Tiedosto t = new Tiedosto(new StringBuilder("kitten123.jpg"), 123);
        kitten.lisaa(t);
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(terminaali, juurihakemisto);

        kayttoliittyma.suorita();

        assertEquals(1, kayttoliittyma.tulkki.tyohakemisto().tiedot().koko());
        assertEquals("Error!", terminaali.tulosteet.alkio(1));
        assertEquals("Error!", terminaali.tulosteet.alkio(2));
        assertEquals("kitten", kayttoliittyma.tulkki.tyohakemisto().nimi().toString());
    }

    // Siirrytään ylihakemistoon.
    @Test
    public void testCdChangeToParentDir() {
        TestiTerminaali terminaali = new TestiTerminaali();
        terminaali.syotteet.lisaaLoppuun("cd ..");
        terminaali.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("root"), null);
        Hakemisto kitten = new Hakemisto(new StringBuilder("kitten"), juurihakemisto);
        juurihakemisto.lisaa(kitten);
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(terminaali, juurihakemisto);
        kayttoliittyma.tulkki.tyohakemisto(kitten);

        kayttoliittyma.suorita();

        assertEquals(1, kayttoliittyma.tulkki.tyohakemisto().tiedot().koko());
        assertEquals("root", kayttoliittyma.tulkki.tyohakemisto().nimi().toString());
    }

    // Siirrytään juurihakemistoon.
    @Test
    public void testCdChangeToTopDir() {
        TestiTerminaali terminaali = new TestiTerminaali();
        terminaali.syotteet.lisaaLoppuun("cd");
        terminaali.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("root"), null);
        Hakemisto cat = new Hakemisto(new StringBuilder("cat"), juurihakemisto);
        juurihakemisto.lisaa(cat);
        Hakemisto kitten = new Hakemisto(new StringBuilder("kitten"), cat);
        cat.lisaa(kitten);
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(terminaali, juurihakemisto);
        kayttoliittyma.tulkki.tyohakemisto(kitten);

        kayttoliittyma.suorita();

        assertEquals(1, kayttoliittyma.tulkki.tyohakemisto().tiedot().koko());
        assertEquals("root", kayttoliittyma.tulkki.tyohakemisto().nimi().toString());
    }

    // Tulostetaan virheilmoitus jos yritetään siirtyä juurihakemiston ylihakemistoon.
    @Test
    public void testCdErrorWhenChangingToParentOfRoot() {
        TestiTerminaali terminaali = new TestiTerminaali();
        terminaali.syotteet.lisaaLoppuun("cd ..");
        terminaali.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto(new StringBuilder("root"), null);
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(terminaali, juurihakemisto);

        kayttoliittyma.suorita();

        assertEquals("Error!", terminaali.tulosteet.alkio(1));
    }

    // Tulostetaan sijainti ennen kehotetta.
    @Test
    public void testPromptInRoot() {
        TestiTerminaali terminaali = new TestiTerminaali();
        terminaali.syotteet.lisaaLoppuun("exit");
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(terminaali);

        kayttoliittyma.suorita();

        assertEquals("/>", terminaali.kehotteet.alkio(0));
    }

    @Test
    public void testPromptWithPath() {
        TestiTerminaali terminaali = new TestiTerminaali();
        terminaali.syotteet.lisaaLoppuun("cd cat");
        terminaali.syotteet.lisaaLoppuun("cd kitten");
        terminaali.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto();
        Hakemisto cat = new Hakemisto(new StringBuilder("cat"), juurihakemisto);
        juurihakemisto.lisaa(cat);
        Hakemisto kitten = new Hakemisto(new StringBuilder("kitten"), cat);
        cat.lisaa(kitten);
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(terminaali, juurihakemisto);
        kayttoliittyma.tulkki.tyohakemisto(kitten);

        kayttoliittyma.suorita();

        assertEquals("/cat/kitten/>", terminaali.kehotteet.alkio(1));
    }

    @Test
    public void testUpdatePromptToRoot() {
        TestiTerminaali terminaali = new TestiTerminaali();
        terminaali.syotteet.lisaaLoppuun("cd");
        terminaali.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto();
        Hakemisto kitten = new Hakemisto(new StringBuilder("kitten"), juurihakemisto);
        juurihakemisto.lisaa(kitten);
        Hakemisto kitten2 = new Hakemisto(new StringBuilder("kitten2"), kitten);
        kitten.lisaa(kitten2);
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(terminaali, juurihakemisto);
        kayttoliittyma.tulkki.tyohakemisto(kitten2);

        kayttoliittyma.suorita();

        assertEquals("/>", terminaali.kehotteet.alkio(1));
    }

    @Test
    public void testFindWithDirs() {
        TestiTerminaali terminaali = new TestiTerminaali();
        terminaali.syotteet.lisaaLoppuun("find");
        terminaali.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto();
        Hakemisto kitten1 = new Hakemisto(new StringBuilder("kitten1"), juurihakemisto);
        juurihakemisto.lisaa(kitten1);
        Hakemisto kitten2 = new Hakemisto(new StringBuilder("kitten2"), juurihakemisto);
        juurihakemisto.lisaa(kitten2);
        Hakemisto kitten3 = new Hakemisto(new StringBuilder("kitten3"), kitten2);
        kitten2.lisaa(kitten3);
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(terminaali, juurihakemisto);

        kayttoliittyma.suorita();

        assertEquals("/kitten1/ 0", terminaali.tulosteet.alkio(1));
        assertEquals("/kitten2/ 1", terminaali.tulosteet.alkio(2));
        assertEquals("/kitten2/kitten3/ 0", terminaali.tulosteet.alkio(3));
    }

    @Test
    public void testFindWithFilesAndDirs() {
        TestiTerminaali terminaali = new TestiTerminaali();
        terminaali.syotteet.lisaaLoppuun("find");
        terminaali.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto();
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
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(terminaali, juurihakemisto);

        kayttoliittyma.suorita();

        assertEquals("/kitten1/ 0", terminaali.tulosteet.alkio(1));
        assertEquals("/kitten2/ 3", terminaali.tulosteet.alkio(2));
        assertEquals("/kitten2/farty_kitten.jpg 123", terminaali.tulosteet.alkio(3));
        assertEquals("/kitten2/kitten3/ 0", terminaali.tulosteet.alkio(4));
        assertEquals("/kitten2/party_kitten.jpg 256", terminaali.tulosteet.alkio(5));
    }

    @Test
    public void testMdAndCd() {
        TestiTerminaali terminaali = new TestiTerminaali();
        terminaali.syotteet.lisaaLoppuun("md cat");
        terminaali.syotteet.lisaaLoppuun("cd cat");
        terminaali.syotteet.lisaaLoppuun("md kitten");
        terminaali.syotteet.lisaaLoppuun("cd kitten");
        terminaali.syotteet.lisaaLoppuun("exit");
        Hakemisto juurihakemisto = new Hakemisto();
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(terminaali, juurihakemisto);

        kayttoliittyma.suorita();

        assertEquals("/cat/kitten/>", terminaali.kehotteet.alkio(4));
    }

}
