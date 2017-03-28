package oope;

import static org.junit.Assert.*;

import org.junit.Test;

import oope2017ht.OmaLista;

public class OmaListaTest {

    @Test
    public void tyhja() {
        OmaLista lista = new OmaLista();
        assertEquals(true, lista.onkoTyhja());
    }

    @Test
    public void yksi() {
        OmaLista lista = new OmaLista();
        lista.lisaaAlkuun("abc");
        assertEquals(false, lista.onkoTyhja());
    }

    @Test
    public void tyhjastaHaku() {
        OmaLista lista = new OmaLista();
        assertEquals(null, lista.hae("ab"));
    }

    @Test
    public void haku1VaaraAlkio() {
        OmaLista lista = new OmaLista();
        lista.lisaaAlkuun("abc");
        assertEquals(null, lista.hae("ab"));
    }

    @Test
    public void haku1OikeaAlkio() {
        OmaLista lista = new OmaLista();
        lista.lisaaAlkuun("ab");
        assertEquals("ab", lista.hae("ab"));
    }

    @Test
    public void haku1OikeaAlkioKolmesta() {
        OmaLista lista = new OmaLista();
        lista.lisaaAlkuun("Ab");
        lista.lisaaAlkuun("ab");
        lista.lisaaAlkuun("AB");
        assertEquals("ab", lista.hae("ab"));
    }

    @Test
    public void haettuaAlkiotaEiOleListassa() {
        OmaLista lista = new OmaLista();
        lista.lisaaAlkuun("Ab");
        lista.lisaaAlkuun("bb");
        lista.lisaaAlkuun("AB");
        assertEquals(null, lista.hae("ab"));
    }

    @Test
    public void nullillaHaku() {
        OmaLista lista = new OmaLista();
        lista.lisaaAlkuun("Ab");
        lista.lisaaAlkuun("bb");
        lista.lisaaAlkuun("AB");
        assertEquals(null, lista.hae(null));
    }

    @Test
    public void nullillaHakuyksiNull() {
        OmaLista lista = new OmaLista();
        lista.lisaaAlkuun("Ab");
        lista.lisaaAlkuun(null);
        lista.lisaaAlkuun("AB");
        assertEquals(null, lista.hae(null));
    }

    @Test
    public void listassaNull() {
        OmaLista lista = new OmaLista();
        lista.lisaaAlkuun("Ab");
        lista.lisaaAlkuun(null);
        lista.lisaaAlkuun("AB");
        assertEquals("AB", lista.hae("AB"));
    }

    /*
     * Poistaa listalta annettua oliota equals-mielessä vastaavan alkion.
     */

    @Test
    public void tyhjastaPoistaminen() {
        OmaLista lista = new OmaLista();
        assertEquals(null, lista.poista("ab"));
    }

    @Test
    public void eiPoistettavaaAlkiota() {
        OmaLista lista = new OmaLista();
        lista.lisaaAlkuun("Ab");
        lista.lisaaAlkuun("bb");
        lista.lisaaAlkuun("AB");
        assertEquals(null, lista.poista("ab"));
    }

    @Test
    public void alkionPoisto1kokoisestaListasta() {
        OmaLista lista = new OmaLista();
        lista.lisaaAlkuun("bb");
        assertEquals("bb", lista.poista("bb"));
    }

    @Test
    public void poistoListasta() {
        OmaLista lista = new OmaLista();
        lista.lisaaAlkuun("Ab");
        lista.lisaaAlkuun("bb");
        lista.lisaaAlkuun("AB");
        assertEquals("bb", lista.poista("bb"));
    }

    @Test
    public void parametriNull() {
        OmaLista lista = new OmaLista();
        lista.lisaaAlkuun("Ab");
        lista.lisaaAlkuun("bb");
        lista.lisaaAlkuun("AB");
        assertEquals(null, lista.poista(null));
    }

    @Test
    public void poistetaanNull() {
        OmaLista lista = new OmaLista();
        lista.lisaaAlkuun("Ab");
        lista.lisaaAlkuun(null);
        lista.lisaaAlkuun("AB");
        assertEquals(null, lista.poista(null));
    }

    /*
     * Lisää listaan järjestyksessä.
     */
    @Test
    public void lisataanTyhjaanListaan() {
        OmaLista lista = new OmaLista();
        assertEquals(true, lista.lisaa(1));
        assertEquals(1, lista.koko());
        assertEquals(1, lista.alkio(0));
    }

    @Test
    public void lisataanKolmenListaan() {
        OmaLista lista = new OmaLista();
        lista.lisaa(1);
        lista.lisaa(3);
        lista.lisaa(4);
        assertEquals(true, lista.lisaa(2));
        assertEquals(4, lista.koko());
        assertEquals(1, lista.alkio(0));
        assertEquals(2, lista.alkio(1));
        assertEquals(3, lista.alkio(2));
        assertEquals(4, lista.alkio(3));
    }

    @Test
    public void lisataanStringejaListaan() {
        OmaLista lista = new OmaLista();
        lista.lisaa("ab");
        lista.lisaa("aa");
        lista.lisaa("pier");
        lista.lisaa("ac");
        assertEquals(true, lista.lisaa("lol"));
        assertEquals(5, lista.koko());
        assertEquals("aa", lista.alkio(0));
        assertEquals("ab", lista.alkio(1));
        assertEquals("ac", lista.alkio(2));
        assertEquals("lol", lista.alkio(3));
        assertEquals("pier", lista.alkio(4));
    }

    @Test
    public void lisataanUusiObjectListaan() {
        OmaLista lista = new OmaLista();
        lista.lisaa("ab");
        lista.lisaa("aa");
        lista.lisaa("pier");
        assertEquals(false, lista.lisaa(new Object()));
        assertEquals(3, lista.koko());
        assertEquals("aa", lista.alkio(0));
        assertEquals("ab", lista.alkio(1));
        assertEquals("pier", lista.alkio(2));
    }

    @Test
    public void lisataanLoppuun() {
        OmaLista lista = new OmaLista();
        lista.lisaa(1);
        lista.lisaa(3);
        lista.lisaa(4);
        assertEquals(true, lista.lisaa(5));
        assertEquals(4, lista.koko());
        assertEquals(1, lista.alkio(0));
        assertEquals(3, lista.alkio(1));
        assertEquals(4, lista.alkio(2));
        assertEquals(5, lista.alkio(3));
    }

    @Test
    public void lisataanSama() {
        OmaLista lista = new OmaLista();
        Integer uusiKolme = new Integer(3);
        lista.lisaa(1);
        lista.lisaa(3);
        lista.lisaa(4);
        assertEquals(true, lista.lisaa(uusiKolme));
        assertEquals(4, lista.koko());
        assertEquals(1, lista.alkio(0));
        assertEquals(3, lista.alkio(1));
        assertEquals(3, lista.alkio(2));
        assertEquals(4, lista.alkio(3));
        assertEquals(true, uusiKolme == lista.alkio(2));
    }

    @Test
    public void lisataanNull() {
        OmaLista lista = new OmaLista();
        lista.lisaa(1);
        lista.lisaa(3);
        lista.lisaa(4);
        assertEquals(false, lista.lisaa(null));
        assertEquals(3, lista.koko());
        assertEquals(1, lista.alkio(0));
        assertEquals(3, lista.alkio(1));
        assertEquals(4, lista.alkio(2));
    }

}
