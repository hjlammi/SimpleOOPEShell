package oope2017ht;

import oope2017ht.OmaLista;
import oope2017ht.UI;

public class TestiUI implements UI {
    public OmaLista tulosteet = new OmaLista();
    public OmaLista syotteet = new OmaLista();

    @Override
    public void tulosta(String tuloste) {
        tulosteet.lisaaLoppuun(tuloste);
    }

    @Override
    public String lueSyote() {
        return (String)syotteet.poistaAlusta();
    }
}
