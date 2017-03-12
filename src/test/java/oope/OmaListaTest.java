package oope;

import static org.junit.Assert.*;

import org.junit.Test;

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
}
